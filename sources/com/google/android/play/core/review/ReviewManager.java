package com.google.android.play.core.review;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.google.android.play.core.tasks.Task;

/* JADX INFO: loaded from: classes.dex */
public interface ReviewManager {
    @NonNull
    Task<Void> launchReviewFlow(@NonNull Activity activity, @NonNull ReviewInfo reviewInfo);

    @NonNull
    Task<ReviewInfo> requestReviewFlow();
}
