package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.bm;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class at<T> extends bm {
    final com.google.android.play.core.tasks.i<T> a;
    final /* synthetic */ au b;

    at(au auVar, com.google.android.play.core.tasks.i<T> iVar) {
        this.b = auVar;
        this.a = iVar;
    }

    public void b(int i, Bundle bundle) throws RemoteException {
        this.b.a.b();
        au.b.d("onCancelInstall(%d)", Integer.valueOf(i));
    }

    public void c(Bundle bundle) throws RemoteException {
        this.b.a.b();
        au.b.d("onDeferredInstall", new Object[0]);
    }

    public void d(Bundle bundle) throws RemoteException {
        this.b.a.b();
        au.b.d("onDeferredLanguageInstall", new Object[0]);
    }

    public void e(Bundle bundle) throws RemoteException {
        this.b.a.b();
        au.b.d("onDeferredLanguageUninstall", new Object[0]);
    }

    public void f(Bundle bundle) throws RemoteException {
        this.b.a.b();
        au.b.d("onDeferredUninstall", new Object[0]);
    }

    public void g(int i, Bundle bundle) throws RemoteException {
        this.b.a.b();
        au.b.d("onGetSession(%d)", Integer.valueOf(i));
    }

    public void h(List<Bundle> list) throws RemoteException {
        this.b.a.b();
        au.b.d("onGetSessionStates", new Object[0]);
    }

    public void i(int i, Bundle bundle) throws RemoteException {
        this.b.a.b();
        au.b.d("onStartInstall(%d)", Integer.valueOf(i));
    }

    @Override // com.google.android.play.core.internal.bn
    public final void j(Bundle bundle) throws RemoteException {
        this.b.a.b();
        int i = bundle.getInt("error_code");
        au.b.b("onError(%d)", Integer.valueOf(i));
        this.a.d(new SplitInstallException(i));
    }

    @Override // com.google.android.play.core.internal.bn
    public final void k(int i) throws RemoteException {
        this.b.a.b();
        au.b.d("onCompleteInstall(%d)", Integer.valueOf(i));
    }

    @Override // com.google.android.play.core.internal.bn
    public final void l() throws RemoteException {
        this.b.a.b();
        au.b.d("onCompleteInstallForAppUpdate", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.bn
    public final void m() throws RemoteException {
        this.b.a.b();
        au.b.d("onGetSplitsForAppUpdate", new Object[0]);
    }
}
