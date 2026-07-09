package com.google.android.play.core.appupdate;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class u {
    private static w a;

    static synchronized w a(Context context) {
        if (a == null) {
            v vVar = new v(null);
            vVar.b(new f(com.google.android.play.core.splitcompat.p.c(context)));
            a = vVar.a();
        }
        return a;
    }
}
