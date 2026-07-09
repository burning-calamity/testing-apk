package com.appsflyer.internal;

import android.content.Context;
import com.appsflyer.AFFacebookDeferredDeeplink;
import com.appsflyer.AFLogger;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class u {

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    private static u f348 = new u();

    private u() {
    }

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    public static u m217() {
        return f348;
    }

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    public static File m216(Context context) {
        return new File(context.getFilesDir(), "AFRequestCache");
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    public static List<AFFacebookDeferredDeeplink> m218(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            File file = new File(context.getFilesDir(), "AFRequestCache");
            if (!file.exists()) {
                file.mkdir();
            } else {
                for (File file2 : file.listFiles()) {
                    StringBuilder sb = new StringBuilder("Found cached request");
                    sb.append(file2.getName());
                    AFLogger.afInfoLog(sb.toString());
                    arrayList.add(m219(file2));
                }
            }
        } catch (Exception e) {
            AFLogger.afErrorLog("Could not cache request", e);
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    public static AFFacebookDeferredDeeplink m219(File file) throws Throwable {
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
        } catch (Exception unused) {
            fileReader = null;
        } catch (Throwable th) {
            th = th;
            fileReader = null;
        }
        try {
            char[] cArr = new char[(int) file.length()];
            fileReader.read(cArr);
            AFFacebookDeferredDeeplink aFFacebookDeferredDeeplink = new AFFacebookDeferredDeeplink(cArr);
            aFFacebookDeferredDeeplink.f28 = file.getName();
            try {
                fileReader.close();
            } catch (IOException e) {
                AFLogger.afErrorLog(e);
            }
            return aFFacebookDeferredDeeplink;
        } catch (Exception unused2) {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e2) {
                    AFLogger.afErrorLog(e2);
                }
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e3) {
                    AFLogger.afErrorLog(e3);
                }
            }
            throw th;
        }
    }

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    public static void m215(String str, Context context) {
        File file = new File(new File(context.getFilesDir(), "AFRequestCache"), str);
        StringBuilder sb = new StringBuilder("Deleting ");
        sb.append(str);
        sb.append(" from cache");
        AFLogger.afInfoLog(sb.toString());
        if (file.exists()) {
            try {
                file.delete();
            } catch (Exception e) {
                StringBuilder sb2 = new StringBuilder("Could not delete ");
                sb2.append(str);
                sb2.append(" from cache");
                AFLogger.afErrorLog(sb2.toString(), e);
            }
        }
    }
}
