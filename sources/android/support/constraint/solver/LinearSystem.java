package android.support.constraint.solver;

import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;

public class LinearSystem {
    private static final boolean DEBUG = false;
    public static final boolean FULL_DEBUG = false;
    private static int POOL_SIZE = 1000;
    public static Metrics sMetrics;
    private int TABLE_SIZE;
    public boolean graphOptimizer;
    private boolean[] mAlreadyTestedCandidates;
    final Cache mCache;
    private Row mGoal;
    private int mMaxColumns;
    private int mMaxRows;
    int mNumColumns;
    int mNumRows;
    private SolverVariable[] mPoolVariables;
    private int mPoolVariablesCount;
    ArrayRow[] mRows;
    private final Row mTempGoal;
    private HashMap<String, SolverVariable> mVariables;
    int mVariablesID;
    private ArrayRow[] tempClientsCopy;

    interface Row {
        void addError(SolverVariable solverVariable);

        void clear();

        SolverVariable getKey();

        SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] zArr);

        void initFromRow(Row row);

        boolean isEmpty();
    }

    private String getDisplayStrength(int i) {
        return i == 1 ? "LOW" : i == 2 ? "MEDIUM" : i == 3 ? "HIGH" : i == 4 ? "HIGHEST" : i == 5 ? "EQUALITY" : i == 6 ? "FIXED" : "NONE";
    }

    public LinearSystem() {
        this.mVariablesID = 0;
        this.mVariables = null;
        this.TABLE_SIZE = 32;
        this.mMaxColumns = this.TABLE_SIZE;
        this.mRows = null;
        this.graphOptimizer = false;
        this.mAlreadyTestedCandidates = new boolean[this.TABLE_SIZE];
        this.mNumColumns = 1;
        this.mNumRows = 0;
        this.mMaxRows = this.TABLE_SIZE;
        this.mPoolVariables = new SolverVariable[POOL_SIZE];
        this.mPoolVariablesCount = 0;
        this.tempClientsCopy = new ArrayRow[this.TABLE_SIZE];
        this.mRows = new ArrayRow[this.TABLE_SIZE];
        releaseRows();
        this.mCache = new Cache();
        this.mGoal = new GoalRow(this.mCache);
        this.mTempGoal = new ArrayRow(this.mCache);
    }

    public void fillMetrics(Metrics metrics) {
        sMetrics = metrics;
    }

    public static Metrics getMetrics() {
        return sMetrics;
    }

    private void increaseTableSize() {
        this.TABLE_SIZE *= 2;
        this.mRows = (ArrayRow[]) Arrays.copyOf(this.mRows, this.TABLE_SIZE);
        this.mCache.mIndexedVariables = (SolverVariable[]) Arrays.copyOf(this.mCache.mIndexedVariables, this.TABLE_SIZE);
        this.mAlreadyTestedCandidates = new boolean[this.TABLE_SIZE];
        this.mMaxColumns = this.TABLE_SIZE;
        this.mMaxRows = this.TABLE_SIZE;
        if (sMetrics != null) {
            sMetrics.tableSizeIncrease++;
            sMetrics.maxTableSize = Math.max(sMetrics.maxTableSize, (long) this.TABLE_SIZE);
            sMetrics.lastTableSize = sMetrics.maxTableSize;
        }
    }

    private void releaseRows() {
        for (int i = 0; i < this.mRows.length; i++) {
            ArrayRow arrayRow = this.mRows[i];
            if (arrayRow != null) {
                this.mCache.arrayRowPool.release(arrayRow);
            }
            this.mRows[i] = null;
        }
    }

    public void reset() {
        for (SolverVariable solverVariable : this.mCache.mIndexedVariables) {
            if (solverVariable != null) {
                solverVariable.reset();
            }
        }
        this.mCache.solverVariablePool.releaseAll(this.mPoolVariables, this.mPoolVariablesCount);
        this.mPoolVariablesCount = 0;
        Arrays.fill(this.mCache.mIndexedVariables, (Object) null);
        if (this.mVariables != null) {
            this.mVariables.clear();
        }
        this.mVariablesID = 0;
        this.mGoal.clear();
        this.mNumColumns = 1;
        for (int i = 0; i < this.mNumRows; i++) {
            this.mRows[i].used = false;
        }
        releaseRows();
        this.mNumRows = 0;
    }

    public SolverVariable createObjectVariable(Object obj) {
        SolverVariable solverVariable = null;
        if (obj == null) {
            return null;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        if (obj instanceof ConstraintAnchor) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) obj;
            solverVariable = constraintAnchor.getSolverVariable();
            if (solverVariable == null) {
                constraintAnchor.resetSolverVariable(this.mCache);
                solverVariable = constraintAnchor.getSolverVariable();
            }
            if (solverVariable.id == -1 || solverVariable.id > this.mVariablesID || this.mCache.mIndexedVariables[solverVariable.id] == null) {
                if (solverVariable.id != -1) {
                    solverVariable.reset();
                }
                this.mVariablesID++;
                this.mNumColumns++;
                solverVariable.id = this.mVariablesID;
                solverVariable.mType = SolverVariable.Type.UNRESTRICTED;
                this.mCache.mIndexedVariables[this.mVariablesID] = solverVariable;
            }
        }
        return solverVariable;
    }

    public ArrayRow createRow() {
        ArrayRow acquire = this.mCache.arrayRowPool.acquire();
        if (acquire == null) {
            acquire = new ArrayRow(this.mCache);
        } else {
            acquire.reset();
        }
        SolverVariable.increaseErrorId();
        return acquire;
    }

    public SolverVariable createSlackVariable() {
        if (sMetrics != null) {
            sMetrics.slackvariables++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(SolverVariable.Type.SLACK, (String) null);
        this.mVariablesID++;
        this.mNumColumns++;
        acquireSolverVariable.id = this.mVariablesID;
        this.mCache.mIndexedVariables[this.mVariablesID] = acquireSolverVariable;
        return acquireSolverVariable;
    }

    public SolverVariable createExtraVariable() {
        if (sMetrics != null) {
            sMetrics.extravariables++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(SolverVariable.Type.SLACK, (String) null);
        this.mVariablesID++;
        this.mNumColumns++;
        acquireSolverVariable.id = this.mVariablesID;
        this.mCache.mIndexedVariables[this.mVariablesID] = acquireSolverVariable;
        return acquireSolverVariable;
    }

    private void addError(ArrayRow arrayRow) {
        arrayRow.addError(this, 0);
    }

    private void addSingleError(ArrayRow arrayRow, int i) {
        addSingleError(arrayRow, i, 0);
    }

    /* access modifiers changed from: package-private */
    public void addSingleError(ArrayRow arrayRow, int i, int i2) {
        arrayRow.addSingleError(createErrorVariable(i2, (String) null), i);
    }

    private SolverVariable createVariable(String str, SolverVariable.Type type) {
        if (sMetrics != null) {
            sMetrics.variables++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(type, (String) null);
        acquireSolverVariable.setName(str);
        this.mVariablesID++;
        this.mNumColumns++;
        acquireSolverVariable.id = this.mVariablesID;
        if (this.mVariables == null) {
            this.mVariables = new HashMap<>();
        }
        this.mVariables.put(str, acquireSolverVariable);
        this.mCache.mIndexedVariables[this.mVariablesID] = acquireSolverVariable;
        return acquireSolverVariable;
    }

    public SolverVariable createErrorVariable(int i, String str) {
        if (sMetrics != null) {
            sMetrics.errors++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable = acquireSolverVariable(SolverVariable.Type.ERROR, str);
        this.mVariablesID++;
        this.mNumColumns++;
        acquireSolverVariable.id = this.mVariablesID;
        acquireSolverVariable.strength = i;
        this.mCache.mIndexedVariables[this.mVariablesID] = acquireSolverVariable;
        this.mGoal.addError(acquireSolverVariable);
        return acquireSolverVariable;
    }

    private SolverVariable acquireSolverVariable(SolverVariable.Type type, String str) {
        SolverVariable acquire = this.mCache.solverVariablePool.acquire();
        if (acquire == null) {
            acquire = new SolverVariable(type, str);
            acquire.setType(type, str);
        } else {
            acquire.reset();
            acquire.setType(type, str);
        }
        if (this.mPoolVariablesCount >= POOL_SIZE) {
            POOL_SIZE *= 2;
            this.mPoolVariables = (SolverVariable[]) Arrays.copyOf(this.mPoolVariables, POOL_SIZE);
        }
        SolverVariable[] solverVariableArr = this.mPoolVariables;
        int i = this.mPoolVariablesCount;
        this.mPoolVariablesCount = i + 1;
        solverVariableArr[i] = acquire;
        return acquire;
    }

    /* access modifiers changed from: package-private */
    public Row getGoal() {
        return this.mGoal;
    }

    /* access modifiers changed from: package-private */
    public ArrayRow getRow(int i) {
        return this.mRows[i];
    }

    /* access modifiers changed from: package-private */
    public float getValueFor(String str) {
        SolverVariable variable = getVariable(str, SolverVariable.Type.UNRESTRICTED);
        if (variable == null) {
            return 0.0f;
        }
        return variable.computedValue;
    }

    public int getObjectVariableValue(Object obj) {
        SolverVariable solverVariable = ((ConstraintAnchor) obj).getSolverVariable();
        if (solverVariable != null) {
            return (int) (solverVariable.computedValue + 0.5f);
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public SolverVariable getVariable(String str, SolverVariable.Type type) {
        if (this.mVariables == null) {
            this.mVariables = new HashMap<>();
        }
        SolverVariable solverVariable = this.mVariables.get(str);
        return solverVariable == null ? createVariable(str, type) : solverVariable;
    }

    public void minimize() throws Exception {
        if (sMetrics != null) {
            sMetrics.minimize++;
        }
        if (this.graphOptimizer) {
            if (sMetrics != null) {
                sMetrics.graphOptimizer++;
            }
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= this.mNumRows) {
                    z = true;
                    break;
                } else if (!this.mRows[i].isSimpleDefinition) {
                    break;
                } else {
                    i++;
                }
            }
            if (!z) {
                minimizeGoal(this.mGoal);
                return;
            }
            if (sMetrics != null) {
                sMetrics.fullySolved++;
            }
            computeValues();
            return;
        }
        minimizeGoal(this.mGoal);
    }

    /* access modifiers changed from: package-private */
    public void minimizeGoal(Row row) throws Exception {
        if (sMetrics != null) {
            sMetrics.minimizeGoal++;
            sMetrics.maxVariables = Math.max(sMetrics.maxVariables, (long) this.mNumColumns);
            sMetrics.maxRows = Math.max(sMetrics.maxRows, (long) this.mNumRows);
        }
        updateRowFromVariables((ArrayRow) row);
        enforceBFS(row);
        optimize(row, false);
        computeValues();
    }

    private final void updateRowFromVariables(ArrayRow arrayRow) {
        if (this.mNumRows > 0) {
            arrayRow.variables.updateFromSystem(arrayRow, this.mRows);
            if (arrayRow.variables.currentSize == 0) {
                arrayRow.isSimpleDefinition = true;
            }
        }
    }

    public void addConstraint(ArrayRow arrayRow) {
        SolverVariable pickPivot;
        if (arrayRow != null) {
            if (sMetrics != null) {
                sMetrics.constraints++;
                if (arrayRow.isSimpleDefinition) {
                    sMetrics.simpleconstraints++;
                }
            }
            if (this.mNumRows + 1 >= this.mMaxRows || this.mNumColumns + 1 >= this.mMaxColumns) {
                increaseTableSize();
            }
            boolean z = false;
            if (!arrayRow.isSimpleDefinition) {
                updateRowFromVariables(arrayRow);
                if (!arrayRow.isEmpty()) {
                    arrayRow.ensurePositiveConstant();
                    if (arrayRow.chooseSubject(this)) {
                        SolverVariable createExtraVariable = createExtraVariable();
                        arrayRow.variable = createExtraVariable;
                        addRow(arrayRow);
                        this.mTempGoal.initFromRow(arrayRow);
                        optimize(this.mTempGoal, true);
                        if (createExtraVariable.definitionId == -1) {
                            if (arrayRow.variable == createExtraVariable && (pickPivot = arrayRow.pickPivot(createExtraVariable)) != null) {
                                if (sMetrics != null) {
                                    sMetrics.pivots++;
                                }
                                arrayRow.pivot(pickPivot);
                            }
                            if (!arrayRow.isSimpleDefinition) {
                                arrayRow.variable.updateReferencesWithNewDefinition(arrayRow);
                            }
                            this.mNumRows--;
                        }
                        z = true;
                    }
                    if (!arrayRow.hasKeyVariable()) {
                        return;
                    }
                } else {
                    return;
                }
            }
            if (!z) {
                addRow(arrayRow);
            }
        }
    }

    private final void addRow(ArrayRow arrayRow) {
        if (this.mRows[this.mNumRows] != null) {
            this.mCache.arrayRowPool.release(this.mRows[this.mNumRows]);
        }
        this.mRows[this.mNumRows] = arrayRow;
        arrayRow.variable.definitionId = this.mNumRows;
        this.mNumRows++;
        arrayRow.variable.updateReferencesWithNewDefinition(arrayRow);
    }

    private final int optimize(Row row, boolean z) {
        if (sMetrics != null) {
            sMetrics.optimize++;
        }
        for (int i = 0; i < this.mNumColumns; i++) {
            this.mAlreadyTestedCandidates[i] = false;
        }
        boolean z2 = false;
        int i2 = 0;
        while (!z2) {
            if (sMetrics != null) {
                sMetrics.iterations++;
            }
            i2++;
            if (i2 >= this.mNumColumns * 2) {
                return i2;
            }
            if (row.getKey() != null) {
                this.mAlreadyTestedCandidates[row.getKey().id] = true;
            }
            SolverVariable pivotCandidate = row.getPivotCandidate(this, this.mAlreadyTestedCandidates);
            if (pivotCandidate != null) {
                if (this.mAlreadyTestedCandidates[pivotCandidate.id]) {
                    return i2;
                }
                this.mAlreadyTestedCandidates[pivotCandidate.id] = true;
            }
            if (pivotCandidate != null) {
                int i3 = -1;
                float f = Float.MAX_VALUE;
                for (int i4 = 0; i4 < this.mNumRows; i4++) {
                    ArrayRow arrayRow = this.mRows[i4];
                    if (arrayRow.variable.mType != SolverVariable.Type.UNRESTRICTED && !arrayRow.isSimpleDefinition && arrayRow.hasVariable(pivotCandidate)) {
                        float f2 = arrayRow.variables.get(pivotCandidate);
                        if (f2 < 0.0f) {
                            float f3 = (-arrayRow.constantValue) / f2;
                            if (f3 < f) {
                                i3 = i4;
                                f = f3;
                            }
                        }
                    }
                }
                if (i3 > -1) {
                    ArrayRow arrayRow2 = this.mRows[i3];
                    arrayRow2.variable.definitionId = -1;
                    if (sMetrics != null) {
                        sMetrics.pivots++;
                    }
                    arrayRow2.pivot(pivotCandidate);
                    arrayRow2.variable.definitionId = i3;
                    arrayRow2.variable.updateReferencesWithNewDefinition(arrayRow2);
                }
            }
            z2 = true;
        }
        return i2;
    }

    private int enforceBFS(Row row) throws Exception {
        float f;
        boolean z;
        int i = 0;
        while (true) {
            f = 0.0f;
            if (i >= this.mNumRows) {
                z = false;
                break;
            } else if (this.mRows[i].variable.mType != SolverVariable.Type.UNRESTRICTED && this.mRows[i].constantValue < 0.0f) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (!z) {
            return 0;
        }
        boolean z2 = false;
        int i2 = 0;
        while (!z2) {
            if (sMetrics != null) {
                sMetrics.bfs++;
            }
            i2++;
            int i3 = 0;
            int i4 = -1;
            int i5 = -1;
            float f2 = Float.MAX_VALUE;
            int i6 = 0;
            while (i3 < this.mNumRows) {
                ArrayRow arrayRow = this.mRows[i3];
                if (arrayRow.variable.mType != SolverVariable.Type.UNRESTRICTED && !arrayRow.isSimpleDefinition && arrayRow.constantValue < f) {
                    int i7 = 1;
                    while (i7 < this.mNumColumns) {
                        SolverVariable solverVariable = this.mCache.mIndexedVariables[i7];
                        float f3 = arrayRow.variables.get(solverVariable);
                        if (f3 > f) {
                            int i8 = i6;
                            float f4 = f2;
                            int i9 = i5;
                            int i10 = i4;
                            for (int i11 = 0; i11 < 7; i11++) {
                                float f5 = solverVariable.strengthVector[i11] / f3;
                                if ((f5 < f4 && i11 == i8) || i11 > i8) {
                                    i9 = i7;
                                    i10 = i3;
                                    f4 = f5;
                                    i8 = i11;
                                }
                            }
                            i4 = i10;
                            i5 = i9;
                            f2 = f4;
                            i6 = i8;
                        }
                        i7++;
                        f = 0.0f;
                    }
                }
                i3++;
                f = 0.0f;
            }
            if (i4 != -1) {
                ArrayRow arrayRow2 = this.mRows[i4];
                arrayRow2.variable.definitionId = -1;
                if (sMetrics != null) {
                    sMetrics.pivots++;
                }
                arrayRow2.pivot(this.mCache.mIndexedVariables[i5]);
                arrayRow2.variable.definitionId = i4;
                arrayRow2.variable.updateReferencesWithNewDefinition(arrayRow2);
            } else {
                z2 = true;
            }
            if (i2 > this.mNumColumns / 2) {
                z2 = true;
            }
            f = 0.0f;
        }
        return i2;
    }

    private void computeValues() {
        for (int i = 0; i < this.mNumRows; i++) {
            ArrayRow arrayRow = this.mRows[i];
            arrayRow.variable.computedValue = arrayRow.constantValue;
        }
    }

    private void displayRows() {
        displaySolverVariables();
        String str = "";
        for (int i = 0; i < this.mNumRows; i++) {
            str = (str + this.mRows[i]) + "\n";
        }
        System.out.println(str + this.mGoal + "\n");
    }

    /* access modifiers changed from: package-private */
    public void displayReadableRows() {
        displaySolverVariables();
        String str = " #  ";
        for (int i = 0; i < this.mNumRows; i++) {
            str = (str + this.mRows[i].toReadableString()) + "\n #  ";
        }
        if (this.mGoal != null) {
            str = str + this.mGoal + "\n";
        }
        System.out.println(str);
    }

    public void displayVariablesReadableRows() {
        displaySolverVariables();
        String str = "";
        for (int i = 0; i < this.mNumRows; i++) {
            if (this.mRows[i].variable.mType == SolverVariable.Type.UNRESTRICTED) {
                str = (str + this.mRows[i].toReadableString()) + "\n";
            }
        }
        System.out.println(str + this.mGoal + "\n");
    }

    public int getMemoryUsed() {
        int i = 0;
        for (int i2 = 0; i2 < this.mNumRows; i2++) {
            if (this.mRows[i2] != null) {
                i += this.mRows[i2].sizeInBytes();
            }
        }
        return i;
    }

    public int getNumEquations() {
        return this.mNumRows;
    }

    public int getNumVariables() {
        return this.mVariablesID;
    }

    /* access modifiers changed from: package-private */
    public void displaySystemInformations() {
        int i = 0;
        for (int i2 = 0; i2 < this.TABLE_SIZE; i2++) {
            if (this.mRows[i2] != null) {
                i += this.mRows[i2].sizeInBytes();
            }
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.mNumRows; i4++) {
            if (this.mRows[i4] != null) {
                i3 += this.mRows[i4].sizeInBytes();
            }
        }
        PrintStream printStream = System.out;
        printStream.println("Linear System -> Table size: " + this.TABLE_SIZE + " (" + getDisplaySize(this.TABLE_SIZE * this.TABLE_SIZE) + ") -- row sizes: " + getDisplaySize(i) + ", actual size: " + getDisplaySize(i3) + " rows: " + this.mNumRows + "/" + this.mMaxRows + " cols: " + this.mNumColumns + "/" + this.mMaxColumns + " " + 0 + " occupied cells, " + getDisplaySize(0));
    }

    private void displaySolverVariables() {
        System.out.println("Display Rows (" + this.mNumRows + AvidJSONUtil.KEY_X + this.mNumColumns + ")\n");
    }

    private String getDisplaySize(int i) {
        int i2 = i * 4;
        int i3 = i2 / 1024;
        int i4 = i3 / 1024;
        if (i4 > 0) {
            return "" + i4 + " Mb";
        } else if (i3 > 0) {
            return "" + i3 + " Kb";
        } else {
            return "" + i2 + " bytes";
        }
    }

    public Cache getCache() {
        return this.mCache;
    }

    public void addGreaterThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowGreaterThan(solverVariable, solverVariable2, createSlackVariable, i);
        if (i2 != 6) {
            addSingleError(createRow, (int) (createRow.variables.get(createSlackVariable) * -1.0f), i2);
        }
        addConstraint(createRow);
    }

    public void addGreaterThan(SolverVariable solverVariable, int i) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowGreaterThan(solverVariable, i, createSlackVariable);
        addConstraint(createRow);
    }

    public void addGreaterBarrier(SolverVariable solverVariable, SolverVariable solverVariable2, boolean z) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowGreaterThan(solverVariable, solverVariable2, createSlackVariable, 0);
        if (z) {
            addSingleError(createRow, (int) (createRow.variables.get(createSlackVariable) * -1.0f), 1);
        }
        addConstraint(createRow);
    }

    public void addLowerThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowLowerThan(solverVariable, solverVariable2, createSlackVariable, i);
        if (i2 != 6) {
            addSingleError(createRow, (int) (createRow.variables.get(createSlackVariable) * -1.0f), i2);
        }
        addConstraint(createRow);
    }

    public void addLowerBarrier(SolverVariable solverVariable, SolverVariable solverVariable2, boolean z) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowLowerThan(solverVariable, solverVariable2, createSlackVariable, 0);
        if (z) {
            addSingleError(createRow, (int) (createRow.variables.get(createSlackVariable) * -1.0f), 1);
        }
        addConstraint(createRow);
    }

    public void addCentering(SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2, int i3) {
        int i4 = i3;
        ArrayRow createRow = createRow();
        createRow.createRowCentering(solverVariable, solverVariable2, i, f, solverVariable3, solverVariable4, i2);
        if (i4 != 6) {
            createRow.addError(this, i4);
        }
        addConstraint(createRow);
    }

    public void addRatio(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f, int i) {
        ArrayRow createRow = createRow();
        createRow.createRowDimensionRatio(solverVariable, solverVariable2, solverVariable3, solverVariable4, f);
        if (i != 6) {
            createRow.addError(this, i);
        }
        addConstraint(createRow);
    }

    public ArrayRow addEquality(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        createRow.createRowEquals(solverVariable, solverVariable2, i);
        if (i2 != 6) {
            createRow.addError(this, i2);
        }
        addConstraint(createRow);
        return createRow;
    }

    public void addEquality(SolverVariable solverVariable, int i) {
        int i2 = solverVariable.definitionId;
        if (solverVariable.definitionId != -1) {
            ArrayRow arrayRow = this.mRows[i2];
            if (arrayRow.isSimpleDefinition) {
                arrayRow.constantValue = (float) i;
            } else if (arrayRow.variables.currentSize == 0) {
                arrayRow.isSimpleDefinition = true;
                arrayRow.constantValue = (float) i;
            } else {
                ArrayRow createRow = createRow();
                createRow.createRowEquals(solverVariable, i);
                addConstraint(createRow);
            }
        } else {
            ArrayRow createRow2 = createRow();
            createRow2.createRowDefinition(solverVariable, i);
            addConstraint(createRow2);
        }
    }

    public void addEquality(SolverVariable solverVariable, int i, int i2) {
        int i3 = solverVariable.definitionId;
        if (solverVariable.definitionId != -1) {
            ArrayRow arrayRow = this.mRows[i3];
            if (arrayRow.isSimpleDefinition) {
                arrayRow.constantValue = (float) i;
                return;
            }
            ArrayRow createRow = createRow();
            createRow.createRowEquals(solverVariable, i);
            createRow.addError(this, i2);
            addConstraint(createRow);
            return;
        }
        ArrayRow createRow2 = createRow();
        createRow2.createRowDefinition(solverVariable, i);
        createRow2.addError(this, i2);
        addConstraint(createRow2);
    }

    public static ArrayRow createRowEquals(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z) {
        ArrayRow createRow = linearSystem.createRow();
        createRow.createRowEquals(solverVariable, solverVariable2, i);
        if (z) {
            linearSystem.addSingleError(createRow, 1);
        }
        return createRow;
    }

    public static ArrayRow createRowDimensionPercent(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, float f, boolean z) {
        ArrayRow createRow = linearSystem.createRow();
        if (z) {
            linearSystem.addError(createRow);
        }
        return createRow.createRowDimensionPercent(solverVariable, solverVariable2, solverVariable3, f);
    }

    public static ArrayRow createRowGreaterThan(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z) {
        SolverVariable createSlackVariable = linearSystem.createSlackVariable();
        ArrayRow createRow = linearSystem.createRow();
        createRow.createRowGreaterThan(solverVariable, solverVariable2, createSlackVariable, i);
        if (z) {
            linearSystem.addSingleError(createRow, (int) (createRow.variables.get(createSlackVariable) * -1.0f));
        }
        return createRow;
    }

    public static ArrayRow createRowLowerThan(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, int i, boolean z) {
        SolverVariable createSlackVariable = linearSystem.createSlackVariable();
        ArrayRow createRow = linearSystem.createRow();
        createRow.createRowLowerThan(solverVariable, solverVariable2, createSlackVariable, i);
        if (z) {
            linearSystem.addSingleError(createRow, (int) (createRow.variables.get(createSlackVariable) * -1.0f));
        }
        return createRow;
    }

    public static ArrayRow createRowCentering(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2, boolean z) {
        ArrayRow createRow = linearSystem.createRow();
        createRow.createRowCentering(solverVariable, solverVariable2, i, f, solverVariable3, solverVariable4, i2);
        if (z) {
            LinearSystem linearSystem2 = linearSystem;
            createRow.addError(linearSystem, 4);
        }
        return createRow;
    }

    public void addCenterPoint(ConstraintWidget constraintWidget, ConstraintWidget constraintWidget2, float f, int i) {
        ConstraintWidget constraintWidget3 = constraintWidget;
        ConstraintWidget constraintWidget4 = constraintWidget2;
        SolverVariable createObjectVariable = createObjectVariable(constraintWidget3.getAnchor(ConstraintAnchor.Type.LEFT));
        SolverVariable createObjectVariable2 = createObjectVariable(constraintWidget3.getAnchor(ConstraintAnchor.Type.TOP));
        SolverVariable createObjectVariable3 = createObjectVariable(constraintWidget3.getAnchor(ConstraintAnchor.Type.RIGHT));
        SolverVariable createObjectVariable4 = createObjectVariable(constraintWidget3.getAnchor(ConstraintAnchor.Type.BOTTOM));
        SolverVariable createObjectVariable5 = createObjectVariable(constraintWidget4.getAnchor(ConstraintAnchor.Type.LEFT));
        SolverVariable createObjectVariable6 = createObjectVariable(constraintWidget4.getAnchor(ConstraintAnchor.Type.TOP));
        SolverVariable createObjectVariable7 = createObjectVariable(constraintWidget4.getAnchor(ConstraintAnchor.Type.RIGHT));
        SolverVariable createObjectVariable8 = createObjectVariable(constraintWidget4.getAnchor(ConstraintAnchor.Type.BOTTOM));
        ArrayRow createRow = createRow();
        double d = (double) f;
        double sin = Math.sin(d);
        SolverVariable solverVariable = createObjectVariable3;
        double d2 = (double) i;
        Double.isNaN(d2);
        createRow.createRowWithAngle(createObjectVariable2, createObjectVariable4, createObjectVariable6, createObjectVariable8, (float) (sin * d2));
        addConstraint(createRow);
        ArrayRow createRow2 = createRow();
        double cos = Math.cos(d);
        Double.isNaN(d2);
        createRow2.createRowWithAngle(createObjectVariable, solverVariable, createObjectVariable5, createObjectVariable7, (float) (cos * d2));
        addConstraint(createRow2);
    }
}
