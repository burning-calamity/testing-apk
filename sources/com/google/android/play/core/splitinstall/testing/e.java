package com.google.android.play.core.splitinstall.testing;

import com.google.android.play.core.splitinstall.SplitInstallSessionState;

/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class e implements i {
    private final int a;
    private final /* synthetic */ int b = 0;

    e(int i) {
        this.a = i;
    }

    e(int i, byte[] bArr) {
        this.a = i;
    }

    @Override // com.google.android.play.core.splitinstall.testing.i
    public final SplitInstallSessionState a(SplitInstallSessionState splitInstallSessionState) {
        if (this.b == 0) {
            return FakeSplitInstallManager.g(this.a, splitInstallSessionState);
        }
        int i = this.a;
        int i2 = FakeSplitInstallManager.a;
        if (splitInstallSessionState == null) {
            return null;
        }
        return SplitInstallSessionState.create(splitInstallSessionState.sessionId(), 6, i, splitInstallSessionState.bytesDownloaded(), splitInstallSessionState.totalBytesToDownload(), splitInstallSessionState.moduleNames(), splitInstallSessionState.languages());
    }
}
