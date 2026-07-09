package com.google.android.gms.internal.measurement;

import androidx.core.view.MotionEventCompat;
import com.google.android.gms.internal.measurement.zzew;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzeu<T extends zzew<T>> {
    private static final zzeu zzd = new zzeu(true);
    final zzhg<T, Object> zza;
    private boolean zzb;
    private boolean zzc;

    private zzeu() {
        this.zza = zzhg.zza(16);
    }

    private zzeu(boolean z) {
        this(zzhg.zza(0));
        zzb();
    }

    private zzeu(zzhg<T, Object> zzhgVar) {
        this.zza = zzhgVar;
        zzb();
    }

    public static <T extends zzew<T>> zzeu<T> zza() {
        return zzd;
    }

    public final void zzb() {
        if (this.zzb) {
            return;
        }
        this.zza.zza();
        this.zzb = true;
    }

    public final boolean zzc() {
        return this.zzb;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzeu) {
            return this.zza.equals(((zzeu) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final Iterator<Map.Entry<T, Object>> zzd() {
        if (this.zzc) {
            return new zzfs(this.zza.entrySet().iterator());
        }
        return this.zza.entrySet().iterator();
    }

    final Iterator<Map.Entry<T, Object>> zze() {
        if (this.zzc) {
            return new zzfs(this.zza.zze().iterator());
        }
        return this.zza.zze().iterator();
    }

    private final Object zza(T t) {
        Object obj = this.zza.get(t);
        if (!(obj instanceof zzfr)) {
            return obj;
        }
        return zzfr.zza();
    }

    private final void zzb(T t, Object obj) {
        if (t.zzd()) {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                zza(t.zzb(), obj2);
            }
            obj = arrayList;
        } else {
            zza(t.zzb(), obj);
        }
        if (obj instanceof zzfr) {
            this.zzc = true;
        }
        this.zza.put(t, obj);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:4:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void zza(com.google.android.gms.internal.measurement.zzik r2, java.lang.Object r3) {
        /*
            com.google.android.gms.internal.measurement.zzfh.zza(r3)
            int[] r0 = com.google.android.gms.internal.measurement.zzex.zza
            com.google.android.gms.internal.measurement.zzir r2 = r2.zza()
            int r2 = r2.ordinal()
            r2 = r0[r2]
            r0 = 1
            r1 = 0
            switch(r2) {
                case 1: goto L40;
                case 2: goto L3d;
                case 3: goto L3a;
                case 4: goto L37;
                case 5: goto L34;
                case 6: goto L31;
                case 7: goto L28;
                case 8: goto L1f;
                case 9: goto L16;
                default: goto L14;
            }
        L14:
            r0 = 0
            goto L42
        L16:
            boolean r2 = r3 instanceof com.google.android.gms.internal.measurement.zzgm
            if (r2 != 0) goto L42
            boolean r2 = r3 instanceof com.google.android.gms.internal.measurement.zzfr
            if (r2 == 0) goto L14
            goto L42
        L1f:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L42
            boolean r2 = r3 instanceof com.google.android.gms.internal.measurement.zzfg
            if (r2 == 0) goto L14
            goto L42
        L28:
            boolean r2 = r3 instanceof com.google.android.gms.internal.measurement.zzdw
            if (r2 != 0) goto L42
            boolean r2 = r3 instanceof byte[]
            if (r2 == 0) goto L14
            goto L42
        L31:
            boolean r0 = r3 instanceof java.lang.String
            goto L42
        L34:
            boolean r0 = r3 instanceof java.lang.Boolean
            goto L42
        L37:
            boolean r0 = r3 instanceof java.lang.Double
            goto L42
        L3a:
            boolean r0 = r3 instanceof java.lang.Float
            goto L42
        L3d:
            boolean r0 = r3 instanceof java.lang.Long
            goto L42
        L40:
            boolean r0 = r3 instanceof java.lang.Integer
        L42:
            if (r0 == 0) goto L45
            return
        L45:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Wrong object type used with protocol message reflection."
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzeu.zza(com.google.android.gms.internal.measurement.zzik, java.lang.Object):void");
    }

    public final boolean zzf() {
        for (int i = 0; i < this.zza.zzc(); i++) {
            if (!zza((Map.Entry) this.zza.zzb(i))) {
                return false;
            }
        }
        Iterator it = this.zza.zzd().iterator();
        while (it.hasNext()) {
            if (!zza((Map.Entry) it.next())) {
                return false;
            }
        }
        return true;
    }

    private static <T extends zzew<T>> boolean zza(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        if (key.zzc() == zzir.MESSAGE) {
            if (key.zzd()) {
                Iterator it = ((List) entry.getValue()).iterator();
                while (it.hasNext()) {
                    if (!((zzgm) it.next()).g_()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzgm) {
                    if (!((zzgm) value).g_()) {
                        return false;
                    }
                } else {
                    if (value instanceof zzfr) {
                        return true;
                    }
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public final void zza(zzeu<T> zzeuVar) {
        for (int i = 0; i < zzeuVar.zza.zzc(); i++) {
            zzb(zzeuVar.zza.zzb(i));
        }
        Iterator it = zzeuVar.zza.zzd().iterator();
        while (it.hasNext()) {
            zzb((Map.Entry) it.next());
        }
    }

    private static Object zza(Object obj) {
        if (obj instanceof zzgv) {
            return ((zzgv) obj).clone();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private final void zzb(Map.Entry<T, Object> entry) {
        zzgm zzgmVarZzv;
        T key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzfr) {
            value = zzfr.zza();
        }
        if (key.zzd()) {
            Object objZza = zza((zzew) key);
            if (objZza == null) {
                objZza = new ArrayList();
            }
            Iterator it = ((List) value).iterator();
            while (it.hasNext()) {
                ((List) objZza).add(zza(it.next()));
            }
            this.zza.put(key, objZza);
            return;
        }
        if (key.zzc() == zzir.MESSAGE) {
            Object objZza2 = zza((zzew) key);
            if (objZza2 == null) {
                this.zza.put(key, zza(value));
                return;
            }
            if (objZza2 instanceof zzgv) {
                zzgmVarZzv = key.zza((zzgv) objZza2, (zzgv) value);
            } else {
                zzgmVarZzv = key.zza(((zzgm) objZza2).zzbq(), (zzgm) value).zzv();
            }
            this.zza.put(key, zzgmVarZzv);
            return;
        }
        this.zza.put(key, zza(value));
    }

    static void zza(zzel zzelVar, zzik zzikVar, int i, Object obj) throws IOException {
        if (zzikVar == zzik.zzj) {
            zzgm zzgmVar = (zzgm) obj;
            zzfh.zza(zzgmVar);
            zzelVar.zza(i, 3);
            zzgmVar.zza(zzelVar);
            zzelVar.zza(i, 4);
        }
        zzelVar.zza(i, zzikVar.zzb());
        switch (zzex.zzb[zzikVar.ordinal()]) {
            case 1:
                zzelVar.zza(((Double) obj).doubleValue());
                break;
            case 2:
                zzelVar.zza(((Float) obj).floatValue());
                break;
            case 3:
                zzelVar.zza(((Long) obj).longValue());
                break;
            case 4:
                zzelVar.zza(((Long) obj).longValue());
                break;
            case 5:
                zzelVar.zza(((Integer) obj).intValue());
                break;
            case 6:
                zzelVar.zzc(((Long) obj).longValue());
                break;
            case 7:
                zzelVar.zzd(((Integer) obj).intValue());
                break;
            case 8:
                zzelVar.zza(((Boolean) obj).booleanValue());
                break;
            case 9:
                ((zzgm) obj).zza(zzelVar);
                break;
            case 10:
                zzelVar.zza((zzgm) obj);
                break;
            case 11:
                if (obj instanceof zzdw) {
                    zzelVar.zza((zzdw) obj);
                } else {
                    zzelVar.zza((String) obj);
                }
                break;
            case MotionEventCompat.AXIS_RX /* 12 */:
                if (obj instanceof zzdw) {
                    zzelVar.zza((zzdw) obj);
                } else {
                    byte[] bArr = (byte[]) obj;
                    zzelVar.zzb(bArr, 0, bArr.length);
                }
                break;
            case 13:
                zzelVar.zzb(((Integer) obj).intValue());
                break;
            case 14:
                zzelVar.zzd(((Integer) obj).intValue());
                break;
            case 15:
                zzelVar.zzc(((Long) obj).longValue());
                break;
            case 16:
                zzelVar.zzc(((Integer) obj).intValue());
                break;
            case 17:
                zzelVar.zzb(((Long) obj).longValue());
                break;
            case 18:
                if (obj instanceof zzfg) {
                    zzelVar.zza(((zzfg) obj).zza());
                } else {
                    zzelVar.zza(((Integer) obj).intValue());
                }
                break;
        }
    }

    public final int zzg() {
        int iZzc = 0;
        for (int i = 0; i < this.zza.zzc(); i++) {
            iZzc += zzc(this.zza.zzb(i));
        }
        Iterator it = this.zza.zzd().iterator();
        while (it.hasNext()) {
            iZzc += zzc((Map.Entry) it.next());
        }
        return iZzc;
    }

    private static int zzc(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        Object value = entry.getValue();
        if (key.zzc() == zzir.MESSAGE && !key.zzd() && !key.zze()) {
            if (value instanceof zzfr) {
                return zzel.zzb(entry.getKey().zza(), (zzfr) value);
            }
            return zzel.zzb(entry.getKey().zza(), (zzgm) value);
        }
        return zza((zzew<?>) key, value);
    }

    static int zza(zzik zzikVar, int i, Object obj) {
        int iZze = zzel.zze(i);
        if (zzikVar == zzik.zzj) {
            zzfh.zza((zzgm) obj);
            iZze <<= 1;
        }
        return iZze + zzb(zzikVar, obj);
    }

    private static int zzb(zzik zzikVar, Object obj) {
        switch (zzex.zzb[zzikVar.ordinal()]) {
            case 1:
                return zzel.zzb(((Double) obj).doubleValue());
            case 2:
                return zzel.zzb(((Float) obj).floatValue());
            case 3:
                return zzel.zzd(((Long) obj).longValue());
            case 4:
                return zzel.zze(((Long) obj).longValue());
            case 5:
                return zzel.zzf(((Integer) obj).intValue());
            case 6:
                return zzel.zzg(((Long) obj).longValue());
            case 7:
                return zzel.zzi(((Integer) obj).intValue());
            case 8:
                return zzel.zzb(((Boolean) obj).booleanValue());
            case 9:
                return zzel.zzc((zzgm) obj);
            case 10:
                if (obj instanceof zzfr) {
                    return zzel.zza((zzfr) obj);
                }
                return zzel.zzb((zzgm) obj);
            case 11:
                if (obj instanceof zzdw) {
                    return zzel.zzb((zzdw) obj);
                }
                return zzel.zzb((String) obj);
            case MotionEventCompat.AXIS_RX /* 12 */:
                if (obj instanceof zzdw) {
                    return zzel.zzb((zzdw) obj);
                }
                return zzel.zzb((byte[]) obj);
            case 13:
                return zzel.zzg(((Integer) obj).intValue());
            case 14:
                return zzel.zzj(((Integer) obj).intValue());
            case 15:
                return zzel.zzh(((Long) obj).longValue());
            case 16:
                return zzel.zzh(((Integer) obj).intValue());
            case 17:
                return zzel.zzf(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzfg) {
                    return zzel.zzk(((zzfg) obj).zza());
                }
                return zzel.zzk(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int zza(zzew<?> zzewVar, Object obj) {
        zzik zzikVarZzb = zzewVar.zzb();
        int iZza = zzewVar.zza();
        if (zzewVar.zzd()) {
            int iZza2 = 0;
            if (zzewVar.zze()) {
                Iterator it = ((List) obj).iterator();
                while (it.hasNext()) {
                    iZza2 += zzb(zzikVarZzb, it.next());
                }
                return zzel.zze(iZza) + iZza2 + zzel.zzl(iZza2);
            }
            Iterator it2 = ((List) obj).iterator();
            while (it2.hasNext()) {
                iZza2 += zza(zzikVarZzb, iZza, it2.next());
            }
            return iZza2;
        }
        return zza(zzikVarZzb, iZza, obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzeu zzeuVar = new zzeu();
        for (int i = 0; i < this.zza.zzc(); i++) {
            Map.Entry<K, Object> entryZzb = this.zza.zzb(i);
            zzeuVar.zzb((zzew) entryZzb.getKey(), entryZzb.getValue());
        }
        Iterator it = this.zza.zzd().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            zzeuVar.zzb((zzew) entry.getKey(), entry.getValue());
        }
        zzeuVar.zzc = this.zzc;
        return zzeuVar;
    }
}
