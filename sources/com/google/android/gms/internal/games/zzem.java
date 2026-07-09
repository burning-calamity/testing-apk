package com.google.android.gms.internal.games;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class zzem {
    private static final String[] zzmt = {"requestId", "outcome"};
    private final int statusCode;
    private final HashMap<String, Integer> zzmu;

    private zzem(int i, HashMap<String, Integer> map) {
        this.statusCode = i;
        this.zzmu = map;
    }

    @VisibleForTesting
    public static zzem zzbd(DataHolder dataHolder) {
        zzeo zzeoVar = new zzeo();
        zzeoVar.zzo(dataHolder.getStatusCode());
        int count = dataHolder.getCount();
        for (int i = 0; i < count; i++) {
            int windowIndex = dataHolder.getWindowIndex(i);
            zzeoVar.zzh(dataHolder.getString("requestId", i, windowIndex), dataHolder.getInteger("outcome", i, windowIndex));
        }
        return zzeoVar.zzca();
    }

    @VisibleForTesting
    public final Set<String> getRequestIds() {
        return this.zzmu.keySet();
    }

    @VisibleForTesting
    public final int getRequestOutcome(String str) {
        boolean zContainsKey = this.zzmu.containsKey(str);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 46);
        sb.append("Request ");
        sb.append(str);
        sb.append(" was not part of the update operation!");
        Preconditions.checkArgument(zContainsKey, sb.toString());
        return this.zzmu.get(str).intValue();
    }
}
