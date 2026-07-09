package com.google.android.play.core.assetpacks;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class bo {
    private final Map<String, Double> a = new HashMap();

    bo() {
    }

    final synchronized void a(String str) {
        this.a.put(str, Double.valueOf(0.0d));
    }

    final synchronized double b(String str) {
        Double d = this.a.get(str);
        if (d == null) {
            return 0.0d;
        }
        return d.doubleValue();
    }

    final synchronized double c(String str, cc ccVar) {
        double d;
        d = (((double) ((bi) ccVar).e) + 1.0d) / ((double) ((bi) ccVar).f);
        this.a.put(str, Double.valueOf(d));
        return d;
    }
}
