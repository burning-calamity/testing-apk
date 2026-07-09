package com.appsflyer.internal.model.event;

import android.content.Context;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public abstract class Purchase extends BackgroundEvent {
    Purchase() {
        this(null, null, null);
    }

    Purchase(@Nullable String str, @Nullable Boolean bool, @Nullable Context context) {
        super(str, Boolean.FALSE, null, bool, context);
    }
}
