package com.appsflyer;

import android.util.Base64;
import androidx.annotation.Nullable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class AFFacebookDeferredDeeplink {

    /* JADX INFO: renamed from: ı, reason: contains not printable characters */
    private byte[] f27;

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    public String f28;

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    String f29;

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    String f30;

    public interface AppLinkFetchEvents {
        void onAppLinkFetchFailed(String str);

        void onAppLinkFetchFinished(@Nullable String str, @Nullable String str2, @Nullable String str3);
    }

    AFFacebookDeferredDeeplink() {
    }

    AFFacebookDeferredDeeplink(String str, byte[] bArr, String str2) {
        this.f30 = str;
        this.f27 = bArr;
        this.f29 = str2;
    }

    public AFFacebookDeferredDeeplink(char[] cArr) {
        Scanner scanner = new Scanner(new String(cArr));
        int i = 0;
        int i2 = 0;
        while (scanner.hasNextLine()) {
            String strNextLine = scanner.nextLine();
            if (strNextLine.startsWith("url=")) {
                this.f30 = strNextLine.substring(4).trim();
            } else if (strNextLine.startsWith("version=")) {
                this.f29 = strNextLine.substring(8).trim();
                Matcher matcher = Pattern.compile("^(0|[1-9]\\d*)\\.(0|[1-9]\\d*)\\.(0|[1-9]\\d*)(?:-((?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\\.(?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?(?:\\+([0-9a-zA-Z-]+(?:\\.[0-9a-zA-Z-]+)*))?$").matcher(this.f29);
                if (matcher.matches()) {
                    i = Integer.parseInt(matcher.group(1));
                    i2 = Integer.parseInt(matcher.group(2));
                }
            } else if (strNextLine.startsWith("data=")) {
                String strTrim = strNextLine.substring(5).trim();
                this.f27 = (i > 4 || i2 >= 11) ? Base64.decode(strTrim, 2) : strTrim.getBytes();
            }
        }
        scanner.close();
    }

    /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
    final byte[] m13() {
        return this.f27;
    }
}
