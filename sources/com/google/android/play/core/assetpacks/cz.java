package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Properties;

/* JADX INFO: loaded from: classes.dex */
final class cz {
    private static final com.google.android.play.core.internal.ag a = new com.google.android.play.core.internal.ag("SliceMetadataManager");
    private final au c;
    private final String d;
    private final int e;
    private final long f;
    private final String g;
    private final byte[] b = new byte[8192];
    private int h = -1;

    cz(au auVar, String str, int i, long j, String str2) {
        this.c = auVar;
        this.d = str;
        this.e = i;
        this.f = j;
        this.g = str2;
    }

    private final File n() {
        File fileO = this.c.o(this.d, this.e, this.f, this.g);
        if (!fileO.exists()) {
            fileO.mkdirs();
        }
        return fileO;
    }

    private final File o() throws IOException {
        File fileM = this.c.m(this.d, this.e, this.f, this.g);
        fileM.getParentFile().mkdirs();
        fileM.createNewFile();
        return fileM;
    }

    final void a(String str, long j, long j2, int i) throws IOException {
        Properties properties = new Properties();
        properties.put("fileStatus", "1");
        properties.put("fileName", str);
        properties.put("fileOffset", String.valueOf(j));
        properties.put("remainingBytes", String.valueOf(j2));
        properties.put("previousChunk", String.valueOf(i));
        properties.put("metadataFileCounter", String.valueOf(this.h));
        FileOutputStream fileOutputStream = new FileOutputStream(o());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                com.google.android.play.core.internal.bz.a(th, th2);
            }
            throw th;
        }
    }

    final void b(byte[] bArr, int i) throws IOException {
        Properties properties = new Properties();
        properties.put("fileStatus", "2");
        properties.put("previousChunk", String.valueOf(i));
        properties.put("metadataFileCounter", String.valueOf(this.h));
        FileOutputStream fileOutputStream = new FileOutputStream(o());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
            File fileN = this.c.n(this.d, this.e, this.f, this.g);
            if (fileN.exists()) {
                fileN.delete();
            }
            fileOutputStream = new FileOutputStream(fileN);
            try {
                fileOutputStream.write(bArr);
                fileOutputStream.close();
            } finally {
                try {
                    fileOutputStream.close();
                } catch (Throwable th) {
                    com.google.android.play.core.internal.bz.a(th, th);
                }
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    final void c(int i) throws IOException {
        Properties properties = new Properties();
        properties.put("fileStatus", "3");
        properties.put("fileOffset", String.valueOf(j().length()));
        properties.put("previousChunk", String.valueOf(i));
        properties.put("metadataFileCounter", String.valueOf(this.h));
        FileOutputStream fileOutputStream = new FileOutputStream(o());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                com.google.android.play.core.internal.bz.a(th, th2);
            }
            throw th;
        }
    }

    final void d(int i) throws IOException {
        Properties properties = new Properties();
        properties.put("fileStatus", "4");
        properties.put("previousChunk", String.valueOf(i));
        properties.put("metadataFileCounter", String.valueOf(this.h));
        FileOutputStream fileOutputStream = new FileOutputStream(o());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                com.google.android.play.core.internal.bz.a(th, th2);
            }
            throw th;
        }
    }

    final cy e() throws IOException {
        File fileM = this.c.m(this.d, this.e, this.f, this.g);
        if (!fileM.exists()) {
            throw new bk("Slice checkpoint file does not exist.");
        }
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(fileM);
        try {
            properties.load(fileInputStream);
            fileInputStream.close();
            if (properties.getProperty("fileStatus") == null || properties.getProperty("previousChunk") == null) {
                throw new bk("Slice checkpoint file corrupt.");
            }
            try {
                int i = Integer.parseInt(properties.getProperty("fileStatus"));
                String property = properties.getProperty("fileName");
                long j = Long.parseLong(properties.getProperty("fileOffset", "-1"));
                long j2 = Long.parseLong(properties.getProperty("remainingBytes", "-1"));
                int i2 = Integer.parseInt(properties.getProperty("previousChunk"));
                this.h = Integer.parseInt(properties.getProperty("metadataFileCounter", "0"));
                return new cy(i, property, j, j2, i2);
            } catch (NumberFormatException e) {
                throw new bk("Slice checkpoint file corrupt.", e);
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                com.google.android.play.core.internal.bz.a(th, th2);
            }
            throw th;
        }
    }

    final void f(InputStream inputStream, long j) throws IOException {
        int i;
        RandomAccessFile randomAccessFile = new RandomAccessFile(j(), "rw");
        try {
            randomAccessFile.seek(j);
            do {
                i = inputStream.read(this.b);
                if (i > 0) {
                    randomAccessFile.write(this.b, 0, i);
                }
            } while (i == 8192);
            randomAccessFile.close();
        } catch (Throwable th) {
            try {
                randomAccessFile.close();
            } catch (Throwable th2) {
                com.google.android.play.core.internal.bz.a(th, th2);
            }
            throw th;
        }
    }

    final void g(byte[] bArr) throws IOException {
        this.h++;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(n(), String.format("%s-LFH.dat", Integer.valueOf(this.h))));
            try {
                fileOutputStream.write(bArr);
                fileOutputStream.close();
            } finally {
            }
        } catch (IOException e) {
            throw new bk("Could not write metadata file.", e);
        }
    }

    final void h(byte[] bArr, InputStream inputStream) throws IOException {
        this.h++;
        FileOutputStream fileOutputStream = new FileOutputStream(j());
        try {
            fileOutputStream.write(bArr);
            byte[] bArr2 = this.b;
            while (true) {
                int i = inputStream.read(bArr2);
                if (i <= 0) {
                    fileOutputStream.close();
                    return;
                } else {
                    fileOutputStream.write(this.b, 0, i);
                    bArr2 = this.b;
                }
            }
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                com.google.android.play.core.internal.bz.a(th, th2);
            }
            throw th;
        }
    }

    final void i(long j, byte[] bArr, int i, int i2) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(j(), "rw");
        try {
            randomAccessFile.seek(j);
            randomAccessFile.write(bArr, i, i2);
            randomAccessFile.close();
        } catch (Throwable th) {
            try {
                randomAccessFile.close();
            } catch (Throwable th2) {
                com.google.android.play.core.internal.bz.a(th, th2);
            }
            throw th;
        }
    }

    final File j() {
        return new File(n(), String.format("%s-NAM.dat", Integer.valueOf(this.h)));
    }

    final int k() throws IOException {
        File fileM = this.c.m(this.d, this.e, this.f, this.g);
        if (!fileM.exists()) {
            return 0;
        }
        FileInputStream fileInputStream = new FileInputStream(fileM);
        try {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
            if (Integer.parseInt(properties.getProperty("fileStatus", "-1")) == 4) {
                return -1;
            }
            if (properties.getProperty("previousChunk") != null) {
                return Integer.parseInt(properties.getProperty("previousChunk")) + 1;
            }
            throw new bk("Slice checkpoint file corrupt.");
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                com.google.android.play.core.internal.bz.a(th, th2);
            }
            throw th;
        }
    }

    final boolean l() {
        File fileM = this.c.m(this.d, this.e, this.f, this.g);
        if (!fileM.exists()) {
            return false;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(fileM);
            try {
                Properties properties = new Properties();
                properties.load(fileInputStream);
                fileInputStream.close();
                if (properties.getProperty("fileStatus") != null) {
                    return Integer.parseInt(properties.getProperty("fileStatus")) == 4;
                }
                a.b("Slice checkpoint file corrupt while checking if extraction finished.", new Object[0]);
                return false;
            } finally {
            }
        } catch (IOException e) {
            a.b("Could not read checkpoint while checking if extraction finished. %s", e);
            return false;
        }
    }

    final void m(byte[] bArr, int i) throws IOException {
        this.h++;
        FileOutputStream fileOutputStream = new FileOutputStream(j());
        try {
            fileOutputStream.write(bArr, 0, i);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                com.google.android.play.core.internal.bz.a(th, th2);
            }
            throw th;
        }
    }
}
