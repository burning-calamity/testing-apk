package com.appsflyer.internal.referrer;

import android.content.Context;
import com.appsflyer.AppsFlyerLibCore;
import com.appsflyer.internal.ContentFetcher;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class HuaweiReferrer extends Referrer {
    public void start(Context context, Runnable runnable) {
        ContentFetcher<Map<String, Object>> contentFetcher = new ContentFetcher<Map<String, Object>>(context, "com.huawei.appmarket.commondata", "ffe391e0ea186d0734ed601e4e70e3224b7309d48e2075bac46d8c667eae7212") { // from class: com.appsflyer.internal.referrer.HuaweiReferrer.1
            /* JADX INFO: Access modifiers changed from: private */
            /* JADX WARN: Removed duplicated region for block: B:19:0x011f A[PHI: r2
  0x011f: PHI (r2v10 android.database.Cursor) = (r2v9 android.database.Cursor), (r2v11 android.database.Cursor) binds: [B:18:0x011d, B:12:0x010d] A[DONT_GENERATE, DONT_INLINE]] */
            @Override // com.appsflyer.internal.ContentFetcher
            /* JADX INFO: renamed from: ı, reason: contains not printable characters and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public java.util.Map<java.lang.String, java.lang.Object> query() {
                /*
                    Method dump skipped, instruction units count: 306
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.referrer.HuaweiReferrer.AnonymousClass1.query():java.util.Map");
            }
        };
        if (AppsFlyerLibCore.getInstance().getLaunchCounter(AppsFlyerLibCore.getSharedPreferences(context), false) >= 2 || !contentFetcher.valid()) {
            return;
        }
        contentFetcher.start();
        start(runnable);
    }
}
