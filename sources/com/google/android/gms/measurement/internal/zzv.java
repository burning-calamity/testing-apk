package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.view.MotionEventCompat;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "ConditionalUserPropertyParcelCreator")
public final class zzv extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzv> CREATOR = new zzy();

    @SafeParcelable.Field(id = 2)
    public String zza;

    @SafeParcelable.Field(id = 3)
    public String zzb;

    @SafeParcelable.Field(id = 4)
    public zzkq zzc;

    @SafeParcelable.Field(id = 5)
    public long zzd;

    @SafeParcelable.Field(id = 6)
    public boolean zze;

    @SafeParcelable.Field(id = 7)
    public String zzf;

    @SafeParcelable.Field(id = 8)
    public zzan zzg;

    @SafeParcelable.Field(id = 9)
    public long zzh;

    @SafeParcelable.Field(id = 10)
    public zzan zzi;

    @SafeParcelable.Field(id = 11)
    public long zzj;

    @SafeParcelable.Field(id = MotionEventCompat.AXIS_RX)
    public zzan zzk;

    zzv(zzv zzvVar) {
        Preconditions.checkNotNull(zzvVar);
        this.zza = zzvVar.zza;
        this.zzb = zzvVar.zzb;
        this.zzc = zzvVar.zzc;
        this.zzd = zzvVar.zzd;
        this.zze = zzvVar.zze;
        this.zzf = zzvVar.zzf;
        this.zzg = zzvVar.zzg;
        this.zzh = zzvVar.zzh;
        this.zzi = zzvVar.zzi;
        this.zzj = zzvVar.zzj;
        this.zzk = zzvVar.zzk;
    }

    @SafeParcelable.Constructor
    zzv(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) zzkq zzkqVar, @SafeParcelable.Param(id = 5) long j, @SafeParcelable.Param(id = 6) boolean z, @SafeParcelable.Param(id = 7) String str3, @SafeParcelable.Param(id = 8) zzan zzanVar, @SafeParcelable.Param(id = 9) long j2, @SafeParcelable.Param(id = 10) zzan zzanVar2, @SafeParcelable.Param(id = 11) long j3, @SafeParcelable.Param(id = MotionEventCompat.AXIS_RX) zzan zzanVar3) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzkqVar;
        this.zzd = j;
        this.zze = z;
        this.zzf = str3;
        this.zzg = zzanVar;
        this.zzh = j2;
        this.zzi = zzanVar2;
        this.zzj = j3;
        this.zzk = zzanVar3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzc, i, false);
        SafeParcelWriter.writeLong(parcel, 5, this.zzd);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zze);
        SafeParcelWriter.writeString(parcel, 7, this.zzf, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzg, i, false);
        SafeParcelWriter.writeLong(parcel, 9, this.zzh);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzi, i, false);
        SafeParcelWriter.writeLong(parcel, 11, this.zzj);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzk, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
