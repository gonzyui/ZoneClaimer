package me.gonzyui.zoneclaimer.utils;

import me.gonzyui.zoneclaimer.Main;

public class LandAlreadyClaimedException extends Exception {
    private ChunkInfo chunkInfo;

    public LandAlreadyClaimedException(ChunkInfo chunkInfo) {
        super(Main.getINSTANCE().getConfig().getString("messages.claim-already-exists").replace("&", "§"));
        this.chunkInfo = chunkInfo;
    }

    public ChunkInfo getChunkInfo() {
        return chunkInfo;
    }
}

