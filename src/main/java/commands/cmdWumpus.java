package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public class cmdWumpus implements Command {


    @Override
    public boolean called(String[] agrs, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] agrs, MessageReceivedEvent event) {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setImage("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fpbs.twimg.com%2Fmedia%2FDRGySzSUQAUk3de.jpg&f=1&nofb=1");

        eb.setTitle("Wumpus", "https://tenor.com/view/wumpus-sunglasses-gif-13250930");

        eb.setColor(Color.BLUE);

        event.getTextChannel().sendMessage(eb.build()).queue();
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        System.out.println("[INFO] Command 'wumpus' was executed");
    }

    @Override
    public String help() {
        return null;
    }
}
