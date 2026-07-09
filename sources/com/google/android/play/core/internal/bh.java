package com.google.android.play.core.internal;

import android.content.ComponentName;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class bh {
    public static <R, P0> R a(Object obj, String str, Class<R> cls, Class<P0> cls2, P0 p0) {
        try {
            return cls.cast(m(obj, str, cls2).invoke(obj, p0));
        } catch (Exception e) {
            throw new bi(String.format("Failed to invoke method %s on an object of type %s", str, obj.getClass()), e);
        }
    }

    public static <R, P0, P1, P2> R b(Object obj, String str, Class<R> cls, Class<P0> cls2, P0 p0, Class<P1> cls3, P1 p1, Class<P2> cls4, P2 p2) {
        try {
            return cls.cast(m(obj, str, cls2, cls3, cls4).invoke(obj, p0, p1, p2));
        } catch (Exception e) {
            throw new bi(String.format("Failed to invoke method %s on an object of type %s", str, obj.getClass()), e);
        }
    }

    public static <R> R c(Class<R> cls) {
        try {
            Constructor<R> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return declaredConstructor.newInstance(new Object[0]);
        } catch (Exception e) {
            throw new bi(String.format("Failed to invoke default constructor on class %s", cls.getName()), e);
        }
    }

    public static <T> bg<T> d(Object obj, String str, Class<T> cls) {
        return new bg<>(obj, o(obj, str), cls);
    }

    public static <T> bg e(Object obj, String str, Class<T> cls) {
        return new bg(obj, o(obj, str), cls, null);
    }

    public static <R, P0> R f(Class cls, Class<R> cls2, Class<P0> cls3, P0 p0) {
        try {
            return cls2.cast(n(cls, "isDexOptNeeded", cls3).invoke(null, p0));
        } catch (Exception e) {
            throw new bi(String.format("Failed to invoke static method %s on type %s", "isDexOptNeeded", cls), e);
        }
    }

    public static <R, P0, P1> R g(Class cls, Class<R> cls2, Class<P0> cls3, P0 p0, Class<P1> cls4, P1 p1) {
        try {
            return cls2.cast(n(cls, "optimizedPathFor", cls3, cls4).invoke(null, p0, p1));
        } catch (Exception e) {
            throw new bi(String.format("Failed to invoke static method %s on type %s", "optimizedPathFor", cls), e);
        }
    }

    public static void h(PackageManager packageManager, ComponentName componentName, int i) {
        ComponentInfo componentInfo;
        int componentEnabledSetting = packageManager.getComponentEnabledSetting(componentName);
        if (componentEnabledSetting != 1) {
            if (componentEnabledSetting != 2) {
                String packageName = componentName.getPackageName();
                String className = componentName.getClassName();
                try {
                    PackageInfo packageInfo = packageManager.getPackageInfo(packageName, i | 512);
                    ComponentInfo[][] componentInfoArr = {packageInfo.activities, packageInfo.services, packageInfo.providers};
                    int i2 = 0;
                    loop0: while (true) {
                        if (i2 >= 3) {
                            componentInfo = null;
                            break;
                        }
                        ComponentInfo[] componentInfoArr2 = componentInfoArr[i2];
                        if (componentInfoArr2 != null) {
                            int length = componentInfoArr2.length;
                            for (int i3 = 0; i3 < length; i3++) {
                                componentInfo = componentInfoArr2[i3];
                                if (componentInfo.name.equals(className)) {
                                    break loop0;
                                }
                            }
                        }
                        i2++;
                    }
                    if (componentInfo != null) {
                        if (componentInfo.isEnabled()) {
                            return;
                        }
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
            packageManager.setComponentEnabledSetting(componentName, 1, 1);
        }
    }

    public static <T> void i(T t, Class<T> cls) {
        if (t == null) {
            throw new IllegalStateException(String.valueOf(cls.getCanonicalName()).concat(" must be set"));
        }
    }

    public static <T> void j(T t) {
        if (t == null) {
            throw null;
        }
    }

    public static <T> void k(T t) {
        if (t == null) {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void l(br brVar, InputStream inputStream, OutputStream outputStream, long j) throws IOException {
        long unsignedShort;
        byte[] bArr = new byte[16384];
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(inputStream, 4096));
        int i = dataInputStream.readInt();
        if (i != -771763713) {
            String strValueOf = String.valueOf(String.format("%x", Integer.valueOf(i)));
            throw new bq(strValueOf.length() != 0 ? "Unexpected magic=".concat(strValueOf) : new String("Unexpected magic="));
        }
        int i2 = dataInputStream.read();
        if (i2 != 4) {
            StringBuilder sb = new StringBuilder(30);
            sb.append("Unexpected version=");
            sb.append(i2);
            throw new bq(sb.toString());
        }
        long j2 = 0;
        while (true) {
            long j3 = j - j2;
            try {
                int unsignedShort2 = dataInputStream.read();
                if (unsignedShort2 == -1) {
                    throw new IOException("Patch file overrun");
                }
                if (unsignedShort2 != 0) {
                    switch (unsignedShort2) {
                        case 247:
                            unsignedShort2 = dataInputStream.readUnsignedShort();
                            p(bArr, dataInputStream, outputStream, unsignedShort2, j3);
                            j2 += (long) unsignedShort2;
                            break;
                        case 248:
                            unsignedShort2 = dataInputStream.readInt();
                            p(bArr, dataInputStream, outputStream, unsignedShort2, j3);
                            j2 += (long) unsignedShort2;
                            break;
                        case 249:
                            unsignedShort = dataInputStream.readUnsignedShort();
                            unsignedShort2 = dataInputStream.read();
                            if (unsignedShort2 == -1) {
                                throw new IOException("Unexpected end of patch");
                            }
                            q(bArr, brVar, outputStream, unsignedShort, unsignedShort2, j3);
                            j2 += (long) unsignedShort2;
                            break;
                            break;
                        case 250:
                            unsignedShort = dataInputStream.readUnsignedShort();
                            unsignedShort2 = dataInputStream.readUnsignedShort();
                            q(bArr, brVar, outputStream, unsignedShort, unsignedShort2, j3);
                            j2 += (long) unsignedShort2;
                            break;
                        case 251:
                            unsignedShort = dataInputStream.readUnsignedShort();
                            unsignedShort2 = dataInputStream.readInt();
                            q(bArr, brVar, outputStream, unsignedShort, unsignedShort2, j3);
                            j2 += (long) unsignedShort2;
                            break;
                        case 252:
                            unsignedShort = dataInputStream.readInt();
                            unsignedShort2 = dataInputStream.read();
                            if (unsignedShort2 == -1) {
                                throw new IOException("Unexpected end of patch");
                            }
                            q(bArr, brVar, outputStream, unsignedShort, unsignedShort2, j3);
                            j2 += (long) unsignedShort2;
                            break;
                            break;
                        case 253:
                            unsignedShort = dataInputStream.readInt();
                            unsignedShort2 = dataInputStream.readUnsignedShort();
                            q(bArr, brVar, outputStream, unsignedShort, unsignedShort2, j3);
                            j2 += (long) unsignedShort2;
                            break;
                        case 254:
                            unsignedShort = dataInputStream.readInt();
                            unsignedShort2 = dataInputStream.readInt();
                            q(bArr, brVar, outputStream, unsignedShort, unsignedShort2, j3);
                            j2 += (long) unsignedShort2;
                            break;
                        case 255:
                            unsignedShort = dataInputStream.readLong();
                            unsignedShort2 = dataInputStream.readInt();
                            q(bArr, brVar, outputStream, unsignedShort, unsignedShort2, j3);
                            j2 += (long) unsignedShort2;
                            break;
                        default:
                            p(bArr, dataInputStream, outputStream, unsignedShort2, j3);
                            j2 += (long) unsignedShort2;
                            break;
                    }
                } else {
                    return;
                }
            } finally {
                outputStream.flush();
            }
        }
    }

    private static Method m(Object obj, String str, Class<?>... clsArr) {
        return n(obj.getClass(), str, clsArr);
    }

    private static Method n(Class<?> cls, String str, Class<?>... clsArr) {
        for (Class<?> superclass = cls; superclass != null; superclass = superclass.getSuperclass()) {
            try {
                Method declaredMethod = superclass.getDeclaredMethod(str, clsArr);
                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                return declaredMethod;
            } catch (NoSuchMethodException unused) {
            }
        }
        throw new bi(String.format("Could not find a method named %s with parameters %s in type %s", str, Arrays.asList(clsArr), cls));
    }

    private static Field o(Object obj, String str) {
        for (Class<?> superclass = obj.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
            try {
                Field declaredField = superclass.getDeclaredField(str);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                return declaredField;
            } catch (NoSuchFieldException unused) {
            }
        }
        throw new bi(String.format("Failed to find a field named %s on an object of instance %s", str, obj.getClass().getName()));
    }

    private static void p(byte[] bArr, DataInputStream dataInputStream, OutputStream outputStream, int i, long j) throws IOException {
        if (i < 0) {
            throw new IOException("copyLength negative");
        }
        if (i > j) {
            throw new IOException("Output length overrun");
        }
        while (i > 0) {
            try {
                int iMin = Math.min(i, 16384);
                dataInputStream.readFully(bArr, 0, iMin);
                outputStream.write(bArr, 0, iMin);
                i -= iMin;
            } catch (EOFException unused) {
                throw new IOException("patch underrun");
            }
        }
    }

    private static void q(byte[] bArr, br brVar, OutputStream outputStream, long j, int i, long j2) throws IOException {
        InputStream inputStreamC;
        int i2 = i;
        if (i2 < 0) {
            throw new IOException("copyLength negative");
        }
        if (j < 0) {
            throw new IOException("inputOffset negative");
        }
        long j3 = i2;
        if (j3 > j2) {
            throw new IOException("Output length overrun");
        }
        try {
            inputStreamC = new bs(brVar, j, j3).c();
        } catch (EOFException e) {
            throw new IOException("patch underrun", e);
        }
        while (i2 > 0) {
            try {
                int iMin = Math.min(i2, 16384);
                int i3 = 0;
                while (i3 < iMin) {
                    int i4 = inputStreamC.read(bArr, i3, iMin - i3);
                    if (i4 == -1) {
                        throw new IOException("truncated input stream");
                    }
                    i3 += i4;
                    throw new IOException("patch underrun", e);
                }
                outputStream.write(bArr, 0, iMin);
                i2 -= iMin;
            } finally {
            }
        }
        inputStreamC.close();
    }
}
