package com.android.billingclient.api;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: com.android.billingclient:billing@@3.0.3 */
/* JADX INFO: loaded from: classes.dex */
public interface ConsumeResponseListener {
    void onConsumeResponse(@NonNull BillingResult billingResult, @NonNull String str);
}
