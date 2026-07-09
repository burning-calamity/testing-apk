package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/* JADX INFO: loaded from: classes.dex */
final class cx {
    private static final com.google.android.play.core.internal.ag a = new com.google.android.play.core.internal.ag("PatchSliceTaskHandler");
    private final au b;
    private final com.google.android.play.core.internal.ca<t> c;

    cx(au auVar, com.google.android.play.core.internal.ca<t> caVar) {
        this.b = auVar;
        this.c = caVar;
    }

    public final void a(cw cwVar) {
        File fileF = this.b.f(cwVar.k, cwVar.a, cwVar.b);
        File file = new File(this.b.g(cwVar.k, cwVar.a, cwVar.b), cwVar.f);
        try {
            InputStream gZIPInputStream = cwVar.h;
            if (cwVar.e == 2) {
                gZIPInputStream = new GZIPInputStream(gZIPInputStream, 8192);
            }
            try {
                aw awVar = new aw(fileF, file);
                File fileH = this.b.h(cwVar.k, cwVar.c, cwVar.d, cwVar.f);
                if (!fileH.exists()) {
                    fileH.mkdirs();
                }
                cz czVar = new cz(this.b, cwVar.k, cwVar.c, cwVar.d, cwVar.f);
                com.google.android.play.core.internal.bh.l(awVar, gZIPInputStream, new bn(fileH, czVar), cwVar.g);
                czVar.d(0);
                gZIPInputStream.close();
                a.d("Patching and extraction finished for slice %s of pack %s.", cwVar.f, cwVar.k);
                this.c.a().e(cwVar.j, cwVar.k, cwVar.f, 0);
                try {
                    cwVar.h.close();
                } catch (IOException unused) {
                    a.e("Could not close file for slice %s of pack %s.", cwVar.f, cwVar.k);
                }
            } finally {
            }
        } catch (IOException e) {
            a.b("IOException during patching %s.", e.getMessage());
            throw new bk(String.format("Error patching slice %s of pack %s.", cwVar.f, cwVar.k), e, cwVar.j);
        }
    }
}
