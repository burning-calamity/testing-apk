package jp.co.altplus.y3native;

import android.app.Activity;
import android.content.Context;
import com.unity3d.player.UnityPlayer;

/* JADX INFO: loaded from: classes.dex */
final class PrivateUtil {
    PrivateUtil() {
    }

    static Activity getActivity() {
        return UnityPlayer.currentActivity;
    }

    static Context getContext() {
        if (UnityPlayer.currentActivity == null) {
            return null;
        }
        return UnityPlayer.currentActivity.getApplicationContext();
    }
}
