package com.google.firebase.encoders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.firebase:firebase-encoders-json@@16.0.0 */
/* JADX INFO: loaded from: classes.dex */
public interface ObjectEncoderContext {
    @NonNull
    ObjectEncoderContext add(@NonNull String str, double d) throws EncodingException, IOException;

    @NonNull
    ObjectEncoderContext add(@NonNull String str, int i) throws EncodingException, IOException;

    @NonNull
    ObjectEncoderContext add(@NonNull String str, long j) throws EncodingException, IOException;

    @NonNull
    ObjectEncoderContext add(@NonNull String str, @Nullable Object obj) throws EncodingException, IOException;

    @NonNull
    ObjectEncoderContext add(@NonNull String str, boolean z) throws EncodingException, IOException;

    @NonNull
    ObjectEncoderContext nested(@NonNull String str) throws IOException;
}
