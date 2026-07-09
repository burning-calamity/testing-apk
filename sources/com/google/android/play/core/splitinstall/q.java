package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.Intent;
import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode;

/* JADX INFO: loaded from: classes.dex */
final class q implements d {
    final /* synthetic */ SplitInstallSessionState a;
    final /* synthetic */ Intent b;
    final /* synthetic */ Context c;
    final /* synthetic */ s d;

    q(s sVar, SplitInstallSessionState splitInstallSessionState, Intent intent, Context context) {
        this.d = sVar;
        this.a = splitInstallSessionState;
        this.b = intent;
        this.c = context;
    }

    @Override // com.google.android.play.core.splitinstall.d
    public final void a() {
        s sVar = this.d;
        sVar.d.post(new r(sVar, this.a, 5, 0));
    }

    @Override // com.google.android.play.core.splitinstall.d
    public final void b() {
        if (this.b.getBooleanExtra("triggered_from_app_after_verification", false)) {
            this.d.a.b("Splits copied and verified more than once.", new Object[0]);
        } else {
            this.b.putExtra("triggered_from_app_after_verification", true);
            this.c.sendBroadcast(this.b);
        }
    }

    @Override // com.google.android.play.core.splitinstall.d
    public final void c(@SplitInstallErrorCode int i) {
        s sVar = this.d;
        sVar.d.post(new r(sVar, this.a, 6, i));
    }
}
