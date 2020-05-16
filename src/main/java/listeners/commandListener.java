package listeners;

import core.commandHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import util.STATIC;

import java.io.IOException;
import java.text.ParseException;

public class commandListener extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {

        if(event.getMessage().getContentRaw().startsWith(STATIC.prefix) && !event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) {
            try {
                commandHandler.handleCommand(commandHandler.parse.parse(event.getMessage().getContentRaw(), event));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

}
