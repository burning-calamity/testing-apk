package com.appsflyer;

import android.net.TrafficStats;
import androidx.annotation.NonNull;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class AFExecutor {

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    private static AFExecutor f21;

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    ScheduledExecutorService f22;

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    ScheduledExecutorService f23;

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    Executor f24;

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    final ThreadFactory f25 = new ThreadFactory() { // from class: com.appsflyer.AFExecutor.1
        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(@NonNull final Runnable runnable) {
            return new Thread(new Runnable() { // from class: com.appsflyer.AFExecutor.1.1
                @Override // java.lang.Runnable
                public final void run() {
                    TrafficStats.setThreadStatsTag("AppsFlyer".hashCode());
                    runnable.run();
                }
            });
        }
    };

    private AFExecutor() {
    }

    public static AFExecutor getInstance() {
        if (f21 == null) {
            f21 = new AFExecutor();
        }
        return f21;
    }

    public Executor getThreadPoolExecutor() {
        Executor executor = this.f24;
        if (executor == null || ((executor instanceof ThreadPoolExecutor) && (((ThreadPoolExecutor) executor).isShutdown() || ((ThreadPoolExecutor) this.f24).isTerminated() || ((ThreadPoolExecutor) this.f24).isTerminating()))) {
            this.f24 = Executors.newFixedThreadPool(2, this.f25);
        }
        return this.f24;
    }

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    final ScheduledThreadPoolExecutor m12() {
        ScheduledExecutorService scheduledExecutorService = this.f22;
        if (scheduledExecutorService == null || scheduledExecutorService.isShutdown() || this.f22.isTerminated()) {
            this.f22 = Executors.newScheduledThreadPool(2, this.f25);
        }
        return (ScheduledThreadPoolExecutor) this.f22;
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    static void m11(ExecutorService executorService) {
        try {
            try {
                AFLogger.afRDLog("shut downing executor ...");
                executorService.shutdown();
                executorService.awaitTermination(10L, TimeUnit.SECONDS);
                if (!executorService.isTerminated()) {
                    AFLogger.afRDLog("killing non-finished tasks");
                }
                executorService.shutdownNow();
            } catch (InterruptedException unused) {
                AFLogger.afRDLog("InterruptedException!!!");
                if (!executorService.isTerminated()) {
                    AFLogger.afRDLog("killing non-finished tasks");
                }
                executorService.shutdownNow();
            }
        } catch (Throwable th) {
            if (!executorService.isTerminated()) {
                AFLogger.afRDLog("killing non-finished tasks");
            }
            executorService.shutdownNow();
            throw th;
        }
    }
}
