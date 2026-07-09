package com.google.android.play.core.internal;

/* JADX INFO: loaded from: classes.dex */
public final class cb<T> implements ce<T> {
    private ce<T> a;

    public static <T> void b(ce<T> ceVar, ce<T> ceVar2) {
        bh.j(ceVar2);
        cb cbVar = (cb) ceVar;
        if (cbVar.a != null) {
            throw new IllegalStateException();
        }
        cbVar.a = ceVar2;
    }

    @Override // com.google.android.play.core.internal.ce
    public final T a() {
        ce<T> ceVar = this.a;
        if (ceVar != null) {
            return ceVar.a();
        }
        throw new IllegalStateException();
    }
}
