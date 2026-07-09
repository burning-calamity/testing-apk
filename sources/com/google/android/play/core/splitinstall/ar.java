package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class ar extends at<List<SplitInstallSessionState>> {
    ar(au auVar, com.google.android.play.core.tasks.i<List<SplitInstallSessionState>> iVar) {
        super(auVar, iVar);
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.google.android.play.core.splitinstall.at, com.google.android.play.core.internal.bn
    public final void h(List<Bundle> list) throws RemoteException {
        super.h(list);
        List arrayList = new ArrayList(list.size());
        Iterator<Bundle> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(SplitInstallSessionState.d(it.next()));
        }
        this.a.e((T) arrayList);
    }
}
