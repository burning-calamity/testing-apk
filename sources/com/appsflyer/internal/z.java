package com.appsflyer.internal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.appsflyer.AFLogger;
import com.appsflyer.AndroidUtils;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerLibCore;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.FirebaseMessagingServiceListener;
import com.appsflyer.internal.a;

/* JADX INFO: loaded from: classes.dex */
public final class z {
    z() {
    }

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    public static boolean m226(Context context) {
        if (AppsFlyerLib.getInstance().isStopped()) {
            return false;
        }
        try {
            Class.forName("com.google.firebase.messaging.FirebaseMessagingService");
            if (AndroidUtils.m48(context, new Intent("com.google.firebase.MESSAGING_EVENT", null, context, FirebaseMessagingServiceListener.class))) {
                return true;
            }
            AFLogger.afWarnLog("Cannot verify existence of our InstanceID Listener Service in the manifest. Please refer to documentation.");
        } catch (ClassNotFoundException unused) {
        } catch (Throwable th) {
            AFLogger.afErrorLog("An error occurred while trying to verify manifest declarations: ", th);
        }
        return false;
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    public static void m225(Context context, String str) {
        if (str != null) {
            AFLogger.afInfoLog("updateServerUninstallToken called with: ".concat(String.valueOf(str)));
            a.e.C0004e c0004eM153 = a.e.C0004e.m153(AppsFlyerProperties.getInstance().getString("afUninstallToken"));
            SharedPreferences sharedPreferences = AppsFlyerLibCore.getSharedPreferences(context);
            if (sharedPreferences.getBoolean("sentRegisterRequestToAF", false) && c0004eM153.f202 != null && c0004eM153.f202.equals(str)) {
                return;
            }
            AppsFlyerProperties.getInstance().set("afUninstallToken", str);
            if (AppsFlyerLibCore.m65(sharedPreferences)) {
                AppsFlyerLibCore.getInstance().m111(context, str);
            }
        }
    }
}
