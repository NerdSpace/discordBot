package listeners;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class Reaction_Listener extends ListenerAdapter {

    @Override
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
        if (event.getChannel().getId().equals("710835815218937886")) {
            Role role = event.getGuild().getRoleById("710837093257052201");
            event.getGuild().addRoleToMember(event.getMember(), role).queue();
        }
    }

    @Override
    public void onGuildMessageReactionRemove(GuildMessageReactionRemoveEvent event) {
        if (event.getChannel().getId().equals("710835815218937886")) {
            Role role = event.getGuild().getRoleById("710837093257052201");
            event.getGuild().removeRoleFromMember(event.getMember(), role).queue();
        }
    }
}
