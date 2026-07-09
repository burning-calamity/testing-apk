package com.appsflyer;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.appsflyer.attribution.AppsFlyerRequestListener;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class AFEvent {
    public final Map<String, Object> params;

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    @Nullable
    Map<String, Object> f7;

    /* JADX INFO: renamed from: Ɩ, reason: contains not printable characters */
    String f8;

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    WeakReference<Context> f9;

    /* JADX INFO: renamed from: ȷ, reason: contains not printable characters */
    private byte[] f10;

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    @Nullable
    String f11;

    /* JADX INFO: renamed from: ɪ, reason: contains not printable characters */
    @Nullable
    private AppsFlyerRequestListener f12;

    /* JADX INFO: renamed from: ɹ, reason: contains not printable characters */
    public int f13;

    /* JADX INFO: renamed from: ɾ, reason: contains not printable characters */
    private String f14;

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    String f15;

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    @Nullable
    Context f16;

    /* JADX INFO: renamed from: І, reason: contains not printable characters */
    String f17;

    /* JADX INFO: renamed from: і, reason: contains not printable characters */
    String f18;

    /* JADX INFO: renamed from: Ӏ, reason: contains not printable characters */
    boolean f19;

    /* JADX INFO: renamed from: ӏ, reason: contains not printable characters */
    private final boolean f20;

    public AFEvent() {
        this(null, null, null);
    }

    public AFEvent(@Nullable String str, @Nullable Boolean bool, @Nullable Context context) {
        this.params = new HashMap();
        this.f8 = str;
        this.f20 = bool != null ? bool.booleanValue() : true;
        this.f16 = context;
    }

    protected AFEvent context(Context context) {
        this.f16 = context;
        return this;
    }

    public Context context() {
        Context context = this.f16;
        if (context != null) {
            return context;
        }
        WeakReference<Context> weakReference = this.f9;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    protected AFEvent weakContext() {
        this.f9 = new WeakReference<>(this.f16);
        this.f16 = null;
        return this;
    }

    public AFEvent urlString(String str) {
        this.f17 = str;
        return this;
    }

    protected String urlString() {
        return this.f17;
    }

    public AFEvent requestListener(AppsFlyerRequestListener appsFlyerRequestListener) {
        this.f12 = appsFlyerRequestListener;
        return this;
    }

    @Nullable
    public AppsFlyerRequestListener getRequestListener() {
        return this.f12;
    }

    protected AFEvent key(String str) {
        this.f14 = str;
        return this;
    }

    public String key() {
        return this.f14;
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    final boolean m10() {
        return this.f19;
    }

    protected AFEvent addParams(Map<String, ?> map) {
        this.params.putAll(map);
        return this;
    }

    public Map<String, Object> params() {
        return this.params;
    }

    public AFEvent post(byte[] bArr) {
        this.f10 = bArr;
        return this;
    }

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    final byte[] m9() {
        return this.f10;
    }

    public boolean isEncrypt() {
        return this.f20;
    }

    @NonNull
    protected String addChannel(String str) {
        String configuredChannel = AppsFlyerLibCore.getInstance().getConfiguredChannel(context());
        return configuredChannel != null ? Uri.parse(str).buildUpon().appendQueryParameter("channel", configuredChannel).build().toString() : str;
    }
}
