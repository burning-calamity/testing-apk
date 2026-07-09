package com.google.android.play.core.review;

import com.google.android.play.core.tasks.j;

/* JADX INFO: loaded from: classes.dex */
final class e extends j {
    e() {
        super("Play Store app is either not installed or not the official version");
    }

    @Override // com.google.android.play.core.tasks.j
    public final int getErrorCode() {
        return -1;
    }
}
