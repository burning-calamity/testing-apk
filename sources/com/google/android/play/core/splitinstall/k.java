package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class k {
    private static m a;

    private k() {
    }

    public static synchronized m a(Context context) {
        if (a == null) {
            b bVar = new b(null);
            bVar.b(new x(com.google.android.play.core.splitcompat.p.c(context)));
            a = bVar.a();
        }
        return a;
    }

    @Nullable
    static h b(XmlPullParser xmlPullParser, g gVar) {
        String strC;
        while (xmlPullParser.next() != 1) {
            try {
                if (xmlPullParser.getEventType() == 2) {
                    if (xmlPullParser.getName().equals("splits")) {
                        while (xmlPullParser.next() != 3) {
                            if (xmlPullParser.getEventType() == 2) {
                                if (!xmlPullParser.getName().equals("module") || (strC = c(AppMeasurementSdk.ConditionalUserProperty.NAME, xmlPullParser)) == null) {
                                    d(xmlPullParser);
                                } else {
                                    while (xmlPullParser.next() != 3) {
                                        if (xmlPullParser.getEventType() == 2) {
                                            if (xmlPullParser.getName().equals("language")) {
                                                while (xmlPullParser.next() != 3) {
                                                    if (xmlPullParser.getEventType() == 2) {
                                                        if (xmlPullParser.getName().equals("entry")) {
                                                            String strC2 = c("key", xmlPullParser);
                                                            String strC3 = c("split", xmlPullParser);
                                                            d(xmlPullParser);
                                                            if (strC2 != null && strC3 != null) {
                                                                gVar.b(strC, strC2, strC3);
                                                            }
                                                        } else {
                                                            d(xmlPullParser);
                                                        }
                                                    }
                                                }
                                            } else {
                                                d(xmlPullParser);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        d(xmlPullParser);
                    }
                }
            } catch (IOException | IllegalStateException | XmlPullParserException e) {
                Log.e("SplitInstall", "Error while parsing splits.xml", e);
                return null;
            }
        }
        return gVar.a();
    }

    @Nullable
    private static String c(String str, XmlPullParser xmlPullParser) {
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            if (xmlPullParser.getAttributeName(i).equals(str)) {
                return xmlPullParser.getAttributeValue(i);
            }
        }
        return null;
    }

    private static void d(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int i = 1;
        while (i != 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i++;
            } else if (next == 3) {
                i--;
            }
        }
    }
}
