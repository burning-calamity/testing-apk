package com.google.android.play.core.internal;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

/* JADX INFO: loaded from: classes.dex */
final class g extends h {
    private final byte[] a;

    public g(X509Certificate x509Certificate, byte[] bArr) {
        super(x509Certificate);
        this.a = bArr;
    }

    @Override // com.google.android.play.core.internal.h, java.security.cert.Certificate
    public final byte[] getEncoded() throws CertificateEncodingException {
        return this.a;
    }
}
