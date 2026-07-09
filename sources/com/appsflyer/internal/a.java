package com.appsflyer.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.appsflyer.AFDateFormat;
import com.appsflyer.AFLogger;
import com.appsflyer.HashUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    private static char[] f191 = {'a', 512, 1208, 1848, 2519, 3151, 3824, 4396, 4868, 5563, 6176, 6879, 7512, 8190, 8811, 9244, 9881, 10592, 11240, 11855, 12532, 13155, 13572, 14218, 14911, 15568, 16213, 16855, 17513, 17944, 18565, 19253, 19877, 20572, 28829, '0', 'y', 535, 1189, 1843, 2453, 3179, 3801, 4399, 4884, 5562, 6163, 6898, 7520, 8187, 8809, 9217, 9875, 10516, 'b', 527, 1214, 1839, 2521, 3139, 3748, 4407, 4937, 5562, 6265, 6793, 7497, 8180, 8765, 9281, 9940, 10536, 11144, 11855, 12460, 13155, 13632, 14290, 14900, 15495, 16157, 16892, 17466, 17990, 18645, 19299, 64703, 65246, 63590, 64486, 62729, 61585, 61998, 60914, 61377, 59763, 58556, 58918, 57751, 58172, 57006, 55497, 55884, 54761, 55087, 53909, 52264, 53177, 51661, 52057, 50940, 'a', 512, 1208, 1848, 2519, 3151, 3824, 4396, 4888, 5567, 6206, 6878, 7519, 8183, 8822, 9239, 9934, 10525, 11225, 11844, 12523, 13161, 13574, 'a', 512, 1208, 1848, 2519, 3151, 3824, 4396, 4895, 5549, 6242, 6907, 7515, 8175, 8810, 9233, 9908, 10543, 11215, 11841, 64960, 65441, 63769, 64153, 62582, 61934, 62289, 60557, 61119, 59418, 58777, 59189, 57564, 57925, 57292, 24078, '\\', 576, 4484, 5089, 5444, 5829, 6264, 7585, 7957, 194, 746, 1117, 2514, 2864, 3247, 3594, 11620, 12102, 10742, 10850, 9371, 8456, 1477, 1967, 280, 765, 3104, 2535, 2903, 5319, 5809, 4124, 'C', 518, 1209, 1833, 2515, 3157, 3809, 4463, 4917, 5542, 6191, 6879, 7512, 8162, 8813, 9245, 9870, 'F', 527, 1205, 1830, 2525, 3138, 3764, 4470, 4895, 5630, 6187, 6879, 7516, 8118, 8807, 9235, 9859, 10534, 11225, 11786, 12520, 13159, 13568, 14218, 14960, 15561, 16197, 16878, 17504, 18006, 18561, 19242, 19875, 20555, 21228, 21886, 22289, 22921, 23610, 24312, 24848, 'g', 523, 1192, 1795, 2518, 3157, 3808, 4451, 4892, 5554, 6185, 6878, 7529, 8166, 8820, 9246, 9865, 10541, 11229, 11870, 12529, 13161, 13594, 14225, 12107, 11534, 11185, 10273, 9947, 9053, 8681, 15975, 15402, 15027, 14114, 13790, 12869, 12541, 3448, 2879, 2448, 1573, 1233, 338, 8164, 7271, 6675, 6276, 'F', 527, 1205, 1830, 2525, 3138, 3764, 4459, 4894, 5544, 6179, 6865, 7501, 8118, 8822, 9239, 9862, 10530, 11225, 11849, 12524, 13155, 13584, 14274, 14909, 15579, 16216, 16882, 17511, 17938, 18628, 19237, 19881, 20570, 21236, 21802, 22301, 22942, 23607, 24231, 24896, 25578, 26213, 26645, 27270, 28012, 28644};

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    private static long f192 = -7069222829189430674L;

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    private static int f193 = 0;

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    private static int f194 = 1;

    a() {
    }

    @Nullable
    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    public static String m143(Context context, long j) {
        String strIntern;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        sb2.append((m148(m141(0, (char) 0, 34).intern()) ? m141(34, (char) 28844, 1) : m141(35, (char) 0, 1)).intern());
        StringBuilder sb4 = new StringBuilder();
        String packageName = context.getPackageName();
        String strM146 = m146(packageName);
        sb2.append(m141(34, (char) 28844, 1).intern());
        sb4.append(strM146);
        if (m147(context) == null) {
            int i = f193 + 81;
            f194 = i % 128;
            sb2.append((i % 2 == 0 ? m141(31, (char) 0, 1) : m141(35, (char) 0, 1)).intern());
            sb4.append(packageName);
        } else {
            sb2.append(m141(34, (char) 28844, 1).intern());
            sb4.append(packageName);
            int i2 = f194 + 123;
            f193 = i2 % 128;
            int i3 = i2 % 2;
        }
        String strM142 = m142(context);
        if ((strM142 == null ? 'E' : '6') != '6') {
            sb2.append(m141(35, (char) 0, 1).intern());
            sb4.append(packageName);
        } else {
            sb2.append(m141(34, (char) 28844, 1).intern());
            sb4.append(strM142);
        }
        sb4.append(m144(context, packageName));
        sb.append(sb4.toString());
        try {
            sb.append(AFDateFormat.getDataFormatter(m141(36, (char) 0, 18).intern()).format(new Date(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime)));
            sb.append(j);
            if ((m148(m141(86, (char) 64734, 25).intern()) ? '=' : (char) 0) != '=') {
                strIntern = m141(35, (char) 0, 1).intern();
            } else {
                int i4 = f194 + 79;
                f193 = i4 % 128;
                strIntern = i4 % 2 != 0 ? m141(119, (char) 28844, 0).intern() : m141(34, (char) 28844, 1).intern();
                int i5 = f193 + 81;
                f194 = i5 % 128;
                int i6 = i5 % 2;
            }
            sb3.append(strIntern);
            sb3.append(((m148(m141(111, (char) 0, 23).intern()) ? '\r' : '>') != '>' ? m141(34, (char) 28844, 1) : m141(35, (char) 0, 1)).intern());
            sb3.append(((m148(m141(134, (char) 0, 20).intern()) ? '\n' : ',') != ',' ? m141(34, (char) 28844, 1) : m141(35, (char) 0, 1)).intern());
            sb3.append((m148(m141(154, (char) 64929, 15).intern()) ? m141(34, (char) 28844, 1) : m141(35, (char) 0, 1)).intern());
            String md5 = HashUtils.toMD5(HashUtils.toSha256(sb.toString()));
            String string = sb2.toString();
            StringBuilder sb5 = new StringBuilder(md5);
            sb5.setCharAt(17, Integer.toString(Integer.parseInt(string, 2), 16).charAt(0));
            String string2 = sb5.toString();
            String string3 = sb3.toString();
            StringBuilder sb6 = new StringBuilder(string2);
            sb6.setCharAt(27, Integer.toString(Integer.parseInt(string3, 2), 16).charAt(0));
            return m145(sb6.toString(), Long.valueOf(j));
        } catch (PackageManager.NameNotFoundException unused) {
            return m141(54, (char) 0, 32).intern();
        }
    }

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    private static String m145(String str, Long l) {
        int i = f194 + 45;
        f193 = i % 128;
        int i2 = i % 2;
        if ((str != null ? 'U' : 'A') != 'U' || l == null || str.length() != 32) {
            return m141(54, (char) 0, 32).intern();
        }
        StringBuilder sb = new StringBuilder(str);
        String string = l.toString();
        long numericValue = 0;
        int i3 = f194 + 75;
        f193 = i3 % 128;
        int i4 = i3 % 2;
        int numericValue2 = 0;
        for (int i5 = 0; i5 < string.length(); i5++) {
            numericValue2 += Character.getNumericValue(string.charAt(i5));
        }
        String hexString = Integer.toHexString(numericValue2);
        sb.replace(7, hexString.length() + 7, hexString);
        int i6 = 0;
        while (true) {
            if (i6 >= sb.length()) {
                break;
            }
            int i7 = f193 + 115;
            f194 = i7 % 128;
            int i8 = i7 % 2;
            numericValue += (long) Character.getNumericValue(sb.charAt(i6));
            i6++;
        }
        while (numericValue > 100) {
            numericValue %= 100;
        }
        sb.insert(23, (int) numericValue);
        if (numericValue < 10) {
            sb.insert(23, m141(35, (char) 0, 1).intern());
            int i9 = f193 + 69;
            f194 = i9 % 128;
            int i10 = i9 % 2;
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    private static boolean m148(String str) {
        int i = f193 + 47;
        f194 = i % 128;
        if (i % 2 == 0) {
        }
        try {
            Class.forName(str);
            int i2 = f194 + 11;
            f193 = i2 % 128;
            int i3 = i2 % 2;
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0032, code lost:
    
        if ((!r10.contains(m141(31640, 24096, 0).intern()) ? 28 : 'S') != 28) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0041, code lost:
    
        if (r10.contains(m141(169, 24096, 1).intern()) == false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0043, code lost:
    
        return r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
    
        r10 = r10.split(m141(170, 0, 2).intern());
        r0 = r10.length;
        r2 = new java.lang.StringBuilder();
        r0 = r0 - 1;
        r2.append(r10[r0]);
        r2.append(m141(169, 24096, 1).intern());
        r7 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x006a, code lost:
    
        if (r7 >= r0) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x006c, code lost:
    
        r8 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x006e, code lost:
    
        r8 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x006f, code lost:
    
        if (r8 == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0071, code lost:
    
        r8 = com.appsflyer.internal.a.f193 + 77;
        com.appsflyer.internal.a.f194 = r8 % 128;
        r8 = r8 % 2;
        r2.append(r10[r7]);
        r2.append(m141(169, 24096, 1).intern());
        r7 = r7 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x008d, code lost:
    
        r2.append(r10[0]);
        r10 = r2.toString();
        r0 = com.appsflyer.internal.a.f193 + 67;
        com.appsflyer.internal.a.f194 = r0 % 128;
        r0 = r0 % 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x009f, code lost:
    
        return r10;
     */
    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String m146(java.lang.String r10) {
        /*
            int r0 = com.appsflyer.internal.a.f193
            int r0 = r0 + 95
            int r1 = r0 % 128
            com.appsflyer.internal.a.f194 = r1
            r1 = 2
            int r0 = r0 % r1
            r2 = 52
            if (r0 != 0) goto L11
            r0 = 35
            goto L13
        L11:
            r0 = 52
        L13:
            r3 = 169(0xa9, float:2.37E-43)
            r4 = 0
            r5 = 24096(0x5e20, float:3.3766E-41)
            r6 = 1
            if (r0 == r2) goto L35
            r0 = 31640(0x7b98, float:4.4337E-41)
            java.lang.String r0 = m141(r0, r5, r4)
            java.lang.String r0 = r0.intern()
            boolean r0 = r10.contains(r0)
            r2 = 28
            if (r0 != 0) goto L30
            r0 = 28
            goto L32
        L30:
            r0 = 83
        L32:
            if (r0 == r2) goto L43
            goto L44
        L35:
            java.lang.String r0 = m141(r3, r5, r6)
            java.lang.String r0 = r0.intern()
            boolean r0 = r10.contains(r0)
            if (r0 != 0) goto L44
        L43:
            return r10
        L44:
            r0 = 170(0xaa, float:2.38E-43)
            java.lang.String r0 = m141(r0, r4, r1)
            java.lang.String r0 = r0.intern()
            java.lang.String[] r10 = r10.split(r0)
            int r0 = r10.length
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            int r0 = r0 - r6
            r7 = r10[r0]
            r2.append(r7)
            java.lang.String r7 = m141(r3, r5, r6)
            java.lang.String r7 = r7.intern()
            r2.append(r7)
            r7 = 1
        L6a:
            if (r7 >= r0) goto L6e
            r8 = 1
            goto L6f
        L6e:
            r8 = 0
        L6f:
            if (r8 == 0) goto L8d
            int r8 = com.appsflyer.internal.a.f193
            int r8 = r8 + 77
            int r9 = r8 % 128
            com.appsflyer.internal.a.f194 = r9
            int r8 = r8 % r1
            r8 = r10[r7]
            r2.append(r8)
            java.lang.String r8 = m141(r3, r5, r6)
            java.lang.String r8 = r8.intern()
            r2.append(r8)
            int r7 = r7 + 1
            goto L6a
        L8d:
            r10 = r10[r4]
            r2.append(r10)
            java.lang.String r10 = r2.toString()
            int r0 = com.appsflyer.internal.a.f193
            int r0 = r0 + 67
            int r2 = r0 % 128
            com.appsflyer.internal.a.f194 = r2
            int r0 = r0 % r1
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.a.m146(java.lang.String):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0061  */
    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String m147(android.content.Context r8) {
        /*
            java.util.Properties r0 = java.lang.System.getProperties()
            r1 = 172(0xac, float:2.41E-43)
            r2 = 4590(0x11ee, float:6.432E-42)
            r3 = 14
            java.lang.String r1 = m141(r1, r2, r3)
            java.lang.String r1 = r1.intern()
            boolean r0 = r0.containsKey(r1)
            r1 = 0
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L61
            int r0 = com.appsflyer.internal.a.f193
            int r0 = r0 + 65
            int r4 = r0 % 128
            com.appsflyer.internal.a.f194 = r4
            int r0 = r0 % 2
            java.io.File r8 = r8.getCacheDir()     // Catch: java.lang.Exception -> L79
            java.lang.String r8 = r8.getPath()     // Catch: java.lang.Exception -> L79
            r0 = 186(0xba, float:2.6E-43)
            r4 = 11595(0x2d4b, float:1.6248E-41)
            r5 = 6
            java.lang.String r0 = m141(r0, r4, r5)     // Catch: java.lang.Exception -> L79
            java.lang.String r0 = r0.intern()     // Catch: java.lang.Exception -> L79
            java.lang.String r4 = ""
            java.lang.String r8 = r8.replace(r0, r4)     // Catch: java.lang.Exception -> L79
            r0 = 192(0xc0, float:2.69E-43)
            r4 = 1515(0x5eb, float:2.123E-42)
            r5 = 10
            java.lang.String r0 = m141(r0, r4, r5)     // Catch: java.lang.Exception -> L79
            java.lang.String r0 = r0.intern()     // Catch: java.lang.Exception -> L79
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)     // Catch: java.lang.Exception -> L79
            java.util.regex.Matcher r8 = r0.matcher(r8)     // Catch: java.lang.Exception -> L79
            boolean r0 = r8.find()     // Catch: java.lang.Exception -> L79
            if (r0 == 0) goto L5e
            r0 = 1
            goto L5f
        L5e:
            r0 = 0
        L5f:
            if (r0 == r3) goto L63
        L61:
            r8 = r1
            goto Lad
        L63:
            int r0 = com.appsflyer.internal.a.f193
            int r0 = r0 + 27
            int r4 = r0 % 128
            com.appsflyer.internal.a.f194 = r4
            int r0 = r0 % 2
            if (r0 != 0) goto L74
            java.lang.String r8 = r8.group(r2)     // Catch: java.lang.Exception -> L79
            goto Lad
        L74:
            java.lang.String r8 = r8.group(r3)     // Catch: java.lang.Exception -> L79
            goto Lad
        L79:
            r8 = move-exception
            com.appsflyer.internal.ac r0 = com.appsflyer.internal.ac.m168()
            r4 = 202(0xca, float:2.83E-43)
            r5 = 17
            java.lang.String r4 = m141(r4, r2, r5)
            java.lang.String r4 = r4.intern()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r6 = 219(0xdb, float:3.07E-43)
            r7 = 41
            java.lang.String r6 = m141(r6, r2, r7)
            java.lang.String r6 = r6.intern()
            r5.append(r6)
            r5.append(r8)
            java.lang.String r8 = r5.toString()
            java.lang.String[] r3 = new java.lang.String[r3]
            r3[r2] = r8
            r0.m175(r1, r4, r3)
            return r1
        Lad:
            int r0 = com.appsflyer.internal.a.f193
            int r0 = r0 + 23
            int r4 = r0 % 128
            com.appsflyer.internal.a.f194 = r4
            int r0 = r0 % 2
            if (r0 != 0) goto Lba
            goto Lbb
        Lba:
            r2 = 1
        Lbb:
            if (r2 == r3) goto Lc3
            super.hashCode()     // Catch: java.lang.Throwable -> Lc1
            return r8
        Lc1:
            r8 = move-exception
            throw r8
        Lc3:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.a.m147(android.content.Context):java.lang.String");
    }

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    private static String m142(Context context) {
        try {
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
            int i = f193 + 49;
            f194 = i % 128;
            int i2 = i % 2;
            int i3 = f193 + 25;
            f194 = i3 % 128;
            int i4 = i3 % 2;
            return str;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    private static String m144(Context context, String str) {
        Iterator it;
        try {
            it = ((List) PackageManager.class.getDeclaredMethod(m141(260, (char) 0, 24).intern(), Integer.TYPE).invoke(context.getPackageManager(), 0)).iterator();
            int i = f194 + 19;
            f193 = i % 128;
            int i2 = i % 2;
        } catch (IllegalAccessException e2) {
            ac acVarM168 = ac.m168();
            String strIntern = m141(284, (char) 12040, 24).intern();
            StringBuilder sb = new StringBuilder();
            sb.append(m141(308, (char) 0, 47).intern());
            sb.append(e2);
            acVarM168.m175(null, strIntern, sb.toString());
        } catch (NoSuchMethodException e3) {
            ac acVarM1682 = ac.m168();
            String strIntern2 = m141(284, (char) 12040, 24).intern();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(m141(308, (char) 0, 47).intern());
            sb2.append(e3);
            acVarM1682.m175(null, strIntern2, sb2.toString());
        } catch (InvocationTargetException e4) {
            ac acVarM1683 = ac.m168();
            String strIntern3 = m141(284, (char) 12040, 24).intern();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(m141(308, (char) 0, 47).intern());
            sb3.append(e4);
            acVarM1683.m175(null, strIntern3, sb3.toString());
        }
        while (it.hasNext()) {
            int i3 = f193 + 99;
            f194 = i3 % 128;
            int i4 = i3 % 2;
            if ((((PackageItemInfo) ((ApplicationInfo) it.next())).packageName.equals(str) ? '\'' : (char) 29) != 29) {
                int i5 = f193 + 77;
                f194 = i5 % 128;
                int i6 = i5 % 2;
                return Boolean.TRUE.toString();
            }
            int i7 = f193 + 89;
            f194 = i7 % 128;
            if (i7 % 2 == 0) {
            }
            String string = Boolean.FALSE.toString();
            int i8 = f194 + 5;
            f193 = i8 % 128;
            int i9 = i8 % 2;
            return string;
        }
        String string2 = Boolean.FALSE.toString();
        int i82 = f194 + 5;
        f193 = i82 % 128;
        int i92 = i82 % 2;
        return string2;
    }

    public static class e extends HashMap<String, Object> {

        /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
        private static int f196 = 0;

        /* JADX INFO: renamed from: Ӏ, reason: contains not printable characters */
        private static int f198 = 1;

        /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
        private final Context f199;

        /* JADX INFO: renamed from: ι, reason: contains not printable characters */
        private final Map<String, Object> f200;

        /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
        private static char[] f197 = {5580, 3950, 8376, 23094, 32592, 37113, 35350, 44893, 49393, 64001, 8114, 12490, 28313, 29740, 23504, 8570, 1035, 'N', 6890, 13589, 20397, 27334, 34168, 40848, 47815, 49450, 56193, 62573, 'f', 6852, 13603, 20355, 27377, 34141, 40958, 47844, 54605, 61347, 2583, 9573, 16349, 23061, 30063, 36805, 43575, 50389, 57329, 64090, 5250, 12201, 19013, 25782, 32513, 39485, 46261, 53006, 60024, 1241, 8054, 14750, 21720, 28454, 35215, 42239, 48960, 55728, 62481, 3917, 10738, 17485, 53240, 54611, 64191, 32837, 42302, 19091, 20596, 64843, 59361, 51221, 45745, 38861, 30808, 25234, 18395, 10347, 4739, 63287, 55422, 49904, 42808, 34894, '6', 6795, 13691, 20417, 27300, 2705, 4203, 16277, 17789, 24578, 4597, 2903, 9392, 24080, 31586, 38094, 36461, 43895, 50398, 65072, 7044, 13558, 11854, 19334, 25852, 40534, 48036, 54598, 52834, 60361, 1297, 15930, 23499, 29985, 28295, 35835, 42292, 57044, 64488, 5451, 3761, 10240, 17683, 32435, 38913, 46463, 44738, 51258, 58777, 7897, 14388, 21904, 20155, 26628, 'b', 6852, 13608, 20362, 27381, 34140, 40942, 47798, 54556, 61437, 2582, 9518, 16269, 23047, 30004, 36763, 43617, 50381, 'a', 6859, 13614, 20381, 27387, 34128, 40890, 47789, 54593, 61347, 2566, 9586, 16338, 23061, 29992, 36810, 43571, 50305, 57331, 64080, 5258, 12199, 19052, 25746, 32556, 39497, 46215, 53045, 59989, 1262, 7957, 14771, 21729, 28427, 35245, 42186, 49008, 12580, 11152, 1143, 32463, 23457, 46107, 44783, 35751, 58381, 57071, 15175, 'x', 6813, 13692, 's', 6848, 13604, 20380, 27387, 34123, 11589, '&', 6877, '&', 6870, '&', 6869, 'f', 6805, 13627, 20447, 27365, 34056, 40878, 47794, 54616, 61439, 2624, 9588, 16340, 23061, 30055, 36806};

        /* JADX INFO: renamed from: ı, reason: contains not printable characters */
        private static long f195 = 4023479684532804261L;

        public e(Map<String, Object> map, Context context) {
            this.f200 = map;
            this.f199 = context;
            put(m149(), m150());
        }

        /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
        private static StringBuilder m151(@NonNull String... strArr) throws Exception {
            Object[] objArr;
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (true) {
                if (!(i < 3)) {
                    break;
                }
                int i2 = f196 + 95;
                f198 = i2 % 128;
                int i3 = i2 % 2;
                arrayList.add(Integer.valueOf(strArr[i].length()));
                i++;
            }
            Collections.sort(arrayList);
            int iIntValue = ((Integer) arrayList.get(0)).intValue();
            StringBuilder sb = new StringBuilder();
            int i4 = 0;
            while (true) {
                objArr = null;
                Integer numValueOf = null;
                if ((i4 < iIntValue ? '$' : (char) 2) != '$') {
                    break;
                }
                int i5 = 0;
                while (true) {
                    if (i5 >= 3) {
                        break;
                    }
                    int iCharAt = strArr[i5].charAt(i4);
                    if (numValueOf == null) {
                        int i6 = f196 + 27;
                        f198 = i6 % 128;
                        if (i6 % 2 != 0) {
                            continue;
                        } else {
                            int i7 = 46 / 0;
                        }
                    } else {
                        iCharAt ^= numValueOf.intValue();
                    }
                    numValueOf = Integer.valueOf(iCharAt);
                    i5++;
                }
                sb.append(Integer.toHexString(numValueOf.intValue()));
                i4++;
                int i8 = f196 + 21;
                f198 = i8 % 128;
                int i9 = i8 % 2;
            }
            int i10 = f198 + 55;
            f196 = i10 % 128;
            if (i10 % 2 == 0) {
                return sb;
            }
            int length = objArr.length;
            return sb;
        }

        @NonNull
        /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
        private String m149() {
            int i = f196 + 51;
            f198 = i % 128;
            int i2 = i % 2;
            try {
                String string = Integer.toString(Build.VERSION.SDK_INT);
                String string2 = this.f200.get(m152(0, (char) 5549, 12).intern()).toString();
                String string3 = this.f200.get(m152(12, (char) 28411, 5).intern()).toString();
                if ((string3 == null ? '3' : (char) 3) == '3') {
                    int i3 = f196 + 87;
                    f198 = i3 % 128;
                    string3 = ((i3 % 2 == 0 ? '.' : (char) 24) != 24 ? m152(77, (char) 0, 20) : m152(17, (char) 0, 8)).intern();
                }
                StringBuilder sb = new StringBuilder(string2);
                sb.reverse();
                StringBuilder sbM151 = m151(string, string3, sb.toString());
                int length = sbM151.length();
                if (length <= 4) {
                    while (length < 4) {
                        length++;
                        sbM151.append('1');
                        int i4 = f196 + 89;
                        f198 = i4 % 128;
                        int i5 = i4 % 2;
                    }
                } else {
                    int i6 = f198 + 25;
                    f196 = i6 % 128;
                    if (i6 % 2 != 0) {
                    }
                    sbM151.delete(4, length);
                }
                sbM151.insert(0, m152(25, (char) 49473, 3).intern());
                return sbM151.toString();
            } catch (Exception e) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(m152(28, (char) 0, 42).intern());
                sb2.append(e);
                AFLogger.afRDLog(sb2.toString());
                return m152(70, (char) 53139, 7).intern();
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x0128  */
        /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private java.lang.String m150() {
            /*
                Method dump skipped, instruction units count: 511
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.a.e.m150():java.lang.String");
        }

        /* JADX INFO: renamed from: com.appsflyer.internal.a$e$e, reason: collision with other inner class name */
        public static class C0004e {

            /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
            private final Object f201;

            /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
            public String f202;

            /* JADX INFO: renamed from: ι, reason: contains not printable characters */
            private long f203;

            C0004e() {
            }

            @NonNull
            /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
            static byte[] m155(@NonNull String str) throws Exception {
                return str.getBytes();
            }

            /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
            static byte[] m156(@NonNull byte[] bArr) throws Exception {
                for (int i = 0; i < bArr.length; i++) {
                    bArr[i] = (byte) (bArr[i] ^ ((i % 2) + 42));
                }
                return bArr;
            }

            @NonNull
            /* JADX INFO: renamed from: ı, reason: contains not printable characters */
            static String m154(@NonNull byte[] bArr) throws Exception {
                StringBuilder sb = new StringBuilder();
                for (byte b : bArr) {
                    String hexString = Integer.toHexString(b);
                    if (hexString.length() == 1) {
                        hexString = "0".concat(String.valueOf(hexString));
                    }
                    sb.append(hexString);
                }
                return sb.toString();
            }

            public C0004e(long j, String str) {
                this.f201 = new Object();
                this.f203 = 0L;
                this.f202 = "";
                this.f203 = j;
                this.f202 = str;
            }

            @NonNull
            /* JADX INFO: renamed from: ı, reason: contains not printable characters */
            public static C0004e m153(String str) {
                if (str != null) {
                    String[] strArrSplit = str.split(",");
                    if (strArrSplit.length >= 2) {
                        return new C0004e(Long.parseLong(strArrSplit[0]), strArrSplit[1]);
                    }
                    return new C0004e(0L, "");
                }
                return new C0004e(0L, "");
            }

            /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
            public final boolean m159(C0004e c0004e) {
                return m158(c0004e.f203, c0004e.f202);
            }

            /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
            private boolean m158(long j, String str) {
                synchronized (this.f201) {
                    if (str != null) {
                        if (!str.equals(this.f202) && m157(j)) {
                            this.f203 = j;
                            this.f202 = str;
                            return true;
                        }
                    }
                    return false;
                }
            }

            /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
            private boolean m157(long j) {
                return j - this.f203 > 2000;
            }

            public final String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(this.f203);
                sb.append(",");
                sb.append(this.f202);
                return sb.toString();
            }
        }

        /* JADX INFO: renamed from: ι, reason: contains not printable characters */
        private static String m152(int i, char c, int i2) {
            int i3 = f198 + 3;
            f196 = i3 % 128;
            int i4 = i3 % 2;
            char[] cArr = new char[i2];
            int i5 = 0;
            while (true) {
                if ((i5 < i2 ? (char) 2 : (char) 31) != 31) {
                    int i6 = f198 + 63;
                    f196 = i6 % 128;
                    if (!(i6 % 2 == 0)) {
                        cArr[i5] = (char) ((((long) f197[i << i5]) - (((long) i5) - f195)) ^ ((long) c));
                        i5 += 127;
                    } else {
                        cArr[i5] = (char) ((((long) f197[i + i5]) ^ (((long) i5) * f195)) ^ ((long) c));
                        i5++;
                    }
                } else {
                    return new String(cArr);
                }
            }
        }
    }

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    private static String m141(int i, char c, int i2) {
        char[] cArr;
        int i3;
        int i4 = f193 + 59;
        f194 = i4 % 128;
        if (!(i4 % 2 != 0)) {
            cArr = new char[i2];
            i3 = 1;
        } else {
            cArr = new char[i2];
            i3 = 0;
        }
        while (true) {
            if (i3 < i2) {
                int i5 = f194 + 93;
                f193 = i5 % 128;
                int i6 = i5 % 2;
                cArr[i3] = (char) ((((long) f191[i + i3]) ^ (((long) i3) * f192)) ^ ((long) c));
                i3++;
                int i7 = f194 + 17;
                f193 = i7 % 128;
                int i8 = i7 % 2;
            } else {
                return new String(cArr);
            }
        }
    }
}
