package com.appsflyer.internal;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.appsflyer.AppsFlyerLibCore;

/* JADX INFO: loaded from: classes.dex */
public class Exlytics {
    public static final String EXCEPTION_COUNT_KEY = "exception_number";

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    @Nullable
    private static Application f190;

    public static void setContext(@NonNull Application application) {
        f190 = application;
    }

    public static void increment() {
        Application application = f190;
        if (application == null) {
            return;
        }
        AppsFlyerLibCore.getSharedPreferences(application).edit().putLong(EXCEPTION_COUNT_KEY, get() + 1).apply();
    }

    public static long get() {
        Application application = f190;
        if (application == null) {
            return -1L;
        }
        return AppsFlyerLibCore.getSharedPreferences(application).getLong(EXCEPTION_COUNT_KEY, 0L);
    }
}
