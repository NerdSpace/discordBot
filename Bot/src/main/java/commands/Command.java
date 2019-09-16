package commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.text.ParseException;

public interface Command {

    boolean called(String[] agrs, MessageReceivedEvent event);

    void action(String[] agrs, MessageReceivedEvent event) throws ParseException, IOException;

    void executed(boolean sucess, MessageReceivedEvent event);

    String help();

}
