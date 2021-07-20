package me.gonzyui.zoneclaimer.utils;

import org.bukkit.World;

import java.util.UUID;

public class ChunkInfo {
    private String id;
    private int x;
    private int z;
    private World world;
    private UUID owner;

    public ChunkInfo(String id, int x, int z, World world, UUID owner) {
        this.id = id;
        this.x = x;
        this.z = z;
        this.world = world;
        this.owner = owner;
    }

    public String getId() { return id; }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public World getWorld() {
        return world;
    }

    public UUID getOwner() {
        return owner;
    }

}
