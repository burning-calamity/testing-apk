package com.google.android.play.core.common;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    private final Map<String, Object> a = new HashMap();
    private final AtomicBoolean b = new AtomicBoolean(false);

    private final synchronized void b() {
        this.a.put("assetOnlyUpdates", false);
    }

    public final synchronized boolean a() {
        if (!this.b.get()) {
            b();
        }
        Object obj = this.a.get("assetOnlyUpdates");
        if (!(obj instanceof Boolean)) {
            return false;
        }
        return ((Boolean) obj).booleanValue();
    }
}
