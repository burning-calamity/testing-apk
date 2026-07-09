package com.google.android.play.core.assetpacks;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
final class b extends com.google.android.play.core.internal.w {
    private final com.google.android.play.core.internal.ag a = new com.google.android.play.core.internal.ag("AssetPackExtractionService");
    private final Context b;
    private final au c;

    b(Context context, au auVar) {
        this.b = context;
        this.c = auVar;
    }

    private final synchronized void d(Bundle bundle) {
        ComponentName componentNameStartForegroundService;
        Intent intent = new Intent(this.b, (Class<?>) ExtractionForegroundService.class);
        int i = bundle.getInt("action_type");
        intent.putExtra("action_type", i);
        if (i == 1) {
            intent.putExtra("notification_channel_name", bundle.getString("notification_channel_name"));
            intent.putExtra("notification_title", bundle.getString("notification_title"));
            intent.putExtra("notification_subtext", bundle.getString("notification_subtext"));
            intent.putExtra("notification_timeout", bundle.getLong("notification_timeout"));
            Parcelable parcelable = bundle.getParcelable("notification_on_click_intent");
            if (parcelable instanceof PendingIntent) {
                intent.putExtra("notification_on_click_intent", parcelable);
            }
            intent.putExtra("notification_color", bundle.getInt("notification_color"));
        }
        try {
            componentNameStartForegroundService = Build.VERSION.SDK_INT >= 26 ? this.b.startForegroundService(intent) : this.b.startService(intent);
        } catch (IllegalStateException | SecurityException e) {
            this.a.c(e, "Failed starting installation service.", new Object[0]);
            componentNameStartForegroundService = null;
        }
        if (componentNameStartForegroundService == null) {
            this.a.b("Failed starting installation service.", new Object[0]);
        }
    }

    @Override // com.google.android.play.core.internal.x
    public final void b(Bundle bundle, com.google.android.play.core.internal.z zVar) throws RemoteException {
        this.a.a("updateServiceState AIDL call", new Object[0]);
        if (!com.google.android.play.core.internal.bp.a(this.b) || !com.google.android.play.core.internal.bp.b(this.b)) {
            zVar.d(new Bundle());
        } else {
            d(bundle);
            zVar.c(new Bundle(), new Bundle());
        }
    }

    @Override // com.google.android.play.core.internal.x
    public final void c(com.google.android.play.core.internal.z zVar) throws RemoteException {
        this.a.a("clearAssetPackStorage AIDL call", new Object[0]);
        if (!com.google.android.play.core.internal.bp.a(this.b) || !com.google.android.play.core.internal.bp.b(this.b)) {
            zVar.d(new Bundle());
        } else {
            this.c.x();
            zVar.e(new Bundle());
        }
    }
}
