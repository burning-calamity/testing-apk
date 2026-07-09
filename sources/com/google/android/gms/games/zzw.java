package com.google.android.gms.games;

import androidx.annotation.NonNull;
import com.google.android.gms.games.GamesMetadata;

/* JADX INFO: loaded from: classes.dex */
final class zzw implements com.google.android.gms.games.internal.zzq<GamesMetadata.LoadGamesResult> {
    zzw() {
    }

    @Override // com.google.android.gms.games.internal.zzq
    public final /* synthetic */ void release(@NonNull GamesMetadata.LoadGamesResult loadGamesResult) {
        GamesMetadata.LoadGamesResult loadGamesResult2 = loadGamesResult;
        if (loadGamesResult2.getGames() != null) {
            loadGamesResult2.getGames().release();
        }
    }
}
