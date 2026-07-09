package com.google.android.play.core.assetpacks;

import android.content.Intent;
import android.os.Bundle;
import com.appsflyer.ServerParameters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
final class ca {
    private static final com.google.android.play.core.internal.ag a = new com.google.android.play.core.internal.ag("ExtractorSessionStoreView");
    private final au b;
    private final com.google.android.play.core.internal.ca<t> c;
    private final bo d;
    private final com.google.android.play.core.internal.ca<Executor> e;
    private final Map<Integer, bx> f = new HashMap();
    private final ReentrantLock g = new ReentrantLock();

    ca(au auVar, com.google.android.play.core.internal.ca<t> caVar, bo boVar, com.google.android.play.core.internal.ca<Executor> caVar2) {
        this.b = auVar;
        this.c = caVar;
        this.d = boVar;
        this.e = caVar2;
    }

    private final Map<String, bx> q(List<String> list) {
        return (Map) r(new bt(this, list, null));
    }

    private final <T> T r(bz<T> bzVar) {
        try {
            a();
            return bzVar.a();
        } finally {
            b();
        }
    }

    private final bx s(int i) {
        Map<Integer, bx> map = this.f;
        Integer numValueOf = Integer.valueOf(i);
        bx bxVar = map.get(numValueOf);
        if (bxVar != null) {
            return bxVar;
        }
        throw new bk(String.format("Could not find session %d while trying to get it", numValueOf), i);
    }

