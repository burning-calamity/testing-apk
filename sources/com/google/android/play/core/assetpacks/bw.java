package com.google.android.play.core.assetpacks;

import com.google.android.play.core.assetpacks.model.AssetPackStatus;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class bw {
    final String a;
    final long b;

    @AssetPackStatus
    int c;
    final long d;
    final List<by> e;

    bw(String str, long j, @AssetPackStatus int i, long j2, List<by> list) {
        this.a = str;
        this.b = j;
        this.c = i;
        this.d = j2;
        this.e = list;
    }
}
