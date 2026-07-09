package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.support.annotation.Nullable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
final class au {
    private static final com.google.android.play.core.internal.ag a = new com.google.android.play.core.internal.ag("AssetPackStorage");
    private static final long b = TimeUnit.DAYS.toMillis(14);
    private static final long c = TimeUnit.DAYS.toMillis(28);
    private final Context d;
    private final cv e;

    au(Context context, cv cvVar) {
        this.d = context;
        this.e = cvVar;
    }

    private final File D(String str, int i) {
        return new File(E(str), String.valueOf(i));
    }

    private final File E(String str) {
        return new File(O(), str);
    }

    private final File F(String str, int i, long j) {
        return new File(j(str, i, j), "merge.tmp");
    }

    private static void G(File file) {
        if (file.listFiles() == null || file.listFiles().length <= 1) {
            return;
        }
        long J = J(file);
        for (File file2 : file.listFiles()) {
            if (!file2.getName().equals(String.valueOf(J)) && !file2.getName().equals("stale.tmp")) {
                P(file2);
            }
        }
    }

    private static long H(File file) {
        return I(file, true);
    }

    private static long I(File file, boolean z) {
        if (!file.exists()) {
            return -1L;
        }
        ArrayList arrayList = new ArrayList();
        if (z && file.listFiles().length > 1) {
            a.e("Multiple pack versions found, using highest version code.", new Object[0]);
        }
        try {
            for (File file2 : file.listFiles()) {
                if (!file2.getName().equals("stale.tmp")) {
                    arrayList.add(Long.valueOf(file2.getName()));
                }
            }
        } catch (NumberFormatException e) {
            a.c(e, "Corrupt asset pack directories.", new Object[0]);
        }
        if (arrayList.isEmpty()) {
            return -1L;
        }
        Collections.sort(arrayList);
        return ((Long) arrayList.get(arrayList.size() - 1)).longValue();
    }

    private static long J(File file) {
        return I(file, false);
    }

    private static List<String> K(PackageInfo packageInfo, String str) {
        ArrayList arrayList = new ArrayList();
        if (packageInfo.splitNames == null) {
            return arrayList;
        }
        for (int i = (-Arrays.binarySearch(packageInfo.splitNames, str)) - 1; i < packageInfo.splitNames.length && packageInfo.splitNames[i].startsWith(str); i++) {
            arrayList.add(packageInfo.applicationInfo.splitSourceDirs[i]);
        }
        return arrayList;
    }

    private final List<File> L() {
        ArrayList arrayList = new ArrayList();
        try {
        } catch (IOException e) {
            a.b("Could not process directory while scanning installed packs. %s", e);
        }
        if (O().exists() && O().listFiles() != null) {
            for (File file : O().listFiles()) {
                if (!file.getCanonicalPath().equals(N().getCanonicalPath())) {
                    arrayList.add(file);
                }
            }
            return arrayList;
        }
        return arrayList;
    }

    private final File M(String str, int i, long j) {
        return new File(new File(new File(N(), str), String.valueOf(i)), String.valueOf(j));
    }

    private final File N() {
        return new File(O(), "_tmp");
    }

    private final File O() {
        return new File(this.d.getFilesDir(), "assetpacks");
    }

