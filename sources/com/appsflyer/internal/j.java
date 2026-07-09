package com.appsflyer.internal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    enum a {
        NONE,
        FIRST,
        ALL
    }

    enum e {
        UNKNOWN,
        ACCELEROMETER,
        MAGNETOMETER,
        RESERVED,
        GYROSCOPE
    }

    j() {
    }

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    private static List<BigDecimal> m195(Object obj) {
        ArrayList arrayList = (ArrayList) obj;
        Float f = (Float) arrayList.get(0);
        Float f2 = (Float) arrayList.get(1);
        Float f3 = (Float) arrayList.get(2);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(BigDecimal.valueOf(Double.parseDouble(f.toString())));
        arrayList2.add(BigDecimal.valueOf(Double.parseDouble(f2.toString())));
        arrayList2.add(BigDecimal.valueOf(Double.parseDouble(f3.toString())));
        return arrayList2;
    }

    /* JADX INFO: renamed from: ι, reason: contains not printable characters */
    public static Map m196(List<Map<String, Object>> list) {
        a aVar;
        a aVar2;
        ArrayList arrayList;
        HashMap map = new HashMap();
        for (Map<String, Object> map2 : list) {
            HashMap map3 = new HashMap();
            boolean z = map2.get("sVS") != null;
            boolean z2 = map2.get("sVE") != null;
            if (z && z2) {
                aVar = a.ALL;
            } else if (z) {
                aVar = a.FIRST;
            } else {
                aVar = a.NONE;
            }
            if (aVar != a.NONE) {
                Integer num = (Integer) map2.get("sT");
                String str = (String) map2.get("sN");
                if (str != null) {
                    map3.put("n", str);
                } else {
                    map3.put("n", "uk");
                }
                e eVar = e.values()[num.intValue()];
                ArrayList arrayList2 = new ArrayList(m195(map2.get("sVS")));
                if (aVar == a.ALL) {
                    arrayList2.addAll(m195(map2.get("sVE")));
                }
                if (eVar == e.MAGNETOMETER) {
                    ArrayList arrayList3 = new ArrayList();
                    BigDecimal bigDecimal = (BigDecimal) arrayList2.get(0);
                    aVar2 = aVar;
                    BigDecimal bigDecimalValueOf = BigDecimal.valueOf(Math.atan2(((BigDecimal) arrayList2.get(1)).doubleValue(), bigDecimal.doubleValue()) * 57.29577951308232d);
                    DecimalFormat decimalFormat = new DecimalFormat("##.#");
                    decimalFormat.setRoundingMode(RoundingMode.DOWN);
                    arrayList3.add(Double.valueOf(Double.parseDouble(decimalFormat.format(bigDecimalValueOf))));
                    BigDecimal bigDecimal2 = (BigDecimal) arrayList2.get(2);
                    DecimalFormat decimalFormat2 = new DecimalFormat("##.#");
                    decimalFormat2.setRoundingMode(RoundingMode.DOWN);
                    arrayList3.add(Double.valueOf(Double.parseDouble(decimalFormat2.format(bigDecimal2))));
                    ArrayList arrayList4 = new ArrayList();
                    if (arrayList2.size() > 5) {
                        BigDecimal bigDecimal3 = (BigDecimal) arrayList2.get(3);
                        BigDecimal bigDecimalSubtract = BigDecimal.valueOf(Math.atan2(((BigDecimal) arrayList2.get(4)).doubleValue(), bigDecimal3.doubleValue()) * 57.29577951308232d).subtract(bigDecimalValueOf);
                        DecimalFormat decimalFormat3 = new DecimalFormat("##.#");
                        decimalFormat3.setRoundingMode(RoundingMode.DOWN);
                        arrayList4.add(Double.valueOf(Double.parseDouble(decimalFormat3.format(bigDecimalSubtract))));
                        BigDecimal bigDecimalSubtract2 = ((BigDecimal) arrayList2.get(5)).subtract((BigDecimal) arrayList2.get(2));
                        DecimalFormat decimalFormat4 = new DecimalFormat("##.#");
                        decimalFormat4.setRoundingMode(RoundingMode.DOWN);
                        arrayList4.add(Double.valueOf(Double.parseDouble(decimalFormat4.format(bigDecimalSubtract2))));
                    }
                    arrayList = new ArrayList();
                    arrayList.add(arrayList3);
                    arrayList.add(arrayList4);
                } else {
                    aVar2 = aVar;
                    ArrayList arrayList5 = new ArrayList();
                    if (arrayList2.size() > 5) {
                        BigDecimal bigDecimalSubtract3 = ((BigDecimal) arrayList2.get(3)).subtract((BigDecimal) arrayList2.get(0));
                        DecimalFormat decimalFormat5 = new DecimalFormat("##.#");
                        decimalFormat5.setRoundingMode(RoundingMode.DOWN);
                        arrayList5.add(Double.valueOf(Double.parseDouble(decimalFormat5.format(bigDecimalSubtract3))));
                        BigDecimal bigDecimalSubtract4 = ((BigDecimal) arrayList2.get(4)).subtract((BigDecimal) arrayList2.get(1));
                        DecimalFormat decimalFormat6 = new DecimalFormat("##.#");
                        decimalFormat6.setRoundingMode(RoundingMode.DOWN);
                        arrayList5.add(Double.valueOf(Double.parseDouble(decimalFormat6.format(bigDecimalSubtract4))));
                        BigDecimal bigDecimalSubtract5 = ((BigDecimal) arrayList2.get(5)).subtract((BigDecimal) arrayList2.get(2));
                        DecimalFormat decimalFormat7 = new DecimalFormat("##.#");
                        decimalFormat7.setRoundingMode(RoundingMode.DOWN);
                        arrayList5.add(Double.valueOf(Double.parseDouble(decimalFormat7.format(bigDecimalSubtract5))));
                    }
                    ArrayList arrayList6 = new ArrayList();
                    BigDecimal bigDecimal4 = (BigDecimal) arrayList2.get(0);
                    DecimalFormat decimalFormat8 = new DecimalFormat("##.#");
                    decimalFormat8.setRoundingMode(RoundingMode.DOWN);
                    arrayList6.add(Double.valueOf(Double.parseDouble(decimalFormat8.format(bigDecimal4))));
                    BigDecimal bigDecimal5 = (BigDecimal) arrayList2.get(1);
                    DecimalFormat decimalFormat9 = new DecimalFormat("##.#");
                    decimalFormat9.setRoundingMode(RoundingMode.DOWN);
                    arrayList6.add(Double.valueOf(Double.parseDouble(decimalFormat9.format(bigDecimal5))));
                    BigDecimal bigDecimal6 = (BigDecimal) arrayList2.get(2);
                    DecimalFormat decimalFormat10 = new DecimalFormat("##.#");
                    decimalFormat10.setRoundingMode(RoundingMode.DOWN);
                    arrayList6.add(Double.valueOf(Double.parseDouble(decimalFormat10.format(bigDecimal6))));
                    ArrayList arrayList7 = new ArrayList();
                    arrayList7.add(arrayList6);
                    arrayList7.add(arrayList5);
                    arrayList = arrayList7;
                }
                map3.put("v", arrayList);
                map.put(b.values()[num.intValue()].f300, map3);
                if (aVar2 == a.FIRST) {
                    map.put("er", "no_svs");
                }
            } else {
                HashMap map4 = new HashMap();
                map4.put("er", "na");
                return map4;
            }
        }
        return map;
    }

    enum b {
        UNKNOWN("uk"),
        ACCELEROMETER("am"),
        MAGNETOMETER("mm"),
        RESERVED("rs"),
        GYROSCOPE("gs");


        /* JADX INFO: renamed from: ι, reason: contains not printable characters */
        String f300;

        b(String str) {
            this.f300 = str;
        }
    }
}
