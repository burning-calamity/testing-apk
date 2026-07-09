package com.appsflyer.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.appsflyer.AFDeepLinkManager;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerLibCore;
import com.appsflyer.OneLinkHttpTask;
import com.appsflyer.ServerConfigHandler;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class y extends OneLinkHttpTask {

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    private static List<String> f350 = Arrays.asList("onelink.me", "onelnk.com", "app.aflink.com");

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    private String f351;

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    public boolean f352;

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    public e f353;

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    private String f354;

    public interface e {
        /* JADX INFO: renamed from: ǃ */
        void mo116(String str);

        /* JADX INFO: renamed from: ι */
        void mo117(Map<String, String> map);
    }

    public y(Uri uri, AppsFlyerLibCore appsFlyerLibCore) {
        super(appsFlyerLibCore);
        this.f352 = false;
        if (TextUtils.isEmpty(uri.getHost()) || TextUtils.isEmpty(uri.getPath())) {
            return;
        }
        Iterator<String> it = f350.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (uri.getHost().contains(it.next())) {
                z = true;
            }
        }
        if (AFDeepLinkManager.f1 != null) {
            StringBuilder sb = new StringBuilder("Validate custom domain URLs: ");
            sb.append(Arrays.asList(AFDeepLinkManager.f1));
            AFLogger.afRDLog(sb.toString());
            for (String str : AFDeepLinkManager.f1) {
                if (uri.getHost().contains(str) && !TextUtils.isEmpty(str)) {
                    StringBuilder sb2 = new StringBuilder("DeepLink matches customDomain: ");
                    sb2.append(uri.toString());
                    AFLogger.afDebugLog(sb2.toString());
                    this.f352 = true;
                    z = true;
                }
            }
        }
        String[] strArrSplit = uri.getPath().split("/");
        if (z && strArrSplit.length == 3) {
            this.oneLinkId = strArrSplit[1];
            this.f354 = strArrSplit[2];
            this.f351 = uri.toString();
        }
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    public final boolean m224() {
        return (TextUtils.isEmpty(this.oneLinkId) || TextUtils.isEmpty(this.f354) || this.oneLinkId.equals("app")) ? false : true;
    }

    @Override // com.appsflyer.OneLinkHttpTask
    public final void initRequest(HttpsURLConnection httpsURLConnection) throws JSONException, IOException {
        httpsURLConnection.setRequestMethod("GET");
    }

    @Override // com.appsflyer.OneLinkHttpTask
    public final String getOneLinkUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append(ServerConfigHandler.getUrl(BASE_URL));
        sb.append("/");
        sb.append(this.oneLinkId);
        sb.append("?id=");
        sb.append(this.f354);
        return sb.toString();
    }

    @Override // com.appsflyer.OneLinkHttpTask
    public final void handleResponse(String str) {
        try {
            HashMap map = new HashMap();
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                map.put(next, jSONObject.optString(next));
            }
            this.f353.mo117(map);
        } catch (JSONException e2) {
            this.f353.mo116("Can't parse one link data");
            AFLogger.afErrorLog("Error while parsing to json ".concat(String.valueOf(str)), e2);
        }
    }

    @Override // com.appsflyer.OneLinkHttpTask
    public final void onErrorResponse() {
        String str = this.f351;
        if (str == null) {
            str = "Can't get one link data";
        }
        this.f353.mo116(str);
    }
}
