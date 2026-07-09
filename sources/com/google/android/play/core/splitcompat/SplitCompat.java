package com.google.android.play.core.splitcompat;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.play.core.internal.at;
import com.google.android.play.core.internal.av;
import com.google.android.play.core.internal.aw;
import com.google.android.play.core.internal.ax;
import com.google.android.play.core.internal.bf;
import com.google.android.play.core.internal.bz;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: classes.dex */
public class SplitCompat {
    private static final AtomicReference<SplitCompat> a = new AtomicReference<>(null);
    private final c b;
    private final Set<String> c = new HashSet();
    private final a d;

    private SplitCompat(Context context) {
        try {
            this.b = new c(context);
            this.d = new a(this.b);
        } catch (PackageManager.NameNotFoundException e) {
            throw new bf(e);
        }
    }

    public static boolean a(Context context) {
        return g(context, true);
    }

    public static boolean b() {
        return a.get() != null;
    }

    private static boolean e() {
        return Build.VERSION.SDK_INT < 21;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<String> f() {
        HashSet hashSet;
        synchronized (this.c) {
            hashSet = new HashSet(this.c);
        }
        return hashSet;
    }

    private static boolean g(Context context, boolean z) {
        if (e()) {
            return false;
        }
        boolean zCompareAndSet = a.compareAndSet(null, new SplitCompat(context));
        SplitCompat splitCompat = a.get();
        if (zCompareAndSet) {
            com.google.android.play.core.splitinstall.l.a.b(new at(context, p.a(), new av(context, splitCompat.b, new ax(), null), splitCompat.b, new p()));
            com.google.android.play.core.splitinstall.o.b(new l(splitCompat));
            p.a().execute(new m(context));
        }
        try {
            splitCompat.h(context, z);
            return true;
        } catch (Exception e) {
            Log.e("SplitCompat", "Error installing additional splits", e);
            return false;
        }
    }

    private final synchronized void h(Context context, boolean z) throws IOException {
        ZipFile zipFile;
        if (z) {
            this.b.a();
        } else {
            p.a().execute(new n(this));
        }
        String packageName = context.getPackageName();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            List<String> arrayList = packageInfo.splitNames == null ? new ArrayList() : Arrays.asList(packageInfo.splitNames);
            Set<q> setI = this.b.i();
            HashSet hashSet = new HashSet();
            Iterator<q> it = setI.iterator();
            while (it.hasNext()) {
                String strB = it.next().b();
                if (arrayList.contains(strB)) {
                    if (z) {
                        this.b.n(strB);
                    } else {
                        hashSet.add(strB);
                    }
                    it.remove();
                }
            }
            if (!hashSet.isEmpty()) {
                p.a().execute(new o(this, hashSet));
            }
            HashSet hashSet2 = new HashSet();
            Iterator<q> it2 = setI.iterator();
            while (it2.hasNext()) {
                String strB2 = it2.next().b();
                if (!com.google.android.play.core.splitinstall.p.e(strB2)) {
                    hashSet2.add(strB2);
                }
            }
            for (String str : arrayList) {
                if (!com.google.android.play.core.splitinstall.p.e(str)) {
                    hashSet2.add(str);
                }
            }
            HashSet<q> hashSet3 = new HashSet(setI.size());
            for (q qVar : setI) {
                if (!com.google.android.play.core.splitinstall.p.d(qVar.b())) {
                    String strB3 = qVar.b();
                    if (hashSet2.contains(com.google.android.play.core.splitinstall.p.d(strB3) ? "" : strB3.split("\\.config\\.", 2)[0])) {
                    }
                }
                hashSet3.add(qVar);
            }
            k kVar = new k(this.b);
            aw awVarA = ax.a();
            ClassLoader classLoader = context.getClassLoader();
            if (z) {
                awVarA.a(classLoader, kVar.a());
            } else {
                Iterator it3 = hashSet3.iterator();
                while (it3.hasNext()) {
                    Set<File> setB = kVar.b((q) it3.next());
                    if (setB == null) {
                        it3.remove();
                    } else {
                        awVarA.a(classLoader, setB);
                    }
                }
            }
            HashSet hashSet4 = new HashSet();
            for (q qVar2 : hashSet3) {
                try {
                    zipFile = new ZipFile(qVar2.a());
                    try {
                        ZipEntry entry = zipFile.getEntry("classes.dex");
                        zipFile.close();
                        if (entry == null || awVarA.b(classLoader, this.b.h(qVar2.b()), qVar2.a(), z)) {
                            hashSet4.add(qVar2.a());
                        } else {
                            String strValueOf = String.valueOf(qVar2.a());
                            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 24);
                            sb.append("split was not installed ");
                            sb.append(strValueOf);
                            Log.w("SplitCompat", sb.toString());
                        }
                    } catch (IOException e) {
                        e = e;
                        if (zipFile != null) {
                            try {
                                zipFile.close();
                            } catch (IOException e2) {
                                bz.a(e, e2);
                            }
                        }
                        throw e;
                    }
                } catch (IOException e3) {
                    e = e3;
                    zipFile = null;
                }
            }
            this.d.b(context, hashSet4);
            HashSet hashSet5 = new HashSet();
            for (q qVar3 : hashSet3) {
                if (hashSet4.contains(qVar3.a())) {
                    String strB4 = qVar3.b();
                    StringBuilder sb2 = new StringBuilder(strB4.length() + 30);
                    sb2.append("Split '");
                    sb2.append(strB4);
                    sb2.append("' installation emulated");
                    Log.d("SplitCompat", sb2.toString());
                    hashSet5.add(qVar3.b());
                } else {
                    String strB5 = qVar3.b();
                    StringBuilder sb3 = new StringBuilder(strB5.length() + 35);
                    sb3.append("Split '");
                    sb3.append(strB5);
                    sb3.append("' installation not emulated.");
                    Log.d("SplitCompat", sb3.toString());
                }
            }
            synchronized (this.c) {
                this.c.addAll(hashSet5);
            }
        } catch (PackageManager.NameNotFoundException e4) {
            throw new IOException(String.format("Cannot load data for application '%s'", packageName), e4);
        }
    }

    public static boolean install(@NonNull Context context) {
        return g(context, false);
    }

    public static boolean installActivity(@NonNull Context context) {
        if (e()) {
            return false;
        }
        SplitCompat splitCompat = a.get();
        if (splitCompat != null) {
            return splitCompat.d.a(context, splitCompat.f());
        }
        if (context.getApplicationContext() != null) {
            install(context.getApplicationContext());
        }
        return install(context);
    }
}