    private static boolean P(File file) {
        boolean zP;
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            zP = true;
            for (File file2 : fileArrListFiles) {
                zP &= P(file2);
            }
        } else {
            zP = true;
        }
        return file.delete() && true == zP;
    }

    final void A(List<String> list) {
        int iA = this.e.a();
        for (File file : L()) {
            if (!list.contains(file.getName()) && H(file) != iA) {
                P(file);
            }
        }
    }

    final void B(String str, int i, long j) {
        if (M(str, i, j).exists()) {
            P(M(str, i, j));
        }
    }

    final void C(String str, int i, long j) {
        if (f(str, i, j).exists()) {
            P(f(str, i, j));
        }
    }

    final boolean a(String str) {
        return e(str) != null;
    }

    final Map<String, AssetPackLocation> b() {
        HashMap map = new HashMap();
        try {
            for (File file : L()) {
                AssetPackLocation assetPackLocationD = d(file.getName());
                if (assetPackLocationD != null) {
                    map.put(file.getName(), assetPackLocationD);
                }
            }
        } catch (IOException e) {
            a.b("Could not process directory while scanning installed packs: %s", e);
        }
        return map;
    }

    final Map<String, Long> c() {
        HashMap map = new HashMap();
        for (String str : b().keySet()) {
            map.put(str, Long.valueOf(u(str)));
        }
        return map;
    }

    @Nullable
    final AssetPackLocation d(String str) throws IOException {
        String strE = e(str);
        if (strE == null) {
            return null;
        }
        File file = new File(strE, "assets");
        if (file.isDirectory()) {
            return AssetPackLocation.b(strE, file.getCanonicalPath());
        }
        a.b("Failed to find assets directory: %s", file);
        return null;
    }

    @Nullable
    final String e(String str) throws IOException {
        int length;
        File file = new File(O(), str);
        if (!file.exists()) {
            a.a("Pack not found with pack name: %s", str);
            return null;
        }
        File file2 = new File(file, String.valueOf(this.e.a()));
        if (!file2.exists()) {
            a.a("Pack not found with pack name: %s app version: %s", str, Integer.valueOf(this.e.a()));
            return null;
        }
        File[] fileArrListFiles = file2.listFiles();
        if (fileArrListFiles == null || (length = fileArrListFiles.length) == 0) {
            a.a("No pack version found for pack name: %s app version: %s", str, Integer.valueOf(this.e.a()));
            return null;
        }
        if (length <= 1) {
            return fileArrListFiles[0].getCanonicalPath();
        }
        a.b("Multiple pack versions found for pack name: %s app version: %s", str, Integer.valueOf(this.e.a()));
        return null;
    }

    final File f(String str, int i, long j) {
        return new File(D(str, i), String.valueOf(j));
    }

    final File g(String str, int i, long j) {
        return new File(f(str, i, j), "_metadata");
    }

    final File h(String str, int i, long j, String str2) {
        return new File(new File(new File(M(str, i, j), "_slices"), "_unverified"), str2);
    }

    final File i(String str, int i, long j, String str2) {
        return new File(new File(new File(M(str, i, j), "_slices"), "_verified"), str2);
    }

    final File j(String str, int i, long j) {
        return new File(M(str, i, j), "_packs");
    }

    final int k(String str, int i, long j) throws IOException {
        File fileF = F(str, i, j);
        if (!fileF.exists()) {
            return 0;
        }
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(fileF);
        try {
            properties.load(fileInputStream);
            fileInputStream.close();
            if (properties.getProperty("numberOfMerges") == null) {
                throw new bk("Merge checkpoint file corrupt.");
            }
            try {
                return Integer.parseInt(properties.getProperty("numberOfMerges"));
            } catch (NumberFormatException e) {
                throw new bk("Merge checkpoint file corrupt.", e);
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                com.google.android.play.core.internal.bz.a(th, th2);
            }
            throw th;
        }
    }

    final void l(String str, int i, long j, int i2) throws IOException {
        File fileF = F(str, i, j);
        Properties properties = new Properties();
        properties.put("numberOfMerges", String.valueOf(i2));
        fileF.getParentFile().mkdirs();
        fileF.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(fileF);
        properties.store(fileOutputStream, (String) null);
        fileOutputStream.close();
    }

    final File m(String str, int i, long j, String str2) {
        return new File(o(str, i, j, str2), "checkpoint.dat");
    }

    final File n(String str, int i, long j, String str2) {
        return new File(o(str, i, j, str2), "checkpoint_ext.dat");
    }

    final File o(String str, int i, long j, String str2) {
        return new File(p(str, i, j), str2);
    }

    final File p(String str, int i, long j) {
        return new File(new File(M(str, i, j), "_slices"), "_metadata");
    }

    final boolean q(String str) {
        if (E(str).exists()) {
            return P(E(str));
        }
        return true;
    }

    final void r(String str, int i, long j) {
        File fileE = E(str);
        if (fileE.exists()) {
            for (File file : fileE.listFiles()) {
                if (!file.getName().equals(String.valueOf(i)) && !file.getName().equals("stale.tmp")) {
                    P(file);
                } else if (file.getName().equals(String.valueOf(i))) {
                    for (File file2 : file.listFiles()) {
                        if (!file2.getName().equals(String.valueOf(j))) {
                            P(file2);
                        }
                    }
                }
            }
        }
    }

    final void s() {
        for (File file : L()) {
            if (file.listFiles() != null) {
                G(file);
                long J = J(file);
                if (this.e.a() != J) {
                    try {
                        new File(new File(file, String.valueOf(J)), "stale.tmp").createNewFile();
                    } catch (IOException unused) {
                        a.b("Could not write staleness marker.", new Object[0]);
                    }
                }
                for (File file2 : file.listFiles()) {
                    G(file2);
                }
            }
        }
    }

    final int t(String str) {
        return (int) H(E(str));
    }

    final long u(String str) {
        return H(D(str, t(str)));
    }

    final void v() {
        for (File file : L()) {
            if (file.listFiles() != null) {
                for (File file2 : file.listFiles()) {
                    File file3 = new File(file2, "stale.tmp");
                    if (file3.exists() && System.currentTimeMillis() - file3.lastModified() > c) {
                        P(file2);
                    }
                }
            }
        }
    }

    final void w() {
        if (N().exists()) {
            for (File file : N().listFiles()) {
                if (System.currentTimeMillis() - file.lastModified() > b) {
                    P(file);
                } else {
                    G(file);
                }
            }
        }
    }

    final void x() {
        P(O());
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0070  */
    @android.support.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final com.google.android.play.core.assetpacks.AssetLocation y(java.lang.String r9, java.lang.String r10) {
        /*
            Method dump skipped, instruction units count: 203
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.assetpacks.au.y(java.lang.String, java.lang.String):com.google.android.play.core.assetpacks.AssetLocation");
    }

    @Nullable
    final AssetLocation z(String str, String str2, AssetPackLocation assetPackLocation) {
        File file = new File(assetPackLocation.assetsPath(), str2);
        if (file.exists()) {
            return AssetLocation.a(file.getPath(), 0L, file.length());
        }
        a.a("The asset %s is not present in Asset Pack %s. Searched in folder: %s", str2, str, assetPackLocation.assetsPath());
        return null;
    }
}
