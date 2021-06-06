package me.chame.discomine.minecraft;

import me.chame.discomine.DiscoMine;

/**
 * The MixinAdapter provides a way of communication for the injected methods,
 * providing static setter methods and non-static getter methods. Another way to
 * do this is using Events or Observers. TODO: Try other ways of doing this.
 */

public class MixinAdapter {
    private static boolean serverStarted = false;
    private static boolean serverStopping = false;
    private static boolean serverStopped = false;
    private static MCServer minecraftServer;

    public MixinAdapter() {

    }

    public static void setServerStarted(boolean serverStart) {
        serverStarted = serverStart;
    }

    public boolean getServerStarted() {
        return serverStarted;
    }

    public static void setServerStopping(boolean serverStop) {
        serverStopping = serverStop;
        //Tell JDA to shutdown.
        DiscoMine.getJda().shutdownNow();
    }

    public boolean getServerStopping() {
        return serverStopping;
    }

    public static void setserverStopped(boolean serverFinalized) {
        serverStopped = serverFinalized;
    }

    public boolean getServerStopped() {
        return serverStopped;
    }

    public static void setMinecraftServer(MCServer mcServer) {
        minecraftServer = mcServer;
    }

    public MCServer getMinecraftServer() {
        return minecraftServer;
    }

    public boolean isReady() {
        if (serverStarted && !serverStopping) {
            return true;
        }
        return false;
    }
}
