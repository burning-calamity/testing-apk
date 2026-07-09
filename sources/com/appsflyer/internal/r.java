package com.appsflyer.internal;

import android.net.NetworkInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class r {

    public static final class b {

        /* JADX INFO: renamed from: ι, reason: contains not printable characters */
        public static final r f334 = new r();
    }

    r() {
    }

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    private static boolean m208(NetworkInfo networkInfo) {
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public static final class a {

        /* JADX INFO: renamed from: ı, reason: contains not printable characters */
        public final String f331;

        /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
        public final String f332;

        /* JADX INFO: renamed from: ι, reason: contains not printable characters */
        public final String f333;

        a(@NonNull String str, @Nullable String str2, @Nullable String str3) {
            this.f331 = str;
            this.f332 = str2;
            this.f333 = str3;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:0|2|52|3|4|(5:6|7|(2:9|(1:(2:11|(3:55|13|(1:15)(2:16|(1:18)(0)))(1:19))(1:54)))(2:20|(2:23|(1:25)(2:26|(2:28|(2:30|22)(2:31|(2:33|25)(0)))(0)))(1:22))|48|49)(0)|34|50|35|(2:39|(1:41))|48|49) */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x008f, code lost:
    
        r11 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0093, code lost:
    
        com.appsflyer.AFLogger.afErrorLog("Exception while collecting network info. ", r11);
     */
    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.appsflyer.internal.r.a m207(@androidx.annotation.NonNull android.content.Context r11) {
        /*
            java.lang.String r0 = "unknown"
            r1 = 0
            java.lang.String r2 = "connectivity"
            java.lang.Object r2 = r11.getSystemService(r2)     // Catch: java.lang.Throwable -> L91
            android.net.ConnectivityManager r2 = (android.net.ConnectivityManager) r2     // Catch: java.lang.Throwable -> L91
            java.lang.String r3 = "MOBILE"
            java.lang.String r4 = "WIFI"
            if (r2 == 0) goto L6d
            r5 = 21
            int r6 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L91
            r7 = 0
            r8 = 1
            if (r5 > r6) goto L3d
            android.net.Network[] r5 = r2.getAllNetworks()     // Catch: java.lang.Throwable -> L91
            int r6 = r5.length     // Catch: java.lang.Throwable -> L91
        L1e:
            if (r7 >= r6) goto L6d
            r9 = r5[r7]     // Catch: java.lang.Throwable -> L91
            android.net.NetworkInfo r9 = r2.getNetworkInfo(r9)     // Catch: java.lang.Throwable -> L91
            boolean r10 = m208(r9)     // Catch: java.lang.Throwable -> L91
            if (r10 == 0) goto L3a
            int r2 = r9.getType()     // Catch: java.lang.Throwable -> L91
            if (r8 != r2) goto L33
            goto L47
        L33:
            int r2 = r9.getType()     // Catch: java.lang.Throwable -> L91
            if (r2 != 0) goto L6d
            goto L53
        L3a:
            int r7 = r7 + 1
            goto L1e
        L3d:
            android.net.NetworkInfo r5 = r2.getNetworkInfo(r8)     // Catch: java.lang.Throwable -> L91
            boolean r5 = m208(r5)     // Catch: java.lang.Throwable -> L91
            if (r5 == 0) goto L49
        L47:
            r0 = r4
            goto L6d
        L49:
            android.net.NetworkInfo r5 = r2.getNetworkInfo(r7)     // Catch: java.lang.Throwable -> L91
            boolean r5 = m208(r5)     // Catch: java.lang.Throwable -> L91
            if (r5 == 0) goto L55
        L53:
            r0 = r3
            goto L6d
        L55:
            android.net.NetworkInfo r2 = r2.getActiveNetworkInfo()     // Catch: java.lang.Throwable -> L91
            boolean r5 = m208(r2)     // Catch: java.lang.Throwable -> L91
            if (r5 == 0) goto L6d
            int r5 = r2.getType()     // Catch: java.lang.Throwable -> L91
            if (r8 != r5) goto L66
            goto L47
        L66:
            int r2 = r2.getType()     // Catch: java.lang.Throwable -> L91
            if (r2 != 0) goto L6d
            goto L53
        L6d:
            java.lang.String r2 = "phone"
            java.lang.Object r11 = r11.getSystemService(r2)     // Catch: java.lang.Throwable -> L91
            android.telephony.TelephonyManager r11 = (android.telephony.TelephonyManager) r11     // Catch: java.lang.Throwable -> L91
            java.lang.String r2 = r11.getSimOperatorName()     // Catch: java.lang.Throwable -> L91
            java.lang.String r1 = r11.getNetworkOperatorName()     // Catch: java.lang.Throwable -> L8f
            if (r1 == 0) goto L85
            boolean r3 = r1.isEmpty()     // Catch: java.lang.Throwable -> L8f
            if (r3 == 0) goto L98
        L85:
            r3 = 2
            int r11 = r11.getPhoneType()     // Catch: java.lang.Throwable -> L8f
            if (r3 != r11) goto L98
            java.lang.String r1 = "CDMA"
            goto L98
        L8f:
            r11 = move-exception
            goto L93
        L91:
            r11 = move-exception
            r2 = r1
        L93:
            java.lang.String r3 = "Exception while collecting network info. "
            com.appsflyer.AFLogger.afErrorLog(r3, r11)
        L98:
            com.appsflyer.internal.r$a r11 = new com.appsflyer.internal.r$a
            r11.<init>(r0, r1, r2)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.r.m207(android.content.Context):com.appsflyer.internal.r$a");
    }
}
