package com.google.android.play.core.assetpacks;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
final class ar extends com.google.android.play.core.listener.b<AssetPackState> {
    private final ca c;
    private final bl d;
    private final com.google.android.play.core.internal.ca<t> e;
    private final be f;
    private final bo g;
    private final com.google.android.play.core.internal.ca<Executor> h;
    private final com.google.android.play.core.internal.ca<Executor> i;
    private final Handler j;

    ar(Context context, ca caVar, bl blVar, com.google.android.play.core.internal.ca<t> caVar2, bo boVar, be beVar, com.google.android.play.core.internal.ca<Executor> caVar3, com.google.android.play.core.internal.ca<Executor> caVar4) {
        super(new com.google.android.play.core.internal.ag("AssetPackServiceListenerRegistry"), new IntentFilter("com.google.android.play.core.assetpacks.receiver.ACTION_SESSION_UPDATE"), context);
        this.j = new Handler(Looper.getMainLooper());
        this.c = caVar;
        this.d = blVar;
        this.e = caVar2;
        this.g = boVar;
        this.f = beVar;
        this.h = caVar3;
        this.i = caVar4;
    }

    @Override // com.google.android.play.core.listener.b
    protected final void a(Context context, Intent intent) {
        final Bundle bundleExtra = intent.getBundleExtra("com.google.android.play.core.assetpacks.receiver.EXTRA_SESSION_STATE");
        if (bundleExtra == null) {
            this.a.b("Empty bundle received from broadcast.", new Object[0]);
            return;
        }
        ArrayList<String> stringArrayList = bundleExtra.getStringArrayList("pack_names");
        if (stringArrayList == null || stringArrayList.size() != 1) {
            this.a.b("Corrupt bundle received from broadcast.", new Object[0]);
            return;
        }
        final AssetPackState assetPackStateD = AssetPackState.d(bundleExtra, stringArrayList.get(0), this.g, at.b);
        this.a.a("ListenerRegistryBroadcastReceiver.onReceive: %s", assetPackStateD);
        PendingIntent pendingIntent = (PendingIntent) bundleExtra.getParcelable("confirmation_intent");
        if (pendingIntent != null) {
            this.f.a(pendingIntent);
        }
        this.i.a().execute(new Runnable(this, bundleExtra, assetPackStateD) { // from class: com.google.android.play.core.assetpacks.ap
            private final ar a;
            private final Bundle b;
            private final AssetPackState c;

            {
                this.a = this;
                this.b = bundleExtra;
                this.c = assetPackStateD;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.a.d(this.b, this.c);
            }
        });
        this.h.a().execute(new Runnable(this, bundleExtra) { // from class: com.google.android.play.core.assetpacks.aq
            private final ar a;
            private final Bundle b;

            {
                this.a = this;
                this.b = bundleExtra;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.a.c(this.b);
            }
        });
    }

    final void b(final AssetPackState assetPackState) {
        this.j.post(new Runnable(this, assetPackState) { // from class: com.google.android.play.core.assetpacks.ao
            private final ar a;
            private final AssetPackState b;

            {
                this.a = this;
                this.b = assetPackState;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.a.i(this.b);
            }
        });
    }

    final /* synthetic */ void c(Bundle bundle) {
        if (this.c.d(bundle)) {
            this.d.a();
        }
    }

    final /* synthetic */ void d(Bundle bundle, AssetPackState assetPackState) {
        if (this.c.e(bundle)) {
            b(assetPackState);
            this.e.a().j();
        }
    }
}
