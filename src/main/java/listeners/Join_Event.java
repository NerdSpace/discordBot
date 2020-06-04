package listeners;

import handlers.MySQL_Handler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Random;


public class Join_Event extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        try {
            embedCreator(Color.CYAN, event.getMember(), event.getGuild());

            Guild guild = event.getGuild();
            guild.getTextChannelsByName("┃welcome", true);
            event.getGuild().modifyMemberRoles(event.getMember(), event.getGuild().getRolesByName("Member", true)).complete();

            MySQL_Handler.createEntry(event.getUser().getId());

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    public static void embedCreator(Color color, Member member, Guild guild) {
        String[] messages = {
                "{member} joined. You must construct additional pylons.",
                "Never gonna give {member} up. Never let {member} down!",
                "Hey! Listen! {member} has joined!",
                "Ha! {member} has joined! You activated my trap card!",
                "We've been expecting you, {member}.",
                "It's dangerous to go alone, take {member}!",
                "Swoooosh. {member} just landed.",
                "Brace yourselves. {member} just joined the server.",
                "A wild {member} appeared.",
                "{member} did you bring popcorn?",
                "Wakeup! {member} is joined!"
        };

        Random random = new Random();
        int randomNumber = random.nextInt(messages.length);

        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setColor(color);
        embedBuilder.setDescription(messages[randomNumber].replace("{member}", member.getAsMention()));

        guild.getDefaultChannel().sendMessage(embedBuilder.build()).queue();
    }

}
