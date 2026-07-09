package com.appsflyer.internal;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import com.appsflyer.AndroidUtils;
import com.appsflyer.AppsFlyerLibCore;
import com.appsflyer.ServerParameters;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/* JADX INFO: loaded from: classes.dex */
public class EventDataCollector {

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    private final SharedPreferences f187;

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    private long f188;

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    private final Application f189;

    public EventDataCollector(Context context) {
        this.f189 = (Application) context.getApplicationContext();
        this.f187 = AppsFlyerLibCore.getSharedPreferences(this.f189);
    }

    public String signature() throws NoSuchAlgorithmException, PackageManager.NameNotFoundException, CertificateException {
        return AndroidUtils.signature(this.f189.getPackageManager(), this.f189.getPackageName());
    }

    public long bootTime() {
        return System.currentTimeMillis() - SystemClock.elapsedRealtime();
    }

    public String disk() {
        long availableBlocks;
        long blockCount;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
        if (Build.VERSION.SDK_INT >= 18) {
            long blockSizeLong = statFs.getBlockSizeLong();
            availableBlocks = statFs.getAvailableBlocksLong() * blockSizeLong;
            blockCount = statFs.getBlockCountLong() * blockSizeLong;
        } else {
            int blockSize = statFs.getBlockSize();
            availableBlocks = statFs.getAvailableBlocks() * blockSize;
            blockCount = statFs.getBlockCount() * blockSize;
        }
        double dPow = Math.pow(2.0d, 20.0d);
        StringBuilder sb = new StringBuilder();
        sb.append((long) (availableBlocks / dPow));
        sb.append("/");
        sb.append((long) (blockCount / dPow));
        return sb.toString();
    }

    public long get(String str) {
        return this.f187.getLong(str, 0L);
    }

    public void set(String str, long j) {
        this.f187.edit().putLong(str, j).apply();
    }

    public void init() {
        if (m140()) {
            this.f188 = System.currentTimeMillis();
        }
    }

    public void foreground() {
        if (m140()) {
            set(ServerParameters.FOREGROUND, System.currentTimeMillis() - this.f188);
        }
    }

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    private boolean m140() {
        return AppsFlyerLibCore.getInstance().getLaunchCounter(this.f187, false) == 0;
    }
}
