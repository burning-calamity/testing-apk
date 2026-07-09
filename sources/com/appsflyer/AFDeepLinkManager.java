package com.appsflyer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import com.appsflyer.deeplink.DeepLinkListener;
import com.appsflyer.internal.referrer.Payload;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class AFDeepLinkManager {
    public static final String DDL_SENT = "ddl_sent";

    @VisibleForTesting
    public static AFDeepLinkManager instance;

    @VisibleForTesting
    public static Intent trampolineIntent;

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    static String[] f0;

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    public static String[] f1;

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    static final int f2 = (int) TimeUnit.SECONDS.toMillis(2);

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    static volatile boolean f3;
    public String contains;
    public DeepLinkListener deepLinkListener;
    public Map<String, String> parameters;

    private AFDeepLinkManager() {
    }

    public static AFDeepLinkManager getInstance() {
        if (instance == null) {
            instance = new AFDeepLinkManager();
        }
        return instance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    public static boolean m5(String str) {
        if (f0 == null || str.contains("af_tranid=")) {
            return false;
        }
        StringBuilder sb = new StringBuilder("Validate ESP URLs :");
        sb.append(Arrays.asList(f0));
        AFLogger.afRDLog(sb.toString());
        try {
            return Arrays.asList(f0).contains(new URL(str).getHost());
        } catch (MalformedURLException unused) {
            return false;
        }
    }

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    static void m4(final Context context, final Map<String, Object> map, final Uri uri) {
        if (m5(uri.toString())) {
            f3 = true;
            AFExecutor aFExecutor = AFExecutor.getInstance();
            if (aFExecutor.f23 == null) {
                aFExecutor.f23 = Executors.newSingleThreadScheduledExecutor(aFExecutor.f25);
            }
            aFExecutor.f23.execute(new Runnable() { // from class: com.appsflyer.AFDeepLinkManager.1
                @Override // java.lang.Runnable
                public final void run() {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    String string = uri.toString();
                    ArrayList arrayList = new ArrayList();
                    Integer num = null;
                    String str = null;
                    String str2 = string;
                    int i = 0;
                    while (i < 5) {
                        Map<String, Object> mapM8 = m8(Uri.parse(str2));
                        String str3 = (String) mapM8.get("res");
                        Integer num2 = (Integer) mapM8.get("status");
                        String str4 = (String) mapM8.get("error");
                        if (str3 == null || !AFDeepLinkManager.m5(str3)) {
                            str = str4;
                            str2 = str3;
                            num = num2;
                            break;
                        } else {
                            if (i < 4) {
                                arrayList.add(str3);
                            }
                            i++;
                            str = str4;
                            str2 = str3;
                            num = num2;
                        }
                    }
                    HashMap map2 = new HashMap();
                    map2.put("res", str2 != null ? str2 : "");
                    map2.put("status", Integer.valueOf(num != null ? num.intValue() : -1));
                    if (str != null) {
                        map2.put("error", str);
                    }
                    if (!arrayList.isEmpty()) {
                        map2.put("redirects", arrayList);
                    }
                    map2.put(Payload.LATENCY, Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                    synchronized (map) {
                        map.put(ServerParameters.DEEP_LINK_RESOLVED, map2);
                        map.put(ServerParameters.DEEP_LINK, uri.toString());
                    }
                    AppsFlyerLibCore.getInstance().handleDeepLinkCallback(context, map, str2 != null ? Uri.parse(str2) : uri);
                    AFDeepLinkManager.f3 = false;
                }

                /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
                private static Map<String, Object> m8(Uri uri2) {
                    HashMap map2 = new HashMap();
                    try {
                        StringBuilder sb = new StringBuilder("ESP deeplink resolving is started: ");
                        sb.append(uri2.toString());
                        AFLogger.afDebugLog(sb.toString());
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(uri2.toString()).openConnection();
                        httpURLConnection.setInstanceFollowRedirects(false);
                        httpURLConnection.setReadTimeout(AFDeepLinkManager.f2);
                        httpURLConnection.setConnectTimeout(AFDeepLinkManager.f2);
                        httpURLConnection.setRequestProperty("User-agent", "Dalvik/2.1.0 (Linux; U; Android 6.0.1; Nexus 5 Build/M4B30Z)");
                        httpURLConnection.setRequestProperty("af-esp", "6.1.0");
                        int responseCode = httpURLConnection.getResponseCode();
                        map2.put("status", Integer.valueOf(responseCode));
                        if (300 <= responseCode && responseCode <= 305) {
                            map2.put("res", httpURLConnection.getHeaderField("Location"));
                        }
                        httpURLConnection.disconnect();
                        AFLogger.afDebugLog("ESP deeplink resolving is finished");
                    } catch (Throwable th) {
                        map2.put("error", th.getLocalizedMessage());
                        AFLogger.afErrorLog(th.getMessage(), th);
                    }
                    return map2;
                }
            });
        } else {
            AppsFlyerLibCore.getInstance().handleDeepLinkCallback(context, map, uri);
        }
        trampolineIntent = null;
    }

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    final boolean m7(Intent intent, Context context, Map<String, Object> map) {
        Intent intent2 = AppsFlyerLibCore.getInstance().pluginDeeplinkIntent;
        Uri data = null;
        Uri data2 = (intent == null || !"android.intent.action.VIEW".equals(intent.getAction())) ? null : intent.getData();
        Uri data3 = (intent2 == null || !"android.intent.action.VIEW".equals(intent2.getAction())) ? null : intent2.getData();
        Intent intent3 = trampolineIntent;
        if (intent3 != null && "android.intent.action.VIEW".equals(intent3.getAction())) {
            data = intent3.getData();
        }
        if (data2 != null) {
            if (!intent.hasExtra("af_consumed")) {
                intent.putExtra("af_consumed", System.currentTimeMillis());
                m4(context, map, data2);
                return true;
            }
            StringBuilder sb = new StringBuilder("skipping re-use of previously consumed deep link: ");
            sb.append(data2.toString());
            sb.append(" w/af_consumed");
            AFLogger.afInfoLog(sb.toString());
            return false;
        }
        if (data != null) {
            if (!trampolineIntent.hasExtra("af_consumed")) {
                trampolineIntent.putExtra("af_consumed", System.currentTimeMillis());
                m4(context, map, data);
                return true;
            }
            StringBuilder sb2 = new StringBuilder("skipping re-use of previously consumed trampoline deep link: ");
            sb2.append(data.toString());
            sb2.append(" w/af_consumed");
            AFLogger.afInfoLog(sb2.toString());
            return false;
        }
        if (data3 != null) {
            if (!intent2.hasExtra("af_consumed")) {
                intent2.putExtra("af_consumed", System.currentTimeMillis());
                StringBuilder sb3 = new StringBuilder("using Plugin Intent fallback with URI: ");
                sb3.append(data3.toString());
                AFLogger.afInfoLog(sb3.toString());
                m4(context, map, data3);
                return true;
            }
            StringBuilder sb4 = new StringBuilder("skipping re-use of previously consumed plugin deep link: ");
            sb4.append(data3.toString());
            sb4.append(" w/af_consumed");
            AFLogger.afInfoLog(sb4.toString());
            return false;
        }
        AFLogger.afDebugLog("No deep link detected");
        return false;
    }

    protected void collectIntentsFromActivities(Intent intent) {
        if (((intent == null || !"android.intent.action.VIEW".equals(intent.getAction())) ? null : intent.getData()) == null || intent == trampolineIntent) {
            return;
        }
        trampolineIntent = intent;
    }
}
