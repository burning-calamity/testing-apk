package com.google.firebase.messaging;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.nearby.connection.Connections;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-messaging@@20.1.0 */
/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "RemoteMessageCreator")
@SafeParcelable.Reserved({1})
public final class RemoteMessage extends AbstractSafeParcelable {
    public static final Parcelable.Creator<RemoteMessage> CREATOR = new zzq();
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_NORMAL = 2;
    public static final int PRIORITY_UNKNOWN = 0;

    @SafeParcelable.Field(id = 2)
    Bundle zza;
    private Map<String, String> zzb;
    private Notification zzc;

    /* JADX INFO: compiled from: com.google.firebase:firebase-messaging@@20.1.0 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface MessagePriority {
    }

    @SafeParcelable.Constructor
    public RemoteMessage(@SafeParcelable.Param(id = 2) Bundle bundle) {
        this.zza = bundle;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 2, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-messaging@@20.1.0 */
    public static class Builder {
        private final Bundle zza = new Bundle();
        private final Map<String, String> zzb = new ArrayMap();

        public Builder(@NonNull String str) {
            if (TextUtils.isEmpty(str)) {
                String strValueOf = String.valueOf(str);
                throw new IllegalArgumentException(strValueOf.length() != 0 ? "Invalid to: ".concat(strValueOf) : new String("Invalid to: "));
            }
            this.zza.putString("google.to", str);
        }

        @NonNull
        public RemoteMessage build() {
            Bundle bundle = new Bundle();
            for (Map.Entry<String, String> entry : this.zzb.entrySet()) {
                bundle.putString(entry.getKey(), entry.getValue());
            }
            bundle.putAll(this.zza);
            this.zza.remove("from");
            return new RemoteMessage(bundle);
        }

        @NonNull
        public Builder addData(@NonNull String str, @Nullable String str2) {
            this.zzb.put(str, str2);
            return this;
        }

        @NonNull
        public Builder setData(@NonNull Map<String, String> map) {
            this.zzb.clear();
            this.zzb.putAll(map);
            return this;
        }

        @NonNull
        public Builder clearData() {
            this.zzb.clear();
            return this;
        }

        @NonNull
        public Builder setMessageId(@NonNull String str) {
            this.zza.putString("google.message_id", str);
            return this;
        }

        @NonNull
        public Builder setMessageType(@Nullable String str) {
            this.zza.putString("message_type", str);
            return this;
        }

        @NonNull
        public Builder setTtl(@IntRange(from = Connections.DURATION_INDEFINITE, to = 86400) int i) {
            this.zza.putString("google.ttl", String.valueOf(i));
            return this;
        }

        @NonNull
        public Builder setCollapseKey(@Nullable String str) {
            this.zza.putString("collapse_key", str);
            return this;
        }
    }

    @Nullable
    public final String getFrom() {
        return this.zza.getString("from");
    }

    @Nullable
    public final String getTo() {
        return this.zza.getString("google.to");
    }

