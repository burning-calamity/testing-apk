package com.google.android.play.core.internal;

import android.content.res.AssetManager;
import android.support.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class au {
    private final com.google.android.play.core.splitcompat.a a;

    @Nullable
    private XmlPullParser b;

    public au(com.google.android.play.core.splitcompat.a aVar) {
        this.a = aVar;
    }

    public final void a(AssetManager assetManager, File file) throws IOException {
        this.b = assetManager.openXmlResourceParser(com.google.android.play.core.splitcompat.a.c(assetManager, file), "AndroidManifest.xml");
    }

    public final long b() throws XmlPullParserException, IOException {
        if (this.b == null) {
            throw new XmlPullParserException("Manifest file needs to be loaded before parsing.");
        }
        while (true) {
            int next = this.b.next();
            if (next != 2) {
                if (next == 1) {
                    break;
                }
            } else if (this.b.getName().equals("manifest")) {
                String attributeValue = this.b.getAttributeValue("http://schemas.android.com/apk/res/android", "versionCode");
                String attributeValue2 = this.b.getAttributeValue("http://schemas.android.com/apk/res/android", "versionCodeMajor");
                if (attributeValue == null) {
                    throw new XmlPullParserException("Manifest entry doesn't contain 'versionCode' attribute.");
                }
                try {
                    int i = Integer.parseInt(attributeValue);
                    if (attributeValue2 == null) {
                        return i;
                    }
                    try {
                        return (((long) Integer.parseInt(attributeValue2)) << 32) | (((long) i) & 4294967295L);
                    } catch (NumberFormatException e) {
                        throw new XmlPullParserException(String.format("Couldn't parse versionCodeMajor to int: %s", e.getMessage()));
                    }
                } catch (NumberFormatException e2) {
                    throw new XmlPullParserException(String.format("Couldn't parse versionCode to int: %s", e2.getMessage()));
                }
            }
        }
        throw new XmlPullParserException("Couldn't find manifest entry at top-level.");
    }
}
