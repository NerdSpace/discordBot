package listeners;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CTF_Apply_Reaction_Listener extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().startsWith("[apply]")) {
            event.getMessage().addReaction("✅").queue();
            event.getMessage().addReaction("❌").queue();
        }
    }

    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
        if (event.getUser().getId() == "393499260613820436") {

            Role applyRole = event.getGuild().getRoleById("710835815218937886");

            event.getGuild().removeRoleFromMember(event.getMember(), applyRole);
        } else {
            event.getReaction().removeReaction(event.getUser());
        }
    }
}
