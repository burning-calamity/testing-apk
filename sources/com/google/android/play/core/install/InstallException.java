package com.google.android.play.core.install;

import com.google.android.play.core.install.model.InstallErrorCode;
import com.google.android.play.core.tasks.j;

/* JADX INFO: loaded from: classes.dex */
public class InstallException extends j {

    @InstallErrorCode
    private final int a;

    public InstallException(@InstallErrorCode int i) {
        super(String.format("Install Error(%d): %s", Integer.valueOf(i), com.google.android.play.core.install.model.a.a(i)));
        if (i == 0) {
            throw new IllegalArgumentException("errorCode should not be 0.");
        }
        this.a = i;
    }

    @Override // com.google.android.play.core.tasks.j
    @InstallErrorCode
    public int getErrorCode() {
        return this.a;
    }
}
