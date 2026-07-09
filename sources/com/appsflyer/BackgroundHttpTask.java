package com.appsflyer;

import android.content.Context;
import android.net.TrafficStats;
import com.appsflyer.internal.ab;
import com.appsflyer.internal.ac;
import com.appsflyer.internal.e;
import com.appsflyer.internal.model.event.BackgroundEvent;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
public class BackgroundHttpTask {

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    private final BackgroundEvent f150;

    public BackgroundHttpTask(BackgroundEvent backgroundEvent) {
        this.f150 = (BackgroundEvent) backgroundEvent.weakContext();
    }

    /* JADX INFO: renamed from: com.appsflyer.BackgroundHttpTask$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        public AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            BackgroundHttpTask.this.doInBackground();
        }
    }

    public HttpURLConnection doInBackground() {
        String serverResponse;
        HttpURLConnection httpURLConnection;
        URL url;
        serverResponse = "";
        String strUrlString = this.f150.urlString();
        String strBody = this.f150.body();
        boolean zTrackingStopped = this.f150.trackingStopped();
        boolean response = this.f150.readResponse();
        boolean zProxyMode = this.f150.proxyMode();
        boolean zIsEncrypt = this.f150.isEncrypt();
        byte[] bytes = strBody.getBytes();
        if (zTrackingStopped) {
            return null;
        }
        boolean z = true;
        try {
            url = new URL(strUrlString);
            if (zProxyMode) {
                ac.m168().m175("server_request", url.toString(), strBody);
                int length = strBody.getBytes("UTF-8").length;
                StringBuilder sb = new StringBuilder("call = ");
                sb.append(url);
                sb.append("; size = ");
                sb.append(length);
                sb.append(" byte");
                sb.append(length > 1 ? "s" : "");
                sb.append("; body = ");
                sb.append(strBody);
                ab.m161(sb.toString());
            }
            TrafficStats.setThreadStatsTag("AppsFlyer".hashCode());
            httpURLConnection = (HttpURLConnection) url.openConnection();
        } catch (Throwable th) {
            th = th;
            httpURLConnection = null;
        }
        try {
            httpURLConnection.setReadTimeout(30000);
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Type", zIsEncrypt ? "application/octet-stream" : "application/json");
            OutputStream outputStream = httpURLConnection.getOutputStream();
            if (zIsEncrypt) {
                try {
                    try {
                        bytes = (byte[]) ((Class) e.m193(24, (char) 0, 24)).getMethod("ɩ", byte[].class).invoke(((Class) e.m193(24, (char) 0, 24)).getMethod("ι", String.class).invoke(null, this.f150.key()), bytes);
                    } catch (Throwable th2) {
                        Throwable cause = th2.getCause();
                        if (cause != null) {
                            throw cause;
                        }
                        throw th2;
                    }
                } catch (Throwable th3) {
                    Throwable cause2 = th3.getCause();
                    if (cause2 != null) {
                        throw cause2;
                    }
                    throw th3;
                }
            }
            outputStream.write(bytes);
            outputStream.close();
            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();
            serverResponse = response ? AppsFlyerLibCore.getInstance().readServerResponse(httpURLConnection) : "";
            if (zProxyMode) {
                ac.m168().m175("server_response", url.toString(), String.valueOf(responseCode), serverResponse);
            }
            if (responseCode == 200) {
                AFLogger.afInfoLog("Status 200 ok");
                Context context = this.f150.context();
                if (url.toString().startsWith(ServerConfigHandler.getUrl(AppsFlyerLibCore.REGISTER_URL)) && context != null) {
                    AppsFlyerLibCore.getSharedPreferences(context).edit().putBoolean("sentRegisterRequestToAF", true).apply();
                    AFLogger.afDebugLog("Successfully registered for Uninstall Measurement");
                }
                z = false;
            }
        } catch (Throwable th4) {
            th = th4;
            AFLogger.afErrorLog("Error while calling ".concat(String.valueOf(strUrlString)), th);
        }
        StringBuilder sb2 = new StringBuilder("Connection ");
        sb2.append(z ? "error" : "call succeeded");
        sb2.append(": ");
        sb2.append(serverResponse);
        AFLogger.afInfoLog(sb2.toString());
        return httpURLConnection;
    }
}
