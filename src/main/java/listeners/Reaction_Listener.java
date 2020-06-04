package listeners;

import handlers.MySQL_Handler;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Reaction_Listener extends ListenerAdapter {

    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
        if (event.getChannel().getId().equals("710835815218937886")) {
            Role role = event.getGuild().getRoleById("710837093257052201");
            event.getGuild().addRoleToMember(event.getMember(), role).queue();

            if (MySQL_Handler.getUser(event.getUser().getId()) == 2) { MySQL_Handler.createEntry(event.getUser().getId()); }

            if (MySQL_Handler.getUser(event.getUser().getId()) == 0) {
                event.getReaction().removeReaction(event.getUser());
                event.getGuild().removeRoleFromMember(event.getMember(), role);
            }
        }
    }

    public void onGuildMessageReactionRemove(GuildMessageReactionRemoveEvent event) {
        if (event.getChannel().getId().equals("710835815218937886")) {
            Role role = event.getGuild().getRoleById("710837093257052201");
            event.getGuild().removeRoleFromMember(event.getMember(), role).queue();

            MySQL_Handler.updateStatus(event.getUser().getId(), 0);
        }
    }
}
