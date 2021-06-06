package me.chame.discomine.discord;

import me.chame.discomine.minecraft.MCServer;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.concurrent.Callable;
import java.util.List;
import java.util.ArrayList;

/**
 * CommandExecutor handles all the calls to commands from Discord. A new one is
 * created per command execution, and should go out of focus (be
 * trash-collected) after it is used in the runnable thread.
 */

public class CommandExecutor implements Callable<Boolean> {
    private final DiscordParameters discordParameters;
    private final String command;
    //MCServer could be static
    private final MCServer server;

    public CommandExecutor(String command, DiscordParameters discordData, MCServer server) {
        this.discordParameters = discordData;
        this.command = command;
        this.server = server;
    }

    @Override
    public Boolean call() throws Exception {
        switch (command) {
            //TODO:Optimize this and handle exception in case the player list is empty.
            case "list":
                StringBuilder playerlist = new StringBuilder();
                List<ServerPlayerEntity> list = new ArrayList<>();
                for (ServerPlayerEntity entity: this.server.getMinecraftServer().getPlayerManager().getPlayerList()){
                    list.add(entity);
                }
                for (ServerPlayerEntity playerEntity: list){
                    playerlist.append(playerEntity.getName().getString().replaceAll("§[b0931825467adcfeklmnor]", "").replaceAll("([_`~*>])", "\\\\$1")).append("\n");
                }
                if (playerlist.toString().endsWith("\n")) {
                    int a = playerlist.lastIndexOf("\n");
                    playerlist = new StringBuilder(playerlist.substring(0, a));
                }
                System.out.println(playerlist.toString());
                this.discordParameters.sendMessage(playerlist.toString());
                System.out.println("Sent");
                break;
        }
        return true;
    }
}
