package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class bj extends j implements bl {
    bj(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.splitinstall.protocol.ISplitInstallService");
    }

    @Override // com.google.android.play.core.internal.bl
    public final void c(String str, List<Bundle> list, Bundle bundle, bn bnVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        parcelA.writeTypedList(list);
        l.b(parcelA, bundle);
        l.c(parcelA, bnVar);
        b(2, parcelA);
    }

    @Override // com.google.android.play.core.internal.bl
    public final void d(String str, int i, Bundle bundle, bn bnVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        parcelA.writeInt(i);
        l.b(parcelA, bundle);
        l.c(parcelA, bnVar);
        b(4, parcelA);
    }

    @Override // com.google.android.play.core.internal.bl
    public final void e(String str, int i, bn bnVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        parcelA.writeInt(i);
        l.c(parcelA, bnVar);
        b(5, parcelA);
    }

    @Override // com.google.android.play.core.internal.bl
    public final void f(String str, bn bnVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        l.c(parcelA, bnVar);
        b(6, parcelA);
    }

    @Override // com.google.android.play.core.internal.bl
    public final void g(String str, List<Bundle> list, Bundle bundle, bn bnVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        parcelA.writeTypedList(list);
        l.b(parcelA, bundle);
        l.c(parcelA, bnVar);
        b(7, parcelA);
    }

    @Override // com.google.android.play.core.internal.bl
    public final void h(String str, List<Bundle> list, Bundle bundle, bn bnVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        parcelA.writeTypedList(list);
        l.b(parcelA, bundle);
        l.c(parcelA, bnVar);
        b(8, parcelA);
    }

    @Override // com.google.android.play.core.internal.bl
    public final void i(String str, List<Bundle> list, Bundle bundle, bn bnVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        parcelA.writeTypedList(list);
        l.b(parcelA, bundle);
        l.c(parcelA, bnVar);
        b(13, parcelA);
    }

    @Override // com.google.android.play.core.internal.bl
    public final void j(String str, List<Bundle> list, Bundle bundle, bn bnVar) throws RemoteException {
        Parcel parcelA = a();
        parcelA.writeString(str);
        parcelA.writeTypedList(list);
        l.b(parcelA, bundle);
        l.c(parcelA, bnVar);
        b(14, parcelA);
    }
}
