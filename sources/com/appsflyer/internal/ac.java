package com.appsflyer.internal;

import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerLibCore;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.BackgroundHttpTask;
import com.appsflyer.BackgroundHttpTask.AnonymousClass1;
import com.appsflyer.ServerConfigHandler;
import com.appsflyer.ServerParameters;
import com.appsflyer.internal.model.event.BackgroundEvent;
import com.appsflyer.internal.model.event.ProxyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class ac {

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    private static String f207 = "https://%smonitorsdk.%s/remote-debug?app_id=";

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    private static ac f208;

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    private JSONObject f211;

    /* JADX INFO: renamed from: Ӏ, reason: contains not printable characters */
    private int f216;

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    private boolean f213 = true;

    /* JADX INFO: renamed from: і, reason: contains not printable characters */
    private final List<String> f215 = new ArrayList();

    /* JADX INFO: renamed from: ɹ, reason: contains not printable characters */
    private String f212 = "-1";

    /* JADX INFO: renamed from: І, reason: contains not printable characters */
    private boolean f214 = AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.DPM, false);

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    public boolean f209 = true ^ this.f214;

    /* JADX INFO: renamed from: Ɩ, reason: contains not printable characters */
    private boolean f210 = false;

    private ac() {
        this.f216 = 0;
        this.f216 = 0;
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    public static ac m168() {
        if (f208 == null) {
            f208 = new ac();
        }
        return f208;
    }

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    public final synchronized void m174(String str) {
        this.f212 = str;
    }

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    public final synchronized void m176() {
        this.f210 = true;
        m175("r_debugging_on", new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.ENGLISH).format(Long.valueOf(System.currentTimeMillis())), new String[0]);
    }

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    public final synchronized void m172() {
        m175("r_debugging_off", new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.ENGLISH).format(Long.valueOf(System.currentTimeMillis())), new String[0]);
        this.f210 = false;
        this.f213 = false;
    }

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    public final synchronized void m177() {
        this.f211 = null;
        f208 = null;
    }

    /* JADX INFO: renamed from: Ɩ, reason: contains not printable characters */
    private boolean m163() {
        if (this.f209) {
            return this.f213 || this.f210;
        }
        return false;
    }

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    private synchronized void m167(String str, String str2, String str3, String str4, String str5, String str6) {
        try {
            this.f211.put(ServerParameters.BRAND, str);
            this.f211.put(ServerParameters.MODEL, str2);
            this.f211.put(ServerParameters.PLATFORM, "Android");
            this.f211.put("platform_version", str3);
            if (str4 != null && str4.length() > 0) {
                this.f211.put(ServerParameters.ADVERTISING_ID_PARAM, str4);
            }
            if (str5 != null && str5.length() > 0) {
                this.f211.put(ServerParameters.IMEI, str5);
            }
            if (str6 != null && str6.length() > 0) {
                this.f211.put(ServerParameters.ANDROID_ID, str6);
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    private synchronized void m165(String str, String str2, String str3, String str4) {
        try {
            this.f211.put(ServerParameters.SDK_DATA_SDK_VERSION, str);
            if (str2 != null && str2.length() > 0) {
                this.f211.put(ServerParameters.DEV_KEY, str2);
            }
            if (str3 != null && str3.length() > 0) {
                this.f211.put("originalAppsFlyerId", str3);
            }
            if (str4 != null && str4.length() > 0) {
                this.f211.put(ServerParameters.AF_USER_ID, str4);
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    private synchronized void m162(String str, String str2, String str3, String str4) {
        if (str != null) {
            try {
                if (str.length() > 0) {
                    this.f211.put(ServerParameters.APP_ID, str);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (str2 != null && str2.length() > 0) {
            this.f211.put("app_version", str2);
        }
        if (str3 != null && str3.length() > 0) {
            this.f211.put("channel", str3);
        }
        if (str4 != null && str4.length() > 0) {
            this.f211.put("preInstall", str4);
        }
    }

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    public final synchronized void m175(String str, String str2, String... strArr) {
        String string;
        if (!m163() || this.f216 >= 98304) {
            return;
        }
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            String strJoin = TextUtils.join(", ", strArr);
            if (str != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(jCurrentTimeMillis);
                sb.append(" ");
                sb.append(Thread.currentThread().getId());
                sb.append(" _/AppsFlyer_6.1.0 [");
                sb.append(str);
                sb.append("] ");
                sb.append(str2);
                sb.append(" ");
                sb.append(strJoin);
                string = sb.toString();
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(jCurrentTimeMillis);
                sb2.append(" ");
                sb2.append(Thread.currentThread().getId());
                sb2.append(" ");
                sb2.append(str2);
                sb2.append("/AppsFlyer_6.1.0 ");
                sb2.append(strJoin);
                string = sb2.toString();
            }
            this.f215.add(string);
            this.f216 += string.length() << 1;
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: і, reason: contains not printable characters */
    private synchronized String m170() {
        String string;
        string = null;
        try {
            this.f211.put("data", new JSONArray((Collection) this.f215));
            string = this.f211.toString();
            m171();
        } catch (JSONException unused) {
        }
        return string;
    }

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    private synchronized void m166(String str, PackageManager packageManager) {
        AppsFlyerProperties appsFlyerProperties = AppsFlyerProperties.getInstance();
        AppsFlyerLibCore appsFlyerLibCore = AppsFlyerLibCore.getInstance();
        String string = appsFlyerProperties.getString("remote_debug_static_data");
        if (string != null) {
            try {
                this.f211 = new JSONObject(string);
            } catch (Throwable unused) {
            }
        } else {
            this.f211 = new JSONObject();
            m167(Build.BRAND, Build.MODEL, Build.VERSION.RELEASE, appsFlyerProperties.getString(ServerParameters.ADVERTISING_ID_PARAM), appsFlyerLibCore.f88, appsFlyerLibCore.f95);
            StringBuilder sb = new StringBuilder("6.1.0.");
            sb.append(AppsFlyerLibCore.f83);
            m165(sb.toString(), appsFlyerProperties.getString(AppsFlyerProperties.AF_KEY), appsFlyerProperties.getString("KSAppsFlyerId"), appsFlyerProperties.getString(ServerParameters.AF_USER_ID));
            try {
                int i = packageManager.getPackageInfo(str, 0).versionCode;
                m162(str, String.valueOf(i), appsFlyerProperties.getString("channel"), appsFlyerProperties.getString("preInstallName"));
            } catch (Throwable unused2) {
            }
            appsFlyerProperties.set("remote_debug_static_data", this.f211.toString());
        }
        try {
            this.f211.put(ServerParameters.LAUNCH_COUNTER, this.f212);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    public static String[] m169(String str, StackTraceElement[] stackTraceElementArr) {
        if (stackTraceElementArr == null) {
            return new String[]{str};
        }
        String[] strArr = new String[stackTraceElementArr.length + 1];
        strArr[0] = str;
        for (int i = 1; i < stackTraceElementArr.length; i++) {
            strArr[i] = stackTraceElementArr[i].toString();
        }
        return strArr;
    }

    /* JADX INFO: renamed from: Ӏ, reason: contains not printable characters */
    private synchronized void m171() {
        this.f215.clear();
        this.f216 = 0;
    }

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    public final synchronized void m173() {
        this.f213 = false;
        m171();
    }

    /* JADX INFO: renamed from: І, reason: contains not printable characters */
    public final boolean m178() {
        return this.f210;
    }

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    public static void m164(String str, PackageManager packageManager) {
        try {
            if (f208 == null) {
                f208 = new ac();
            }
            f208.m166(str, packageManager);
            if (f208 == null) {
                f208 = new ac();
            }
            BackgroundEvent backgroundEventTrackingStopped = new ProxyEvent().body(f208.m170()).trackingStopped(AppsFlyerLib.getInstance().isStopped());
            StringBuilder sb = new StringBuilder();
            sb.append(ServerConfigHandler.getUrl(f207));
            sb.append(str);
            new Thread(new BackgroundHttpTask((BackgroundEvent) backgroundEventTrackingStopped.urlString(sb.toString())).new AnonymousClass1()).start();
        } catch (Throwable th) {
            AFLogger.afErrorLog(th);
        }
    }
}
