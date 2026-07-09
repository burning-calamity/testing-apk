package com.appsflyer.share;

import android.content.Context;
import com.appsflyer.AFExecutor;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerLibCore;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.CreateOneLinkHttpTask;
import com.appsflyer.OneLinkHttpTask;
import com.appsflyer.ServerConfigHandler;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class LinkGenerator {

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    String f361;

    /* JADX INFO: renamed from: Ɩ, reason: contains not printable characters */
    private String f362;

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    private String f363;

    /* JADX INFO: renamed from: ɨ, reason: contains not printable characters */
    private String f364;

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    private String f365;

    /* JADX INFO: renamed from: ɪ, reason: contains not printable characters */
    private String f366;

    /* JADX INFO: renamed from: ɹ, reason: contains not printable characters */
    private String f367;

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    String f369;

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    private String f370;

    /* JADX INFO: renamed from: І, reason: contains not printable characters */
    private String f371;

    /* JADX INFO: renamed from: і, reason: contains not printable characters */
    private String f372;

    /* JADX INFO: renamed from: Ӏ, reason: contains not printable characters */
    private String f373;

    /* JADX INFO: renamed from: ӏ, reason: contains not printable characters */
    private Map<String, String> f374 = new HashMap();

    /* JADX INFO: renamed from: ɾ, reason: contains not printable characters */
    private Map<String, String> f368 = new HashMap();

    public LinkGenerator(String str) {
        this.f370 = str;
    }

    public LinkGenerator setBrandDomain(String str) {
        this.f364 = str;
        return this;
    }

    public String getBrandDomain() {
        return this.f364;
    }

    public LinkGenerator setDeeplinkPath(String str) {
        this.f372 = str;
        return this;
    }

    public LinkGenerator setBaseDeeplink(String str) {
        this.f366 = str;
        return this;
    }

    public String getChannel() {
        return this.f365;
    }

    public LinkGenerator setChannel(String str) {
        this.f365 = str;
        return this;
    }

    public LinkGenerator setReferrerCustomerId(String str) {
        this.f367 = str;
        return this;
    }

    public String getMediaSource() {
        return this.f370;
    }

    public Map<String, String> getParameters() {
        return this.f374;
    }

    public String getCampaign() {
        return this.f363;
    }

    public LinkGenerator setCampaign(String str) {
        this.f363 = str;
        return this;
    }

    public LinkGenerator addParameter(String str, String str2) {
        this.f374.put(str, str2);
        return this;
    }

    public LinkGenerator addParameters(Map<String, String> map) {
        if (map != null) {
            this.f374.putAll(map);
        }
        return this;
    }

    public LinkGenerator setReferrerUID(String str) {
        this.f371 = str;
        return this;
    }

    public LinkGenerator setReferrerName(String str) {
        this.f373 = str;
        return this;
    }

    public LinkGenerator setReferrerImageURL(String str) {
        this.f362 = str;
        return this;
    }

    public LinkGenerator setBaseURL(String str, String str2, String str3) {
        if (str == null || str.length() <= 0) {
            this.f361 = String.format("https://%s/%s", ServerConfigHandler.getUrl("%sapp.%s"), str3);
        } else {
            if (str2 == null || str2.length() < 5) {
                str2 = "go.onelink.me";
            }
            this.f361 = String.format("https://%s/%s", str2, str);
        }
        return this;
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    private StringBuilder m229() {
        StringBuilder sb = new StringBuilder();
        String str = this.f361;
        if (str != null && str.startsWith("http")) {
            sb.append(this.f361);
        } else {
            sb.append(ServerConfigHandler.getUrl(Constants.f355));
        }
        if (this.f369 != null) {
            sb.append('/');
            sb.append(this.f369);
        }
        this.f368.put(Constants.URL_MEDIA_SOURCE, this.f370);
        sb.append('?');
        sb.append("pid=");
        sb.append(m228(this.f370, "media source"));
        String str2 = this.f371;
        if (str2 != null) {
            this.f368.put("af_referrer_uid", str2);
            sb.append('&');
            sb.append("af_referrer_uid=");
            sb.append(m228(this.f371, "referrerUID"));
        }
        String str3 = this.f365;
        if (str3 != null) {
            this.f368.put("af_channel", str3);
            sb.append('&');
            sb.append("af_channel=");
            sb.append(m228(this.f365, "channel"));
        }
        String str4 = this.f367;
        if (str4 != null) {
            this.f368.put("af_referrer_customer_id", str4);
            sb.append('&');
            sb.append("af_referrer_customer_id=");
            sb.append(m228(this.f367, "referrerCustomerId"));
        }
        String str5 = this.f363;
        if (str5 != null) {
            this.f368.put(Constants.URL_CAMPAIGN, str5);
            sb.append('&');
            sb.append("c=");
            sb.append(m228(this.f363, FirebaseAnalytics.Param.CAMPAIGN));
        }
        String str6 = this.f373;
        if (str6 != null) {
            this.f368.put("af_referrer_name", str6);
            sb.append('&');
            sb.append("af_referrer_name=");
            sb.append(m228(this.f373, "referrerName"));
        }
        String str7 = this.f362;
        if (str7 != null) {
            this.f368.put("af_referrer_image_url", str7);
            sb.append('&');
            sb.append("af_referrer_image_url=");
            sb.append(m228(this.f362, "referrerImageURL"));
        }
        if (this.f366 != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.f366);
            sb2.append(this.f366.endsWith("/") ? "" : "/");
            String str8 = this.f372;
            if (str8 != null) {
                sb2.append(str8);
            }
            this.f368.put("af_dp", sb2.toString());
            sb.append('&');
            sb.append("af_dp=");
            sb.append(m228(this.f366, "baseDeeplink"));
            if (this.f372 != null) {
                sb.append(this.f366.endsWith("/") ? "" : "%2F");
                sb.append(m228(this.f372, "deeplinkPath"));
            }
        }
        for (String str9 : this.f374.keySet()) {
            String string = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str9);
            sb3.append("=");
            sb3.append(m228(this.f374.get(str9), str9));
            if (!string.contains(sb3.toString())) {
                sb.append('&');
                sb.append(str9);
                sb.append('=');
                sb.append(m228(this.f374.get(str9), str9));
            }
        }
        return sb;
    }

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    private static String m228(String str, String str2) {
        try {
            return URLEncoder.encode(str, "utf8");
        } catch (UnsupportedEncodingException e) {
            StringBuilder sb = new StringBuilder("Illegal ");
            sb.append(str2);
            sb.append(": ");
            sb.append(str);
            AFLogger.afErrorLog(sb.toString(), e);
            return "";
        } catch (Throwable th) {
            AFLogger.afErrorLog(th);
            return "";
        }
    }

    public String generateLink() {
        return m229().toString();
    }

    public void generateLink(Context context, CreateOneLinkHttpTask.ResponseListener responseListener) {
        String string = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.ONELINK_ID);
        if (!this.f374.isEmpty()) {
            for (Map.Entry<String, String> entry : this.f374.entrySet()) {
                this.f368.put(entry.getKey(), entry.getValue());
            }
        }
        m229();
        String str = this.f364;
        Map<String, String> map = this.f368;
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false)) {
            AFLogger.afInfoLog("CustomerUserId not set, generate User Invite Link is disabled", true);
            return;
        }
        CreateOneLinkHttpTask createOneLinkHttpTask = new CreateOneLinkHttpTask(string, map, AppsFlyerLibCore.getInstance(), context, AppsFlyerLib.getInstance().isStopped());
        createOneLinkHttpTask.setConnProvider(new OneLinkHttpTask.HttpsUrlConnectionProvider());
        createOneLinkHttpTask.setListener(responseListener);
        createOneLinkHttpTask.setBrandDomain(str);
        AFExecutor.getInstance().getThreadPoolExecutor().execute(createOneLinkHttpTask);
    }
}
