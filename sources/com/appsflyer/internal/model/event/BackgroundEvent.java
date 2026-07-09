package com.appsflyer.internal.model.event;

import android.content.Context;
import androidx.annotation.Nullable;
import com.appsflyer.AFEvent;
import com.appsflyer.AFHelper;

/* JADX INFO: loaded from: classes.dex */
public abstract class BackgroundEvent extends AFEvent {

    /* JADX INFO: renamed from: ɪ, reason: contains not printable characters */
    private boolean f314;

    /* JADX INFO: renamed from: ɾ, reason: contains not printable characters */
    private final boolean f315;

    /* JADX INFO: renamed from: ӏ, reason: contains not printable characters */
    private final boolean f316;

    BackgroundEvent() {
        this(null, null, null, null, null);
    }

    public BackgroundEvent(@Nullable String str, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Boolean bool3, @Nullable Context context) {
        super(str, Boolean.valueOf(bool3 != null ? bool3.booleanValue() : false), context);
        this.f315 = bool != null ? bool.booleanValue() : true;
        this.f316 = bool2 != null ? bool2.booleanValue() : true;
    }

    public BackgroundEvent trackingStopped(boolean z) {
        this.f314 = z;
        return this;
    }

    public boolean trackingStopped() {
        return this.f314;
    }

    public boolean readResponse() {
        return this.f315;
    }

    public boolean proxyMode() {
        return this.f316;
    }

    public String body() {
        return AFHelper.toJsonObject(params()).toString();
    }
}
