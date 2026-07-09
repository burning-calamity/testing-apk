package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class x {
    private final Context a;

    x(Context context) {
        this.a = context;
    }

    @Nullable
    static File c(Context context) {
        String string;
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null || (string = bundle.getString("local_testing_dir")) == null) {
                return null;
            }
            return new File(context.getExternalFilesDir(null), string);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    final s a() {
        return s.b(this.a);
    }

    final Context b() {
        return this.a;
    }
}
