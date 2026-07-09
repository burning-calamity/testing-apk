package com.google.android.play.core.internal;

import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class bd implements bc {
    private final /* synthetic */ int a = 0;

    bd() {
    }

    bd(byte[] bArr) {
    }

    @Override // com.google.android.play.core.internal.bc
    public final Object[] a(Object obj, List list, List list2) {
        return (Object[]) (this.a != 0 ? bh.b(obj, "makePathElements", Object[].class, List.class, list, File.class, null, List.class, list2) : bh.a(obj, "makePathElements", Object[].class, List.class, list));
    }
}
