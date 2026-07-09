package com.google.android.play.core.appupdate;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/* JADX INFO: loaded from: classes.dex */
final class b extends ResultReceiver {
    final /* synthetic */ com.google.android.play.core.tasks.i a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    b(Handler handler, com.google.android.play.core.tasks.i iVar) {
        super(handler);
        this.a = iVar;
    }

    @Override // android.os.ResultReceiver
    public final void onReceiveResult(int i, Bundle bundle) {
        com.google.android.play.core.tasks.i iVar;
        int i2 = 1;
        if (i == 1) {
            iVar = this.a;
            i2 = -1;
        } else if (i != 2) {
            iVar = this.a;
        } else {
            iVar = this.a;
            i2 = 0;
        }
        iVar.e(Integer.valueOf(i2));
    }
}
