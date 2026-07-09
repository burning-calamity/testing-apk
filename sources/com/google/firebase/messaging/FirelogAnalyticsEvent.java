package com.google.firebase.messaging;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.firebase:firebase-messaging@@20.1.0 */
/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
final class FirelogAnalyticsEvent {
    private final String zza;
    private final Intent zzb;

    /* JADX INFO: compiled from: com.google.firebase:firebase-messaging@@20.1.0 */
    static class zza implements ObjectEncoder<FirelogAnalyticsEvent> {
        zza() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws EncodingException, IOException {
            FirelogAnalyticsEvent firelogAnalyticsEvent = (FirelogAnalyticsEvent) obj;
            ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
            Intent intentZza = firelogAnalyticsEvent.zza();
            objectEncoderContext2.add("ttl", zzo.zzf(intentZza));
            objectEncoderContext2.add(NotificationCompat.CATEGORY_EVENT, firelogAnalyticsEvent.zzb());
            objectEncoderContext2.add("instanceId", zzo.zzc());
            objectEncoderContext2.add("priority", zzo.zzm(intentZza));
            objectEncoderContext2.add("packageName", zzo.zzb());
            objectEncoderContext2.add("sdkPlatform", "ANDROID");
            objectEncoderContext2.add("messageType", zzo.zzk(intentZza));
            String strZzj = zzo.zzj(intentZza);
            if (strZzj != null) {
                objectEncoderContext2.add("messageId", strZzj);
            }
            String strZzl = zzo.zzl(intentZza);
            if (strZzl != null) {
                objectEncoderContext2.add("topic", strZzl);
            }
            String strZzg = zzo.zzg(intentZza);
            if (strZzg != null) {
                objectEncoderContext2.add("collapseKey", strZzg);
            }
            if (zzo.zzi(intentZza) != null) {
                objectEncoderContext2.add("analyticsLabel", zzo.zzi(intentZza));
            }
            if (zzo.zzh(intentZza) != null) {
                objectEncoderContext2.add("composerLabel", zzo.zzh(intentZza));
            }
            String strZzd = zzo.zzd();
            if (strZzd != null) {
                objectEncoderContext2.add("projectNumber", strZzd);
            }
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-messaging@@20.1.0 */
    static final class zzb implements ObjectEncoder<zzc> {
        zzb() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws EncodingException, IOException {
            objectEncoderContext.add("messaging_client_event", ((zzc) obj).zza());
        }
    }

    FirelogAnalyticsEvent(@NonNull String str, @NonNull Intent intent) {
        this.zza = Preconditions.checkNotEmpty(str, "evenType must be non-null");
        this.zzb = (Intent) Preconditions.checkNotNull(intent, "intent must be non-null");
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-messaging@@20.1.0 */
    static final class zzc {
        private final FirelogAnalyticsEvent zza;

        zzc(@NonNull FirelogAnalyticsEvent firelogAnalyticsEvent) {
            this.zza = (FirelogAnalyticsEvent) Preconditions.checkNotNull(firelogAnalyticsEvent);
        }

        @NonNull
        final FirelogAnalyticsEvent zza() {
            return this.zza;
        }
    }

    @NonNull
    final Intent zza() {
        return this.zzb;
    }

    @NonNull
    final String zzb() {
        return this.zza;
    }
}
