package com.appsflyer;

import java.security.MessageDigest;
import java.util.Formatter;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class HashUtils {
    public static String toSHA1(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
            return m124(messageDigest.digest());
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("Error turning ");
            sb.append(str.substring(0, 6));
            sb.append(".. to SHA1");
            AFLogger.afErrorLog(sb.toString(), e);
            return null;
        }
    }

    public static String toMD5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
            return m124(messageDigest.digest());
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("Error turning ");
            sb.append(str.substring(0, 6));
            sb.append(".. to MD5");
            AFLogger.afErrorLog(sb.toString(), e);
            return null;
        }
    }

    public static String toSha256(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes());
            return bytesToHex(messageDigest.digest());
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("Error turning ");
            sb.append(str.substring(0, 6));
            sb.append(".. to SHA-256");
            AFLogger.afErrorLog(sb.toString(), e);
            return null;
        }
    }

    public static String bytesToHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(Integer.toString((b & 255) + 256, 16).substring(1));
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
    private static String m124(byte[] bArr) {
        Formatter formatter = new Formatter();
        for (byte b : bArr) {
            formatter.format("%02x", Byte.valueOf(b));
        }
        String string = formatter.toString();
        formatter.close();
        return string;
    }

    public static String getOneLinkAuthorization(long j) {
        StringBuilder sb = new StringBuilder();
        sb.append(AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY));
        sb.append(j);
        return toSHA1(sb.toString());
    }

    public String getHashCode(Map<String, Object> map) {
        String str = (String) map.get(ServerParameters.AF_DEV_KEY);
        String str2 = (String) map.get(ServerParameters.TIMESTAMP);
        String str3 = (String) map.get(ServerParameters.AF_USER_ID);
        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(0, 7));
        sb.append(str3.substring(0, 7));
        sb.append(str2.substring(str2.length() - 7));
        return toSHA1(sb.toString());
    }

    public String getHashCodeV2(Map<String, Object> map) {
        String str = (String) map.get(ServerParameters.AF_DEV_KEY);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(map.get(ServerParameters.TIMESTAMP));
        String string = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(string);
        sb2.append(map.get(ServerParameters.AF_USER_ID));
        String string2 = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(string2);
        sb3.append(map.get(ServerParameters.INSTALL_DATE));
        String string3 = sb3.toString();
        StringBuilder sb4 = new StringBuilder();
        sb4.append(string3);
        sb4.append(map.get("counter"));
        String string4 = sb4.toString();
        StringBuilder sb5 = new StringBuilder();
        sb5.append(string4);
        sb5.append(map.get("iaecounter"));
        return toSHA1(toMD5(sb5.toString()));
    }
}
