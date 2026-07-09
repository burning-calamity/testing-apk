package com.appsflyer;

import com.appsflyer.internal.a;
import com.appsflyer.internal.z;
import com.google.firebase.messaging.FirebaseMessagingService;

/* JADX INFO: loaded from: classes.dex */
public class FirebaseMessagingServiceListener extends FirebaseMessagingService {
    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(String str) {
        super.onNewToken(str);
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (str != null) {
            AFLogger.afInfoLog("Firebase Refreshed Token = ".concat(String.valueOf(str)));
            a.e.C0004e c0004eM153 = a.e.C0004e.m153(AppsFlyerProperties.getInstance().getString("afUninstallToken"));
            a.e.C0004e c0004e = new a.e.C0004e(jCurrentTimeMillis, str);
            if (c0004eM153.m159(c0004e)) {
                z.m225(getApplicationContext(), c0004e.f202);
            }
        }
    }
}
