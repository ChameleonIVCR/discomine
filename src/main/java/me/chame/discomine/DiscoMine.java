package me.chame.discomine;

import me.chame.discomine.utils.ConfigFile;

import net.fabricmc.api.DedicatedServerModInitializer ;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;


public class DiscoMine implements DedicatedServerModInitializer {
    private static JDA jda;
    private static ConfigFile config;
    
    @Override
    public void onInitializeServer() {
        initialize();
    }

    public static JDA getJda(){
        return jda;
    }
    
    public static ConfigFile getConfigFile(){
        return config;
    }
    
    private static void initialize() {
        config = new ConfigFile();
        try {
            jda = JDABuilder.createLight(config.getProperty("botToken")).build().awaitReady();
        } catch (LoginException e) {
            System.out.println("The provided");
        } catch (InterruptedException x){
            
        } catch (IllegalArgumentException i){

        } catch (IllegalStateException s){

        }
    } 
}