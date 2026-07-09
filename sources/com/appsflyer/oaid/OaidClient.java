package com.appsflyer.oaid;

import android.content.Context;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.appsflyer.internal.referrer.Payload;
import com.bun.miitmdid.interfaces.IIdentifierListener;
import com.huawei.hms.ads.identifier.AdvertisingIdClient;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class OaidClient {
    private final Context context;
    private final Logger logger;
    private final long timeout;
    private final TimeUnit unit;

    public OaidClient(Context context, long j, TimeUnit timeUnit) {
        this.logger = Logger.getLogger("AppsFlyerOaid6.1.2");
        this.context = context;
        this.timeout = j;
        this.unit = timeUnit;
        this.logger.setLevel(Level.OFF);
    }

    public OaidClient(Context context) {
        this(context, 1L, TimeUnit.SECONDS);
    }

    private static boolean isHuawei() {
        try {
            if (!Build.BRAND.equalsIgnoreCase(Payload.SOURCE_HUAWEI)) {
                if (((Integer) Class.forName("com.huawei.android.os.BuildEx$VERSION").getDeclaredField("EMUI_SDK_INT").get(null)).intValue() <= 0) {
                    return false;
                }
            }
            return true;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException unused) {
            return false;
        }
    }

    private static boolean isMsaAvailableAtRuntime() {
        try {
            IIdentifierListener.class.getName();
            return true;
        } catch (NoClassDefFoundError unused) {
            return false;
        }
    }

    @Nullable
    public Info fetch() {
        Info infoFetchMsa;
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (isHuawei()) {
                    infoFetchMsa = fetchHuawei();
                } else {
                    infoFetchMsa = isMsaAvailableAtRuntime() ? OaidMsaClient.fetchMsa(this.context, this.logger, this.timeout, this.unit) : null;
                }
                this.logger.info("Fetch " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms");
                return infoFetchMsa;
            } catch (Throwable th) {
                this.logger.info(th.getMessage());
            }
        }
        return null;
    }

    @Nullable
    private Info fetchHuawei() {
        try {
            if (!AdvertisingIdClient.isAdvertisingIdAvailable(this.context)) {
                return null;
            }
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.context);
            return new Info(advertisingIdInfo.getId(), Boolean.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled()));
        } catch (Throwable th) {
            this.logger.info(th.getMessage());
            return null;
        }
    }

    public void setLogging(boolean z) {
        this.logger.setLevel(z ? null : Level.OFF);
    }

    public static class Info {
        private final String id;
        private final Boolean lat;

        @VisibleForTesting
        public Info(String str, Boolean bool) {
            this.id = str;
            this.lat = bool;
        }

        public Info(String str) {
            this(str, null);
        }

        public String getId() {
            return this.id;
        }

        @Nullable
        public Boolean getLat() {
            return this.lat;
        }
    }
}
