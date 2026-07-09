package com.onevcat.uniwebview;

import android.os.Binder;

/* JADX INFO: loaded from: classes.dex */
public class ObjectWrapperForBinder extends Binder {
    private final Object mData;

    public ObjectWrapperForBinder(Object obj) {
        this.mData = obj;
    }

    public Object getData() {
        return this.mData;
    }
}
