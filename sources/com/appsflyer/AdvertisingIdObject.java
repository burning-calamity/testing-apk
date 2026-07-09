package com.appsflyer;

import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class AdvertisingIdObject {

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    private final Boolean f75;

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    private final String f76;

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    private Boolean f77;

    AdvertisingIdObject(String str, Boolean bool) {
        this.f76 = str;
        this.f75 = bool;
    }

    public Boolean isManual() {
        return this.f77;
    }

    public void setManual(boolean z) {
        this.f77 = Boolean.valueOf(z);
    }

    public String getAdvertisingId() {
        return this.f76;
    }

    @Nullable
    public Boolean isLimitAdTracking() {
        return this.f75;
    }
}
