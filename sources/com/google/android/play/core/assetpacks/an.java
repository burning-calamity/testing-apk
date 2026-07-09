package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
final class an implements t {
    private static final com.google.android.play.core.internal.ag a = new com.google.android.play.core.internal.ag("AssetPackServiceImpl");
    private static final Intent b = new Intent("com.google.android.play.core.assetmoduleservice.BIND_ASSET_MODULE_SERVICE").setPackage("com.android.vending");
    private final String c;
    private final bo d;

    @Nullable
    private com.google.android.play.core.internal.aq<com.google.android.play.core.internal.t> e;

    @Nullable
    private com.google.android.play.core.internal.aq<com.google.android.play.core.internal.t> f;
    private final AtomicBoolean g = new AtomicBoolean();

    an(Context context, bo boVar) {
        this.c = context.getPackageName();
        this.d = boVar;
        if (com.google.android.play.core.internal.bp.a(context)) {
            this.e = new com.google.android.play.core.internal.aq<>(com.google.android.play.core.splitcompat.p.c(context), a, "AssetPackService", b, u.b);
            this.f = new com.google.android.play.core.internal.aq<>(com.google.android.play.core.splitcompat.p.c(context), a, "AssetPackService-keepAlive", b, u.a);
        }
        a.a("AssetPackService initiated.", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bundle A(int i, String str) {
        Bundle bundleB = B(i);
        bundleB.putString("module_name", str);
        return bundleB;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bundle B(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("session_id", i);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bundle C() {
        Bundle bundle = new Bundle();
        bundle.putInt("playcore_version_code", 11000);
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(1);
        bundle.putIntegerArrayList("supported_compression_formats", arrayList);
        bundle.putIntegerArrayList("supported_patch_formats", new ArrayList<>());
        return bundle;
    }

    static /* synthetic */ ArrayList k(Collection collection) {
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

    static /* synthetic */ Bundle m(Map map) {
        Bundle bundleC = C();
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        for (Map.Entry entry : map.entrySet()) {
            Bundle bundle = new Bundle();
            bundle.putString("installed_asset_module_name", (String) entry.getKey());
            bundle.putLong("installed_asset_module_version", ((Long) entry.getValue()).longValue());
            arrayList.add(bundle);
        }
        bundleC.putParcelableArrayList("installed_asset_module", arrayList);
        return bundleC;
    }

    static /* synthetic */ Bundle r(int i, String str, String str2, int i2) {
        Bundle bundleA = A(i, str);
        bundleA.putString("slice_id", str2);
        bundleA.putInt("chunk_number", i2);
        return bundleA;
    }

    static /* synthetic */ List v(an anVar, List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            AssetPackState next = AssetPackStates.b((Bundle) it.next(), anVar.d).packStates().values().iterator().next();
            if (next == null) {
                a.b("onGetSessionStates: Bundle contained no pack.", new Object[0]);
            }
            if (ck.f(next.status())) {
                arrayList.add(next.name());
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void y(int i, String str, int i2) {
        if (this.e == null) {
            throw new bk("The Play Store app is not installed or is an unofficial version.", i);
        }
        a.d("notifyModuleCompleted", new Object[0]);
        com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        this.e.a(new ac(this, iVar, i, str, iVar, i2));
    }

    private static <T> Task<T> z() {
        a.b("onError(%d)", -11);
        return Tasks.b(new AssetPackException(-11));
    }

    @Override // com.google.android.play.core.assetpacks.t
    public final Task<AssetPackStates> a(List<String> list, List<String> list2, Map<String, Long> map) {
        if (this.e == null) {
            return z();
        }
        a.d("startDownload(%s)", list2);
        com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        this.e.a(new x(this, iVar, list2, map, iVar, list));
        iVar.c().addOnSuccessListener(new OnSuccessListener(this) { // from class: com.google.android.play.core.assetpacks.v
            private final an a;

            {
                this.a = this;
            }

            @Override // com.google.android.play.core.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                this.a.j();
            }
        });
        return iVar.c();
    }

    @Override // com.google.android.play.core.assetpacks.t
    public final void b(List<String> list) {
        if (this.e == null) {
            return;
        }
        a.d("cancelDownloads(%s)", list);
        com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        this.e.a(new y(this, iVar, list, iVar));
    }

    @Override // com.google.android.play.core.assetpacks.t
    public final Task<List<String>> c(Map<String, Long> map) {
        if (this.e == null) {
            return z();
        }
        a.d("syncPacks", new Object[0]);
        com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        this.e.a(new z(this, iVar, map, iVar));
        return iVar.c();
    }

    @Override // com.google.android.play.core.assetpacks.t
    public final Task<AssetPackStates> d(List<String> list, as asVar, Map<String, Long> map) {
        if (this.e == null) {
            return z();
        }
        a.d("getPackStates(%s)", list);
        com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        this.e.a(new aa(this, iVar, list, map, iVar, asVar));
        return iVar.c();
    }

    @Override // com.google.android.play.core.assetpacks.t
    public final void e(int i, String str, String str2, int i2) {
        if (this.e == null) {
            throw new bk("The Play Store app is not installed or is an unofficial version.", i);
        }
        a.d("notifyChunkTransferred", new Object[0]);
        com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        this.e.a(new ab(this, iVar, i, str, str2, i2, iVar));
    }

    @Override // com.google.android.play.core.assetpacks.t
    public final void f(int i, String str) {
        y(i, str, 10);
    }

    @Override // com.google.android.play.core.assetpacks.t
    public final void g(int i) {
        if (this.e == null) {
            throw new bk("The Play Store app is not installed or is an unofficial version.", i);
        }
        a.d("notifySessionFailed", new Object[0]);
        com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        this.e.a(new ad(this, iVar, i, iVar));
    }

    @Override // com.google.android.play.core.assetpacks.t
    public final Task<ParcelFileDescriptor> h(int i, String str, String str2, int i2) {
        if (this.e == null) {
            return z();
        }
        a.d("getChunkFileDescriptor(%s, %s, %d, session=%d)", str, str2, Integer.valueOf(i2), Integer.valueOf(i));
        com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        this.e.a(new ae(this, iVar, i, str, str2, i2, iVar));
        return iVar.c();
    }

    @Override // com.google.android.play.core.assetpacks.t
    public final void i(String str) {
        if (this.e == null) {
            return;
        }
        a.d("removePack(%s)", str);
        com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        this.e.a(new w(this, iVar, str, iVar));
    }

    @Override // com.google.android.play.core.assetpacks.t
    public final synchronized void j() {
        if (this.f == null) {
            a.e("Keep alive connection manager is not initialized.", new Object[0]);
            return;
        }
        a.d("keepAlive", new Object[0]);
        if (!this.g.compareAndSet(false, true)) {
            a.d("Service is already kept alive.", new Object[0]);
        } else {
            com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
            this.f.a(new af(this, iVar, iVar));
        }
    }
}
