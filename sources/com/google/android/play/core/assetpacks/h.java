package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/* JADX INFO: loaded from: classes.dex */
final class h extends ResultReceiver {
    final /* synthetic */ com.google.android.play.core.tasks.i a;
    final /* synthetic */ i b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    h(i iVar, Handler handler, com.google.android.play.core.tasks.i iVar2) {
        super(handler);
        this.b = iVar;
        this.a = iVar2;
    }

    @Override // android.os.ResultReceiver
    public final void onReceiveResult(int i, Bundle bundle) {
        if (i == 1) {
            this.a.e(-1);
            this.b.h.a(null);
        } else if (i != 2) {
            this.a.d(new AssetPackException(-100));
        } else {
            this.a.e(0);
        }
    }
}
