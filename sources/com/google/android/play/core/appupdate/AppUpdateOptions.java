package com.google.android.play.core.appupdate;

import android.support.annotation.NonNull;
import com.google.android.play.core.install.model.AppUpdateType;

/* JADX INFO: loaded from: classes.dex */
public abstract class AppUpdateOptions {

    public static abstract class Builder {
        @NonNull
        public abstract AppUpdateOptions build();

        @NonNull
        public abstract Builder setAllowAssetPackDeletion(boolean z);

        @NonNull
        public abstract Builder setAppUpdateType(@AppUpdateType int i);
    }

    @NonNull
    public static AppUpdateOptions defaultOptions(@AppUpdateType int i) {
        return newBuilder(i).build();
    }

    @NonNull
    public static Builder newBuilder(@AppUpdateType int i) {
        s sVar = new s();
        sVar.setAppUpdateType(i);
        sVar.setAllowAssetPackDeletion(false);
        return sVar;
    }

    public abstract boolean allowAssetPackDeletion();

    @AppUpdateType
    public abstract int appUpdateType();
}
