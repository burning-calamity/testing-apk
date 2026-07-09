package jp.co.altplus.y3native;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
final class Debug {
    static final String TAG = "y3native";

    Debug() {
    }

    public static void Log(String str) {
        Log.d(TAG, str);
    }

    public static void LogError(String str) {
        Log.e(TAG, str);
    }

    public static void LogWarning(String str) {
        Log.w(TAG, str);
    }
}
