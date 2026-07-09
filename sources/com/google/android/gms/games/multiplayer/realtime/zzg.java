package com.google.android.gms.games.multiplayer.realtime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzg implements zzh {
    private final RoomUpdateCallback zzou;
    private final RoomStatusUpdateCallback zzov;
    private final OnRealTimeMessageReceivedListener zzpf;

    public zzg(@NonNull RoomUpdateCallback roomUpdateCallback, @Nullable RoomStatusUpdateCallback roomStatusUpdateCallback, @Nullable OnRealTimeMessageReceivedListener onRealTimeMessageReceivedListener) {
        this.zzou = roomUpdateCallback;
        this.zzov = roomStatusUpdateCallback;
        this.zzpf = onRealTimeMessageReceivedListener;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onConnectedToRoom(@Nullable Room room) {
        RoomStatusUpdateCallback roomStatusUpdateCallback = this.zzov;
        if (roomStatusUpdateCallback != null) {
            roomStatusUpdateCallback.onConnectedToRoom(room);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onDisconnectedFromRoom(@Nullable Room room) {
        RoomStatusUpdateCallback roomStatusUpdateCallback = this.zzov;
        if (roomStatusUpdateCallback != null) {
            roomStatusUpdateCallback.onDisconnectedFromRoom(room);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener
    public final void onJoinedRoom(int i, @Nullable Room room) {
        RoomUpdateCallback roomUpdateCallback = this.zzou;
        if (roomUpdateCallback != null) {
            roomUpdateCallback.onJoinedRoom(i, room);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener
    public final void onLeftRoom(int i, @NonNull String str) {
        RoomUpdateCallback roomUpdateCallback = this.zzou;
        if (roomUpdateCallback != null) {
            roomUpdateCallback.onLeftRoom(i, str);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onP2PConnected(@NonNull String str) {
        RoomStatusUpdateCallback roomStatusUpdateCallback = this.zzov;
        if (roomStatusUpdateCallback != null) {
            roomStatusUpdateCallback.onP2PConnected(str);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onP2PDisconnected(@NonNull String str) {
        RoomStatusUpdateCallback roomStatusUpdateCallback = this.zzov;
        if (roomStatusUpdateCallback != null) {
            roomStatusUpdateCallback.onP2PDisconnected(str);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onPeerDeclined(@Nullable Room room, @NonNull List<String> list) {
        RoomStatusUpdateCallback roomStatusUpdateCallback = this.zzov;
        if (roomStatusUpdateCallback != null) {
            roomStatusUpdateCallback.onPeerDeclined(room, list);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onPeerInvitedToRoom(@Nullable Room room, @NonNull List<String> list) {
        RoomStatusUpdateCallback roomStatusUpdateCallback = this.zzov;
        if (roomStatusUpdateCallback != null) {
            roomStatusUpdateCallback.onPeerInvitedToRoom(room, list);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onPeerJoined(@Nullable Room room, @NonNull List<String> list) {
        RoomStatusUpdateCallback roomStatusUpdateCallback = this.zzov;
        if (roomStatusUpdateCallback != null) {
            roomStatusUpdateCallback.onPeerJoined(room, list);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onPeerLeft(@Nullable Room room, @NonNull List<String> list) {
        RoomStatusUpdateCallback roomStatusUpdateCallback = this.zzov;
        if (roomStatusUpdateCallback != null) {
            roomStatusUpdateCallback.onPeerLeft(room, list);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onPeersConnected(@Nullable Room room, @NonNull List<String> list) {
        RoomStatusUpdateCallback roomStatusUpdateCallback = this.zzov;
        if (roomStatusUpdateCallback != null) {
            roomStatusUpdateCallback.onPeersConnected(room, list);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onPeersDisconnected(@Nullable Room room, @NonNull List<String> list) {
        RoomStatusUpdateCallback roomStatusUpdateCallback = this.zzov;
        if (roomStatusUpdateCallback != null) {
            roomStatusUpdateCallback.onPeersDisconnected(room, list);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.OnRealTimeMessageReceivedListener, com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener
    public final void onRealTimeMessageReceived(@NonNull RealTimeMessage realTimeMessage) {
        OnRealTimeMessageReceivedListener onRealTimeMessageReceivedListener = this.zzpf;
        if (onRealTimeMessageReceivedListener != null) {
            onRealTimeMessageReceivedListener.onRealTimeMessageReceived(realTimeMessage);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onRoomAutoMatching(@Nullable Room room) {
        RoomStatusUpdateCallback roomStatusUpdateCallback = this.zzov;
        if (roomStatusUpdateCallback != null) {
            roomStatusUpdateCallback.onRoomAutoMatching(room);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener
    public final void onRoomConnected(int i, @Nullable Room room) {
        RoomUpdateCallback roomUpdateCallback = this.zzou;
        if (roomUpdateCallback != null) {
            roomUpdateCallback.onRoomConnected(i, room);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener
    public final void onRoomConnecting(@Nullable Room room) {
        RoomStatusUpdateCallback roomStatusUpdateCallback = this.zzov;
        if (roomStatusUpdateCallback != null) {
            roomStatusUpdateCallback.onRoomConnecting(room);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener
    public final void onRoomCreated(int i, @Nullable Room room) {
        RoomUpdateCallback roomUpdateCallback = this.zzou;
        if (roomUpdateCallback != null) {
            roomUpdateCallback.onRoomCreated(i, room);
        }
    }
}
