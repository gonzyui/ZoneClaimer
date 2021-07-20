package me.gonzyui.zoneclaimer.files;

import me.gonzyui.zoneclaimer.Main;
import me.gonzyui.zoneclaimer.utils.ChunkInfo;
import me.gonzyui.zoneclaimer.utils.LandAlreadyClaimedException;
import me.gonzyui.zoneclaimer.utils.LandNotClaimedException;
import me.gonzyui.zoneclaimer.utils.NotLandOwnerException;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class Data {
    private static Main plugin;
    private static File file;
    private static YamlConfiguration config;

    public static void setup(Main plugin) {
        Data.plugin = plugin;

        file = new File(plugin.getDataFolder(), "data.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
        save();

        plugin.getLogger().info("data.yml has been loaded!");
    }

    public static void addClaim(Chunk chunk, UUID owner) throws LandAlreadyClaimedException {
        int x = chunk.getX();
        int z = chunk.getZ();
        World world = chunk.getWorld();

        if (claimExists(x, z, world)) {
            throw new LandAlreadyClaimedException(getChunkInfo(x, z, world));
        }

        String id = generateId();
        config.set(id + ".x", x);
        config.set(id + ".z", z);
        config.set(id + ".world", world.getName());
        config.set(id + ".owner", owner.toString());
        save();
    }

    public static void removeClaim(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot pass a null ID into this method.");
        }
        config.set(id, null);
        save();
    }

    public static void removeClaim(Chunk chunk, UUID uuid) throws LandNotClaimedException, NotLandOwnerException {
        ChunkInfo cInfo = getChunkInfo(chunk.getX(), chunk.getZ(), chunk.getWorld());

        if (cInfo == null) {
            throw new LandNotClaimedException();
        }

        if (!cInfo.getOwner().equals(uuid)) {
            throw new NotLandOwnerException(cInfo);
        }

        removeClaim(cInfo.getId());
    }

    @Nullable
    public static String getChunkId(int x, int z, World world) {
        ChunkInfo info = getChunkInfo(x, z, world);
        return (info != null) ? info.getId() : null;
    }

    @Nullable
    public static ChunkInfo getChunkInfo(int x, int z, World world) {
        Set<String> keySet = config.getKeys(false);

        for (String s : keySet) {
            if (config.getInt(s + ".x") == x && config.getInt(s + ".z") == z && config.getString(s + ".world").equals(world.getName())) {
                return new ChunkInfo(s, x, z, world, UUID.fromString(config.getString(s + ".owner")));
            }
        }

        return null;
    }

    @Nullable
    public static ChunkInfo getChunkInfo(Chunk chunk) {
        return getChunkInfo(chunk.getX(), chunk.getZ(), chunk.getWorld());
    }

    public static boolean claimExists(int x, int z, World world) {
        Set<String> keySet = config.getKeys(false);

        for (String s : keySet) {
            if (config.getInt(s + ".x") == x && config.getInt(s + ".z") == z && config.getString(s + ".world").equals(world.getName())) {
                return true;
            }
        }

        return false;
    }

    private static String generateId() {
        int l = 65;
        int r = 90;
        int len = 7;
        Random random = new Random();
        String genString;

        do {
            genString = random.ints(l, r + 1)
                    .limit(len)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
        } while (idExists(genString));

        return genString;
    }

    private static boolean idExists(String id) {
        if (id == null) {
            return true;
        }

        return config.getKeys(false).contains(id);
    }

    private static void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
