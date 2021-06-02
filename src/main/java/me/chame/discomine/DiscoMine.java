package me.chame.discomine;

import net.fabricmc.api.ModInitializer;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;


public class DiscoMine implements ModInitializer {
    private static JDA jda;
    
    @Override
    public void onInitialize() {
        try {
            jda = JDABuilder.createDefault("token").build();
        } catch (Exception e) {
            
        }
    }

    public static JDA getJda(){
        return jda;
    }
}