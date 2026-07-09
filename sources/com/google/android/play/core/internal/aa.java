package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public final class aa extends j implements ac {
    aa(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.inappreview.protocol.IInAppReviewService");
    }

    @Override // com.google.android.play.core.internal.ac
    public final void c(String str, Bundle bundle, ae aeVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        l.b(parcelA, bundle);
        l.c(parcelA, aeVar);
        b(2, parcelA);
    }
}
