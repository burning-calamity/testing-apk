package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public abstract class ad extends k implements ae {
    public ad() {
        super("com.google.android.play.core.inappreview.protocol.IInAppReviewServiceCallback");
    }

    @Override // com.google.android.play.core.internal.k
    protected final boolean a(int i, Parcel parcel) throws RemoteException {
        if (i != 2) {
            return false;
        }
        b((Bundle) l.a(parcel, Bundle.CREATOR));
        return true;
    }
}
