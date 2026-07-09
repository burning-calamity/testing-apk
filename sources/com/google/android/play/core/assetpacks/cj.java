package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.appsflyer.ServerParameters;
import com.google.android.gms.drive.DriveFile;
import com.google.android.play.core.assetpacks.model.AssetPackStatus;
import com.google.android.play.core.common.LocalTestingException;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
final class cj implements t {
    private static final com.google.android.play.core.internal.ag a = new com.google.android.play.core.internal.ag("FakeAssetPackService");
    private static final AtomicInteger h = new AtomicInteger(1);
    private final String b;
    private final ar c;
    private final bo d;
    private final Context e;
    private final cv f;
    private final com.google.android.play.core.internal.ca<Executor> g;
    private final Handler i = new Handler(Looper.getMainLooper());

    cj(File file, ar arVar, bo boVar, Context context, cv cvVar, com.google.android.play.core.internal.ca<Executor> caVar) {
        this.b = file.getAbsolutePath();
        this.c = arVar;
        this.d = boVar;
        this.e = context;
        this.f = cvVar;
        this.g = caVar;
    }

    static long k(@AssetPackStatus int i, long j) {
        if (i == 2) {
            return j / 2;
        }
        if (i == 3 || i == 4) {
            return j;
        }
        return 0L;
    }

    private final AssetPackState p(String str, @AssetPackStatus int i) throws LocalTestingException {
        long length = 0;
        for (File file : q(str)) {
            length += file.length();
        }
        return AssetPackState.c(str, i, 0, k(i, length), length, this.d.b(str), 1, "");
    }

    private final File[] q(final String str) throws LocalTestingException {
        File file = new File(this.b);
        if (!file.isDirectory()) {
            throw new LocalTestingException(String.format("Local testing directory '%s' not found.", file));
        }
        File[] fileArrListFiles = file.listFiles(new FilenameFilter(str) { // from class: com.google.android.play.core.assetpacks.ch
            private final String a;

            {
                this.a = str;
            }

            @Override // java.io.FilenameFilter
            public final boolean accept(File file2, String str2) {
                return str2.startsWith(String.valueOf(this.a).concat("-")) && str2.endsWith(".apk");
            }
        });
        if (fileArrListFiles == null) {
            throw new LocalTestingException(String.format("Failed fetching APKs for pack '%s'.", str));
        }
        if (fileArrListFiles.length == 0) {
            throw new LocalTestingException(String.format("No APKs available for pack '%s'.", str));
        }
        for (File file2 : fileArrListFiles) {
            if (com.google.android.play.core.internal.ax.b(file2).equals(str)) {
                return fileArrListFiles;
            }
        }
        throw new LocalTestingException(String.format("No master slice available for pack '%s'.", str));
    }

    private static String r(File file) throws LocalTestingException {
        try {
            return ck.a(Arrays.asList(file));
        } catch (IOException e) {
            throw new LocalTestingException(String.format("Could not digest file: %s.", file), e);
        } catch (NoSuchAlgorithmException e2) {
            throw new LocalTestingException("SHA256 algorithm not supported.", e2);
        }
    }

    private final void s(int i, String str, @AssetPackStatus int i2) throws LocalTestingException {
        Bundle bundle = new Bundle();
        bundle.putInt(ServerParameters.APP_VERSION_CODE, this.f.a());
        bundle.putInt("session_id", i);
        File[] fileArrQ = q(str);
        ArrayList<String> arrayList = new ArrayList<>();
        long length = 0;
        for (File file : fileArrQ) {
            length += file.length();
            ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>();
            arrayList2.add(i2 == 3 ? new Intent().setData(Uri.EMPTY) : null);
            String strB = com.google.android.play.core.internal.ax.b(file);
            bundle.putParcelableArrayList(com.google.android.play.core.internal.i.f("chunk_intents", str, strB), arrayList2);
            bundle.putString(com.google.android.play.core.internal.i.f("uncompressed_hash_sha256", str, strB), r(file));
            bundle.putLong(com.google.android.play.core.internal.i.f("uncompressed_size", str, strB), file.length());
            arrayList.add(strB);
        }
        bundle.putStringArrayList(com.google.android.play.core.internal.i.e("slice_ids", str), arrayList);
        bundle.putLong(com.google.android.play.core.internal.i.e("pack_version", str), this.f.a());
        bundle.putInt(com.google.android.play.core.internal.i.e("status", str), i2);
        bundle.putInt(com.google.android.play.core.internal.i.e("error_code", str), 0);
        bundle.putLong(com.google.android.play.core.internal.i.e("bytes_downloaded", str), k(i2, length));
        bundle.putLong(com.google.android.play.core.internal.i.e("total_bytes_to_download", str), length);
        bundle.putStringArrayList("pack_names", new ArrayList<>(Arrays.asList(str)));
        bundle.putLong("bytes_downloaded", k(i2, length));
        bundle.putLong("total_bytes_to_download", length);
        final Intent intentPutExtra = new Intent("com.google.android.play.core.assetpacks.receiver.ACTION_SESSION_UPDATE").putExtra("com.google.android.play.core.assetpacks.receiver.EXTRA_SESSION_STATE", bundle);
        this.i.post(new Runnable(this, intentPutExtra) { // from class: com.google.android.play.core.assetpacks.ci
            private final cj a;
            private final Intent b;

            {
                this.a = this;
                this.b = intentPutExtra;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.a.l(this.b);
            }
        });
    }

