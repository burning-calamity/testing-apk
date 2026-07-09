package com.appsflyer.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerLibCore;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.ServerParameters;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.ref.WeakReference;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public final class x {

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    private static String f349;

    x() {
    }

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    public static synchronized String m222(WeakReference<Context> weakReference) {
        if (weakReference.get() == null) {
            return f349;
        }
        if (f349 == null) {
            String string = null;
            if (weakReference.get() != null) {
                string = AppsFlyerLibCore.getSharedPreferences(weakReference.get()).getString("AF_INSTALLATION", null);
            }
            if (string != null) {
                f349 = string;
            } else {
                try {
                    File file = new File(weakReference.get().getFilesDir(), "AF_INSTALLATION");
                    if (file.exists()) {
                        f349 = m223(file);
                        file.delete();
                    } else {
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        StringBuilder sb = new StringBuilder();
                        sb.append(jCurrentTimeMillis);
                        sb.append("-");
                        sb.append(Math.abs(new SecureRandom().nextLong()));
                        f349 = sb.toString();
                    }
                    String str = f349;
                    SharedPreferences.Editor editorEdit = AppsFlyerLibCore.getSharedPreferences(weakReference.get()).edit();
                    editorEdit.putString("AF_INSTALLATION", str);
                    editorEdit.apply();
                } catch (Exception e) {
                    AFLogger.afErrorLog("Error getting AF unique ID", e);
                }
            }
            if (f349 != null) {
                AppsFlyerProperties.getInstance().set(ServerParameters.AF_USER_ID, f349);
            }
        }
        return f349;
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    private static String m223(File file) throws Throwable {
        byte[] bArr;
        RandomAccessFile randomAccessFile = null;
        byte[] bArr2 = null;
        randomAccessFile = null;
        try {
            try {
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "r");
                try {
                    bArr2 = new byte[(int) randomAccessFile2.length()];
                    randomAccessFile2.readFully(bArr2);
                    randomAccessFile2.close();
                    try {
                        randomAccessFile2.close();
                    } catch (IOException e) {
                        AFLogger.afErrorLog("Exception while trying to close the InstallationFile", e);
                    }
                } catch (IOException e2) {
                    e = e2;
                    bArr = bArr2;
                    randomAccessFile = randomAccessFile2;
                    AFLogger.afErrorLog("Exception while reading InstallationFile: ", e);
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e3) {
                            AFLogger.afErrorLog("Exception while trying to close the InstallationFile", e3);
                        }
                    }
                    bArr2 = bArr;
                } catch (Throwable th) {
                    th = th;
                    randomAccessFile = randomAccessFile2;
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e4) {
                            AFLogger.afErrorLog("Exception while trying to close the InstallationFile", e4);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e5) {
            e = e5;
            bArr = null;
        }
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        return new String(bArr2);
    }
}
