package android.support.v4.view.animation;

public class FastOutSlowInInterpolator extends LookupTableInterpolator {
    private static final float[] VALUES;

    public /* bridge */ /* synthetic */ float getInterpolation(float f) {
        return super.getInterpolation(f);
    }

    static {
        float[] fArr = new float[FetchConst.NETWORK_WIFI];
        // fill-array-data instruction
        fArr[0] = 0;
        fArr[1] = 953267991;
        fArr[2] = 961656599;
        fArr[3] = 973279855;
        fArr[4] = 980151802;
        fArr[5] = 985104436;
        fArr[6] = 990057071;
        fArr[7] = 993063548;
        fArr[8] = 996929018;
        fArr[9] = 999734169;
        fArr[10] = 1002311149;
        fArr[11] = 1005102878;
        fArr[12] = 1007371158;
        fArr[13] = 1009089144;
        fArr[14] = 1010914506;
        fArr[15] = 1012954615;
        fArr[16] = 1015115520;
        fArr[17] = 1016296636;
        fArr[18] = 1017638814;
        fArr[19] = 1019034678;
        fArr[20] = 1020537917;
        fArr[21] = 1022148529;
        fArr[22] = 1023638346;
        fArr[23] = 1024551027;
        fArr[24] = 1025517394;
        fArr[25] = 1026564293;
        fArr[26] = 1027664878;
        fArr[27] = 1028819150;
        fArr[28] = 1030053954;
        fArr[29] = 1031342444;
        fArr[30] = 1032268546;
        fArr[31] = 1032993322;
        fArr[32] = 1033758363;
        fArr[33] = 1034550247;
        fArr[34] = 1035395819;
        fArr[35] = 1036281656;
        fArr[36] = 1037221180;
        fArr[37] = 1038187548;
        fArr[38] = 1039207603;
        fArr[39] = 1040234368;
        fArr[40] = 1040784661;
        fArr[41] = 1041368508;
        fArr[42] = 1041972488;
        fArr[43] = 1042603311;
        fArr[44] = 1043254267;
        fArr[45] = 1043932067;
        fArr[46] = 1044636710;
        fArr[47] = 1045361485;
        fArr[48] = 1046113105;
        fArr[49] = 1046884857;
        fArr[50] = 1047676741;
        fArr[51] = 1048488758;
        fArr[52] = 1048948454;
        fArr[53] = 1049374595;
        fArr[54] = 1049807448;
        fArr[55] = 1050247011;
        fArr[56] = 1050693285;
        fArr[57] = 1051142914;
        fArr[58] = 1051595899;
        fArr[59] = 1052052239;
        fArr[60] = 1052511935;
        fArr[61] = 1052971631;
        fArr[62] = 1053427971;
        fArr[63] = 1053884311;
        fArr[64] = 1054337296;
        fArr[65] = 1054790281;
        fArr[66] = 1055236555;
        fArr[67] = 1055676118;
        fArr[68] = 1056112325;
        fArr[69] = 1056541822;
        fArr[70] = 1056964608;
        fArr[71] = 1057172645;
        fArr[72] = 1057377328;
        fArr[73] = 1057576976;
        fArr[74] = 1057773270;
        fArr[75] = 1057966208;
        fArr[76] = 1058155790;
        fArr[77] = 1058340340;
        fArr[78] = 1058521534;
        fArr[79] = 1058697694;
        fArr[80] = 1058870500;
        fArr[81] = 1059039950;
        fArr[82] = 1059204366;
        fArr[83] = 1059365428;
        fArr[84] = 1059523133;
        fArr[85] = 1059675806;
        fArr[86] = 1059826801;
        fArr[87] = 1059972763;
        fArr[88] = 1060115369;
        fArr[89] = 1060254620;
        fArr[90] = 1060392193;
        fArr[91] = 1060524733;
        fArr[92] = 1060653918;
        fArr[93] = 1060781425;
        fArr[94] = 1060905576;
        fArr[95] = 1061026372;
        fArr[96] = 1061143813;
        fArr[97] = 1061257898;
        fArr[98] = 1061370305;
        fArr[99] = 1061481035;
        fArr[100] = 1061588409;
        fArr[101] = 1061692427;
        fArr[102] = 1061794768;
        fArr[103] = 1061893754;
        fArr[104] = 1061991062;
        fArr[105] = 1062086692;
        fArr[106] = 1062178967;
        fArr[107] = 1062269564;
        fArr[108] = 1062358483;
        fArr[109] = 1062444047;
        fArr[110] = 1062529611;
        fArr[111] = 1062611819;
        fArr[112] = 1062692350;
        fArr[113] = 1062771202;
        fArr[114] = 1062848378;
        fArr[115] = 1062922197;
        fArr[116] = 1062996017;
        fArr[117] = 1063068159;
        fArr[118] = 1063136946;
        fArr[119] = 1063205732;
        fArr[120] = 1063272841;
        fArr[121] = 1063336595;
        fArr[122] = 1063400348;
        fArr[123] = 1063462424;
        fArr[124] = 1063522822;
        fArr[125] = 1063583220;
        fArr[126] = 1063640262;
        fArr[127] = 1063697305;
        fArr[128] = 1063752670;
        fArr[129] = 1063806357;
        fArr[130] = 1063858366;
        fArr[131] = 1063908698;
        fArr[132] = 1063959029;
        fArr[133] = 1064007683;
        fArr[134] = 1064056337;
        fArr[135] = 1064101636;
        fArr[136] = 1064146934;
        fArr[137] = 1064190555;
        fArr[138] = 1064234176;
        fArr[139] = 1064276119;
        fArr[140] = 1064316384;
        fArr[141] = 1064356649;
        fArr[142] = 1064395237;
        fArr[143] = 1064433825;
        fArr[144] = 1064470734;
        fArr[145] = 1064505967;
        fArr[146] = 1064541199;
        fArr[147] = 1064574753;
        fArr[148] = 1064608308;
        fArr[149] = 1064640184;
        fArr[150] = 1064672061;
        fArr[151] = 1064702260;
        fArr[152] = 1064730781;
        fArr[153] = 1064759303;
        fArr[154] = 1064787824;
        fArr[155] = 1064814667;
        fArr[156] = 1064841511;
        fArr[157] = 1064866677;
        fArr[158] = 1064891843;
        fArr[159] = 1064915331;
        fArr[160] = 1064938819;
        fArr[161] = 1064960629;
        fArr[162] = 1064982440;
        fArr[163] = 1065002572;
        fArr[164] = 1065022705;
        fArr[165] = 1065042838;
        fArr[166] = 1065061292;
        fArr[167] = 1065079747;
        fArr[168] = 1065098202;
        fArr[169] = 1065114980;
        fArr[170] = 1065130079;
        fArr[171] = 1065146856;
        fArr[172] = 1065160278;
        fArr[173] = 1065175378;
        fArr[174] = 1065188799;
        fArr[175] = 1065202221;
        fArr[176] = 1065213965;
        fArr[177] = 1065225709;
        fArr[178] = 1065237453;
        fArr[179] = 1065247520;
        fArr[180] = 1065259264;
        fArr[181] = 1065267652;
        fArr[182] = 1065277719;
        fArr[183] = 1065286107;
        fArr[184] = 1065292818;
        fArr[185] = 1065301207;
        fArr[186] = 1065307918;
        fArr[187] = 1065314628;
        fArr[188] = 1065319662;
        fArr[189] = 1065326372;
        fArr[190] = 1065329728;
        fArr[191] = 1065334761;
        fArr[192] = 1065338117;
        fArr[193] = 1065341472;
        fArr[194] = 1065344827;
        fArr[195] = 1065348183;
        fArr[196] = 1065349861;
        fArr[197] = 1065351538;
        fArr[198] = 1065351538;
        fArr[199] = 1065353216;
        fArr[200] = 1065353216;
        VALUES = fArr;
    }

    public FastOutSlowInInterpolator() {
        super(VALUES);
    }
}
