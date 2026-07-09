package com.google.firebase.encoders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: com.google.firebase:firebase-encoders-json@@16.0.0 */
/* JADX INFO: loaded from: classes.dex */
public interface Encoder<TValue, TContext> {
    void encode(@Nullable TValue tvalue, @NonNull TContext tcontext) throws EncodingException, IOException;
}
