package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;


public class cmdInvite implements Command {
    @Override
    public boolean called(String[] agrs, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] agrs, MessageReceivedEvent event) {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setTitle("**Invite**");
        eb.setColor(Color.BLUE);


        eb.setThumbnail("https://d.newsweek.com/en/full/1323071/discord-furry-moderator-abuse-image-quackityhd.png?w=1600&h=1600&q=88&f=f1df672f2c3eeb15e90ecebda32f6c6c");
        eb.addField("https://discord.gg/DMEEChw", "Das ist der Discord Invite Link zu diesem Server" ,true);

        event.getTextChannel().sendMessage(eb.build()).queue();
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

        System.out.println("[INFO] Command 'invite' was executed");

    }

    @Override
    public String help() {
        return null;
    }
}
