package com.google.android.play.core.assetpacks;

import com.google.android.play.core.tasks.OnFailureListener;

/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class g implements OnFailureListener {
    static final OnFailureListener a = new g();

    private g() {
    }

    @Override // com.google.android.play.core.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        i.a.e(String.format("Could not sync active asset packs. %s", exc), new Object[0]);
    }
}
