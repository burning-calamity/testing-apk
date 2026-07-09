package com.google.android.play.core.internal;

import android.util.Log;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
final class be implements ay {
    private final /* synthetic */ int a = 0;

    be() {
    }

    be(byte[] bArr) {
    }

    be(char[] cArr) {
    }

    @Override // com.google.android.play.core.internal.ay
    public final boolean a(Object obj, File file, File file2) {
        int i = this.a;
        if (i != 0) {
            if (i != 1) {
                return true;
            }
            return new File((String) bh.g(obj.getClass(), String.class, File.class, file, File.class, file2)).exists();
        }
        try {
            return !((Boolean) bh.f(Class.forName("dalvik.system.DexFile"), Boolean.class, String.class, file.getPath())).booleanValue();
        } catch (ClassNotFoundException unused) {
            Log.e("SplitCompat", "Unexpected missing dalvik.system.DexFile.");
            return false;
        }
    }
}
