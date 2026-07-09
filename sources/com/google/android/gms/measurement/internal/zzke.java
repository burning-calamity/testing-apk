package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzke extends zzaf {
    private final /* synthetic */ zzkb zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzke(zzkb zzkbVar, zzhc zzhcVar) {
        super(zzhcVar);
        this.zza = zzkbVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzaf
    @WorkerThread
    public final void zza() {
        this.zza.zzc();
    }
}
