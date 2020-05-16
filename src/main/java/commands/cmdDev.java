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

        eb.setTitle("**Developer**", "https://github.com/nerdspace");
        eb.setDescription("This bot was developed by the **NerdSpace Community** \n Founder of this *'organization'* is StackNeverFlow");

        eb.setThumbnail("https://cdn3.iconfinder.com/data/icons/roles-computer-it/128/front-end_developer-2-512.png");

        eb.setColor(Color.CYAN);

        event.getTextChannel().sendMessage(eb.build()).queue();
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
