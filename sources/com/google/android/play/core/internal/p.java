package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public abstract class p extends k implements q {
    public p() {
        super("com.google.android.play.core.appupdate.protocol.IAppUpdateServiceCallback");
    }

    @Override // com.google.android.play.core.internal.k
    protected final boolean a(int i, Parcel parcel) throws RemoteException {
        if (i == 2) {
            b((Bundle) l.a(parcel, Bundle.CREATOR));
            return true;
        }
        if (i != 3) {
            return false;
        }
        c((Bundle) l.a(parcel, Bundle.CREATOR));
        return true;
    }
}
