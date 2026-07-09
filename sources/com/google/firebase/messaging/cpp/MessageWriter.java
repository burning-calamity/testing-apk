package com.google.firebase.messaging.cpp;

import android.content.Context;
import android.net.Uri;
import com.google.firebase.messaging.RemoteMessage;
import com.google.flatbuffers.FlatBufferBuilder;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileLock;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class MessageWriter {
    private static final MessageWriter DEFAULT_INSTANCE = new MessageWriter();
    static final String LOCK_FILE = "FIREBASE_CLOUD_MESSAGING_LOCKFILE";
    static final String STORAGE_FILE = "FIREBASE_CLOUD_MESSAGING_LOCAL_STORAGE";
    private static final String TAG = "FIREBASE_MESSAGE_WRITER";

    private static String emptyIfNull(String str) {
        return str != null ? str : "";
    }

    private static String priorityToString(int i) {
        return i != 1 ? i != 2 ? "" : "normal" : "high";
    }

    public static MessageWriter defaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public void writeMessage(Context context, RemoteMessage remoteMessage, boolean z, Uri uri) {
        String from = remoteMessage.getFrom();
        String to = remoteMessage.getTo();
        String messageId = remoteMessage.getMessageId();
        String messageType = remoteMessage.getMessageType();
        Map<String, String> data = remoteMessage.getData();
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        String collapseKey = remoteMessage.getCollapseKey();
        int priority = remoteMessage.getPriority();
        int originalPriority = remoteMessage.getOriginalPriority();
        long sentTime = remoteMessage.getSentTime();
        int ttl = remoteMessage.getTtl();
        Uri link = (uri != null || notification == null) ? uri : notification.getLink();
        String string = link != null ? link.toString() : null;
        Object[] objArr = new Object[4];
        objArr[0] = from;
        objArr[1] = messageId;
        objArr[2] = data == null ? "(null)" : data.toString();
        objArr[3] = notification != null ? notification.toString() : "(null)";
        DebugLogging.log(TAG, String.format("onMessageReceived from=%s message_id=%s, data=%s, notification=%s", objArr));
        writeMessageToInternalStorage(context, from, to, messageId, messageType, null, data, notification, z, string, collapseKey, priority, originalPriority, sentTime, ttl);
    }

    void writeMessageEventToInternalStorage(Context context, String str, String str2, String str3) {
        writeMessageToInternalStorage(context, null, null, str, str2, null, null, null, false, null, null, 0, 0, 0L, 0);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x004d -> B:23:0x0051). Please report as a decompilation issue!!! */
    void writeMessageToInternalStorage(Context context, String str, String str2, String str3, String str4, String str5, Map<String, String> map, RemoteMessage.Notification notification, boolean z, String str6, String str7, int i, int i2, long j, int i3) {
        byte[] bArrGenerateMessageByteBuffer = generateMessageByteBuffer(str, str2, str3, str4, str5, map, notification, z, str6, str7, i, i2, j, i3);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.putInt(bArrGenerateMessageByteBuffer.length);
        FileLock fileLockLock = null;
        try {
            try {
                try {
                    fileLockLock = context.openFileOutput(LOCK_FILE, 0).getChannel().lock();
                    FileOutputStream fileOutputStreamOpenFileOutput = context.openFileOutput(STORAGE_FILE, 32768);
                    fileOutputStreamOpenFileOutput.write(byteBufferAllocate.array());
                    fileOutputStreamOpenFileOutput.write(bArrGenerateMessageByteBuffer);
                    fileOutputStreamOpenFileOutput.close();
                    if (fileLockLock != null) {
                        fileLockLock.release();
                    }
                } catch (Throwable th) {
                    if (fileLockLock != null) {
                        try {
                            fileLockLock.release();
                            throw th;
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw th;
                        }
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (fileLockLock == null) {
                } else {
                    fileLockLock.release();
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    private static byte[] generateMessageByteBuffer(String str, String str2, String str3, String str4, String str5, Map<String, String> map, RemoteMessage.Notification notification, boolean z, String str6, String str7, int i, int i2, long j, int i3) {
        int iCreateDataVector;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int iEndSerializedNotification;
        int iCreateBodyLocArgsVector;
        int i14;
        int iCreateTitleLocArgsVector;
        FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder(0);
        int iCreateString = flatBufferBuilder.createString(emptyIfNull(str));
        int iCreateString2 = flatBufferBuilder.createString(emptyIfNull(str2));
        int iCreateString3 = flatBufferBuilder.createString(emptyIfNull(str3));
        int iCreateString4 = flatBufferBuilder.createString(emptyIfNull(str4));
        int iCreateString5 = flatBufferBuilder.createString(emptyIfNull(str5));
        int iCreateString6 = flatBufferBuilder.createString(emptyIfNull(str6));
        int iCreateString7 = flatBufferBuilder.createString(emptyIfNull(str7));
        int iCreateString8 = flatBufferBuilder.createString(priorityToString(i));
        int iCreateString9 = flatBufferBuilder.createString(priorityToString(i2));
        if (map != null) {
            int[] iArr = new int[map.size()];
            int i15 = 0;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                iArr[i15] = DataPair.createDataPair(flatBufferBuilder, flatBufferBuilder.createString(entry.getKey()), flatBufferBuilder.createString(entry.getValue()));
                i15++;
            }
            iCreateDataVector = SerializedMessage.createDataVector(flatBufferBuilder, iArr);
        } else {
            iCreateDataVector = 0;
        }
        if (notification != null) {
            int iCreateString10 = flatBufferBuilder.createString(emptyIfNull(notification.getTitle()));
            int iCreateString11 = flatBufferBuilder.createString(emptyIfNull(notification.getBody()));
            int iCreateString12 = flatBufferBuilder.createString(emptyIfNull(notification.getIcon()));
            int iCreateString13 = flatBufferBuilder.createString(emptyIfNull(notification.getSound()));
            int iCreateString14 = flatBufferBuilder.createString("");
            i9 = iCreateString6;
            int iCreateString15 = flatBufferBuilder.createString(emptyIfNull(notification.getTag()));
            i13 = iCreateDataVector;
            int iCreateString16 = flatBufferBuilder.createString(emptyIfNull(notification.getColor()));
            i10 = iCreateString7;
            int iCreateString17 = flatBufferBuilder.createString(emptyIfNull(notification.getClickAction()));
            i8 = iCreateString5;
            int iCreateString18 = flatBufferBuilder.createString(emptyIfNull(notification.getChannelId()));
            i12 = iCreateString9;
            int iCreateString19 = flatBufferBuilder.createString(emptyIfNull(notification.getBodyLocalizationKey()));
            i11 = iCreateString8;
            String[] bodyLocalizationArgs = notification.getBodyLocalizationArgs();
            if (bodyLocalizationArgs != null) {
                i7 = iCreateString4;
                int[] iArr2 = new int[bodyLocalizationArgs.length];
                i6 = iCreateString3;
                int length = bodyLocalizationArgs.length;
                i5 = iCreateString2;
                int i16 = 0;
                int i17 = 0;
                while (i16 < length) {
                    iArr2[i17] = flatBufferBuilder.createString(bodyLocalizationArgs[i16]);
                    i16++;
                    length = length;
                    i17++;
                }
                iCreateBodyLocArgsVector = SerializedNotification.createBodyLocArgsVector(flatBufferBuilder, iArr2);
            } else {
                i5 = iCreateString2;
                i6 = iCreateString3;
                i7 = iCreateString4;
                iCreateBodyLocArgsVector = 0;
            }
            int iCreateString20 = flatBufferBuilder.createString(emptyIfNull(notification.getTitleLocalizationKey()));
            String[] titleLocalizationArgs = notification.getTitleLocalizationArgs();
            if (titleLocalizationArgs != null) {
                int[] iArr3 = new int[titleLocalizationArgs.length];
                i4 = iCreateString;
                int length2 = titleLocalizationArgs.length;
                i14 = iCreateString20;
                int i18 = 0;
                int i19 = 0;
                while (i18 < length2) {
                    iArr3[i19] = flatBufferBuilder.createString(titleLocalizationArgs[i18]);
                    i18++;
                    length2 = length2;
                    i19++;
                }
                iCreateTitleLocArgsVector = SerializedNotification.createTitleLocArgsVector(flatBufferBuilder, iArr3);
            } else {
                i4 = iCreateString;
                i14 = iCreateString20;
                iCreateTitleLocArgsVector = 0;
            }
            SerializedNotification.startSerializedNotification(flatBufferBuilder);
            SerializedNotification.addTitle(flatBufferBuilder, iCreateString10);
            SerializedNotification.addBody(flatBufferBuilder, iCreateString11);
            SerializedNotification.addIcon(flatBufferBuilder, iCreateString12);
            SerializedNotification.addSound(flatBufferBuilder, iCreateString13);
            SerializedNotification.addBadge(flatBufferBuilder, iCreateString14);
            SerializedNotification.addTag(flatBufferBuilder, iCreateString15);
            SerializedNotification.addColor(flatBufferBuilder, iCreateString16);
            SerializedNotification.addClickAction(flatBufferBuilder, iCreateString17);
            SerializedNotification.addAndroidChannelId(flatBufferBuilder, iCreateString18);
            SerializedNotification.addBodyLocKey(flatBufferBuilder, iCreateString19);
            SerializedNotification.addBodyLocArgs(flatBufferBuilder, iCreateBodyLocArgsVector);
            SerializedNotification.addTitleLocKey(flatBufferBuilder, i14);
            SerializedNotification.addTitleLocArgs(flatBufferBuilder, iCreateTitleLocArgsVector);
            iEndSerializedNotification = SerializedNotification.endSerializedNotification(flatBufferBuilder);
        } else {
            i4 = iCreateString;
            i5 = iCreateString2;
            i6 = iCreateString3;
            i7 = iCreateString4;
            i8 = iCreateString5;
            i9 = iCreateString6;
            i10 = iCreateString7;
            i11 = iCreateString8;
            i12 = iCreateString9;
            i13 = iCreateDataVector;
            iEndSerializedNotification = 0;
        }
        SerializedMessage.startSerializedMessage(flatBufferBuilder);
        SerializedMessage.addFrom(flatBufferBuilder, i4);
        SerializedMessage.addTo(flatBufferBuilder, i5);
        SerializedMessage.addMessageId(flatBufferBuilder, i6);
        SerializedMessage.addMessageType(flatBufferBuilder, i7);
        SerializedMessage.addPriority(flatBufferBuilder, i11);
        SerializedMessage.addOriginalPriority(flatBufferBuilder, i12);
        SerializedMessage.addSentTime(flatBufferBuilder, j);
        SerializedMessage.addTimeToLive(flatBufferBuilder, i3);
        SerializedMessage.addError(flatBufferBuilder, i8);
        SerializedMessage.addCollapseKey(flatBufferBuilder, i10);
        if (map != null) {
            SerializedMessage.addData(flatBufferBuilder, i13);
        }
        if (notification != null) {
            SerializedMessage.addNotification(flatBufferBuilder, iEndSerializedNotification);
        }
        SerializedMessage.addNotificationOpened(flatBufferBuilder, z);
        SerializedMessage.addLink(flatBufferBuilder, i9);
        int iEndSerializedMessage = SerializedMessage.endSerializedMessage(flatBufferBuilder);
        SerializedEvent.startSerializedEvent(flatBufferBuilder);
        SerializedEvent.addEventType(flatBufferBuilder, (byte) 1);
        SerializedEvent.addEvent(flatBufferBuilder, iEndSerializedMessage);
        flatBufferBuilder.finish(SerializedEvent.endSerializedEvent(flatBufferBuilder));
        return flatBufferBuilder.sizedByteArray();
    }
}
