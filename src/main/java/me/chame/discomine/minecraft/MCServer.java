package me.chame.discomine.minecraft;

import net.minecraft.server.MinecraftServer;

import java.util.UUID;

/**
 * This is a convenience wrapper class around MinecraftServer, only one should
 * be created per initialization.
 */

public class MCServer {
    final private MinecraftServer minecraftServer;

    public MCServer(MinecraftServer minecraftServer) {
        this.minecraftServer = minecraftServer;
    }

    public MinecraftServer getMinecraftServer() {
        return minecraftServer;
    }

    public String getPlayerNamefromUUID(UUID playerId) {
        return this.minecraftServer.getPlayerManager().getPlayer(playerId).getPlayerListName().getString();
    }

    public String[] getPlayers() {
        return this.minecraftServer.getPlayerNames();
    }

    public String getServerIp() {
        return this.minecraftServer.getServerIp() + Integer.toString(this.minecraftServer.getServerPort());
    }

    public String getMaxPlayers() {
        return Integer.toString(this.minecraftServer.getMaxPlayerCount());
    }
}
