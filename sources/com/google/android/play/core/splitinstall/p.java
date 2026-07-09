package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class p {
    private static final com.google.android.play.core.internal.ag a = new com.google.android.play.core.internal.ag("SplitInstallInfoProvider");
    private final Context b;
    private final String c;

    public p(Context context) {
        this.b = context;
        this.c = context.getPackageName();
    }

    public p(Context context, String str) {
        this.b = context;
        this.c = str;
    }

    public static boolean d(String str) {
        return str.startsWith("config.");
    }

    public static boolean e(String str) {
        return d(str) || str.contains(".config.");
    }

    private final Set<String> f() {
        HashSet hashSet = new HashSet();
        Bundle bundleG = g();
        if (bundleG != null) {
            String string = bundleG.getString("com.android.dynamic.apk.fused.modules");
            if (string == null || string.isEmpty()) {
                a.a("App has no fused modules.", new Object[0]);
            } else {
                Collections.addAll(hashSet, string.split(",", -1));
                hashSet.remove("");
                hashSet.remove("base");
            }
        }
        if (Build.VERSION.SDK_INT >= 21) {
            String[] strArr = null;
            try {
                PackageInfo packageInfo = this.b.getPackageManager().getPackageInfo(this.c, 0);
                if (packageInfo != null) {
                    strArr = packageInfo.splitNames;
                }
            } catch (PackageManager.NameNotFoundException unused) {
                a.e("App is not found in PackageManager", new Object[0]);
            }
            if (strArr != null) {
                a.a("Adding splits from package manager: %s", Arrays.toString(strArr));
                Collections.addAll(hashSet, strArr);
            } else {
                a.a("No splits are found or app cannot be found in package manager.", new Object[0]);
            }
            n nVarA = o.a();
            if (nVarA != null) {
                hashSet.addAll(nVarA.a());
            }
        }
        return hashSet;
    }

    @Nullable
    private final Bundle g() {
        try {
            ApplicationInfo applicationInfo = this.b.getPackageManager().getApplicationInfo(this.c, 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                return applicationInfo.metaData;
            }
            a.a("App has no applicationInfo or metaData", new Object[0]);
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            a.e("App is not found in PackageManager", new Object[0]);
            return null;
        }
    }

    public final Set<String> a() {
        HashSet hashSet = new HashSet();
        for (String str : f()) {
            if (!e(str)) {
                hashSet.add(str);
            }
        }
        return hashSet;
    }

    @Nullable
    public final Set<String> b() {
        h hVarC = c();
        if (hVarC == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        Set<String> setF = f();
        setF.add("");
        Set<String> setA = a();
        setA.add("");
        for (Map.Entry<String, Set<String>> entry : hVarC.a(setA).entrySet()) {
            if (setF.containsAll(entry.getValue())) {
                hashSet.add(entry.getKey());
            }
        }
        return hashSet;
    }

    @Nullable
    public final h c() {
        com.google.android.play.core.internal.ag agVar;
        Object[] objArr;
        String str;
        Bundle bundleG = g();
        if (bundleG == null) {
            agVar = a;
            objArr = new Object[0];
            str = "No metadata found in Context.";
        } else {
            int i = bundleG.getInt("com.android.vending.splits");
            if (i == 0) {
                agVar = a;
                objArr = new Object[0];
                str = "No metadata found in AndroidManifest.";
            } else {
                try {
                    h hVarB = k.b(this.b.getResources().getXml(i), new g());
                    if (hVarB == null) {
                        a.e("Can't parse languages metadata.", new Object[0]);
                    }
                    return hVarB;
                } catch (Resources.NotFoundException unused) {
                    agVar = a;
                    objArr = new Object[0];
                    str = "Resource with languages metadata doesn't exist.";
                }
            }
        }
        agVar.e(str, objArr);
        return null;
    }
}
