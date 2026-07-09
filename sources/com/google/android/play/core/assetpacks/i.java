package com.google.android.play.core.assetpacks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.play.core.assetpacks.model.AssetPackStatus;
import com.google.android.play.core.common.PlayCoreDialogWrapperActivity;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
final class i implements AssetPackManager {
    private static final com.google.android.play.core.internal.ag a = new com.google.android.play.core.internal.ag("AssetPackManager");
    private final au b;
    private final com.google.android.play.core.internal.ca<t> c;
    private final ar d;
    private final com.google.android.play.core.splitinstall.p e;
    private final ca f;
    private final bo g;
    private final be h;
    private final com.google.android.play.core.internal.ca<Executor> i;
    private final com.google.android.play.core.common.a j;
    private final Handler k = new Handler(Looper.getMainLooper());
    private boolean l;

    i(au auVar, com.google.android.play.core.internal.ca<t> caVar, ar arVar, com.google.android.play.core.splitinstall.p pVar, ca caVar2, bo boVar, be beVar, com.google.android.play.core.internal.ca<Executor> caVar3, com.google.android.play.core.common.a aVar) {
        this.b = auVar;
        this.c = caVar;
        this.d = arVar;
        this.e = pVar;
        this.f = caVar2;
        this.g = boVar;
        this.h = beVar;
        this.i = caVar3;
        this.j = aVar;
    }

    private final void h() {
        this.i.a().execute(new e(this, null));
    }

    private final void i() {
        this.i.a().execute(new e(this));
        this.l = true;
    }

    final void a(boolean z) {
        boolean zJ = this.d.j();
        this.d.e(z);
        if (!z || zJ) {
            return;
        }
        h();
    }

    @AssetPackStatus
    final int b(@AssetPackStatus int i, String str) {
        if (!this.b.a(str) && i == 4) {
            return 8;
        }
        if (!this.b.a(str) || i == 4) {
            return i;
        }
        return 4;
    }

