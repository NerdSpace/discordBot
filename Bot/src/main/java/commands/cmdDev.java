package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;

public class cmdDev implements Command {
    @Override
    public boolean called(String[] agrs, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] agrs, MessageReceivedEvent event) throws ParseException, IOException {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setTitle("**Developer**", "https://twitter.com/ExceptionAPI");
        eb.setDescription("This bot was developed by ExceptionAPI");


        eb.setColor(Color.CYAN);
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
