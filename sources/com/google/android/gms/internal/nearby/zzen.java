package com.google.android.gms.internal.nearby;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "OnConnectionResultParamsCreator")
public final class zzen extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzen> CREATOR = new zzeo();

    @SafeParcelable.Field(getter = "getStatusCode", id = 2)
    private int statusCode;

    @SafeParcelable.Field(getter = "getRemoteEndpointId", id = 1)
    private String zzat;

    @Nullable
    @SafeParcelable.Field(getter = "getHandshakeData", id = 3)
    private byte[] zzau;

    private zzen() {
    }

    @SafeParcelable.Constructor
    zzen(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) int i, @Nullable @SafeParcelable.Param(id = 3) byte[] bArr) {
        this.zzat = str;
        this.statusCode = i;
        this.zzau = bArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzen) {
            zzen zzenVar = (zzen) obj;
            if (Objects.equal(this.zzat, zzenVar.zzat) && Objects.equal(Integer.valueOf(this.statusCode), Integer.valueOf(zzenVar.statusCode)) && Arrays.equals(this.zzau, zzenVar.zzau)) {
                return true;
            }
        }
        return false;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzat, Integer.valueOf(this.statusCode), Integer.valueOf(Arrays.hashCode(this.zzau)));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzat, false);
        SafeParcelWriter.writeInt(parcel, 2, this.statusCode);
        SafeParcelWriter.writeByteArray(parcel, 3, this.zzau, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final String zzg() {
        return this.zzat;
    }
}