    @NonNull
    public final Map<String, String> getData() {
        if (this.zzb == null) {
            Bundle bundle = this.zza;
            ArrayMap arrayMap = new ArrayMap();
            for (String str : bundle.keySet()) {
                Object obj = bundle.get(str);
                if (obj instanceof String) {
                    String str2 = (String) obj;
                    if (!str.startsWith("google.") && !str.startsWith("gcm.") && !str.equals("from") && !str.equals("message_type") && !str.equals("collapse_key")) {
                        arrayMap.put(str, str2);
                    }
                }
            }
            this.zzb = arrayMap;
        }
        return this.zzb;
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-messaging@@20.1.0 */
    public static class Notification {
        private final String zza;
        private final String zzb;
        private final String[] zzc;
        private final String zzd;
        private final String zze;
        private final String[] zzf;
        private final String zzg;
        private final String zzh;
        private final String zzi;
        private final String zzj;
        private final String zzk;
        private final String zzl;
        private final String zzm;
        private final Uri zzn;
        private final String zzo;
        private final Integer zzp;
        private final Integer zzq;
        private final Integer zzr;
        private final int[] zzs;
        private final Long zzt;
        private final boolean zzu;
        private final boolean zzv;
        private final boolean zzw;
        private final boolean zzx;
        private final boolean zzy;
        private final long[] zzz;

        private Notification(zzn zznVar) {
            this.zza = zznVar.zza("gcm.n.title");
            this.zzb = zznVar.zze("gcm.n.title");
            this.zzc = zza(zznVar, "gcm.n.title");
            this.zzd = zznVar.zza("gcm.n.body");
            this.zze = zznVar.zze("gcm.n.body");
            this.zzf = zza(zznVar, "gcm.n.body");
            this.zzg = zznVar.zza("gcm.n.icon");
            this.zzi = zznVar.zzb();
            this.zzj = zznVar.zza("gcm.n.tag");
            this.zzk = zznVar.zza("gcm.n.color");
            this.zzl = zznVar.zza("gcm.n.click_action");
            this.zzm = zznVar.zza("gcm.n.android_channel_id");
            this.zzn = zznVar.zza();
            this.zzh = zznVar.zza("gcm.n.image");
            this.zzo = zznVar.zza("gcm.n.ticker");
            this.zzp = zznVar.zzc("gcm.n.notification_priority");
            this.zzq = zznVar.zzc("gcm.n.visibility");
            this.zzr = zznVar.zzc("gcm.n.notification_count");
            this.zzu = zznVar.zzb("gcm.n.sticky");
            this.zzv = zznVar.zzb("gcm.n.local_only");
            this.zzw = zznVar.zzb("gcm.n.default_sound");
            this.zzx = zznVar.zzb("gcm.n.default_vibrate_timings");
            this.zzy = zznVar.zzb("gcm.n.default_light_settings");
            this.zzt = zznVar.zzd("gcm.n.event_time");
            this.zzs = zznVar.zzd();
            this.zzz = zznVar.zzc();
        }

        private static String[] zza(zzn zznVar, String str) {
            Object[] objArrZzf = zznVar.zzf(str);
            if (objArrZzf == null) {
                return null;
            }
            String[] strArr = new String[objArrZzf.length];
            for (int i = 0; i < objArrZzf.length; i++) {
                strArr[i] = String.valueOf(objArrZzf[i]);
            }
            return strArr;
        }

        @Nullable
        public String getTitle() {
            return this.zza;
        }

        @Nullable
        public String getTitleLocalizationKey() {
            return this.zzb;
        }

        @Nullable
        public String[] getTitleLocalizationArgs() {
            return this.zzc;
        }

        @Nullable
        public String getBody() {
            return this.zzd;
        }

        @Nullable
        public String getBodyLocalizationKey() {
            return this.zze;
        }

        @Nullable
        public String[] getBodyLocalizationArgs() {
            return this.zzf;
        }

        @Nullable
        public String getIcon() {
            return this.zzg;
        }

        @Nullable
        public Uri getImageUrl() {
            String str = this.zzh;
            if (str != null) {
                return Uri.parse(str);
            }
            return null;
        }

        @Nullable
        public String getSound() {
            return this.zzi;
        }

        @Nullable
        public String getTag() {
            return this.zzj;
        }

        @Nullable
        public String getColor() {
            return this.zzk;
        }

        @Nullable
        public String getClickAction() {
            return this.zzl;
        }

        @Nullable
        public String getChannelId() {
            return this.zzm;
        }

        @Nullable
        public Uri getLink() {
            return this.zzn;
        }

        @Nullable
        public String getTicker() {
            return this.zzo;
        }

        public boolean getSticky() {
            return this.zzu;
        }

        public boolean getLocalOnly() {
            return this.zzv;
        }

        public boolean getDefaultSound() {
            return this.zzw;
        }

        public boolean getDefaultVibrateSettings() {
            return this.zzx;
        }

        public boolean getDefaultLightSettings() {
            return this.zzy;
        }

        @Nullable
        public Integer getNotificationPriority() {
            return this.zzp;
        }

        @Nullable
        public Integer getVisibility() {
            return this.zzq;
        }

        @Nullable
        public Integer getNotificationCount() {
            return this.zzr;
        }

        @Nullable
        public Long getEventTime() {
            return this.zzt;
        }

        @Nullable
        public int[] getLightSettings() {
            return this.zzs;
        }

        @Nullable
        public long[] getVibrateTimings() {
            return this.zzz;
        }
    }

    @Nullable
    public final String getCollapseKey() {
        return this.zza.getString("collapse_key");
    }

    @Nullable
    public final String getMessageId() {
        String string = this.zza.getString("google.message_id");
        return string == null ? this.zza.getString("message_id") : string;
    }

    @Nullable
    public final String getMessageType() {
        return this.zza.getString("message_type");
    }

    public final long getSentTime() {
        Object obj = this.zza.get("google.sent_time");
        if (obj instanceof Long) {
            return ((Long) obj).longValue();
        }
        if (!(obj instanceof String)) {
            return 0L;
        }
        try {
            return Long.parseLong((String) obj);
        } catch (NumberFormatException unused) {
            String strValueOf = String.valueOf(obj);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 19);
            sb.append("Invalid sent time: ");
            sb.append(strValueOf);
            Log.w("FirebaseMessaging", sb.toString());
            return 0L;
        }
    }

    public final int getTtl() {
        Object obj = this.zza.get("google.ttl");
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (!(obj instanceof String)) {
            return 0;
        }
        try {
            return Integer.parseInt((String) obj);
        } catch (NumberFormatException unused) {
            String strValueOf = String.valueOf(obj);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 13);
            sb.append("Invalid TTL: ");
            sb.append(strValueOf);
            Log.w("FirebaseMessaging", sb.toString());
            return 0;
        }
    }

    public final int getOriginalPriority() {
        String string = this.zza.getString("google.original_priority");
        if (string == null) {
            string = this.zza.getString("google.priority");
        }
        return zza(string);
    }

    public final int getPriority() {
        String string = this.zza.getString("google.delivered_priority");
        if (string == null) {
            if ("1".equals(this.zza.getString("google.priority_reduced"))) {
                return 2;
            }
            string = this.zza.getString("google.priority");
        }
        return zza(string);
    }

    private static int zza(String str) {
        if ("high".equals(str)) {
            return 1;
        }
        return "normal".equals(str) ? 2 : 0;
    }

    @Nullable
    public final Notification getNotification() {
        if (this.zzc == null && zzn.zza(this.zza)) {
            this.zzc = new Notification(new zzn(this.zza));
        }
        return this.zzc;
    }

    @KeepForSdk
    public final Intent toIntent() {
        Intent intent = new Intent();
        intent.putExtras(this.zza);
        return intent;
    }
}
