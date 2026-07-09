package com.google.android.play.core.assetpacks;

/* JADX INFO: loaded from: classes.dex */
final class bk extends RuntimeException {
    final int a;

    bk(String str) {
        super(str);
        this.a = -1;
    }

    bk(String str, int i) {
        super(str);
        this.a = i;
    }

    bk(String str, Exception exc) {
        super(str, exc);
        this.a = -1;
    }

    bk(String str, Exception exc, int i) {
        super(str, exc);
        this.a = i;
    }
}
