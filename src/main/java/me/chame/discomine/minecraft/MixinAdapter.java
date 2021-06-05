package me.chame.discomine.minecraft;

public class MixinAdapter {
    private static boolean serverStarted = false;
    private static boolean serverStopping = false;
    private static boolean serverStopped = false;
    private static MCServer minecraftServer;

    public MixinAdapter(){

    }

    public static void setServerStarted(boolean serverStart){
        serverStarted = serverStart;
    }

    public boolean getServerStarted(){
        return serverStarted;
    }

    public static void setServerStopping(boolean serverStop){
        serverStopping = serverStop;
    }

    public boolean getServerStopping(){
        return serverStopping;
    }

    public static void setserverStopped(boolean serverFinalized){
        serverStopped = serverFinalized;
    }

    //Use this to kill JDA
    public boolean getServerStopped(){
        return serverStopped;
    }

    public static void setMinecraftServer(MCServer mcServer){
        minecraftServer = mcServer;
    }

    public MCServer getMinecraftServer(){
        return minecraftServer;
    }

    public boolean isReady(){
        if(serverStarted && !serverStopping){
            return true;
        }
        return false;
    }
}
