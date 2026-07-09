package com.google.android.gms.internal.games;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.games.Games;

/* JADX INFO: loaded from: classes.dex */
public class zzu extends GoogleApi<Games.GamesOptions> {
    protected zzu(@NonNull Activity activity, @Nullable Games.GamesOptions gamesOptions) {
        super(activity, Games.API, gamesOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    protected zzu(@NonNull Context context, @Nullable Games.GamesOptions gamesOptions) {
        super(context, Games.API, gamesOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
