package com.google.firebase.encoders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.firebase:firebase-encoders-json@@16.0.0 */
/* JADX INFO: loaded from: classes.dex */
public interface ValueEncoderContext {
    @NonNull
    ValueEncoderContext add(double d) throws EncodingException, IOException;

    @NonNull
    ValueEncoderContext add(int i) throws EncodingException, IOException;

    @NonNull
    ValueEncoderContext add(long j) throws EncodingException, IOException;

    @NonNull
    ValueEncoderContext add(@Nullable String str) throws EncodingException, IOException;

    @NonNull
    ValueEncoderContext add(boolean z) throws EncodingException, IOException;

    @NonNull
    ValueEncoderContext add(@NonNull byte[] bArr) throws EncodingException, IOException;
}
