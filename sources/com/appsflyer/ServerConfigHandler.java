package com.appsflyer;

import androidx.annotation.Nullable;
import com.appsflyer.internal.ac;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ServerConfigHandler {
    @Nullable
    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    static JSONObject m126(String str) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
        } catch (Throwable th) {
            th = th;
            jSONObject = null;
        }
        try {
            boolean z = AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.DPM, false);
            if (jSONObject.optBoolean("monitor", false) && !z) {
                ac.m168().m176();
            } else {
                ac.m168().m173();
                ac.m168().m172();
            }
            if (jSONObject.has("ol_id")) {
                String strOptString = jSONObject.optString("ol_scheme", null);
                String strOptString2 = jSONObject.optString("ol_domain", null);
                String strOptString3 = jSONObject.optString("ol_ver", null);
                if (strOptString != null) {
                    AppsFlyerProperties.getInstance().set(AppsFlyerProperties.ONELINK_SCHEME, strOptString);
                }
                if (strOptString2 != null) {
                    AppsFlyerProperties.getInstance().set(AppsFlyerProperties.ONELINK_DOMAIN, strOptString2);
                }
                if (strOptString3 != null) {
                    AppsFlyerProperties.getInstance().set("onelinkVersion", strOptString3);
                }
            }
        } catch (Throwable th2) {
            th = th2;
            AFLogger.afErrorLog(th.getMessage(), th);
            ac.m168().m173();
            ac.m168().m172();
        }
        return jSONObject;
    }

    public static String getUrl(String str) {
        return String.format(str, AppsFlyerLib.getInstance().getHostPrefix(), AppsFlyerLibCore.getInstance().getHostName());
    }
}
