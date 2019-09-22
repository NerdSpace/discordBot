package core;

import commands.*;
import listeners.commandListener;
import listeners.readyListener;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import util.BotToken;

import javax.security.auth.login.LoginException;

public class Main {

    static JDABuilder builder;

    public static void main(String[] Args) {

        builder = new JDABuilder(AccountType.BOT);

        builder.setToken(BotToken.TOKEN);
        builder.setAutoReconnect(true);

        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.listening("to you in #spam"));

        addListeners();
        addCommands();

        // start of the discord bot

        try {
            JDA jda = builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }

    }

    public static void addCommands() {

        commandHandler.commands.put("wumpus", new cmdWumpus());
        commandHandler.commands.put("invite", new cmdInvite());
        commandHandler.commands.put("adminhelp", new cmdAdminHelp());
        commandHandler.commands.put("clear", new cmdClear());
        commandHandler.commands.put("vote", new Vote());
        commandHandler.commands.put("dev", new cmdDev());

    }

    public static void addListeners() {

        builder.addEventListeners(new commandListener());
        builder.addEventListeners(new readyListener());

    }


}