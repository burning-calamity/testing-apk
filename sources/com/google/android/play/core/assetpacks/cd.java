package com.google.android.play.core.assetpacks;

import android.support.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class cd {
    private static final com.google.android.play.core.internal.ag a = new com.google.android.play.core.internal.ag("ExtractorTaskFinder");
    private final ca b;
    private final au c;
    private final bc d;
    private final com.google.android.play.core.common.a e;

    cd(ca caVar, au auVar, bc bcVar, com.google.android.play.core.common.a aVar) {
        this.b = caVar;
        this.c = auVar;
        this.d = bcVar;
        this.e = aVar;
    }

    private final boolean b(bx bxVar, by byVar) {
        au auVar = this.c;
        bw bwVar = bxVar.c;
        return new cz(auVar, bwVar.a, bxVar.b, bwVar.b, byVar.a).l();
    }

    private static boolean c(by byVar) {
        int i = byVar.f;
        return i == 1 || i == 2;
    }

    @Nullable
    final cc a() {
        cc cwVar;
        int iK;
        try {
            this.b.a();
            ArrayList arrayList = new ArrayList();
            for (bx bxVar : this.b.c().values()) {
                if (ck.h(bxVar.c.c)) {
                    arrayList.add(bxVar);
                }
            }
            bi biVar = null;
            if (!arrayList.isEmpty()) {
                if (this.e.a()) {
                    Map<String, Long> mapC = this.c.c();
                    Iterator it = arrayList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            cwVar = null;
                            break;
                        }
                        bx bxVar2 = (bx) it.next();
                        Long l = mapC.get(bxVar2.c.a);
                        if (l != null && bxVar2.c.b == l.longValue()) {
                            a.a("Found promote pack task for session %s with pack %s.", Integer.valueOf(bxVar2.a), bxVar2.c.a);
                            int i = bxVar2.a;
                            String str = bxVar2.c.a;
                            this.c.t(str);
                            int i2 = bxVar2.b;
                            bw bwVar = bxVar2.c;
                            cwVar = new cc(i, str, null);
                            break;
                        }
                    }
                    if (cwVar == null) {
                    }
                    return cwVar;
                }
                Iterator it2 = arrayList.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        cwVar = null;
                        break;
                    }
                    bx bxVar3 = (bx) it2.next();
                    try {
                        au auVar = this.c;
                        bw bwVar2 = bxVar3.c;
                        if (auVar.k(bwVar2.a, bxVar3.b, bwVar2.b) == bxVar3.c.e.size()) {
                            a.a("Found final move task for session %s with pack %s.", Integer.valueOf(bxVar3.a), bxVar3.c.a);
                            int i3 = bxVar3.a;
                            bw bwVar3 = bxVar3.c;
                            cwVar = new cq(i3, bwVar3.a, bxVar3.b, bwVar3.b);
                            break;
                        }
                    } catch (IOException e) {
                        throw new bk(String.format("Failed to check number of completed merges for session %s, pack %s", Integer.valueOf(bxVar3.a), bxVar3.c.a), e, bxVar3.a);
                    }
                }
                if (cwVar == null) {
                    Iterator it3 = arrayList.iterator();
                    loop3: while (true) {
                        if (!it3.hasNext()) {
                            cwVar = null;
                            break;
                        }
                        bx bxVar4 = (bx) it3.next();
                        if (ck.h(bxVar4.c.c)) {
                            for (by byVar : bxVar4.c.e) {
                                au auVar2 = this.c;
                                bw bwVar4 = bxVar4.c;
                                if (auVar2.i(bwVar4.a, bxVar4.b, bwVar4.b, byVar.a).exists()) {
                                    a.a("Found merge task for session %s with pack %s and slice %s.", Integer.valueOf(bxVar4.a), bxVar4.c.a, byVar.a);
                                    int i4 = bxVar4.a;
                                    bw bwVar5 = bxVar4.c;
                                    cwVar = new cn(i4, bwVar5.a, bxVar4.b, bwVar5.b, byVar.a);
                                    break loop3;
                                }
                            }
                        }
                    }
                    if (cwVar == null) {
                        Iterator it4 = arrayList.iterator();
                        loop5: while (true) {
                            if (!it4.hasNext()) {
                                cwVar = null;
                                break;
                            }
                            bx bxVar5 = (bx) it4.next();
                            if (ck.h(bxVar5.c.c)) {
                                for (by byVar2 : bxVar5.c.e) {
                                    if (b(bxVar5, byVar2)) {
                                        au auVar3 = this.c;
                                        bw bwVar6 = bxVar5.c;
                                        if (auVar3.h(bwVar6.a, bxVar5.b, bwVar6.b, byVar2.a).exists()) {
                                            a.a("Found verify task for session %s with pack %s and slice %s.", Integer.valueOf(bxVar5.a), bxVar5.c.a, byVar2.a);
                                            int i5 = bxVar5.a;
                                            bw bwVar7 = bxVar5.c;
                                            String str2 = bwVar7.a;
                                            int i6 = bxVar5.b;
                                            long j = bwVar7.b;
                                            String str3 = byVar2.a;
                                            String str4 = byVar2.b;
                                            long j2 = byVar2.c;
                                            cwVar = new dc(i5, str2, i6, j, str3, str4);
                                            break loop5;
                                        }
                                    }
                                }
                            }
                        }
                        if (cwVar == null) {
                            Iterator it5 = arrayList.iterator();
                            loop7: while (true) {
                                if (!it5.hasNext()) {
                                    biVar = null;
                                    break;
                                }
                                bx bxVar6 = (bx) it5.next();
                                if (ck.h(bxVar6.c.c)) {
                                    Iterator<by> it6 = bxVar6.c.e.iterator();
                                    while (it6.hasNext()) {
                                        by next = it6.next();
                                        if (!c(next)) {
                                            au auVar4 = this.c;
                                            bw bwVar8 = bxVar6.c;
                                            Iterator<by> it7 = it6;
                                            try {
                                                iK = new cz(auVar4, bwVar8.a, bxVar6.b, bwVar8.b, next.a).k();
                                            } catch (IOException e2) {
                                                a.b("Slice checkpoint corrupt, restarting extraction. %s", e2);
                                                iK = 0;
                                            }
                                            if (iK != -1 && next.d.get(iK).a) {
                                                a.a("Found extraction task using compression format %s for session %s, pack %s, slice %s, chunk %s.", Integer.valueOf(next.e), Integer.valueOf(bxVar6.a), bxVar6.c.a, next.a, Integer.valueOf(iK));
                                                InputStream inputStreamA = this.d.a(bxVar6.a, bxVar6.c.a, next.a, iK);
                                                int i7 = bxVar6.a;
                                                bw bwVar9 = bxVar6.c;
                                                String str5 = bwVar9.a;
                                                int i8 = bxVar6.b;
                                                long j3 = bwVar9.b;
                                                String str6 = next.a;
                                                int i9 = next.e;
                                                int size = next.d.size();
                                                bw bwVar10 = bxVar6.c;
                                                biVar = new bi(i7, str5, i8, j3, str6, i9, iK, size, bwVar10.d, bwVar10.c, inputStreamA);
                                                break loop7;
                                            }
                                            it6 = it7;
                                        }
                                    }
                                }
                            }
                            if (biVar == null) {
                                Iterator it8 = arrayList.iterator();
                                loop9: while (true) {
                                    if (!it8.hasNext()) {
                                        cwVar = null;
                                        break;
                                    }
                                    bx bxVar7 = (bx) it8.next();
                                    if (ck.h(bxVar7.c.c)) {
                                        for (by byVar3 : bxVar7.c.e) {
                                            if (c(byVar3) && byVar3.d.get(0).a && !b(bxVar7, byVar3)) {
                                                a.a("Found patch slice task using patch format %s for session %s, pack %s, slice %s.", Integer.valueOf(byVar3.f), Integer.valueOf(bxVar7.a), bxVar7.c.a, byVar3.a);
                                                InputStream inputStreamA2 = this.d.a(bxVar7.a, bxVar7.c.a, byVar3.a, 0);
                                                int i10 = bxVar7.a;
                                                String str7 = bxVar7.c.a;
                                                cwVar = new cw(i10, str7, this.c.t(str7), this.c.u(bxVar7.c.a), bxVar7.b, bxVar7.c.b, byVar3.f, byVar3.a, byVar3.c, inputStreamA2);
                                                break loop9;
                                            }
                                        }
                                    }
                                }
                                if (cwVar == null) {
                                    this.b.b();
                                    return null;
                                }
                            }
                        }
                    }
                }
                return cwVar;
            }
            return biVar;
        } finally {
            this.b.b();
        }
    }
}
