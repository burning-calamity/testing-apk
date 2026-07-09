package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
final class av {
    private final Context a;

    av(@NonNull Context context) {
        this.a = context;
    }

    private final SharedPreferences c() {
        return this.a.getSharedPreferences("playcore_split_install_internal", 0);
    }

    final synchronized Set<String> a() {
        Set<String> stringSet;
        try {
            stringSet = c().getStringSet("deferred_uninstall_module_list", new HashSet());
            if (stringSet == null) {
                stringSet = new HashSet<>();
            }
        } catch (Exception unused) {
            return new HashSet();
        }
        return stringSet;
    }

    final synchronized void b(Collection<String> collection) {
        Set<String> setA = a();
        Iterator<String> it = collection.iterator();
        boolean zAdd = false;
        while (it.hasNext()) {
            zAdd |= setA.add(it.next());
        }
        if (zAdd) {
            try {
                c().edit().putStringSet("deferred_uninstall_module_list", setA).apply();
            } catch (Exception unused) {
            }
        }
    }
}
