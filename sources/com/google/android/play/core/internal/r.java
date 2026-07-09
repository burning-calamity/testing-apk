package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class r extends j implements t {
    r(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.assetpacks.protocol.IAssetModuleService");
    }

    @Override // com.google.android.play.core.internal.t
    public final void c(String str, List<Bundle> list, Bundle bundle, v vVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        parcelA.writeTypedList(list);
        l.b(parcelA, bundle);
        l.c(parcelA, vVar);
        b(2, parcelA);
    }

    @Override // com.google.android.play.core.internal.t
    public final void d(String str, List<Bundle> list, Bundle bundle, v vVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        parcelA.writeTypedList(list);
        l.b(parcelA, bundle);
        l.c(parcelA, vVar);
        b(14, parcelA);
    }

    @Override // com.google.android.play.core.internal.t
    public final void e(String str, Bundle bundle, v vVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        l.b(parcelA, bundle);
        l.c(parcelA, vVar);
        b(5, parcelA);
    }

    @Override // com.google.android.play.core.internal.t
    public final void f(String str, Bundle bundle, Bundle bundle2, v vVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        l.b(parcelA, bundle);
        l.b(parcelA, bundle2);
        l.c(parcelA, vVar);
        b(6, parcelA);
    }

    @Override // com.google.android.play.core.internal.t
    public final void g(String str, Bundle bundle, Bundle bundle2, v vVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        l.b(parcelA, bundle);
        l.b(parcelA, bundle2);
        l.c(parcelA, vVar);
        b(7, parcelA);
    }

    @Override // com.google.android.play.core.internal.t
    public final void h(String str, Bundle bundle, Bundle bundle2, v vVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        l.b(parcelA, bundle);
        l.b(parcelA, bundle2);
        l.c(parcelA, vVar);
        b(9, parcelA);
    }

    @Override // com.google.android.play.core.internal.t
    public final void i(String str, Bundle bundle, v vVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        l.b(parcelA, bundle);
        l.c(parcelA, vVar);
        b(10, parcelA);
    }

    @Override // com.google.android.play.core.internal.t
    public final void j(String str, Bundle bundle, Bundle bundle2, v vVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        l.b(parcelA, bundle);
        l.b(parcelA, bundle2);
        l.c(parcelA, vVar);
        b(11, parcelA);
    }

    @Override // com.google.android.play.core.internal.t
    public final void k(String str, List<Bundle> list, Bundle bundle, v vVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        parcelA.writeTypedList(list);
        l.b(parcelA, bundle);
        l.c(parcelA, vVar);
        b(12, parcelA);
    }

    @Override // com.google.android.play.core.internal.t
    public final void l(String str, Bundle bundle, Bundle bundle2, v vVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        l.b(parcelA, bundle);
        l.b(parcelA, bundle2);
        l.c(parcelA, vVar);
        b(13, parcelA);
    }
}
