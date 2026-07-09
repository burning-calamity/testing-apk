package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FilenameFilter;

/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class da implements FilenameFilter {
    static final FilenameFilter a = new da();

    private da() {
    }

    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String str) {
        return db.a.matcher(str).matches();
    }
}
