package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.Filter;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "FilterHolderCreator")
@SafeParcelable.Reserved({1000})
public class FilterHolder extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<FilterHolder> CREATOR = new zzh();
    private final Filter zzba;

    @SafeParcelable.Field(id = 1)
    private final zzb<?> zzln;

    @SafeParcelable.Field(id = 2)
    private final zzd zzlo;

    @SafeParcelable.Field(id = 3)
    private final zzr zzlp;

    @SafeParcelable.Field(id = 4)
    private final zzv zzlq;

    @SafeParcelable.Field(id = 5)
    private final zzp<?> zzlr;

    @SafeParcelable.Field(id = 6)
    private final zzt zzls;

    @SafeParcelable.Field(id = 7)
    private final zzn zzlt;

    @SafeParcelable.Field(id = 8)
    private final zzl zzlu;

    @SafeParcelable.Field(id = 9)
    private final zzz zzlv;

    public FilterHolder(Filter filter) {
        Preconditions.checkNotNull(filter, "Null filter.");
        this.zzln = filter instanceof zzb ? (zzb) filter : null;
        this.zzlo = filter instanceof zzd ? (zzd) filter : null;
        this.zzlp = filter instanceof zzr ? (zzr) filter : null;
        this.zzlq = filter instanceof zzv ? (zzv) filter : null;
        this.zzlr = filter instanceof zzp ? (zzp) filter : null;
        this.zzls = filter instanceof zzt ? (zzt) filter : null;
        this.zzlt = filter instanceof zzn ? (zzn) filter : null;
        this.zzlu = filter instanceof zzl ? (zzl) filter : null;
        this.zzlv = filter instanceof zzz ? (zzz) filter : null;
        if (this.zzln == null && this.zzlo == null && this.zzlp == null && this.zzlq == null && this.zzlr == null && this.zzls == null && this.zzlt == null && this.zzlu == null && this.zzlv == null) {
            throw new IllegalArgumentException("Invalid filter type.");
        }
        this.zzba = filter;
    }

    @SafeParcelable.Constructor
    FilterHolder(@SafeParcelable.Param(id = 1) zzb<?> zzbVar, @SafeParcelable.Param(id = 2) zzd zzdVar, @SafeParcelable.Param(id = 3) zzr zzrVar, @SafeParcelable.Param(id = 4) zzv zzvVar, @SafeParcelable.Param(id = 5) zzp<?> zzpVar, @SafeParcelable.Param(id = 6) zzt zztVar, @SafeParcelable.Param(id = 7) zzn<?> zznVar, @SafeParcelable.Param(id = 8) zzl zzlVar, @SafeParcelable.Param(id = 9) zzz zzzVar) {
        this.zzln = zzbVar;
        this.zzlo = zzdVar;
        this.zzlp = zzrVar;
        this.zzlq = zzvVar;
        this.zzlr = zzpVar;
        this.zzls = zztVar;
        this.zzlt = zznVar;
        this.zzlu = zzlVar;
        this.zzlv = zzzVar;
        zzb<?> zzbVar2 = this.zzln;
        if (zzbVar2 != null) {
            this.zzba = zzbVar2;
            return;
        }
        zzd zzdVar2 = this.zzlo;
        if (zzdVar2 != null) {
            this.zzba = zzdVar2;
            return;
        }
        zzr zzrVar2 = this.zzlp;
        if (zzrVar2 != null) {
            this.zzba = zzrVar2;
            return;
        }
        zzv zzvVar2 = this.zzlq;
        if (zzvVar2 != null) {
            this.zzba = zzvVar2;
            return;
        }
        zzp<?> zzpVar2 = this.zzlr;
        if (zzpVar2 != null) {
            this.zzba = zzpVar2;
            return;
        }
        zzt zztVar2 = this.zzls;
        if (zztVar2 != null) {
            this.zzba = zztVar2;
            return;
        }
        zzn zznVar2 = this.zzlt;
        if (zznVar2 != null) {
            this.zzba = zznVar2;
            return;
        }
        zzl zzlVar2 = this.zzlu;
        if (zzlVar2 != null) {
            this.zzba = zzlVar2;
            return;
        }
        zzz zzzVar2 = this.zzlv;
        if (zzzVar2 == null) {
            throw new IllegalArgumentException("At least one filter must be set.");
        }
        this.zzba = zzzVar2;
    }

    public final Filter getFilter() {
        return this.zzba;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzln, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzlo, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzlp, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzlq, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzlr, i, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzls, i, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzlt, i, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzlu, i, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.zzlv, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
