package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.core.view.MotionEventCompat;

/* JADX INFO: loaded from: classes.dex */
public abstract class u extends k implements v {
    public u() {
        super("com.google.android.play.core.assetpacks.protocol.IAssetModuleServiceCallback");
    }

    @Override // com.google.android.play.core.internal.k
    protected final boolean a(int i, Parcel parcel) throws RemoteException {
        switch (i) {
            case 2:
                b(parcel.readInt(), (Bundle) l.a(parcel, Bundle.CREATOR));
                return true;
            case 3:
                int i2 = parcel.readInt();
                h(i2);
                return true;
            case 4:
                int i3 = parcel.readInt();
                j(i3);
                return true;
            case 5:
                c(parcel.createTypedArrayList(Bundle.CREATOR));
                return true;
            case 6:
                Bundle bundle = (Bundle) l.a(parcel, Bundle.CREATOR);
                k(bundle);
                return true;
            case 7:
                g((Bundle) l.a(parcel, Bundle.CREATOR));
                return true;
            case 8:
                Bundle bundle2 = (Bundle) l.a(parcel, Bundle.CREATOR);
                l(bundle2);
                return true;
            case 9:
            default:
                return false;
            case 10:
                Bundle bundle3 = (Bundle) l.a(parcel, Bundle.CREATOR);
                m(bundle3);
                return true;
            case 11:
                d((Bundle) l.a(parcel, Bundle.CREATOR), (Bundle) l.a(parcel, Bundle.CREATOR));
                return true;
            case MotionEventCompat.AXIS_RX /* 12 */:
                e((Bundle) l.a(parcel, Bundle.CREATOR), (Bundle) l.a(parcel, Bundle.CREATOR));
                return true;
            case 13:
                f((Bundle) l.a(parcel, Bundle.CREATOR), (Bundle) l.a(parcel, Bundle.CREATOR));
                return true;
            case 14:
                n();
                return true;
            case 15:
                i();
                return true;
        }
    }
}
