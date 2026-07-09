package com.google.android.play.core.tasks;

import android.support.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public interface OnCompleteListener<ResultT> {
    void onComplete(@NonNull Task<ResultT> task);
}
