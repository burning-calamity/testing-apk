package com.google.android.play.core.assetpacks;

import android.app.PendingIntent;
import android.support.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
final class be {

    @Nullable
    private PendingIntent a;

    be() {
    }

    final void a(@Nullable PendingIntent pendingIntent) {
        this.a = pendingIntent;
    }

    @Nullable
    final PendingIntent b() {
        return this.a;
    }
}
