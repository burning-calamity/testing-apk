package com.google.android.play.core.internal;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.LocaleList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class bo {
    private final Context a;

    public bo(Context context) {
        this.a = context;
    }

    private static String b(Locale locale) {
        String strConcat;
        String strValueOf = String.valueOf(locale.getLanguage());
        if (locale.getCountry().isEmpty()) {
            strConcat = "";
        } else {
            String strValueOf2 = String.valueOf(locale.getCountry());
            strConcat = strValueOf2.length() != 0 ? "_".concat(strValueOf2) : new String("_");
        }
        String strValueOf3 = String.valueOf(strConcat);
        return strValueOf3.length() != 0 ? strValueOf.concat(strValueOf3) : new String(strValueOf);
    }

    public final List<String> a() {
        Configuration configuration = this.a.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT < 24) {
            return Collections.singletonList(b(configuration.locale));
        }
        LocaleList locales = configuration.getLocales();
        ArrayList arrayList = new ArrayList(locales.size());
        for (int i = 0; i < locales.size(); i++) {
            arrayList.add(b(locales.get(i)));
        }
        return arrayList;
    }
}
