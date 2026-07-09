package com.google.android.play.core.review;

import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.play.core.common.PlayCoreDialogWrapperActivity;
import com.google.android.play.core.splitcompat.p;

/* JADX INFO: loaded from: classes.dex */
public class ReviewManagerFactory {
    private ReviewManagerFactory() {
    }

    @NonNull
    public static ReviewManager create(@NonNull Context context) {
        PlayCoreDialogWrapperActivity.a(context);
        return new c(new i(p.c(context)));
    }
}
