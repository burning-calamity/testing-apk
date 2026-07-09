package com.appsflyer;

import android.text.TextUtils;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public abstract class OneLinkHttpTask implements Runnable {
    protected static String BASE_URL = "https://%sonelink.%s/shortlink-sdk/v1";
    static final String NO_CONNECTION_ERROR_MSG = "Can't get one link data";
    private static final int WAIT_TIMEOUT = 3000;
    private AppsFlyerLibCore afLib;
    private HttpsUrlConnectionProvider connectionProvider;
    public String oneLinkId;

    protected abstract String getOneLinkUrl();

    protected abstract void handleResponse(String str);

    protected abstract void initRequest(HttpsURLConnection httpsURLConnection) throws JSONException, IOException;

    protected abstract void onErrorResponse();

    public OneLinkHttpTask(AppsFlyerLibCore appsFlyerLibCore) {
        this.afLib = appsFlyerLibCore;
    }

    public static void setUrl(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if ("onelink".equals(entry.getKey())) {
                BASE_URL = entry.getValue();
            }
        }
    }

    public void setConnProvider(HttpsUrlConnectionProvider httpsUrlConnectionProvider) {
        this.connectionProvider = httpsUrlConnectionProvider;
    }

    @Override // java.lang.Runnable
    public void run() {
        doRequest();
    }

    private void doRequest() {
        String serverResponse;
        String str = "";
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        String oneLinkUrl = getOneLinkUrl();
        AFLogger.afRDLog("oneLinkUrl: " + oneLinkUrl);
        try {
            HttpsURLConnection httpsURLConnectionM125 = this.connectionProvider.m125(oneLinkUrl);
            httpsURLConnectionM125.addRequestProperty("content-type", "application/json");
            httpsURLConnectionM125.addRequestProperty("authorization", HashUtils.getOneLinkAuthorization(jCurrentTimeMillis));
            httpsURLConnectionM125.addRequestProperty("af-timestamp", String.valueOf(jCurrentTimeMillis));
            httpsURLConnectionM125.setReadTimeout(3000);
            httpsURLConnectionM125.setConnectTimeout(3000);
            initRequest(httpsURLConnectionM125);
            int responseCode = httpsURLConnectionM125.getResponseCode();
            serverResponse = this.afLib.readServerResponse(httpsURLConnectionM125);
            try {
                if (responseCode == 200) {
                    AFLogger.afInfoLog("Status 200 ok");
                } else {
                    str = "Response code = " + responseCode + " content = " + serverResponse;
                }
            } catch (Throwable th) {
                th = th;
                AFLogger.afErrorLog("Error while calling " + oneLinkUrl, th);
                str = "Error while calling " + oneLinkUrl + " stacktrace: " + th.toString();
            }
        } catch (Throwable th2) {
            th = th2;
            serverResponse = "";
        }
        if (TextUtils.isEmpty(str)) {
            AFLogger.afInfoLog("Connection call succeeded: " + serverResponse);
            handleResponse(serverResponse);
            return;
        }
        AFLogger.afWarnLog("Connection error: " + str);
        onErrorResponse();
    }

    public static class HttpsUrlConnectionProvider {
        /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
        final HttpsURLConnection m125(String str) throws IOException {
            return (HttpsURLConnection) new URL(str).openConnection();
        }
    }
}
