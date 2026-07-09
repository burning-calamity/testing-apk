package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
final class aw extends com.google.android.play.core.internal.br {
    private final File a;
    private final File b;
    private final NavigableMap<Long, File> c = new TreeMap();

    aw(File file, File file2) throws IOException {
        this.a = file;
        this.b = file2;
        List<File> listA = db.a(this.a, this.b);
        if (listA.isEmpty()) {
            throw new bk(String.format("Virtualized slice archive empty for %s, %s", this.a, this.b));
        }
        long length = 0;
        for (File file3 : listA) {
            this.c.put(Long.valueOf(length), file3);
            length += file3.length();
        }
    }

    private final InputStream d(long j, Long l) throws IOException {
        FileInputStream fileInputStream = new FileInputStream((File) this.c.get(l));
        if (fileInputStream.skip(j - l.longValue()) == j - l.longValue()) {
            return fileInputStream;
        }
        throw new bk(String.format("Virtualized slice archive corrupt, could not skip in file with key %s", l));
    }

    @Override // com.google.android.play.core.internal.br
    public final long a() {
        Map.Entry<Long, File> entryLastEntry = this.c.lastEntry();
        return entryLastEntry.getKey().longValue() + entryLastEntry.getValue().length();
    }

    @Override // com.google.android.play.core.internal.br
    protected final InputStream b(long j, long j2) throws IOException {
        if (j < 0 || j2 < 0) {
            throw new bk(String.format("Invalid input parameters %s, %s", Long.valueOf(j), Long.valueOf(j2)));
        }
        long j3 = j + j2;
        if (j3 > a()) {
            throw new bk(String.format("Trying to access archive out of bounds. Archive ends at: %s. Tried accessing: %s", Long.valueOf(a()), Long.valueOf(j3)));
        }
        Long lFloorKey = this.c.floorKey(Long.valueOf(j));
        Long lFloorKey2 = this.c.floorKey(Long.valueOf(j3));
        if (lFloorKey.equals(lFloorKey2)) {
            return new av(d(j, lFloorKey), j2);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(d(j, lFloorKey));
        Collection<File> collectionValues = this.c.subMap(lFloorKey, false, lFloorKey2, false).values();
        if (!collectionValues.isEmpty()) {
            arrayList.add(new cl(Collections.enumeration(collectionValues)));
        }
        arrayList.add(new av(new FileInputStream((File) this.c.get(lFloorKey2)), j2 - (lFloorKey2.longValue() - j)));
        return new SequenceInputStream(Collections.enumeration(arrayList));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }
}
