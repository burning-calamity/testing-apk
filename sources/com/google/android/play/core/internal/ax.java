package com.google.android.play.core.internal;

import android.os.Build;
import androidx.core.view.MotionEventCompat;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class ax {
    public static aw a() {
        if (Build.VERSION.SDK_INT < 21) {
            throw new AssertionError("Unsupported Android Version");
        }
        switch (Build.VERSION.SDK_INT) {
            case MotionEventCompat.AXIS_WHEEL /* 21 */:
                return new ba((byte[]) null);
            case MotionEventCompat.AXIS_GAS /* 22 */:
                return new ba();
            case MotionEventCompat.AXIS_BRAKE /* 23 */:
                return new ba((char[]) null);
            case MotionEventCompat.AXIS_DISTANCE /* 24 */:
                return new ba((short[]) null);
            case 25:
                return new ba((int[]) null);
            case MotionEventCompat.AXIS_SCROLL /* 26 */:
                return new ba((boolean[]) null);
            case MotionEventCompat.AXIS_RELATIVE_X /* 27 */:
                if (Build.VERSION.PREVIEW_SDK_INT == 0) {
                    return new ba((float[]) null);
                }
                break;
        }
        return new ba((byte[][]) null);
    }

    public static String b(File file) {
        if (!file.getName().endsWith(".apk")) {
            throw new IllegalArgumentException("Non-apk found in splits directory.");
        }
        String str = "";
        String strReplaceFirst = file.getName().replaceFirst("(_\\d+)?\\.apk", "");
        if (strReplaceFirst.equals("base-master")) {
            return "";
        }
        String str2 = "base-";
        if (strReplaceFirst.startsWith("base-")) {
            str = "config.";
        } else {
            strReplaceFirst = strReplaceFirst.replace("-", ".config.");
            str2 = ".config.master";
        }
        return strReplaceFirst.replace(str2, str);
    }

    public static void c(boolean z, Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    public static <T> void d(T t, Object obj) {
        if (t == null) {
            throw new NullPointerException((String) obj);
        }
    }
}
