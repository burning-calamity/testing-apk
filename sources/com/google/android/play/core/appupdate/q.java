package com.google.android.play.core.appupdate;

import android.content.Context;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
final class q {
    private final Context a;

    q(Context context) {
        this.a = context;
    }

    private static long b(File file) {
        if (!file.isDirectory()) {
            return file.length();
        }
        File[] fileArrListFiles = file.listFiles();
        long jB = 0;
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                jB += b(file2);
            }
        }
        return jB;
    }

    final long a() {
        return b(new File(this.a.getFilesDir(), "assetpacks"));
    }
}
