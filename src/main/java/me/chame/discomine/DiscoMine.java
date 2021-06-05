package me.chame.discomine;

import me.chame.discomine.utils.ConfigFile;
import me.chame.discomine.discord.Listener;
import me.chame.discomine.minecraft.MixinAdapter;
import net.fabricmc.api.DedicatedServerModInitializer ;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;

/**
 * Main entrypoint for DiscoMine when running on a dedicated server.
 */

public class DiscoMine implements DedicatedServerModInitializer {
    private static JDA jda;
    private static ConfigFile config = new ConfigFile();
    private static MixinAdapter asyncadapter = new MixinAdapter();
    
    @Override
    public void onInitializeServer() {
        if (initialize()){

        }
    }

    //prolly unused
    public static JDA getJda(){
        return jda;
    }
    
    public static ConfigFile getConfigFile(){
        return config;
    }
    
    private static boolean initialize() {
        try {
            System.out.println("DiscoMine: Attempting to login...");

            JDABuilder builder = JDABuilder.createLight(config.getProperty("botToken"));
            builder.setActivity(Activity.playing("Minecraft"));
            builder.addEventListeners(new Listener(config.getProperty("trigger").replaceAll("\\s+", ""), config.getProperty("channelId"), asyncadapter));

            jda = builder.build().awaitReady();

            System.out.println("DiscoMine: Logged in.");
            return true;
        } catch (LoginException | IllegalArgumentException e) {
            System.out.println("DiscoMine: The provided Discord bot token is incorrect.");
        } catch (InterruptedException x){
            System.out.println("DiscoMine: The login process was interrupted.");
        } catch (IllegalStateException s){
            System.out.println("DiscoMine: The plugin has shutdown while trying to login.");
        }

        System.out.println("DiscoMine: Aborting...");
        return false;
    } 
}