    private static String t(Bundle bundle) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList("pack_names");
        if (stringArrayList == null || stringArrayList.isEmpty()) {
            throw new bk("Session without pack received.");
        }
        return stringArrayList.get(0);
    }

    private static <T> List<T> u(List<T> list) {
        return list == null ? Collections.emptyList() : list;
    }

    final void a() {
        this.g.lock();
    }

    final void b() {
        this.g.unlock();
    }

    final Map<Integer, bx> c() {
        return this.f;
    }

    final boolean d(Bundle bundle) {
        return ((Boolean) r(new bq(this, bundle, null))).booleanValue();
    }

    final boolean e(Bundle bundle) {
        return ((Boolean) r(new bq(this, bundle))).booleanValue();
    }

    final void f(final String str, final int i, final long j) {
        r(new bz(this, str, i, j) { // from class: com.google.android.play.core.assetpacks.br
            private final ca a;
            private final String b;
            private final int c;
            private final long d;

            {
                this.a = this;
                this.b = str;
                this.c = i;
                this.d = j;
            }

            @Override // com.google.android.play.core.assetpacks.bz
            public final Object a() {
                this.a.m(this.b, this.c, this.d);
                return null;
            }
        });
    }

    final void g(int i) {
        r(new bs(this, i));
    }

    final Map<String, Integer> h(List<String> list) {
        return (Map) r(new bt(this, list));
    }

    final /* synthetic */ Map i(List list) {
        int i;
        Map<String, bx> mapQ = q(list);
        HashMap map = new HashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            final bx bxVar = mapQ.get(str);
            if (bxVar == null) {
                i = 8;
            } else {
                if (ck.f(bxVar.c.c)) {
                    try {
                        bxVar.c.c = 6;
                        this.e.a().execute(new Runnable(this, bxVar) { // from class: com.google.android.play.core.assetpacks.bu
                            private final ca a;
                            private final bx b;

                            {
                                this.a = this;
                                this.b = bxVar;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                this.a.g(this.b.a);
                            }
                        });
                        this.d.a(str);
                    } catch (bk unused) {
                        a.d("Session %d with pack %s does not exist, no need to cancel.", Integer.valueOf(bxVar.a), str);
                    }
                }
                i = bxVar.c.c;
            }
            map.put(str, Integer.valueOf(i));
        }
        return map;
    }

    final /* synthetic */ Map j(List list) {
        HashMap map = new HashMap();
        for (bx bxVar : this.f.values()) {
            String str = bxVar.c.a;
            if (list.contains(str)) {
                bx bxVar2 = (bx) map.get(str);
                if ((bxVar2 == null ? -1 : bxVar2.a) < bxVar.a) {
                    map.put(str, bxVar);
                }
            }
        }
        return map;
    }

    final /* synthetic */ Boolean k(Bundle bundle) {
        boolean z;
        int i = bundle.getInt("session_id");
        if (i == 0) {
            return true;
        }
        Map<Integer, bx> map = this.f;
        Integer numValueOf = Integer.valueOf(i);
        if (!map.containsKey(numValueOf)) {
            return true;
        }
        bx bxVar = this.f.get(numValueOf);
        if (bxVar.c.c == 6) {
            z = false;
        } else {
            z = !ck.i(bxVar.c.c, bundle.getInt(com.google.android.play.core.internal.i.e("status", t(bundle))));
        }
        return Boolean.valueOf(z);
    }

    final /* synthetic */ Boolean l(Bundle bundle) {
        int i = bundle.getInt("session_id");
        if (i == 0) {
            return false;
        }
        Map<Integer, bx> map = this.f;
        Integer numValueOf = Integer.valueOf(i);
        boolean z = true;
        if (map.containsKey(numValueOf)) {
            bx bxVarS = s(i);
            int i2 = bundle.getInt(com.google.android.play.core.internal.i.e("status", bxVarS.c.a));
            if (ck.i(bxVarS.c.c, i2)) {
                a.a("Found stale update for session %s with status %d.", numValueOf, Integer.valueOf(bxVarS.c.c));
                bw bwVar = bxVarS.c;
                String str = bwVar.a;
                int i3 = bwVar.c;
                if (i3 == 4) {
                    this.c.a().f(i, str);
                } else if (i3 == 5) {
                    this.c.a().g(i);
                } else if (i3 == 6) {
                    this.c.a().b(Arrays.asList(str));
                }
            } else {
                bxVarS.c.c = i2;
                if (ck.g(i2)) {
                    g(i);
                    this.d.a(bxVarS.c.a);
                } else {
                    for (by byVar : bxVarS.c.e) {
                        ArrayList parcelableArrayList = bundle.getParcelableArrayList(com.google.android.play.core.internal.i.f("chunk_intents", bxVarS.c.a, byVar.a));
                        if (parcelableArrayList != null) {
                            for (int i4 = 0; i4 < parcelableArrayList.size(); i4++) {
                                if (parcelableArrayList.get(i4) != null && ((Intent) parcelableArrayList.get(i4)).getData() != null) {
                                    byVar.d.get(i4).a = true;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            String strT = t(bundle);
            long j = bundle.getLong(com.google.android.play.core.internal.i.e("pack_version", strT));
            int i5 = bundle.getInt(com.google.android.play.core.internal.i.e("status", strT));
            long j2 = bundle.getLong(com.google.android.play.core.internal.i.e("total_bytes_to_download", strT));
            ArrayList<String> stringArrayList = bundle.getStringArrayList(com.google.android.play.core.internal.i.e("slice_ids", strT));
            ArrayList arrayList = new ArrayList();
            for (String str2 : u(stringArrayList)) {
                ArrayList parcelableArrayList2 = bundle.getParcelableArrayList(com.google.android.play.core.internal.i.f("chunk_intents", strT, str2));
                ArrayList arrayList2 = new ArrayList();
                Iterator it = u(parcelableArrayList2).iterator();
                while (it.hasNext()) {
                    if (((Intent) it.next()) == null) {
                        z = false;
                    }
                    arrayList2.add(new bv(z));
                    z = true;
                }
                String string = bundle.getString(com.google.android.play.core.internal.i.f("uncompressed_hash_sha256", strT, str2));
                long j3 = bundle.getLong(com.google.android.play.core.internal.i.f("uncompressed_size", strT, str2));
                int i6 = bundle.getInt(com.google.android.play.core.internal.i.f("patch_format", strT, str2), 0);
                arrayList.add(i6 != 0 ? new by(str2, string, j3, arrayList2, 0, i6) : new by(str2, string, j3, arrayList2, bundle.getInt(com.google.android.play.core.internal.i.f("compression_format", strT, str2), 0), 0));
                z = true;
            }
            this.f.put(Integer.valueOf(i), new bx(i, bundle.getInt(ServerParameters.APP_VERSION_CODE), new bw(strT, j, i5, j2, arrayList)));
        }
        return true;
    }

    final /* synthetic */ void m(String str, int i, long j) {
        bx bxVar = q(Arrays.asList(str)).get(str);
        if (bxVar == null || ck.g(bxVar.c.c)) {
            a.b(String.format("Could not find pack %s while trying to complete it", str), new Object[0]);
        }
        this.b.B(str, i, j);
        bxVar.c.c = 4;
    }

    final /* synthetic */ void n(int i) {
        s(i).c.c = 5;
    }

    final /* synthetic */ void o(int i) {
        bx bxVarS = s(i);
        if (!ck.g(bxVarS.c.c)) {
            throw new bk(String.format("Could not safely delete session %d because it is not in a terminal state.", Integer.valueOf(i)), i);
        }
        au auVar = this.b;
        bw bwVar = bxVarS.c;
        auVar.B(bwVar.a, bxVarS.b, bwVar.b);
        bw bwVar2 = bxVarS.c;
        int i2 = bwVar2.c;
        if (i2 == 5 || i2 == 6) {
            this.b.C(bwVar2.a, bxVarS.b, bwVar2.b);
        }
    }

    final void p(int i) {
        r(new bs(this, i, null));
    }
}
