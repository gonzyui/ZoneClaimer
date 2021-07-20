package me.gonzyui.zoneclaimer.utils;

import me.gonzyui.zoneclaimer.Main;

public class LandNotClaimedException extends Exception {
    public LandNotClaimedException() {
        super(Main.getINSTANCE().getConfig().getString("messages.actual-chunk-not-claimed").replace("&", "§"));
    }
}
