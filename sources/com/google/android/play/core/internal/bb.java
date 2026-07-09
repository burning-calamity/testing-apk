package com.google.android.play.core.internal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class bb implements az {
    private final /* synthetic */ int a = 0;

    bb() {
    }

    bb(byte[] bArr) {
    }

    @Override // com.google.android.play.core.internal.az
    public final Object[] a(Object obj, ArrayList arrayList, File file, ArrayList arrayList2) {
        return (Object[]) (this.a != 0 ? bh.b(obj, "makeDexElements", Object[].class, ArrayList.class, arrayList, File.class, file, ArrayList.class, arrayList2) : bh.b(obj, "makePathElements", Object[].class, List.class, arrayList, File.class, file, List.class, arrayList2));
    }
}
