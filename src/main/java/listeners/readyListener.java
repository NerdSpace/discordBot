package listeners;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class readyListener extends ListenerAdapter {

    public void onReady(ReadyEvent event) {
        System.out.println("[INFO] The bot has connect to the server.");

        for (Guild g : event.getJDA().getGuilds()) {
            g.getTextChannelsByName("bot-updates", true).get(0).sendMessage("The Bot is up again. :partying_face:").queue();
        }
    }

}
