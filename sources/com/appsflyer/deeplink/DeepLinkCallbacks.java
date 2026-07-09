package com.appsflyer.deeplink;

import com.appsflyer.AFDeepLinkManager;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerLibCore;
import com.appsflyer.deeplink.DeepLinkResult;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public class DeepLinkCallbacks {
    public static void onDeepLinkingError(String str) {
        if (AFDeepLinkManager.getInstance().deepLinkListener != null) {
            AFLogger.afDebugLog("[DDL] Error occurred: ".concat(String.valueOf(str)));
            m139(new DeepLinkResult(null, DeepLinkResult.Error.NETWORK));
        } else if (AppsFlyerLibCore.conversionDataListener != null) {
            try {
                AFLogger.afDebugLog("Calling onAppOpenAttributionFailure with: ".concat(String.valueOf(str)));
                AppsFlyerLibCore.conversionDataListener.onAttributionFailure(str);
            } catch (Throwable th) {
                AFLogger.afErrorLog(th.getLocalizedMessage(), th);
            }
        }
    }

    public static void onDeepLinkingSuccess(Map<String, String> map) {
        DeepLinkResult deepLinkResult;
        if (AFDeepLinkManager.getInstance().deepLinkListener != null) {
            try {
                try {
                    DeepLink deepLinkM138 = DeepLink.m138(map);
                    deepLinkM138.f174.put("is_deferred", false);
                    deepLinkResult = new DeepLinkResult(deepLinkM138, null);
                } catch (JSONException e) {
                    AFLogger.afErrorLog("[DDL] Error occurred", e, true);
                    deepLinkResult = new DeepLinkResult(null, DeepLinkResult.Error.UNEXPECTED);
                }
                m139(deepLinkResult);
                return;
            } catch (Throwable th) {
                m139(new DeepLinkResult(null, null));
                throw th;
            }
        }
        onAppOpenAttribution(map);
    }

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    static void m139(DeepLinkResult deepLinkResult) {
        if (AFDeepLinkManager.getInstance().deepLinkListener != null) {
            StringBuilder sb = new StringBuilder("[DDL] Calling onDeepLinking with:\n");
            sb.append(deepLinkResult.toString());
            AFLogger.afDebugLog(sb.toString());
            try {
                AFDeepLinkManager.getInstance().deepLinkListener.onDeepLinking(deepLinkResult);
                return;
            } catch (Throwable th) {
                AFLogger.afErrorLog(th.getLocalizedMessage(), th);
                return;
            }
        }
        AFLogger.afDebugLog("[DDL] skipping, no callback registered");
    }

    public static void onAppOpenAttribution(Map<String, String> map) {
        if (AppsFlyerLibCore.conversionDataListener != null) {
            try {
                StringBuilder sb = new StringBuilder("Calling onAppOpenAttribution with:\n");
                sb.append(map.toString());
                AFLogger.afDebugLog(sb.toString());
                AppsFlyerLibCore.conversionDataListener.onAppOpenAttribution(map);
            } catch (Throwable th) {
                AFLogger.afErrorLog(th.getLocalizedMessage(), th);
            }
        }
    }
}
