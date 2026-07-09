package com.appsflyer.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class v extends ContentFetcher<String> {
    public v(Context context) {
        super(context, "com.facebook.katana.provider.AttributionIdProvider", "e3f9e1e0cf99d0e56a055ba65e241b3399f7cea524326b0cdd6ec1327ed0fdc1", 500L);
    }

    @Nullable
    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    public final String m221() {
        start();
        return (String) super.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.appsflyer.internal.ContentFetcher
    /* JADX INFO: renamed from: ǃ, reason: contains not printable characters and merged with bridge method [inline-methods] */
    public String query() throws Throwable {
        Cursor cursor = null;
        try {
            ContentResolver contentResolver = this.context.getContentResolver();
            StringBuilder sb = new StringBuilder("content://");
            sb.append(this.authority);
            Cursor cursorQuery = contentResolver.query(Uri.parse(sb.toString()), new String[]{"aid"}, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        String string = cursorQuery.getString(cursorQuery.getColumnIndex("aid"));
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = cursorQuery;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // com.appsflyer.internal.ContentFetcher
    @Nullable
    public final /* synthetic */ String get() {
        start();
        return (String) super.get();
    }
}
