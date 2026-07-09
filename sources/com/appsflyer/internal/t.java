package com.appsflyer.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: classes.dex */
public final class t {
    t() {
    }

    /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
    public static a m211(Context context) throws Exception {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot be called from the main thread");
        }
        try {
            byte b = 0;
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            e eVar = new e(b);
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            try {
                try {
                    if (!context.bindService(intent, eVar, 1)) {
                        if (context != null) {
                            context.unbindService(eVar);
                        }
                        throw new IOException("Google Play connection failed");
                    }
                    if (eVar.f347) {
                        throw new IllegalStateException();
                    }
                    eVar.f347 = true;
                    d dVar = new d(eVar.f346.take());
                    return new a(dVar.m214(), dVar.m213());
                } catch (Exception e2) {
                    throw e2;
                }
            } finally {
                if (context != null) {
                    context.unbindService(eVar);
                }
            }
        } catch (Exception e3) {
            throw e3;
        }
    }

    public static final class a {

        /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
        public final String f343;

        /* JADX INFO: renamed from: ι, reason: contains not printable characters */
        private final boolean f344;

        a(String str, boolean z) {
            this.f343 = str;
            this.f344 = z;
        }

        /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
        public final boolean m212() {
            return this.f344;
        }
    }

    static final class e implements ServiceConnection {

        /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
        final LinkedBlockingQueue<IBinder> f346;

        /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
        boolean f347;

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
        }

        private e() {
            this.f346 = new LinkedBlockingQueue<>(1);
            this.f347 = false;
        }

        /* synthetic */ e(byte b) {
            this();
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f346.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }
    }

    static final class d implements IInterface {

        /* JADX INFO: renamed from: ɩ, reason: contains not printable characters */
        private IBinder f345;

        d(IBinder iBinder) {
            this.f345 = iBinder;
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this.f345;
        }

        /* JADX INFO: renamed from: Ι, reason: contains not printable characters */
        public final String m214() throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.f345.transact(1, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readString();
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        /* JADX INFO: renamed from: ǃ, reason: contains not printable characters */
        final boolean m213() throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                parcelObtain.writeInt(1);
                this.f345.transact(2, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readInt() != 0;
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }
    }
}
