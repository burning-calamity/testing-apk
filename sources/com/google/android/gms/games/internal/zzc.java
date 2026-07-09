package com.google.android.gms.games.internal;

import android.os.Bundle;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class zzc {
    public static int zza(@Nullable Bundle bundle) {
        int size;
        int iHashCode;
        int iKeyAt;
        int iHashCode2;
        int iHashCode3;
        if (bundle == null || (size = bundle.size()) == 0) {
            return 0;
        }
        String[] strArr = (String[]) bundle.keySet().toArray(new String[size]);
        Arrays.sort(strArr);
        int i = 1;
        for (String str : strArr) {
            i *= 31;
            Object obj = bundle.get(str);
            if (obj != null) {
                if (obj instanceof Bundle) {
                    iHashCode = zza((Bundle) obj);
                } else if (obj instanceof byte[]) {
                    iHashCode = Arrays.hashCode((byte[]) obj);
                } else if (obj instanceof char[]) {
                    iHashCode = Arrays.hashCode((char[]) obj);
                } else if (obj instanceof short[]) {
                    iHashCode = Arrays.hashCode((short[]) obj);
                } else if (obj instanceof float[]) {
                    iHashCode = Arrays.hashCode((float[]) obj);
                } else if (obj instanceof CharSequence[]) {
                    iHashCode = Arrays.hashCode((CharSequence[]) obj);
                } else {
                    if (obj instanceof Object[]) {
                        iKeyAt = 1;
                        for (Object obj2 : (Object[]) obj) {
                            iKeyAt *= 31;
                            if (obj2 instanceof Bundle) {
                                iHashCode3 = zza((Bundle) obj2);
                            } else if (obj2 != null) {
                                iHashCode3 = obj2.hashCode();
                            }
                            iKeyAt += iHashCode3;
                        }
                    } else if (obj instanceof SparseArray) {
                        SparseArray sparseArray = (SparseArray) obj;
                        int size2 = sparseArray.size();
                        iKeyAt = 1;
                        for (int i2 = 0; i2 < size2; i2++) {
                            iKeyAt = ((iKeyAt * 31) + sparseArray.keyAt(i2)) * 31;
                            Object objValueAt = sparseArray.valueAt(i2);
                            if (objValueAt instanceof Bundle) {
                                iHashCode2 = zza((Bundle) objValueAt);
                            } else if (objValueAt != null) {
                                iHashCode2 = objValueAt.hashCode();
                            }
                            iKeyAt += iHashCode2;
                        }
                    } else {
                        iHashCode = obj.hashCode();
                    }
                    i += iKeyAt;
                }
                i += iHashCode;
            }
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:159:0x00f4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:164:0x014a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0029 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0029 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean zza(@androidx.annotation.Nullable android.os.Bundle r10, @androidx.annotation.Nullable android.os.Bundle r11) {
        /*
            Method dump skipped, instruction units count: 340
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.games.internal.zzc.zza(android.os.Bundle, android.os.Bundle):boolean");
    }
}
