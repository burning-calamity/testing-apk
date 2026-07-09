package com.appsflyer.unity;

import androidx.annotation.NonNull;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerInAppPurchaseValidatorListener;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.CreateOneLinkHttpTask;
import com.appsflyer.attribution.AppsFlyerRequestListener;
import com.appsflyer.deeplink.DeepLinkListener;
import com.appsflyer.deeplink.DeepLinkResult;
import com.appsflyer.share.CrossPromotionHelper;
import com.appsflyer.share.LinkGenerator;
import com.appsflyer.share.ShareInviteHelper;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.unity3d.player.UnityPlayer;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AppsFlyerAndroidWrapper {
    private static final String GCD_CALLBACK = "onConversionDataSuccess";
    private static final String GCD_ERROR_CALLBACK = "onConversionDataFail";
    private static final String GENERATE_LINK_CALLBACK = "onInviteLinkGenerated";
    private static final String GENERATE_LINK_ERROR_CALLBACK = "onInviteLinkGeneratedFailure";
    private static final String IN_APP_RESPONSE_CALLBACK = "inAppResponseReceived";
    private static final String OAOA_CALLBACK = "onAppOpenAttribution";
    private static final String OAOA_ERROR_CALLBACK = "onAppOpenAttributionFailure";
    private static final String ON_DEEPLINKING = "onDeepLinking";
    private static final String START_REQUEST_CALLBACK = "requestResponseReceived";
    private static final String VALIDATE_CALLBACK = "didFinishValidateReceipt";
    private static final String VALIDATE_ERROR_CALLBACK = "didFinishValidateReceiptWithError";
    private static AppsFlyerConversionListener conversionListener = null;
    private static String devkey = "";

    public static void setConsumeAFDeepLinks(boolean z) {
    }

    public static void initSDK(String str, String str2) {
        if (conversionListener == null && str2 != null) {
            conversionListener = getConversionListener(str2);
        }
        devkey = str;
        AppsFlyerLib.getInstance().init(str, conversionListener, UnityPlayer.currentActivity);
        AppsFlyerLib.getInstance().setExtension("unity_android_6.1.9");
    }

    public static void startTracking(final boolean z, final String str) {
        AppsFlyerLib.getInstance().start(UnityPlayer.currentActivity, devkey, new AppsFlyerRequestListener() { // from class: com.appsflyer.unity.AppsFlyerAndroidWrapper.1
            @Override // com.appsflyer.attribution.AppsFlyerRequestListener
            public void onSuccess() {
                if (!z || str == null) {
                    return;
                }
                HashMap map = new HashMap();
                map.put("statusCode", 200);
                UnityPlayer.UnitySendMessage(str, AppsFlyerAndroidWrapper.START_REQUEST_CALLBACK, new JSONObject(map).toString());
            }

            @Override // com.appsflyer.attribution.AppsFlyerRequestListener
            public void onError(int i, @NonNull String str2) {
                if (!z || str == null) {
                    return;
                }
                HashMap map = new HashMap();
                map.put("statusCode", Integer.valueOf(i));
                map.put("errorDescription", str2);
                UnityPlayer.UnitySendMessage(str, AppsFlyerAndroidWrapper.START_REQUEST_CALLBACK, new JSONObject(map).toString());
            }
        });
    }

    public static void startTracking() {
        startTracking(false, null);
    }

    public static void stopTracking(boolean z) {
        AppsFlyerLib.getInstance().stop(z, UnityPlayer.currentActivity);
    }

    public static String getSdkVersion() {
        return AppsFlyerLib.getInstance().getSdkVersion();
    }

    public static void updateServerUninstallToken(String str) {
        AppsFlyerLib.getInstance().updateServerUninstallToken(UnityPlayer.currentActivity, str);
    }

    public static void setIsDebug(boolean z) {
        AppsFlyerLib.getInstance().setDebugLog(z);
    }

    public static void setImeiData(String str) {
        AppsFlyerLib.getInstance().setImeiData(str);
    }

    public static void setAndroidIdData(String str) {
        AppsFlyerLib.getInstance().setAndroidIdData(str);
    }

    public static void setCustomerUserId(String str) {
        AppsFlyerLib.getInstance().setCustomerUserId(str);
    }

    public static void waitForCustomerUserId(boolean z) {
        AppsFlyerLib.getInstance().waitForCustomerUserId(true);
    }

    public static void setCustomerIdAndTrack(String str) {
        AppsFlyerLib.getInstance().setCustomerIdAndLogSession(str, UnityPlayer.currentActivity);
    }

    public static String getOutOfStore() {
        return AppsFlyerLib.getInstance().getOutOfStore(UnityPlayer.currentActivity);
    }

    public static void setOutOfStore(String str) {
        AppsFlyerLib.getInstance().setOutOfStore(str);
    }

    public static void setAppInviteOneLinkID(String str) {
        AppsFlyerLib.getInstance().setAppInviteOneLink(str);
    }

    public static void setAdditionalData(HashMap<String, Object> map) {
        AppsFlyerLib.getInstance().setAdditionalData(map);
    }

    public static void setUserEmails(String... strArr) {
        AppsFlyerLib.getInstance().setUserEmails(strArr);
    }

    public static void setUserEmails(AppsFlyerProperties.EmailsCryptType emailsCryptType, String... strArr) {
        AppsFlyerLib.getInstance().setUserEmails(emailsCryptType, strArr);
    }

    public static void setCollectAndroidID(boolean z) {
        AppsFlyerLib.getInstance().setCollectAndroidID(z);
    }

    public static void setCollectIMEI(boolean z) {
        AppsFlyerLib.getInstance().setCollectIMEI(z);
    }

    public static void setResolveDeepLinkURLs(String... strArr) {
        AppsFlyerLib.getInstance().setResolveDeepLinkURLs(strArr);
    }

    public static void setOneLinkCustomDomain(String... strArr) {
        AppsFlyerLib.getInstance().setOneLinkCustomDomain(strArr);
    }

    public static void setIsUpdate(boolean z) {
        AppsFlyerLib.getInstance().setIsUpdate(z);
    }

    public static void setCurrencyCode(String str) {
        AppsFlyerLib.getInstance().setCurrencyCode(str);
    }

    public static void trackLocation(double d, double d2) {
        AppsFlyerLib.getInstance().logLocation(UnityPlayer.currentActivity, d, d2);
    }

    public static void trackEvent(String str, HashMap<String, Object> map, final boolean z, final String str2) {
        AppsFlyerLib.getInstance().logEvent(UnityPlayer.currentActivity, str, map, new AppsFlyerRequestListener() { // from class: com.appsflyer.unity.AppsFlyerAndroidWrapper.2
            @Override // com.appsflyer.attribution.AppsFlyerRequestListener
            public void onSuccess() {
                if (!z || str2 == null) {
                    return;
                }
                HashMap map2 = new HashMap();
                map2.put("statusCode", 200);
                UnityPlayer.UnitySendMessage(str2, AppsFlyerAndroidWrapper.IN_APP_RESPONSE_CALLBACK, new JSONObject(map2).toString());
            }

            @Override // com.appsflyer.attribution.AppsFlyerRequestListener
            public void onError(int i, @NonNull String str3) {
                if (!z || str2 == null) {
                    return;
                }
                HashMap map2 = new HashMap();
                map2.put("statusCode", Integer.valueOf(i));
                map2.put("errorDescription", str3);
                UnityPlayer.UnitySendMessage(str2, AppsFlyerAndroidWrapper.IN_APP_RESPONSE_CALLBACK, new JSONObject(map2).toString());
            }
        });
    }

    public static void trackEvent(String str, HashMap<String, Object> map) {
        trackEvent(str, map, false, null);
    }

    public static void setDeviceTrackingDisabled(boolean z) {
        AppsFlyerLib.getInstance().anonymizeUser(z);
    }

    public static void enableFacebookDeferredApplinks(boolean z) {
        AppsFlyerLib.getInstance().enableFacebookDeferredApplinks(z);
    }

    public static void setPreinstallAttribution(String str, String str2, String str3) {
        AppsFlyerLib.getInstance().setPreinstallAttribution(str, str2, str3);
    }

    public static boolean isPreInstalledApp() {
        return AppsFlyerLib.getInstance().isPreInstalledApp(UnityPlayer.currentActivity);
    }

    public static String getAttributionId() {
        return AppsFlyerLib.getInstance().getAttributionId(UnityPlayer.currentActivity);
    }

    public static String getAppsFlyerId() {
        return AppsFlyerLib.getInstance().getAppsFlyerUID(UnityPlayer.currentActivity);
    }

    public static void validateAndTrackInAppPurchase(String str, String str2, String str3, String str4, String str5, HashMap<String, String> map, String str6) {
        AppsFlyerLib.getInstance().validateAndLogInAppPurchase(UnityPlayer.currentActivity, str, str2, str3, str4, str5, map);
        if (str6 != null) {
            initInAppPurchaseValidatorListener(str6);
        }
    }

    public static boolean isTrackingStopped() {
        return AppsFlyerLib.getInstance().isStopped();
    }

    public static void setMinTimeBetweenSessions(int i) {
        AppsFlyerLib.getInstance().setMinTimeBetweenSessions(i);
    }

    public static void setLogLevel(AFLogger.LogLevel logLevel) {
        AppsFlyerLib.getInstance().setLogLevel(logLevel);
    }

    public static void setHost(String str, String str2) {
        AppsFlyerLib.getInstance().setHost(str, str2);
    }

    public static String getHostName() {
        return AppsFlyerLib.getInstance().getHostName();
    }

    public static String getHostPrefix() {
        return AppsFlyerLib.getInstance().getHostPrefix();
    }

    public static void setCollectOaid(boolean z) {
        AppsFlyerLib.getInstance().setCollectOaid(z);
    }

    public static void setSharingFilterForAllPartners() {
        AppsFlyerLib.getInstance().setSharingFilterForAllPartners();
    }

    public static void setSharingFilter(String... strArr) {
        AppsFlyerLib.getInstance().setSharingFilter(strArr);
    }

    public static void getConversionData(String str) {
        if (conversionListener == null) {
            conversionListener = getConversionListener(str);
        }
        AppsFlyerLib.getInstance().registerConversionListener(UnityPlayer.currentActivity, conversionListener);
    }

    private static AppsFlyerConversionListener getConversionListener(final String str) {
        return new AppsFlyerConversionListener() { // from class: com.appsflyer.unity.AppsFlyerAndroidWrapper.3
            @Override // com.appsflyer.AppsFlyerConversionListener
            public void onConversionDataSuccess(Map<String, Object> map) {
                if (str != null) {
                    UnityPlayer.UnitySendMessage(str, AppsFlyerAndroidWrapper.GCD_CALLBACK, new JSONObject(map).toString());
                }
            }

            @Override // com.appsflyer.AppsFlyerConversionListener
            public void onConversionDataFail(String str2) {
                String str3 = str;
                if (str3 != null) {
                    UnityPlayer.UnitySendMessage(str3, AppsFlyerAndroidWrapper.GCD_ERROR_CALLBACK, str2);
                }
            }

            @Override // com.appsflyer.AppsFlyerConversionListener
            public void onAppOpenAttribution(Map<String, String> map) {
                if (str != null) {
                    UnityPlayer.UnitySendMessage(str, AppsFlyerAndroidWrapper.OAOA_CALLBACK, new JSONObject(map).toString());
                }
            }

            @Override // com.appsflyer.AppsFlyerConversionListener
            public void onAttributionFailure(String str2) {
                String str3 = str;
                if (str3 != null) {
                    UnityPlayer.UnitySendMessage(str3, AppsFlyerAndroidWrapper.OAOA_ERROR_CALLBACK, str2);
                }
            }
        };
    }

    public static void initInAppPurchaseValidatorListener(final String str) {
        AppsFlyerLib.getInstance().registerValidatorListener(UnityPlayer.currentActivity, new AppsFlyerInAppPurchaseValidatorListener() { // from class: com.appsflyer.unity.AppsFlyerAndroidWrapper.4
            @Override // com.appsflyer.AppsFlyerInAppPurchaseValidatorListener
            public void onValidateInApp() {
                String str2 = str;
                if (str2 != null) {
                    UnityPlayer.UnitySendMessage(str2, AppsFlyerAndroidWrapper.VALIDATE_CALLBACK, "Validate success");
                }
            }

            @Override // com.appsflyer.AppsFlyerInAppPurchaseValidatorListener
            public void onValidateInAppFailure(String str2) {
                String str3 = str;
                if (str3 != null) {
                    UnityPlayer.UnitySendMessage(str3, AppsFlyerAndroidWrapper.VALIDATE_ERROR_CALLBACK, str2);
                }
            }
        });
    }

    public static void handlePushNotifications() {
        AppsFlyerLib.getInstance().sendPushNotificationData(UnityPlayer.currentActivity);
    }

    public static void setPhoneNumber(String str) {
        AppsFlyerLib.getInstance().setPhoneNumber(str);
    }

    public static void attributeAndOpenStore(String str, String str2, Map<String, String> map) {
        CrossPromotionHelper.logAndOpenStore(UnityPlayer.currentActivity, str, str2, map);
    }

    public static void recordCrossPromoteImpression(String str, String str2, Map<String, String> map) {
        CrossPromotionHelper.logCrossPromoteImpression(UnityPlayer.currentActivity, str, str2, map);
    }

    public static void createOneLinkInviteListener(Map<String, String> map, final String str) {
        LinkGenerator linkGeneratorGenerateInviteUrl = ShareInviteHelper.generateInviteUrl(UnityPlayer.currentActivity);
        linkGeneratorGenerateInviteUrl.setChannel(map.get("channel"));
        linkGeneratorGenerateInviteUrl.setCampaign(map.get(FirebaseAnalytics.Param.CAMPAIGN));
        linkGeneratorGenerateInviteUrl.setReferrerName(map.get("referrerName"));
        linkGeneratorGenerateInviteUrl.setReferrerImageURL(map.get("referrerImageUrl"));
        linkGeneratorGenerateInviteUrl.setReferrerCustomerId(map.get("customerID"));
        linkGeneratorGenerateInviteUrl.setBaseDeeplink(map.get("baseDeepLink"));
        linkGeneratorGenerateInviteUrl.setBrandDomain(map.get("brandDomain"));
        map.remove("channel");
        map.remove(FirebaseAnalytics.Param.CAMPAIGN);
        map.remove("referrerName");
        map.remove("referrerImageUrl");
        map.remove("customerID");
        map.remove("baseDeepLink");
        map.remove("brandDomain");
        linkGeneratorGenerateInviteUrl.addParameters(map);
        linkGeneratorGenerateInviteUrl.generateLink(UnityPlayer.currentActivity, new CreateOneLinkHttpTask.ResponseListener() { // from class: com.appsflyer.unity.AppsFlyerAndroidWrapper.5
            @Override // com.appsflyer.CreateOneLinkHttpTask.ResponseListener
            public void onResponse(String str2) {
                String str3 = str;
                if (str3 != null) {
                    UnityPlayer.UnitySendMessage(str3, AppsFlyerAndroidWrapper.GENERATE_LINK_CALLBACK, str2);
                }
            }

            @Override // com.appsflyer.CreateOneLinkHttpTask.ResponseListener
            public void onResponseError(String str2) {
                String str3 = str;
                if (str3 != null) {
                    UnityPlayer.UnitySendMessage(str3, AppsFlyerAndroidWrapper.GENERATE_LINK_ERROR_CALLBACK, str2);
                }
            }
        });
    }

    public static void subscribeForDeepLink(final String str) {
        AppsFlyerLib.getInstance().subscribeForDeepLink(new DeepLinkListener() { // from class: com.appsflyer.unity.AppsFlyerAndroidWrapper.6
            @Override // com.appsflyer.deeplink.DeepLinkListener
            public void onDeepLinking(@NonNull DeepLinkResult deepLinkResult) {
                String str2 = str;
                if (str2 != null) {
                    UnityPlayer.UnitySendMessage(str2, AppsFlyerAndroidWrapper.ON_DEEPLINKING, deepLinkResult.toString());
                }
            }
        });
    }
}
