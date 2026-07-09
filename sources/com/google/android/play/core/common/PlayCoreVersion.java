package com.google.android.play.core.common;

import android.os.Bundle;
import com.google.android.play.core.internal.ag;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class PlayCoreVersion {
    private static final Set<String> a = new HashSet(Arrays.asList("app_update", "review"));
    private static final Set<String> b = new HashSet(Arrays.asList("native", "unity"));
    private static final Map<String, Map<String, Integer>> c = new HashMap();
    private static final ag d = new ag("PlayCoreVersion");

    private PlayCoreVersion() {
    }

    public static synchronized Map<String, Integer> a(String str) {
        if (!c.containsKey(str)) {
            HashMap map = new HashMap();
            map.put("java", 11000);
            c.put(str, map);
        }
        return c.get(str);
    }

    public static synchronized void addVersion(String str, String str2, int i) {
        if (!a.contains(str)) {
            d.e("Illegal module name: %s", str);
        } else if (b.contains(str2)) {
            a(str).put(str2, Integer.valueOf(i));
        } else {
            d.e("Illegal platform name: %s", str2);
        }
    }

    public static Bundle b(String str) {
        Bundle bundle = new Bundle();
        Map<String, Integer> mapA = a(str);
        bundle.putInt("playcore_version_code", mapA.get("java").intValue());
        if (mapA.containsKey("native")) {
            bundle.putInt("playcore_native_version", mapA.get("native").intValue());
        }
        if (mapA.containsKey("unity")) {
            bundle.putInt("playcore_unity_version", mapA.get("unity").intValue());
        }
        return bundle;
    }
}
