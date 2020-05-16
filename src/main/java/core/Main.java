package core;

import commands.*;
import listeners.*;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import util.BotToken;

import javax.security.auth.login.LoginException;

public class Main {

    public static JDABuilder builder;

    public static void main(String[] Args) {

        builder = new JDABuilder(AccountType.BOT);

        builder.setToken(BotToken.TOKEN);
        builder.setAutoReconnect(true);

        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.listening("to you in #spam"));

        addCommands();
        addListeners();


        // start of the discord bot

        try {
            JDA jda = builder.build();
            jda.addEventListener(new Reaction_Listener());
        } catch (LoginException e) {
            e.printStackTrace();
        }

    }

    public static void addCommands() {

        commandHandler.commands.put("wumpus".toLowerCase(), new cmdWumpus());
        commandHandler.commands.put("invite".toLowerCase(), new cmdInvite());
        commandHandler.commands.put("adminhelp".toLowerCase(), new cmdAdminHelp());
        commandHandler.commands.put("clear".toLowerCase(), new cmdClear());
        commandHandler.commands.put("vote".toLowerCase(), new Vote());
        commandHandler.commands.put("dev".toLowerCase(), new cmdDev());
        commandHandler.commands.put("help".toLowerCase(), new Help_Command());
        commandHandler.commands.put("mute".toLowerCase(), new Mute_Command());
        commandHandler.commands.put("meme".toLowerCase(), new Meme_Command());

        System.out.println("[INFO] Commands has been registerd");

    }

    public static void addListeners() {

        builder.addEventListeners(new commandListener());
        builder.addEventListeners(new readyListener());
        builder.addEventListeners(new GuildMessageReceived_Hi_Listener());
        builder.addEventListeners(new Join_Event());
        builder.addEventListeners(new Reaction_Listener());

        System.out.println("[INFO] Listener has been registerd");

    }

}