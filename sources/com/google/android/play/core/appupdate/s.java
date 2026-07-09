package com.google.android.play.core.appupdate;

import com.google.android.play.core.appupdate.AppUpdateOptions;

/* JADX INFO: loaded from: classes.dex */
final class s extends AppUpdateOptions.Builder {
    private Integer a;
    private Boolean b;

    s() {
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateOptions.Builder
    public final AppUpdateOptions build() {
        String strConcat = this.a == null ? "".concat(" appUpdateType") : "";
        if (this.b == null) {
            strConcat = String.valueOf(strConcat).concat(" allowAssetPackDeletion");
        }
        if (strConcat.isEmpty()) {
            return new t(this.a.intValue(), this.b.booleanValue());
        }
        String strValueOf = String.valueOf(strConcat);
        throw new IllegalStateException(strValueOf.length() != 0 ? "Missing required properties:".concat(strValueOf) : new String("Missing required properties:"));
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateOptions.Builder
    public final AppUpdateOptions.Builder setAllowAssetPackDeletion(boolean z) {
        this.b = Boolean.valueOf(z);
        return this;
    }

    @Override // com.google.android.play.core.appupdate.AppUpdateOptions.Builder
    public final AppUpdateOptions.Builder setAppUpdateType(int i) {
        this.a = Integer.valueOf(i);
        return this;
    }
}
