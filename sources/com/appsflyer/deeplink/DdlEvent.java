package com.appsflyer.deeplink;

import android.content.Context;
import androidx.annotation.Nullable;
import com.appsflyer.AFLogger;
import com.appsflyer.AdvertisingIdObject;
import com.appsflyer.AppsFlyerLibCore;
import com.appsflyer.internal.model.event.BackgroundEvent;
import com.appsflyer.internal.referrer.Payload;
import com.appsflyer.internal.referrer.Referrer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class DdlEvent extends BackgroundEvent {
    public static long LISTENER_TIMEOUT = TimeUnit.SECONDS.toMillis(3);
    public static String URL = "https://%sdlsdk.%s/v1.0/android/";

    /* JADX INFO: renamed from: ȷ, reason: contains not printable characters */
    private int f163;

    /* JADX INFO: renamed from: ɨ, reason: contains not printable characters */
    private final List<Referrer> f164;

    /* JADX INFO: renamed from: ɪ, reason: contains not printable characters */
    private int f165;

    /* JADX INFO: renamed from: ɾ, reason: contains not printable characters */
    private final CountDownLatch f166;

    /* JADX INFO: renamed from: ɿ, reason: contains not printable characters */
    private int f167;

    /* JADX INFO: renamed from: ӏ, reason: contains not printable characters */
    private boolean f168;

    public DdlEvent(Context context) {
        super(null, Boolean.FALSE, Boolean.TRUE, null, context);
        this.f164 = new ArrayList();
        this.f166 = new CountDownLatch(1);
    }

    public static void setUrl(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().equals("dlsdk")) {
                URL = entry.getValue();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0091  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void start() {
        /*
            r7 = this;
            java.lang.String r0 = "ddl_sent"
            java.lang.String r1 = "[DDL] start"
            com.appsflyer.AFLogger.afDebugLog(r1)
            java.util.concurrent.FutureTask r1 = new java.util.concurrent.FutureTask
            com.appsflyer.deeplink.DdlEvent$2 r2 = new com.appsflyer.deeplink.DdlEvent$2
            r2.<init>()
            r1.<init>(r2)
            java.lang.Thread r2 = new java.lang.Thread
            r2.<init>(r1)
            r2.start()
            r2 = 0
            r3 = 1
            long r4 = com.appsflyer.deeplink.DdlEvent.LISTENER_TIMEOUT     // Catch: java.util.concurrent.TimeoutException -> L3c java.lang.InterruptedException -> L7c java.util.concurrent.ExecutionException -> L7e
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.util.concurrent.TimeoutException -> L3c java.lang.InterruptedException -> L7c java.util.concurrent.ExecutionException -> L7e
            java.lang.Object r1 = r1.get(r4, r6)     // Catch: java.util.concurrent.TimeoutException -> L3c java.lang.InterruptedException -> L7c java.util.concurrent.ExecutionException -> L7e
            com.appsflyer.deeplink.DeepLinkResult r1 = (com.appsflyer.deeplink.DeepLinkResult) r1     // Catch: java.util.concurrent.TimeoutException -> L3c java.lang.InterruptedException -> L7c java.util.concurrent.ExecutionException -> L7e
            android.content.Context r4 = r7.context()     // Catch: java.util.concurrent.TimeoutException -> L3c java.lang.InterruptedException -> L7c java.util.concurrent.ExecutionException -> L7e
            android.content.SharedPreferences r4 = com.appsflyer.AppsFlyerLibCore.getSharedPreferences(r4)     // Catch: java.util.concurrent.TimeoutException -> L3c java.lang.InterruptedException -> L7c java.util.concurrent.ExecutionException -> L7e
            android.content.SharedPreferences$Editor r4 = r4.edit()     // Catch: java.util.concurrent.TimeoutException -> L3c java.lang.InterruptedException -> L7c java.util.concurrent.ExecutionException -> L7e
            android.content.SharedPreferences$Editor r4 = r4.putBoolean(r0, r3)     // Catch: java.util.concurrent.TimeoutException -> L3c java.lang.InterruptedException -> L7c java.util.concurrent.ExecutionException -> L7e
            r4.apply()     // Catch: java.util.concurrent.TimeoutException -> L3c java.lang.InterruptedException -> L7c java.util.concurrent.ExecutionException -> L7e
            com.appsflyer.deeplink.DeepLinkCallbacks.m139(r1)     // Catch: java.util.concurrent.TimeoutException -> L3c java.lang.InterruptedException -> L7c java.util.concurrent.ExecutionException -> L7e
            return
        L3c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r4 = "[DDL] Timeout, didn't manage to find deferred deep link after "
            r1.<init>(r4)
            int r4 = r7.f165
            r1.append(r4)
            java.lang.String r4 = " attempt(s) within "
            r1.append(r4)
            long r4 = com.appsflyer.deeplink.DdlEvent.LISTENER_TIMEOUT
            r1.append(r4)
            java.lang.String r4 = " milliseconds"
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            com.appsflyer.AFLogger.afDebugLog(r1)
            com.appsflyer.deeplink.DeepLinkResult r1 = new com.appsflyer.deeplink.DeepLinkResult
            com.appsflyer.deeplink.DeepLinkResult$Error r4 = com.appsflyer.deeplink.DeepLinkResult.Error.TIMEOUT
            r1.<init>(r2, r4)
            android.content.Context r2 = r7.context()
            android.content.SharedPreferences r2 = com.appsflyer.AppsFlyerLibCore.getSharedPreferences(r2)
            android.content.SharedPreferences$Editor r2 = r2.edit()
            android.content.SharedPreferences$Editor r0 = r2.putBoolean(r0, r3)
            r0.apply()
            com.appsflyer.deeplink.DeepLinkCallbacks.m139(r1)
            return
        L7c:
            r1 = move-exception
            goto L7f
        L7e:
            r1 = move-exception
        L7f:
            java.lang.String r4 = "[DDL] Error occurred"
            com.appsflyer.AFLogger.afErrorLog(r4, r1, r3)
            com.appsflyer.deeplink.DeepLinkResult r4 = new com.appsflyer.deeplink.DeepLinkResult
            java.lang.Throwable r1 = r1.getCause()
            boolean r1 = r1 instanceof java.io.IOException
            if (r1 == 0) goto L91
            com.appsflyer.deeplink.DeepLinkResult$Error r1 = com.appsflyer.deeplink.DeepLinkResult.Error.NETWORK
            goto L93
        L91:
            com.appsflyer.deeplink.DeepLinkResult$Error r1 = com.appsflyer.deeplink.DeepLinkResult.Error.UNEXPECTED
        L93:
            r4.<init>(r2, r1)
            android.content.Context r1 = r7.context()
            android.content.SharedPreferences r1 = com.appsflyer.AppsFlyerLibCore.getSharedPreferences(r1)
            android.content.SharedPreferences$Editor r1 = r1.edit()
            android.content.SharedPreferences$Editor r0 = r1.putBoolean(r0, r3)
            r0.apply()
            com.appsflyer.deeplink.DeepLinkCallbacks.m139(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.deeplink.DdlEvent.start():void");
    }

    @Nullable
    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    private static Map<String, Object> m127(final AdvertisingIdObject advertisingIdObject) {
        Boolean boolIsLimitAdTracking;
        boolean z = false;
        if (advertisingIdObject != null && advertisingIdObject.getAdvertisingId() != null && ((boolIsLimitAdTracking = advertisingIdObject.isLimitAdTracking()) == null || !boolIsLimitAdTracking.booleanValue())) {
            z = true;
        }
        if (z) {
            return new HashMap<String, Object>() { // from class: com.appsflyer.deeplink.DdlEvent.4
                {
                    put("type", "unhashed");
                    put("value", advertisingIdObject.getAdvertisingId());
                }
            };
        }
        return null;
    }

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    private boolean m132() {
        List list = (List) this.params.get(Payload.RFRS);
        return (list != null ? list.size() : 0) < this.f167 && !this.params.containsKey(Payload.RFRS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0113  */
    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m131(android.content.Context r8) throws java.security.NoSuchAlgorithmException, java.security.InvalidKeyException {
        /*
            Method dump skipped, instruction units count: 418
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.deeplink.DdlEvent.m131(android.content.Context):void");
    }

    /* JADX INFO: renamed from: com.appsflyer.deeplink.DdlEvent$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
        static final /* synthetic */ int[] f169 = new int[Referrer.State.values().length];

        static {
            try {
                f169[Referrer.State.FINISHED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f169[Referrer.State.STARTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    public void m129(Referrer referrer) {
        if (m133(referrer)) {
            this.f164.add(referrer);
            this.f166.countDown();
            StringBuilder sb = new StringBuilder("[DDL] Added non-organic ");
            sb.append(referrer.getClass().getSimpleName());
            AFLogger.afDebugLog(sb.toString());
            return;
        }
        int i = this.f163 + 1;
        this.f163 = i;
        if (i == this.f167) {
            this.f166.countDown();
        }
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    private static boolean m133(Referrer referrer) {
        Long l = (Long) referrer.map.get(Payload.CLICK_TS);
        return l != null && System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(l.longValue()) < TimeUnit.DAYS.toMillis(1L);
    }

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    static /* synthetic */ void m128(DdlEvent ddlEvent) {
        ArrayList<Referrer> arrayList = new ArrayList();
        AppsFlyerLibCore appsFlyerLibCore = AppsFlyerLibCore.getInstance();
        arrayList.add(appsFlyerLibCore.googleReferrer);
        arrayList.add(appsFlyerLibCore.huaweiReferrer);
        ArrayList<Referrer> arrayList2 = new ArrayList();
        for (Referrer referrer : arrayList) {
            if (referrer != null && referrer.getState() != Referrer.State.NOT_STARTED) {
                arrayList2.add(referrer);
            }
        }
        ddlEvent.f167 = arrayList2.size();
        for (final Referrer referrer2 : arrayList2) {
            int i = AnonymousClass1.f169[referrer2.getState().ordinal()];
            if (i == 1) {
                StringBuilder sb = new StringBuilder("[DDL] ");
                sb.append(referrer2.map.get("source"));
                sb.append(" referrer collected earlier");
                AFLogger.afDebugLog(sb.toString());
                ddlEvent.m129(referrer2);
            } else if (i == 2) {
                referrer2.addObserver(new Observer() { // from class: com.appsflyer.deeplink.DdlEvent.3
                    @Override // java.util.Observer
                    public final void update(Observable observable, Object obj) {
                        StringBuilder sb2 = new StringBuilder("[DDL] ");
                        sb2.append(referrer2.map.get("source"));
                        sb2.append(" referrer collected via observer");
                        AFLogger.afDebugLog(sb2.toString());
                        DdlEvent.this.m129((Referrer) observable);
                    }
                });
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0090, code lost:
    
        return new com.appsflyer.deeplink.DeepLinkResult(null, null);
     */
    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ com.appsflyer.deeplink.DeepLinkResult m130(com.appsflyer.deeplink.DdlEvent r7, android.content.Context r8) throws org.json.JSONException, java.lang.InterruptedException, java.security.NoSuchAlgorithmException, java.security.InvalidKeyException, java.io.IOException {
        /*
        L0:
            long r0 = java.lang.System.currentTimeMillis()
            com.appsflyer.BackgroundHttpTask r2 = new com.appsflyer.BackgroundHttpTask
            r2.<init>(r7)
            java.net.HttpURLConnection r2 = r2.doInBackground()
            int r3 = r7.f165
            r4 = 1
            if (r3 != r4) goto L21
            com.appsflyer.internal.EventDataCollector r3 = new com.appsflyer.internal.EventDataCollector
            r3.<init>(r8)
            long r5 = java.lang.System.currentTimeMillis()
            long r5 = r5 - r0
            java.lang.String r0 = "ddl_net_latency"
            r3.set(r0, r5)
        L21:
            int r0 = r2.getResponseCode()
            r1 = 200(0xc8, float:2.8E-43)
            r3 = 0
            if (r0 != r1) goto L91
            com.appsflyer.AppsFlyerLibCore r0 = com.appsflyer.AppsFlyerLibCore.getInstance()
            java.lang.String r0 = r0.readServerResponse(r2)
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>(r0)
            java.lang.String r0 = "is_second_ping"
            boolean r0 = r1.optBoolean(r0, r4)
            r7.f168 = r0
            java.lang.String r0 = "found"
            boolean r0 = r1.optBoolean(r0)
            if (r0 != 0) goto L49
            r0 = r3
            goto L5a
        L49:
            java.lang.String r0 = "click_event"
            org.json.JSONObject r0 = r1.optJSONObject(r0)
            com.appsflyer.deeplink.DeepLink r0 = com.appsflyer.deeplink.DeepLink.m137(r0)
            org.json.JSONObject r1 = r0.f174
            java.lang.String r2 = "is_deferred"
            r1.put(r2, r4)
        L5a:
            if (r0 == 0) goto L62
            com.appsflyer.deeplink.DeepLinkResult r7 = new com.appsflyer.deeplink.DeepLinkResult
            r7.<init>(r0, r3)
            return r7
        L62:
            int r0 = r7.f165
            if (r0 > r4) goto L8b
            boolean r0 = r7.m132()
            if (r0 == 0) goto L8b
            boolean r0 = r7.f168
            if (r0 == 0) goto L8b
            java.lang.String r0 = "[DDL] Waiting for referrers..."
            com.appsflyer.AFLogger.afDebugLog(r0)
            java.util.concurrent.CountDownLatch r0 = r7.f166
            r0.await()
            int r0 = r7.f163
            int r1 = r7.f167
            if (r0 != r1) goto L86
            com.appsflyer.deeplink.DeepLinkResult r7 = new com.appsflyer.deeplink.DeepLinkResult
            r7.<init>(r3, r3)
            return r7
        L86:
            r7.m131(r8)
            goto L0
        L8b:
            com.appsflyer.deeplink.DeepLinkResult r7 = new com.appsflyer.deeplink.DeepLinkResult
            r7.<init>(r3, r3)
            return r7
        L91:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "[DDL] Error occurred. Server response code = "
            r7.<init>(r8)
            int r8 = r2.getResponseCode()
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            com.appsflyer.AFLogger.afDebugLog(r7)
            com.appsflyer.deeplink.DeepLinkResult r7 = new com.appsflyer.deeplink.DeepLinkResult
            com.appsflyer.deeplink.DeepLinkResult$Error r8 = com.appsflyer.deeplink.DeepLinkResult.Error.HTTP_STATUS_CODE
            r7.<init>(r3, r8)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.deeplink.DdlEvent.m130(com.appsflyer.deeplink.DdlEvent, android.content.Context):com.appsflyer.deeplink.DeepLinkResult");
    }
}
