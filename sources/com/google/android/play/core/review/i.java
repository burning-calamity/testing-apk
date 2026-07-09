package com.google.android.play.core.review;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import com.google.android.play.core.internal.ac;
import com.google.android.play.core.internal.ag;
import com.google.android.play.core.internal.aq;
import com.google.android.play.core.internal.bp;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;

/* JADX INFO: loaded from: classes.dex */
public final class i {
    private static final ag b = new ag("ReviewService");

    @Nullable
    aq<ac> a;
    private final String c;

    public i(Context context) {
        this.c = context.getPackageName();
        if (bp.a(context)) {
            this.a = new aq<>(context, b, "com.google.android.finsky.inappreviewservice.InAppReviewService", new Intent("com.google.android.finsky.BIND_IN_APP_REVIEW_SERVICE").setPackage("com.android.vending"), d.a);
        }
    }

    public final Task<ReviewInfo> a() {
        b.d("requestInAppReview (%s)", this.c);
        if (this.a == null) {
            b.b("Play Store app is either not installed or not the official version", new Object[0]);
            return Tasks.b(new e());
        }
        com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        this.a.a(new f(this, iVar, iVar));
        return iVar.c();
    }
}
