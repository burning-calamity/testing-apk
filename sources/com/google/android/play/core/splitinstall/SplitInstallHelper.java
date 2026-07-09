package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class SplitInstallHelper {
    private static final com.google.android.play.core.internal.ag a = new com.google.android.play.core.internal.ag("SplitInstallHelper");

    private SplitInstallHelper() {
    }

    public static void loadLibrary(@NonNull Context context, @NonNull String str) throws UnsatisfiedLinkError {
        synchronized (k.class) {
            try {
                System.loadLibrary(str);
            } catch (UnsatisfiedLinkError e) {
                String str2 = context.getApplicationInfo().nativeLibraryDir;
                String strMapLibraryName = System.mapLibraryName(str);
                StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 1 + String.valueOf(strMapLibraryName).length());
                sb.append(str2);
                sb.append("/");
                sb.append(strMapLibraryName);
                String string = sb.toString();
                if (!new File(string).exists()) {
                    throw e;
                }
                System.load(string);
            }
        }
    }

    public static void updateAppInfo(@NonNull Context context) {
        if (Build.VERSION.SDK_INT <= 25 || Build.VERSION.SDK_INT >= 28) {
            return;
        }
        a.d("Calling dispatchPackageBroadcast", new Object[0]);
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Method method = cls.getMethod("currentActivityThread", new Class[0]);
            method.setAccessible(true);
            Object objInvoke = method.invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mAppThread");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(objInvoke);
            obj.getClass().getMethod("dispatchPackageBroadcast", Integer.TYPE, String[].class).invoke(obj, 3, new String[]{context.getPackageName()});
            a.d("Called dispatchPackageBroadcast", new Object[0]);
        } catch (Exception e) {
            a.c(e, "Update app info with dispatchPackageBroadcast failed!", new Object[0]);
        }
    }
}
