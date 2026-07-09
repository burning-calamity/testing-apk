package com.google.android.gms.internal.firebase_messaging;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Deque;

/* JADX INFO: compiled from: com.google.firebase:firebase-messaging@@20.1.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zzj {
    private static final OutputStream zza = new zzi();

    private static byte[] zza(Deque<byte[]> deque, int i) {
        byte[] bArr = new byte[i];
        int i2 = i;
        while (i2 > 0) {
            byte[] bArrRemoveFirst = deque.removeFirst();
            int iMin = Math.min(i2, bArrRemoveFirst.length);
            System.arraycopy(bArrRemoveFirst, 0, bArr, i - i2, iMin);
            i2 -= iMin;
        }
        return bArr;
    }

    public static byte[] zza(InputStream inputStream) throws IOException {
        int i;
        zzg.zza(inputStream);
        ArrayDeque arrayDeque = new ArrayDeque(20);
        int iZza = 8192;
        for (int i2 = 0; i2 < 2147483639; i2 = i) {
            byte[] bArr = new byte[Math.min(iZza, 2147483639 - i2)];
            arrayDeque.add(bArr);
            i = i2;
            int i3 = 0;
            while (i3 < bArr.length) {
                int i4 = inputStream.read(bArr, i3, bArr.length - i3);
                if (i4 == -1) {
                    return zza(arrayDeque, i);
                }
                i3 += i4;
                i += i4;
            }
            iZza = zzn.zza(iZza, 2);
        }
        if (inputStream.read() == -1) {
            return zza(arrayDeque, 2147483639);
        }
        throw new OutOfMemoryError("input is too large to fit in a byte array");
    }

    public static InputStream zza(InputStream inputStream, long j) {
        return new zzl(inputStream, 1048577L);
    }
}
