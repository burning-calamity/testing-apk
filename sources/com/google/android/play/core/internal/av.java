package com.google.android.play.core.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.AssetManager;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class av {
    private final com.google.android.play.core.splitcompat.c a;
    private final Context b;
    private final au c;

    @Nullable
    private PackageInfo d;
    private final ax e;

    public av(Context context, com.google.android.play.core.splitcompat.c cVar, ax axVar, byte[] bArr) {
        au auVar = new au(new com.google.android.play.core.splitcompat.a(cVar));
        this.a = cVar;
        this.e = axVar;
        this.b = context;
        this.c = auVar;
    }

    @Nullable
    private final PackageInfo d() {
        if (this.d == null) {
            try {
                this.d = this.b.getPackageManager().getPackageInfo(this.b.getPackageName(), 64);
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            }
        }
        return this.d;
    }

    @Nullable
    private static X509Certificate e(Signature signature) {
        try {
            return (X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(signature.toByteArray()));
        } catch (CertificateException e) {
            Log.e("SplitCompat", "Cannot decode certificate.", e);
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x0083, code lost:
    
        r3 = new java.lang.StringBuilder(java.lang.String.valueOf(r6).length() + 32);
        r3.append("Downloaded split ");
        r3.append(r6);
        r3.append(" is not signed.");
        r13 = r3.toString();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean a(java.io.File[] r13) {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.internal.av.a(java.io.File[]):boolean");
    }

    public final boolean b(File[] fileArr) throws XmlPullParserException, IOException {
        long longVersionCode = Build.VERSION.SDK_INT >= 28 ? d().getLongVersionCode() : r0.versionCode;
        AssetManager assetManager = (AssetManager) bh.c(AssetManager.class);
        int length = fileArr.length;
        do {
            length--;
            if (length < 0) {
                return true;
            }
            this.c.a(assetManager, fileArr[length]);
        } while (longVersionCode == this.c.b());
        return false;
    }

    public final boolean c(List<Intent> list) throws IOException {
        Iterator<Intent> it = list.iterator();
        while (it.hasNext()) {
            if (!this.a.c(it.next().getStringExtra("split_id")).exists()) {
                return false;
            }
        }
        return true;
    }
}
