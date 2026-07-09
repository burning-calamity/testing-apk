package com.android.billingclient.api;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import com.android.billingclient.api.Purchase;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: compiled from: com.android.billingclient:billing@@3.0.3 */
/* JADX INFO: loaded from: classes.dex */
public abstract class BillingClient {

    /* JADX INFO: compiled from: com.android.billingclient:billing@@3.0.3 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface BillingResponseCode {
        public static final int BILLING_UNAVAILABLE = 3;
        public static final int DEVELOPER_ERROR = 5;
        public static final int ERROR = 6;
        public static final int FEATURE_NOT_SUPPORTED = -2;
        public static final int ITEM_ALREADY_OWNED = 7;
        public static final int ITEM_NOT_OWNED = 8;
        public static final int ITEM_UNAVAILABLE = 4;
        public static final int OK = 0;
        public static final int SERVICE_DISCONNECTED = -1;
        public static final int SERVICE_TIMEOUT = -3;
        public static final int SERVICE_UNAVAILABLE = 2;
        public static final int USER_CANCELED = 1;
    }

    /* JADX INFO: compiled from: com.android.billingclient:billing@@3.0.3 */
    public static final class Builder {
        private boolean zza;
        private final Context zzb;
        private PurchasesUpdatedListener zzc;

        /* synthetic */ Builder(Context context, zzf zzfVar) {
            this.zzb = context;
        }

        @NonNull
        @UiThread
        public BillingClient build() {
            Context context = this.zzb;
            if (context == null) {
                throw new IllegalArgumentException("Please provide a valid Context.");
            }
            PurchasesUpdatedListener purchasesUpdatedListener = this.zzc;
            if (purchasesUpdatedListener == null) {
                throw new IllegalArgumentException("Please provide a valid listener for purchases updates.");
            }
            if (this.zza) {
                return new BillingClientImpl(null, true, context, purchasesUpdatedListener);
            }
            throw new IllegalArgumentException("Support for pending purchases must be enabled. Enable this by calling 'enablePendingPurchases()' on BillingClientBuilder.");
        }

        @NonNull
        @UiThread
        public Builder enablePendingPurchases() {
            this.zza = true;
            return this;
        }

        @NonNull
        @UiThread
        public Builder setListener(@NonNull PurchasesUpdatedListener purchasesUpdatedListener) {
            this.zzc = purchasesUpdatedListener;
            return this;
        }
    }

    /* JADX INFO: compiled from: com.android.billingclient:billing@@3.0.3 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface FeatureType {

        @NonNull
        public static final String IN_APP_ITEMS_ON_VR = "inAppItemsOnVr";

        @NonNull
        public static final String PRICE_CHANGE_CONFIRMATION = "priceChangeConfirmation";

        @NonNull
        public static final String SUBSCRIPTIONS = "subscriptions";

        @NonNull
        public static final String SUBSCRIPTIONS_ON_VR = "subscriptionsOnVr";

        @NonNull
        public static final String SUBSCRIPTIONS_UPDATE = "subscriptionsUpdate";
    }

    /* JADX INFO: compiled from: com.android.billingclient:billing@@3.0.3 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface SkuType {

        @NonNull
        public static final String INAPP = "inapp";

        @NonNull
        public static final String SUBS = "subs";
    }

    @NonNull
    @UiThread
    public static Builder newBuilder(@NonNull Context context) {
        return new Builder(context, null);
    }

    public abstract void acknowledgePurchase(@NonNull AcknowledgePurchaseParams acknowledgePurchaseParams, @NonNull AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener);

    public abstract void consumeAsync(@NonNull ConsumeParams consumeParams, @NonNull ConsumeResponseListener consumeResponseListener);

    @UiThread
    public abstract void endConnection();

    @NonNull
    @UiThread
    public abstract BillingResult isFeatureSupported(@NonNull String str);

    @UiThread
    public abstract boolean isReady();

    @NonNull
    @UiThread
    public abstract BillingResult launchBillingFlow(@NonNull Activity activity, @NonNull BillingFlowParams billingFlowParams);

    @UiThread
    public abstract void launchPriceChangeConfirmationFlow(@NonNull Activity activity, @NonNull PriceChangeFlowParams priceChangeFlowParams, @NonNull PriceChangeConfirmationListener priceChangeConfirmationListener);

    public abstract void queryPurchaseHistoryAsync(@NonNull String str, @NonNull PurchaseHistoryResponseListener purchaseHistoryResponseListener);

    @NonNull
    public abstract Purchase.PurchasesResult queryPurchases(@NonNull String str);

    public abstract void querySkuDetailsAsync(@NonNull SkuDetailsParams skuDetailsParams, @NonNull SkuDetailsResponseListener skuDetailsResponseListener);

    @UiThread
    public abstract void startConnection(@NonNull BillingClientStateListener billingClientStateListener);
}
