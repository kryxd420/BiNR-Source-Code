package com.google.android.gms.wallet;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AutoResolveHelper {
    public static final int RESULT_ERROR = 1;
    /* access modifiers changed from: private */
    public static final long zzo = TimeUnit.MINUTES.toMillis(10);
    @VisibleForTesting
    static long zzp = SystemClock.elapsedRealtime();

    @VisibleForTesting
    static class zza<TResult extends AutoResolvableResult> implements OnCompleteListener<TResult>, Runnable {
        @VisibleForTesting
        private static final Handler zzq = new Handler(Looper.getMainLooper());
        @VisibleForTesting
        static final SparseArray<zza<?>> zzr = new SparseArray<>(2);
        private static final AtomicInteger zzs = new AtomicInteger();
        int zzt;
        private zzb zzu;
        private Task<TResult> zzv;

        zza() {
        }

        public static <TResult extends AutoResolvableResult> zza<TResult> zza(Task<TResult> task) {
            zza<TResult> zza = new zza<>();
            zza.zzt = zzs.incrementAndGet();
            zzr.put(zza.zzt, zza);
            zzq.postDelayed(zza, AutoResolveHelper.zzo);
            task.addOnCompleteListener(zza);
            return zza;
        }

        private final void zzb() {
            if (this.zzv != null && this.zzu != null) {
                zzr.delete(this.zzt);
                zzq.removeCallbacks(this);
                this.zzu.zzb(this.zzv);
            }
        }

        public final void onComplete(@NonNull Task<TResult> task) {
            this.zzv = task;
            zzb();
        }

        public final void run() {
            zzr.delete(this.zzt);
        }

        public final void zza(zzb zzb) {
            this.zzu = zzb;
            zzb();
        }

        public final void zzb(zzb zzb) {
            if (this.zzu == zzb) {
                this.zzu = null;
            }
        }
    }

    public static class zzb extends Fragment {
        private static String zzw = "resolveCallId";
        private static String zzx = "requestCode";
        private static String zzy = "initializationElapsedRealtime";
        private static String zzz = "delivered";
        private int zzaa;
        private zza<?> zzab;
        @VisibleForTesting
        private boolean zzac;

        /* access modifiers changed from: private */
        public static Fragment zza(int i, int i2) {
            Bundle bundle = new Bundle();
            bundle.putInt(zzw, i);
            bundle.putInt(zzx, i2);
            bundle.putLong(zzy, AutoResolveHelper.zzp);
            zzb zzb = new zzb();
            zzb.setArguments(bundle);
            return zzb;
        }

        /* access modifiers changed from: private */
        public final void zzb(@Nullable Task<? extends AutoResolvableResult> task) {
            if (!this.zzac) {
                this.zzac = true;
                Activity activity = getActivity();
                activity.getFragmentManager().beginTransaction().remove(this).commit();
                if (task != null) {
                    AutoResolveHelper.zza(activity, this.zzaa, task);
                } else {
                    AutoResolveHelper.zza(activity, this.zzaa, 0, new Intent());
                }
            }
        }

        private final void zzc() {
            if (this.zzab != null) {
                this.zzab.zzb(this);
            }
        }

        public final void onCreate(@Nullable Bundle bundle) {
            super.onCreate(bundle);
            this.zzaa = getArguments().getInt(zzx);
            this.zzab = AutoResolveHelper.zzp != getArguments().getLong(zzy) ? null : zza.zzr.get(getArguments().getInt(zzw));
            this.zzac = bundle != null && bundle.getBoolean(zzz);
        }

        public final void onPause() {
            super.onPause();
            zzc();
        }

        public final void onResume() {
            super.onResume();
            if (this.zzab != null) {
                this.zzab.zza(this);
                return;
            }
            if (Log.isLoggable("AutoResolveHelper", 5)) {
                Log.w("AutoResolveHelper", "Sending canceled result for garbage collected task!");
            }
            zzb((Task<? extends AutoResolvableResult>) null);
        }

        public final void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
            bundle.putBoolean(zzz, this.zzac);
            zzc();
        }
    }

    private AutoResolveHelper() {
    }

    @Nullable
    public static Status getStatusFromIntent(@Nullable Intent intent) {
        if (intent == null) {
            return null;
        }
        return (Status) intent.getParcelableExtra("com.google.android.gms.common.api.AutoResolveHelper.status");
    }

    public static void putStatusIntoIntent(@NonNull Intent intent, @Nullable Status status) {
        if (status == null) {
            intent.removeExtra("com.google.android.gms.common.api.AutoResolveHelper.status");
        } else {
            intent.putExtra("com.google.android.gms.common.api.AutoResolveHelper.status", status);
        }
    }

    @MainThread
    public static <TResult extends AutoResolvableResult> void resolveTask(@NonNull Task<TResult> task, @NonNull Activity activity, int i) {
        zza<TResult> zza2 = zza.zza(task);
        FragmentTransaction beginTransaction = activity.getFragmentManager().beginTransaction();
        Fragment zzb2 = zzb.zza(zza2.zzt, i);
        int i2 = zza2.zzt;
        StringBuilder sb = new StringBuilder(58);
        sb.append("com.google.android.gms.wallet.AutoResolveHelper");
        sb.append(i2);
        beginTransaction.add(zzb2, sb.toString()).commit();
    }

    /* access modifiers changed from: private */
    public static void zza(Activity activity, int i, int i2, Intent intent) {
        PendingIntent createPendingResult = activity.createPendingResult(i, intent, 1073741824);
        if (createPendingResult != null) {
            try {
                createPendingResult.send(i2);
            } catch (PendingIntent.CanceledException e) {
                if (Log.isLoggable("AutoResolveHelper", 6)) {
                    Log.e("AutoResolveHelper", "Exception sending pending result", e);
                }
            }
        } else if (Log.isLoggable("AutoResolveHelper", 5)) {
            Log.w("AutoResolveHelper", "Null pending result returned when trying to deliver task result!");
        }
    }

    /* access modifiers changed from: private */
    public static void zza(Activity activity, int i, Task<? extends AutoResolvableResult> task) {
        if (activity.isFinishing()) {
            if (Log.isLoggable("AutoResolveHelper", 3)) {
                Log.d("AutoResolveHelper", "Ignoring task result for, Activity is finishing.");
            }
        } else if (task.getException() instanceof ResolvableApiException) {
            try {
                ((ResolvableApiException) task.getException()).startResolutionForResult(activity, i);
            } catch (IntentSender.SendIntentException e) {
                if (Log.isLoggable("AutoResolveHelper", 6)) {
                    Log.e("AutoResolveHelper", "Error starting pending intent!", e);
                }
            }
        } else {
            Intent intent = new Intent();
            int i2 = 1;
            if (task.isSuccessful()) {
                i2 = -1;
                ((AutoResolvableResult) task.getResult()).putIntoIntent(intent);
            } else if (task.getException() instanceof ApiException) {
                ApiException apiException = (ApiException) task.getException();
                putStatusIntoIntent(intent, new Status(apiException.getStatusCode(), apiException.getMessage(), (PendingIntent) null));
            } else {
                if (Log.isLoggable("AutoResolveHelper", 6)) {
                    Log.e("AutoResolveHelper", "Unexpected non API exception!", task.getException());
                }
                putStatusIntoIntent(intent, new Status(8, "Unexpected non API exception when trying to deliver the task result to an activity!"));
            }
            zza(activity, i, i2, intent);
        }
    }

    public static <TResult> void zza(Status status, TResult tresult, TaskCompletionSource<TResult> taskCompletionSource) {
        if (status.isSuccess()) {
            taskCompletionSource.setResult(tresult);
        } else {
            taskCompletionSource.setException(ApiExceptionUtil.fromStatus(status));
        }
    }
}
