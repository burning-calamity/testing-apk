package com.google.android.play.core.listener;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import com.google.android.play.core.internal.ag;
import com.google.android.play.core.internal.ax;
import com.google.android.play.core.splitcompat.p;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class b<StateT> {
    protected final ag a;
    private final IntentFilter c;
    private final Context d;
    protected final Set<StateUpdatedListener<StateT>> b = new HashSet();

    @Nullable
    private a e = null;
    private volatile boolean f = false;

    protected b(ag agVar, IntentFilter intentFilter, Context context) {
        this.a = agVar;
        this.c = intentFilter;
        this.d = p.c(context);
    }

    private final void b() {
        a aVar;
        if ((this.f || !this.b.isEmpty()) && this.e == null) {
            this.e = new a(this);
            this.d.registerReceiver(this.e, this.c);
        }
        if (this.f || !this.b.isEmpty() || (aVar = this.e) == null) {
            return;
        }
        this.d.unregisterReceiver(aVar);
        this.e = null;
    }

    protected abstract void a(Context context, Intent intent);

    public final synchronized void e(boolean z) {
        this.f = z;
        b();
    }

    public final synchronized void f(StateUpdatedListener<StateT> stateUpdatedListener) {
        this.a.d("registerListener", new Object[0]);
        ax.d(stateUpdatedListener, "Registered Play Core listener should not be null.");
        this.b.add(stateUpdatedListener);
        b();
    }

    public final synchronized void g(StateUpdatedListener<StateT> stateUpdatedListener) {
        this.a.d("unregisterListener", new Object[0]);
        ax.d(stateUpdatedListener, "Unregistered Play Core listener should not be null.");
        this.b.remove(stateUpdatedListener);
        b();
    }

    public final synchronized void h() {
        this.a.d("clearListeners", new Object[0]);
        this.b.clear();
        b();
    }

    public final synchronized void i(StateT statet) {
        Iterator it = new HashSet(this.b).iterator();
        while (it.hasNext()) {
            ((StateUpdatedListener) it.next()).onStateUpdate(statet);
        }
    }

    public final synchronized boolean j() {
        return this.e != null;
    }
}
