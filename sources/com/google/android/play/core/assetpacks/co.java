package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
final class co {
    private static final com.google.android.play.core.internal.ag a = new com.google.android.play.core.internal.ag("MergeSliceTaskHandler");
    private final au b;

    co(au auVar) {
        this.b = auVar;
    }

    private static void b(File file, File file2) {
        if (!file.isDirectory()) {
            if (file2.exists()) {
                String strValueOf = String.valueOf(file2);
                StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 51);
                sb.append("File clashing with existing file from other slice: ");
                sb.append(strValueOf);
                throw new bk(sb.toString());
            }
            if (file.renameTo(file2)) {
                return;
            }
            String strValueOf2 = String.valueOf(file);
            StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf2).length() + 21);
            sb2.append("Unable to move file: ");
            sb2.append(strValueOf2);
            throw new bk(sb2.toString());
        }
        file2.mkdirs();
        for (File file3 : file.listFiles()) {
            b(file3, new File(file2, file3.getName()));
        }
        if (file.delete()) {
            return;
        }
        String strValueOf3 = String.valueOf(file);
        StringBuilder sb3 = new StringBuilder(String.valueOf(strValueOf3).length() + 28);
        sb3.append("Unable to delete directory: ");
        sb3.append(strValueOf3);
        throw new bk(sb3.toString());
    }

    public final void a(cn cnVar) {
        File fileI = this.b.i(cnVar.k, cnVar.a, cnVar.b, cnVar.c);
        if (!fileI.exists()) {
            throw new bk(String.format("Cannot find verified files for slice %s.", cnVar.c), cnVar.j);
        }
        File fileJ = this.b.j(cnVar.k, cnVar.a, cnVar.b);
        if (!fileJ.exists()) {
            fileJ.mkdirs();
        }
        b(fileI, fileJ);
        try {
            this.b.l(cnVar.k, cnVar.a, cnVar.b, this.b.k(cnVar.k, cnVar.a, cnVar.b) + 1);
        } catch (IOException e) {
            a.b("Writing merge checkpoint failed with %s.", e.getMessage());
            throw new bk("Writing merge checkpoint failed.", e, cnVar.j);
        }
    }
}
