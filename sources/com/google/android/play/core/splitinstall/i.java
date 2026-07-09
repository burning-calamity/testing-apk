package com.google.android.play.core.splitinstall;

import android.app.Activity;
import android.content.IntentSender;
import android.support.annotation.NonNull;
import com.google.android.play.core.common.IntentSenderForResultStarter;
import com.google.android.play.core.internal.ca;
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager;
import com.google.android.play.core.tasks.Task;
import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
final class i implements SplitInstallManager {
    private final ca<v> a;
    private final ca<FakeSplitInstallManager> b;
    private final ca<File> c;

    i(ca<v> caVar, ca<FakeSplitInstallManager> caVar2, ca<File> caVar3) {
        this.a = caVar;
        this.b = caVar2;
        this.c = caVar3;
    }

    private final SplitInstallManager c() {
        return (SplitInstallManager) (this.c.a() == null ? this.a : this.b).a();
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final void a(@NonNull SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        c().a(splitInstallStateUpdatedListener);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final void b(@NonNull SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        c().b(splitInstallStateUpdatedListener);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    @NonNull
    public final Task<Void> cancelInstall(int i) {
        return c().cancelInstall(i);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    @NonNull
    public final Task<Void> deferredInstall(List<String> list) {
        return c().deferredInstall(list);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    @NonNull
    public final Task<Void> deferredLanguageInstall(List<Locale> list) {
        return c().deferredLanguageInstall(list);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    @NonNull
    public final Task<Void> deferredLanguageUninstall(List<Locale> list) {
        return c().deferredLanguageUninstall(list);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    @NonNull
    public final Task<Void> deferredUninstall(List<String> list) {
        return c().deferredUninstall(list);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    @NonNull
    public final Set<String> getInstalledLanguages() {
        return c().getInstalledLanguages();
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    @NonNull
    public final Set<String> getInstalledModules() {
        return c().getInstalledModules();
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    @NonNull
    public final Task<SplitInstallSessionState> getSessionState(int i) {
        return c().getSessionState(i);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    @NonNull
    public final Task<List<SplitInstallSessionState>> getSessionStates() {
        return c().getSessionStates();
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final void registerListener(@NonNull SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        c().registerListener(splitInstallStateUpdatedListener);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final boolean startConfirmationDialogForResult(@NonNull SplitInstallSessionState splitInstallSessionState, @NonNull Activity activity, int i) throws IntentSender.SendIntentException {
        return c().startConfirmationDialogForResult(splitInstallSessionState, activity, i);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final boolean startConfirmationDialogForResult(@NonNull SplitInstallSessionState splitInstallSessionState, @NonNull IntentSenderForResultStarter intentSenderForResultStarter, int i) throws IntentSender.SendIntentException {
        return c().startConfirmationDialogForResult(splitInstallSessionState, intentSenderForResultStarter, i);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final Task<Integer> startInstall(@NonNull SplitInstallRequest splitInstallRequest) {
        return c().startInstall(splitInstallRequest);
    }

    @Override // com.google.android.play.core.splitinstall.SplitInstallManager
    public final void unregisterListener(@NonNull SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        c().unregisterListener(splitInstallStateUpdatedListener);
    }
}
