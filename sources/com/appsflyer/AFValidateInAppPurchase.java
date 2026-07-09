package com.appsflyer;

import android.content.Context;
import androidx.annotation.NonNull;
import com.appsflyer.internal.ac;
import com.appsflyer.internal.model.event.Purchase;
import com.appsflyer.internal.model.event.SdkServices;
import com.appsflyer.internal.model.event.Validate;
import com.appsflyer.internal.referrer.Payload;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AFValidateInAppPurchase implements Runnable {

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    private static String f63 = "https://%ssdk-services.%s/validate-android-signature";

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    private static String f64;

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    private WeakReference<Context> f65;

    /* JADX INFO: renamed from: Ɩ, reason: contains not printable characters */
    private String f66;

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    private String f67;

    /* JADX INFO: renamed from: ɹ, reason: contains not printable characters */
    private Map<String, String> f68;

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    private String f69;

    /* JADX INFO: renamed from: І, reason: contains not printable characters */
    private String f70;

    /* JADX INFO: renamed from: і, reason: contains not printable characters */
    private String f71;

    /* JADX INFO: renamed from: Ӏ, reason: contains not printable characters */
    private String f72;

    static {
        StringBuilder sb = new StringBuilder("https://%svalidate.%s/api/v");
        sb.append(AppsFlyerLibCore.SERVER_BUILD_NUMBER);
        sb.append("/androidevent?buildnumber=6.1.0&app_id=");
        f64 = sb.toString();
    }

    AFValidateInAppPurchase(Context context, String str, String str2, String str3, String str4, String str5, String str6, Map<String, String> map) {
        this.f65 = new WeakReference<>(context);
        this.f69 = str;
        this.f67 = str2;
        this.f70 = str4;
        this.f66 = str5;
        this.f71 = str6;
        this.f68 = map;
        this.f72 = str3;
    }

    public static void setUrl(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String value = entry.getValue();
            String key = entry.getKey();
            byte b = -1;
            int iHashCode = key.hashCode();
            if (iHashCode != -1421272810) {
                if (iHashCode == 454162577 && key.equals("sdk-services")) {
                    b = 0;
                }
            } else if (key.equals("validate")) {
                b = 1;
            }
            if (b == 0) {
                f63 = value;
            } else if (b == 1) {
                f64 = value;
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        String str = this.f69;
        if (str == null || str.length() == 0 || AppsFlyerLib.getInstance().isStopped()) {
            return;
        }
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                Context context = this.f65.get();
                if (context == null) {
                    return;
                }
                HashMap map = new HashMap();
                map.put("public-key", this.f67);
                map.put("sig-data", this.f70);
                map.put("signature", this.f72);
                final HashMap map2 = new HashMap(map);
                new Thread(new Runnable() { // from class: com.appsflyer.AFValidateInAppPurchase.5
                    @Override // java.lang.Runnable
                    public final void run() {
                        AFValidateInAppPurchase aFValidateInAppPurchase = AFValidateInAppPurchase.this;
                        AFValidateInAppPurchase.m41(aFValidateInAppPurchase, map2, aFValidateInAppPurchase.f68, AFValidateInAppPurchase.this.f65);
                    }
                }).start();
                map.put("dev_key", this.f69);
                map.put(ServerParameters.APP_ID, context.getPackageName());
                map.put(ServerParameters.AF_USER_ID, AppsFlyerLib.getInstance().getAppsFlyerUID(context));
                map.put(ServerParameters.ADVERTISING_ID_PARAM, AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM));
                String string = new JSONObject(map).toString();
                String url = ServerConfigHandler.getUrl(f63);
                ac.m168().m175("server_request", url, string);
                HttpURLConnection httpURLConnectionM43 = m43((Purchase) new SdkServices().addParams(map).urlString(url));
                int responseCode = httpURLConnectionM43 != null ? httpURLConnectionM43.getResponseCode() : -1;
                String serverResponse = AppsFlyerLibCore.getInstance().readServerResponse(httpURLConnectionM43);
                ac.m168().m175("server_response", url, String.valueOf(responseCode), serverResponse);
                JSONObject jSONObject = new JSONObject(serverResponse);
                jSONObject.put("code", responseCode);
                if (responseCode == 200) {
                    StringBuilder sb = new StringBuilder("Validate response 200 ok: ");
                    sb.append(jSONObject.toString());
                    AFLogger.afInfoLog(sb.toString());
                    m45(jSONObject.optBoolean("result"), this.f70, this.f66, this.f71, jSONObject.toString());
                } else {
                    AFLogger.afInfoLog("Failed Validate request");
                    m45(false, this.f70, this.f66, this.f71, jSONObject.toString());
                }
                if (httpURLConnectionM43 != null) {
                    httpURLConnectionM43.disconnect();
                }
            } catch (Throwable th) {
                if (AppsFlyerLibCore.f80 != null) {
                    AFLogger.afErrorLog("Failed Validate request + ex", th);
                    m45(false, this.f70, this.f66, this.f71, th.getMessage());
                }
                AFLogger.afErrorLog(th.getMessage(), th);
                if (0 != 0) {
                    httpURLConnection.disconnect();
                }
            }
        } catch (Throwable th2) {
            if (0 != 0) {
                httpURLConnection.disconnect();
            }
            throw th2;
        }
    }

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    private static HttpURLConnection m43(@NonNull Purchase purchase) {
        StringBuilder sb = new StringBuilder("Calling ");
        sb.append(purchase.urlString());
        AFLogger.afDebugLog(sb.toString());
        return new BackgroundHttpTask(purchase.trackingStopped(AppsFlyerLib.getInstance().isStopped())).doInBackground();
    }

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    private static void m45(boolean z, String str, String str2, String str3, String str4) {
        if (AppsFlyerLibCore.f80 != null) {
            StringBuilder sb = new StringBuilder("Validate callback parameters: ");
            sb.append(str);
            sb.append(" ");
            sb.append(str2);
            sb.append(" ");
            sb.append(str3);
            AFLogger.afDebugLog(sb.toString());
            if (z) {
                AFLogger.afDebugLog("Validate in app purchase success: ".concat(String.valueOf(str4)));
                AppsFlyerLibCore.f80.onValidateInApp();
                return;
            }
            AFLogger.afDebugLog("Validate in app purchase failed: ".concat(String.valueOf(str4)));
            AppsFlyerInAppPurchaseValidatorListener appsFlyerInAppPurchaseValidatorListener = AppsFlyerLibCore.f80;
            if (str4 == null) {
                str4 = "Failed validating";
            }
            appsFlyerInAppPurchaseValidatorListener.onValidateInAppFailure(str4);
        }
    }

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    static /* synthetic */ void m41(AFValidateInAppPurchase aFValidateInAppPurchase, Map map, Map map2, WeakReference weakReference) {
        if (weakReference.get() != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(ServerConfigHandler.getUrl(f64));
            sb.append(((Context) weakReference.get()).getPackageName());
            String string = sb.toString();
            String string2 = AppsFlyerLibCore.getSharedPreferences((Context) weakReference.get()).getString(Payload.RFR, "");
            AFEvent aFEventKey = new Validate((Context) weakReference.get()).key(aFValidateInAppPurchase.f69);
            aFEventKey.f18 = string2;
            AFEvent aFEvent = (Validate) aFEventKey;
            Map<String, Object> mapM113 = AppsFlyerLibCore.getInstance().m113(aFEvent);
            mapM113.put(FirebaseAnalytics.Param.PRICE, aFValidateInAppPurchase.f66);
            mapM113.put(FirebaseAnalytics.Param.CURRENCY, aFValidateInAppPurchase.f71);
            mapM113.put("receipt_data", map);
            if (map2 != null) {
                mapM113.put("extra_prms", map2);
            }
            ac.m168().m175("server_request", string, new JSONObject(mapM113).toString());
            HttpURLConnection httpURLConnectionM43 = null;
            try {
                try {
                    httpURLConnectionM43 = m43((Purchase) aFEvent.addParams(mapM113).urlString(string));
                    int responseCode = httpURLConnectionM43 != null ? httpURLConnectionM43.getResponseCode() : -1;
                    String serverResponse = AppsFlyerLibCore.getInstance().readServerResponse(httpURLConnectionM43);
                    ac.m168().m175("server_response", string, String.valueOf(responseCode), serverResponse);
                    StringBuilder sb2 = new StringBuilder("Validate-WH response - ");
                    sb2.append(responseCode);
                    sb2.append(": ");
                    sb2.append(new JSONObject(serverResponse).toString());
                    AFLogger.afInfoLog(sb2.toString());
                    if (httpURLConnectionM43 != null) {
                        httpURLConnectionM43.disconnect();
                    }
                } catch (Throwable th) {
                    AFLogger.afErrorLog(th.getMessage(), th);
                    if (httpURLConnectionM43 != null) {
                        httpURLConnectionM43.disconnect();
                    }
                }
            } catch (Throwable th2) {
                if (httpURLConnectionM43 != null) {
                    httpURLConnectionM43.disconnect();
                }
                throw th2;
            }
        }
    }
}
