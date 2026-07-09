package net.himecut.usb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.google.firebase.MessagingUnityPlayerActivity;

/* JADX INFO: loaded from: classes.dex */
public class UsbActivity extends Activity {
    private static Activity wpk;

    /* JADX WARN: Type inference failed for: r5v4, types: [net.himecut.usb.UsbActivity$1] */
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Context applicationContext = getApplicationContext();
        SharedPreferences.Editor editorEdit = applicationContext.getSharedPreferences(applicationContext.getPackageName() + ".v2.playerprefs", 0).edit();
        editorEdit.putString("GachasController", getIntent().getData().toString());
        editorEdit.commit();
        wpk = this;
        new Handler() { // from class: net.himecut.usb.UsbActivity.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (UsbActivity.wpk != null) {
                    UsbActivity.wpk.startActivity(new Intent(UsbActivity.wpk.getApplication(), (Class<?>) MessagingUnityPlayerActivity.class));
                    UsbActivity.wpk.finish();
                    Activity unused = UsbActivity.wpk = null;
                }
            }
        }.sendEmptyMessageDelayed(0, 10L);
    }
}
