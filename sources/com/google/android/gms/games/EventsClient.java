package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: classes.dex */
public class EventsClient extends com.google.android.gms.internal.games.zzu {
    private static final PendingResultUtil.ResultConverter<Events.LoadEventsResult, EventBuffer> zzj = new zzg();

    EventsClient(@NonNull Activity activity, @NonNull Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    EventsClient(@NonNull Context context, @NonNull Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public void increment(@NonNull String str, @IntRange(from = Connections.DURATION_INDEFINITE) int i) {
        doWrite(new zzf(this, str, i));
    }

    public Task<AnnotatedData<EventBuffer>> load(boolean z) {
        return com.google.android.gms.games.internal.zzi.zzb(Games.Events.load(asGoogleApiClient(), z), zzj);
    }

    public Task<AnnotatedData<EventBuffer>> loadByIds(boolean z, @NonNull String... strArr) {
        return com.google.android.gms.games.internal.zzi.zzb(Games.Events.loadByIds(asGoogleApiClient(), z, strArr), zzj);
    }
}
