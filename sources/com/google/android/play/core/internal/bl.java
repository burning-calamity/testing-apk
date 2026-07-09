package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface bl extends IInterface {
    void c(String str, List<Bundle> list, Bundle bundle, bn bnVar) throws RemoteException;

    void d(String str, int i, Bundle bundle, bn bnVar) throws RemoteException;

    void e(String str, int i, bn bnVar) throws RemoteException;

    void f(String str, bn bnVar) throws RemoteException;

    void g(String str, List<Bundle> list, Bundle bundle, bn bnVar) throws RemoteException;

    void h(String str, List<Bundle> list, Bundle bundle, bn bnVar) throws RemoteException;

    void i(String str, List<Bundle> list, Bundle bundle, bn bnVar) throws RemoteException;

    void j(String str, List<Bundle> list, Bundle bundle, bn bnVar) throws RemoteException;
}
