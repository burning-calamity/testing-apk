package com.appsflyer.deeplink;

import androidx.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class DeepLinkResult {

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    private final DeepLink f175;

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    private final Error f176;

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    private final e f177;

    public enum Error {
        TIMEOUT,
        NETWORK,
        HTTP_STATUS_CODE,
        UNEXPECTED
    }

    enum e {
        FOUND,
        NOT_FOUND,
        ERROR
    }

    public DeepLinkResult(@Nullable DeepLink deepLink, @Nullable Error error) {
        this.f175 = deepLink;
        this.f176 = error;
        if (error != null) {
            this.f177 = e.ERROR;
        } else if (deepLink != null) {
            this.f177 = e.FOUND;
        } else {
            this.f177 = e.NOT_FOUND;
        }
    }

    public Error getError() {
        return this.f176;
    }

    public DeepLink getDeepLink() {
        return this.f175;
    }

    public e getStatus() {
        return this.f177;
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("deepLink", this.f175);
            jSONObject.put("error", this.f176);
            jSONObject.put("status", this.f177);
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }
}
