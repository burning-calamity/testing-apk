package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public final class y extends j implements z {
    y(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.assetpacks.protocol.IAssetPackExtractionServiceCallback");
    }

    @Override // com.google.android.play.core.internal.z
    public final void c(Bundle bundle, Bundle bundle2) throws RemoteException {
        Parcel parcelA = a();
        l.b(parcelA, bundle);
        l.b(parcelA, bundle2);
        b(2, parcelA);
    }

    @Override // com.google.android.play.core.internal.z
    public final void d(Bundle bundle) throws RemoteException {
        Parcel parcelA = a();
        l.b(parcelA, bundle);
        b(3, parcelA);
    }

    @Override // com.google.android.play.core.internal.z
    public final void e(Bundle bundle) throws RemoteException {
        Parcel parcelA = a();
        l.b(parcelA, bundle);
        b(4, parcelA);
    }
}
