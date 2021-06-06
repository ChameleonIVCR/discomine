package me.chame.discomine.discord;

import me.chame.discomine.minecraft.MCServer;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.concurrent.Callable;
import java.util.List;

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
    public Boolean call() {
        switch (command) {

            case "list":
                List<ServerPlayerEntity> players = this.server.getMinecraftServer().getPlayerManager().getPlayerList();

                if (players.isEmpty()) {
                    this.discordParameters.sendMessage("There's no one online.");
                }

                else {

                    StringBuilder playerList = new StringBuilder("Players online:\n");

                    for (ServerPlayerEntity playerEntity : players) {
                        playerList.append(playerEntity.getName().getString().replaceAll("§[b0931825467adcfeklmnor]", "").replaceAll("([_`~*>|])", "\\\\$1")).append("\n");
                    }

                    System.out.println(playerList.toString().trim());

                    this.discordParameters.sendMessage(playerList.toString().trim());

                    System.out.println("Sent");

                    break;
                }
        }
        return true;
    }
}
