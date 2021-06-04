package me.chame.discomine.discord;

import me.chame.discomine.DiscoMine;
import me.chame.discomine.discord.DiscordParameters;
import me.chame.discomine.discord.CommandExecutor;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.*;

import java.util.List;
import java.util.ArrayList;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * The Listener class extends the ListenerAdapter from JDA and is initialized at
 * bot login, here we set the triggers and channel to listen to in Discord. The
 * CommandExecutor class is also initialized here. TODO: Support more channels?
 * TODO: Edit webhooks when possible to avoid flooding.
 */

public class Listener extends ListenerAdapter {
    // Trigger
    private final String trigger;
    // Channel to listen to
    private final String channelListenId;
    // Commands to listen to
    private List<String> commandList;
    // Concurrent Executor service. Do we need this?. Using two threads max.
    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    public Listener(String trigger, String channelListenId) {
        this.channelListenId = channelListenId;
        this.trigger = trigger;
        // Commands to listen to, these should be updated at CommandExecutor too.
        final String[] commandArray = { "list", "ping", "uptime", "motd", "ip" };

        this.commandList = new ArrayList<>();
        for (String command : commandArray) {
            this.commandList.add(trigger + command);
        }
    }

    // On message received first process command triggers, if nothing matches, send
    // to game.
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // If message is from self, null, deleted author, or is not in the selected
        // channel to listen to, return.
        if (event.getMember() == null || event.getAuthor().equals(DiscoMine.getJda().getSelfUser())
                || event.getChannel().getId() != channelListenId)
            return;

        Message msg = event.getMessage();
        String msgFirstWord = getFirstWord(msg.getContentRaw().toLowerCase());

        for (String command : this.commandList) {
            if (msgFirstWord.equals(command)) {
                FutureTask<Boolean> task = new FutureTask<>(new CommandExecutor(command.replace(this.trigger, ""), new DiscordParameters(msg, event.getChannel(), event.getGuild(), event.getMember())));

                executor.execute(task);
                return;
            }
        }

        // Log the messages to console for debug purposes.
        if (event.isFromType(ChannelType.PRIVATE)) {
            System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(), event.getMessage().getContentDisplay());
        } else {
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(), event.getTextChannel().getName(),
                    event.getMember().getEffectiveName(), event.getMessage().getContentDisplay());
        }
    }

    // Quite literally gets the first word of a message, trying to use as low memory
    // as possible.
    private String getFirstWord(String text) {
        int index = text.indexOf(' ');
        if (index > -1) {
            return text.substring(0, index).trim();
        } else {
            return text;
        }
    }
}