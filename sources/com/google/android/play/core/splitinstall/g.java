package com.google.android.play.core.splitinstall;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class g {
    private final Map<String, Map<String, String>> a = new HashMap();

    public final h a() {
        HashMap map = new HashMap();
        for (Map.Entry<String, Map<String, String>> entry : this.a.entrySet()) {
            map.put(entry.getKey(), Collections.unmodifiableMap(new HashMap(entry.getValue())));
        }
        return new h(Collections.unmodifiableMap(map));
    }

    public final void b(String str, String str2, String str3) {
        if (!this.a.containsKey(str2)) {
            this.a.put(str2, new HashMap());
        }
        this.a.get(str2).put(str, str3);
    }
}
