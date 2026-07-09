package com.google.android.play.core.install;

/* JADX INFO: loaded from: classes.dex */
final class NativeInstallStateUpdateListener implements InstallStateUpdatedListener {
    NativeInstallStateUpdateListener() {
    }

    @Override // com.google.android.play.core.listener.StateUpdatedListener
    public native void onStateUpdate(InstallState installState);
}
