package listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildMessageReceived_Hi_Listener extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equalsIgnoreCase("hi") || event.getMessage().getContentRaw().equalsIgnoreCase("hey") || event.getMessage().getContentRaw().equalsIgnoreCase("Moin") || event.getMessage().getContentRaw().equalsIgnoreCase("Moino")) {
            event.getMessage().addReaction("👋").queue();
        }
    }

}
