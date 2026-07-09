package com.google.android.gms.internal.drive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class zziu implements Cloneable {
    private Object value;
    private zzis<?, ?> zznc;
    private List<zziz> zznd = new ArrayList();

    zziu() {
    }

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzaq()];
        zza(zzip.zzb(bArr));
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: zzbj, reason: merged with bridge method [inline-methods] */
    public final zziu clone() {
        Object objClone;
        zziu zziuVar = new zziu();
        try {
            zziuVar.zznc = this.zznc;
            if (this.zznd == null) {
                zziuVar.zznd = null;
            } else {
                zziuVar.zznd.addAll(this.zznd);
            }
            if (this.value != null) {
                if (this.value instanceof zzix) {
                    objClone = (zzix) ((zzix) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    objClone = ((byte[]) this.value).clone();
                } else {
                    int i = 0;
                    if (this.value instanceof byte[][]) {
                        byte[][] bArr = (byte[][]) this.value;
                        byte[][] bArr2 = new byte[bArr.length][];
                        zziuVar.value = bArr2;
                        while (i < bArr.length) {
                            bArr2[i] = (byte[]) bArr[i].clone();
                            i++;
                        }
                    } else if (this.value instanceof boolean[]) {
                        objClone = ((boolean[]) this.value).clone();
                    } else if (this.value instanceof int[]) {
                        objClone = ((int[]) this.value).clone();
                    } else if (this.value instanceof long[]) {
                        objClone = ((long[]) this.value).clone();
                    } else if (this.value instanceof float[]) {
                        objClone = ((float[]) this.value).clone();
                    } else if (this.value instanceof double[]) {
                        objClone = ((double[]) this.value).clone();
                    } else if (this.value instanceof zzix[]) {
                        zzix[] zzixVarArr = (zzix[]) this.value;
                        zzix[] zzixVarArr2 = new zzix[zzixVarArr.length];
                        zziuVar.value = zzixVarArr2;
                        while (i < zzixVarArr.length) {
                            zzixVarArr2[i] = (zzix) zzixVarArr[i].clone();
                            i++;
                        }
                    }
                }
                zziuVar.value = objClone;
            }
            return zziuVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        List<zziz> list;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zziu)) {
            return false;
        }
        zziu zziuVar = (zziu) obj;
        if (this.value == null || zziuVar.value == null) {
            List<zziz> list2 = this.zznd;
            if (list2 != null && (list = zziuVar.zznd) != null) {
                return list2.equals(list);
            }
            try {
                return Arrays.equals(toByteArray(), zziuVar.toByteArray());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        zzis<?, ?> zzisVar = this.zznc;
        if (zzisVar != zziuVar.zznc) {
            return false;
        }
        if (!zzisVar.zzmx.isArray()) {
            return this.value.equals(zziuVar.value);
        }
        Object obj2 = this.value;
        return obj2 instanceof byte[] ? Arrays.equals((byte[]) obj2, (byte[]) zziuVar.value) : obj2 instanceof int[] ? Arrays.equals((int[]) obj2, (int[]) zziuVar.value) : obj2 instanceof long[] ? Arrays.equals((long[]) obj2, (long[]) zziuVar.value) : obj2 instanceof float[] ? Arrays.equals((float[]) obj2, (float[]) zziuVar.value) : obj2 instanceof double[] ? Arrays.equals((double[]) obj2, (double[]) zziuVar.value) : obj2 instanceof boolean[] ? Arrays.equals((boolean[]) obj2, (boolean[]) zziuVar.value) : Arrays.deepEquals((Object[]) obj2, (Object[]) zziuVar.value);
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    final void zza(zzip zzipVar) throws IOException {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        for (zziz zzizVar : this.zznd) {
            zzipVar.zzp(zzizVar.tag);
            zzipVar.zzc(zzizVar.zzng);
        }
    }

    final void zza(zziz zzizVar) throws IOException {
        List<zziz> list = this.zznd;
        if (list != null) {
            list.add(zzizVar);
            return;
        }
        Object obj = this.value;
        if (!(obj instanceof zzix)) {
            if (obj instanceof zzix[]) {
                Collections.singletonList(zzizVar);
                throw new NoSuchMethodError();
            }
            Collections.singletonList(zzizVar);
            throw new NoSuchMethodError();
        }
        byte[] bArr = zzizVar.zzng;
        zzio zzioVarZza = zzio.zza(bArr, 0, bArr.length);
        int iZzbe = zzioVarZza.zzbe();
        if (iZzbe != bArr.length - zzip.zzm(iZzbe)) {
            throw zziw.zzbk();
        }
        zzix zzixVarZza = ((zzix) this.value).zza(zzioVarZza);
        this.zznc = this.zznc;
        this.value = zzixVarZza;
        this.zznd = null;
    }

    final int zzaq() {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        int iZzq = 0;
        for (zziz zzizVar : this.zznd) {
            iZzq += zzip.zzq(zzizVar.tag) + 0 + zzizVar.zzng.length;
        }
        return iZzq;
    }
}
