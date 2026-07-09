package com.android.billingclient.api;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.android.billingclient:billing@@3.0.3 */
/* JADX INFO: loaded from: classes.dex */
public class SkuDetailsParams {
    private String zza;
    private List<String> zzb;

    /* JADX INFO: compiled from: com.android.billingclient:billing@@3.0.3 */
    public static class Builder {
        private String zza;
        private List<String> zzb;

        private Builder() {
        }

        /* synthetic */ Builder(zzaq zzaqVar) {
        }

        @NonNull
        public SkuDetailsParams build() {
            if (this.zza == null) {
                throw new IllegalArgumentException("SKU type must be set");
            }
            if (this.zzb == null) {
                throw new IllegalArgumentException("SKU list or SkuWithOffer list must be set");
            }
            SkuDetailsParams skuDetailsParams = new SkuDetailsParams();
            skuDetailsParams.zza = this.zza;
            skuDetailsParams.zzb = this.zzb;
            return skuDetailsParams;
        }

        @NonNull
        public Builder setSkusList(@NonNull List<String> list) {
            this.zzb = new ArrayList(list);
            return this;
        }

        @NonNull
        public Builder setType(@NonNull String str) {
            this.zza = str;
            return this;
        }
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder(null);
    }

    @NonNull
    public String getSkuType() {
        return this.zza;
    }

    @NonNull
    public List<String> getSkusList() {
        return this.zzb;
    }
}
