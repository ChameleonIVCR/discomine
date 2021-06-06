package me.chame.discomine.discord;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

/**
 * This is a convenience wrapper class around JDA messages. It provides a quick
 * way of sending messages, and retrieving data from a message.
 */

public class DiscordParameters {
    private final Message msg;
    private final MessageChannel msgch;
    private final Guild guild;
    private final Member member;

    public DiscordParameters(Message msg, MessageChannel msgch, Guild guild, Member member) {
        this.msg = msg;
        this.msgch = msgch;
        this.guild = guild;
        this.member = member;
    }

    public Message getMessage() {
        return this.msg;
    }

    public MessageChannel getMessageChannel() {
        return this.msgch;
    }

    public Guild getGuild() {
        return this.guild;
    }

    public Member getMember() {
        return this.member;
    }

    public void sendMessage(String message) {
        msgch.sendMessage(message).complete();
    }

    public void sendMessage(Message message) {
        msgch.sendMessage(message).complete();
    }

    public void sendTyping(){
        msgch.sendTyping();
    }
}