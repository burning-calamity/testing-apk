package com.appsflyer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.appsflyer.internal.p;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public final class AFSensorManager {

    @VisibleForTesting
    public static volatile AFSensorManager sInstance;

    /* JADX INFO: renamed from: ȷ, reason: contains not printable characters */
    private boolean f46;

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    boolean f47;

    /* JADX INFO: renamed from: ɾ, reason: contains not printable characters */
    private final SensorManager f49;

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    final Handler f50;

    /* JADX INFO: renamed from: ɹ, reason: contains not printable characters */
    private static final BitSet f42 = new BitSet(6);

    /* JADX INFO: renamed from: ɨ, reason: contains not printable characters */
    private static final Handler f41 = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    final Object f43 = new Object();

    /* JADX INFO: renamed from: ɪ, reason: contains not printable characters */
    private final Map<p, p> f48 = new HashMap(f42.size());

    /* JADX INFO: renamed from: ӏ, reason: contains not printable characters */
    private final Map<p, Map<String, Object>> f56 = new ConcurrentHashMap(f42.size());

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    final Runnable f45 = new Runnable() { // from class: com.appsflyer.AFSensorManager.3
        @Override // java.lang.Runnable
        public final void run() {
            synchronized (AFSensorManager.this.f43) {
                final AFSensorManager aFSensorManager = AFSensorManager.this;
                aFSensorManager.f52.execute(new Runnable() { // from class: com.appsflyer.AFSensorManager.4
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            for (Sensor sensor : AFSensorManager.this.f49.getSensorList(-1)) {
                                if (AFSensorManager.m35(sensor.getType())) {
                                    p pVar = new p(sensor, AFSensorManager.this.f52);
                                    if (!AFSensorManager.this.f48.containsKey(pVar)) {
                                        AFSensorManager.this.f48.put(pVar, pVar);
                                    }
                                    AFSensorManager.this.f49.registerListener((SensorEventListener) AFSensorManager.this.f48.get(pVar), sensor, 0);
                                }
                            }
                        } catch (Throwable unused) {
                        }
                        AFSensorManager.this.f46 = true;
                    }
                });
                AFSensorManager.this.f50.postDelayed(AFSensorManager.this.f53, 100L);
                AFSensorManager.this.f47 = true;
            }
        }
    };

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    final Runnable f51 = new Runnable() { // from class: com.appsflyer.AFSensorManager.2
        @Override // java.lang.Runnable
        public final void run() {
            synchronized (AFSensorManager.this.f43) {
                AFSensorManager aFSensorManager = AFSensorManager.this;
                aFSensorManager.f52.execute(aFSensorManager.new AnonymousClass6());
            }
        }
    };

    /* JADX INFO: renamed from: Ɩ, reason: contains not printable characters */
    final Runnable f44 = new Runnable() { // from class: com.appsflyer.AFSensorManager.1
        @Override // java.lang.Runnable
        public final void run() {
            synchronized (AFSensorManager.this.f43) {
                if (AFSensorManager.this.f47) {
                    AFSensorManager.this.f50.removeCallbacks(AFSensorManager.this.f45);
                    AFSensorManager.this.f50.removeCallbacks(AFSensorManager.this.f51);
                    AFSensorManager aFSensorManager = AFSensorManager.this;
                    aFSensorManager.f52.execute(aFSensorManager.new AnonymousClass6());
                    AFSensorManager.this.f47 = false;
                }
            }
        }
    };

    /* JADX INFO: renamed from: Ӏ, reason: contains not printable characters */
    int f55 = 1;

    /* JADX INFO: renamed from: і, reason: contains not printable characters */
    long f54 = 0;

    /* JADX INFO: renamed from: г, reason: contains not printable characters */
    private final Runnable f53 = new Runnable() { // from class: com.appsflyer.AFSensorManager.5
        @Override // java.lang.Runnable
        public final void run() {
            synchronized (AFSensorManager.this.f43) {
                if (AFSensorManager.this.f55 == 0) {
                    AFSensorManager.this.f55 = 1;
                }
                AFSensorManager.this.f50.postDelayed(AFSensorManager.this.f51, ((long) AFSensorManager.this.f55) * 500);
            }
        }
    };

    /* JADX INFO: renamed from: І, reason: contains not printable characters */
    final Executor f52 = Executors.newSingleThreadExecutor();

    static {
        f42.set(1);
        f42.set(2);
        f42.set(4);
    }

    private AFSensorManager(@NonNull SensorManager sensorManager, Handler handler) {
        this.f49 = sensorManager;
        this.f50 = handler;
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    static AFSensorManager m34(Context context) {
        if (sInstance != null) {
            return sInstance;
        }
        return m36((SensorManager) context.getApplicationContext().getSystemService("sensor"), f41);
    }

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    private static AFSensorManager m36(SensorManager sensorManager, Handler handler) {
        if (sInstance == null) {
            synchronized (AFSensorManager.class) {
                if (sInstance == null) {
                    sInstance = new AFSensorManager(sensorManager, handler);
                }
            }
        }
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    public static boolean m35(int i) {
        return i >= 0 && f42.get(i);
    }

    /* JADX INFO: renamed from: com.appsflyer.AFSensorManager$6, reason: invalid class name */
    final class AnonymousClass6 implements Runnable {
        AnonymousClass6() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                if (!AFSensorManager.this.f48.isEmpty()) {
                    for (p pVar : AFSensorManager.this.f48.values()) {
                        AFSensorManager.this.f49.unregisterListener(pVar);
                        pVar.m203(AFSensorManager.this.f56, true);
                    }
                }
            } catch (Throwable unused) {
            }
            AFSensorManager.this.f55 = 0;
            AFSensorManager.this.f46 = false;
        }
    }

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    final List<Map<String, Object>> m39() {
        Iterator<p> it = this.f48.values().iterator();
        while (it.hasNext()) {
            it.next().m203(this.f56, true);
        }
        Map<p, Map<String, Object>> map = this.f56;
        if (map == null || map.isEmpty()) {
            return new CopyOnWriteArrayList(Collections.emptyList());
        }
        return new CopyOnWriteArrayList(this.f56.values());
    }

    @NonNull
    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    final List<Map<String, Object>> m40() {
        synchronized (this.f43) {
            if (!this.f48.isEmpty() && this.f46) {
                Iterator<p> it = this.f48.values().iterator();
                while (it.hasNext()) {
                    it.next().m203(this.f56, false);
                }
            }
            if (this.f56.isEmpty()) {
                return new CopyOnWriteArrayList(Collections.emptyList());
            }
            return new CopyOnWriteArrayList(this.f56.values());
        }
    }
}