    final /* synthetic */ void c() {
        this.b.v();
        this.b.s();
        this.b.w();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final AssetPackStates cancel(List<String> list) {
        Map<String, Integer> mapH = this.f.h(list);
        HashMap map = new HashMap();
        for (String str : list) {
            Integer num = mapH.get(str);
            map.put(str, AssetPackState.c(str, num == null ? 0 : num.intValue(), 0, 0L, 0L, 0.0d, 0, ""));
        }
        this.c.a().b(list);
        return AssetPackStates.a(0L, map);
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final void clearListeners() {
        this.d.h();
    }

    final /* synthetic */ void d() {
        Task<List<String>> taskC = this.c.a().c(this.b.c());
        Executor executorA = this.i.a();
        au auVar = this.b;
        auVar.getClass();
        taskC.addOnSuccessListener(executorA, f.a(auVar));
        taskC.addOnFailureListener(this.i.a(), g.a);
    }

    final /* synthetic */ void f(String str, com.google.android.play.core.tasks.i iVar) {
        if (!this.b.q(str)) {
            iVar.b(new IOException(String.format("Failed to remove pack %s.", str)));
        } else {
            iVar.a(null);
            this.c.a().i(str);
        }
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final Task<AssetPackStates> fetch(List<String> list) {
        Map<String, Long> mapC = this.b.c();
        ArrayList arrayList = new ArrayList(list);
        ArrayList arrayList2 = new ArrayList();
        if (!this.j.a()) {
            arrayList.removeAll(mapC.keySet());
            arrayList2.addAll(list);
            arrayList2.removeAll(arrayList);
        }
        if (!arrayList.isEmpty()) {
            return this.c.a().a(arrayList2, arrayList, mapC);
        }
        Bundle bundle = new Bundle();
        bundle.putInt("session_id", 0);
        bundle.putInt("error_code", 0);
        for (String str : list) {
            bundle.putInt(com.google.android.play.core.internal.i.e("status", str), 4);
            bundle.putInt(com.google.android.play.core.internal.i.e("error_code", str), 0);
            bundle.putLong(com.google.android.play.core.internal.i.e("total_bytes_to_download", str), 0L);
            bundle.putLong(com.google.android.play.core.internal.i.e("bytes_downloaded", str), 0L);
        }
        bundle.putStringArrayList("pack_names", new ArrayList<>(list));
        bundle.putLong("total_bytes_to_download", 0L);
        bundle.putLong("bytes_downloaded", 0L);
        return Tasks.a(AssetPackStates.b(bundle, this.g));
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    @Nullable
    public final AssetLocation getAssetLocation(String str, String str2) {
        AssetPackLocation assetPackLocationD;
        if (!this.l) {
            this.i.a().execute(new e(this));
            this.l = true;
        }
        if (this.b.a(str)) {
            try {
                assetPackLocationD = this.b.d(str);
            } catch (IOException unused) {
            }
        } else {
            assetPackLocationD = this.e.a().contains(str) ? AssetPackLocation.a() : null;
        }
        if (assetPackLocationD == null) {
            return null;
        }
        if (assetPackLocationD.packStorageMethod() == 1) {
            return this.b.y(str, str2);
        }
        if (assetPackLocationD.packStorageMethod() == 0) {
            return this.b.z(str, str2, assetPackLocationD);
        }
        a.a("The asset %s is not present in Asset Pack %s", str2, str);
        return null;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    @Nullable
    public final AssetPackLocation getPackLocation(String str) {
        if (!this.l) {
            i();
        }
        if (this.b.a(str)) {
            try {
                return this.b.d(str);
            } catch (IOException unused) {
                return null;
            }
        }
        if (this.e.a().contains(str)) {
            return AssetPackLocation.a();
        }
        return null;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final Map<String, AssetPackLocation> getPackLocations() {
        Map<String, AssetPackLocation> mapB = this.b.b();
        HashMap map = new HashMap();
        Iterator<String> it = this.e.a().iterator();
        while (it.hasNext()) {
            map.put(it.next(), AssetPackLocation.a());
        }
        mapB.putAll(map);
        return mapB;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final Task<AssetPackStates> getPackStates(List<String> list) {
        return this.c.a().d(list, new as(this) { // from class: com.google.android.play.core.assetpacks.c
            private final i a;

            {
                this.a = this;
            }

            @Override // com.google.android.play.core.assetpacks.as
            public final int a(int i, String str) {
                return this.a.b(i, str);
            }
        }, this.b.c());
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final synchronized void registerListener(AssetPackStateUpdateListener assetPackStateUpdateListener) {
        boolean zJ = this.d.j();
        this.d.f(assetPackStateUpdateListener);
        if (zJ) {
            return;
        }
        h();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final Task<Void> removePack(final String str) {
        final com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        this.i.a().execute(new Runnable(this, str, iVar) { // from class: com.google.android.play.core.assetpacks.d
            private final i a;
            private final String b;
            private final com.google.android.play.core.tasks.i c;

            {
                this.a = this;
                this.b = str;
                this.c = iVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.a.f(this.b, this.c);
            }
        });
        return iVar.c();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final Task<Integer> showCellularDataConfirmation(Activity activity) {
        if (activity == null) {
            return Tasks.b(new AssetPackException(-3));
        }
        if (this.h.b() == null) {
            return Tasks.b(new AssetPackException(-12));
        }
        Intent intent = new Intent(activity, (Class<?>) PlayCoreDialogWrapperActivity.class);
        intent.putExtra("confirmation_intent", this.h.b());
        com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        intent.putExtra("result_receiver", new h(this, this.k, iVar));
        activity.startActivity(intent);
        return iVar.c();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackManager
    public final void unregisterListener(AssetPackStateUpdateListener assetPackStateUpdateListener) {
        this.d.g(assetPackStateUpdateListener);
    }
}
