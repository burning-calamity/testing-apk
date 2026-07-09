package com.google.android.datatransport.runtime.backends;

import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* JADX INFO: loaded from: classes.dex */
public interface BackendRegistry {
    @Nullable
    TransportBackend get(String str);
}
