package com.google.android.play.core.internal;

/* JADX INFO: loaded from: classes.dex */
public final class cc<T> implements ce, ca {
    private static final Object a = new Object();
    private volatile ce<T> b;
    private volatile Object c = a;

    private cc(ce<T> ceVar) {
        this.b = ceVar;
    }

    public static <P extends ce<T>, T> ce<T> b(P p) {
        bh.j(p);
        return p instanceof cc ? p : new cc(p);
    }

    public static <P extends ce<T>, T> ca<T> c(P p) {
        if (p instanceof ca) {
            return (ca) p;
        }
        bh.j(p);
        return new cc(p);
    }

    @Override // com.google.android.play.core.internal.ce
    public final T a() {
        T tA = (T) this.c;
        if (tA == a) {
            synchronized (this) {
                tA = (T) this.c;
                if (tA == a) {
                    tA = this.b.a();
                    Object obj = this.c;
                    if (obj != a && !(obj instanceof cd) && obj != tA) {
                        String strValueOf = String.valueOf(obj);
                        String strValueOf2 = String.valueOf(tA);
                        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 118 + String.valueOf(strValueOf2).length());
                        sb.append("Scoped provider was invoked recursively returning different results: ");
                        sb.append(strValueOf);
                        sb.append(" & ");
                        sb.append(strValueOf2);
                        sb.append(". This is likely due to a circular dependency.");
                        throw new IllegalStateException(sb.toString());
                    }
                    this.c = tA;
                    this.b = null;
                }
            }
        }
        return tA;
    }
}
