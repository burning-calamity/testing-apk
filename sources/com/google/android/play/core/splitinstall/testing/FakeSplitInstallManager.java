package com.google.android.play.core.splitinstall.testing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.play.core.common.IntentSenderForResultStarter;
import com.google.android.play.core.internal.af;
import com.google.android.play.core.internal.ax;
import com.google.android.play.core.internal.bo;
import com.google.android.play.core.splitinstall.SplitInstallException;
import com.google.android.play.core.splitinstall.SplitInstallManager;
import com.google.android.play.core.splitinstall.SplitInstallRequest;
import com.google.android.play.core.splitinstall.SplitInstallSessionState;
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener;
import com.google.android.play.core.splitinstall.l;
import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode;
import com.google.android.play.core.splitinstall.p;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public class FakeSplitInstallManager implements SplitInstallManager {
    public static final /* synthetic */ int a = 0;
    private static final long c = TimeUnit.SECONDS.toMillis(1);
    private final Handler b;
    private final Context d;
    private final p e;
    private final bo f;
    private final af<SplitInstallSessionState> g;
    private final af<SplitInstallSessionState> h;
    private final Executor i;
    private final com.google.android.play.core.splitinstall.e j;
    private final File k;
    private final AtomicReference<SplitInstallSessionState> l;
    private final Set<String> m;
    private final Set<String> n;
    private final AtomicBoolean o;
    private final a p;

    @Deprecated
    public FakeSplitInstallManager(Context context, File file) {
        this(context, file, new p(context, context.getPackageName()));
    }

    FakeSplitInstallManager(Context context, @Nullable File file, p pVar) {
        Executor executorA = com.google.android.play.core.splitcompat.p.a();
        bo boVar = new bo(context);
        a aVar = a.a;
        this.b = new Handler(Looper.getMainLooper());
        this.l = new AtomicReference<>();
        this.m = Collections.synchronizedSet(new HashSet());
        this.n = Collections.synchronizedSet(new HashSet());
        this.o = new AtomicBoolean(false);
        this.d = context;
        this.k = file;
        this.e = pVar;
        this.i = executorA;
        this.f = boVar;
        this.p = aVar;
        this.h = new af<>();
        this.g = new af<>();
        this.j = l.a;
    }

    static final /* synthetic */ SplitInstallSessionState g(int i, SplitInstallSessionState splitInstallSessionState) {
        int iStatus;
        if (splitInstallSessionState != null && i == splitInstallSessionState.sessionId() && ((iStatus = splitInstallSessionState.status()) == 1 || iStatus == 2 || iStatus == 8 || iStatus == 9 || iStatus == 7)) {
            return SplitInstallSessionState.create(i, 7, splitInstallSessionState.errorCode(), splitInstallSessionState.bytesDownloaded(), splitInstallSessionState.totalBytesToDownload(), splitInstallSessionState.moduleNames(), splitInstallSessionState.languages());
        }
        throw new SplitInstallException(-3);
    }

    static final /* synthetic */ SplitInstallSessionState i(Integer num, int i, int i2, Long l, Long l2, List list, List list2, SplitInstallSessionState splitInstallSessionState) {
        SplitInstallSessionState splitInstallSessionStateCreate = splitInstallSessionState == null ? SplitInstallSessionState.create(0, 0, 0, 0L, 0L, new ArrayList(), new ArrayList()) : splitInstallSessionState;
        return SplitInstallSessionState.create(num == null ? splitInstallSessionStateCreate.sessionId() : num.intValue(), i, i2, l == null ? splitInstallSessionStateCreate.bytesDownloaded() : l.longValue(), l2 == null ? splitInstallSessionStateCreate.totalBytesToDownload() : l2.longValue(), list == null ? splitInstallSessionStateCreate.moduleNames() : list, list2 == null ? splitInstallSessionStateCreate.languages() : list2);
    }

    static final /* synthetic */ void j() {
        SystemClock.sleep(c);
    }

    @Nullable
    private final SplitInstallSessionState n() {
        return this.l.get();
    }

    @Nullable
    private final synchronized SplitInstallSessionState o(i iVar) {
        SplitInstallSessionState splitInstallSessionStateN = n();
        SplitInstallSessionState splitInstallSessionStateA = iVar.a(splitInstallSessionStateN);
        if (this.l.compareAndSet(splitInstallSessionStateN, splitInstallSessionStateA)) {
            return splitInstallSessionStateA;
        }
        return null;
    }

    private final boolean p(final int i, final int i2, @Nullable final Long l, @Nullable final Long l2, @Nullable final List<String> list, @Nullable final Integer num, @Nullable final List<String> list2) {
        SplitInstallSessionState splitInstallSessionStateO = o(new i(num, i, i2, l, l2, list, list2) { // from class: com.google.android.play.core.splitinstall.testing.b
            private final Integer a;
            private final int b;
            private final int c;
            private final Long d;
            private final Long e;
            private final List f;
            private final List g;

            {
                this.a = num;
                this.b = i;
                this.c = i2;
                this.d = l;
                this.e = l2;
                this.f = list;
                this.g = list2;
            }

            @Override // com.google.android.play.core.splitinstall.testing.i
            public final SplitInstallSessionState a(SplitInstallSessionState splitInstallSessionState) {
                return FakeSplitInstallManager.i(this.a, this.b, this.c, this.d, this.e, this.f, this.g, splitInstallSessionState);
            }
        });
        if (splitInstallSessionStateO == null) {
            return false;
        }
        t(splitInstallSessionStateO);
        return true;
    }

    private final Task<Integer> q(@SplitInstallErrorCode int i) {
        o(new e(i, null));
        return Tasks.b(new SplitInstallException(i));
    }

    private static String r(String str) {
        return str.split("\\.config\\.", 2)[0];
    }

    private final com.google.android.play.core.splitinstall.h s() {
        com.google.android.play.core.splitinstall.h hVarC = this.e.c();
        if (hVarC != null) {
            return hVarC;
        }
        throw new IllegalStateException("Language information could not be found. Make sure you are using the target application context, not the tests context, and the app is built as a bundle.");
    }

    private final void t(final SplitInstallSessionState splitInstallSessionState) {
        this.b.post(new Runnable(this, splitInstallSessionState) { // from class: com.google.android.play.core.splitinstall.testing.f
            private final FakeSplitInstallManager a;
            private final SplitInstallSessionState b;

            {
                this.a = this;
                this.b = splitInstallSessionState;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.a.f(this.b);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void u(List<Intent> list, List<String> list2, List<String> list3, long j, boolean z) {
        this.j.a().a(list, new h(this, list2, list3, j, z, list));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void v(List<String> list, List<String> list2, long j) {
        this.m.addAll(list);
        this.n.addAll(list2);
        Long lValueOf = Long.valueOf(j);
        p(5, 0, lValueOf, lValueOf, null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean w(int i) {
        return p(6, i, null, null, null, null, null);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final void a(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.g.a(splitInstallStateUpdatedListener);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final void b(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.g.b(splitInstallStateUpdatedListener);
    }

    final File c() {
        return this.k;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> cancelInstall(int i) {
        try {
            SplitInstallSessionState splitInstallSessionStateO = o(new e(i));
            if (splitInstallSessionStateO != null) {
                t(splitInstallSessionStateO);
            }
            return Tasks.a(null);
        } catch (SplitInstallException e) {
            return Tasks.b(e);
        }
    }

    final /* synthetic */ void d(List list, List list2, List list3, long j) {
        if (this.o.get()) {
            w(-6);
        } else if (this.j.a() != null) {
            u(list, list2, list3, j, false);
        } else {
            v(list2, list3, j);
        }
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredInstall(List<String> list) {
        return Tasks.b(new SplitInstallException(-5));
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredLanguageInstall(List<Locale> list) {
        return Tasks.b(new SplitInstallException(-5));
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredLanguageUninstall(List<Locale> list) {
        return Tasks.b(new SplitInstallException(-5));
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Void> deferredUninstall(List<String> list) {
        return Tasks.b(new SplitInstallException(-5));
    }

    final /* synthetic */ void e(long j, List list, List list2, List list3) {
        long j2 = j / 3;
        long jMin = 0;
        for (int i = 0; i < 3; i++) {
            jMin = Math.min(j, jMin + j2);
            p(2, 0, Long.valueOf(jMin), Long.valueOf(j), null, null, null);
            j();
            SplitInstallSessionState splitInstallSessionStateN = n();
            if (splitInstallSessionStateN.status() == 9 || splitInstallSessionStateN.status() == 7 || splitInstallSessionStateN.status() == 6) {
                return;
            }
        }
        this.i.execute(new g(this, list, list2, list3, j));
    }

    final /* synthetic */ void f(SplitInstallSessionState splitInstallSessionState) {
        this.g.c(splitInstallSessionState);
        this.h.c(splitInstallSessionState);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Set<String> getInstalledLanguages() {
        HashSet hashSet = new HashSet();
        if (this.e.b() != null) {
            hashSet.addAll(this.e.b());
        }
        hashSet.addAll(this.n);
        return hashSet;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Set<String> getInstalledModules() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(this.e.a());
        hashSet.addAll(this.m);
        return hashSet;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<SplitInstallSessionState> getSessionState(int i) {
        SplitInstallSessionState splitInstallSessionStateN = n();
        return (splitInstallSessionStateN == null || splitInstallSessionStateN.sessionId() != i) ? Tasks.b(new SplitInstallException(-4)) : Tasks.a(splitInstallSessionStateN);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<List<SplitInstallSessionState>> getSessionStates() {
        SplitInstallSessionState splitInstallSessionStateN = n();
        return Tasks.a(splitInstallSessionStateN != null ? Collections.singletonList(splitInstallSessionStateN) : Collections.emptyList());
    }

    final /* synthetic */ void h(List list, List list2) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            File file = (File) it.next();
            String strB = ax.b(file);
            Uri uriFromFile = Uri.fromFile(file);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(uriFromFile, this.d.getContentResolver().getType(uriFromFile));
            intent.addFlags(1);
            intent.putExtra("module_name", r(strB));
            intent.putExtra("split_id", strB);
            arrayList.add(intent);
            arrayList2.add(r(ax.b(file)));
        }
        SplitInstallSessionState splitInstallSessionStateN = n();
        if (splitInstallSessionStateN == null) {
            return;
        }
        this.i.execute(new g(this, splitInstallSessionStateN.totalBytesToDownload(), arrayList, arrayList2, list2));
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final void registerListener(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.h.a(splitInstallStateUpdatedListener);
    }

    public void setShouldNetworkError(boolean z) {
        this.o.set(z);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final boolean startConfirmationDialogForResult(SplitInstallSessionState splitInstallSessionState, Activity activity, int i) throws IntentSender.SendIntentException {
        return false;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final boolean startConfirmationDialogForResult(SplitInstallSessionState splitInstallSessionState, IntentSenderForResultStarter intentSenderForResultStarter, int i) throws IntentSender.SendIntentException {
        return false;
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Integer> startInstall(final SplitInstallRequest splitInstallRequest) {
        int i;
        File[] fileArr;
        int i2;
        int i3;
        try {
            SplitInstallSessionState splitInstallSessionStateO = o(new i(splitInstallRequest) { // from class: com.google.android.play.core.splitinstall.testing.d
                private final SplitInstallRequest a;

                {
                    this.a = splitInstallRequest;
                }

                @Override // com.google.android.play.core.splitinstall.testing.i
                public final SplitInstallSessionState a(SplitInstallSessionState splitInstallSessionState) {
                    SplitInstallRequest splitInstallRequest2 = this.a;
                    int i4 = FakeSplitInstallManager.a;
                    if (splitInstallSessionState == null || splitInstallSessionState.hasTerminalStatus()) {
                        return SplitInstallSessionState.create(splitInstallSessionState == null ? 1 : 1 + splitInstallSessionState.sessionId(), 1, 0, 0L, 0L, splitInstallRequest2.getModuleNames(), new ArrayList());
                    }
                    throw new SplitInstallException(-1);
                }
            });
            if (splitInstallSessionStateO == null) {
                return q(-100);
            }
            int iSessionId = splitInstallSessionStateO.sessionId();
            final ArrayList arrayList = new ArrayList();
            Iterator<Locale> it = splitInstallRequest.getLanguages().iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getLanguage());
            }
            HashSet hashSet = new HashSet();
            final ArrayList arrayList2 = new ArrayList();
            File[] fileArrListFiles = this.k.listFiles();
            if (fileArrListFiles == null) {
                Log.w("FakeSplitInstallManager", "Specified splits directory does not exist.");
                return q(-5);
            }
            int length = fileArrListFiles.length;
            int i4 = 0;
            long length2 = 0;
            while (i4 < length) {
                File file = fileArrListFiles[i4];
                String strB = ax.b(file);
                String strR = r(strB);
                hashSet.add(strB);
                if (splitInstallRequest.getModuleNames().contains(strR)) {
                    String strR2 = r(strB);
                    HashSet hashSet2 = new HashSet(this.f.a());
                    fileArr = fileArrListFiles;
                    Map<String, Set<String>> mapA = s().a(Arrays.asList(strR2));
                    HashSet hashSet3 = new HashSet();
                    Iterator<Set<String>> it2 = mapA.values().iterator();
                    while (it2.hasNext()) {
                        hashSet3.addAll(it2.next());
                        length = length;
                    }
                    i2 = length;
                    HashSet hashSet4 = new HashSet();
                    Iterator it3 = hashSet2.iterator();
                    while (it3.hasNext()) {
                        String str = (String) it3.next();
                        Iterator it4 = it3;
                        if (str.contains("_")) {
                            i3 = iSessionId;
                            str = str.split("_", -1)[0];
                        } else {
                            i3 = iSessionId;
                        }
                        hashSet4.add(str);
                        it3 = it4;
                        iSessionId = i3;
                    }
                    i = iSessionId;
                    hashSet4.addAll(this.n);
                    hashSet4.addAll(arrayList);
                    HashSet hashSet5 = new HashSet();
                    for (Map.Entry<String, Set<String>> entry : mapA.entrySet()) {
                        if (hashSet4.contains(entry.getKey())) {
                            hashSet5.addAll(entry.getValue());
                        }
                    }
                    if (!hashSet3.contains(strB) || hashSet5.contains(strB)) {
                        length2 += file.length();
                        arrayList2.add(file);
                        break;
                    }
                    i4++;
                    fileArrListFiles = fileArr;
                    length = i2;
                    iSessionId = i;
                } else {
                    i = iSessionId;
                    fileArr = fileArrListFiles;
                    i2 = length;
                }
                List<Locale> languages = splitInstallRequest.getLanguages();
                ArrayList arrayList3 = new ArrayList(this.m);
                arrayList3.addAll(Arrays.asList("", "base"));
                Map<String, Set<String>> mapA2 = s().a(arrayList3);
                for (Locale locale : languages) {
                    if (mapA2.containsKey(locale.getLanguage()) && mapA2.get(locale.getLanguage()).contains(strB)) {
                        length2 += file.length();
                        arrayList2.add(file);
                        break;
                        break;
                    }
                }
                i4++;
                fileArrListFiles = fileArr;
                length = i2;
                iSessionId = i;
            }
            int i5 = iSessionId;
            String strValueOf = String.valueOf(hashSet);
            String strValueOf2 = String.valueOf(splitInstallRequest.getModuleNames());
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 22 + String.valueOf(strValueOf2).length());
            sb.append("availableSplits ");
            sb.append(strValueOf);
            sb.append(" want ");
            sb.append(strValueOf2);
            Log.i("FakeSplitInstallManager", sb.toString());
            if (!hashSet.containsAll(new HashSet(splitInstallRequest.getModuleNames()))) {
                return q(-2);
            }
            Long lValueOf = Long.valueOf(length2);
            List<String> moduleNames = splitInstallRequest.getModuleNames();
            Integer numValueOf = Integer.valueOf(i5);
            p(1, 0, 0L, lValueOf, moduleNames, numValueOf, arrayList);
            this.i.execute(new Runnable(this, arrayList2, arrayList) { // from class: com.google.android.play.core.splitinstall.testing.c
                private final FakeSplitInstallManager a;
                private final List b;
                private final List c;

                {
                    this.a = this;
                    this.b = arrayList2;
                    this.c = arrayList;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.a.h(this.b, this.c);
                }
            });
            return Tasks.a(numValueOf);
        } catch (SplitInstallException e) {
            return q(e.getErrorCode());
        }
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final void unregisterListener(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.h.b(splitInstallStateUpdatedListener);
    }
}
