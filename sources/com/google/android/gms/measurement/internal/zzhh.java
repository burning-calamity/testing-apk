package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
public final class zzhh {
    final Context zza;
    String zzb;
    String zzc;
    String zzd;
    Boolean zze;
    long zzf;
    com.google.android.gms.internal.measurement.zzv zzg;
    boolean zzh;

    @VisibleForTesting
    public zzhh(Context context, com.google.android.gms.internal.measurement.zzv zzvVar) {
        this.zzh = true;
        Preconditions.checkNotNull(context);
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.zza = applicationContext;
        if (zzvVar != null) {
            this.zzg = zzvVar;
            this.zzb = zzvVar.zzf;
            this.zzc = zzvVar.zze;
            this.zzd = zzvVar.zzd;
            this.zzh = zzvVar.zzc;
            this.zzf = zzvVar.zzb;
            if (zzvVar.zzg != null) {
                this.zze = Boolean.valueOf(zzvVar.zzg.getBoolean("dataCollectionDefaultEnabled", true));
            }
        }
    }
}
