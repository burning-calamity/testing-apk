package com.google.android.play.core.internal;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
final class ba implements aw {
    private final /* synthetic */ int a = 0;

    ba() {
    }

    ba(byte[] bArr) {
    }

    ba(char[] cArr) {
    }

    ba(float[] fArr) {
    }

    ba(int[] iArr) {
    }

    ba(short[] sArr) {
    }

    ba(boolean[] zArr) {
    }

    ba(byte[][] bArr) {
    }

    static void c(ClassLoader classLoader, Set<File> set) {
        if (set.isEmpty()) {
            return;
        }
        HashSet hashSet = new HashSet();
        for (File file : set) {
            String strValueOf = String.valueOf(file.getParentFile().getAbsolutePath());
            Log.d("Splitcompat", strValueOf.length() != 0 ? "Adding native library parent directory: ".concat(strValueOf) : new String("Adding native library parent directory: "));
            hashSet.add(file.getParentFile());
        }
        bg bgVarE = bh.e(e(classLoader), "nativeLibraryDirectories", File.class);
        hashSet.removeAll(Arrays.asList((File[]) bgVarE.a()));
        synchronized (com.google.android.play.core.splitinstall.k.class) {
            int size = hashSet.size();
            StringBuilder sb = new StringBuilder(30);
            sb.append("Adding directories ");
            sb.append(size);
            Log.d("Splitcompat", sb.toString());
            bgVarE.e(hashSet);
        }
    }

    static boolean d(ClassLoader classLoader, File file, File file2, boolean z, az azVar, String str, ay ayVar) {
        ArrayList<IOException> arrayList = new ArrayList<>();
        Object objE = e(classLoader);
        bg bgVarE = bh.e(objE, "dexElements", Object.class);
        List listAsList = Arrays.asList((Object[]) bgVarE.a());
        ArrayList arrayList2 = new ArrayList();
        Iterator it = listAsList.iterator();
        while (it.hasNext()) {
            arrayList2.add((File) bh.d(it.next(), str, File.class).a());
        }
        if (arrayList2.contains(file2)) {
            return true;
        }
        if (!z && !ayVar.a(objE, file2, file)) {
            String strValueOf = String.valueOf(file2.getPath());
            Log.w("SplitCompat", strValueOf.length() != 0 ? "Should be optimized ".concat(strValueOf) : new String("Should be optimized "));
            return false;
        }
        bgVarE.d(Arrays.asList(azVar.a(objE, new ArrayList<>(Collections.singleton(file2)), file, arrayList)));
        if (arrayList.isEmpty()) {
            return true;
        }
        bf bfVar = new bf("DexPathList.makeDexElement failed");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            IOException iOException = arrayList.get(i);
            Log.e("SplitCompat", "DexPathList.makeDexElement failed", iOException);
            bz.a(bfVar, iOException);
        }
        bh.e(objE, "dexElementsSuppressedExceptions", IOException.class).d(arrayList);
        throw bfVar;
    }

    static Object e(ClassLoader classLoader) {
        return bh.d(classLoader, "pathList", Object.class).a();
    }

    static az f() {
        return new bb(null);
    }

    static ay g() {
        return new be((byte[]) null);
    }

    public static void h(ClassLoader classLoader, Set<File> set, bc bcVar) {
        if (set.isEmpty()) {
            return;
        }
        HashSet hashSet = new HashSet();
        Iterator<File> it = set.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getParentFile());
        }
        Object objE = e(classLoader);
        bg bgVarD = bh.d(objE, "nativeLibraryDirectories", List.class);
        synchronized (com.google.android.play.core.splitinstall.k.class) {
            ArrayList arrayList = new ArrayList((Collection) bgVarD.a());
            hashSet.removeAll(arrayList);
            arrayList.addAll(hashSet);
            bgVarD.b(arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        Object[] objArrA = bcVar.a(objE, new ArrayList(hashSet), arrayList2);
        if (arrayList2.isEmpty()) {
            synchronized (com.google.android.play.core.splitinstall.k.class) {
                bh.e(objE, "nativeLibraryPathElements", Object.class).e(Arrays.asList(objArrA));
            }
            return;
        }
        bf bfVar = new bf("Error in makePathElements");
        int size = arrayList2.size();
        for (int i = 0; i < size; i++) {
            bz.a(bfVar, arrayList2.get(i));
        }
        throw bfVar;
    }

    static az i() {
        return new bb();
    }

    static bc j() {
        return new bd(null);
    }

    public static boolean k(ClassLoader classLoader, File file, File file2, boolean z) {
        return d(classLoader, file, file2, z, i(), "zip", g());
    }

    static void l(ClassLoader classLoader, Set<File> set) {
        h(classLoader, set, new bd());
    }

    static boolean m(ClassLoader classLoader, File file, File file2, boolean z) {
        return d(classLoader, file, file2, z, i(), "path", new be());
    }

    @Override // com.google.android.play.core.internal.aw
    public final void a(ClassLoader classLoader, Set set) {
        switch (this.a) {
            case 0:
                c(classLoader, set);
                break;
            case 1:
                c(classLoader, set);
                break;
            case 2:
            case 3:
            case 4:
                h(classLoader, set, j());
                break;
            case 5:
                l(classLoader, set);
                break;
            case 6:
                l(classLoader, set);
                break;
            default:
                l(classLoader, set);
                break;
        }
    }

    @Override // com.google.android.play.core.internal.aw
    public final boolean b(ClassLoader classLoader, File file, File file2, boolean z) {
        az azVarF;
        ay ayVarG;
        String str;
        switch (this.a) {
            case 0:
            case 1:
                azVarF = f();
                ayVarG = g();
                str = "zip";
                break;
            case 2:
                return k(classLoader, file, file2, z);
            case 3:
                return k(classLoader, file, file2, z);
            case 4:
                return k(classLoader, file, file2, z);
            case 5:
                return m(classLoader, file, file2, z);
            case 6:
                return m(classLoader, file, file2, z);
            default:
                azVarF = i();
                ayVarG = new be((char[]) null);
                str = "path";
                break;
        }
        return d(classLoader, file, file2, z, azVarF, str, ayVarG);
    }
}
