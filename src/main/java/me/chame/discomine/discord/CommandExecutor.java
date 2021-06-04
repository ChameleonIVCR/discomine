package me.chame.discomine.discord;

import me.chame.discomine.discord.DiscordParameters;

import java.util.concurrent.Callable;

/**
 * CommandExecutor handles all the calls to commands from Discord. A new one is created
 * per command execution, and should go out of focus after it is used in the runnable
 * thread.
 */

public class CommandExecutor implements Callable<Boolean> {
    private final DiscordParameters discordParameters;
    private final String command;

    public CommandExecutor(String command, DiscordParameters discordData){
        this.discordParameters = discordData;
        this.command = command;
    }

    @Override
    public Boolean call() throws Exception{
        switch (command){
            case "list":
                return true;
        }
        return false;
    }
}
