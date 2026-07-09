package com.google.android.play.core.splitcompat;

import android.os.Build;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.play.core.internal.bz;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: classes.dex */
public final class k {
    public static final /* synthetic */ int a = 0;
    private static final Pattern b = Pattern.compile("lib/([^/]+)/(.*\\.so)$");
    private final c c;

    k(c cVar) throws IOException {
        this.c = cVar;
    }

    static /* synthetic */ Set d(k kVar, Set set, q qVar, ZipFile zipFile) throws IOException {
        HashSet hashSet = new HashSet();
        kVar.f(qVar, set, new g(hashSet, qVar, zipFile));
        return hashSet;
    }

    private static void e(q qVar, h hVar) throws IOException {
        ZipFile zipFile;
        String str;
        try {
            zipFile = new ZipFile(qVar.a());
            try {
                String strB = qVar.b();
                HashMap map = new HashMap();
                Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
                while (enumerationEntries.hasMoreElements()) {
                    ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                    Matcher matcher = b.matcher(zipEntryNextElement.getName());
                    if (matcher.matches()) {
                        String strGroup = matcher.group(1);
                        String strGroup2 = matcher.group(2);
                        Log.d("SplitCompat", String.format("NativeLibraryExtractor: split '%s' has native library '%s' for ABI '%s'", strB, strGroup2, strGroup));
                        Set hashSet = (Set) map.get(strGroup);
                        if (hashSet == null) {
                            hashSet = new HashSet();
                            map.put(strGroup, hashSet);
                        }
                        hashSet.add(new j(zipEntryNextElement, strGroup2));
                    }
                }
                HashMap map2 = new HashMap();
                for (String str2 : Build.SUPPORTED_ABIS) {
                    if (map.containsKey(str2)) {
                        Log.d("SplitCompat", String.format("NativeLibraryExtractor: there are native libraries for supported ABI %s; will use this ABI", str2));
                        for (j jVar : (Set) map.get(str2)) {
                            if (map2.containsKey(jVar.a)) {
                                str = String.format("NativeLibraryExtractor: skipping library %s for ABI %s; already present for a better ABI", jVar.a, str2);
                            } else {
                                map2.put(jVar.a, jVar);
                                str = String.format("NativeLibraryExtractor: using library %s for ABI %s", jVar.a, str2);
                            }
                            Log.d("SplitCompat", str);
                        }
                    } else {
                        Log.d("SplitCompat", String.format("NativeLibraryExtractor: there are no native libraries for supported ABI %s", str2));
                    }
                }
                hVar.a(zipFile, new HashSet(map2.values()));
                zipFile.close();
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void f(q qVar, Set<j> set, i iVar) throws IOException {
        for (j jVar : set) {
            File fileE = this.c.e(qVar.b(), jVar.a);
            boolean z = false;
            if (fileE.exists() && fileE.length() == jVar.b.getSize()) {
                z = true;
            }
            iVar.a(jVar, fileE, z);
        }
    }

    final Set<File> a() throws IOException {
        Log.d("SplitCompat", "NativeLibraryExtractor: synchronizing native libraries");
        Set<q> setI = this.c.i();
        for (String str : this.c.j()) {
            Iterator<q> it = setI.iterator();
            while (true) {
                if (!it.hasNext()) {
                    Log.i("SplitCompat", String.format("NativeLibraryExtractor: extracted split '%s' has no corresponding split; deleting", str));
                    this.c.k(str);
                    break;
                }
                if (it.next().b().equals(str)) {
                    break;
                }
            }
        }
        HashSet hashSet = new HashSet();
        for (q qVar : setI) {
            HashSet hashSet2 = new HashSet();
            e(qVar, new f(this, hashSet2, qVar));
            for (File file : this.c.m(qVar.b())) {
                if (!hashSet2.contains(file)) {
                    Log.i("SplitCompat", String.format("NativeLibraryExtractor: file '%s' found in split '%s' that is not in the split file '%s'; removing", file.getAbsolutePath(), qVar.b(), qVar.a().getAbsolutePath()));
                    this.c.l(file);
                }
            }
            hashSet.addAll(hashSet2);
        }
        return hashSet;
    }

    @Nullable
    final Set<File> b(q qVar) throws IOException {
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        HashSet hashSet = new HashSet();
        e(qVar, new e(this, qVar, hashSet, atomicBoolean));
        if (atomicBoolean.get()) {
            return hashSet;
        }
        return null;
    }
}
