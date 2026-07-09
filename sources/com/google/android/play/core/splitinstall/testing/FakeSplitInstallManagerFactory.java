package com.google.android.play.core.splitinstall.testing;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.play.core.common.LocalTestingException;
import com.google.android.play.core.splitcompat.SplitCompat;
import com.google.android.play.core.splitinstall.k;
import com.google.android.play.core.splitinstall.p;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class FakeSplitInstallManagerFactory {

    @Nullable
    private static FakeSplitInstallManager a;

    public static FakeSplitInstallManager create(Context context) {
        try {
            File fileB = k.a(context).b();
            if (fileB == null) {
                throw new LocalTestingException("Failed to retrieve local testing directory path");
            }
            if (fileB.exists()) {
                return create(context, fileB);
            }
            throw new LocalTestingException(String.format("Local testing directory not found: %s", fileB));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized FakeSplitInstallManager create(Context context, File file) {
        if (a == null) {
            a = createNewInstance(context, file);
        } else if (!a.c().getAbsolutePath().equals(file.getAbsolutePath())) {
            throw new RuntimeException(String.format("Different module directories used to initialize FakeSplitInstallManager: '%s' and '%s'", a.c().getAbsolutePath(), file.getAbsolutePath()));
        }
        return a;
    }

    public static FakeSplitInstallManager createNewInstance(Context context, File file) {
        SplitCompat.install(context);
        return new FakeSplitInstallManager(context, file, new p(context, context.getPackageName()));
    }
}
