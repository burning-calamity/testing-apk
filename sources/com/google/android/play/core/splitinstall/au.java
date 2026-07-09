package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.play.core.internal.bl;
import com.google.android.play.core.internal.bp;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class au {
    private static final com.google.android.play.core.internal.ag b = new com.google.android.play.core.internal.ag("SplitInstallService");
    private static final Intent c = new Intent("com.google.android.play.core.splitinstall.BIND_SPLIT_INSTALL_SERVICE").setPackage("com.android.vending");

    @Nullable
    com.google.android.play.core.internal.aq<bl> a;
    private final String d;

    public au(Context context) {
        this.d = context.getPackageName();
        if (bp.a(context)) {
            this.a = new com.google.android.play.core.internal.aq<>(com.google.android.play.core.splitcompat.p.c(context), b, "SplitInstallService", c, ac.a);
        }
    }

    static /* synthetic */ ArrayList i(Collection collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            Bundle bundle = new Bundle();
            bundle.putString("module_name", str);
            arrayList.add(bundle);
        }
        return arrayList;
    }

    static /* synthetic */ ArrayList j(Collection collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            Bundle bundle = new Bundle();
            bundle.putString("language", str);
            arrayList.add(bundle);
        }
        return arrayList;
    }

    static /* synthetic */ Bundle l() {
        Bundle bundle = new Bundle();
        bundle.putInt("playcore_version_code", 11000);
        return bundle;
    }

    private static <T> Task<T> n() {
        b.b("onError(%d)", -14);
        return Tasks.b(new SplitInstallException(-14));
    }

    public final Task<Integer> a(Collection<String> collection, Collection<String> collection2) {
        if (this.a == null) {
            return n();
        }
        b.d("startInstall(%s,%s)", collection, collection2);
        com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        this.a.a(new ad(this, iVar, collection, collection2, iVar));
        return iVar.c();
    }

    public final Task<Void> b(List<String> list) {
        if (this.a == null) {
            return n();
        }
        b.d("deferredUninstall(%s)", list);
        com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        this.a.a(new ae(this, iVar, list, iVar));
        return iVar.c();
    }

    public final Task<Void> c(List<String> list) {
        if (this.a == null) {
            return n();
        }
        b.d("deferredInstall(%s)", list);
        com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        this.a.a(new af(this, iVar, list, iVar));
        return iVar.c();
    }

    public final Task<Void> d(List<String> list) {
        if (this.a == null) {
            return n();
        }
        b.d("deferredLanguageInstall(%s)", list);
        com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        this.a.a(new ag(this, iVar, list, iVar));
        return iVar.c();
    }

    public final Task<Void> e(List<String> list) {
        if (this.a == null) {
            return n();
        }
        b.d("deferredLanguageUninstall(%s)", list);
        com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        this.a.a(new ah(this, iVar, list, iVar));
        return iVar.c();
    }

    public final Task<SplitInstallSessionState> f(int i) {
        if (this.a == null) {
            return n();
        }
        b.d("getSessionState(%d)", Integer.valueOf(i));
        com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        this.a.a(new ai(this, iVar, i, iVar));
        return iVar.c();
    }

    public final Task<List<SplitInstallSessionState>> g() {
        if (this.a == null) {
            return n();
        }
        b.d("getSessionStates", new Object[0]);
        com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        this.a.a(new aj(this, iVar, iVar));
        return iVar.c();
    }

    public final Task<Void> h(int i) {
        if (this.a == null) {
            return n();
        }
        b.d("cancelInstall(%d)", Integer.valueOf(i));
        com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        this.a.a(new ak(this, iVar, i, iVar));
        return iVar.c();
    }
}