    @Override // com.google.android.play.core.assetpacks.t
    public final Task<AssetPackStates> a(final List<String> list, final List<String> list2, Map<String, Long> map) {
        a.d("startDownload(%s)", list2);
        final com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        this.g.a().execute(new Runnable(this, list2, iVar, list) { // from class: com.google.android.play.core.assetpacks.ce
            private final cj a;
            private final List b;
            private final com.google.android.play.core.tasks.i c;
            private final List d;

            {
                this.a = this;
                this.b = list2;
                this.c = iVar;
                this.d = list;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.a.o(this.b, this.c, this.d);
            }
        });
        return iVar.c();
    }

    @Override // com.google.android.play.core.assetpacks.t
    public final void b(List<String> list) {
        a.d("cancelDownload(%s)", list);
    }

    @Override // com.google.android.play.core.assetpacks.t
    public final Task<List<String>> c(Map<String, Long> map) {
        a.d("syncPacks()", new Object[0]);
        return Tasks.a(new ArrayList());
    }

    @Override // com.google.android.play.core.assetpacks.t
    public final Task<AssetPackStates> d(final List<String> list, final as asVar, Map<String, Long> map) {
        a.d("getPackStates(%s)", list);
        final com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        this.g.a().execute(new Runnable(this, list, asVar, iVar) { // from class: com.google.android.play.core.assetpacks.cf
            private final cj a;
            private final List b;
            private final as c;
            private final com.google.android.play.core.tasks.i d;

            {
                this.a = this;
                this.b = list;
                this.c = asVar;
                this.d = iVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.a.n(this.b, this.c, this.d);
            }
        });
        return iVar.c();
    }

    @Override // com.google.android.play.core.assetpacks.t
    public final void e(int i, String str, String str2, int i2) {
        a.d("notifyChunkTransferred", new Object[0]);
    }

    @Override // com.google.android.play.core.assetpacks.t
    public final void f(final int i, final String str) {
        a.d("notifyModuleCompleted", new Object[0]);
        this.g.a().execute(new Runnable(this, i, str) { // from class: com.google.android.play.core.assetpacks.cg
            private final cj a;
            private final int b;
            private final String c;

            {
                this.a = this;
                this.b = i;
                this.c = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.a.m(this.b, this.c);
            }
        });
    }

    @Override // com.google.android.play.core.assetpacks.t
    public final void g(int i) {
        a.d("notifySessionFailed", new Object[0]);
    }

    @Override // com.google.android.play.core.assetpacks.t
    public final Task<ParcelFileDescriptor> h(int i, String str, String str2, int i2) {
        int i3;
        a.d("getChunkFileDescriptor(session=%d, %s, %s, %d)", Integer.valueOf(i), str, str2, Integer.valueOf(i2));
        com.google.android.play.core.tasks.i iVar = new com.google.android.play.core.tasks.i();
        try {
        } catch (LocalTestingException e) {
            a.e("getChunkFileDescriptor failed", e);
            iVar.b(e);
        } catch (FileNotFoundException e2) {
            a.e("getChunkFileDescriptor failed", e2);
            iVar.b(new LocalTestingException("Asset Slice file not found.", e2));
        }
        for (File file : q(str)) {
            if (com.google.android.play.core.internal.ax.b(file).equals(str2)) {
                iVar.a(ParcelFileDescriptor.open(file, DriveFile.MODE_READ_ONLY));
                return iVar.c();
            }
        }
        throw new LocalTestingException(String.format("Local testing slice for '%s' not found.", str2));
    }

    @Override // com.google.android.play.core.assetpacks.t
    public final void i(String str) {
        a.d("removePack(%s)", str);
    }

    @Override // com.google.android.play.core.assetpacks.t
    public final void j() {
        a.d("keepAlive", new Object[0]);
    }

    final /* synthetic */ void l(Intent intent) {
        this.c.a(this.e, intent);
    }

    final /* synthetic */ void m(int i, String str) {
        try {
            s(i, str, 4);
        } catch (LocalTestingException e) {
            a.e("notifyModuleCompleted failed", e);
        }
    }

    final /* synthetic */ void n(List list, as asVar, com.google.android.play.core.tasks.i iVar) {
        HashMap map = new HashMap();
        Iterator it = list.iterator();
        long j = 0;
        while (it.hasNext()) {
            String str = (String) it.next();
            try {
                AssetPackState assetPackStateP = p(str, asVar.a(8, str));
                j += assetPackStateP.totalBytesToDownload();
                map.put(str, assetPackStateP);
            } catch (LocalTestingException e) {
                iVar.b(e);
                return;
            }
        }
        iVar.a(AssetPackStates.a(j, map));
    }

    final /* synthetic */ void o(List list, com.google.android.play.core.tasks.i iVar, List list2) {
        HashMap map = new HashMap();
        Iterator it = list.iterator();
        long j = 0;
        while (it.hasNext()) {
            String str = (String) it.next();
            try {
                AssetPackState assetPackStateP = p(str, 1);
                j += assetPackStateP.totalBytesToDownload();
                map.put(str, assetPackStateP);
            } catch (LocalTestingException e) {
                iVar.b(e);
                return;
            }
        }
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            String str2 = (String) it2.next();
            try {
                int andIncrement = h.getAndIncrement();
                s(andIncrement, str2, 1);
                s(andIncrement, str2, 2);
                s(andIncrement, str2, 3);
            } catch (LocalTestingException e2) {
                iVar.b(e2);
                return;
            }
        }
        Iterator it3 = list2.iterator();
        while (it3.hasNext()) {
            String str3 = (String) it3.next();
            map.put(str3, AssetPackState.c(str3, 4, 0, 0L, 0L, 0.0d, 1, ""));
        }
        iVar.a(AssetPackStates.a(j, map));
    }
}
