package com.google.android.play.core.splitinstall;

/* JADX INFO: loaded from: classes.dex */
final class r implements Runnable {
    final /* synthetic */ SplitInstallSessionState a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ s d;

    r(s sVar, SplitInstallSessionState splitInstallSessionState, int i, int i2) {
        this.d = sVar;
        this.a = splitInstallSessionState;
        this.b = i;
        this.c = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        s sVar = this.d;
        SplitInstallSessionState splitInstallSessionState = this.a;
        sVar.k(new a(splitInstallSessionState.sessionId(), this.b, this.c, splitInstallSessionState.bytesDownloaded(), splitInstallSessionState.totalBytesToDownload(), splitInstallSessionState.a(), splitInstallSessionState.b(), splitInstallSessionState.resolutionIntent(), splitInstallSessionState.c()));
    }
}
