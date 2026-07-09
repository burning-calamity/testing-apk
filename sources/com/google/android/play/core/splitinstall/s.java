package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class s extends com.google.android.play.core.listener.b<SplitInstallSessionState> {

    @Nullable
    private static s c;
    private final Handler d;
    private final e e;
    private final Set<SplitInstallStateUpdatedListener> f;

    public s(Context context, e eVar) {
        super(new com.google.android.play.core.internal.ag("SplitInstallListenerRegistry"), new IntentFilter("com.google.android.play.core.splitinstall.receiver.SplitInstallUpdateIntentService"), context);
        this.d = new Handler(Looper.getMainLooper());
        this.f = new LinkedHashSet();
        this.e = eVar;
    }

    public static synchronized s b(Context context) {
        if (c == null) {
            c = new s(context, l.a);
        }
        return c;
    }

    @Override // com.google.android.play.core.listener.b
    protected final void a(Context context, Intent intent) {
        Bundle bundleExtra = intent.getBundleExtra("session_state");
        if (bundleExtra == null) {
            return;
        }
        SplitInstallSessionState splitInstallSessionStateD = SplitInstallSessionState.d(bundleExtra);
        this.a.a("ListenerRegistryBroadcastReceiver.onReceive: %s", splitInstallSessionStateD);
        f fVarA = this.e.a();
        if (splitInstallSessionStateD.status() != 3 || fVarA == null) {
            k(splitInstallSessionStateD);
        } else {
            fVarA.a(splitInstallSessionStateD.c(), new q(this, splitInstallSessionStateD, intent, context));
        }
    }

    final synchronized void c(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.f.add(splitInstallStateUpdatedListener);
    }

    final synchronized void d(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.f.remove(splitInstallStateUpdatedListener);
    }

    public final synchronized void k(SplitInstallSessionState splitInstallSessionState) {
        Iterator it = new LinkedHashSet(this.f).iterator();
        while (it.hasNext()) {
            ((SplitInstallStateUpdatedListener) it.next()).onStateUpdate(splitInstallSessionState);
        }
        super.i(splitInstallSessionState);
    }
}
