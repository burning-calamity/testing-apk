package com.google.android.play.core.splitcompat;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.StrictMode;
import android.util.Log;
import com.google.android.play.core.internal.bh;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    private final c a;

    public a(c cVar) {
        this.a = cVar;
    }

    public static final int c(AssetManager assetManager, File file) {
        int iIntValue = ((Integer) bh.a(assetManager, "addAssetPath", Integer.class, String.class, file.getPath())).intValue();
        StringBuilder sb = new StringBuilder(39);
        sb.append("addAssetPath completed with ");
        sb.append(iIntValue);
        Log.d("SplitCompat", sb.toString());
        return iIntValue;
    }

    final synchronized boolean a(Context context, Set<String> set) {
        StrictMode.ThreadPolicy threadPolicy;
        boolean z;
        try {
            threadPolicy = StrictMode.getThreadPolicy();
            try {
                StrictMode.allowThreadDiskReads();
                StrictMode.allowThreadDiskWrites();
            } catch (Exception e) {
                e = e;
                Log.i("SplitCompat", "Unable to set up strict mode.", e);
            }
        } catch (Exception e2) {
            e = e2;
            threadPolicy = null;
        }
        try {
            try {
                HashSet hashSet = new HashSet();
                Iterator<String> it = set.iterator();
                while (it.hasNext()) {
                    hashSet.add(this.a.c(it.next()));
                }
                b(context, hashSet);
                if (threadPolicy != null) {
                    StrictMode.setThreadPolicy(threadPolicy);
                }
                z = true;
            } catch (Exception e3) {
                Log.e("SplitCompat", "Error installing additional splits", e3);
                if (threadPolicy != null) {
                    StrictMode.setThreadPolicy(threadPolicy);
                }
                z = false;
            }
        } catch (Throwable th) {
            if (threadPolicy != null) {
                StrictMode.setThreadPolicy(threadPolicy);
            }
            throw th;
        }
        return z;
    }

    public final synchronized void b(Context context, Set<File> set) {
        AssetManager assets = context.getAssets();
        Iterator<File> it = set.iterator();
        while (it.hasNext()) {
            c(assets, it.next());
        }
    }
}
