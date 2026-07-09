package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.core.view.MotionEventCompat;

/* JADX INFO: loaded from: classes.dex */
public abstract class bm extends k implements bn {
    public bm() {
        super("com.google.android.play.core.splitinstall.protocol.ISplitInstallServiceCallback");
    }

    @Override // com.google.android.play.core.internal.k
    protected final boolean a(int i, Parcel parcel) throws RemoteException {
        switch (i) {
            case 2:
                i(parcel.readInt(), (Bundle) l.a(parcel, Bundle.CREATOR));
                return true;
            case 3:
                int i2 = parcel.readInt();
                k(i2);
                return true;
            case 4:
                b(parcel.readInt(), (Bundle) l.a(parcel, Bundle.CREATOR));
                return true;
            case 5:
                g(parcel.readInt(), (Bundle) l.a(parcel, Bundle.CREATOR));
                return true;
            case 6:
                j((Bundle) l.a(parcel, Bundle.CREATOR));
                return true;
            case 7:
                h(parcel.createTypedArrayList(Bundle.CREATOR));
                return true;
            case 8:
                f((Bundle) l.a(parcel, Bundle.CREATOR));
                return true;
            case 9:
                c((Bundle) l.a(parcel, Bundle.CREATOR));
                return true;
            case 10:
                m();
                return true;
            case 11:
                l();
                return true;
            case MotionEventCompat.AXIS_RX /* 12 */:
                d((Bundle) l.a(parcel, Bundle.CREATOR));
                return true;
            case 13:
                e((Bundle) l.a(parcel, Bundle.CREATOR));
                return true;
            default:
                return false;
        }
    }
}
