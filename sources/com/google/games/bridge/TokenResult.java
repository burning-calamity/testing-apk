package com.google.games.bridge;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes.dex */
public class TokenResult implements Result {
    private String authCode;
    private String email;
    private String idToken;
    private Status status;

    public TokenResult() {
    }

    TokenResult(String str, String str2, String str3, int i) {
        this.status = new Status(i);
        this.authCode = str;
        this.idToken = str3;
        this.email = str2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Status: ");
        sb.append(this.status);
        sb.append(" email: ");
        String str = this.email;
        if (str == null) {
            str = "<null>";
        }
        sb.append(str);
        sb.append(" id:");
        String str2 = this.idToken;
        if (str2 == null) {
            str2 = "<null>";
        }
        sb.append(str2);
        sb.append(" access: ");
        String str3 = this.authCode;
        if (str3 == null) {
            str3 = "<null>";
        }
        sb.append(str3);
        return sb.toString();
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.status;
    }

    public int getStatusCode() {
        return this.status.getStatusCode();
    }

    public String getAuthCode() {
        String str = this.authCode;
        return str == null ? "" : str;
    }

    public String getIdToken() {
        String str = this.idToken;
        return str == null ? "" : str;
    }

    public String getEmail() {
        String str = this.email;
        return str == null ? "" : str;
    }

    public void setStatus(int i) {
        this.status = new Status(i);
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public void setAuthCode(String str) {
        this.authCode = str;
    }

    public void setIdToken(String str) {
        this.idToken = str;
    }
}
