package com.appsflyer;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.os.EnvironmentCompat;
import com.appsflyer.AFFacebookDeferredDeeplink;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.BackgroundHttpTask.AnonymousClass1;
import com.appsflyer.Foreground;
import com.appsflyer.OneLinkHttpTask;
import com.appsflyer.attribution.AppsFlyerRequestListener;
import com.appsflyer.attribution.RequestError;
import com.appsflyer.deeplink.DdlEvent;
import com.appsflyer.deeplink.DeepLinkCallbacks;
import com.appsflyer.deeplink.DeepLinkListener;
import com.appsflyer.internal.EventDataCollector;
import com.appsflyer.internal.Exlytics;
import com.appsflyer.internal.ab;
import com.appsflyer.internal.ac;
import com.appsflyer.internal.ad;
import com.appsflyer.internal.attribution.RequestErrorMessage;
import com.appsflyer.internal.model.event.AdRevenue;
import com.appsflyer.internal.model.event.Attr;
import com.appsflyer.internal.model.event.BackgroundEvent;
import com.appsflyer.internal.model.event.BackgroundReferrerLaunch;
import com.appsflyer.internal.model.event.CachedEvent;
import com.appsflyer.internal.model.event.InAppEvent;
import com.appsflyer.internal.model.event.Launch;
import com.appsflyer.internal.model.event.Stats;
import com.appsflyer.internal.model.event.UninstallTokenEvent;
import com.appsflyer.internal.n;
import com.appsflyer.internal.r;
import com.appsflyer.internal.referrer.GoogleReferrer;
import com.appsflyer.internal.referrer.HuaweiReferrer;
import com.appsflyer.internal.referrer.Payload;
import com.appsflyer.internal.referrer.Referrer;
import com.appsflyer.internal.u;
import com.appsflyer.internal.v;
import com.appsflyer.internal.w;
import com.appsflyer.internal.x;
import com.appsflyer.internal.y;
import com.appsflyer.internal.z;
import com.appsflyer.share.Constants;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.HttpURLConnection;
import java.net.NetworkInterface;
import java.net.URI;
import java.net.URL;
import java.security.KeyStoreException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AppsFlyerLibCore extends AppsFlyerLib {
    public static final String AF_PRE_INSTALL_PATH = "AF_PRE_INSTALL_PATH";
    public static final String BUILD_NUMBER = "6.1.0";
    public static String FIRST_LAUNCHES_URL = null;

    @VisibleForTesting
    public static final String INSTALL_REFERRER_PREF = "rfr";
    public static final String IS_STOP_TRACKING_USED = "is_stop_tracking_used";
    public static final String LOG_TAG = "AppsFlyer_6.1.0";
    public static final String PRE_INSTALL_SYSTEM_DEFAULT = "/data/local/tmp/pre_install.appsflyer";
    public static final String PRE_INSTALL_SYSTEM_DEFAULT_ETC = "/etc/pre_install.appsflyer";
    public static final String PRE_INSTALL_SYSTEM_RO_PROP = "ro.appsflyer.preinstall.path";

    @VisibleForTesting
    public static String REFERRER_TRACKING_URL = null;
    public static String REGISTER_URL = null;
    public static AppsFlyerConversionListener conversionDataListener = null;

    @VisibleForTesting
    public static AppsFlyerLibCore instance = null;

    /* JADX INFO: renamed from: Ɩ, reason: contains not printable characters */
    private static final String f79;

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    static AppsFlyerInAppPurchaseValidatorListener f80 = null;

    /* JADX INFO: renamed from: ɪ, reason: contains not printable characters */
    private static final List<String> f81;

    /* JADX INFO: renamed from: ɹ, reason: contains not printable characters */
    private static final String f82;

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    public static final String f83 = "64";

    /* JADX INFO: renamed from: І, reason: contains not printable characters */
    private static String f84;

    /* JADX INFO: renamed from: і, reason: contains not printable characters */
    private static String f85;

    /* JADX INFO: renamed from: ӏ, reason: contains not printable characters */
    private static String f87;

    @Nullable
    public GoogleReferrer googleReferrer;
    public String[] sharingFilter;

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    public String f88;

    /* JADX INFO: renamed from: ŀ, reason: contains not printable characters */
    private long f89;

    /* JADX INFO: renamed from: Ɨ, reason: contains not printable characters */
    private boolean f92;

    /* JADX INFO: renamed from: ƚ, reason: contains not printable characters */
    private Map<Long, String> f93;

    /* JADX INFO: renamed from: ǀ, reason: contains not printable characters */
    private Application f94;

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    public String f95;

    /* JADX INFO: renamed from: ɍ, reason: contains not printable characters */
    private boolean f97;

    /* JADX INFO: renamed from: ɔ, reason: contains not printable characters */
    private Map<String, Object> f98;

    /* JADX INFO: renamed from: ʅ, reason: contains not printable characters */
    private String f105;

    /* JADX INFO: renamed from: ʟ, reason: contains not printable characters */
    private long f106;

    /* JADX INFO: renamed from: ͻ, reason: contains not printable characters */
    private EventDataCollector f107;

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    @VisibleForTesting
    long f108;

    /* JADX INFO: renamed from: ϲ, reason: contains not printable characters */
    private Map<String, Object> f109;

    /* JADX INFO: renamed from: ϳ, reason: contains not printable characters */
    private SharedPreferences f110;

    /* JADX INFO: renamed from: Ј, reason: contains not printable characters */
    private String f111;
    public static final String SERVER_BUILD_NUMBER = "6.1.0".substring(0, "6.1.0".lastIndexOf("."));

    /* JADX INFO: renamed from: Ӏ, reason: contains not printable characters */
    private static String f86 = "https://%sstats.%s/stats";
    protected Intent pluginDeeplinkIntent = null;

    /* JADX INFO: renamed from: ȷ, reason: contains not printable characters */
    private long f96 = -1;

    /* JADX INFO: renamed from: ɾ, reason: contains not printable characters */
    private long f103 = -1;

    /* JADX INFO: renamed from: ɨ, reason: contains not printable characters */
    private long f100 = TimeUnit.SECONDS.toMillis(5);

    /* JADX INFO: renamed from: г, reason: contains not printable characters */
    private boolean f112 = false;

    /* JADX INFO: renamed from: ł, reason: contains not printable characters */
    private ScheduledExecutorService f90 = null;

    /* JADX INFO: renamed from: ɿ, reason: contains not printable characters */
    private boolean f104 = false;

    /* JADX INFO: renamed from: ſ, reason: contains not printable characters */
    private ad f91 = new ad();

    /* JADX INFO: renamed from: ɼ, reason: contains not printable characters */
    private boolean f102 = false;

    /* JADX INFO: renamed from: ɟ, reason: contains not printable characters */
    private boolean f99 = false;

    /* JADX INFO: renamed from: ɺ, reason: contains not printable characters */
    private boolean f101 = false;

    /* JADX INFO: renamed from: с, reason: contains not printable characters */
    private boolean f113 = false;
    public final HuaweiReferrer huaweiReferrer = new HuaweiReferrer();

    /* JADX INFO: renamed from: ɾ, reason: contains not printable characters */
    static /* synthetic */ ScheduledExecutorService m82(AppsFlyerLibCore appsFlyerLibCore) {
        appsFlyerLibCore.f90 = null;
        return null;
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(SERVER_BUILD_NUMBER);
        sb.append("/androidevent?buildnumber=6.1.0&app_id=");
        f82 = sb.toString();
        StringBuilder sb2 = new StringBuilder("https://%sregister.%s/api/v");
        sb2.append(f82);
        REGISTER_URL = sb2.toString();
        StringBuilder sb3 = new StringBuilder("https://%sadrevenue.%s/api/v");
        sb3.append(SERVER_BUILD_NUMBER);
        sb3.append("/android?buildnumber=6.1.0&app_id=");
        f85 = sb3.toString();
        StringBuilder sb4 = new StringBuilder();
        sb4.append(SERVER_BUILD_NUMBER);
        sb4.append("/androidevent?app_id=");
        f79 = sb4.toString();
        StringBuilder sb5 = new StringBuilder("https://%sconversions.%s/api/v");
        sb5.append(f79);
        FIRST_LAUNCHES_URL = sb5.toString();
        StringBuilder sb6 = new StringBuilder("https://%slaunches.%s/api/v");
        sb6.append(f79);
        f84 = sb6.toString();
        StringBuilder sb7 = new StringBuilder("https://%sinapps.%s/api/v");
        sb7.append(f79);
        f87 = sb7.toString();
        StringBuilder sb8 = new StringBuilder("https://%sattr.%s/api/v");
        sb8.append(f79);
        REFERRER_TRACKING_URL = sb8.toString();
        f81 = Arrays.asList("is_cache");
        f80 = null;
        conversionDataListener = null;
        instance = new AppsFlyerLibCore();
    }

    @VisibleForTesting
    public AppsFlyerLibCore() {
        AFVersionDeclaration.init();
    }

    public static AppsFlyerLibCore getInstance() {
        return instance;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void performOnAppAttribution(@NonNull Context context, @NonNull URI uri) {
        if (uri == null || uri.toString().isEmpty()) {
            StringBuilder sb = new StringBuilder("Link is \"");
            sb.append(uri);
            sb.append("\"");
            DeepLinkCallbacks.onDeepLinkingError(sb.toString());
            return;
        }
        if (context == null) {
            StringBuilder sb2 = new StringBuilder("Context is \"");
            sb2.append(context);
            sb2.append("\"");
            DeepLinkCallbacks.onDeepLinkingError(sb2.toString());
            return;
        }
        AFDeepLinkManager.getInstance();
        AFDeepLinkManager.m4(context, new HashMap(), Uri.parse(uri.toString()));
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setSharingFilter(@NonNull String... strArr) {
        if (strArr == null || Arrays.equals(this.sharingFilter, new String[]{"all"})) {
            return;
        }
        Pattern patternCompile = Pattern.compile("[\\d\\w_]{1,45}");
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (str == null || !patternCompile.matcher(str).matches()) {
                AFLogger.afWarnLog("Invalid partner name :".concat(String.valueOf(str)));
            } else {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            this.sharingFilter = null;
        } else {
            this.sharingFilter = (String[]) arrayList.toArray(new String[0]);
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setSharingFilterForAllPartners() {
        this.sharingFilter = new String[]{"all"};
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void appendParametersToDeepLinkingURL(String str, Map<String, String> map) {
        AFDeepLinkManager aFDeepLinkManager = AFDeepLinkManager.getInstance();
        aFDeepLinkManager.contains = str;
        aFDeepLinkManager.parameters = map;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void subscribeForDeepLink(@NonNull DeepLinkListener deepLinkListener) {
        AFDeepLinkManager.getInstance().deepLinkListener = deepLinkListener;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void subscribeForDeepLink(@NonNull DeepLinkListener deepLinkListener, long j) {
        DdlEvent.LISTENER_TIMEOUT = j;
        subscribeForDeepLink(deepLinkListener);
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    final void m115(Context context, Intent intent) {
        if (intent.getStringExtra("appsflyer_preinstall") != null) {
            getInstance();
            String stringExtra = intent.getStringExtra("appsflyer_preinstall");
            try {
                if (!new JSONObject(stringExtra).has(Constants.URL_MEDIA_SOURCE)) {
                    AFLogger.afWarnLog("Cannot set preinstall attribution data without a media source");
                } else {
                    AppsFlyerProperties.getInstance().set("preInstallName", stringExtra);
                }
            } catch (JSONException e2) {
                AFLogger.afErrorLog("Error parsing JSON for preinstall", e2);
            }
        }
        AFLogger.afInfoLog("****** onReceive called *******");
        AppsFlyerProperties.getInstance().setOnReceiveCalled();
        String stringExtra2 = intent.getStringExtra(Payload.RFR);
        AFLogger.afInfoLog("Play store referrer: ".concat(String.valueOf(stringExtra2)));
        if (stringExtra2 != null) {
            SharedPreferences.Editor editorEdit = getSharedPreferences(context).edit();
            editorEdit.putString(Payload.RFR, stringExtra2);
            editorEdit.apply();
            AppsFlyerProperties.getInstance().setReferrer(stringExtra2);
            if (AppsFlyerProperties.getInstance().isFirstLaunchCalled()) {
                AFLogger.afInfoLog("onReceive: isLaunchCalled");
                AFEvent aFEventContext = new BackgroundReferrerLaunch().context(context);
                if (aFEventContext.context() != null) {
                    aFEventContext.f16 = aFEventContext.context().getApplicationContext();
                }
                AFEvent aFEventWeakContext = aFEventContext.weakContext();
                aFEventWeakContext.f18 = stringExtra2;
                if (stringExtra2 == null || stringExtra2.length() <= 5 || !m104(aFEventWeakContext, getSharedPreferences(context))) {
                    return;
                }
                m101(AFExecutor.getInstance().m12(), new a(this, aFEventWeakContext, (byte) 0), 5L, TimeUnit.MILLISECONDS);
            }
        }
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    private static void m92(JSONObject jSONObject) {
        String str;
        ArrayList arrayList = new ArrayList();
        Iterator<String> itKeys = jSONObject.keys();
        while (true) {
            if (!itKeys.hasNext()) {
                break;
            }
            try {
                JSONArray jSONArray = new JSONArray((String) jSONObject.get(itKeys.next()));
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(Long.valueOf(jSONArray.getLong(i)));
                }
            } catch (JSONException unused) {
            }
        }
        Collections.sort(arrayList);
        Iterator<String> itKeys2 = jSONObject.keys();
        loop2: while (true) {
            str = null;
            while (itKeys2.hasNext() && str == null) {
                String next = itKeys2.next();
                try {
                    JSONArray jSONArray2 = new JSONArray((String) jSONObject.get(next));
                    String str2 = str;
                    int i2 = 0;
                    while (i2 < jSONArray2.length()) {
                        try {
                            if (jSONArray2.getLong(i2) == ((Long) arrayList.get(0)).longValue() || jSONArray2.getLong(i2) == ((Long) arrayList.get(1)).longValue() || jSONArray2.getLong(i2) == ((Long) arrayList.get(arrayList.size() - 1)).longValue()) {
                                break;
                            }
                            i2++;
                            str2 = next;
                        } catch (JSONException unused2) {
                        }
                    }
                    str = str2;
                } catch (JSONException unused3) {
                }
            }
        }
        if (str != null) {
            jSONObject.remove(str);
        }
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    static void m87(Context context, String str) {
        JSONObject jSONObject;
        JSONArray jSONArray;
        AFLogger.afDebugLog("received a new (extra) referrer: ".concat(String.valueOf(str)));
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            String string = getSharedPreferences(context).getString("extraReferrers", null);
            if (string == null) {
                jSONObject = new JSONObject();
                jSONArray = new JSONArray();
            } else {
                jSONObject = new JSONObject(string);
                if (jSONObject.has(str)) {
                    jSONArray = new JSONArray((String) jSONObject.get(str));
                } else {
                    jSONArray = new JSONArray();
                }
            }
            if (jSONArray.length() < 5) {
                jSONArray.put(jCurrentTimeMillis);
            }
            if (jSONObject.length() >= 4) {
                m92(jSONObject);
            }
            jSONObject.put(str, jSONArray.toString());
            String string2 = jSONObject.toString();
            SharedPreferences.Editor editorEdit = getSharedPreferences(context).edit();
            editorEdit.putString("extraReferrers", string2);
            editorEdit.apply();
        } catch (JSONException unused) {
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder("Couldn't save referrer - ");
            sb.append(str);
            sb.append(": ");
            AFLogger.afErrorLog(sb.toString(), th);
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void stop(boolean z, Context context) {
        this.f99 = z;
        u.m217();
        try {
            File fileM216 = u.m216(context);
            if (!fileM216.exists()) {
                fileM216.mkdir();
            } else {
                for (File file : fileM216.listFiles()) {
                    StringBuilder sb = new StringBuilder("Found cached request");
                    sb.append(file.getName());
                    AFLogger.afInfoLog(sb.toString());
                    u.m215(u.m219(file).f28, context);
                }
            }
        } catch (Exception e2) {
            AFLogger.afErrorLog("Could not cache request", e2);
        }
        if (this.f99) {
            SharedPreferences.Editor editorEdit = getSharedPreferences(context).edit();
            editorEdit.putBoolean(IS_STOP_TRACKING_USED, true);
            editorEdit.apply();
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public String getSdkVersion() {
        ac.m168().m175("public_api_call", "getSdkVersion", new String[0]);
        StringBuilder sb = new StringBuilder("version: 6.1.0 (build ");
        sb.append(f83);
        sb.append(")");
        return sb.toString();
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void onPause(Context context) {
        if (Foreground.listener != null) {
            Foreground.listener.onBecameBackground(context);
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void updateServerUninstallToken(Context context, String str) {
        z.m225(context, str);
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setDebugLog(boolean z) {
        setLogLevel(z ? AFLogger.LogLevel.DEBUG : AFLogger.LogLevel.NONE);
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setImeiData(String str) {
        ac.m168().m175("public_api_call", "setImeiData", str);
        this.f88 = str;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setOaidData(String str) {
        ac.m168().m175("public_api_call", "setOaidData", str);
        AdvertisingIdUtil.f78 = str;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setAndroidIdData(String str) {
        ac.m168().m175("public_api_call", "setAndroidIdData", str);
        this.f95 = str;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public AppsFlyerLib enableLocationCollection(boolean z) {
        this.f104 = z;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    public static void m54(Context context, String str, long j) {
        SharedPreferences.Editor editorEdit = getSharedPreferences(context).edit();
        editorEdit.putLong(str, j);
        editorEdit.apply();
    }

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    private static boolean m105(String str, boolean z) {
        return AppsFlyerProperties.getInstance().getBoolean(str, z);
    }

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    private static boolean m64() {
        return m105(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false) && AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.APP_USER_ID) == null;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void waitForCustomerUserId(boolean z) {
        AFLogger.afInfoLog("initAfterCustomerUserID: ".concat(String.valueOf(z)), true);
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, z);
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setCustomerIdAndLogSession(String str, @NonNull Context context) {
        if (context != null) {
            if (m64()) {
                setCustomerUserId(str);
                AppsFlyerProperties.getInstance().set(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false);
                StringBuilder sb = new StringBuilder("CustomerUserId set: ");
                sb.append(str);
                sb.append(" - Initializing AppsFlyer Tacking");
                AFLogger.afInfoLog(sb.toString(), true);
                String referrer = AppsFlyerProperties.getInstance().getReferrer(context);
                String string = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY);
                if (referrer == null) {
                    referrer = "";
                }
                if (context instanceof Activity) {
                    ((Activity) context).getIntent();
                }
                AFEvent aFEventContext = new Launch().context(context);
                aFEventContext.f8 = null;
                AFEvent aFEventKey = aFEventContext.key(string);
                aFEventKey.f7 = null;
                aFEventKey.f18 = referrer;
                aFEventKey.f11 = null;
                m100(aFEventKey);
                if (AppsFlyerProperties.getInstance().getString("afUninstallToken") != null) {
                    m111(context, AppsFlyerProperties.getInstance().getString("afUninstallToken"));
                    return;
                }
                return;
            }
            setCustomerUserId(str);
            AFLogger.afInfoLog("waitForCustomerUserId is false; setting CustomerUserID: ".concat(String.valueOf(str)), true);
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public String getOutOfStore(Context context) {
        String string = AppsFlyerProperties.getInstance().getString("api_store_value");
        if (string != null) {
            return string;
        }
        String strM67 = context == null ? null : m67("AF_STORE", context.getPackageManager(), context.getPackageName());
        if (strM67 != null) {
            return strM67;
        }
        AFLogger.afInfoLog("No out-of-store value set");
        return null;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setOutOfStore(String str) {
        if (str != null) {
            String lowerCase = str.toLowerCase();
            AppsFlyerProperties.getInstance().set("api_store_value", lowerCase);
            AFLogger.afInfoLog("Store API set with value: ".concat(String.valueOf(lowerCase)), true);
            return;
        }
        AFLogger.m22("Cannot set setOutOfStore with null");
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setAppInviteOneLink(String str) {
        ac.m168().m175("public_api_call", "setAppInviteOneLink", str);
        AFLogger.afInfoLog("setAppInviteOneLink = ".concat(String.valueOf(str)));
        if (str == null || !str.equals(AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.ONELINK_ID))) {
            AppsFlyerProperties.getInstance().remove(AppsFlyerProperties.ONELINK_DOMAIN);
            AppsFlyerProperties.getInstance().remove("onelinkVersion");
            AppsFlyerProperties.getInstance().remove(AppsFlyerProperties.ONELINK_SCHEME);
        }
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.ONELINK_ID, str);
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setAdditionalData(HashMap<String, Object> map) {
        if (map != null) {
            ac.m168().m175("public_api_call", "setAdditionalData", map.toString());
            AppsFlyerProperties.getInstance().setCustomData(new JSONObject(map).toString());
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void sendPushNotificationData(Activity activity) {
        long jLongValue;
        if (activity != null && activity.getIntent() != null) {
            ac acVarM168 = ac.m168();
            StringBuilder sb = new StringBuilder("activity_intent_");
            sb.append(activity.getIntent().toString());
            acVarM168.m175("public_api_call", "sendPushNotificationData", activity.getLocalClassName(), sb.toString());
        } else if (activity != null) {
            ac.m168().m175("public_api_call", "sendPushNotificationData", activity.getLocalClassName(), "activity_intent_null");
        } else {
            ac.m168().m175("public_api_call", "sendPushNotificationData", "activity_null");
        }
        this.f105 = m61(activity);
        if (this.f105 != null) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (this.f93 == null) {
                AFLogger.afInfoLog("pushes: initializing pushes history..");
                this.f93 = new ConcurrentHashMap();
                jLongValue = jCurrentTimeMillis;
            } else {
                try {
                    long j = AppsFlyerProperties.getInstance().getLong("pushPayloadMaxAging", 1800000L);
                    jLongValue = jCurrentTimeMillis;
                    for (Long l : this.f93.keySet()) {
                        try {
                            JSONObject jSONObject = new JSONObject(this.f105);
                            JSONObject jSONObject2 = new JSONObject(this.f93.get(l));
                            if (jSONObject.get(Constants.URL_MEDIA_SOURCE).equals(jSONObject2.get(Constants.URL_MEDIA_SOURCE)) && jSONObject.get(Constants.URL_CAMPAIGN).equals(jSONObject2.get(Constants.URL_CAMPAIGN))) {
                                StringBuilder sb2 = new StringBuilder("PushNotificationMeasurement: A previous payload with same PID and campaign was already acknowledged! (old: ");
                                sb2.append(jSONObject2);
                                sb2.append(", new: ");
                                sb2.append(jSONObject);
                                sb2.append(")");
                                AFLogger.afInfoLog(sb2.toString());
                                this.f105 = null;
                                return;
                            }
                            if (jCurrentTimeMillis - l.longValue() > j) {
                                this.f93.remove(l);
                            }
                            if (l.longValue() <= jLongValue) {
                                jLongValue = l.longValue();
                            }
                        } catch (Throwable th) {
                            th = th;
                            StringBuilder sb3 = new StringBuilder("Error while handling push notification measurement: ");
                            sb3.append(th.getClass().getSimpleName());
                            AFLogger.afErrorLog(sb3.toString(), th);
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    jLongValue = jCurrentTimeMillis;
                }
            }
            if (this.f93.size() == AppsFlyerProperties.getInstance().getInt("pushPayloadHistorySize", 2)) {
                StringBuilder sb4 = new StringBuilder("pushes: removing oldest overflowing push (oldest push:");
                sb4.append(jLongValue);
                sb4.append(")");
                AFLogger.afInfoLog(sb4.toString());
                this.f93.remove(Long.valueOf(jLongValue));
            }
            this.f93.put(Long.valueOf(jCurrentTimeMillis), this.f105);
            start(activity);
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setUserEmails(String... strArr) {
        ac.m168().m175("public_api_call", "setUserEmails", strArr);
        setUserEmails(AppsFlyerProperties.EmailsCryptType.NONE, strArr);
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setUserEmails(AppsFlyerProperties.EmailsCryptType emailsCryptType, String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length + 1);
        arrayList.add(emailsCryptType.toString());
        arrayList.addAll(Arrays.asList(strArr));
        ac.m168().m175("public_api_call", "setUserEmails", (String[]) arrayList.toArray(new String[strArr.length + 1]));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.EMAIL_CRYPT_TYPE, emailsCryptType.getValue());
        HashMap map = new HashMap();
        ArrayList arrayList2 = new ArrayList();
        String str = null;
        for (String str2 : strArr) {
            if (AnonymousClass7.f127[emailsCryptType.ordinal()] != 2) {
                arrayList2.add(HashUtils.toSha256(str2));
                str = "sha256_el_arr";
            } else {
                arrayList2.add(str2);
                str = "plain_el_arr";
            }
        }
        map.put(str, arrayList2);
        AppsFlyerProperties.getInstance().setUserEmails(new JSONObject(map).toString());
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setCollectAndroidID(boolean z) {
        ac.m168().m175("public_api_call", "setCollectAndroidID", String.valueOf(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.COLLECT_ANDROID_ID, Boolean.toString(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.COLLECT_ANDROID_ID_FORCE_BY_USER, Boolean.toString(z));
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setCollectIMEI(boolean z) {
        ac.m168().m175("public_api_call", "setCollectIMEI", String.valueOf(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.COLLECT_IMEI, Boolean.toString(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.COLLECT_IMEI_FORCE_BY_USER, Boolean.toString(z));
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setCollectOaid(boolean z) {
        ac.m168().m175("public_api_call", "setCollectOaid", String.valueOf(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.COLLECT_OAID, Boolean.toString(z));
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setResolveDeepLinkURLs(String... strArr) {
        AFLogger.afDebugLog(String.format("setResolveDeepLinkURLs %s", Arrays.toString(strArr)));
        AFDeepLinkManager.f0 = strArr;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setOneLinkCustomDomain(String... strArr) {
        AFLogger.afDebugLog(String.format("setOneLinkCustomDomain %s", Arrays.toString(strArr)));
        AFDeepLinkManager.f1 = strArr;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public AppsFlyerLib init(@NonNull String str, AppsFlyerConversionListener appsFlyerConversionListener, @NonNull final Context context) {
        if (this.f97) {
            return this;
        }
        this.f97 = true;
        if (context == null) {
            AFLogger.afWarnLog("init :: context is null, Google Install Referrer will be not initialized!");
        } else {
            if (this.f107 == null) {
                this.f107 = new EventDataCollector(context);
            }
            this.f107.init();
            this.f94 = (Application) context.getApplicationContext();
            if (GoogleReferrer.allow(this, context)) {
                if (this.googleReferrer == null) {
                    this.googleReferrer = new GoogleReferrer();
                    AFLogger.afDebugLog("Connecting to Install Referrer Library...");
                    this.googleReferrer.start(context, new Runnable() { // from class: com.appsflyer.AppsFlyerLibCore.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                AFLogger.afDebugLog("Install Referrer collected locally");
                                AppsFlyerLibCore.m63(AppsFlyerLibCore.this);
                            } catch (Throwable th) {
                                AFLogger.afErrorLog(th.getMessage(), th);
                            }
                        }
                    });
                } else {
                    AFLogger.afWarnLog("GoogleReferrer instance already created");
                }
            }
            final SharedPreferences sharedPreferences = getSharedPreferences(context);
            this.huaweiReferrer.start(context, new Runnable() { // from class: com.appsflyer.AppsFlyerLibCore.4
                @Override // java.lang.Runnable
                public final void run() {
                    if (AppsFlyerLibCore.this.getLaunchCounter(sharedPreferences, false) == 1) {
                        if (!GoogleReferrer.allow(AppsFlyerLibCore.this, context) || sharedPreferences.getBoolean(AppsFlyerProperties.NEW_REFERRER_SENT, false)) {
                            AppsFlyerLibCore.this.m76(new Attr().context(context));
                        }
                    }
                }
            });
            this.f113 = m103(context);
            Exlytics.setContext(this.f94);
        }
        ac acVarM168 = ac.m168();
        String[] strArr = new String[2];
        strArr[0] = str;
        strArr[1] = appsFlyerConversionListener == null ? "null" : "conversionDataListener";
        acVarM168.m175("public_api_call", "init", strArr);
        AFLogger.m23(String.format("Initializing AppsFlyer SDK: (v%s.%s)", "6.1.0", f83));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.AF_KEY, str);
        ab.m160(str);
        conversionDataListener = appsFlyerConversionListener;
        return this;
    }

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    private boolean m103(Context context) {
        try {
            Class.forName("com.appsflyer.lvl.AppsFlyerLVL");
            final long jCurrentTimeMillis = System.currentTimeMillis();
            this.f109 = new ConcurrentHashMap();
            final n.b bVar = new n.b() { // from class: com.appsflyer.AppsFlyerLibCore.5
                @Override // com.appsflyer.internal.n.b
                /* JADX INFO: renamed from: ı, reason: contains not printable characters */
                public final void mo118(@NonNull String str, @NonNull String str2) {
                    AppsFlyerLibCore.this.f109.put("signedData", str);
                    AppsFlyerLibCore.this.f109.put("signature", str2);
                    AppsFlyerLibCore.this.f109.put("ttr", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                    AFLogger.afInfoLog("Successfully retrieved Google LVL data.");
                }

                @Override // com.appsflyer.internal.n.b
                /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
                public final void mo119(String str, Exception exc) {
                    String message = exc.getMessage();
                    if (message == null) {
                        message = EnvironmentCompat.MEDIA_UNKNOWN;
                    }
                    AppsFlyerLibCore.this.f109.put("error", message);
                    AFLogger.afErrorLog(str, exc, true);
                }
            };
            try {
                try {
                    try {
                        Class<?> cls = Class.forName("com.appsflyer.lvl.AppsFlyerLVL");
                        Class<?> cls2 = Class.forName("com.appsflyer.lvl.AppsFlyerLVL$resultListener");
                        cls.getMethod("checkLicense", Long.TYPE, Context.class, cls2).invoke(null, Long.valueOf(jCurrentTimeMillis), context, Proxy.newProxyInstance(cls2.getClassLoader(), new Class[]{cls2}, new InvocationHandler() { // from class: com.appsflyer.internal.n.3
                            public AnonymousClass3() {
                            }

                            @Override // java.lang.reflect.InvocationHandler
                            public final Object invoke(Object obj, Method method, Object[] objArr) {
                                if (method.getName().equals("onLvlResult")) {
                                    String str = objArr[0] != null ? (String) objArr[0] : null;
                                    String str2 = objArr[1] != null ? (String) objArr[1] : null;
                                    b bVar2 = bVar;
                                    if (bVar2 == null) {
                                        AFLogger.afDebugLog("onLvlResult invocation succeeded, but listener is null");
                                    } else if (str != null && str2 != null) {
                                        bVar2.mo118(str, str2);
                                    } else if (str2 == null) {
                                        bVar.mo119("onLvlResult with error", new Exception("AFLVL Invalid signature"));
                                    } else {
                                        bVar.mo119("onLvlResult with error", new Exception("AFLVL Invalid signedData"));
                                    }
                                } else if (method.getName().equals("onLvlFailure")) {
                                    b bVar3 = bVar;
                                    if (bVar3 != null) {
                                        if (objArr[0] != null) {
                                            bVar3.mo119("onLvlFailure with exception", (Exception) objArr[0]);
                                        } else {
                                            bVar3.mo119("onLvlFailure", new Exception(EnvironmentCompat.MEDIA_UNKNOWN));
                                        }
                                    } else {
                                        AFLogger.afDebugLog("onLvlFailure: listener is null");
                                    }
                                } else {
                                    b bVar4 = bVar;
                                    if (bVar4 != null) {
                                        bVar4.mo119("lvlInvocation failed", new Exception("com.appsflyer.lvl.AppsFlyerLVL$resultListener invocation failed"));
                                    }
                                }
                                return null;
                            }
                        }));
                    } catch (NoSuchMethodException e2) {
                        bVar.mo119(e2.getClass().getSimpleName(), e2);
                    } catch (InvocationTargetException e3) {
                        bVar.mo119(e3.getClass().getSimpleName(), e3);
                    }
                } catch (IllegalAccessException e4) {
                    bVar.mo119(e4.getClass().getSimpleName(), e4);
                }
            } catch (ClassNotFoundException e5) {
                bVar.mo119(e5.getClass().getSimpleName(), e5);
            }
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void enableFacebookDeferredApplinks(boolean z) {
        this.f101 = z;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void start(@NonNull Context context) {
        start(context, null);
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void start(@NonNull Context context, String str) {
        start(context, str, null);
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void start(@NonNull Context context, final String str, final AppsFlyerRequestListener appsFlyerRequestListener) {
        if (Foreground.listener != null) {
            return;
        }
        if (!this.f97) {
            AFLogger.afWarnLog("ERROR: AppsFlyer SDK is not initialized! The API call 'start()' must be called after the 'init(String, AppsFlyerConversionListener)' API method, which should be called on the Application's onCreate.");
            if (str == null) {
                if (appsFlyerRequestListener != null) {
                    appsFlyerRequestListener.onError(RequestError.NO_DEV_KEY, RequestErrorMessage.NO_DEV_KEY);
                    return;
                }
                return;
            }
        }
        this.f94 = (Application) context.getApplicationContext();
        ac.m168().m175("public_api_call", "start", str);
        AFLogger.afInfoLog(String.format("Starting AppsFlyer: (v%s.%s)", "6.1.0", f83));
        StringBuilder sb = new StringBuilder("Build Number: ");
        sb.append(f83);
        AFLogger.afInfoLog(sb.toString());
        AppsFlyerProperties.getInstance().loadProperties(this.f94.getApplicationContext());
        if (TextUtils.isEmpty(str)) {
            if (TextUtils.isEmpty(AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY))) {
                AFLogger.afWarnLog("ERROR: AppsFlyer SDK is not initialized! You must provide AppsFlyer Dev-Key either in the 'init' API method (should be called on Application's onCreate),or in the start() API (should be called on Activity's onCreate).");
                if (appsFlyerRequestListener != null) {
                    appsFlyerRequestListener.onError(RequestError.NO_DEV_KEY, RequestErrorMessage.NO_DEV_KEY);
                    return;
                }
                return;
            }
        } else {
            AppsFlyerProperties.getInstance().set(AppsFlyerProperties.AF_KEY, str);
            ab.m160(str);
        }
        Context baseContext = this.f94.getBaseContext();
        try {
            if ((baseContext.getPackageManager().getPackageInfo(baseContext.getPackageName(), 0).applicationInfo.flags & 32768) != 0) {
                if (baseContext.getResources().getIdentifier("appsflyer_backup_rules", "xml", baseContext.getPackageName()) != 0) {
                    AFLogger.afInfoLog("appsflyer_backup_rules.xml detected, using AppsFlyer defined backup rules for AppsFlyer SDK data", true);
                } else {
                    AFLogger.m22("'allowBackup' is set to true; appsflyer_backup_rules.xml not detected.\nAppsFlyer shared preferences should be excluded from auto backup by adding: <exclude domain=\"sharedpref\" path=\"appsflyer-data\"/> to the Application's <full-backup-content> rules");
                }
            }
        } catch (Exception e2) {
            StringBuilder sb2 = new StringBuilder("checkBackupRules Exception: ");
            sb2.append(e2.toString());
            AFLogger.afRDLog(sb2.toString());
        }
        if (this.f101) {
            Context applicationContext = this.f94.getApplicationContext();
            this.f98 = new HashMap();
            final long jCurrentTimeMillis = System.currentTimeMillis();
            AFFacebookDeferredDeeplink.AppLinkFetchEvents appLinkFetchEvents = new AFFacebookDeferredDeeplink.AppLinkFetchEvents() { // from class: com.appsflyer.AppsFlyerLibCore.2
                @Override // com.appsflyer.AFFacebookDeferredDeeplink.AppLinkFetchEvents
                public final void onAppLinkFetchFinished(String str2, String str3, String str4) {
                    if (str2 == null) {
                        AppsFlyerLibCore.this.f98.put("link", "");
                    } else {
                        AFLogger.afInfoLog("Facebook Deferred AppLink data received: ".concat(String.valueOf(str2)));
                        AppsFlyerLibCore.this.f98.put("link", str2);
                        if (str3 != null) {
                            AppsFlyerLibCore.this.f98.put("target_url", str3);
                        }
                        if (str4 != null) {
                            HashMap map = new HashMap();
                            HashMap map2 = new HashMap();
                            map2.put("promo_code", str4);
                            map.put("deeplink_context", map2);
                            AppsFlyerLibCore.this.f98.put("extras", map);
                        }
                    }
                    AppsFlyerLibCore.this.f98.put("ttr", String.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                }

                @Override // com.appsflyer.AFFacebookDeferredDeeplink.AppLinkFetchEvents
                public final void onAppLinkFetchFailed(String str2) {
                    AppsFlyerLibCore.this.f98.put("error", str2);
                }
            };
            try {
                Class.forName("com.facebook.FacebookSdk").getMethod("sdkInitialize", Context.class).invoke(null, applicationContext);
                Class<?> cls = Class.forName("com.facebook.applinks.AppLinkData");
                Class<?> cls2 = Class.forName("com.facebook.applinks.AppLinkData$CompletionHandler");
                Method method = cls.getMethod("fetchDeferredAppLinkData", Context.class, String.class, cls2);
                Object objNewProxyInstance = Proxy.newProxyInstance(cls2.getClassLoader(), new Class[]{cls2}, new InvocationHandler() { // from class: com.appsflyer.AFFacebookDeferredDeeplink.1

                    /* JADX INFO: renamed from: ǃ */
                    private /* synthetic */ AppLinkFetchEvents f31;

                    /* JADX INFO: renamed from: ι */
                    private /* synthetic */ Class f32;

                    AnonymousClass1(Class cls3, AppLinkFetchEvents appLinkFetchEvents2) {
                        cls = cls3;
                        appLinkFetchEvents = appLinkFetchEvents2;
                    }

                    @Override // java.lang.reflect.InvocationHandler
                    public final Object invoke(Object obj, Method method2, Object[] objArr) throws Throwable {
                        String string;
                        String string2;
                        String string3;
                        Bundle bundle;
                        if (method2.getName().equals("onDeferredAppLinkDataFetched")) {
                            if (objArr[0] != null) {
                                Bundle bundle2 = (Bundle) Bundle.class.cast(cls.getMethod("getArgumentBundle", new Class[0]).invoke(cls.cast(objArr[0]), new Object[0]));
                                if (bundle2 != null) {
                                    string2 = bundle2.getString("com.facebook.platform.APPLINK_NATIVE_URL");
                                    string3 = bundle2.getString("target_url");
                                    Bundle bundle3 = bundle2.getBundle("extras");
                                    string = (bundle3 == null || (bundle = bundle3.getBundle("deeplink_context")) == null) ? null : bundle.getString("promo_code");
                                } else {
                                    string = null;
                                    string2 = null;
                                    string3 = null;
                                }
                                AppLinkFetchEvents appLinkFetchEvents2 = appLinkFetchEvents;
                                if (appLinkFetchEvents2 != null) {
                                    appLinkFetchEvents2.onAppLinkFetchFinished(string2, string3, string);
                                }
                            } else {
                                AppLinkFetchEvents appLinkFetchEvents3 = appLinkFetchEvents;
                                if (appLinkFetchEvents3 != null) {
                                    appLinkFetchEvents3.onAppLinkFetchFinished(null, null, null);
                                }
                            }
                            return null;
                        }
                        AppLinkFetchEvents appLinkFetchEvents4 = appLinkFetchEvents;
                        if (appLinkFetchEvents4 != null) {
                            appLinkFetchEvents4.onAppLinkFetchFailed("onDeferredAppLinkDataFetched invocation failed");
                        }
                        return null;
                    }
                });
                String string = applicationContext.getString(applicationContext.getResources().getIdentifier("facebook_app_id", "string", applicationContext.getPackageName()));
                if (TextUtils.isEmpty(string)) {
                    appLinkFetchEvents2.onAppLinkFetchFailed("Facebook app id not defined in resources");
                } else {
                    method.invoke(null, applicationContext, string, objNewProxyInstance);
                }
            } catch (ClassNotFoundException e3) {
                appLinkFetchEvents2.onAppLinkFetchFailed(e3.toString());
            } catch (IllegalAccessException e4) {
                appLinkFetchEvents2.onAppLinkFetchFailed(e4.toString());
            } catch (NoSuchMethodException e5) {
                appLinkFetchEvents2.onAppLinkFetchFailed(e5.toString());
            } catch (InvocationTargetException e6) {
                appLinkFetchEvents2.onAppLinkFetchFailed(e6.toString());
            }
        }
        Foreground.m123(context, new Foreground.Listener() { // from class: com.appsflyer.AppsFlyerLibCore.3
            @Override // com.appsflyer.Foreground.Listener
            public final void onBecameForeground(Activity activity) {
                AppsFlyerLibCore.this.f106 = System.currentTimeMillis();
                AppsFlyerLibCore.m96(AppsFlyerLibCore.this, activity).foreground();
                AFLogger.afInfoLog("onBecameForeground");
                if (AppsFlyerLibCore.this.getLaunchCounter(AppsFlyerLibCore.getSharedPreferences(activity), false) < 2) {
                    AFSensorManager aFSensorManagerM34 = AFSensorManager.m34(activity);
                    aFSensorManagerM34.f50.post(aFSensorManagerM34.f44);
                    aFSensorManagerM34.f50.post(aFSensorManagerM34.f45);
                }
                Launch launch = new Launch();
                AFDeepLinkManager aFDeepLinkManager = AFDeepLinkManager.getInstance();
                Map<String, Object> mapParams = launch.params();
                SharedPreferences sharedPreferences = AppsFlyerLibCore.getSharedPreferences(activity);
                boolean z = sharedPreferences.getBoolean(AFDeepLinkManager.DDL_SENT, false);
                int launchCounter = AppsFlyerLibCore.getInstance().getLaunchCounter(sharedPreferences, false);
                if (!aFDeepLinkManager.m7(activity.getIntent(), activity, mapParams) && aFDeepLinkManager.deepLinkListener != null && launchCounter == 0 && !z) {
                    new DdlEvent(activity).start();
                }
                AppsFlyerLibCore.this.m112(launch.context(activity).key(str).requestListener(appsFlyerRequestListener));
            }

            @Override // com.appsflyer.Foreground.Listener
            public final void onBecameBackground(Context context2) {
                AFLogger.afInfoLog("onBecameBackground");
                AppsFlyerLibCore.this.f89 = System.currentTimeMillis();
                AppsFlyerLibCore.m96(AppsFlyerLibCore.this, context2).set(ServerParameters.PREVIOUS_SESSION_DURATION, TimeUnit.MILLISECONDS.toSeconds(AppsFlyerLibCore.this.f89 - AppsFlyerLibCore.this.f106));
                AFLogger.afInfoLog("callStatsBackground background call");
                AppsFlyerLibCore.this.m114(new WeakReference<>(context2));
                ac acVarM168 = ac.m168();
                if (acVarM168.m178()) {
                    acVarM168.m172();
                    if (context2 != null) {
                        ac.m164(context2.getPackageName(), context2.getPackageManager());
                    }
                    acVarM168.m177();
                } else {
                    AFLogger.afDebugLog("RD status is OFF");
                }
                AFExecutor aFExecutor = AFExecutor.getInstance();
                try {
                    AFExecutor.m11(aFExecutor.f22);
                    if (aFExecutor.f24 instanceof ThreadPoolExecutor) {
                        AFExecutor.m11((ThreadPoolExecutor) aFExecutor.f24);
                    }
                } catch (Throwable th) {
                    AFLogger.afErrorLog("failed to stop Executors", th);
                }
                AFSensorManager aFSensorManagerM34 = AFSensorManager.m34(context2);
                aFSensorManagerM34.f50.post(aFSensorManagerM34.f44);
            }
        });
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    private static void m86(Context context) {
        int i;
        if (AndroidUtils.m49()) {
            i = 23;
            AFLogger.afRDLog("OPPO device found");
        } else {
            i = 18;
        }
        if (Build.VERSION.SDK_INT >= i && !m105(AppsFlyerProperties.DISABLE_KEYSTORE, true)) {
            StringBuilder sb = new StringBuilder("OS SDK is=");
            sb.append(Build.VERSION.SDK_INT);
            sb.append("; use KeyStore");
            AFLogger.afRDLog(sb.toString());
            AFKeystoreWrapper aFKeystoreWrapper = new AFKeystoreWrapper(context);
            if (!aFKeystoreWrapper.m16()) {
                aFKeystoreWrapper.f33 = x.m222(new WeakReference(context));
                aFKeystoreWrapper.f35 = 0;
                aFKeystoreWrapper.m18(aFKeystoreWrapper.m19());
            } else {
                String strM19 = aFKeystoreWrapper.m19();
                synchronized (aFKeystoreWrapper.f36) {
                    aFKeystoreWrapper.f35++;
                    AFLogger.afInfoLog("Deleting key with alias: ".concat(String.valueOf(strM19)));
                    try {
                        synchronized (aFKeystoreWrapper.f36) {
                            aFKeystoreWrapper.f34.deleteEntry(strM19);
                        }
                    } catch (KeyStoreException e2) {
                        StringBuilder sb2 = new StringBuilder("Exception ");
                        sb2.append(e2.getMessage());
                        sb2.append(" occurred");
                        AFLogger.afErrorLog(sb2.toString(), e2);
                    }
                }
                aFKeystoreWrapper.m18(aFKeystoreWrapper.m19());
            }
            AppsFlyerProperties.getInstance().set("KSAppsFlyerId", aFKeystoreWrapper.m20());
            AppsFlyerProperties.getInstance().set("KSAppsFlyerRICounter", String.valueOf(aFKeystoreWrapper.m17()));
            return;
        }
        StringBuilder sb3 = new StringBuilder("OS SDK is=");
        sb3.append(Build.VERSION.SDK_INT);
        sb3.append("; no KeyStore usage");
        AFLogger.afRDLog(sb3.toString());
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setCustomerUserId(String str) {
        ac.m168().m175("public_api_call", "setCustomerUserId", str);
        AFLogger.afInfoLog("setCustomerUserId = ".concat(String.valueOf(str)));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.APP_USER_ID, str);
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setPhoneNumber(String str) {
        this.f111 = HashUtils.toSha256(str);
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setAppId(String str) {
        ac.m168().m175("public_api_call", "setAppId", str);
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.APP_ID, str);
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setExtension(String str) {
        ac.m168().m175("public_api_call", "setExtension", str);
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.EXTENSION, str);
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setIsUpdate(boolean z) {
        ac.m168().m175("public_api_call", "setIsUpdate", String.valueOf(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.IS_UPDATE, z);
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setCurrencyCode(String str) {
        ac.m168().m175("public_api_call", "setCurrencyCode", str);
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.CURRENCY_CODE, str);
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void logLocation(Context context, double d2, double d3) {
        ac.m168().m175("public_api_call", "logLocation", String.valueOf(d2), String.valueOf(d3));
        HashMap map = new HashMap();
        map.put(AFInAppEventParameterName.LONGTITUDE, Double.toString(d3));
        map.put(AFInAppEventParameterName.LATITUDE, Double.toString(d2));
        AFEvent aFEventContext = new InAppEvent().context(context);
        aFEventContext.f8 = AFInAppEventType.LOCATION_COORDINATES;
        aFEventContext.f7 = map;
        m112(aFEventContext);
    }

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    final void m114(WeakReference<Context> weakReference) {
        if (weakReference.get() == null) {
            return;
        }
        AFLogger.afInfoLog("app went to background");
        SharedPreferences sharedPreferences = getSharedPreferences(weakReference.get());
        AppsFlyerProperties.getInstance().saveProperties(sharedPreferences);
        long j = this.f89 - this.f106;
        HashMap map = new HashMap();
        String string = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY);
        if (string == null) {
            AFLogger.afWarnLog("[callStats] AppsFlyer's SDK cannot send any event without providing DevKey.");
            return;
        }
        String string2 = AppsFlyerProperties.getInstance().getString("KSAppsFlyerId");
        if (AppsFlyerProperties.getInstance().getBoolean("deviceTrackingDisabled", false)) {
            map.put("deviceTrackingDisabled", "true");
        }
        AdvertisingIdObject advertisingIdObjectM46 = AdvertisingIdUtil.m46(weakReference.get().getContentResolver());
        if (advertisingIdObjectM46 != null) {
            map.put(ServerParameters.AMAZON_AID, advertisingIdObjectM46.getAdvertisingId());
            map.put(ServerParameters.AMAZON_AID_LIMIT, String.valueOf(advertisingIdObjectM46.isLimitAdTracking()));
        }
        String string3 = AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM);
        if (string3 != null) {
            map.put(ServerParameters.ADVERTISING_ID_PARAM, string3);
        }
        map.put(ServerParameters.APP_ID, weakReference.get().getPackageName());
        map.put(ServerParameters.DEV_KEY, string);
        map.put(ServerParameters.AF_USER_ID, x.m222(weakReference));
        map.put(ServerParameters.TIME_SPENT_IN_APP, String.valueOf(j / 1000));
        map.put(ServerParameters.STATUS_TYPE, "user_closed_app");
        map.put(ServerParameters.PLATFORM, "Android");
        map.put(ServerParameters.LAUNCH_COUNTER, Integer.toString(getLaunchCounter(sharedPreferences, false)));
        map.put("channel", getConfiguredChannel(weakReference.get()));
        if (string2 == null) {
            string2 = "";
        }
        map.put(ServerParameters.ORIGINAL_AF_UID, string2);
        if (this.f102) {
            try {
                AFLogger.afDebugLog("Running callStats task");
                new Thread(new BackgroundHttpTask((BackgroundEvent) new Stats().trackingStopped(isStopped()).addParams(map).urlString(ServerConfigHandler.getUrl(f86))).new AnonymousClass1()).start();
                return;
            } catch (Throwable th) {
                AFLogger.afErrorLog("Could not send callStats request", th);
                return;
            }
        }
        AFLogger.afDebugLog("Stats call is disabled, ignore ...");
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void logSession(Context context) {
        ac.m168().m175("public_api_call", "logSession", new String[0]);
        ac.m168().f209 = false;
        AFEvent aFEventContext = new InAppEvent().context(context);
        aFEventContext.f8 = null;
        aFEventContext.f7 = null;
        m112(aFEventContext);
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void logEvent(@NonNull Context context, String str, Map<String, Object> map, AppsFlyerRequestListener appsFlyerRequestListener) {
        AFEvent aFEventContext = new InAppEvent().context(context);
        aFEventContext.f8 = str;
        aFEventContext.f7 = map == null ? null : new HashMap(map);
        AFEvent aFEventRequestListener = aFEventContext.requestListener(appsFlyerRequestListener);
        ac acVarM168 = ac.m168();
        String[] strArr = new String[2];
        strArr[0] = str;
        strArr[1] = new JSONObject(aFEventRequestListener.f7 == null ? new HashMap() : aFEventRequestListener.f7).toString();
        acVarM168.m175("public_api_call", "logEvent", strArr);
        if (str != null) {
            AFSensorManager aFSensorManagerM34 = AFSensorManager.m34(context);
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (aFSensorManagerM34.f54 != 0) {
                aFSensorManagerM34.f55++;
                if (aFSensorManagerM34.f54 - jCurrentTimeMillis < 500) {
                    aFSensorManagerM34.f50.removeCallbacks(aFSensorManagerM34.f51);
                    aFSensorManagerM34.f50.post(aFSensorManagerM34.f45);
                }
            } else {
                aFSensorManagerM34.f50.post(aFSensorManagerM34.f44);
                aFSensorManagerM34.f50.post(aFSensorManagerM34.f45);
            }
            aFSensorManagerM34.f54 = jCurrentTimeMillis;
        }
        m112(aFEventRequestListener);
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void sendAdRevenue(Context context, Map<String, Object> map) {
        AFEvent aFEventContext = new AdRevenue().context(context);
        aFEventContext.f7 = map;
        Context context2 = aFEventContext.context();
        String url = ServerConfigHandler.getUrl(f85);
        StringBuilder sb = new StringBuilder();
        sb.append(url);
        sb.append(context2.getPackageName());
        String string = sb.toString();
        SharedPreferences sharedPreferences = getSharedPreferences(context2);
        byte b = 0;
        int launchCounter = getLaunchCounter(sharedPreferences, false);
        int iM58 = m58(sharedPreferences, "appsFlyerAdRevenueCount", true);
        HashMap map2 = new HashMap();
        map2.put(ServerParameters.AD_REVENUE_PAYLOAD, aFEventContext.f7);
        map2.put(ServerParameters.AD_REVENUE_COUNTER, Integer.valueOf(iM58));
        String string2 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY);
        map2.put("af_key", string2);
        map2.put(ServerParameters.LAUNCH_COUNTER, Integer.valueOf(launchCounter));
        map2.put(ServerParameters.TIMESTAMP, Long.toString(new Date().getTime()));
        map2.put(ServerParameters.AF_USER_ID, x.m222(new WeakReference(context2)));
        String string3 = AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM);
        map2.put(ServerParameters.ADVERTISING_ID_ENABLED_PARAM, AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_ENABLED_PARAM));
        if (string3 != null) {
            map2.put(ServerParameters.ADVERTISING_ID_PARAM, string3);
        }
        map2.put(ServerParameters.DEVICE_KEY, Build.DEVICE);
        m89(context2, map2);
        try {
            PackageInfo packageInfo = context2.getPackageManager().getPackageInfo(context2.getPackageName(), 0);
            map2.put(ServerParameters.APP_VERSION_CODE, Integer.toString(packageInfo.versionCode));
            SimpleDateFormat dataFormatter = AFDateFormat.getDataFormatter("yyyy-MM-dd_HHmmssZ");
            long j = packageInfo.firstInstallTime;
            dataFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            map2.put("install_date", dataFormatter.format(new Date(j)));
            String string4 = sharedPreferences.getString("appsFlyerFirstInstall", null);
            if (string4 == null) {
                string4 = m68(dataFormatter, context2);
            }
            map2.put("first_launch_date", string4);
        } catch (Throwable th) {
            AFLogger.afErrorLog("AdRevenue - Exception while collecting app version data ", th);
        }
        AFEvent aFEventAddParams = aFEventContext.urlString(string).addParams(map2);
        if (aFEventAddParams.context() != null) {
            aFEventAddParams.f16 = aFEventAddParams.context().getApplicationContext();
        }
        aFEventAddParams.f13 = launchCounter;
        m101(AFExecutor.getInstance().m12(), new e(this, aFEventAddParams.key(string2), b), 1L, TimeUnit.MILLISECONDS);
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void logEvent(Context context, String str, Map<String, Object> map) {
        logEvent(context, str, map, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001b  */
    @androidx.annotation.VisibleForTesting
    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final void m112(@androidx.annotation.NonNull com.appsflyer.AFEvent r6) {
        /*
            r5 = this;
            android.content.Context r0 = r6.context()
            boolean r1 = r0 instanceof android.app.Activity
            java.lang.String r2 = ""
            if (r1 == 0) goto L1b
            r1 = r0
            android.app.Activity r1 = (android.app.Activity) r1
            r1.getIntent()
            android.net.Uri r1 = com.appsflyer.internal.ActivityCompat.getReferrer(r1)
            if (r1 == 0) goto L1b
            java.lang.String r1 = r1.toString()
            goto L1c
        L1b:
            r1 = r2
        L1c:
            com.appsflyer.AppsFlyerProperties r3 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r4 = "AppsFlyerKey"
            java.lang.String r3 = r3.getString(r4)
            if (r3 != 0) goto L3b
            java.lang.String r0 = "[LogEvent/Launch] AppsFlyer's SDK cannot send any event without providing DevKey."
            com.appsflyer.AFLogger.afWarnLog(r0)
            com.appsflyer.attribution.AppsFlyerRequestListener r6 = r6.getRequestListener()
            if (r6 == 0) goto L3a
            int r0 = com.appsflyer.attribution.RequestError.NO_DEV_KEY
            java.lang.String r1 = com.appsflyer.internal.attribution.RequestErrorMessage.NO_DEV_KEY
            r6.onError(r0, r1)
        L3a:
            return
        L3b:
            com.appsflyer.AppsFlyerProperties r3 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r0 = r3.getReferrer(r0)
            if (r0 != 0) goto L46
            r0 = r2
        L46:
            r6.f18 = r0
            r6.f11 = r1
            r5.m100(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.AppsFlyerLibCore.m112(com.appsflyer.AFEvent):void");
    }

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    public final void m111(Context context, String str) {
        if (m64()) {
            AFLogger.afInfoLog("CustomerUserId not set, reporting is disabled", true);
            return;
        }
        HashMap map = new HashMap();
        String string = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY);
        if (string == null) {
            AFLogger.afWarnLog("[registerUninstall] AppsFlyer's SDK cannot send any event without providing DevKey.");
            return;
        }
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            map.put(ServerParameters.APP_VERSION_CODE, Integer.toString(packageInfo.versionCode));
            map.put(ServerParameters.APP_VERSION_NAME, packageInfo.versionName);
            map.put(ServerParameters.APP_NAME, packageManager.getApplicationLabel(packageInfo.applicationInfo).toString());
            long j = packageInfo.firstInstallTime;
            SimpleDateFormat dataFormatter = AFDateFormat.getDataFormatter("yyyy-MM-dd_HHmmssZ");
            dataFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            map.put(ServerParameters.INSTALL_DATE, dataFormatter.format(new Date(j)));
        } catch (Throwable th) {
            AFLogger.afErrorLog("Exception while collecting application version info.", th);
        }
        m75(context, map);
        String string2 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.APP_USER_ID);
        if (string2 != null) {
            map.put(ServerParameters.APP_USER_ID, string2);
        }
        try {
            map.put(ServerParameters.MODEL, Build.MODEL);
            map.put(ServerParameters.BRAND, Build.BRAND);
        } catch (Throwable th2) {
            AFLogger.afErrorLog("Exception while collecting device brand and model.", th2);
        }
        if (AppsFlyerProperties.getInstance().getBoolean("deviceTrackingDisabled", false)) {
            map.put("deviceTrackingDisabled", "true");
        }
        AdvertisingIdObject advertisingIdObjectM46 = AdvertisingIdUtil.m46(context.getContentResolver());
        if (advertisingIdObjectM46 != null) {
            map.put(ServerParameters.AMAZON_AID, advertisingIdObjectM46.getAdvertisingId());
            map.put(ServerParameters.AMAZON_AID_LIMIT, String.valueOf(advertisingIdObjectM46.isLimitAdTracking()));
        }
        String string3 = AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM);
        if (string3 != null) {
            map.put(ServerParameters.ADVERTISING_ID_PARAM, string3);
        }
        map.put(ServerParameters.DEV_KEY, string);
        map.put(ServerParameters.AF_USER_ID, x.m222(new WeakReference(context)));
        map.put(ServerParameters.AF_FIREBASE_TOKEN, str);
        map.put(ServerParameters.LAUNCH_COUNTER, Integer.toString(getLaunchCounter(getSharedPreferences(context), false)));
        map.put(ServerParameters.ANDROID_SDK_INT, Integer.toString(Build.VERSION.SDK_INT));
        String configuredChannel = getConfiguredChannel(context);
        if (configuredChannel != null) {
            map.put("channel", configuredChannel);
        }
        try {
            AFEvent aFEventContext = new UninstallTokenEvent().trackingStopped(isStopped()).addParams(map).context(context);
            StringBuilder sb = new StringBuilder();
            sb.append(ServerConfigHandler.getUrl(REGISTER_URL));
            sb.append(packageName);
            new Thread(new BackgroundHttpTask((BackgroundEvent) aFEventContext.urlString(sb.toString())).new AnonymousClass1()).start();
        } catch (Throwable th3) {
            AFLogger.afErrorLog(th3.getMessage(), th3);
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void anonymizeUser(boolean z) {
        ac.m168().m175("public_api_call", "anonymizeUser", String.valueOf(z));
        AppsFlyerProperties.getInstance().set("deviceTrackingDisabled", z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    public static Map<String, Object> m69(Context context) throws w {
        String string = getSharedPreferences(context).getString("attributionId", null);
        if (string != null && string.length() > 0) {
            return m72(string);
        }
        throw new w();
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void registerConversionListener(Context context, AppsFlyerConversionListener appsFlyerConversionListener) {
        ac.m168().m175("public_api_call", "registerConversionListener", new String[0]);
        if (appsFlyerConversionListener != null) {
            conversionDataListener = appsFlyerConversionListener;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void unregisterConversionListener() {
        ac.m168().m175("public_api_call", "unregisterConversionListener", new String[0]);
        conversionDataListener = null;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void registerValidatorListener(Context context, AppsFlyerInAppPurchaseValidatorListener appsFlyerInAppPurchaseValidatorListener) {
        ac.m168().m175("public_api_call", "registerValidatorListener", new String[0]);
        AFLogger.afDebugLog("registerValidatorListener called");
        if (appsFlyerInAppPurchaseValidatorListener == null) {
            AFLogger.afDebugLog("registerValidatorListener null listener");
        } else {
            f80 = appsFlyerInAppPurchaseValidatorListener;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    public static Map<String, Object> m72(String str) {
        HashMap map = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (!f81.contains(next)) {
                    map.put(next, jSONObject.isNull(next) ? null : jSONObject.get(next));
                }
            }
            return map;
        } catch (JSONException e2) {
            AFLogger.afErrorLog(e2.getMessage(), e2);
            return null;
        }
    }

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    private boolean m79() {
        if (this.f96 > 0) {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.f96;
            SimpleDateFormat dataFormatter = AFDateFormat.getDataFormatter("yyyy/MM/dd HH:mm:ss.SSS Z");
            long j = this.f96;
            dataFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            String str = dataFormatter.format(new Date(j));
            long j2 = this.f103;
            dataFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            String str2 = dataFormatter.format(new Date(j2));
            if (jCurrentTimeMillis < this.f100 && !isStopped()) {
                AFLogger.afInfoLog(String.format(Locale.US, "Last Launch attempt: %s;\nLast successful Launch event: %s;\nThis launch is blocked: %s ms < %s ms", str, str2, Long.valueOf(jCurrentTimeMillis), Long.valueOf(this.f100)));
                return true;
            }
            if (!isStopped()) {
                AFLogger.afInfoLog(String.format(Locale.US, "Last Launch attempt: %s;\nLast successful Launch event: %s;\nSending launch (+%s ms)", str, str2, Long.valueOf(jCurrentTimeMillis)));
            }
        } else if (!isStopped()) {
            AFLogger.afInfoLog("Sending first launch for this session!");
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    public void m76(AFEvent aFEvent) {
        String url;
        ScheduledExecutorService scheduledExecutorServiceM12;
        Context context = aFEvent.context();
        String str = aFEvent.f8;
        if (context == null) {
            AFLogger.afDebugLog("sendWithEvent - got null context. skipping event/launch.");
            return;
        }
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        AppsFlyerProperties.getInstance().saveProperties(sharedPreferences);
        if (!isStopped()) {
            StringBuilder sb = new StringBuilder("sendWithEvent from activity: ");
            sb.append(context.getClass().getName());
            AFLogger.afInfoLog(sb.toString());
        }
        byte b = 0;
        boolean z = str == null;
        boolean z2 = aFEvent instanceof BackgroundReferrerLaunch;
        boolean z3 = aFEvent instanceof Attr;
        aFEvent.f19 = z;
        Map<String, ?> mapM113 = m113(aFEvent);
        String str2 = (String) mapM113.get(ServerParameters.AF_DEV_KEY);
        if (str2 == null || str2.length() == 0) {
            AFLogger.afDebugLog("Not sending data yet, waiting for dev key");
            AppsFlyerRequestListener requestListener = aFEvent.getRequestListener();
            if (requestListener != null) {
                requestListener.onError(RequestError.NO_DEV_KEY, RequestErrorMessage.NO_DEV_KEY);
                return;
            }
            return;
        }
        if (!isStopped()) {
            AFLogger.afInfoLog("AppsFlyerLib.sendWithEvent");
        }
        int launchCounter = getLaunchCounter(sharedPreferences, false);
        if (z3 || z2) {
            url = ServerConfigHandler.getUrl(REFERRER_TRACKING_URL);
        } else if (!z) {
            url = ServerConfigHandler.getUrl(f87);
        } else if (launchCounter < 2) {
            url = ServerConfigHandler.getUrl(FIRST_LAUNCHES_URL);
        } else {
            url = ServerConfigHandler.getUrl(f84);
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(url);
        sb2.append(context.getPackageName());
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(string);
        sb3.append("&buildnumber=6.1.0");
        String string2 = sb3.toString();
        String configuredChannel = getConfiguredChannel(context);
        if (configuredChannel != null) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(string2);
            sb4.append("&channel=");
            sb4.append(configuredChannel);
            string2 = sb4.toString();
        }
        if (!(AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_ANDROID_ID_FORCE_BY_USER, false) || AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_IMEI_FORCE_BY_USER, false)) && mapM113.get(ServerParameters.ADVERTISING_ID_PARAM) != null) {
            try {
                if (TextUtils.isEmpty(this.f95) && mapM113.remove(ServerParameters.ANDROID_ID) != null) {
                    AFLogger.afInfoLog("validateGaidAndIMEI :: removing: android_id");
                }
                if (TextUtils.isEmpty(this.f88) && mapM113.remove(ServerParameters.IMEI) != null) {
                    AFLogger.afInfoLog("validateGaidAndIMEI :: removing: imei");
                }
            } catch (Exception e2) {
                AFLogger.afErrorLog("failed to remove IMEI or AndroidID key from params; ", e2);
            }
        }
        AFEvent aFEventAddParams = aFEvent.urlString(string2).addParams(mapM113);
        if (aFEventAddParams.context() != null) {
            aFEventAddParams.f16 = aFEventAddParams.context().getApplicationContext();
        }
        aFEventAddParams.f13 = launchCounter;
        e eVar = new e(this, aFEventAddParams, b);
        if (z) {
            if (GoogleReferrer.allow(this, context) && !m55()) {
                AFLogger.afDebugLog("Failed to get new referrer, wait ...");
                b = 1;
            }
            if (this.huaweiReferrer.getState() == Referrer.State.STARTED) {
                b = 1;
            }
            if (this.f101 && !m93()) {
                AFLogger.afDebugLog("fetching Facebook deferred AppLink data, wait ...");
                b = 1;
            }
            if (this.f113 && !m102()) {
                b = 1;
            }
        }
        if (AFDeepLinkManager.f3) {
            AFLogger.afRDLog("ESP deeplink: execute launch on SerialExecutor");
            AFExecutor aFExecutor = AFExecutor.getInstance();
            if (aFExecutor.f23 == null) {
                aFExecutor.f23 = Executors.newSingleThreadScheduledExecutor(aFExecutor.f25);
            }
            scheduledExecutorServiceM12 = aFExecutor.f23;
        } else {
            scheduledExecutorServiceM12 = AFExecutor.getInstance().m12();
        }
        m101(scheduledExecutorServiceM12, eVar, b != 0 ? 500L : 0L, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    private boolean m104(AFEvent aFEvent, SharedPreferences sharedPreferences) {
        int launchCounter = getLaunchCounter(sharedPreferences, false);
        return (!sharedPreferences.getBoolean(AppsFlyerProperties.NEW_REFERRER_SENT, false) && launchCounter == 1) || (launchCounter == 1 && !(aFEvent instanceof Attr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    public boolean m55() {
        GoogleReferrer googleReferrer = this.googleReferrer;
        return googleReferrer != null && googleReferrer.oldMap.size() > 0;
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    private boolean m93() {
        Map<String, Object> map = this.f98;
        return (map == null || map.isEmpty()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    public boolean m102() {
        Map<String, Object> map = this.f109;
        return (map == null || map.isEmpty()) ? false : true;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(66:0|2|(1:4)(1:5)|6|(1:8)|(28:464|9|(3:11|(1:13)(1:14)|15)(1:16)|17|473|18|(1:20)|471|25|(1:27)|28|(1:30)|31|(1:33)|38|(1:40)|41|(21:43|(7:45|(1:47)|48|(1:50)(1:51)|(1:55)|56|(1:58))|59|(1:61)(1:62)|63|(1:65)|(1:67)|68|(1:70)|71|(1:73)|74|75|(1:77)|78|(1:80)|81|(1:85)|86|(1:88)|89)(7:90|91|446|92|93|(7:95|96|460|97|98|448|99)(1:102)|103)|112|(1:117)|118|(1:120)|(3:450|121|(1:123))|128|(1:132)|133|(1:139)|140)|(1:142)(3:143|(2:151|152)(4:145|(4:148|(1:150)|151|152)|147|152)|445)|(1:154)|155|(3:157|(1:159)(3:160|(10:162|(1:164)|165|(1:167)|168|(1:170)|171|(1:176)(1:175)|(2:179|(1:181)(2:182|178))(1:178)|445)|(1:184))|(1:186))|(1:188)|189|(2:192|(1:194)(1:195))(1:191)|(1:197)|(2:202|(2:440|441)(1:206))(1:201)|207|(1:209)|210|(1:212)(2:213|(1:215))|(2:217|(1:219))|220|(1:222)|223|(3:225|(1:227)|228)|229|(1:231)|232|233|(3:467|235|(1:243))|244|245|(1:247)(6:248|(3:277|(2:279|280)|281)(3:252|(5:479|254|(2:256|280)(1:(4:258|259|481|260)(1:281))|(1:274)(1:275)|276)(0)|445)|(1:283)(1:284)|285|(5:305|(1:307)(1:308)|(1:310)(1:311)|312|(3:314|(1:316)|317))(5:289|(4:465|291|(1:(1:295)(1:297))(1:293)|304)(0)|(0)(0)|312|(0))|445)|469|318|(1:320)|477|325|458|330|454|335|340|452|341|462|346|347|456|348|(1:350)|351|359|(1:361)|(3:363|(1:365)|366)|367|(4:475|369|(1:371)(1:372)|373)|378|(2:380|(1:382)(1:383))|384|(1:386)|387|(1:389)(1:390)|391|(10:406|407|408|(1:410)(1:411)|412|(6:414|(4:416|(1:418)|419|(1:421))|422|(1:424)|425|(3:427|(1:429)(2:430|(1:432))|433))|434|(1:436)|437|(1:439))(2:393|(2:395|(11:397|406|407|408|(0)(0)|412|(0)|434|(0)|437|(0))(10:398|407|408|(0)(0)|412|(0)|434|(0)|437|(0)))(12:399|(1:401)(1:402)|403|(1:405)|408|(0)(0)|412|(0)|434|(0)|437|(0)))|445|(1:(0))) */
    /* JADX WARN: Can't wrap try/catch for region: R(93:0|2|(1:4)(1:5)|6|(1:8)|464|9|(3:11|(1:13)(1:14)|15)(1:16)|17|473|18|(1:20)|471|25|(1:27)|28|(1:30)|31|(1:33)|38|(1:40)|41|(21:43|(7:45|(1:47)|48|(1:50)(1:51)|(1:55)|56|(1:58))|59|(1:61)(1:62)|63|(1:65)|(1:67)|68|(1:70)|71|(1:73)|74|75|(1:77)|78|(1:80)|81|(1:85)|86|(1:88)|89)(7:90|91|446|92|93|(7:95|96|460|97|98|448|99)(1:102)|103)|112|(1:117)|118|(1:120)|(3:450|121|(1:123))|128|(1:132)|133|(1:139)|140|(1:142)(3:143|(2:151|152)(4:145|(4:148|(1:150)|151|152)|147|152)|445)|(1:154)|155|(3:157|(1:159)(3:160|(10:162|(1:164)|165|(1:167)|168|(1:170)|171|(1:176)(1:175)|(2:179|(1:181)(2:182|178))(1:178)|445)|(1:184))|(1:186))|(1:188)|189|(2:192|(1:194)(1:195))(1:191)|(1:197)|(2:202|(2:440|441)(1:206))(1:201)|207|(1:209)|210|(1:212)(2:213|(1:215))|(2:217|(1:219))|220|(1:222)|223|(3:225|(1:227)|228)|229|(1:231)|232|233|(3:467|235|(1:243))|244|245|(1:247)(6:248|(3:277|(2:279|280)|281)(3:252|(5:479|254|(2:256|280)(1:(4:258|259|481|260)(1:281))|(1:274)(1:275)|276)(0)|445)|(1:283)(1:284)|285|(5:305|(1:307)(1:308)|(1:310)(1:311)|312|(3:314|(1:316)|317))(5:289|(4:465|291|(1:(1:295)(1:297))(1:293)|304)(0)|(0)(0)|312|(0))|445)|469|318|(1:320)|477|325|458|330|454|335|340|452|341|462|346|347|456|348|(1:350)|351|359|(1:361)|(3:363|(1:365)|366)|367|(4:475|369|(1:371)(1:372)|373)|378|(2:380|(1:382)(1:383))|384|(1:386)|387|(1:389)(1:390)|391|(10:406|407|408|(1:410)(1:411)|412|(6:414|(4:416|(1:418)|419|(1:421))|422|(1:424)|425|(3:427|(1:429)(2:430|(1:432))|433))|434|(1:436)|437|(1:439))(2:393|(2:395|(11:397|406|407|408|(0)(0)|412|(0)|434|(0)|437|(0))(10:398|407|408|(0)(0)|412|(0)|434|(0)|437|(0)))(12:399|(1:401)(1:402)|403|(1:405)|408|(0)(0)|412|(0)|434|(0)|437|(0)))|445|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:322:0x06fc, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:324:0x06fe, code lost:
    
        r9 = new java.lang.StringBuilder("ERROR: could not get uid ");
        r9.append(r0.getMessage());
        com.appsflyer.AFLogger.afErrorLog(r9.toString(), r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:327:0x0721, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:329:0x0723, code lost:
    
        com.appsflyer.AFLogger.afErrorLog("Exception while collecting display language name. ", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:332:0x0736, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:334:0x0738, code lost:
    
        com.appsflyer.AFLogger.afErrorLog("Exception while collecting display language code. ", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:337:0x074b, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:339:0x074d, code lost:
    
        com.appsflyer.AFLogger.afErrorLog("Exception while collecting country name. ", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:343:0x078c, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:345:0x078e, code lost:
    
        com.appsflyer.AFLogger.afErrorLog("Exception while collecting install date. ", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:353:0x0805, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:355:0x0807, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:356:0x0808, code lost:
    
        r12 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:358:0x080b, code lost:
    
        com.appsflyer.AFLogger.afErrorLog("Exception while collecting app version data ", r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:243:0x057d A[Catch: Throwable -> 0x0b0b, TryCatch #11 {Throwable -> 0x0b0b, blocks: (B:9:0x006a, B:11:0x0070, B:15:0x007d, B:17:0x008d, B:18:0x0090, B:20:0x009a, B:25:0x00a9, B:27:0x00c5, B:28:0x00ca, B:30:0x00d2, B:31:0x00d7, B:33:0x00df, B:38:0x00ec, B:40:0x012c, B:41:0x0133, B:43:0x0138, B:45:0x013e, B:47:0x0144, B:48:0x0151, B:50:0x015a, B:53:0x016d, B:55:0x0174, B:56:0x017b, B:58:0x0181, B:51:0x0163, B:59:0x0188, B:61:0x01ab, B:63:0x01b2, B:65:0x01cf, B:67:0x01d6, B:68:0x01db, B:70:0x01e5, B:71:0x0204, B:73:0x0208, B:74:0x020f, B:77:0x0217, B:78:0x021a, B:80:0x0225, B:81:0x022a, B:83:0x0234, B:85:0x023a, B:86:0x023d, B:88:0x0249, B:89:0x0252, B:112:0x02dc, B:115:0x02f4, B:117:0x02fe, B:118:0x0308, B:120:0x0314, B:121:0x0319, B:123:0x0327, B:128:0x0334, B:130:0x033c, B:132:0x0342, B:133:0x0347, B:135:0x0353, B:139:0x035d, B:140:0x0362, B:142:0x036e, B:154:0x03aa, B:155:0x03b3, B:157:0x03c3, B:159:0x03c9, B:186:0x044c, B:160:0x03d1, B:162:0x03d7, B:164:0x03e7, B:165:0x03f9, B:167:0x03ff, B:168:0x0405, B:170:0x040b, B:171:0x0411, B:173:0x0417, B:182:0x042d, B:184:0x043c, B:188:0x0455, B:189:0x045e, B:197:0x047f, B:199:0x048a, B:201:0x0490, B:207:0x04af, B:209:0x04bb, B:210:0x04c0, B:212:0x04c8, B:217:0x04e5, B:219:0x04ec, B:220:0x04f1, B:222:0x04fd, B:223:0x0508, B:225:0x0514, B:227:0x051b, B:228:0x0531, B:229:0x0536, B:231:0x0542, B:232:0x0547, B:243:0x057d, B:244:0x0582, B:247:0x0590, B:318:0x06eb, B:320:0x06f6, B:325:0x0713, B:330:0x0728, B:335:0x073d, B:340:0x0752, B:341:0x0766, B:359:0x0810, B:361:0x082d, B:363:0x0836, B:365:0x083a, B:366:0x084f, B:369:0x085d, B:371:0x0870, B:373:0x087d, B:372:0x0878, B:378:0x0898, B:380:0x08a4, B:382:0x08b3, B:383:0x08b9, B:384:0x08c0, B:386:0x08ca, B:387:0x08e0, B:391:0x0906, B:398:0x091c, B:408:0x0987, B:412:0x0991, B:414:0x09cb, B:416:0x09cf, B:418:0x09dd, B:419:0x0a04, B:421:0x0a0a, B:422:0x0a0f, B:424:0x0a24, B:427:0x0a2e, B:429:0x0a41, B:433:0x0a52, B:430:0x0a45, B:432:0x0a4f, B:434:0x0a55, B:436:0x0a94, B:437:0x0aa4, B:439:0x0af7, B:399:0x0950, B:401:0x095e, B:403:0x096a, B:405:0x0976, B:377:0x0883, B:358:0x080b, B:345:0x078e, B:339:0x074d, B:334:0x0738, B:329:0x0723, B:324:0x06fe, B:248:0x0597, B:250:0x05aa, B:252:0x05b2, B:254:0x05b8, B:283:0x0626, B:285:0x0641, B:287:0x0650, B:289:0x0658, B:291:0x065e, B:310:0x06a3, B:312:0x06be, B:314:0x06c4, B:316:0x06e1, B:317:0x06e6, B:311:0x06b9, B:295:0x066d, B:301:0x0681, B:303:0x0690, B:305:0x0699, B:307:0x069d, B:284:0x063c, B:258:0x05d9, B:260:0x05df, B:269:0x05f1, B:271:0x05fe, B:274:0x0608, B:276:0x0615, B:277:0x061b, B:279:0x061f, B:239:0x056e, B:241:0x0575, B:213:0x04ce, B:215:0x04da, B:202:0x0498, B:204:0x04a4, B:206:0x04aa, B:440:0x0aff, B:195:0x0471, B:143:0x0374, B:145:0x037a, B:152:0x039a, B:150:0x038c, B:127:0x032f, B:90:0x0267, B:92:0x0275, B:95:0x027f, B:97:0x0286, B:99:0x0291, B:103:0x02bc, B:111:0x02d7, B:37:0x00e7, B:24:0x00a4, B:16:0x0088, B:235:0x055e), top: B:464:0x006a, inners: #3, #4, #6, #8, #12, #13, #14, #15, #17, #19, #20, #21 }] */
    /* JADX WARN: Removed duplicated region for block: B:247:0x0590 A[Catch: Throwable -> 0x0b0b, TRY_ENTER, TryCatch #11 {Throwable -> 0x0b0b, blocks: (B:9:0x006a, B:11:0x0070, B:15:0x007d, B:17:0x008d, B:18:0x0090, B:20:0x009a, B:25:0x00a9, B:27:0x00c5, B:28:0x00ca, B:30:0x00d2, B:31:0x00d7, B:33:0x00df, B:38:0x00ec, B:40:0x012c, B:41:0x0133, B:43:0x0138, B:45:0x013e, B:47:0x0144, B:48:0x0151, B:50:0x015a, B:53:0x016d, B:55:0x0174, B:56:0x017b, B:58:0x0181, B:51:0x0163, B:59:0x0188, B:61:0x01ab, B:63:0x01b2, B:65:0x01cf, B:67:0x01d6, B:68:0x01db, B:70:0x01e5, B:71:0x0204, B:73:0x0208, B:74:0x020f, B:77:0x0217, B:78:0x021a, B:80:0x0225, B:81:0x022a, B:83:0x0234, B:85:0x023a, B:86:0x023d, B:88:0x0249, B:89:0x0252, B:112:0x02dc, B:115:0x02f4, B:117:0x02fe, B:118:0x0308, B:120:0x0314, B:121:0x0319, B:123:0x0327, B:128:0x0334, B:130:0x033c, B:132:0x0342, B:133:0x0347, B:135:0x0353, B:139:0x035d, B:140:0x0362, B:142:0x036e, B:154:0x03aa, B:155:0x03b3, B:157:0x03c3, B:159:0x03c9, B:186:0x044c, B:160:0x03d1, B:162:0x03d7, B:164:0x03e7, B:165:0x03f9, B:167:0x03ff, B:168:0x0405, B:170:0x040b, B:171:0x0411, B:173:0x0417, B:182:0x042d, B:184:0x043c, B:188:0x0455, B:189:0x045e, B:197:0x047f, B:199:0x048a, B:201:0x0490, B:207:0x04af, B:209:0x04bb, B:210:0x04c0, B:212:0x04c8, B:217:0x04e5, B:219:0x04ec, B:220:0x04f1, B:222:0x04fd, B:223:0x0508, B:225:0x0514, B:227:0x051b, B:228:0x0531, B:229:0x0536, B:231:0x0542, B:232:0x0547, B:243:0x057d, B:244:0x0582, B:247:0x0590, B:318:0x06eb, B:320:0x06f6, B:325:0x0713, B:330:0x0728, B:335:0x073d, B:340:0x0752, B:341:0x0766, B:359:0x0810, B:361:0x082d, B:363:0x0836, B:365:0x083a, B:366:0x084f, B:369:0x085d, B:371:0x0870, B:373:0x087d, B:372:0x0878, B:378:0x0898, B:380:0x08a4, B:382:0x08b3, B:383:0x08b9, B:384:0x08c0, B:386:0x08ca, B:387:0x08e0, B:391:0x0906, B:398:0x091c, B:408:0x0987, B:412:0x0991, B:414:0x09cb, B:416:0x09cf, B:418:0x09dd, B:419:0x0a04, B:421:0x0a0a, B:422:0x0a0f, B:424:0x0a24, B:427:0x0a2e, B:429:0x0a41, B:433:0x0a52, B:430:0x0a45, B:432:0x0a4f, B:434:0x0a55, B:436:0x0a94, B:437:0x0aa4, B:439:0x0af7, B:399:0x0950, B:401:0x095e, B:403:0x096a, B:405:0x0976, B:377:0x0883, B:358:0x080b, B:345:0x078e, B:339:0x074d, B:334:0x0738, B:329:0x0723, B:324:0x06fe, B:248:0x0597, B:250:0x05aa, B:252:0x05b2, B:254:0x05b8, B:283:0x0626, B:285:0x0641, B:287:0x0650, B:289:0x0658, B:291:0x065e, B:310:0x06a3, B:312:0x06be, B:314:0x06c4, B:316:0x06e1, B:317:0x06e6, B:311:0x06b9, B:295:0x066d, B:301:0x0681, B:303:0x0690, B:305:0x0699, B:307:0x069d, B:284:0x063c, B:258:0x05d9, B:260:0x05df, B:269:0x05f1, B:271:0x05fe, B:274:0x0608, B:276:0x0615, B:277:0x061b, B:279:0x061f, B:239:0x056e, B:241:0x0575, B:213:0x04ce, B:215:0x04da, B:202:0x0498, B:204:0x04a4, B:206:0x04aa, B:440:0x0aff, B:195:0x0471, B:143:0x0374, B:145:0x037a, B:152:0x039a, B:150:0x038c, B:127:0x032f, B:90:0x0267, B:92:0x0275, B:95:0x027f, B:97:0x0286, B:99:0x0291, B:103:0x02bc, B:111:0x02d7, B:37:0x00e7, B:24:0x00a4, B:16:0x0088, B:235:0x055e), top: B:464:0x006a, inners: #3, #4, #6, #8, #12, #13, #14, #15, #17, #19, #20, #21 }] */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0597 A[Catch: Throwable -> 0x0b0b, TryCatch #11 {Throwable -> 0x0b0b, blocks: (B:9:0x006a, B:11:0x0070, B:15:0x007d, B:17:0x008d, B:18:0x0090, B:20:0x009a, B:25:0x00a9, B:27:0x00c5, B:28:0x00ca, B:30:0x00d2, B:31:0x00d7, B:33:0x00df, B:38:0x00ec, B:40:0x012c, B:41:0x0133, B:43:0x0138, B:45:0x013e, B:47:0x0144, B:48:0x0151, B:50:0x015a, B:53:0x016d, B:55:0x0174, B:56:0x017b, B:58:0x0181, B:51:0x0163, B:59:0x0188, B:61:0x01ab, B:63:0x01b2, B:65:0x01cf, B:67:0x01d6, B:68:0x01db, B:70:0x01e5, B:71:0x0204, B:73:0x0208, B:74:0x020f, B:77:0x0217, B:78:0x021a, B:80:0x0225, B:81:0x022a, B:83:0x0234, B:85:0x023a, B:86:0x023d, B:88:0x0249, B:89:0x0252, B:112:0x02dc, B:115:0x02f4, B:117:0x02fe, B:118:0x0308, B:120:0x0314, B:121:0x0319, B:123:0x0327, B:128:0x0334, B:130:0x033c, B:132:0x0342, B:133:0x0347, B:135:0x0353, B:139:0x035d, B:140:0x0362, B:142:0x036e, B:154:0x03aa, B:155:0x03b3, B:157:0x03c3, B:159:0x03c9, B:186:0x044c, B:160:0x03d1, B:162:0x03d7, B:164:0x03e7, B:165:0x03f9, B:167:0x03ff, B:168:0x0405, B:170:0x040b, B:171:0x0411, B:173:0x0417, B:182:0x042d, B:184:0x043c, B:188:0x0455, B:189:0x045e, B:197:0x047f, B:199:0x048a, B:201:0x0490, B:207:0x04af, B:209:0x04bb, B:210:0x04c0, B:212:0x04c8, B:217:0x04e5, B:219:0x04ec, B:220:0x04f1, B:222:0x04fd, B:223:0x0508, B:225:0x0514, B:227:0x051b, B:228:0x0531, B:229:0x0536, B:231:0x0542, B:232:0x0547, B:243:0x057d, B:244:0x0582, B:247:0x0590, B:318:0x06eb, B:320:0x06f6, B:325:0x0713, B:330:0x0728, B:335:0x073d, B:340:0x0752, B:341:0x0766, B:359:0x0810, B:361:0x082d, B:363:0x0836, B:365:0x083a, B:366:0x084f, B:369:0x085d, B:371:0x0870, B:373:0x087d, B:372:0x0878, B:378:0x0898, B:380:0x08a4, B:382:0x08b3, B:383:0x08b9, B:384:0x08c0, B:386:0x08ca, B:387:0x08e0, B:391:0x0906, B:398:0x091c, B:408:0x0987, B:412:0x0991, B:414:0x09cb, B:416:0x09cf, B:418:0x09dd, B:419:0x0a04, B:421:0x0a0a, B:422:0x0a0f, B:424:0x0a24, B:427:0x0a2e, B:429:0x0a41, B:433:0x0a52, B:430:0x0a45, B:432:0x0a4f, B:434:0x0a55, B:436:0x0a94, B:437:0x0aa4, B:439:0x0af7, B:399:0x0950, B:401:0x095e, B:403:0x096a, B:405:0x0976, B:377:0x0883, B:358:0x080b, B:345:0x078e, B:339:0x074d, B:334:0x0738, B:329:0x0723, B:324:0x06fe, B:248:0x0597, B:250:0x05aa, B:252:0x05b2, B:254:0x05b8, B:283:0x0626, B:285:0x0641, B:287:0x0650, B:289:0x0658, B:291:0x065e, B:310:0x06a3, B:312:0x06be, B:314:0x06c4, B:316:0x06e1, B:317:0x06e6, B:311:0x06b9, B:295:0x066d, B:301:0x0681, B:303:0x0690, B:305:0x0699, B:307:0x069d, B:284:0x063c, B:258:0x05d9, B:260:0x05df, B:269:0x05f1, B:271:0x05fe, B:274:0x0608, B:276:0x0615, B:277:0x061b, B:279:0x061f, B:239:0x056e, B:241:0x0575, B:213:0x04ce, B:215:0x04da, B:202:0x0498, B:204:0x04a4, B:206:0x04aa, B:440:0x0aff, B:195:0x0471, B:143:0x0374, B:145:0x037a, B:152:0x039a, B:150:0x038c, B:127:0x032f, B:90:0x0267, B:92:0x0275, B:95:0x027f, B:97:0x0286, B:99:0x0291, B:103:0x02bc, B:111:0x02d7, B:37:0x00e7, B:24:0x00a4, B:16:0x0088, B:235:0x055e), top: B:464:0x006a, inners: #3, #4, #6, #8, #12, #13, #14, #15, #17, #19, #20, #21 }] */
    /* JADX WARN: Removed duplicated region for block: B:274:0x0608 A[Catch: Throwable -> 0x0b0b, TryCatch #11 {Throwable -> 0x0b0b, blocks: (B:9:0x006a, B:11:0x0070, B:15:0x007d, B:17:0x008d, B:18:0x0090, B:20:0x009a, B:25:0x00a9, B:27:0x00c5, B:28:0x00ca, B:30:0x00d2, B:31:0x00d7, B:33:0x00df, B:38:0x00ec, B:40:0x012c, B:41:0x0133, B:43:0x0138, B:45:0x013e, B:47:0x0144, B:48:0x0151, B:50:0x015a, B:53:0x016d, B:55:0x0174, B:56:0x017b, B:58:0x0181, B:51:0x0163, B:59:0x0188, B:61:0x01ab, B:63:0x01b2, B:65:0x01cf, B:67:0x01d6, B:68:0x01db, B:70:0x01e5, B:71:0x0204, B:73:0x0208, B:74:0x020f, B:77:0x0217, B:78:0x021a, B:80:0x0225, B:81:0x022a, B:83:0x0234, B:85:0x023a, B:86:0x023d, B:88:0x0249, B:89:0x0252, B:112:0x02dc, B:115:0x02f4, B:117:0x02fe, B:118:0x0308, B:120:0x0314, B:121:0x0319, B:123:0x0327, B:128:0x0334, B:130:0x033c, B:132:0x0342, B:133:0x0347, B:135:0x0353, B:139:0x035d, B:140:0x0362, B:142:0x036e, B:154:0x03aa, B:155:0x03b3, B:157:0x03c3, B:159:0x03c9, B:186:0x044c, B:160:0x03d1, B:162:0x03d7, B:164:0x03e7, B:165:0x03f9, B:167:0x03ff, B:168:0x0405, B:170:0x040b, B:171:0x0411, B:173:0x0417, B:182:0x042d, B:184:0x043c, B:188:0x0455, B:189:0x045e, B:197:0x047f, B:199:0x048a, B:201:0x0490, B:207:0x04af, B:209:0x04bb, B:210:0x04c0, B:212:0x04c8, B:217:0x04e5, B:219:0x04ec, B:220:0x04f1, B:222:0x04fd, B:223:0x0508, B:225:0x0514, B:227:0x051b, B:228:0x0531, B:229:0x0536, B:231:0x0542, B:232:0x0547, B:243:0x057d, B:244:0x0582, B:247:0x0590, B:318:0x06eb, B:320:0x06f6, B:325:0x0713, B:330:0x0728, B:335:0x073d, B:340:0x0752, B:341:0x0766, B:359:0x0810, B:361:0x082d, B:363:0x0836, B:365:0x083a, B:366:0x084f, B:369:0x085d, B:371:0x0870, B:373:0x087d, B:372:0x0878, B:378:0x0898, B:380:0x08a4, B:382:0x08b3, B:383:0x08b9, B:384:0x08c0, B:386:0x08ca, B:387:0x08e0, B:391:0x0906, B:398:0x091c, B:408:0x0987, B:412:0x0991, B:414:0x09cb, B:416:0x09cf, B:418:0x09dd, B:419:0x0a04, B:421:0x0a0a, B:422:0x0a0f, B:424:0x0a24, B:427:0x0a2e, B:429:0x0a41, B:433:0x0a52, B:430:0x0a45, B:432:0x0a4f, B:434:0x0a55, B:436:0x0a94, B:437:0x0aa4, B:439:0x0af7, B:399:0x0950, B:401:0x095e, B:403:0x096a, B:405:0x0976, B:377:0x0883, B:358:0x080b, B:345:0x078e, B:339:0x074d, B:334:0x0738, B:329:0x0723, B:324:0x06fe, B:248:0x0597, B:250:0x05aa, B:252:0x05b2, B:254:0x05b8, B:283:0x0626, B:285:0x0641, B:287:0x0650, B:289:0x0658, B:291:0x065e, B:310:0x06a3, B:312:0x06be, B:314:0x06c4, B:316:0x06e1, B:317:0x06e6, B:311:0x06b9, B:295:0x066d, B:301:0x0681, B:303:0x0690, B:305:0x0699, B:307:0x069d, B:284:0x063c, B:258:0x05d9, B:260:0x05df, B:269:0x05f1, B:271:0x05fe, B:274:0x0608, B:276:0x0615, B:277:0x061b, B:279:0x061f, B:239:0x056e, B:241:0x0575, B:213:0x04ce, B:215:0x04da, B:202:0x0498, B:204:0x04a4, B:206:0x04aa, B:440:0x0aff, B:195:0x0471, B:143:0x0374, B:145:0x037a, B:152:0x039a, B:150:0x038c, B:127:0x032f, B:90:0x0267, B:92:0x0275, B:95:0x027f, B:97:0x0286, B:99:0x0291, B:103:0x02bc, B:111:0x02d7, B:37:0x00e7, B:24:0x00a4, B:16:0x0088, B:235:0x055e), top: B:464:0x006a, inners: #3, #4, #6, #8, #12, #13, #14, #15, #17, #19, #20, #21 }] */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0614  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x0623  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x06a0  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x06a3 A[Catch: Throwable -> 0x0b0b, TryCatch #11 {Throwable -> 0x0b0b, blocks: (B:9:0x006a, B:11:0x0070, B:15:0x007d, B:17:0x008d, B:18:0x0090, B:20:0x009a, B:25:0x00a9, B:27:0x00c5, B:28:0x00ca, B:30:0x00d2, B:31:0x00d7, B:33:0x00df, B:38:0x00ec, B:40:0x012c, B:41:0x0133, B:43:0x0138, B:45:0x013e, B:47:0x0144, B:48:0x0151, B:50:0x015a, B:53:0x016d, B:55:0x0174, B:56:0x017b, B:58:0x0181, B:51:0x0163, B:59:0x0188, B:61:0x01ab, B:63:0x01b2, B:65:0x01cf, B:67:0x01d6, B:68:0x01db, B:70:0x01e5, B:71:0x0204, B:73:0x0208, B:74:0x020f, B:77:0x0217, B:78:0x021a, B:80:0x0225, B:81:0x022a, B:83:0x0234, B:85:0x023a, B:86:0x023d, B:88:0x0249, B:89:0x0252, B:112:0x02dc, B:115:0x02f4, B:117:0x02fe, B:118:0x0308, B:120:0x0314, B:121:0x0319, B:123:0x0327, B:128:0x0334, B:130:0x033c, B:132:0x0342, B:133:0x0347, B:135:0x0353, B:139:0x035d, B:140:0x0362, B:142:0x036e, B:154:0x03aa, B:155:0x03b3, B:157:0x03c3, B:159:0x03c9, B:186:0x044c, B:160:0x03d1, B:162:0x03d7, B:164:0x03e7, B:165:0x03f9, B:167:0x03ff, B:168:0x0405, B:170:0x040b, B:171:0x0411, B:173:0x0417, B:182:0x042d, B:184:0x043c, B:188:0x0455, B:189:0x045e, B:197:0x047f, B:199:0x048a, B:201:0x0490, B:207:0x04af, B:209:0x04bb, B:210:0x04c0, B:212:0x04c8, B:217:0x04e5, B:219:0x04ec, B:220:0x04f1, B:222:0x04fd, B:223:0x0508, B:225:0x0514, B:227:0x051b, B:228:0x0531, B:229:0x0536, B:231:0x0542, B:232:0x0547, B:243:0x057d, B:244:0x0582, B:247:0x0590, B:318:0x06eb, B:320:0x06f6, B:325:0x0713, B:330:0x0728, B:335:0x073d, B:340:0x0752, B:341:0x0766, B:359:0x0810, B:361:0x082d, B:363:0x0836, B:365:0x083a, B:366:0x084f, B:369:0x085d, B:371:0x0870, B:373:0x087d, B:372:0x0878, B:378:0x0898, B:380:0x08a4, B:382:0x08b3, B:383:0x08b9, B:384:0x08c0, B:386:0x08ca, B:387:0x08e0, B:391:0x0906, B:398:0x091c, B:408:0x0987, B:412:0x0991, B:414:0x09cb, B:416:0x09cf, B:418:0x09dd, B:419:0x0a04, B:421:0x0a0a, B:422:0x0a0f, B:424:0x0a24, B:427:0x0a2e, B:429:0x0a41, B:433:0x0a52, B:430:0x0a45, B:432:0x0a4f, B:434:0x0a55, B:436:0x0a94, B:437:0x0aa4, B:439:0x0af7, B:399:0x0950, B:401:0x095e, B:403:0x096a, B:405:0x0976, B:377:0x0883, B:358:0x080b, B:345:0x078e, B:339:0x074d, B:334:0x0738, B:329:0x0723, B:324:0x06fe, B:248:0x0597, B:250:0x05aa, B:252:0x05b2, B:254:0x05b8, B:283:0x0626, B:285:0x0641, B:287:0x0650, B:289:0x0658, B:291:0x065e, B:310:0x06a3, B:312:0x06be, B:314:0x06c4, B:316:0x06e1, B:317:0x06e6, B:311:0x06b9, B:295:0x066d, B:301:0x0681, B:303:0x0690, B:305:0x0699, B:307:0x069d, B:284:0x063c, B:258:0x05d9, B:260:0x05df, B:269:0x05f1, B:271:0x05fe, B:274:0x0608, B:276:0x0615, B:277:0x061b, B:279:0x061f, B:239:0x056e, B:241:0x0575, B:213:0x04ce, B:215:0x04da, B:202:0x0498, B:204:0x04a4, B:206:0x04aa, B:440:0x0aff, B:195:0x0471, B:143:0x0374, B:145:0x037a, B:152:0x039a, B:150:0x038c, B:127:0x032f, B:90:0x0267, B:92:0x0275, B:95:0x027f, B:97:0x0286, B:99:0x0291, B:103:0x02bc, B:111:0x02d7, B:37:0x00e7, B:24:0x00a4, B:16:0x0088, B:235:0x055e), top: B:464:0x006a, inners: #3, #4, #6, #8, #12, #13, #14, #15, #17, #19, #20, #21 }] */
    /* JADX WARN: Removed duplicated region for block: B:311:0x06b9 A[Catch: Throwable -> 0x0b0b, TryCatch #11 {Throwable -> 0x0b0b, blocks: (B:9:0x006a, B:11:0x0070, B:15:0x007d, B:17:0x008d, B:18:0x0090, B:20:0x009a, B:25:0x00a9, B:27:0x00c5, B:28:0x00ca, B:30:0x00d2, B:31:0x00d7, B:33:0x00df, B:38:0x00ec, B:40:0x012c, B:41:0x0133, B:43:0x0138, B:45:0x013e, B:47:0x0144, B:48:0x0151, B:50:0x015a, B:53:0x016d, B:55:0x0174, B:56:0x017b, B:58:0x0181, B:51:0x0163, B:59:0x0188, B:61:0x01ab, B:63:0x01b2, B:65:0x01cf, B:67:0x01d6, B:68:0x01db, B:70:0x01e5, B:71:0x0204, B:73:0x0208, B:74:0x020f, B:77:0x0217, B:78:0x021a, B:80:0x0225, B:81:0x022a, B:83:0x0234, B:85:0x023a, B:86:0x023d, B:88:0x0249, B:89:0x0252, B:112:0x02dc, B:115:0x02f4, B:117:0x02fe, B:118:0x0308, B:120:0x0314, B:121:0x0319, B:123:0x0327, B:128:0x0334, B:130:0x033c, B:132:0x0342, B:133:0x0347, B:135:0x0353, B:139:0x035d, B:140:0x0362, B:142:0x036e, B:154:0x03aa, B:155:0x03b3, B:157:0x03c3, B:159:0x03c9, B:186:0x044c, B:160:0x03d1, B:162:0x03d7, B:164:0x03e7, B:165:0x03f9, B:167:0x03ff, B:168:0x0405, B:170:0x040b, B:171:0x0411, B:173:0x0417, B:182:0x042d, B:184:0x043c, B:188:0x0455, B:189:0x045e, B:197:0x047f, B:199:0x048a, B:201:0x0490, B:207:0x04af, B:209:0x04bb, B:210:0x04c0, B:212:0x04c8, B:217:0x04e5, B:219:0x04ec, B:220:0x04f1, B:222:0x04fd, B:223:0x0508, B:225:0x0514, B:227:0x051b, B:228:0x0531, B:229:0x0536, B:231:0x0542, B:232:0x0547, B:243:0x057d, B:244:0x0582, B:247:0x0590, B:318:0x06eb, B:320:0x06f6, B:325:0x0713, B:330:0x0728, B:335:0x073d, B:340:0x0752, B:341:0x0766, B:359:0x0810, B:361:0x082d, B:363:0x0836, B:365:0x083a, B:366:0x084f, B:369:0x085d, B:371:0x0870, B:373:0x087d, B:372:0x0878, B:378:0x0898, B:380:0x08a4, B:382:0x08b3, B:383:0x08b9, B:384:0x08c0, B:386:0x08ca, B:387:0x08e0, B:391:0x0906, B:398:0x091c, B:408:0x0987, B:412:0x0991, B:414:0x09cb, B:416:0x09cf, B:418:0x09dd, B:419:0x0a04, B:421:0x0a0a, B:422:0x0a0f, B:424:0x0a24, B:427:0x0a2e, B:429:0x0a41, B:433:0x0a52, B:430:0x0a45, B:432:0x0a4f, B:434:0x0a55, B:436:0x0a94, B:437:0x0aa4, B:439:0x0af7, B:399:0x0950, B:401:0x095e, B:403:0x096a, B:405:0x0976, B:377:0x0883, B:358:0x080b, B:345:0x078e, B:339:0x074d, B:334:0x0738, B:329:0x0723, B:324:0x06fe, B:248:0x0597, B:250:0x05aa, B:252:0x05b2, B:254:0x05b8, B:283:0x0626, B:285:0x0641, B:287:0x0650, B:289:0x0658, B:291:0x065e, B:310:0x06a3, B:312:0x06be, B:314:0x06c4, B:316:0x06e1, B:317:0x06e6, B:311:0x06b9, B:295:0x066d, B:301:0x0681, B:303:0x0690, B:305:0x0699, B:307:0x069d, B:284:0x063c, B:258:0x05d9, B:260:0x05df, B:269:0x05f1, B:271:0x05fe, B:274:0x0608, B:276:0x0615, B:277:0x061b, B:279:0x061f, B:239:0x056e, B:241:0x0575, B:213:0x04ce, B:215:0x04da, B:202:0x0498, B:204:0x04a4, B:206:0x04aa, B:440:0x0aff, B:195:0x0471, B:143:0x0374, B:145:0x037a, B:152:0x039a, B:150:0x038c, B:127:0x032f, B:90:0x0267, B:92:0x0275, B:95:0x027f, B:97:0x0286, B:99:0x0291, B:103:0x02bc, B:111:0x02d7, B:37:0x00e7, B:24:0x00a4, B:16:0x0088, B:235:0x055e), top: B:464:0x006a, inners: #3, #4, #6, #8, #12, #13, #14, #15, #17, #19, #20, #21 }] */
    /* JADX WARN: Removed duplicated region for block: B:314:0x06c4 A[Catch: Throwable -> 0x0b0b, TryCatch #11 {Throwable -> 0x0b0b, blocks: (B:9:0x006a, B:11:0x0070, B:15:0x007d, B:17:0x008d, B:18:0x0090, B:20:0x009a, B:25:0x00a9, B:27:0x00c5, B:28:0x00ca, B:30:0x00d2, B:31:0x00d7, B:33:0x00df, B:38:0x00ec, B:40:0x012c, B:41:0x0133, B:43:0x0138, B:45:0x013e, B:47:0x0144, B:48:0x0151, B:50:0x015a, B:53:0x016d, B:55:0x0174, B:56:0x017b, B:58:0x0181, B:51:0x0163, B:59:0x0188, B:61:0x01ab, B:63:0x01b2, B:65:0x01cf, B:67:0x01d6, B:68:0x01db, B:70:0x01e5, B:71:0x0204, B:73:0x0208, B:74:0x020f, B:77:0x0217, B:78:0x021a, B:80:0x0225, B:81:0x022a, B:83:0x0234, B:85:0x023a, B:86:0x023d, B:88:0x0249, B:89:0x0252, B:112:0x02dc, B:115:0x02f4, B:117:0x02fe, B:118:0x0308, B:120:0x0314, B:121:0x0319, B:123:0x0327, B:128:0x0334, B:130:0x033c, B:132:0x0342, B:133:0x0347, B:135:0x0353, B:139:0x035d, B:140:0x0362, B:142:0x036e, B:154:0x03aa, B:155:0x03b3, B:157:0x03c3, B:159:0x03c9, B:186:0x044c, B:160:0x03d1, B:162:0x03d7, B:164:0x03e7, B:165:0x03f9, B:167:0x03ff, B:168:0x0405, B:170:0x040b, B:171:0x0411, B:173:0x0417, B:182:0x042d, B:184:0x043c, B:188:0x0455, B:189:0x045e, B:197:0x047f, B:199:0x048a, B:201:0x0490, B:207:0x04af, B:209:0x04bb, B:210:0x04c0, B:212:0x04c8, B:217:0x04e5, B:219:0x04ec, B:220:0x04f1, B:222:0x04fd, B:223:0x0508, B:225:0x0514, B:227:0x051b, B:228:0x0531, B:229:0x0536, B:231:0x0542, B:232:0x0547, B:243:0x057d, B:244:0x0582, B:247:0x0590, B:318:0x06eb, B:320:0x06f6, B:325:0x0713, B:330:0x0728, B:335:0x073d, B:340:0x0752, B:341:0x0766, B:359:0x0810, B:361:0x082d, B:363:0x0836, B:365:0x083a, B:366:0x084f, B:369:0x085d, B:371:0x0870, B:373:0x087d, B:372:0x0878, B:378:0x0898, B:380:0x08a4, B:382:0x08b3, B:383:0x08b9, B:384:0x08c0, B:386:0x08ca, B:387:0x08e0, B:391:0x0906, B:398:0x091c, B:408:0x0987, B:412:0x0991, B:414:0x09cb, B:416:0x09cf, B:418:0x09dd, B:419:0x0a04, B:421:0x0a0a, B:422:0x0a0f, B:424:0x0a24, B:427:0x0a2e, B:429:0x0a41, B:433:0x0a52, B:430:0x0a45, B:432:0x0a4f, B:434:0x0a55, B:436:0x0a94, B:437:0x0aa4, B:439:0x0af7, B:399:0x0950, B:401:0x095e, B:403:0x096a, B:405:0x0976, B:377:0x0883, B:358:0x080b, B:345:0x078e, B:339:0x074d, B:334:0x0738, B:329:0x0723, B:324:0x06fe, B:248:0x0597, B:250:0x05aa, B:252:0x05b2, B:254:0x05b8, B:283:0x0626, B:285:0x0641, B:287:0x0650, B:289:0x0658, B:291:0x065e, B:310:0x06a3, B:312:0x06be, B:314:0x06c4, B:316:0x06e1, B:317:0x06e6, B:311:0x06b9, B:295:0x066d, B:301:0x0681, B:303:0x0690, B:305:0x0699, B:307:0x069d, B:284:0x063c, B:258:0x05d9, B:260:0x05df, B:269:0x05f1, B:271:0x05fe, B:274:0x0608, B:276:0x0615, B:277:0x061b, B:279:0x061f, B:239:0x056e, B:241:0x0575, B:213:0x04ce, B:215:0x04da, B:202:0x0498, B:204:0x04a4, B:206:0x04aa, B:440:0x0aff, B:195:0x0471, B:143:0x0374, B:145:0x037a, B:152:0x039a, B:150:0x038c, B:127:0x032f, B:90:0x0267, B:92:0x0275, B:95:0x027f, B:97:0x0286, B:99:0x0291, B:103:0x02bc, B:111:0x02d7, B:37:0x00e7, B:24:0x00a4, B:16:0x0088, B:235:0x055e), top: B:464:0x006a, inners: #3, #4, #6, #8, #12, #13, #14, #15, #17, #19, #20, #21 }] */
    /* JADX WARN: Removed duplicated region for block: B:320:0x06f6 A[Catch: Exception -> 0x06fc, Throwable -> 0x0b0b, TRY_LEAVE, TryCatch #14 {Exception -> 0x06fc, blocks: (B:318:0x06eb, B:320:0x06f6), top: B:469:0x06eb, outer: #11 }] */
    /* JADX WARN: Removed duplicated region for block: B:350:0x07ab A[Catch: Throwable -> 0x0805, TryCatch #7 {Throwable -> 0x0805, blocks: (B:348:0x07a3, B:350:0x07ab, B:351:0x07bd), top: B:456:0x07a3 }] */
    /* JADX WARN: Removed duplicated region for block: B:361:0x082d A[Catch: Throwable -> 0x0b0b, TryCatch #11 {Throwable -> 0x0b0b, blocks: (B:9:0x006a, B:11:0x0070, B:15:0x007d, B:17:0x008d, B:18:0x0090, B:20:0x009a, B:25:0x00a9, B:27:0x00c5, B:28:0x00ca, B:30:0x00d2, B:31:0x00d7, B:33:0x00df, B:38:0x00ec, B:40:0x012c, B:41:0x0133, B:43:0x0138, B:45:0x013e, B:47:0x0144, B:48:0x0151, B:50:0x015a, B:53:0x016d, B:55:0x0174, B:56:0x017b, B:58:0x0181, B:51:0x0163, B:59:0x0188, B:61:0x01ab, B:63:0x01b2, B:65:0x01cf, B:67:0x01d6, B:68:0x01db, B:70:0x01e5, B:71:0x0204, B:73:0x0208, B:74:0x020f, B:77:0x0217, B:78:0x021a, B:80:0x0225, B:81:0x022a, B:83:0x0234, B:85:0x023a, B:86:0x023d, B:88:0x0249, B:89:0x0252, B:112:0x02dc, B:115:0x02f4, B:117:0x02fe, B:118:0x0308, B:120:0x0314, B:121:0x0319, B:123:0x0327, B:128:0x0334, B:130:0x033c, B:132:0x0342, B:133:0x0347, B:135:0x0353, B:139:0x035d, B:140:0x0362, B:142:0x036e, B:154:0x03aa, B:155:0x03b3, B:157:0x03c3, B:159:0x03c9, B:186:0x044c, B:160:0x03d1, B:162:0x03d7, B:164:0x03e7, B:165:0x03f9, B:167:0x03ff, B:168:0x0405, B:170:0x040b, B:171:0x0411, B:173:0x0417, B:182:0x042d, B:184:0x043c, B:188:0x0455, B:189:0x045e, B:197:0x047f, B:199:0x048a, B:201:0x0490, B:207:0x04af, B:209:0x04bb, B:210:0x04c0, B:212:0x04c8, B:217:0x04e5, B:219:0x04ec, B:220:0x04f1, B:222:0x04fd, B:223:0x0508, B:225:0x0514, B:227:0x051b, B:228:0x0531, B:229:0x0536, B:231:0x0542, B:232:0x0547, B:243:0x057d, B:244:0x0582, B:247:0x0590, B:318:0x06eb, B:320:0x06f6, B:325:0x0713, B:330:0x0728, B:335:0x073d, B:340:0x0752, B:341:0x0766, B:359:0x0810, B:361:0x082d, B:363:0x0836, B:365:0x083a, B:366:0x084f, B:369:0x085d, B:371:0x0870, B:373:0x087d, B:372:0x0878, B:378:0x0898, B:380:0x08a4, B:382:0x08b3, B:383:0x08b9, B:384:0x08c0, B:386:0x08ca, B:387:0x08e0, B:391:0x0906, B:398:0x091c, B:408:0x0987, B:412:0x0991, B:414:0x09cb, B:416:0x09cf, B:418:0x09dd, B:419:0x0a04, B:421:0x0a0a, B:422:0x0a0f, B:424:0x0a24, B:427:0x0a2e, B:429:0x0a41, B:433:0x0a52, B:430:0x0a45, B:432:0x0a4f, B:434:0x0a55, B:436:0x0a94, B:437:0x0aa4, B:439:0x0af7, B:399:0x0950, B:401:0x095e, B:403:0x096a, B:405:0x0976, B:377:0x0883, B:358:0x080b, B:345:0x078e, B:339:0x074d, B:334:0x0738, B:329:0x0723, B:324:0x06fe, B:248:0x0597, B:250:0x05aa, B:252:0x05b2, B:254:0x05b8, B:283:0x0626, B:285:0x0641, B:287:0x0650, B:289:0x0658, B:291:0x065e, B:310:0x06a3, B:312:0x06be, B:314:0x06c4, B:316:0x06e1, B:317:0x06e6, B:311:0x06b9, B:295:0x066d, B:301:0x0681, B:303:0x0690, B:305:0x0699, B:307:0x069d, B:284:0x063c, B:258:0x05d9, B:260:0x05df, B:269:0x05f1, B:271:0x05fe, B:274:0x0608, B:276:0x0615, B:277:0x061b, B:279:0x061f, B:239:0x056e, B:241:0x0575, B:213:0x04ce, B:215:0x04da, B:202:0x0498, B:204:0x04a4, B:206:0x04aa, B:440:0x0aff, B:195:0x0471, B:143:0x0374, B:145:0x037a, B:152:0x039a, B:150:0x038c, B:127:0x032f, B:90:0x0267, B:92:0x0275, B:95:0x027f, B:97:0x0286, B:99:0x0291, B:103:0x02bc, B:111:0x02d7, B:37:0x00e7, B:24:0x00a4, B:16:0x0088, B:235:0x055e), top: B:464:0x006a, inners: #3, #4, #6, #8, #12, #13, #14, #15, #17, #19, #20, #21 }] */
    /* JADX WARN: Removed duplicated region for block: B:363:0x0836 A[Catch: Throwable -> 0x0b0b, TryCatch #11 {Throwable -> 0x0b0b, blocks: (B:9:0x006a, B:11:0x0070, B:15:0x007d, B:17:0x008d, B:18:0x0090, B:20:0x009a, B:25:0x00a9, B:27:0x00c5, B:28:0x00ca, B:30:0x00d2, B:31:0x00d7, B:33:0x00df, B:38:0x00ec, B:40:0x012c, B:41:0x0133, B:43:0x0138, B:45:0x013e, B:47:0x0144, B:48:0x0151, B:50:0x015a, B:53:0x016d, B:55:0x0174, B:56:0x017b, B:58:0x0181, B:51:0x0163, B:59:0x0188, B:61:0x01ab, B:63:0x01b2, B:65:0x01cf, B:67:0x01d6, B:68:0x01db, B:70:0x01e5, B:71:0x0204, B:73:0x0208, B:74:0x020f, B:77:0x0217, B:78:0x021a, B:80:0x0225, B:81:0x022a, B:83:0x0234, B:85:0x023a, B:86:0x023d, B:88:0x0249, B:89:0x0252, B:112:0x02dc, B:115:0x02f4, B:117:0x02fe, B:118:0x0308, B:120:0x0314, B:121:0x0319, B:123:0x0327, B:128:0x0334, B:130:0x033c, B:132:0x0342, B:133:0x0347, B:135:0x0353, B:139:0x035d, B:140:0x0362, B:142:0x036e, B:154:0x03aa, B:155:0x03b3, B:157:0x03c3, B:159:0x03c9, B:186:0x044c, B:160:0x03d1, B:162:0x03d7, B:164:0x03e7, B:165:0x03f9, B:167:0x03ff, B:168:0x0405, B:170:0x040b, B:171:0x0411, B:173:0x0417, B:182:0x042d, B:184:0x043c, B:188:0x0455, B:189:0x045e, B:197:0x047f, B:199:0x048a, B:201:0x0490, B:207:0x04af, B:209:0x04bb, B:210:0x04c0, B:212:0x04c8, B:217:0x04e5, B:219:0x04ec, B:220:0x04f1, B:222:0x04fd, B:223:0x0508, B:225:0x0514, B:227:0x051b, B:228:0x0531, B:229:0x0536, B:231:0x0542, B:232:0x0547, B:243:0x057d, B:244:0x0582, B:247:0x0590, B:318:0x06eb, B:320:0x06f6, B:325:0x0713, B:330:0x0728, B:335:0x073d, B:340:0x0752, B:341:0x0766, B:359:0x0810, B:361:0x082d, B:363:0x0836, B:365:0x083a, B:366:0x084f, B:369:0x085d, B:371:0x0870, B:373:0x087d, B:372:0x0878, B:378:0x0898, B:380:0x08a4, B:382:0x08b3, B:383:0x08b9, B:384:0x08c0, B:386:0x08ca, B:387:0x08e0, B:391:0x0906, B:398:0x091c, B:408:0x0987, B:412:0x0991, B:414:0x09cb, B:416:0x09cf, B:418:0x09dd, B:419:0x0a04, B:421:0x0a0a, B:422:0x0a0f, B:424:0x0a24, B:427:0x0a2e, B:429:0x0a41, B:433:0x0a52, B:430:0x0a45, B:432:0x0a4f, B:434:0x0a55, B:436:0x0a94, B:437:0x0aa4, B:439:0x0af7, B:399:0x0950, B:401:0x095e, B:403:0x096a, B:405:0x0976, B:377:0x0883, B:358:0x080b, B:345:0x078e, B:339:0x074d, B:334:0x0738, B:329:0x0723, B:324:0x06fe, B:248:0x0597, B:250:0x05aa, B:252:0x05b2, B:254:0x05b8, B:283:0x0626, B:285:0x0641, B:287:0x0650, B:289:0x0658, B:291:0x065e, B:310:0x06a3, B:312:0x06be, B:314:0x06c4, B:316:0x06e1, B:317:0x06e6, B:311:0x06b9, B:295:0x066d, B:301:0x0681, B:303:0x0690, B:305:0x0699, B:307:0x069d, B:284:0x063c, B:258:0x05d9, B:260:0x05df, B:269:0x05f1, B:271:0x05fe, B:274:0x0608, B:276:0x0615, B:277:0x061b, B:279:0x061f, B:239:0x056e, B:241:0x0575, B:213:0x04ce, B:215:0x04da, B:202:0x0498, B:204:0x04a4, B:206:0x04aa, B:440:0x0aff, B:195:0x0471, B:143:0x0374, B:145:0x037a, B:152:0x039a, B:150:0x038c, B:127:0x032f, B:90:0x0267, B:92:0x0275, B:95:0x027f, B:97:0x0286, B:99:0x0291, B:103:0x02bc, B:111:0x02d7, B:37:0x00e7, B:24:0x00a4, B:16:0x0088, B:235:0x055e), top: B:464:0x006a, inners: #3, #4, #6, #8, #12, #13, #14, #15, #17, #19, #20, #21 }] */
    /* JADX WARN: Removed duplicated region for block: B:380:0x08a4 A[Catch: Throwable -> 0x0b0b, TryCatch #11 {Throwable -> 0x0b0b, blocks: (B:9:0x006a, B:11:0x0070, B:15:0x007d, B:17:0x008d, B:18:0x0090, B:20:0x009a, B:25:0x00a9, B:27:0x00c5, B:28:0x00ca, B:30:0x00d2, B:31:0x00d7, B:33:0x00df, B:38:0x00ec, B:40:0x012c, B:41:0x0133, B:43:0x0138, B:45:0x013e, B:47:0x0144, B:48:0x0151, B:50:0x015a, B:53:0x016d, B:55:0x0174, B:56:0x017b, B:58:0x0181, B:51:0x0163, B:59:0x0188, B:61:0x01ab, B:63:0x01b2, B:65:0x01cf, B:67:0x01d6, B:68:0x01db, B:70:0x01e5, B:71:0x0204, B:73:0x0208, B:74:0x020f, B:77:0x0217, B:78:0x021a, B:80:0x0225, B:81:0x022a, B:83:0x0234, B:85:0x023a, B:86:0x023d, B:88:0x0249, B:89:0x0252, B:112:0x02dc, B:115:0x02f4, B:117:0x02fe, B:118:0x0308, B:120:0x0314, B:121:0x0319, B:123:0x0327, B:128:0x0334, B:130:0x033c, B:132:0x0342, B:133:0x0347, B:135:0x0353, B:139:0x035d, B:140:0x0362, B:142:0x036e, B:154:0x03aa, B:155:0x03b3, B:157:0x03c3, B:159:0x03c9, B:186:0x044c, B:160:0x03d1, B:162:0x03d7, B:164:0x03e7, B:165:0x03f9, B:167:0x03ff, B:168:0x0405, B:170:0x040b, B:171:0x0411, B:173:0x0417, B:182:0x042d, B:184:0x043c, B:188:0x0455, B:189:0x045e, B:197:0x047f, B:199:0x048a, B:201:0x0490, B:207:0x04af, B:209:0x04bb, B:210:0x04c0, B:212:0x04c8, B:217:0x04e5, B:219:0x04ec, B:220:0x04f1, B:222:0x04fd, B:223:0x0508, B:225:0x0514, B:227:0x051b, B:228:0x0531, B:229:0x0536, B:231:0x0542, B:232:0x0547, B:243:0x057d, B:244:0x0582, B:247:0x0590, B:318:0x06eb, B:320:0x06f6, B:325:0x0713, B:330:0x0728, B:335:0x073d, B:340:0x0752, B:341:0x0766, B:359:0x0810, B:361:0x082d, B:363:0x0836, B:365:0x083a, B:366:0x084f, B:369:0x085d, B:371:0x0870, B:373:0x087d, B:372:0x0878, B:378:0x0898, B:380:0x08a4, B:382:0x08b3, B:383:0x08b9, B:384:0x08c0, B:386:0x08ca, B:387:0x08e0, B:391:0x0906, B:398:0x091c, B:408:0x0987, B:412:0x0991, B:414:0x09cb, B:416:0x09cf, B:418:0x09dd, B:419:0x0a04, B:421:0x0a0a, B:422:0x0a0f, B:424:0x0a24, B:427:0x0a2e, B:429:0x0a41, B:433:0x0a52, B:430:0x0a45, B:432:0x0a4f, B:434:0x0a55, B:436:0x0a94, B:437:0x0aa4, B:439:0x0af7, B:399:0x0950, B:401:0x095e, B:403:0x096a, B:405:0x0976, B:377:0x0883, B:358:0x080b, B:345:0x078e, B:339:0x074d, B:334:0x0738, B:329:0x0723, B:324:0x06fe, B:248:0x0597, B:250:0x05aa, B:252:0x05b2, B:254:0x05b8, B:283:0x0626, B:285:0x0641, B:287:0x0650, B:289:0x0658, B:291:0x065e, B:310:0x06a3, B:312:0x06be, B:314:0x06c4, B:316:0x06e1, B:317:0x06e6, B:311:0x06b9, B:295:0x066d, B:301:0x0681, B:303:0x0690, B:305:0x0699, B:307:0x069d, B:284:0x063c, B:258:0x05d9, B:260:0x05df, B:269:0x05f1, B:271:0x05fe, B:274:0x0608, B:276:0x0615, B:277:0x061b, B:279:0x061f, B:239:0x056e, B:241:0x0575, B:213:0x04ce, B:215:0x04da, B:202:0x0498, B:204:0x04a4, B:206:0x04aa, B:440:0x0aff, B:195:0x0471, B:143:0x0374, B:145:0x037a, B:152:0x039a, B:150:0x038c, B:127:0x032f, B:90:0x0267, B:92:0x0275, B:95:0x027f, B:97:0x0286, B:99:0x0291, B:103:0x02bc, B:111:0x02d7, B:37:0x00e7, B:24:0x00a4, B:16:0x0088, B:235:0x055e), top: B:464:0x006a, inners: #3, #4, #6, #8, #12, #13, #14, #15, #17, #19, #20, #21 }] */
    /* JADX WARN: Removed duplicated region for block: B:386:0x08ca A[Catch: Throwable -> 0x0b0b, TryCatch #11 {Throwable -> 0x0b0b, blocks: (B:9:0x006a, B:11:0x0070, B:15:0x007d, B:17:0x008d, B:18:0x0090, B:20:0x009a, B:25:0x00a9, B:27:0x00c5, B:28:0x00ca, B:30:0x00d2, B:31:0x00d7, B:33:0x00df, B:38:0x00ec, B:40:0x012c, B:41:0x0133, B:43:0x0138, B:45:0x013e, B:47:0x0144, B:48:0x0151, B:50:0x015a, B:53:0x016d, B:55:0x0174, B:56:0x017b, B:58:0x0181, B:51:0x0163, B:59:0x0188, B:61:0x01ab, B:63:0x01b2, B:65:0x01cf, B:67:0x01d6, B:68:0x01db, B:70:0x01e5, B:71:0x0204, B:73:0x0208, B:74:0x020f, B:77:0x0217, B:78:0x021a, B:80:0x0225, B:81:0x022a, B:83:0x0234, B:85:0x023a, B:86:0x023d, B:88:0x0249, B:89:0x0252, B:112:0x02dc, B:115:0x02f4, B:117:0x02fe, B:118:0x0308, B:120:0x0314, B:121:0x0319, B:123:0x0327, B:128:0x0334, B:130:0x033c, B:132:0x0342, B:133:0x0347, B:135:0x0353, B:139:0x035d, B:140:0x0362, B:142:0x036e, B:154:0x03aa, B:155:0x03b3, B:157:0x03c3, B:159:0x03c9, B:186:0x044c, B:160:0x03d1, B:162:0x03d7, B:164:0x03e7, B:165:0x03f9, B:167:0x03ff, B:168:0x0405, B:170:0x040b, B:171:0x0411, B:173:0x0417, B:182:0x042d, B:184:0x043c, B:188:0x0455, B:189:0x045e, B:197:0x047f, B:199:0x048a, B:201:0x0490, B:207:0x04af, B:209:0x04bb, B:210:0x04c0, B:212:0x04c8, B:217:0x04e5, B:219:0x04ec, B:220:0x04f1, B:222:0x04fd, B:223:0x0508, B:225:0x0514, B:227:0x051b, B:228:0x0531, B:229:0x0536, B:231:0x0542, B:232:0x0547, B:243:0x057d, B:244:0x0582, B:247:0x0590, B:318:0x06eb, B:320:0x06f6, B:325:0x0713, B:330:0x0728, B:335:0x073d, B:340:0x0752, B:341:0x0766, B:359:0x0810, B:361:0x082d, B:363:0x0836, B:365:0x083a, B:366:0x084f, B:369:0x085d, B:371:0x0870, B:373:0x087d, B:372:0x0878, B:378:0x0898, B:380:0x08a4, B:382:0x08b3, B:383:0x08b9, B:384:0x08c0, B:386:0x08ca, B:387:0x08e0, B:391:0x0906, B:398:0x091c, B:408:0x0987, B:412:0x0991, B:414:0x09cb, B:416:0x09cf, B:418:0x09dd, B:419:0x0a04, B:421:0x0a0a, B:422:0x0a0f, B:424:0x0a24, B:427:0x0a2e, B:429:0x0a41, B:433:0x0a52, B:430:0x0a45, B:432:0x0a4f, B:434:0x0a55, B:436:0x0a94, B:437:0x0aa4, B:439:0x0af7, B:399:0x0950, B:401:0x095e, B:403:0x096a, B:405:0x0976, B:377:0x0883, B:358:0x080b, B:345:0x078e, B:339:0x074d, B:334:0x0738, B:329:0x0723, B:324:0x06fe, B:248:0x0597, B:250:0x05aa, B:252:0x05b2, B:254:0x05b8, B:283:0x0626, B:285:0x0641, B:287:0x0650, B:289:0x0658, B:291:0x065e, B:310:0x06a3, B:312:0x06be, B:314:0x06c4, B:316:0x06e1, B:317:0x06e6, B:311:0x06b9, B:295:0x066d, B:301:0x0681, B:303:0x0690, B:305:0x0699, B:307:0x069d, B:284:0x063c, B:258:0x05d9, B:260:0x05df, B:269:0x05f1, B:271:0x05fe, B:274:0x0608, B:276:0x0615, B:277:0x061b, B:279:0x061f, B:239:0x056e, B:241:0x0575, B:213:0x04ce, B:215:0x04da, B:202:0x0498, B:204:0x04a4, B:206:0x04aa, B:440:0x0aff, B:195:0x0471, B:143:0x0374, B:145:0x037a, B:152:0x039a, B:150:0x038c, B:127:0x032f, B:90:0x0267, B:92:0x0275, B:95:0x027f, B:97:0x0286, B:99:0x0291, B:103:0x02bc, B:111:0x02d7, B:37:0x00e7, B:24:0x00a4, B:16:0x0088, B:235:0x055e), top: B:464:0x006a, inners: #3, #4, #6, #8, #12, #13, #14, #15, #17, #19, #20, #21 }] */
    /* JADX WARN: Removed duplicated region for block: B:389:0x0903  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x0905  */
    /* JADX WARN: Removed duplicated region for block: B:393:0x0915  */
    /* JADX WARN: Removed duplicated region for block: B:406:0x0984  */
    /* JADX WARN: Removed duplicated region for block: B:410:0x098f  */
    /* JADX WARN: Removed duplicated region for block: B:411:0x0990  */
    /* JADX WARN: Removed duplicated region for block: B:414:0x09cb A[Catch: Throwable -> 0x0b0b, TryCatch #11 {Throwable -> 0x0b0b, blocks: (B:9:0x006a, B:11:0x0070, B:15:0x007d, B:17:0x008d, B:18:0x0090, B:20:0x009a, B:25:0x00a9, B:27:0x00c5, B:28:0x00ca, B:30:0x00d2, B:31:0x00d7, B:33:0x00df, B:38:0x00ec, B:40:0x012c, B:41:0x0133, B:43:0x0138, B:45:0x013e, B:47:0x0144, B:48:0x0151, B:50:0x015a, B:53:0x016d, B:55:0x0174, B:56:0x017b, B:58:0x0181, B:51:0x0163, B:59:0x0188, B:61:0x01ab, B:63:0x01b2, B:65:0x01cf, B:67:0x01d6, B:68:0x01db, B:70:0x01e5, B:71:0x0204, B:73:0x0208, B:74:0x020f, B:77:0x0217, B:78:0x021a, B:80:0x0225, B:81:0x022a, B:83:0x0234, B:85:0x023a, B:86:0x023d, B:88:0x0249, B:89:0x0252, B:112:0x02dc, B:115:0x02f4, B:117:0x02fe, B:118:0x0308, B:120:0x0314, B:121:0x0319, B:123:0x0327, B:128:0x0334, B:130:0x033c, B:132:0x0342, B:133:0x0347, B:135:0x0353, B:139:0x035d, B:140:0x0362, B:142:0x036e, B:154:0x03aa, B:155:0x03b3, B:157:0x03c3, B:159:0x03c9, B:186:0x044c, B:160:0x03d1, B:162:0x03d7, B:164:0x03e7, B:165:0x03f9, B:167:0x03ff, B:168:0x0405, B:170:0x040b, B:171:0x0411, B:173:0x0417, B:182:0x042d, B:184:0x043c, B:188:0x0455, B:189:0x045e, B:197:0x047f, B:199:0x048a, B:201:0x0490, B:207:0x04af, B:209:0x04bb, B:210:0x04c0, B:212:0x04c8, B:217:0x04e5, B:219:0x04ec, B:220:0x04f1, B:222:0x04fd, B:223:0x0508, B:225:0x0514, B:227:0x051b, B:228:0x0531, B:229:0x0536, B:231:0x0542, B:232:0x0547, B:243:0x057d, B:244:0x0582, B:247:0x0590, B:318:0x06eb, B:320:0x06f6, B:325:0x0713, B:330:0x0728, B:335:0x073d, B:340:0x0752, B:341:0x0766, B:359:0x0810, B:361:0x082d, B:363:0x0836, B:365:0x083a, B:366:0x084f, B:369:0x085d, B:371:0x0870, B:373:0x087d, B:372:0x0878, B:378:0x0898, B:380:0x08a4, B:382:0x08b3, B:383:0x08b9, B:384:0x08c0, B:386:0x08ca, B:387:0x08e0, B:391:0x0906, B:398:0x091c, B:408:0x0987, B:412:0x0991, B:414:0x09cb, B:416:0x09cf, B:418:0x09dd, B:419:0x0a04, B:421:0x0a0a, B:422:0x0a0f, B:424:0x0a24, B:427:0x0a2e, B:429:0x0a41, B:433:0x0a52, B:430:0x0a45, B:432:0x0a4f, B:434:0x0a55, B:436:0x0a94, B:437:0x0aa4, B:439:0x0af7, B:399:0x0950, B:401:0x095e, B:403:0x096a, B:405:0x0976, B:377:0x0883, B:358:0x080b, B:345:0x078e, B:339:0x074d, B:334:0x0738, B:329:0x0723, B:324:0x06fe, B:248:0x0597, B:250:0x05aa, B:252:0x05b2, B:254:0x05b8, B:283:0x0626, B:285:0x0641, B:287:0x0650, B:289:0x0658, B:291:0x065e, B:310:0x06a3, B:312:0x06be, B:314:0x06c4, B:316:0x06e1, B:317:0x06e6, B:311:0x06b9, B:295:0x066d, B:301:0x0681, B:303:0x0690, B:305:0x0699, B:307:0x069d, B:284:0x063c, B:258:0x05d9, B:260:0x05df, B:269:0x05f1, B:271:0x05fe, B:274:0x0608, B:276:0x0615, B:277:0x061b, B:279:0x061f, B:239:0x056e, B:241:0x0575, B:213:0x04ce, B:215:0x04da, B:202:0x0498, B:204:0x04a4, B:206:0x04aa, B:440:0x0aff, B:195:0x0471, B:143:0x0374, B:145:0x037a, B:152:0x039a, B:150:0x038c, B:127:0x032f, B:90:0x0267, B:92:0x0275, B:95:0x027f, B:97:0x0286, B:99:0x0291, B:103:0x02bc, B:111:0x02d7, B:37:0x00e7, B:24:0x00a4, B:16:0x0088, B:235:0x055e), top: B:464:0x006a, inners: #3, #4, #6, #8, #12, #13, #14, #15, #17, #19, #20, #21 }] */
    /* JADX WARN: Removed duplicated region for block: B:436:0x0a94 A[Catch: Throwable -> 0x0b0b, TryCatch #11 {Throwable -> 0x0b0b, blocks: (B:9:0x006a, B:11:0x0070, B:15:0x007d, B:17:0x008d, B:18:0x0090, B:20:0x009a, B:25:0x00a9, B:27:0x00c5, B:28:0x00ca, B:30:0x00d2, B:31:0x00d7, B:33:0x00df, B:38:0x00ec, B:40:0x012c, B:41:0x0133, B:43:0x0138, B:45:0x013e, B:47:0x0144, B:48:0x0151, B:50:0x015a, B:53:0x016d, B:55:0x0174, B:56:0x017b, B:58:0x0181, B:51:0x0163, B:59:0x0188, B:61:0x01ab, B:63:0x01b2, B:65:0x01cf, B:67:0x01d6, B:68:0x01db, B:70:0x01e5, B:71:0x0204, B:73:0x0208, B:74:0x020f, B:77:0x0217, B:78:0x021a, B:80:0x0225, B:81:0x022a, B:83:0x0234, B:85:0x023a, B:86:0x023d, B:88:0x0249, B:89:0x0252, B:112:0x02dc, B:115:0x02f4, B:117:0x02fe, B:118:0x0308, B:120:0x0314, B:121:0x0319, B:123:0x0327, B:128:0x0334, B:130:0x033c, B:132:0x0342, B:133:0x0347, B:135:0x0353, B:139:0x035d, B:140:0x0362, B:142:0x036e, B:154:0x03aa, B:155:0x03b3, B:157:0x03c3, B:159:0x03c9, B:186:0x044c, B:160:0x03d1, B:162:0x03d7, B:164:0x03e7, B:165:0x03f9, B:167:0x03ff, B:168:0x0405, B:170:0x040b, B:171:0x0411, B:173:0x0417, B:182:0x042d, B:184:0x043c, B:188:0x0455, B:189:0x045e, B:197:0x047f, B:199:0x048a, B:201:0x0490, B:207:0x04af, B:209:0x04bb, B:210:0x04c0, B:212:0x04c8, B:217:0x04e5, B:219:0x04ec, B:220:0x04f1, B:222:0x04fd, B:223:0x0508, B:225:0x0514, B:227:0x051b, B:228:0x0531, B:229:0x0536, B:231:0x0542, B:232:0x0547, B:243:0x057d, B:244:0x0582, B:247:0x0590, B:318:0x06eb, B:320:0x06f6, B:325:0x0713, B:330:0x0728, B:335:0x073d, B:340:0x0752, B:341:0x0766, B:359:0x0810, B:361:0x082d, B:363:0x0836, B:365:0x083a, B:366:0x084f, B:369:0x085d, B:371:0x0870, B:373:0x087d, B:372:0x0878, B:378:0x0898, B:380:0x08a4, B:382:0x08b3, B:383:0x08b9, B:384:0x08c0, B:386:0x08ca, B:387:0x08e0, B:391:0x0906, B:398:0x091c, B:408:0x0987, B:412:0x0991, B:414:0x09cb, B:416:0x09cf, B:418:0x09dd, B:419:0x0a04, B:421:0x0a0a, B:422:0x0a0f, B:424:0x0a24, B:427:0x0a2e, B:429:0x0a41, B:433:0x0a52, B:430:0x0a45, B:432:0x0a4f, B:434:0x0a55, B:436:0x0a94, B:437:0x0aa4, B:439:0x0af7, B:399:0x0950, B:401:0x095e, B:403:0x096a, B:405:0x0976, B:377:0x0883, B:358:0x080b, B:345:0x078e, B:339:0x074d, B:334:0x0738, B:329:0x0723, B:324:0x06fe, B:248:0x0597, B:250:0x05aa, B:252:0x05b2, B:254:0x05b8, B:283:0x0626, B:285:0x0641, B:287:0x0650, B:289:0x0658, B:291:0x065e, B:310:0x06a3, B:312:0x06be, B:314:0x06c4, B:316:0x06e1, B:317:0x06e6, B:311:0x06b9, B:295:0x066d, B:301:0x0681, B:303:0x0690, B:305:0x0699, B:307:0x069d, B:284:0x063c, B:258:0x05d9, B:260:0x05df, B:269:0x05f1, B:271:0x05fe, B:274:0x0608, B:276:0x0615, B:277:0x061b, B:279:0x061f, B:239:0x056e, B:241:0x0575, B:213:0x04ce, B:215:0x04da, B:202:0x0498, B:204:0x04a4, B:206:0x04aa, B:440:0x0aff, B:195:0x0471, B:143:0x0374, B:145:0x037a, B:152:0x039a, B:150:0x038c, B:127:0x032f, B:90:0x0267, B:92:0x0275, B:95:0x027f, B:97:0x0286, B:99:0x0291, B:103:0x02bc, B:111:0x02d7, B:37:0x00e7, B:24:0x00a4, B:16:0x0088, B:235:0x055e), top: B:464:0x006a, inners: #3, #4, #6, #8, #12, #13, #14, #15, #17, #19, #20, #21 }] */
    /* JADX WARN: Removed duplicated region for block: B:439:0x0af7 A[Catch: Throwable -> 0x0b0b, TryCatch #11 {Throwable -> 0x0b0b, blocks: (B:9:0x006a, B:11:0x0070, B:15:0x007d, B:17:0x008d, B:18:0x0090, B:20:0x009a, B:25:0x00a9, B:27:0x00c5, B:28:0x00ca, B:30:0x00d2, B:31:0x00d7, B:33:0x00df, B:38:0x00ec, B:40:0x012c, B:41:0x0133, B:43:0x0138, B:45:0x013e, B:47:0x0144, B:48:0x0151, B:50:0x015a, B:53:0x016d, B:55:0x0174, B:56:0x017b, B:58:0x0181, B:51:0x0163, B:59:0x0188, B:61:0x01ab, B:63:0x01b2, B:65:0x01cf, B:67:0x01d6, B:68:0x01db, B:70:0x01e5, B:71:0x0204, B:73:0x0208, B:74:0x020f, B:77:0x0217, B:78:0x021a, B:80:0x0225, B:81:0x022a, B:83:0x0234, B:85:0x023a, B:86:0x023d, B:88:0x0249, B:89:0x0252, B:112:0x02dc, B:115:0x02f4, B:117:0x02fe, B:118:0x0308, B:120:0x0314, B:121:0x0319, B:123:0x0327, B:128:0x0334, B:130:0x033c, B:132:0x0342, B:133:0x0347, B:135:0x0353, B:139:0x035d, B:140:0x0362, B:142:0x036e, B:154:0x03aa, B:155:0x03b3, B:157:0x03c3, B:159:0x03c9, B:186:0x044c, B:160:0x03d1, B:162:0x03d7, B:164:0x03e7, B:165:0x03f9, B:167:0x03ff, B:168:0x0405, B:170:0x040b, B:171:0x0411, B:173:0x0417, B:182:0x042d, B:184:0x043c, B:188:0x0455, B:189:0x045e, B:197:0x047f, B:199:0x048a, B:201:0x0490, B:207:0x04af, B:209:0x04bb, B:210:0x04c0, B:212:0x04c8, B:217:0x04e5, B:219:0x04ec, B:220:0x04f1, B:222:0x04fd, B:223:0x0508, B:225:0x0514, B:227:0x051b, B:228:0x0531, B:229:0x0536, B:231:0x0542, B:232:0x0547, B:243:0x057d, B:244:0x0582, B:247:0x0590, B:318:0x06eb, B:320:0x06f6, B:325:0x0713, B:330:0x0728, B:335:0x073d, B:340:0x0752, B:341:0x0766, B:359:0x0810, B:361:0x082d, B:363:0x0836, B:365:0x083a, B:366:0x084f, B:369:0x085d, B:371:0x0870, B:373:0x087d, B:372:0x0878, B:378:0x0898, B:380:0x08a4, B:382:0x08b3, B:383:0x08b9, B:384:0x08c0, B:386:0x08ca, B:387:0x08e0, B:391:0x0906, B:398:0x091c, B:408:0x0987, B:412:0x0991, B:414:0x09cb, B:416:0x09cf, B:418:0x09dd, B:419:0x0a04, B:421:0x0a0a, B:422:0x0a0f, B:424:0x0a24, B:427:0x0a2e, B:429:0x0a41, B:433:0x0a52, B:430:0x0a45, B:432:0x0a4f, B:434:0x0a55, B:436:0x0a94, B:437:0x0aa4, B:439:0x0af7, B:399:0x0950, B:401:0x095e, B:403:0x096a, B:405:0x0976, B:377:0x0883, B:358:0x080b, B:345:0x078e, B:339:0x074d, B:334:0x0738, B:329:0x0723, B:324:0x06fe, B:248:0x0597, B:250:0x05aa, B:252:0x05b2, B:254:0x05b8, B:283:0x0626, B:285:0x0641, B:287:0x0650, B:289:0x0658, B:291:0x065e, B:310:0x06a3, B:312:0x06be, B:314:0x06c4, B:316:0x06e1, B:317:0x06e6, B:311:0x06b9, B:295:0x066d, B:301:0x0681, B:303:0x0690, B:305:0x0699, B:307:0x069d, B:284:0x063c, B:258:0x05d9, B:260:0x05df, B:269:0x05f1, B:271:0x05fe, B:274:0x0608, B:276:0x0615, B:277:0x061b, B:279:0x061f, B:239:0x056e, B:241:0x0575, B:213:0x04ce, B:215:0x04da, B:202:0x0498, B:204:0x04a4, B:206:0x04aa, B:440:0x0aff, B:195:0x0471, B:143:0x0374, B:145:0x037a, B:152:0x039a, B:150:0x038c, B:127:0x032f, B:90:0x0267, B:92:0x0275, B:95:0x027f, B:97:0x0286, B:99:0x0291, B:103:0x02bc, B:111:0x02d7, B:37:0x00e7, B:24:0x00a4, B:16:0x0088, B:235:0x055e), top: B:464:0x006a, inners: #3, #4, #6, #8, #12, #13, #14, #15, #17, #19, #20, #21 }] */
    /* JADX WARN: Removed duplicated region for block: B:475:0x085d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final java.util.Map<java.lang.String, java.lang.Object> m113(com.appsflyer.AFEvent r28) {
        /*
            Method dump skipped, instruction units count: 2837
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.AppsFlyerLibCore.m113(com.appsflyer.AFEvent):java.util.Map");
    }

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    private static Map<String, Object> m73(Map<String, Object> map) {
        if (map.containsKey(ServerParameters.PERFORMANCE)) {
            return (Map) map.get(ServerParameters.PERFORMANCE);
        }
        HashMap map2 = new HashMap();
        map.put(ServerParameters.PERFORMANCE, map2);
        return map2;
    }

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    public static boolean m65(@NonNull SharedPreferences sharedPreferences) {
        return Boolean.parseBoolean(sharedPreferences.getString("sentSuccessfully", null));
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    private static void m89(Context context, Map<String, Object> map) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager != null) {
            int rotation = windowManager.getDefaultDisplay().getRotation();
            map.put("sc_o", rotation != 0 ? rotation != 1 ? rotation != 2 ? rotation != 3 ? "" : "lr" : "pr" : "l" : "p");
        }
    }

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    private static String m61(Context context) {
        Intent intent;
        String string = null;
        if ((context instanceof Activity) && (intent = ((Activity) context).getIntent()) != null) {
            try {
                Bundle extras = intent.getExtras();
                if (extras != null && (string = extras.getString("af")) != null) {
                    AFLogger.afInfoLog("Push Notification received af payload = ".concat(String.valueOf(string)));
                    extras.remove("af");
                    ((Activity) context).setIntent(intent.putExtras(extras));
                }
            } catch (Throwable th) {
                AFLogger.afErrorLog(th.getMessage(), th);
            }
        }
        return string;
    }

    protected void handleDeepLinkCallback(Context context, Map<String, Object> map, Uri uri) {
        if (!map.containsKey(ServerParameters.DEEP_LINK)) {
            String string = uri.toString();
            if (string == null) {
                string = null;
            } else if (string.matches("fb\\d*?://authorize.*") && string.contains("access_token")) {
                int iIndexOf = string.indexOf(63);
                String strSubstring = iIndexOf == -1 ? "" : string.substring(iIndexOf);
                if (strSubstring.length() != 0) {
                    ArrayList arrayList = new ArrayList();
                    if (strSubstring.contains("&")) {
                        arrayList = new ArrayList(Arrays.asList(strSubstring.split("&")));
                    } else {
                        arrayList.add(strSubstring);
                    }
                    StringBuilder sb = new StringBuilder();
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        String str = (String) it.next();
                        if (str.contains("access_token")) {
                            it.remove();
                        } else {
                            if (sb.length() != 0) {
                                sb.append("&");
                            } else if (!str.startsWith("?")) {
                                sb.append("?");
                            }
                            sb.append(str);
                        }
                    }
                    string = string.replace(strSubstring, sb.toString());
                }
            }
            AFDeepLinkManager aFDeepLinkManager = AFDeepLinkManager.getInstance();
            if (aFDeepLinkManager.contains != null && aFDeepLinkManager.parameters != null && string.contains(aFDeepLinkManager.contains)) {
                Uri.Builder builderBuildUpon = Uri.parse(string).buildUpon();
                Uri.Builder builderBuildUpon2 = Uri.EMPTY.buildUpon();
                for (Map.Entry<String, String> entry : aFDeepLinkManager.parameters.entrySet()) {
                    builderBuildUpon.appendQueryParameter(entry.getKey(), entry.getValue());
                    builderBuildUpon2.appendQueryParameter(entry.getKey(), entry.getValue());
                }
                string = builderBuildUpon.build().toString();
                map.put("appended_query_params", builderBuildUpon2.build().getEncodedQuery());
            }
            map.put(ServerParameters.DEEP_LINK, string);
        }
        final HashMap map2 = new HashMap();
        map2.put("link", uri.toString());
        y yVar = new y(uri, this);
        if (yVar.f352) {
            map.put(ServerParameters.IS_BRANDED, Boolean.TRUE);
        }
        yVar.setConnProvider(new OneLinkHttpTask.HttpsUrlConnectionProvider());
        if (!yVar.m224()) {
            AndroidUtils.m47(context, map2, uri);
            DeepLinkCallbacks.onDeepLinkingSuccess(map2);
        } else {
            yVar.f353 = new y.e() { // from class: com.appsflyer.AppsFlyerLibCore.10
                @Override // com.appsflyer.internal.y.e
                /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
                public final void mo116(String str2) {
                    DeepLinkCallbacks.onDeepLinkingError(str2);
                }

                @Override // com.appsflyer.internal.y.e
                /* JADX INFO: renamed from: ι, reason: contains not printable characters */
                public final void mo117(Map<String, String> map3) {
                    for (String str2 : map3.keySet()) {
                        map2.put(str2, map3.get(str2));
                    }
                    DeepLinkCallbacks.onDeepLinkingSuccess(map2);
                }
            };
            AFExecutor.getInstance().getThreadPoolExecutor().execute(yVar);
        }
    }

    /* JADX INFO: renamed from: І, reason: contains not printable characters */
    private static boolean m106(Context context) {
        try {
            if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == 0) {
                return true;
            }
        } catch (Throwable th) {
            AFLogger.afErrorLog("WARNING:  Google play services is unavailable. ", th);
        }
        try {
            context.getPackageManager().getPackageInfo("com.google.android.gms", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e2) {
            AFLogger.afErrorLog("WARNING:  Google Play Services is unavailable. ", e2);
            return false;
        }
    }

    /* JADX INFO: renamed from: ɹ, reason: contains not printable characters */
    private static boolean m80(Context context) {
        return (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_ANDROID_ID_FORCE_BY_USER, false) || AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_IMEI_FORCE_BY_USER, false)) || !m106(context);
    }

    public boolean isAppsFlyerFirstLaunch(Context context) {
        return !getSharedPreferences(context).contains("appsFlyerCount");
    }

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    private static String m98(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
        } catch (Throwable th) {
            AFLogger.afErrorLog(th.getMessage(), th);
            return null;
        }
    }

    @Nullable
    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    private static String m67(String str, PackageManager packageManager, String str2) {
        Object obj;
        try {
            Bundle bundle = ((PackageItemInfo) packageManager.getApplicationInfo(str2, 128)).metaData;
            if (bundle == null || (obj = bundle.get(str)) == null) {
                return null;
            }
            return obj.toString();
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder("Could not find ");
            sb.append(str);
            sb.append(" value in the manifest");
            AFLogger.afErrorLog(sb.toString(), th);
            return null;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setPreinstallAttribution(String str, String str2, String str3) {
        AFLogger.afDebugLog("setPreinstallAttribution API called");
        JSONObject jSONObject = new JSONObject();
        if (str != null) {
            try {
                jSONObject.put(Constants.URL_MEDIA_SOURCE, str);
            } catch (JSONException e2) {
                AFLogger.afErrorLog(e2.getMessage(), e2);
            }
        }
        if (str2 != null) {
            jSONObject.put(Constants.URL_CAMPAIGN, str2);
        }
        if (str3 != null) {
            jSONObject.put(Constants.URL_SITE_ID, str3);
        }
        if (jSONObject.has(Constants.URL_MEDIA_SOURCE)) {
            AppsFlyerProperties.getInstance().set("preInstallName", jSONObject.toString());
        } else {
            AFLogger.afWarnLog("Cannot set preinstall attribution data without a media source");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0060 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String m62(java.io.File r4, java.lang.String r5) throws java.lang.Throwable {
        /*
            r0 = 0
            java.util.Properties r1 = new java.util.Properties     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L29 java.io.FileNotFoundException -> L41
            r1.<init>()     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L29 java.io.FileNotFoundException -> L41
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L29 java.io.FileNotFoundException -> L41
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L29 java.io.FileNotFoundException -> L41
            r1.load(r2)     // Catch: java.lang.Throwable -> L24 java.io.FileNotFoundException -> L42 java.lang.Throwable -> L5d
            java.lang.String r3 = "Found PreInstall property!"
            com.appsflyer.AFLogger.afInfoLog(r3)     // Catch: java.lang.Throwable -> L24 java.io.FileNotFoundException -> L42 java.lang.Throwable -> L5d
            java.lang.String r4 = r1.getProperty(r5)     // Catch: java.lang.Throwable -> L24 java.io.FileNotFoundException -> L42 java.lang.Throwable -> L5d
            r2.close()     // Catch: java.lang.Throwable -> L1b
            goto L23
        L1b:
            r5 = move-exception
            java.lang.String r0 = r5.getMessage()
            com.appsflyer.AFLogger.afErrorLog(r0, r5)
        L23:
            return r4
        L24:
            r4 = move-exception
            goto L2b
        L26:
            r4 = move-exception
            r2 = r0
            goto L5e
        L29:
            r4 = move-exception
            r2 = r0
        L2b:
            java.lang.String r5 = r4.getMessage()     // Catch: java.lang.Throwable -> L5d
            com.appsflyer.AFLogger.afErrorLog(r5, r4)     // Catch: java.lang.Throwable -> L5d
            if (r2 == 0) goto L5c
            r2.close()     // Catch: java.lang.Throwable -> L38
            goto L5c
        L38:
            r4 = move-exception
            java.lang.String r5 = r4.getMessage()
            com.appsflyer.AFLogger.afErrorLog(r5, r4)
            goto L5c
        L41:
            r2 = r0
        L42:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5d
            java.lang.String r1 = "PreInstall file wasn't found: "
            r5.<init>(r1)     // Catch: java.lang.Throwable -> L5d
            java.lang.String r4 = r4.getAbsolutePath()     // Catch: java.lang.Throwable -> L5d
            r5.append(r4)     // Catch: java.lang.Throwable -> L5d
            java.lang.String r4 = r5.toString()     // Catch: java.lang.Throwable -> L5d
            com.appsflyer.AFLogger.afDebugLog(r4)     // Catch: java.lang.Throwable -> L5d
            if (r2 == 0) goto L5c
            r2.close()     // Catch: java.lang.Throwable -> L38
        L5c:
            return r0
        L5d:
            r4 = move-exception
        L5e:
            if (r2 == 0) goto L6c
            r2.close()     // Catch: java.lang.Throwable -> L64
            goto L6c
        L64:
            r5 = move-exception
            java.lang.String r0 = r5.getMessage()
            com.appsflyer.AFLogger.afErrorLog(r0, r5)
        L6c:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.AppsFlyerLibCore.m62(java.io.File, java.lang.String):java.lang.String");
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    private static boolean m95(File file) {
        return file == null || !file.exists();
    }

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    private static File m60(String str) {
        if (str == null) {
            return null;
        }
        try {
            if (str.trim().length() > 0) {
                return new File(str.trim());
            }
            return null;
        } catch (Throwable th) {
            AFLogger.afErrorLog(th.getMessage(), th);
            return null;
        }
    }

    @Nullable
    public String getConfiguredChannel(Context context) {
        String string = AppsFlyerProperties.getInstance().getString("channel");
        if (string == null) {
            string = context == null ? null : m67("CHANNEL", context.getPackageManager(), context.getPackageName());
        }
        if (string == null || !string.equals("")) {
            return string;
        }
        return null;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public boolean isPreInstalledApp(Context context) {
        try {
        } catch (PackageManager.NameNotFoundException e2) {
            AFLogger.afErrorLog("Could not check if app is pre installed", e2);
        }
        return (context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).flags & 1) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    public static String m66(Context context, String str) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        if (sharedPreferences.contains("CACHED_CHANNEL")) {
            return sharedPreferences.getString("CACHED_CHANNEL", null);
        }
        SharedPreferences.Editor editorEdit = getSharedPreferences(context).edit();
        editorEdit.putString("CACHED_CHANNEL", str);
        editorEdit.apply();
        return str;
    }

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    private String m68(SimpleDateFormat simpleDateFormat, Context context) {
        String str;
        String string = getSharedPreferences(context).getString("appsFlyerFirstInstall", null);
        if (string == null) {
            if (isAppsFlyerFirstLaunch(context)) {
                AFLogger.afDebugLog("AppsFlyer: first launch detected");
                str = simpleDateFormat.format(new Date());
            } else {
                str = "";
            }
            string = str;
            SharedPreferences.Editor editorEdit = getSharedPreferences(context).edit();
            editorEdit.putString("appsFlyerFirstInstall", string);
            editorEdit.apply();
        }
        AFLogger.afInfoLog("AppsFlyer: first launch date: ".concat(String.valueOf(string)));
        return string;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public String getAttributionId(Context context) {
        try {
            return new v(context).m221();
        } catch (Throwable th) {
            AFLogger.afErrorLog("Could not collect facebook attribution id. ", th);
            return null;
        }
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        if (getInstance().f110 == null) {
            getInstance().f110 = context.getApplicationContext().getSharedPreferences("appsflyer-data", 0);
        }
        return getInstance().f110;
    }

    public final int getLaunchCounter(SharedPreferences sharedPreferences, boolean z) {
        return m58(sharedPreferences, "appsFlyerCount", z);
    }

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    private static int m58(SharedPreferences sharedPreferences, String str, boolean z) {
        int i = sharedPreferences.getInt(str, 0);
        if (z) {
            i++;
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putInt(str, i);
            editorEdit.apply();
        }
        if (ac.m168().m178()) {
            ac.m168().m174(String.valueOf(i));
        }
        return i;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public String getAppsFlyerUID(Context context) {
        ac.m168().m175("public_api_call", "getAppsFlyerUID", new String[0]);
        return x.m222(new WeakReference(context));
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void validateAndLogInAppPurchase(Context context, String str, String str2, String str3, String str4, String str5, Map<String, String> map) {
        ac acVarM168 = ac.m168();
        String[] strArr = new String[6];
        strArr[0] = str;
        strArr[1] = str2;
        strArr[2] = str3;
        strArr[3] = str4;
        strArr[4] = str5;
        strArr[5] = map == null ? "" : map.toString();
        acVarM168.m175("public_api_call", "validateAndTrackInAppPurchase", strArr);
        if (!isStopped()) {
            StringBuilder sb = new StringBuilder("Validate in app called with parameters: ");
            sb.append(str3);
            sb.append(" ");
            sb.append(str4);
            sb.append(" ");
            sb.append(str5);
            AFLogger.afInfoLog(sb.toString());
        }
        if (str == null || str4 == null || str2 == null || str5 == null || str3 == null) {
            AppsFlyerInAppPurchaseValidatorListener appsFlyerInAppPurchaseValidatorListener = f80;
            if (appsFlyerInAppPurchaseValidatorListener != null) {
                appsFlyerInAppPurchaseValidatorListener.onValidateInAppFailure("Please provide purchase parameters");
                return;
            }
            return;
        }
        Context applicationContext = context.getApplicationContext();
        String string = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY);
        if (context instanceof Activity) {
            ((Activity) context).getIntent();
        }
        new Thread(new AFValidateInAppPurchase(applicationContext, string, str, str2, str3, str4, str5, map)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    public static void m101(ScheduledExecutorService scheduledExecutorService, Runnable runnable, long j, TimeUnit timeUnit) {
        if (scheduledExecutorService != null) {
            try {
                if (!scheduledExecutorService.isShutdown() && !scheduledExecutorService.isTerminated()) {
                    scheduledExecutorService.schedule(runnable, j, timeUnit);
                    return;
                }
            } catch (RejectedExecutionException e2) {
                AFLogger.afErrorLog("scheduleJob failed with RejectedExecutionException Exception", e2);
                return;
            } catch (Throwable th) {
                AFLogger.afErrorLog("scheduleJob failed with Exception", th);
                return;
            }
        }
        AFLogger.afWarnLog("scheduler is null, shut downed or terminated");
    }

    @Override // com.appsflyer.AppsFlyerLib
    public boolean isStopped() {
        return this.f99;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.Reader] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.Reader] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    @NonNull
    public String readServerResponse(HttpURLConnection httpURLConnection) throws Throwable {
        InputStreamReader inputStreamReader;
        StringBuilder sb = new StringBuilder();
        ?? r1 = 0;
        r1 = 0;
        r1 = 0;
        r1 = 0;
        r1 = 0;
        try {
            try {
                try {
                    InputStream errorStream = httpURLConnection.getErrorStream();
                    if (errorStream == null) {
                        errorStream = httpURLConnection.getInputStream();
                    }
                    inputStreamReader = new InputStreamReader(errorStream);
                    try {
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        r1 = 0;
                        while (true) {
                            try {
                                String line = bufferedReader.readLine();
                                if (line == null) {
                                    break;
                                }
                                sb.append(r1 != 0 ? '\n' : "");
                                sb.append(line);
                                r1 = 1;
                            } catch (Throwable th) {
                                th = th;
                                r1 = bufferedReader;
                                if (r1 != 0) {
                                    try {
                                        r1.close();
                                    } catch (Throwable th2) {
                                        AFLogger.afErrorLog(th2);
                                        throw th;
                                    }
                                }
                                if (inputStreamReader != null) {
                                    inputStreamReader.close();
                                }
                                throw th;
                            }
                        }
                        bufferedReader.close();
                        inputStreamReader.close();
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    inputStreamReader = null;
                }
            } catch (Throwable th5) {
                AFLogger.afErrorLog(th5);
            }
            String string = sb.toString();
            try {
                new JSONObject(string);
                return string;
            } catch (JSONException unused) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("string_response", string);
                    return jSONObject.toString();
                } catch (JSONException unused2) {
                    return new JSONObject().toString();
                }
            }
        } catch (Throwable th6) {
            th = th6;
        }
    }

    /* JADX INFO: renamed from: Ɩ, reason: contains not printable characters */
    private static float m56(Context context) {
        try {
            Intent intentRegisterReceiver = context.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = intentRegisterReceiver.getIntExtra(FirebaseAnalytics.Param.LEVEL, -1);
            int intExtra2 = intentRegisterReceiver.getIntExtra("scale", -1);
            if (intExtra == -1 || intExtra2 == -1) {
                return 50.0f;
            }
            return (intExtra / intExtra2) * 100.0f;
        } catch (Throwable th) {
            AFLogger.afErrorLog(th.getMessage(), th);
            return 1.0f;
        }
    }

    /* JADX INFO: renamed from: Ӏ, reason: contains not printable characters */
    private static boolean m110(Context context) {
        if (context != null) {
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                    for (Network network : connectivityManager.getAllNetworks()) {
                        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
                        if (networkCapabilities.hasTransport(4) && !networkCapabilities.hasCapability(15)) {
                            return true;
                        }
                    }
                    return false;
                } catch (Exception e2) {
                    AFLogger.afErrorLog("Failed collecting ivc data", e2);
                }
            } else if (Build.VERSION.SDK_INT >= 16) {
                ArrayList arrayList = new ArrayList();
                try {
                    for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                        if (networkInterface.isUp()) {
                            arrayList.add(networkInterface.getName());
                        }
                    }
                    return arrayList.contains("tun0");
                } catch (Exception e3) {
                    AFLogger.afErrorLog("Failed collecting ivc data", e3);
                }
            }
        }
        return false;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setLogLevel(@NonNull AFLogger.LogLevel logLevel) {
        boolean z = logLevel.getLevel() > AFLogger.LogLevel.NONE.getLevel();
        ac.m168().m175("public_api_call", "log", String.valueOf(z));
        AppsFlyerProperties.getInstance().set("shouldLog", z);
        AppsFlyerProperties.getInstance().set("logLevel", logLevel.getLevel());
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setMinTimeBetweenSessions(int i) {
        this.f100 = TimeUnit.SECONDS.toMillis(i);
    }

    class a implements Runnable {

        /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
        private AFEvent f136;

        /* synthetic */ a(AppsFlyerLibCore appsFlyerLibCore, AFEvent aFEvent, byte b) {
            this(aFEvent);
        }

        private a(AFEvent aFEvent) {
            this.f136 = aFEvent;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AppsFlyerLibCore appsFlyerLibCore = AppsFlyerLibCore.this;
            AFEvent aFEvent = this.f136;
            aFEvent.f16 = aFEvent.f9.get();
            appsFlyerLibCore.m76(aFEvent);
        }
    }

    class e implements Runnable {

        /* JADX INFO: renamed from: ı, reason: contains not printable characters */
        private final AFEvent f140;

        /* synthetic */ e(AppsFlyerLibCore appsFlyerLibCore, AFEvent aFEvent, byte b) {
            this(aFEvent);
        }

        private e(AFEvent aFEvent) {
            this.f140 = aFEvent.weakContext();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:106:0x025e  */
        /* JADX WARN: Type inference failed for: r1v10, types: [java.io.IOException, java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r1v11 */
        /* JADX WARN: Type inference failed for: r1v12 */
        /* JADX WARN: Type inference failed for: r1v13, types: [java.io.Writer] */
        /* JADX WARN: Type inference failed for: r1v15, types: [java.io.Writer] */
        /* JADX WARN: Type inference failed for: r1v16, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v17 */
        /* JADX WARN: Type inference failed for: r1v18 */
        /* JADX WARN: Type inference failed for: r1v19 */
        /* JADX WARN: Type inference failed for: r1v22 */
        /* JADX WARN: Type inference failed for: r1v4 */
        /* JADX WARN: Type inference failed for: r1v41 */
        /* JADX WARN: Type inference failed for: r1v42 */
        /* JADX WARN: Type inference failed for: r1v43 */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:96:0x0240 -> B:125:0x0243). Please report as a decompilation issue!!! */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void run() throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 623
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.AppsFlyerLibCore.e.run():void");
        }
    }

    /* JADX INFO: renamed from: com.appsflyer.AppsFlyerLibCore$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {

        /* JADX INFO: renamed from: ı, reason: contains not printable characters */
        static final /* synthetic */ int[] f126 = new int[Referrer.State.values().length];

        /* JADX INFO: renamed from: ι, reason: contains not printable characters */
        static final /* synthetic */ int[] f127;

        static {
            try {
                f126[Referrer.State.FINISHED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f126[Referrer.State.STARTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            f127 = new int[AppsFlyerProperties.EmailsCryptType.values().length];
            try {
                f127[AppsFlyerProperties.EmailsCryptType.SHA256.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f127[AppsFlyerProperties.EmailsCryptType.NONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static void setUrl(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String value = entry.getValue();
            String key = entry.getKey();
            byte b = -1;
            switch (key.hashCode()) {
                case -1407250527:
                    if (key.equals("launches")) {
                        b = 2;
                    }
                    break;
                case -1184318185:
                    if (key.equals("inapps")) {
                        b = 3;
                    }
                    break;
                case -1084269027:
                    if (key.equals("conversions")) {
                        b = 0;
                    }
                    break;
                case -690213213:
                    if (key.equals("register")) {
                        b = 4;
                    }
                    break;
                case 3004913:
                    if (key.equals("attr")) {
                        b = 1;
                    }
                    break;
                case 57793177:
                    if (key.equals("adrevenue")) {
                        b = 6;
                    }
                    break;
                case 109757599:
                    if (key.equals("stats")) {
                        b = 5;
                    }
                    break;
            }
            switch (b) {
                case 0:
                    FIRST_LAUNCHES_URL = value;
                    break;
                case 1:
                    REFERRER_TRACKING_URL = value;
                    break;
                case 2:
                    f84 = value;
                    break;
                case 3:
                    f87 = value;
                    break;
                case 4:
                    REGISTER_URL = value;
                    break;
                case 5:
                    f86 = value;
                    break;
                case 6:
                    f85 = value;
                    break;
            }
        }
    }

    public static class InstallAttributionIdFetcher implements Runnable {

        /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
        private static final List<String> f128 = Arrays.asList("googleplay", "playstore", "googleplaystore");

        /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
        @VisibleForTesting
        private static String f129 = "https://%sgcdsdk.%s/install_data/v4.0/";

        /* JADX INFO: renamed from: ı, reason: contains not printable characters */
        private final Application f130;

        /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
        final ScheduledExecutorService f131;

        /* JADX INFO: renamed from: ɹ, reason: contains not printable characters */
        private final AtomicInteger f132;

        /* JADX INFO: renamed from: ι, reason: contains not printable characters */
        private final String f133;

        /* JADX INFO: renamed from: і, reason: contains not printable characters */
        private final int f134;

        /* JADX INFO: renamed from: Ӏ, reason: contains not printable characters */
        private AppsFlyerLibCore f135;

        /* synthetic */ InstallAttributionIdFetcher(AppsFlyerLibCore appsFlyerLibCore, Application application, String str, byte b) {
            this(appsFlyerLibCore, application, str);
        }

        private InstallAttributionIdFetcher(AppsFlyerLibCore appsFlyerLibCore, Application application, String str) {
            this.f131 = AFExecutor.getInstance().m12();
            this.f132 = new AtomicInteger(0);
            this.f135 = appsFlyerLibCore;
            this.f130 = application;
            this.f133 = str;
            this.f134 = 0;
        }

        private InstallAttributionIdFetcher(InstallAttributionIdFetcher installAttributionIdFetcher) {
            this.f131 = AFExecutor.getInstance().m12();
            this.f132 = new AtomicInteger(0);
            this.f135 = installAttributionIdFetcher.f135;
            this.f130 = installAttributionIdFetcher.f130;
            this.f133 = installAttributionIdFetcher.f133;
            this.f134 = installAttributionIdFetcher.f134 + 1;
        }

        public static void setUrl(Map<String, String> map) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if ("gcdsdk".equals(entry.getKey())) {
                    f129 = entry.getValue();
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:32:0x0130 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:44:0x016d A[Catch: all -> 0x0291, Throwable -> 0x0293, TryCatch #5 {Throwable -> 0x0293, all -> 0x0291, blocks: (B:30:0x00f9, B:41:0x014d, B:43:0x015b, B:38:0x013b, B:40:0x013f, B:44:0x016d, B:46:0x019b, B:48:0x01a9, B:50:0x01c3, B:52:0x01c9, B:53:0x01d4, B:56:0x01de, B:58:0x01e4, B:59:0x01f8, B:60:0x0209, B:62:0x020f, B:63:0x0222, B:66:0x0234, B:68:0x023f, B:70:0x0243, B:72:0x024b, B:74:0x025f, B:78:0x026c, B:77:0x0266, B:67:0x023a), top: B:112:0x00f9, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:46:0x019b A[Catch: all -> 0x0291, Throwable -> 0x0293, TryCatch #5 {Throwable -> 0x0293, all -> 0x0291, blocks: (B:30:0x00f9, B:41:0x014d, B:43:0x015b, B:38:0x013b, B:40:0x013f, B:44:0x016d, B:46:0x019b, B:48:0x01a9, B:50:0x01c3, B:52:0x01c9, B:53:0x01d4, B:56:0x01de, B:58:0x01e4, B:59:0x01f8, B:60:0x0209, B:62:0x020f, B:63:0x0222, B:66:0x0234, B:68:0x023f, B:70:0x0243, B:72:0x024b, B:74:0x025f, B:78:0x026c, B:77:0x0266, B:67:0x023a), top: B:112:0x00f9, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:81:0x028d  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 770
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.AppsFlyerLibCore.InstallAttributionIdFetcher.run():void");
        }

        /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
        static /* synthetic */ void m121(Map map) {
            StringBuilder sb = new StringBuilder("[GCD-A02] Calling onConversionDataSuccess with:\n");
            sb.append(map.toString());
            AFLogger.afDebugLog(sb.toString());
            AppsFlyerLibCore.conversionDataListener.onConversionDataSuccess(map);
        }

        /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
        static /* synthetic */ void m120(String str) {
            if (AppsFlyerLibCore.conversionDataListener != null) {
                AFLogger.afDebugLog("[GCD-A02] Calling onConversionFailure with:\n".concat(String.valueOf(str)));
                AppsFlyerLibCore.conversionDataListener.onConversionDataFail(str);
            }
        }
    }

    class d implements Runnable {

        /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
        private WeakReference<Context> f139;

        public d(Context context) {
            this.f139 = null;
            this.f139 = new WeakReference<>(context);
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (AppsFlyerLibCore.this.f112) {
                return;
            }
            AppsFlyerLibCore.this.f108 = System.currentTimeMillis();
            if (this.f139 == null) {
                return;
            }
            AppsFlyerLibCore.this.f112 = true;
            try {
                try {
                    String strM85 = AppsFlyerLibCore.m85(AppsFlyerProperties.AF_KEY);
                    synchronized (this.f139) {
                        u.m217();
                        for (AFFacebookDeferredDeeplink aFFacebookDeferredDeeplink : u.m218(this.f139.get())) {
                            StringBuilder sb = new StringBuilder("resending request: ");
                            sb.append(aFFacebookDeferredDeeplink.f30);
                            AFLogger.afInfoLog(sb.toString());
                            try {
                                long jCurrentTimeMillis = System.currentTimeMillis();
                                long j = Long.parseLong(aFFacebookDeferredDeeplink.f28, 10);
                                AppsFlyerLibCore appsFlyerLibCore = AppsFlyerLibCore.this;
                                CachedEvent cachedEvent = new CachedEvent();
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append(aFFacebookDeferredDeeplink.f30);
                                sb2.append("&isCachedRequest=true&timeincache=");
                                sb2.append((jCurrentTimeMillis - j) / 1000);
                                AFEvent aFEventKey = cachedEvent.urlString(sb2.toString()).post(aFFacebookDeferredDeeplink.m13()).key(strM85);
                                aFEventKey.f9 = this.f139;
                                aFEventKey.f15 = aFFacebookDeferredDeeplink.f28;
                                aFEventKey.f19 = false;
                                AppsFlyerLibCore.m77(appsFlyerLibCore, aFEventKey);
                            } catch (Exception e) {
                                AFLogger.afErrorLog("Failed to resend cached request", e);
                            }
                        }
                    }
                } catch (Exception e2) {
                    AFLogger.afErrorLog("failed to check cache. ", e2);
                }
                AppsFlyerLibCore.this.f112 = false;
                AppsFlyerLibCore.this.f90.shutdown();
                AppsFlyerLibCore.m82(AppsFlyerLibCore.this);
            } catch (Throwable th) {
                AppsFlyerLibCore.this.f112 = false;
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    private void m100(AFEvent aFEvent) {
        if (aFEvent.context() != null) {
            aFEvent.f16 = aFEvent.context().getApplicationContext();
        }
        byte b = 0;
        boolean z = aFEvent.f8 == null;
        if (m64()) {
            AFLogger.afInfoLog("CustomerUserId not set, reporting is disabled", true);
            return;
        }
        if (z) {
            if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.LAUNCH_PROTECT_ENABLED, true)) {
                if (m79()) {
                    AppsFlyerRequestListener requestListener = aFEvent.getRequestListener();
                    if (requestListener != null) {
                        requestListener.onError(RequestError.EVENT_TIMEOUT, RequestErrorMessage.EVENT_TIMEOUT);
                        return;
                    }
                    return;
                }
            } else {
                AFLogger.afInfoLog("Allowing multiple launches within a 5 second time window.");
            }
            this.f96 = System.currentTimeMillis();
        }
        m101(AFExecutor.getInstance().m12(), new a(this, aFEvent.weakContext(), b), 0L, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    private static void m75(Context context, Map<String, ? super String> map) {
        r rVar = r.b.f334;
        r.a aVarM207 = r.m207(context);
        map.put(ServerParameters.NETWORK, aVarM207.f331);
        if (aVarM207.f333 != null) {
            map.put(ServerParameters.OPERATOR, aVarM207.f333);
        }
        if (aVarM207.f332 != null) {
            map.put(ServerParameters.CARRIER, aVarM207.f332);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v11 */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v3, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v5 */
    /* JADX WARN: Type inference failed for: r14v9, types: [java.net.HttpURLConnection] */
    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    private void m90(AFEvent aFEvent) throws Throwable {
        boolean z;
        ?? r14;
        long jCurrentTimeMillis;
        HttpURLConnection httpURLConnection;
        DataOutputStream dataOutputStream;
        URL url = new URL(aFEvent.f17);
        byte[] bArrM9 = aFEvent.m9();
        String strKey = aFEvent.key();
        String str = aFEvent.f15;
        boolean zM10 = aFEvent.m10();
        Context context = aFEvent.context();
        AppsFlyerRequestListener requestListener = aFEvent.getRequestListener();
        boolean z2 = zM10 && conversionDataListener != null;
        if (zM10 && aFEvent.f13 == 1) {
            z = z2;
            r14 = 1;
        } else {
            z = z2;
            r14 = 0;
        }
        if (r14 != 0) {
            if (this.f107 == null) {
                this.f107 = new EventDataCollector(context);
            }
            this.f107.set(ServerParameters.FIRST_LAUNCH_COLLECTION, System.currentTimeMillis() - this.f106);
            jCurrentTimeMillis = System.currentTimeMillis();
        } else {
            jCurrentTimeMillis = 0;
        }
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            try {
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Content-Length", String.valueOf(bArrM9.length));
            } catch (Throwable th) {
                th = th;
                r14 = httpURLConnection;
            }
        } catch (Throwable th2) {
            th = th2;
            r14 = 0;
        }
        try {
            httpURLConnection.setRequestProperty("Content-Type", aFEvent.isEncrypt() ? "application/octet-stream" : "application/json");
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setDoOutput(true);
            try {
                dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            } catch (Throwable th3) {
                th = th3;
                dataOutputStream = null;
            }
            try {
                dataOutputStream.write(bArrM9);
                dataOutputStream.close();
                int responseCode = httpURLConnection.getResponseCode();
                if (r14 != 0) {
                    if (this.f107 == null) {
                        this.f107 = new EventDataCollector(context);
                    }
                    this.f107.set(ServerParameters.FIRST_LAUNCH_NETWORKING, System.currentTimeMillis() - jCurrentTimeMillis);
                }
                String serverResponse = readServerResponse(httpURLConnection);
                ac acVarM168 = ac.m168();
                String string = url.toString();
                String[] strArr = new String[2];
                strArr[0] = String.valueOf(responseCode);
                r14 = httpURLConnection;
                strArr[1] = serverResponse;
                acVarM168.m175("server_response", string, strArr);
                AFLogger.afInfoLog("response code: ".concat(String.valueOf(responseCode)));
                SharedPreferences sharedPreferences = getSharedPreferences(context);
                if (responseCode == 200) {
                    if (context != null && zM10) {
                        this.f103 = System.currentTimeMillis();
                    }
                    if (requestListener != null) {
                        requestListener.onSuccess();
                    }
                    if (str != null) {
                        u.m217();
                        u.m215(str, context);
                    } else {
                        SharedPreferences.Editor editorEdit = getSharedPreferences(context).edit();
                        editorEdit.putString("sentSuccessfully", "true");
                        editorEdit.apply();
                        if (!this.f112 && System.currentTimeMillis() - this.f108 >= 15000 && this.f90 == null) {
                            this.f90 = AFExecutor.getInstance().m12();
                            m101(this.f90, new d(context), 1L, TimeUnit.SECONDS);
                        }
                    }
                    String string2 = AppsFlyerProperties.getInstance().getString("afUninstallToken");
                    if (string2 != null) {
                        AFLogger.afDebugLog("Uninstall Token exists: ".concat(String.valueOf(string2)));
                        if (!sharedPreferences.getBoolean("sentRegisterRequestToAF", false)) {
                            AFLogger.afDebugLog("Resending Uninstall token to AF servers: ".concat(String.valueOf(string2)));
                            z.m225(context, string2);
                        }
                    }
                    if (this.pluginDeeplinkIntent != null) {
                        this.pluginDeeplinkIntent = null;
                    }
                    this.f102 = ServerConfigHandler.m126(serverResponse).optBoolean("send_background", false);
                } else if (requestListener != null) {
                    int i = RequestError.RESPONSE_CODE_FAILURE;
                    StringBuilder sb = new StringBuilder();
                    sb.append(RequestErrorMessage.RESPONSE_CODE_FAILURE);
                    sb.append(" ");
                    sb.append(responseCode);
                    requestListener.onError(i, sb.toString());
                }
                StringBuilder sb2 = new StringBuilder("[GCD-A01] Loading conversion data. Counter: ");
                sb2.append(aFEvent.f13);
                AFLogger.afDebugLog(sb2.toString());
                long j = sharedPreferences.getLong("appsflyerConversionDataCacheExpiration", 0L);
                if (j != 0 && System.currentTimeMillis() - j > 5184000000L) {
                    AFLogger.afDebugLog("[GCD-E02] Cached conversion data expired");
                    SharedPreferences.Editor editorEdit2 = getSharedPreferences(context).edit();
                    editorEdit2.putBoolean("sixtyDayConversionData", true);
                    editorEdit2.apply();
                    SharedPreferences.Editor editorEdit3 = getSharedPreferences(context).edit();
                    editorEdit3.putString("attributionId", null);
                    editorEdit3.apply();
                    m54(context, "appsflyerConversionDataCacheExpiration", 0L);
                }
                if (sharedPreferences.getString("attributionId", null) == null && strKey != null && z) {
                    InstallAttributionIdFetcher installAttributionIdFetcher = new InstallAttributionIdFetcher(this, (Application) context.getApplicationContext(), strKey, (byte) 0);
                    m101(installAttributionIdFetcher.f131, installAttributionIdFetcher, 10L, TimeUnit.MILLISECONDS);
                } else if (strKey == null) {
                    AFLogger.afDebugLog("[GCD-E05] AppsFlyer dev key is missing");
                } else if (z && sharedPreferences.getString("attributionId", null) != null && getLaunchCounter(sharedPreferences, false) > 1) {
                    try {
                        Map<String, Object> mapM69 = m69(context);
                        if (mapM69 != null) {
                            try {
                                if (!mapM69.containsKey("is_first_launch")) {
                                    mapM69.put("is_first_launch", Boolean.FALSE);
                                }
                                InstallAttributionIdFetcher.m121(mapM69);
                            } catch (Throwable th4) {
                                AFLogger.afErrorLog(th4.getLocalizedMessage(), th4);
                            }
                        }
                    } catch (w e2) {
                        AFLogger.afErrorLog(e2.getMessage(), e2);
                    }
                } else if (conversionDataListener == null) {
                    AFLogger.afDebugLog("[GCD-E01] AppsFlyerConversionListener is null - skip gcd");
                }
                if (r14 != 0) {
                    r14.disconnect();
                }
            } catch (Throwable th5) {
                th = th5;
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            if (r14 != 0) {
                r14.disconnect();
            }
            throw th;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public void setHost(String str, String str2) {
        if (str != null) {
            AppsFlyerProperties.getInstance().set("custom_host_prefix", str);
        }
        if (str2 == null || str2.isEmpty()) {
            AFLogger.afWarnLog("hostName cannot be null or empty");
        } else {
            AppsFlyerProperties.getInstance().set("custom_host", str2);
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public String getHostName() {
        String string = AppsFlyerProperties.getInstance().getString("custom_host");
        return string != null ? string : ServerParameters.DEFAULT_HOST;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public String getHostPrefix() {
        String string = AppsFlyerProperties.getInstance().getString("custom_host_prefix");
        return string != null ? string : "";
    }

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    static /* synthetic */ void m63(AppsFlyerLibCore appsFlyerLibCore) {
        AFEvent aFEventContext = new Attr().context(appsFlyerLibCore.f94);
        if (appsFlyerLibCore.m55() && appsFlyerLibCore.m104(aFEventContext, getSharedPreferences(appsFlyerLibCore.f94))) {
            appsFlyerLibCore.m76(aFEventContext);
        }
    }

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    static /* synthetic */ EventDataCollector m96(AppsFlyerLibCore appsFlyerLibCore, Context context) {
        if (appsFlyerLibCore.f107 == null) {
            appsFlyerLibCore.f107 = new EventDataCollector(context);
        }
        return appsFlyerLibCore.f107;
    }

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    static /* synthetic */ void m77(AppsFlyerLibCore appsFlyerLibCore, AFEvent aFEvent) throws Throwable {
        String string;
        StringBuilder sb = new StringBuilder("url: ");
        sb.append(aFEvent.urlString());
        AFLogger.afInfoLog(sb.toString());
        if (aFEvent.f15 != null) {
            string = Base64.encodeToString(aFEvent.m9(), 2);
            AFLogger.afInfoLog("cached data: ".concat(String.valueOf(string)));
        } else {
            string = new JSONObject(aFEvent.params()).toString();
            String strReplaceAll = string.replaceAll("\\p{C}", "*Non-printing character*");
            if (!strReplaceAll.equals(string)) {
                AFLogger.afWarnLog("Payload contains non-printing characters");
                string = strReplaceAll;
            }
            ab.m161("data: ".concat(String.valueOf(string)));
        }
        ac.m168().m175("server_request", aFEvent.urlString(), string);
        try {
            appsFlyerLibCore.m90(aFEvent);
        } catch (IOException e2) {
            AFLogger.afErrorLog("Exception in sendRequestToServer. ", e2);
            if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.USE_HTTP_FALLBACK, false)) {
                appsFlyerLibCore.m90(aFEvent.urlString(aFEvent.urlString().replace("https:", "http:")));
                return;
            }
            StringBuilder sb2 = new StringBuilder("failed to send request to server. ");
            sb2.append(e2.getLocalizedMessage());
            AFLogger.afInfoLog(sb2.toString());
            throw e2;
        }
    }

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    static /* synthetic */ void m74(Context context, String str, String str2) {
        SharedPreferences.Editor editorEdit = getSharedPreferences(context).edit();
        editorEdit.putString(str, str2);
        editorEdit.apply();
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    static /* synthetic */ String m85(String str) {
        return AppsFlyerProperties.getInstance().getString(str);
    }
}
