package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes.dex */
final class dd {
    private static final com.google.android.play.core.internal.ag a = new com.google.android.play.core.internal.ag("VerifySliceTaskHandler");
    private final au b;

    dd(au auVar) {
        this.b = auVar;
    }

    private final void b(dc dcVar, File file) {
        try {
            File fileO = this.b.o(dcVar.k, dcVar.a, dcVar.b, dcVar.c);
            if (!fileO.exists()) {
                throw new bk(String.format("Cannot find metadata files for slice %s.", dcVar.c), dcVar.j);
            }
            try {
                if (!ck.a(db.a(file, fileO)).equals(dcVar.d)) {
                    throw new bk(String.format("Verification failed for slice %s.", dcVar.c), dcVar.j);
                }
                a.d("Verification of slice %s of pack %s successful.", dcVar.c, dcVar.k);
            } catch (IOException e) {
                throw new bk(String.format("Could not digest file during verification for slice %s.", dcVar.c), e, dcVar.j);
            } catch (NoSuchAlgorithmException e2) {
                throw new bk("SHA256 algorithm not supported.", e2, dcVar.j);
            }
        } catch (IOException e3) {
            throw new bk(String.format("Could not reconstruct slice archive during verification for slice %s.", dcVar.c), e3, dcVar.j);
        }
    }

    public final void a(dc dcVar) {
        File fileH = this.b.h(dcVar.k, dcVar.a, dcVar.b, dcVar.c);
        if (!fileH.exists()) {
            throw new bk(String.format("Cannot find unverified files for slice %s.", dcVar.c), dcVar.j);
        }
        b(dcVar, fileH);
        File fileI = this.b.i(dcVar.k, dcVar.a, dcVar.b, dcVar.c);
        if (!fileI.exists()) {
            fileI.mkdirs();
        }
        if (!fileH.renameTo(fileI)) {
            throw new bk(String.format("Failed to move slice %s after verification.", dcVar.c), dcVar.j);
        }
    }
}
