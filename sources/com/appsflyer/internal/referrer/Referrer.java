package com.appsflyer.internal.referrer;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/* JADX INFO: loaded from: classes.dex */
public abstract class Referrer extends Observable {
    public final Map<String, Object> map = new HashMap();

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    private State f339 = State.NOT_STARTED;

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    private long f340;

    public enum State {
        NOT_STARTED,
        STARTED,
        FINISHED
    }

    public void start(final Runnable runnable) {
        this.f340 = System.currentTimeMillis();
        this.f339 = State.STARTED;
        addObserver(new Observer() { // from class: com.appsflyer.internal.referrer.Referrer.2
            @Override // java.util.Observer
            public final void update(Observable observable, Object obj) {
                runnable.run();
            }
        });
    }

    public void finish() {
        this.map.put(Payload.LATENCY, Long.valueOf(System.currentTimeMillis() - this.f340));
        this.f339 = State.FINISHED;
        setChanged();
        notifyObservers();
    }

    public State getState() {
        return this.f339;
    }
}
