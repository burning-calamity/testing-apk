package com.onevcat.uniwebview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.webkit.WebChromeClient;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class UniWebViewFileChooserActivity extends Activity {
    private static final int FILECHOOSER_RESULTCODE = 19238467;
    public static final String INTENT_CHROME_CLIENT_NAME = "chrome_client";
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 19238468;

    private UniWebViewChromeClient getChromeClient() {
        String stringExtra = getIntent().getStringExtra(INTENT_CHROME_CLIENT_NAME);
        if (stringExtra == null) {
            Logger.getInstance().critical("The intent does not contain a extra name for chrome client. It should not happen.");
            return null;
        }
        Logger.getInstance().verbose("Getting chrome client with web view name: " + stringExtra);
        UniWebViewDialog uniWebViewDialog = UniWebViewManager.getInstance().getUniWebViewDialog(stringExtra);
        if (uniWebViewDialog == null) {
            Logger.getInstance().critical("Cannot get a correct chrome client. Error.");
            return null;
        }
        return uniWebViewDialog.getChromeClient();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Logger.getInstance().verbose("UniWebViewFileChooserActivity onCreate. Bound client: " + getChromeClient());
        if (Build.VERSION.SDK_INT >= 23 && checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        } else {
            startFileChooserActivity();
        }
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (iArr[0] == 0) {
                startFileChooserActivity();
                return;
            }
            UniWebViewChromeClient chromeClient = getChromeClient();
            if (chromeClient == null) {
                Logger.getInstance().critical("Unexpected onRequestPermissionsResult since the chrome client has been already reset to null.");
            } else {
                chromeClient.receivedFileValue(null, false);
                finish();
            }
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        Logger.getInstance().verbose("File Chooser activity result: " + i2);
        if (i == FILECHOOSER_RESULTCODE) {
            UniWebViewChromeClient chromeClient = getChromeClient();
            if (chromeClient == null) {
                Logger.getInstance().critical("Unexpected onActivityResult since the chrome client has been already reset to null.");
            } else if (i2 == -1) {
                Logger.getInstance().info("File chooser got a file. : " + intent);
                chromeClient.receivedFileValue(intent, true);
            } else {
                Logger.getInstance().critical("File chooser failed to get a file. Result code: " + i2);
                chromeClient.receivedFileValue(null, false);
            }
        }
        super.onActivityResult(i, i2, intent);
        finish();
    }

    private void startFileChooserActivity() {
        Parcelable[] parcelableArr;
        File fileCreateImageFile;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        UniWebViewChromeClient chromeClient = getChromeClient();
        if (chromeClient == null) {
            Logger.getInstance().critical("Unexpected startFileChooserActivity since the chrome client has been already reset to null.");
            Logger.getInstance().critical("There is not related chrome client with this file chooser. It might be an Android system issue and the file choosing process is terminated.");
            return;
        }
        if (intent.resolveActivity(getPackageManager()) != null) {
            Logger.getInstance().verbose("Found an activity for taking photo. Try to get image.");
            try {
                fileCreateImageFile = chromeClient.createImageFile();
                try {
                    Logger.getInstance().verbose("photoFile: " + fileCreateImageFile.getAbsolutePath());
                    intent.putExtra("PhotoPath", chromeClient.getCameraPhotoPath());
                } catch (IOException e) {
                    e = e;
                    Logger.getInstance().critical("Error while creating image file. Exception: " + e);
                }
            } catch (IOException e2) {
                e = e2;
                fileCreateImageFile = null;
            }
            if (fileCreateImageFile != null) {
                chromeClient.setCameraPhotoPath("file:" + fileCreateImageFile.getAbsolutePath());
                intent.putExtra("output", Uri.fromFile(fileCreateImageFile));
            } else {
                intent = null;
            }
        }
        Intent intent2 = new Intent("android.intent.action.OPEN_DOCUMENT");
        intent2.addCategory("android.intent.category.OPENABLE");
        WebChromeClient.FileChooserParams fileChooserParams = chromeClient.getFileChooserParams();
        intent2.setType("*/*");
        if (fileChooserParams != null && fileChooserParams.getAcceptTypes() != null && fileChooserParams.getAcceptTypes().length >= 1 && !fileChooserParams.getAcceptTypes()[0].equals("")) {
            intent2.putExtra("android.intent.extra.MIME_TYPES", fileChooserParams.getAcceptTypes());
        }
        intent2.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        if (intent != null) {
            parcelableArr = new Intent[]{intent};
        } else {
            parcelableArr = new Intent[0];
        }
        Intent intent3 = new Intent("android.intent.action.CHOOSER");
        intent3.putExtra("android.intent.extra.TITLE", getResources().getString(R.string.CHOOSE_IMAGE));
        intent3.putExtra("android.intent.extra.INTENT", intent2);
        intent3.putExtra("android.intent.extra.INITIAL_INTENTS", parcelableArr);
        startActivityForResult(intent3, FILECHOOSER_RESULTCODE);
    }
}
