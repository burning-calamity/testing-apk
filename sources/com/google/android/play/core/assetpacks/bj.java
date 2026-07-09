package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.util.zip.GZIPInputStream;

/* JADX INFO: loaded from: classes.dex */
final class bj {
    private static final com.google.android.play.core.internal.ag a = new com.google.android.play.core.internal.ag("ExtractChunkTaskHandler");
    private final byte[] b = new byte[8192];
    private final au c;
    private final com.google.android.play.core.internal.ca<t> d;
    private final com.google.android.play.core.internal.ca<ar> e;
    private final bo f;

    bj(au auVar, com.google.android.play.core.internal.ca<t> caVar, com.google.android.play.core.internal.ca<ar> caVar2, bo boVar) {
        this.c = auVar;
        this.d = caVar;
        this.e = caVar2;
        this.f = boVar;
    }

    private final File b(bi biVar) {
        File fileH = this.c.h(biVar.k, biVar.a, biVar.b, biVar.c);
        if (!fileH.exists()) {
            fileH.mkdirs();
        }
        return fileH;
    }

    public final void a(bi biVar) {
        InputStream sequenceInputStream;
        de deVarA;
        File fileJ;
        long length;
        int iMin;
        int iMax;
        long j;
        cz czVar = new cz(this.c, biVar.k, biVar.a, biVar.b, biVar.c);
        File fileO = this.c.o(biVar.k, biVar.a, biVar.b, biVar.c);
        if (!fileO.exists()) {
            fileO.mkdirs();
        }
        try {
            InputStream inputStream = biVar.i;
            InputStream gZIPInputStream = biVar.d != 1 ? inputStream : new GZIPInputStream(inputStream, 8192);
            try {
                if (biVar.e > 0) {
                    cy cyVarE = czVar.e();
                    int iE = cyVarE.e();
                    int i = biVar.e;
                    if (iE != i - 1) {
                        throw new bk(String.format("Trying to resume with chunk number %s when previously processed chunk was number %s.", Integer.valueOf(i), Integer.valueOf(cyVarE.e())), biVar.j);
                    }
                    int iA = cyVarE.a();
                    if (iA == 1) {
                        a.a("Resuming zip entry from last chunk during file %s.", cyVarE.b());
                        File file = new File(cyVarE.b());
                        if (!file.exists()) {
                            throw new bk("Partial file specified in checkpoint does not exist. Corrupt directory.", biVar.j);
                        }
                        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                        randomAccessFile.seek(cyVarE.c());
                        long jD = cyVarE.d();
                        while (true) {
                            iMin = (int) Math.min(jD, 8192L);
                            iMax = Math.max(gZIPInputStream.read(this.b, 0, iMin), 0);
                            if (iMax > 0) {
                                randomAccessFile.write(this.b, 0, iMax);
                            }
                            j = jD - ((long) iMax);
                            if (j <= 0 || iMax <= 0) {
                                break;
                            } else {
                                jD = j;
                            }
                        }
                        long length2 = randomAccessFile.length();
                        randomAccessFile.close();
                        if (iMax != iMin) {
                            a.a("Chunk has ended while resuming the previous chunks file content.", new Object[0]);
                            czVar.a(file.getCanonicalPath(), length2, j, biVar.e);
                        }
                        sequenceInputStream = gZIPInputStream;
                    } else if (iA == 2) {
                        a.a("Resuming zip entry from last chunk during local file header.", new Object[0]);
                        File fileN = this.c.n(biVar.k, biVar.a, biVar.b, biVar.c);
                        if (!fileN.exists()) {
                            throw new bk("Checkpoint extension file not found.", biVar.j);
                        }
                        sequenceInputStream = new SequenceInputStream(new FileInputStream(fileN), gZIPInputStream);
                    } else {
                        if (iA != 3) {
                            throw new bk(String.format("Slice checkpoint file corrupt. Unexpected FileExtractionStatus %s.", Integer.valueOf(cyVarE.a())), biVar.j);
                        }
                        a.a("Resuming central directory from last chunk.", new Object[0]);
                        czVar.f(gZIPInputStream, cyVarE.c());
                        if (!biVar.a()) {
                            throw new bk("Chunk has ended twice during central directory. This should not be possible with chunk sizes of 50MB.", biVar.j);
                        }
                    }
                    sequenceInputStream = null;
                } else {
                    sequenceInputStream = gZIPInputStream;
                }
                if (sequenceInputStream != null) {
                    bd bdVar = new bd(sequenceInputStream);
                    File fileB = b(biVar);
                    do {
                        deVarA = bdVar.a();
                        if (!deVarA.g() && !bdVar.c()) {
                            if (!deVarA.c() || deVarA.b()) {
                                czVar.h(deVarA.i(), bdVar);
                            } else {
                                czVar.g(deVarA.i());
                                File file2 = new File(fileB, deVarA.d());
                                file2.getParentFile().mkdirs();
                                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                                byte[] bArr = this.b;
                                while (true) {
                                    int i2 = bdVar.read(bArr);
                                    if (i2 <= 0) {
                                        break;
                                    }
                                    fileOutputStream.write(this.b, 0, i2);
                                    bArr = this.b;
                                }
                                fileOutputStream.close();
                            }
                        }
                        if (bdVar.b()) {
                            break;
                        }
                    } while (!bdVar.c());
                    if (bdVar.c()) {
                        a.a("Writing central directory metadata.", new Object[0]);
                        czVar.h(deVarA.i(), sequenceInputStream);
                    }
                    if (!biVar.a()) {
                        if (deVarA.g()) {
                            a.a("Writing slice checkpoint for partial local file header.", new Object[0]);
                            czVar.b(deVarA.i(), biVar.e);
                        } else if (bdVar.c()) {
                            a.a("Writing slice checkpoint for central directory.", new Object[0]);
                            czVar.c(biVar.e);
                        } else {
                            if (deVarA.f() == 0) {
                                a.a("Writing slice checkpoint for partial file.", new Object[0]);
                                fileJ = new File(b(biVar), deVarA.d());
                                length = deVarA.e() - bdVar.d();
                                if (fileJ.length() != length) {
                                    throw new bk("Partial file is of unexpected size.");
                                }
                            } else {
                                a.a("Writing slice checkpoint for partial unextractable file.", new Object[0]);
                                fileJ = czVar.j();
                                length = fileJ.length();
                            }
                            czVar.a(fileJ.getCanonicalPath(), length, bdVar.d(), biVar.e);
                        }
                    }
                }
                gZIPInputStream.close();
                if (biVar.a()) {
                    try {
                        czVar.d(biVar.e);
                    } catch (IOException e) {
                        a.b("Writing extraction finished checkpoint failed with %s.", e.getMessage());
                        throw new bk("Writing extraction finished checkpoint failed.", e, biVar.j);
                    }
                }
                a.d("Extraction finished for chunk %s of slice %s of pack %s of session %s.", Integer.valueOf(biVar.e), biVar.c, biVar.k, Integer.valueOf(biVar.j));
                this.d.a().e(biVar.j, biVar.k, biVar.c, biVar.e);
                try {
                    biVar.i.close();
                } catch (IOException unused) {
                    a.e("Could not close file for chunk %s of slice %s of pack %s.", Integer.valueOf(biVar.e), biVar.c, biVar.k);
                }
                if (biVar.h == 3) {
                    ar arVarA = this.e.a();
                    String str = biVar.k;
                    long j2 = biVar.g;
                    arVarA.b(AssetPackState.c(str, 3, 0, j2, j2, this.f.c(str, biVar), 1, ""));
                }
            } finally {
            }
        } catch (IOException e2) {
            a.b("IOException during extraction %s.", e2.getMessage());
            throw new bk(String.format("Error extracting chunk %s of slice %s of pack %s of session %s.", Integer.valueOf(biVar.e), biVar.c, biVar.k, Integer.valueOf(biVar.j)), e2, biVar.j);
        }
    }
}
