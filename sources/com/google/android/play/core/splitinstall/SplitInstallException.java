package com.google.android.play.core.splitinstall;

import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode;

/* JADX INFO: loaded from: classes.dex */
public class SplitInstallException extends com.google.android.play.core.tasks.j {

    @SplitInstallErrorCode
    private final int a;

    public SplitInstallException(@SplitInstallErrorCode int i) {
        super(String.format("Split Install Error(%d): %s", Integer.valueOf(i), com.google.android.play.core.splitinstall.model.a.a(i)));
        if (i == 0) {
            throw new IllegalArgumentException("errorCode should not be 0.");
        }
        this.a = i;
    }

    @Override // com.google.android.play.core.tasks.j
    @SplitInstallErrorCode
    public int getErrorCode() {
        return this.a;
    }
}
