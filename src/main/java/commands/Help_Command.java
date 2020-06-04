package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public class Help_Command implements Command {

    @Override
    public boolean called(String[] agrs, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] agrs, MessageReceivedEvent event) {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setTitle("Help");

        eb.setDescription("**-invite** ┃ Get the invite link to the server" + "\n" +
                "**-vote** ┃ Vote_Command for a poll" + "\n" +
                "**-meme** ┃ See a funny meme" + "\n" +
                "**-dev** ┃ Show information about this bot/ developers");

        eb.setColor(Color.PINK);

        event.getTextChannel().sendMessage(eb.build()).queue();
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        System.out.println("[INFO] Command 'help' was executed");
    }

    @Override
    public String help() {
        return null;
    }
}

