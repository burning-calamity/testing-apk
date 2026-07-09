package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
final class db {
    private static final Pattern a = Pattern.compile("[0-9]+-(NAM|LFH)\\.dat");

    static List<File> a(File file, File file2) throws IOException {
        File[] fileArr;
        ArrayList arrayList = new ArrayList();
        File[] fileArrListFiles = file2.listFiles(da.a);
        if (fileArrListFiles == null) {
            fileArr = new File[0];
        } else {
            File[] fileArr2 = new File[fileArrListFiles.length];
            for (File file3 : fileArrListFiles) {
                int i = Integer.parseInt(file3.getName().split("-")[0]);
                if (i > fileArrListFiles.length || fileArr2[i] != null) {
                    throw new bk("Metadata folder ordering corrupt.");
                }
                fileArr2[i] = file3;
            }
            fileArr = fileArr2;
        }
        for (File file4 : fileArr) {
            arrayList.add(file4);
            if (file4.getName().contains("LFH")) {
                FileInputStream fileInputStream = new FileInputStream(file4);
                try {
                    de deVarA = new bd(fileInputStream).a();
                    if (deVarA.d() == null) {
                        throw new bk("Metadata files corrupt. Could not read local file header.");
                    }
                    File file5 = new File(file, deVarA.d());
                    if (!file5.exists()) {
                        throw new bk(String.format("Missing asset file %s during slice reconstruction.", file5.getCanonicalPath()));
                    }
                    arrayList.add(file5);
                    fileInputStream.close();
                } catch (Throwable th) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        com.google.android.play.core.internal.bz.a(th, th2);
                    }
                    throw th;
                }
            }
        }
        return arrayList;
    }
}
