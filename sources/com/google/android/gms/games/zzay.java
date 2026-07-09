package com.google.android.gms.games;

import androidx.annotation.NonNull;
import com.google.android.gms.games.Players;

/* JADX INFO: loaded from: classes.dex */
final class zzay implements com.google.android.gms.games.internal.zzq<Players.LoadPlayersResult> {
    zzay() {
    }

    @Override // com.google.android.gms.games.internal.zzq
    public final /* synthetic */ void release(@NonNull Players.LoadPlayersResult loadPlayersResult) {
        Players.LoadPlayersResult loadPlayersResult2 = loadPlayersResult;
        if (loadPlayersResult2.getPlayers() != null) {
            loadPlayersResult2.getPlayers().release();
        }
    }
}
