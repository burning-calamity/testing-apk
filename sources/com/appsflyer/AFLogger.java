package com.appsflyer;

import android.util.Log;
import androidx.annotation.NonNull;
import com.appsflyer.internal.Exlytics;
import com.appsflyer.internal.ac;

/* JADX INFO: loaded from: classes.dex */
public class AFLogger {

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    private static final long f38 = System.currentTimeMillis();

    public static void afInfoLog(String str, boolean z) {
        if (m25(LogLevel.INFO)) {
            Log.i(AppsFlyerLibCore.LOG_TAG, m21(str, false));
        }
        if (z) {
            ac.m168().m175(null, "I", m21(str, true));
        }
    }

    @NonNull
    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    private static String m21(String str, boolean z) {
        if (str == null) {
            str = "null";
        }
        if (!z && LogLevel.VERBOSE.getLevel() > AppsFlyerProperties.getInstance().getInt("logLevel", LogLevel.NONE.getLevel())) {
            return str;
        }
        StringBuilder sb = new StringBuilder("(");
        sb.append(System.currentTimeMillis() - f38);
        sb.append(") [");
        sb.append(Thread.currentThread().getName());
        sb.append("] ");
        sb.append(str);
        return sb.toString();
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    private static void m24(String str, Throwable th, boolean z, boolean z2) {
        if (m25(LogLevel.ERROR)) {
            if (str == null) {
                str = th.getClass().getSimpleName();
            }
            String strM21 = m21(str, false);
            if (z2) {
                Log.e(AppsFlyerLibCore.LOG_TAG, strM21, th);
            } else if (z) {
                Log.d(AppsFlyerLibCore.LOG_TAG, strM21);
            }
        }
        ac acVarM168 = ac.m168();
        Throwable cause = th.getCause();
        acVarM168.m175("exception", th.getClass().getSimpleName(), ac.m169(cause == null ? th.getMessage() : cause.getMessage(), cause == null ? th.getStackTrace() : cause.getStackTrace()));
        Exlytics.increment();
    }

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    static void m22(String str) {
        if (m25(LogLevel.WARNING)) {
            Log.w(AppsFlyerLibCore.LOG_TAG, m21(str, false));
        }
        ac.m168().m175(null, "W", m21(str, true));
    }

    public static void afRDLog(String str) {
        if (m25(LogLevel.VERBOSE)) {
            Log.v(AppsFlyerLibCore.LOG_TAG, m21(str, false));
        }
        ac.m168().m175(null, "V", m21(str, true));
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    private static boolean m25(LogLevel logLevel) {
        return logLevel.getLevel() <= AppsFlyerProperties.getInstance().getInt("logLevel", LogLevel.NONE.getLevel());
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    static void m23(String str) {
        if (!m26()) {
            Log.d(AppsFlyerLibCore.LOG_TAG, m21(str, false));
        }
        ac.m168().m175(null, "F", str);
    }

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    private static boolean m26() {
        return AppsFlyerProperties.getInstance().isLogsDisabledCompletely();
    }

    public static void afInfoLog(String str) {
        afInfoLog(str, true);
    }

    public static void afErrorLog(String str, Throwable th) {
        m24(str, th, true, false);
    }

    public static void afErrorLog(String str, Throwable th, boolean z) {
        m24(str, th, true, z);
    }

    public static void afErrorLog(Throwable th) {
        m24(null, th, false, false);
    }

    public static void afWarnLog(String str) {
        m22(str);
    }

    public enum LogLevel {
        NONE(0),
        ERROR(1),
        WARNING(2),
        INFO(3),
        DEBUG(4),
        VERBOSE(5);


        /* JADX INFO: renamed from: ι, reason: contains not printable characters */
        private int f40;

        LogLevel(int i) {
            this.f40 = i;
        }

        public final int getLevel() {
            return this.f40;
        }
    }

    public static void afDebugLog(String str) {
        if (m25(LogLevel.DEBUG)) {
            Log.d(AppsFlyerLibCore.LOG_TAG, m21(str, false));
        }
        ac.m168().m175(null, "D", m21(str, true));
    }
}
