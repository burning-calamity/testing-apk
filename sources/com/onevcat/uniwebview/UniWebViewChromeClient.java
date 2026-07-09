package com.onevcat.uniwebview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Message;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class UniWebViewChromeClient extends VideoEnabledWebChromeClient {
    private Activity activity;
    private AlertDialog alertDialog;
    private ValueCallback<Uri[]> callback;
    private String cameraPhotoPath;
    private UniWebViewDialog dialog;
    private WebChromeClient.FileChooserParams params;
    private UniWebView popUpWebView;

    AlertDialog getAlertDialog() {
        return this.alertDialog;
    }

    UniWebViewChromeClient(Activity activity, View view, ViewGroup viewGroup, View view2, UniWebView uniWebView, UniWebViewDialog uniWebViewDialog) {
        super(view, viewGroup, view2, uniWebView);
        this.activity = activity;
        this.dialog = uniWebViewDialog;
    }

    private UniWebViewChromeClient(Activity activity, UniWebViewDialog uniWebViewDialog) {
        this.activity = activity;
        this.dialog = uniWebViewDialog;
    }

    @Override // android.webkit.WebChromeClient
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        Logger.getInstance().info("UniWebViewChromeClient onShowFileChooser.");
        if (this.callback != null) {
            Logger.getInstance().critical("Trying to show another file chooser before previous one finished. Discard previous upload!");
            this.callback.onReceiveValue(null);
        }
        this.callback = valueCallback;
        this.params = fileChooserParams;
        Logger.getInstance().verbose("Start file chooser activity.");
        Intent intent = new Intent(this.activity, (Class<?>) UniWebViewFileChooserActivity.class);
        intent.putExtra(UniWebViewFileChooserActivity.INTENT_CHROME_CLIENT_NAME, this.dialog.getWebViewName());
        this.activity.startActivity(intent);
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public void onPermissionRequest(final PermissionRequest permissionRequest) {
        Logger.getInstance().info("UniWebViewChromeClient onPermissionRequest, url: " + permissionRequest.getOrigin().toString());
        this.activity.runOnUiThread(new Runnable() { // from class: com.onevcat.uniwebview.UniWebViewChromeClient.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    URL url = new URL(permissionRequest.getOrigin().toString());
                    if (UniWebViewChromeClient.this.dialog.getPermissionTrustDomains().contains(url.getHost())) {
                        Logger.getInstance().info("Permission domain '" + url.getHost() + "' contains in permission trusted domains. Granting...");
                        permissionRequest.grant(permissionRequest.getResources());
                    } else {
                        Logger.getInstance().critical("Permission domain '" + url.getHost() + "' is not allowed. Deny request.");
                        Logger.getInstance().critical("If you want to allow permission access from this domain, add it through `UniWebView.AddPermissionTrustDomain` first");
                        permissionRequest.deny();
                    }
                } catch (MalformedURLException e) {
                    Logger.getInstance().critical("onPermissionRequest failed due to malformed url exception. " + e.getMessage());
                    permissionRequest.deny();
                }
            }
        });
    }

    WebChromeClient.FileChooserParams getFileChooserParams() {
        return this.params;
    }

    private void showAlert() {
        if (this.dialog.getImmersiveMode()) {
            this.alertDialog.getWindow().setFlags(8, 8);
            this.alertDialog.show();
            this.alertDialog.getWindow().getDecorView().setSystemUiVisibility(this.dialog.getWindow().getDecorView().getSystemUiVisibility());
            this.alertDialog.getWindow().clearFlags(8);
            return;
        }
        this.alertDialog.show();
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
        this.alertDialog = new AlertDialog.Builder(this.dialog.getContext()).setTitle(str).setMessage(str2).setCancelable(false).setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.onevcat.uniwebview.UniWebViewChromeClient.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                jsResult.confirm();
                UniWebViewChromeClient.this.alertDialog = null;
            }
        }).create();
        showAlert();
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsConfirm(WebView webView, String str, String str2, final JsResult jsResult) {
        this.alertDialog = new AlertDialog.Builder(this.dialog.getContext()).setTitle(str).setMessage(str2).setIcon(android.R.drawable.ic_dialog_info).setCancelable(false).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() { // from class: com.onevcat.uniwebview.UniWebViewChromeClient.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                jsResult.confirm();
                UniWebViewChromeClient.this.alertDialog = null;
            }
        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() { // from class: com.onevcat.uniwebview.UniWebViewChromeClient.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                jsResult.cancel();
                UniWebViewChromeClient.this.alertDialog = null;
            }
        }).create();
        showAlert();
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, final JsPromptResult jsPromptResult) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.dialog.getContext());
        builder.setTitle(str).setMessage(str2).setIcon(android.R.drawable.ic_dialog_info).setCancelable(false);
        final EditText editText = new EditText(this.dialog.getContext());
        editText.setSingleLine();
        builder.setView(editText);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() { // from class: com.onevcat.uniwebview.UniWebViewChromeClient.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Editable text = editText.getText();
                String string = text != null ? text.toString() : "";
                dialogInterface.dismiss();
                jsPromptResult.confirm(string);
                UniWebViewChromeClient.this.alertDialog = null;
            }
        });
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() { // from class: com.onevcat.uniwebview.UniWebViewChromeClient.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                jsPromptResult.cancel();
                UniWebViewChromeClient.this.alertDialog = null;
            }
        });
        this.alertDialog = builder.create();
        showAlert();
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
        callback.invoke(str, true, false);
    }

    @Override // android.webkit.WebChromeClient
    @SuppressLint({"SetJavaScriptEnabled"})
    public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
        Logger.getInstance().info("onCreateWindow: " + webView + ", isUserGesture: " + z2);
        if (this.dialog.getWebView().getSettings().supportMultipleWindows() && z2) {
            UniWebView uniWebView = new UniWebView(this.activity);
            uniWebView.getSettings().setJavaScriptEnabled(true);
            uniWebView.setWebChromeClient(new UniWebViewChromeClient(this.activity, this.dialog));
            uniWebView.setWebViewClient(new WebViewClient());
            uniWebView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            uniWebView.getSettings().setUserAgentString(this.dialog.getWebView().getSettings().getUserAgentString());
            webView.addView(uniWebView);
            ((WebView.WebViewTransport) message.obj).setWebView(uniWebView);
            message.sendToTarget();
            setPopUpWebView(uniWebView);
            return true;
        }
        return super.onCreateWindow(webView, z, z2, message);
    }

    @Override // android.webkit.WebChromeClient
    public void onCloseWindow(WebView webView) {
        super.onCloseWindow(webView);
        this.dialog.getWebView().removeView(webView);
        setPopUpWebView(null);
    }

    void receivedFileValue(Intent intent, boolean z) {
        String str;
        Uri[] uriArr;
        Uri[] uriArr2;
        String str2;
        if (intent != null) {
            String dataString = intent.getDataString();
            ArrayList arrayList = new ArrayList();
            if (dataString != null) {
                arrayList.add(Uri.parse(dataString));
            } else {
                ClipData clipData = intent.getClipData();
                if (clipData != null) {
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        arrayList.add(clipData.getItemAt(i).getUri());
                    }
                }
            }
            if (arrayList.isEmpty()) {
                if (z && (str2 = this.cameraPhotoPath) != null) {
                    uriArr = new Uri[]{Uri.parse(str2)};
                    uriArr2 = uriArr;
                }
                uriArr2 = null;
            } else {
                ArrayList arrayList2 = new ArrayList();
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    Uri uri = (Uri) arrayList.get(i2);
                    String strUriToFilename = uriToFilename(uri);
                    if (strUriToFilename != null) {
                        arrayList2.add(Uri.fromFile(new File(strUriToFilename)));
                    } else {
                        try {
                            InputStream inputStreamOpenInputStream = this.activity.getContentResolver().openInputStream(uri);
                            File fileCreateTempFile = createTempFile(uri);
                            copyInputStreamToFile(inputStreamOpenInputStream, fileCreateTempFile);
                            arrayList2.add(Uri.fromFile(fileCreateTempFile));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Logger.getInstance().critical("Can not get correct path on disk storage.");
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            Logger.getInstance().critical("Can not get correct path on disk storage.");
                        }
                    }
                }
                uriArr2 = (Uri[]) arrayList2.toArray(new Uri[0]);
            }
        } else {
            if (z && (str = this.cameraPhotoPath) != null) {
                uriArr = new Uri[]{Uri.parse(str)};
                uriArr2 = uriArr;
            }
            uriArr2 = null;
        }
        ValueCallback<Uri[]> valueCallback = this.callback;
        if (valueCallback != null) {
            valueCallback.onReceiveValue(uriArr2);
        }
        this.callback = null;
        this.params = null;
    }

    private void copyInputStreamToFile(InputStream inputStream, File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStream.read(bArr);
                if (i > 0) {
                    fileOutputStream.write(bArr, 0, i);
                } else {
                    fileOutputStream.close();
                    inputStream.close();
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String uriToFilename(Uri uri) {
        return ProviderPathConverter.getPath(this.activity, uri);
    }

    File createImageFile() throws IOException {
        String str = "IMAGE_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date()) + "_";
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!externalStoragePublicDirectory.exists()) {
            externalStoragePublicDirectory.mkdir();
        }
        return File.createTempFile(str, ".png", externalStoragePublicDirectory);
    }

    File createTempFile(Uri uri) throws IOException {
        Cursor cursorQuery = this.activity.getContentResolver().query(uri, null, null, null, null, null);
        String string = (cursorQuery == null || !cursorQuery.moveToFirst()) ? "" : cursorQuery.getString(cursorQuery.getColumnIndex("_display_name"));
        cursorQuery.close();
        return File.createTempFile(string, null, null);
    }

    void setCameraPhotoPath(String str) {
        this.cameraPhotoPath = str;
    }

    String getCameraPhotoPath() {
        return this.cameraPhotoPath;
    }

    public UniWebView getPopUpWebView() {
        return this.popUpWebView;
    }

    public void setPopUpWebView(UniWebView uniWebView) {
        this.popUpWebView = uniWebView;
    }
}
