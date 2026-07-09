package com.google.android.play.core.internal;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class aq<T extends IInterface> {
    private static final Map<String, Handler> a = new HashMap();
    private final Context b;
    private final ag c;
    private final String d;
    private boolean f;
    private final Intent g;
    private final am<T> h;

    @Nullable
    private ServiceConnection k;

    @Nullable
    private T l;
    private final List<ah> e = new ArrayList();
    private final IBinder.DeathRecipient j = new IBinder.DeathRecipient(this) { // from class: com.google.android.play.core.internal.ai
        private final aq a;

        {
            this.a = this;
        }

        @Override // android.os.IBinder.DeathRecipient
        public final void binderDied() {
            this.a.n();
        }
    };
    private final WeakReference<al> i = new WeakReference<>(null);

    public aq(Context context, ag agVar, String str, Intent intent, am<T> amVar) {
        this.b = context;
        this.c = agVar;
        this.d = str;
        this.g = intent;
        this.h = amVar;
    }

    static /* synthetic */ void d(aq aqVar, ah ahVar) {
        if (aqVar.l != null || aqVar.f) {
            if (!aqVar.f) {
                ahVar.run();
                return;
            } else {
                aqVar.c.d("Waiting to bind to the service.", new Object[0]);
                aqVar.e.add(ahVar);
                return;
            }
        }
        aqVar.c.d("Initiate binding to the service.", new Object[0]);
        aqVar.e.add(ahVar);
        aqVar.k = new ap(aqVar);
        aqVar.f = true;
        if (aqVar.b.bindService(aqVar.g, aqVar.k, 1)) {
            return;
        }
        aqVar.c.d("Failed to bind to the service.", new Object[0]);
        aqVar.f = false;
        Iterator<ah> it = aqVar.e.iterator();
        while (it.hasNext()) {
            it.next().b(new ar());
        }
        aqVar.e.clear();
    }

    static /* synthetic */ void j(aq aqVar) {
        aqVar.c.d("linkToDeath", new Object[0]);
        try {
            aqVar.l.asBinder().linkToDeath(aqVar.j, 0);
        } catch (RemoteException e) {
            aqVar.c.c(e, "linkToDeath failed", new Object[0]);
        }
    }

    static /* synthetic */ void m(aq aqVar) {
        aqVar.c.d("unlinkToDeath", new Object[0]);
        aqVar.l.asBinder().unlinkToDeath(aqVar.j, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void r(ah ahVar) {
        Handler handler;
        synchronized (a) {
            if (!a.containsKey(this.d)) {
                HandlerThread handlerThread = new HandlerThread(this.d, 10);
                handlerThread.start();
                a.put(this.d, new Handler(handlerThread.getLooper()));
            }
            handler = a.get(this.d);
        }
        handler.post(ahVar);
    }

    public final void a(ah ahVar) {
        r(new aj(this, ahVar.c(), ahVar));
    }

    public final void b() {
        r(new ak(this));
    }

    @Nullable
    public final T c() {
        return this.l;
    }

    final /* bridge */ /* synthetic */ void n() {
        this.c.d("reportBinderDeath", new Object[0]);
        al alVar = this.i.get();
        if (alVar != null) {
            this.c.d("calling onBinderDied", new Object[0]);
            alVar.a();
            return;
        }
        this.c.d("%s : Binder has died.", this.d);
        Iterator<ah> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().b(Build.VERSION.SDK_INT < 15 ? new RemoteException() : new RemoteException(String.valueOf(this.d).concat(" : Binder has died.")));
        }
        this.e.clear();
    }
}
