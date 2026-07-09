package com.appsflyer.internal;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.appsflyer.AndroidUtils;
import com.appsflyer.ServerParameters;
import com.google.firebase.analytics.FirebaseAnalytics;

/* JADX INFO: loaded from: classes.dex */
public final class q {

    public static final class b {

        /* JADX INFO: renamed from: ı, reason: contains not printable characters */
        public static final q f330 = new q();
    }

    q() {
    }

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    private static boolean m205(@NonNull Context context, @NonNull String[] strArr) {
        for (String str : strArr) {
            if (AndroidUtils.isPermissionAvailable(context, str)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    public static Location m206(@NonNull Context context) {
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService(FirebaseAnalytics.Param.LOCATION);
            Location lastKnownLocation = m205(context, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}) ? locationManager.getLastKnownLocation(ServerParameters.NETWORK) : null;
            Location lastKnownLocation2 = m205(context, new String[]{"android.permission.ACCESS_FINE_LOCATION"}) ? locationManager.getLastKnownLocation("gps") : null;
            if (lastKnownLocation2 == null && lastKnownLocation == null) {
                lastKnownLocation2 = null;
            } else {
                if (lastKnownLocation2 != null || lastKnownLocation == null) {
                    if (lastKnownLocation != null || lastKnownLocation2 == null) {
                        if (60000 < lastKnownLocation.getTime() - lastKnownLocation2.getTime()) {
                        }
                    }
                }
                lastKnownLocation2 = lastKnownLocation;
            }
            if (lastKnownLocation2 != null) {
                return lastKnownLocation2;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
