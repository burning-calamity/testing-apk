package com.google.android.play.core.splitinstall;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class InternalFrameworkListenerExtensions {
    private InternalFrameworkListenerExtensions() {
    }

    public static void registerFrameworkListener(Context context, SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        k.a(context).a().a(splitInstallStateUpdatedListener);
    }

    public static void unregisterFrameworkListener(Context context, SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        k.a(context).a().b(splitInstallStateUpdatedListener);
    }
}
