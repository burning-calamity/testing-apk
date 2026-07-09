package com.google.android.gms.nearby.messages;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.quest.Quests;
import java.util.ArrayList;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "StrategyCreator")
@Immutable
public class Strategy extends AbstractSafeParcelable {
    public static final Strategy BLE_ONLY;
    public static final Parcelable.Creator<Strategy> CREATOR = new zzg();
    public static final Strategy DEFAULT = new Builder().build();
    public static final int DISCOVERY_MODE_BROADCAST = 1;
    public static final int DISCOVERY_MODE_DEFAULT = 3;
    public static final int DISCOVERY_MODE_SCAN = 2;
    public static final int DISTANCE_TYPE_DEFAULT = 0;
    public static final int DISTANCE_TYPE_EARSHOT = 1;
    public static final int TTL_SECONDS_DEFAULT = 300;
    public static final int TTL_SECONDS_INFINITE = Integer.MAX_VALUE;
    public static final int TTL_SECONDS_MAX = 86400;

    @Deprecated
    private static final Strategy zzfm;

    @SafeParcelable.VersionField(id = 1000)
    private final int zzex;

    @SafeParcelable.Field(id = 1)
    @Deprecated
    private final int zzfn;

    @SafeParcelable.Field(id = 2)
    private final int zzfo;

    @SafeParcelable.Field(id = 3)
    private final int zzfp;

    @SafeParcelable.Field(id = 4)
    @Deprecated
    private final boolean zzfq;

    @SafeParcelable.Field(getter = "getDiscoveryMedium", id = 5)
    private final int zzfr;

    @SafeParcelable.Field(getter = "getDiscoveryMode", id = 6)
    private final int zzfs;

    @SafeParcelable.Field(getter = "getBackgroundScanMode", id = 7)
    private final int zzft;

    public static class Builder {
        private int zzfu = 3;
        private int zzfv = Strategy.TTL_SECONDS_DEFAULT;
        private int zzfw = 0;
        private int zzfx = -1;
        private int zzfy = 0;

        public Strategy build() {
            if (this.zzfx == 2 && this.zzfw == 1) {
                throw new IllegalStateException("Cannot set EARSHOT with BLE only mode.");
            }
            return new Strategy(2, 0, this.zzfv, this.zzfw, false, this.zzfx, this.zzfu, 0);
        }

        public Builder setDiscoveryMode(int i) {
            this.zzfu = i;
            return this;
        }

        public Builder setDistanceType(int i) {
            this.zzfw = i;
            return this;
        }

        public Builder setTtlSeconds(int i) {
            Preconditions.checkArgument(i == Integer.MAX_VALUE || (i > 0 && i <= 86400), "mTtlSeconds(%d) must either be TTL_SECONDS_INFINITE, or it must be between 1 and TTL_SECONDS_MAX(%d) inclusive", Integer.valueOf(i), Integer.valueOf(Strategy.TTL_SECONDS_MAX));
            this.zzfv = i;
            return this;
        }

        public final Builder zze(int i) {
            this.zzfx = 2;
            return this;
        }
    }

