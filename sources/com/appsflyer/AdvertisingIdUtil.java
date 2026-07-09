package com.appsflyer;

import android.content.ContentResolver;
import android.os.Build;
import android.provider.Settings;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class AdvertisingIdUtil {
    public static final String AMAZON_MANUFACTURER = "Amazon";

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    static String f78;

    @Nullable
    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    static AdvertisingIdObject m46(ContentResolver contentResolver) {
        String string;
        if (contentResolver == null || AppsFlyerProperties.getInstance().getString(ServerParameters.AMAZON_AID) != null || !AMAZON_MANUFACTURER.equals(Build.MANUFACTURER)) {
            return null;
        }
        int i = Settings.Secure.getInt(contentResolver, "limit_ad_tracking", 2);
        if (i == 0) {
            return new AdvertisingIdObject(Settings.Secure.getString(contentResolver, "advertising_id"), Boolean.FALSE);
        }
        if (i == 2) {
            return null;
        }
        try {
            string = Settings.Secure.getString(contentResolver, "advertising_id");
        } catch (Throwable th) {
            AFLogger.afErrorLog("Couldn't fetch Amazon Advertising ID (Ad-Tracking is limited!)", th);
            string = "";
        }
        return new AdvertisingIdObject(string, Boolean.TRUE);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.appsflyer.AdvertisingIdObject getOaid(android.content.Context r5) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 0
            r2 = 21
            if (r0 >= r2) goto L8
            return r1
        L8:
            com.appsflyer.AppsFlyerProperties r0 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r2 = com.appsflyer.AdvertisingIdUtil.f78
            r3 = 1
            if (r2 == 0) goto L13
            r2 = 1
            goto L14
        L13:
            r2 = 0
        L14:
            if (r2 == 0) goto L1b
            java.lang.String r5 = com.appsflyer.AdvertisingIdUtil.f78
            r0 = r5
        L19:
            r5 = r1
            goto L47
        L1b:
            java.lang.String r4 = "collectOAID"
            boolean r3 = r0.getBoolean(r4, r3)
            if (r3 == 0) goto L45
            com.appsflyer.oaid.OaidClient r3 = new com.appsflyer.oaid.OaidClient     // Catch: java.lang.Throwable -> L3e
            r3.<init>(r5)     // Catch: java.lang.Throwable -> L3e
            boolean r5 = r0.isEnableLog()     // Catch: java.lang.Throwable -> L3e
            r3.setLogging(r5)     // Catch: java.lang.Throwable -> L3e
            com.appsflyer.oaid.OaidClient$Info r5 = r3.fetch()     // Catch: java.lang.Throwable -> L3e
            if (r5 == 0) goto L45
            java.lang.String r0 = r5.getId()     // Catch: java.lang.Throwable -> L3e
            java.lang.Boolean r5 = r5.getLat()     // Catch: java.lang.Throwable -> L3f
            goto L47
        L3e:
            r0 = r1
        L3f:
            java.lang.String r5 = "No OAID library"
            com.appsflyer.AFLogger.afDebugLog(r5)
            goto L19
        L45:
            r5 = r1
            r0 = r5
        L47:
            if (r0 == 0) goto L51
            com.appsflyer.AdvertisingIdObject r1 = new com.appsflyer.AdvertisingIdObject
            r1.<init>(r0, r5)
            r1.setManual(r2)
        L51:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.AdvertisingIdUtil.getOaid(android.content.Context):com.appsflyer.AdvertisingIdObject");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0042 A[Catch: Throwable -> 0x004a, TRY_LEAVE, TryCatch #0 {Throwable -> 0x004a, blocks: (B:13:0x003c, B:15:0x0042), top: B:50:0x003c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.appsflyer.AdvertisingIdObject getGaid(android.content.Context r11, java.util.Map<java.lang.String, java.lang.Object> r12) {
        /*
            Method dump skipped, instruction units count: 366
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.AdvertisingIdUtil.getGaid(android.content.Context, java.util.Map):com.appsflyer.AdvertisingIdObject");
    }
}
