package com.appsflyer.share;

import android.content.Context;
import androidx.annotation.NonNull;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.ServerConfigHandler;
import com.appsflyer.ServerParameters;
import com.appsflyer.internal.aa;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class CrossPromotionHelper {

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    private static String f356 = "https://%simpression.%s";

    public static void logAndOpenStore(@NonNull Context context, String str, String str2) {
        logAndOpenStore(context, str, str2, null);
    }

    public static void logAndOpenStore(@NonNull Context context, String str, String str2, Map<String, String> map) {
        LinkGenerator linkGeneratorM227 = m227(context, str, str2, map, ServerConfigHandler.getUrl(Constants.f355));
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false)) {
            AFLogger.afInfoLog("CustomerUserId not set, track And Open Store is disabled", true);
            return;
        }
        if (map == null) {
            map = new HashMap<>();
        }
        map.put("af_campaign", str2);
        AppsFlyerLib.getInstance().logEvent(context, "af_cross_promotion", map);
        new Thread(new c(linkGeneratorM227.generateLink(), new aa(), context, AppsFlyerLib.getInstance().isStopped())).start();
    }

    public static void logCrossPromoteImpression(@NonNull Context context, String str, String str2) {
        logCrossPromoteImpression(context, str, str2, null);
    }

    public static void logCrossPromoteImpression(@NonNull Context context, String str, String str2, Map<String, String> map) {
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false)) {
            AFLogger.afInfoLog("CustomerUserId not set, Promote Impression is disabled", true);
        } else {
            new Thread(new c(m227(context, str, str2, map, ServerConfigHandler.getUrl(f356)).generateLink(), null, null, AppsFlyerLib.getInstance().isStopped())).start();
        }
    }

    @NonNull
    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    private static LinkGenerator m227(@NonNull Context context, String str, String str2, Map<String, String> map, String str3) {
        LinkGenerator linkGenerator = new LinkGenerator("af_cross_promotion");
        linkGenerator.f361 = str3;
        linkGenerator.f369 = str;
        linkGenerator.addParameter(Constants.URL_SITE_ID, context.getPackageName());
        if (str2 != null) {
            linkGenerator.setCampaign(str2);
        }
        if (map != null) {
            linkGenerator.addParameters(map);
        }
        String string = AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM);
        if (string != null) {
            linkGenerator.addParameter("advertising_id", string);
        }
        return linkGenerator;
    }

    public static void setUrl(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String value = entry.getValue();
            String key = entry.getKey();
            byte b = -1;
            int iHashCode = key.hashCode();
            if (iHashCode != 96801) {
                if (iHashCode == 120623625 && key.equals("impression")) {
                    b = 1;
                }
            } else if (key.equals("app")) {
                b = 0;
            }
            if (b == 0) {
                Constants.f355 = value;
            } else if (b == 1) {
                f356 = value;
            }
        }
    }

    static class c implements Runnable {

        /* JADX INFO: renamed from: ı, reason: contains not printable characters */
        private final String f357;

        /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
        private final boolean f358;

        /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
        private final aa f359;

        /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
        private final WeakReference<Context> f360;

        c(String str, aa aaVar, Context context, boolean z) {
            this.f357 = str;
            this.f359 = aaVar;
            this.f360 = new WeakReference<>(context);
            this.f358 = z;
        }

        /* JADX WARN: Removed duplicated region for block: B:38:0x00cb  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void run() throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 207
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.share.CrossPromotionHelper.c.run():void");
        }
    }
}