    static {
        Strategy strategyBuild = new Builder().zze(2).setTtlSeconds(Integer.MAX_VALUE).build();
        BLE_ONLY = strategyBuild;
        zzfm = strategyBuild;
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x000b A[PHI: r8
  0x000b: PHI (r8v2 int) = (r8v0 int), (r8v1 int) binds: [B:3:0x0009, B:7:0x0011] A[DONT_GENERATE, DONT_INLINE]] */
    @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    Strategy(@com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 1000) int r2, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 1) int r3, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 2) int r4, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 3) int r5, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 4) boolean r6, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 5) int r7, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 6) int r8, @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param(id = 7) int r9) {
        /*
            r1 = this;
            r1.<init>()
            r1.zzex = r2
            r1.zzfn = r3
            r2 = 1
            r0 = 2
            if (r3 != 0) goto Le
        Lb:
            r1.zzfs = r8
            goto L19
        Le:
            if (r3 == r0) goto L17
            r8 = 3
            if (r3 == r8) goto L14
            goto Lb
        L14:
            r1.zzfs = r0
            goto L19
        L17:
            r1.zzfs = r2
        L19:
            r1.zzfp = r5
            r1.zzfq = r6
            if (r6 == 0) goto L27
            r1.zzfr = r0
            r2 = 2147483647(0x7fffffff, float:NaN)
            r1.zzfo = r2
            goto L38
        L27:
            r1.zzfo = r4
            r3 = -1
            if (r7 == r3) goto L36
            if (r7 == 0) goto L36
            if (r7 == r2) goto L36
            r2 = 6
            if (r7 == r2) goto L36
            r1.zzfr = r7
            goto L38
        L36:
            r1.zzfr = r3
        L38:
            r1.zzft = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.nearby.messages.Strategy.<init>(int, int, int, int, boolean, int, int, int):void");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Strategy)) {
            return false;
        }
        Strategy strategy = (Strategy) obj;
        return this.zzex == strategy.zzex && this.zzfs == strategy.zzfs && this.zzfo == strategy.zzfo && this.zzfp == strategy.zzfp && this.zzfr == strategy.zzfr && this.zzft == strategy.zzft;
    }

    public int hashCode() {
        return (((((((((this.zzex * 31) + this.zzfs) * 31) + this.zzfo) * 31) + this.zzfp) * 31) + this.zzfr) * 31) + this.zzft;
    }

    public String toString() {
        String string;
        String string2;
        String string3;
        int i = this.zzfo;
        int i2 = this.zzfp;
        String string4 = "DEFAULT";
        if (i2 == 0) {
            string = "DEFAULT";
        } else if (i2 != 1) {
            StringBuilder sb = new StringBuilder(19);
            sb.append("UNKNOWN:");
            sb.append(i2);
            string = sb.toString();
        } else {
            string = "EARSHOT";
        }
        int i3 = this.zzfr;
        if (i3 == -1) {
            string2 = "DEFAULT";
        } else {
            ArrayList arrayList = new ArrayList();
            if ((i3 & 4) > 0) {
                arrayList.add("ULTRASOUND");
            }
            if ((i3 & 2) > 0) {
                arrayList.add("BLE");
            }
            if (arrayList.isEmpty()) {
                StringBuilder sb2 = new StringBuilder(19);
                sb2.append("UNKNOWN:");
                sb2.append(i3);
                string2 = sb2.toString();
            } else {
                string2 = arrayList.toString();
            }
        }
        int i4 = this.zzfs;
        if (i4 == 3) {
            string3 = "DEFAULT";
        } else {
            ArrayList arrayList2 = new ArrayList();
            if ((i4 & 1) > 0) {
                arrayList2.add("BROADCAST");
            }
            if ((i4 & 2) > 0) {
                arrayList2.add("SCAN");
            }
            if (arrayList2.isEmpty()) {
                StringBuilder sb3 = new StringBuilder(19);
                sb3.append("UNKNOWN:");
                sb3.append(i4);
                string3 = sb3.toString();
            } else {
                string3 = arrayList2.toString();
            }
        }
        int i5 = this.zzft;
        if (i5 != 0) {
            if (i5 != 1) {
                StringBuilder sb4 = new StringBuilder(20);
                sb4.append("UNKNOWN: ");
                sb4.append(i5);
                string4 = sb4.toString();
            } else {
                string4 = "ALWAYS_ON";
            }
        }
        StringBuilder sb5 = new StringBuilder(String.valueOf(string).length() + Quests.SELECT_ENDING_SOON + String.valueOf(string2).length() + String.valueOf(string3).length() + String.valueOf(string4).length());
        sb5.append("Strategy{ttlSeconds=");
        sb5.append(i);
        sb5.append(", distanceType=");
        sb5.append(string);
        sb5.append(", discoveryMedium=");
        sb5.append(string2);
        sb5.append(", discoveryMode=");
        sb5.append(string3);
        sb5.append(", backgroundScanMode=");
        sb5.append(string4);
        sb5.append('}');
        return sb5.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzfn);
        SafeParcelWriter.writeInt(parcel, 2, this.zzfo);
        SafeParcelWriter.writeInt(parcel, 3, this.zzfp);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzfq);
        SafeParcelWriter.writeInt(parcel, 5, this.zzfr);
        SafeParcelWriter.writeInt(parcel, 6, this.zzfs);
        SafeParcelWriter.writeInt(parcel, 7, this.zzft);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzex);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final int zzae() {
        return this.zzft;
    }
}
