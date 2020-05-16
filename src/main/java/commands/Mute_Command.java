package commands;

import core.permsCore;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;

public class Mute_Command implements Command {

    private String moderator;
    private static TextChannel channel;

    private Member getCreator(Guild guild) {
        return guild.getMember(guild.getJDA().getUserById(moderator));
    }


    @Override
    public boolean called(String[] agrs, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] agrs, MessageReceivedEvent event) throws ParseException, IOException {
        channel = event.getTextChannel();

        if (agrs.length < 1) {
            message(help(), Color.RED);
            return;
        }


        switch (agrs[0]) {

            case "mute":
                if(permsCore.check(event)) return;

                break;

            case "unmute":
                if(permsCore.check(event)) return;
                break;

            case "info":
                if(permsCore.check(event)) return;
                break;
        }
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }

    // For the eb on wrong usage

    @Override
    public String help() {
        return "Use **-adminhelp** to see the right syntax";
    }

    private void message(String help, Color color) {
        EmbedBuilder eb = new EmbedBuilder().setDescription(help).setColor(color);
        channel.sendMessage(eb.build()).queue();
    }

    private void mute_player() {

    }
}
