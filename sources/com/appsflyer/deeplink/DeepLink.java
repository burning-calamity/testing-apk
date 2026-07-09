package com.appsflyer.deeplink;

import androidx.annotation.Nullable;
import com.appsflyer.ServerParameters;
import com.appsflyer.share.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class DeepLink {

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    final JSONObject f174;

    private DeepLink(JSONObject jSONObject) {
        this.f174 = jSONObject;
    }

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    static DeepLink m137(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("custom_params");
        if (jSONObjectOptJSONObject != null) {
            jSONObject.remove("custom_params");
            Iterator<String> itKeys = jSONObjectOptJSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                jSONObject.put(next, jSONObjectOptJSONObject.opt(next));
            }
        }
        return new DeepLink(jSONObject);
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    static DeepLink m138(Map<String, String> map) throws JSONException {
        Set<String> setKeySet = map.keySet();
        setKeySet.removeAll(Arrays.asList("install_time", "path", "scheme", "host", Constants.URL_MEDIA_SOURCE, Constants.URL_CAMPAIGN, "af_prt", "af_mp", "clickid", Constants.URL_SITE_ID, "af_sub_siteid", "af_c_id", "af_adset", "af_adset_id", "af_ad", "af_ad_id", "af_ad_type", "af_click_lookback", "af_viewthrough_lookback", "af_channel", "af_keywords", "af_cost_model", "af_cost_currency", "af_cost_value", "af_r", "af_web_dp", "af_dp", "af_force_deeplink", "af_ref", "is_incentivized", "af_param_forwarding", "is_retargeting", "af_reengagement_window", "is_branded_link", "is_universal_link", "esp_name", "af_generated_clk", FirebaseAnalytics.Param.TRANSACTION_ID, "af_fp_lookback_window", "af_vt_fp_lookback_window", "af_fp_priority", "af_generate_clk", "af_inactivity_window", "af_ol_red", "af_attr", "af_ol_lp", "af_blank_red", "af_source", "af_lp_src", "af_src_browser", "af_tranid", "af_wrt_clk", "af_ua", "af_ip", "af_lang", "advertising_id", "sha1_advertising_id", "md5_advertising_id", ServerParameters.ANDROID_ID, "sha1_android_id", "md5_android_id", ServerParameters.IMEI, "sha1_imei", "md5_imei", ServerParameters.OAID, "sha1_oaid", "md5_oaid", "af_android_url", "sha1_el", "fire_advertising_id", "sha1_fire_advertising_id", "idfa", "md5_idfa", "af_ios_url", "af_ios_fallback", "sha1_idfa", "mac", "sha1_mac", "af_banner", "af_slk_web_endpoint", "af_chrome_lp", "af_android_custom_url", "af_ios_custom_url", "af_enc_data", "engmnt_source", "redirect_response_data", "shortlink", ServerParameters.ADVERTISING_ID_PARAM, "sha1_advertiserId", "advertiser_id", "sha1_advertiser_id", "muid", "idfv", "md5_idfv", "sha1_idfv", "af_installpostback", "http_referrer", "af_model", "af_os", "md5_advertiserId", "af_video_total_length", "af_video_played_length", "af_playable_played_length", "af_ad_time_viewed", "af_ad_displayed_percent", "af_audio_total_length", "af_audio_played_length", "link"));
        HashMap map2 = new HashMap();
        for (String str : setKeySet) {
            map2.put(str, map.get(str));
        }
        return m137(new JSONObject(map2));
    }

    public JSONObject getClickEvent() {
        return this.f174;
    }

    public String toString() {
        return this.f174.toString();
    }

    @Nullable
    public String getStringValue(String str) {
        Object objOpt = this.f174.opt(str);
        if (objOpt == JSONObject.NULL) {
            objOpt = null;
        }
        if (!(objOpt instanceof Boolean)) {
            return (String) objOpt;
        }
        Object objOpt2 = this.f174.opt(str);
        if (objOpt2 == JSONObject.NULL) {
            objOpt2 = null;
        }
        return String.valueOf(objOpt2);
    }

    @Nullable
    public String getDeepLinkValue() {
        Object objOpt = this.f174.opt("deep_link_value");
        if (objOpt == JSONObject.NULL) {
            objOpt = null;
        }
        return (String) objOpt;
    }

    @Nullable
    public String getMatchType() {
        Object objOpt = this.f174.opt("match_type");
        if (objOpt == JSONObject.NULL) {
            objOpt = null;
        }
        return (String) objOpt;
    }

    @Nullable
    public String getClickHttpReferrer() {
        Object objOpt = this.f174.opt("click_http_referrer");
        if (objOpt == JSONObject.NULL) {
            objOpt = null;
        }
        return (String) objOpt;
    }

    @Nullable
    public String getMediaSource() {
        Object objOpt = this.f174.opt("media_source");
        if (objOpt == JSONObject.NULL) {
            objOpt = null;
        }
        return (String) objOpt;
    }

    @Nullable
    public String getCampaign() {
        Object objOpt = this.f174.opt(FirebaseAnalytics.Param.CAMPAIGN);
        if (objOpt == JSONObject.NULL) {
            objOpt = null;
        }
        return (String) objOpt;
    }

    @Nullable
    public String getCampaignId() {
        Object objOpt = this.f174.opt("campaign_id");
        if (objOpt == JSONObject.NULL) {
            objOpt = null;
        }
        return (String) objOpt;
    }

    @Nullable
    public String getAfSub1() {
        Object objOpt = this.f174.opt("af_sub1");
        if (objOpt == JSONObject.NULL) {
            objOpt = null;
        }
        return (String) objOpt;
    }

    @Nullable
    public String getAfSub2() {
        Object objOpt = this.f174.opt("af_sub2");
        if (objOpt == JSONObject.NULL) {
            objOpt = null;
        }
        return (String) objOpt;
    }

    @Nullable
    public String getAfSub3() {
        Object objOpt = this.f174.opt("af_sub3");
        if (objOpt == JSONObject.NULL) {
            objOpt = null;
        }
        return (String) objOpt;
    }

    @Nullable
    public String getAfSub4() {
        Object objOpt = this.f174.opt("af_sub4");
        if (objOpt == JSONObject.NULL) {
            objOpt = null;
        }
        return (String) objOpt;
    }

    @Nullable
    public String getAfSub5() {
        Object objOpt = this.f174.opt("af_sub5");
        if (objOpt == JSONObject.NULL) {
            objOpt = null;
        }
        return (String) objOpt;
    }

    @Nullable
    public Boolean isDeferred() {
        Object objOpt = this.f174.opt("is_deferred");
        if (objOpt == JSONObject.NULL) {
            objOpt = null;
        }
        return (Boolean) objOpt;
    }
}
