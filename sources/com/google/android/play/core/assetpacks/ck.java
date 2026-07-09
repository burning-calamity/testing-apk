package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.util.Base64;
import com.google.android.play.core.assetpacks.model.AssetPackStatus;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ck {
    private static a a;

    static String a(List<File> list) throws NoSuchAlgorithmException, IOException {
        int i;
        MessageDigest messageDigest = MessageDigest.getInstance("SHA256");
        byte[] bArr = new byte[8192];
        Iterator<File> it = list.iterator();
        while (it.hasNext()) {
            FileInputStream fileInputStream = new FileInputStream(it.next());
            do {
                try {
                    i = fileInputStream.read(bArr);
                    if (i > 0) {
                        messageDigest.update(bArr, 0, i);
                    }
                } catch (Throwable th) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        com.google.android.play.core.internal.bz.a(th, th2);
                    }
                    throw th;
                }
            } while (i != -1);
            fileInputStream.close();
        }
        return Base64.encodeToString(messageDigest.digest(), 11);
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x014f, code lost:
    
        if (r11 != null) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0151, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0154, code lost:
    
        r1 = r11.longValue();
        r3 = new byte[8];
        r4.seek(22 + r1);
        r4.readFully(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x017e, code lost:
    
        return com.google.android.play.core.assetpacks.AssetLocation.a(r21, ((r1 + 30) + ((long) e(r3, 4))) + ((long) e(r3, 6)), d(r3, 0));
     */
    @android.support.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static com.google.android.play.core.assetpacks.AssetLocation b(java.lang.String r21, java.lang.String r22) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 419
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.assetpacks.ck.b(java.lang.String, java.lang.String):com.google.android.play.core.assetpacks.AssetLocation");
    }

    static int c(byte[] bArr, int i) {
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    static long d(byte[] bArr, int i) {
        return ((long) ((e(bArr, i + 2) << 16) | e(bArr, i))) & 4294967295L;
    }

    static int e(byte[] bArr, int i) {
        return ((bArr[i + 1] & 255) << 8) | (bArr[i] & 255);
    }

    public static boolean f(@AssetPackStatus int i) {
        return i == 1 || i == 7 || i == 2 || i == 3;
    }

    public static boolean g(@AssetPackStatus int i) {
        return i == 5 || i == 6 || i == 4;
    }

    public static boolean h(@AssetPackStatus int i) {
        return i == 2 || i == 7 || i == 3;
    }

    static boolean i(@AssetPackStatus int i, @AssetPackStatus int i2) {
        if (i == 5) {
            if (i2 != 5) {
                return true;
            }
            i = 5;
        }
        if (i == 6 && i2 != 6 && i2 != 5) {
            return true;
        }
        if (i == 4 && i2 != 4) {
            return true;
        }
        if (i == 3 && (i2 == 2 || i2 == 7 || i2 == 1 || i2 == 8)) {
            return true;
        }
        if (i == 2) {
            return i2 == 1 || i2 == 8;
        }
        return false;
    }

    static synchronized a j(Context context) {
        if (a == null) {
            bg bgVar = new bg(null);
            bgVar.b(new l(com.google.android.play.core.splitcompat.p.c(context)));
            a = bgVar.a();
        }
        return a;
    }

    private static bb k(byte[] bArr) {
        int iE = e(bArr, 10);
        d(bArr, 12);
        return new bb(d(bArr, 16), iE);
    }
}
