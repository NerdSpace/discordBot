package core;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public class permsCore {

    public static boolean check(MessageReceivedEvent event) {

            if(event.getGuild().getMember(event.getAuthor()).isOwner())
                return false;
            else {
                EmbedBuilder eb = new EmbedBuilder();

                eb.setDescription(":warning: Sorry, " + event.getAuthor().getAsMention() + ", you don't have the permission to use command!");

                eb.setColor(Color.PINK);

                event.getTextChannel().sendMessage(eb.build()).queue();
                return true;
            }
    }

}
