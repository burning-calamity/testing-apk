package com.appsflyer.internal;

import androidx.annotation.VisibleForTesting;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerProperties;

/* JADX INFO: loaded from: classes.dex */
public final class ab {

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    @VisibleForTesting
    private static String f205;

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    @VisibleForTesting
    private static String f206;

    ab() {
    }

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    public static void m160(String str) {
        f205 = str;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (i == 0 || i == str.length() - 1) {
                sb.append(str.charAt(i));
            } else {
                sb.append("*");
            }
        }
        f206 = sb.toString();
    }

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    public static void m161(String str) {
        if (f205 == null) {
            m160(AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY));
        }
        String str2 = f205;
        if (str2 != null) {
            AFLogger.afInfoLog(str.replace(str2, f206));
        }
    }
}
