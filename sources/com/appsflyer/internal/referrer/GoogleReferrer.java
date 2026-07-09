package com.appsflyer.internal.referrer;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.appsflyer.AFLogger;
import com.appsflyer.AndroidUtils;
import com.appsflyer.AppsFlyerLibCore;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class GoogleReferrer extends Referrer {
    public final Map<String, Object> oldMap = new HashMap();

    public static boolean allow(@NonNull AppsFlyerLibCore appsFlyerLibCore, @NonNull Context context) {
        if (appsFlyerLibCore.getLaunchCounter(AppsFlyerLibCore.getSharedPreferences(context), false) > 2) {
            AFLogger.afRDLog("Install referrer will not load, the counter > 2, ");
            return false;
        }
        try {
            Class.forName("com.android.installreferrer.api.InstallReferrerClient");
            if (AndroidUtils.isPermissionAvailable(context, "com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE")) {
                AFLogger.afDebugLog("Install referrer is allowed");
                return true;
            }
            AFLogger.afDebugLog("Install referrer is not allowed");
            return false;
        } catch (ClassNotFoundException unused) {
            StringBuilder sb = new StringBuilder("Class ");
            sb.append("com.android.installreferrer.api.InstallReferrerClient");
            sb.append(" not found");
            AFLogger.afRDLog(sb.toString());
            return false;
        } catch (Throwable th) {
            AFLogger.afErrorLog("An error occurred while trying to verify manifest : ".concat("com.android.installreferrer.api.InstallReferrerClient"), th);
            return false;
        }
    }

    public void start(final Context context, @NonNull Runnable runnable) {
        start(runnable);
        try {
            final InstallReferrerClient installReferrerClientBuild = InstallReferrerClient.newBuilder(context).build();
            AFLogger.afDebugLog("Connecting to Install Referrer Library...");
            installReferrerClientBuild.startConnection(new InstallReferrerStateListener() { // from class: com.appsflyer.internal.referrer.GoogleReferrer.2
                @Override // com.android.installreferrer.api.InstallReferrerStateListener
                public final void onInstallReferrerSetupFinished(int i) {
                    GoogleReferrer.this.oldMap.put("code", String.valueOf(i));
                    GoogleReferrer.this.map.put("source", Payload.SOURCE_GOOGLE);
                    GoogleReferrer.this.map.put(Payload.API, Long.valueOf(AndroidUtils.getVersionCode(context, "com.android.vending")));
                    GoogleReferrer.this.map.put(Payload.API_NAME, AndroidUtils.getVersionName(context, "com.android.vending"));
                    GoogleReferrer.this.map.putAll(new MandatoryFields());
                    if (i == -1) {
                        AFLogger.afWarnLog("InstallReferrer SERVICE_DISCONNECTED");
                        GoogleReferrer.this.map.put(Payload.RESPONSE, Payload.RESPONSE_SERVICE_DISCONNECTED);
                    } else if (i == 0) {
                        GoogleReferrer.this.map.put(Payload.RESPONSE, Payload.RESPONSE_OK);
                        try {
                            AFLogger.afDebugLog("InstallReferrer connected");
                            if (installReferrerClientBuild.isReady()) {
                                ReferrerDetails installReferrer = installReferrerClientBuild.getInstallReferrer();
                                String installReferrer2 = installReferrer.getInstallReferrer();
                                if (installReferrer2 != null) {
                                    GoogleReferrer.this.oldMap.put("val", installReferrer2);
                                    GoogleReferrer.this.map.put(Payload.RFR, installReferrer2);
                                }
                                long referrerClickTimestampSeconds = installReferrer.getReferrerClickTimestampSeconds();
                                GoogleReferrer.this.oldMap.put("clk", Long.toString(referrerClickTimestampSeconds));
                                GoogleReferrer.this.map.put(Payload.CLICK_TS, Long.valueOf(referrerClickTimestampSeconds));
                                long installBeginTimestampSeconds = installReferrer.getInstallBeginTimestampSeconds();
                                GoogleReferrer.this.oldMap.put("install", Long.toString(installBeginTimestampSeconds));
                                GoogleReferrer.this.map.put(Payload.INSTALL_BEGIN_TS, Long.valueOf(installBeginTimestampSeconds));
                                HashMap map = new HashMap();
                                try {
                                    boolean googlePlayInstantParam = installReferrer.getGooglePlayInstantParam();
                                    GoogleReferrer.this.oldMap.put(Payload.INSTANT, Boolean.valueOf(googlePlayInstantParam));
                                    map.put(Payload.INSTANT, Boolean.valueOf(googlePlayInstantParam));
                                } catch (NoSuchMethodError unused) {
                                }
                                try {
                                    map.put(Payload.CLICK_SERVER_TS, Long.valueOf(installReferrer.getReferrerClickTimestampServerSeconds()));
                                    map.put(Payload.INSTALL_BEGIN_SERVER_TS, Long.valueOf(installReferrer.getInstallBeginTimestampServerSeconds()));
                                    map.put(Payload.INSTALL_VERSION, installReferrer.getInstallVersion());
                                } catch (NoSuchMethodError unused2) {
                                }
                                if (!map.isEmpty()) {
                                    GoogleReferrer.this.map.put(Payload.GOOGLE_CUSTOM, map);
                                }
                            } else {
                                AFLogger.afWarnLog("ReferrerClient: InstallReferrer is not ready");
                                GoogleReferrer.this.oldMap.put(NotificationCompat.CATEGORY_ERROR, "ReferrerClient: InstallReferrer is not ready");
                            }
                        } catch (Throwable th) {
                            StringBuilder sb = new StringBuilder("Failed to get install referrer: ");
                            sb.append(th.getMessage());
                            AFLogger.afWarnLog(sb.toString());
                            GoogleReferrer.this.oldMap.put(NotificationCompat.CATEGORY_ERROR, th.getMessage());
                        }
                    } else if (i == 1) {
                        GoogleReferrer.this.map.put(Payload.RESPONSE, Payload.RESPONSE_SERVICE_UNAVAILABLE);
                        AFLogger.afWarnLog("InstallReferrer not supported");
                    } else if (i == 2) {
                        AFLogger.afWarnLog("InstallReferrer FEATURE_NOT_SUPPORTED");
                        GoogleReferrer.this.map.put(Payload.RESPONSE, Payload.RESPONSE_FEATURE_NOT_SUPPORTED);
                    } else if (i == 3) {
                        AFLogger.afWarnLog("InstallReferrer DEVELOPER_ERROR");
                        GoogleReferrer.this.map.put(Payload.RESPONSE, Payload.RESPONSE_DEVELOPER_ERROR);
                    } else {
                        AFLogger.afWarnLog("responseCode not found.");
                    }
                    AFLogger.afDebugLog("Install Referrer collected locally");
                    GoogleReferrer.this.finish();
                    installReferrerClientBuild.endConnection();
                }

                @Override // com.android.installreferrer.api.InstallReferrerStateListener
                public final void onInstallReferrerServiceDisconnected() {
                    AFLogger.afDebugLog("Install Referrer service disconnected");
                }
            });
        } catch (Throwable th) {
            AFLogger.afErrorLog("referrerClient -> startConnection", th);
        }
    }
}
