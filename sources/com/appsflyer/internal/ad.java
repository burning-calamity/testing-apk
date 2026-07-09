package com.appsflyer.internal;

import com.appsflyer.AFLogger;

/* JADX INFO: loaded from: classes.dex */
public final class ad {

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    private d f217 = new d() { // from class: com.appsflyer.internal.ad.5
        @Override // com.appsflyer.internal.ad.d
        /* JADX INFO: renamed from: ı, reason: contains not printable characters */
        public final Class<?> mo181(String str) throws ClassNotFoundException {
            return Class.forName(str);
        }
    };

    interface d {
        /* JADX INFO: renamed from: ı */
        Class<?> mo181(String str) throws ClassNotFoundException;
    }

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    public final String m180() {
        for (a aVar : a.values()) {
            if (m179(aVar.f232)) {
                return aVar.f233;
            }
        }
        return a.DEFAULT.f233;
    }

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    private boolean m179(String str) {
        try {
            this.f217.mo181(str);
            StringBuilder sb = new StringBuilder("Class: ");
            sb.append(str);
            sb.append(" is found.");
            AFLogger.afRDLog(sb.toString());
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        } catch (Throwable th) {
            AFLogger.afErrorLog(th.getMessage(), th);
            return false;
        }
    }

    enum a {
        ADOBE_AIR("android_adobe_air", "com.appsflyer.adobeair.AppsFlyerExtension"),
        ADOBE_MOBILE_SDK("android_adobe_mobile", "com.appsflyer.adobeextension.AppsFlyerAdobeExtension"),
        COCOS2DX("android_cocos2dx", "org.cocos2dx.lib.Cocos2dxActivity"),
        CORDOVA("android_cordova", "com.appsflyer.cordova.plugin.AppsFlyerPlugin"),
        DEFAULT("android_native", "android_native"),
        FLUTTER("android_flutter", "com.appsflyer.appsflyersdk.AppsflyerSdkPlugin"),
        M_PARTICLE("android_mparticle", "com.mparticle.kits.AppsFlyerKit"),
        NATIVE_SCRIPT("android_native_script", "com.tns.NativeScriptActivity"),
        REACT_NATIVE("android_reactNative", "com.appsflyer.reactnative.RNAppsFlyerModule"),
        SEGMENT("android_segment", "com.segment.analytics.android.integrations.appsflyer.AppsflyerIntegration"),
        UNITY("android_unity", "com.appsflyer.unity.AppsFlyerAndroidWrapper"),
        UNREAL_ENGINE("android_unreal", "com.epicgames.ue4.GameActivity"),
        XAMARIN("android_xamarin", "mono.android.app.XamarinAndroidEnvironmentVariables");


        /* JADX INFO: renamed from: ȷ, reason: contains not printable characters */
        private String f232;

        /* JADX INFO: renamed from: ӏ, reason: contains not printable characters */
        private String f233;

        a(String str, String str2) {
            this.f233 = str;
            this.f232 = str2;
        }
    }
}
