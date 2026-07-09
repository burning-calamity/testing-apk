package com.appsflyer.internal;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class p implements SensorEventListener {

    /* JADX INFO: renamed from: Ɩ, reason: contains not printable characters */
    private final Executor f320;

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    private final int f322;

    /* JADX INFO: renamed from: ɹ, reason: contains not printable characters */
    @NonNull
    private final String f323;

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    double f324;

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    long f325;

    /* JADX INFO: renamed from: І, reason: contains not printable characters */
    private final int f326;

    /* JADX INFO: renamed from: і, reason: contains not printable characters */
    @NonNull
    private final String f327;

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    final float[][] f319 = new float[2][];

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    final long[] f321 = new long[2];

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    public p(Sensor sensor, Executor executor) {
        this.f320 = executor;
        this.f322 = sensor.getType();
        String name = sensor.getName();
        this.f323 = name == null ? "" : name;
        String vendor = sensor.getVendor();
        this.f327 = vendor == null ? "" : vendor;
        this.f326 = ((((this.f322 + 31) * 31) + this.f323.hashCode()) * 31) + this.f327.hashCode();
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    static double m200(@NonNull float[] fArr, @NonNull float[] fArr2) {
        int iMin = Math.min(fArr.length, fArr2.length);
        double dPow = 0.0d;
        for (int i = 0; i < iMin; i++) {
            dPow += StrictMath.pow(fArr[i] - fArr2[i], 2.0d);
        }
        return Math.sqrt(dPow);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    public static boolean m198(Sensor sensor) {
        return (sensor == null || sensor.getName() == null || sensor.getVendor() == null) ? false : true;
    }

    @NonNull
    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    private static List<Float> m199(@NonNull float[] fArr) {
        ArrayList arrayList = new ArrayList(fArr.length);
        for (float f : fArr) {
            arrayList.add(Float.valueOf(f));
        }
        return arrayList;
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(final SensorEvent sensorEvent) {
        this.f320.execute(new Runnable() { // from class: com.appsflyer.internal.p.4
            @Override // java.lang.Runnable
            public final void run() {
                SensorEvent sensorEvent2 = sensorEvent;
                if (sensorEvent2 == null || sensorEvent2.values == null || !p.m198(sensorEvent.sensor)) {
                    return;
                }
                p pVar = p.this;
                int type = sensorEvent.sensor.getType();
                String name = sensorEvent.sensor.getName();
                String vendor = sensorEvent.sensor.getVendor();
                long j = sensorEvent.timestamp;
                float[] fArr = sensorEvent.values;
                if (pVar.m204(type, name, vendor)) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    float[] fArr2 = pVar.f319[0];
                    if (fArr2 == null) {
                        pVar.f319[0] = Arrays.copyOf(fArr, fArr.length);
                        pVar.f321[0] = jCurrentTimeMillis;
                        return;
                    }
                    float[] fArr3 = pVar.f319[1];
                    if (fArr3 == null) {
                        float[] fArrCopyOf = Arrays.copyOf(fArr, fArr.length);
                        pVar.f319[1] = fArrCopyOf;
                        pVar.f321[1] = jCurrentTimeMillis;
                        pVar.f324 = p.m200(fArr2, fArrCopyOf);
                        return;
                    }
                    if (50000000 <= j - pVar.f325) {
                        pVar.f325 = j;
                        if (Arrays.equals(fArr3, fArr)) {
                            pVar.f321[1] = jCurrentTimeMillis;
                            return;
                        }
                        double dM200 = p.m200(fArr2, fArr);
                        if (dM200 > pVar.f324) {
                            pVar.f319[1] = Arrays.copyOf(fArr, fArr.length);
                            pVar.f321[1] = jCurrentTimeMillis;
                            pVar.f324 = dM200;
                        }
                    }
                }
            }
        });
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    public final void m203(@NonNull Map<p, Map<String, Object>> map, boolean z) {
        if (m201()) {
            map.put(this, m197());
            if (z) {
                int length = this.f319.length;
                for (int i = 0; i < length; i++) {
                    this.f319[i] = null;
                }
                int length2 = this.f321.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    this.f321[i2] = 0;
                }
                this.f324 = 0.0d;
                this.f325 = 0L;
                return;
            }
            return;
        }
        if (map.containsKey(this)) {
            return;
        }
        map.put(this, m197());
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    final boolean m204(int i, @NonNull String str, @NonNull String str2) {
        return this.f322 == i && this.f323.equals(str) && this.f327.equals(str2);
    }

    @NonNull
    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    private Map<String, Object> m197() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(7);
        concurrentHashMap.put("sT", Integer.valueOf(this.f322));
        concurrentHashMap.put("sN", this.f323);
        concurrentHashMap.put("sV", this.f327);
        float[] fArr = this.f319[0];
        if (fArr != null) {
            concurrentHashMap.put("sVS", m199(fArr));
        }
        float[] fArr2 = this.f319[1];
        if (fArr2 != null) {
            concurrentHashMap.put("sVE", m199(fArr2));
        }
        return concurrentHashMap;
    }

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    private boolean m201() {
        return this.f319[0] != null;
    }

    public final int hashCode() {
        return this.f326;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return m204(pVar.f322, pVar.f323, pVar.f327);
    }
}
