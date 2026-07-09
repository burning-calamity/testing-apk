package com.google.android.play.core.assetpacks;

import com.google.android.play.core.assetpacks.model.AssetPackStatus;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
final class bi extends cc {
    final int a;
    final long b;
    final String c;
    final int d;
    final int e;
    final int f;
    final long g;

    @AssetPackStatus
    final int h;
    final InputStream i;

    bi(int i, String str, int i2, long j, String str2, int i3, int i4, int i5, long j2, @AssetPackStatus int i6, InputStream inputStream) {
        super(i, str);
        this.a = i2;
        this.b = j;
        this.c = str2;
        this.d = i3;
        this.e = i4;
        this.f = i5;
        this.g = j2;
        this.h = i6;
        this.i = inputStream;
    }

    final boolean a() {
        return this.e + 1 == this.f;
    }
}
