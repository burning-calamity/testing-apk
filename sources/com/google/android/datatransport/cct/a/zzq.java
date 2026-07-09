package com.google.android.datatransport.cct.a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.datatransport.cct.a.zzg;
import com.google.auto.value.AutoValue;

/* JADX INFO: compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
/* JADX INFO: loaded from: classes.dex */
@AutoValue
public abstract class zzq {

    /* JADX INFO: compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    @AutoValue.Builder
    public static abstract class zza {
        @NonNull
        public abstract zza zza(@Nullable com.google.android.datatransport.cct.a.zza zzaVar);

        @NonNull
        public abstract zza zza(@Nullable zzb zzbVar);

        @NonNull
        public abstract zzq zza();
    }

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    public static final class zzb {
        public static final zzb zza = new zzb("UNKNOWN", 0, 0);
        public static final zzb zzb = new zzb("ANDROID", 1, 4);

        static {
            zzb[] zzbVarArr = {zza, zzb};
        }

        private zzb(String str, int i, int i2) {
        }
    }

    @NonNull
    public static zza zza() {
        return new zzg.zza();
    }
}
