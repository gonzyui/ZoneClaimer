package me.gonzyui.zoneclaimer.utils;

import me.gonzyui.zoneclaimer.Main;

public class NotLandOwnerException extends Exception {

    private ChunkInfo chunkInfo;

    public NotLandOwnerException(ChunkInfo chunkInfo) {
        super(Main.getINSTANCE().getConfig().getString("messages.dont-own-chunk").replace("&", "§"));
        this.chunkInfo = chunkInfo;
    }

    public ChunkInfo getChunkInfo() {
        return chunkInfo;
    }
}

