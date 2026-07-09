package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
final class ah extends ag<ParcelFileDescriptor> {
    ah(an anVar, com.google.android.play.core.tasks.i<ParcelFileDescriptor> iVar) {
        super(anVar, iVar);
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.google.android.play.core.assetpacks.ag, com.google.android.play.core.internal.v
    public final void e(Bundle bundle, Bundle bundle2) throws RemoteException {
        super.e(bundle, bundle2);
        this.a.e((T) ((ParcelFileDescriptor) bundle.getParcelable("chunk_file_descriptor")));
    }
}
