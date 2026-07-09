package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class t implements Runnable {
    final /* synthetic */ SplitInstallRequest a;
    final /* synthetic */ v b;

    t(v vVar, SplitInstallRequest splitInstallRequest) {
        this.b = vVar;
        this.a = splitInstallRequest;
    }

    @Override // java.lang.Runnable
    public final void run() {
        s sVar = this.b.b;
        List<String> moduleNames = this.a.getModuleNames();
        List listE = v.e(this.a.getLanguages());
        Bundle bundle = new Bundle();
        bundle.putInt("session_id", 0);
        bundle.putInt("status", 5);
        bundle.putInt("error_code", 0);
        if (!moduleNames.isEmpty()) {
            bundle.putStringArrayList("module_names", new ArrayList<>(moduleNames));
        }
        if (!listE.isEmpty()) {
            bundle.putStringArrayList("languages", new ArrayList<>(listE));
        }
        bundle.putLong("total_bytes_to_download", 0L);
        bundle.putLong("bytes_downloaded", 0L);
        sVar.k(SplitInstallSessionState.d(bundle));
    }
}
