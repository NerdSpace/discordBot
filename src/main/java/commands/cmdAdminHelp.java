package commands;

import core.permsCore;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class cmdAdminHelp implements Command {
    @Override
    public boolean called(String[] agrs, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] agrs, MessageReceivedEvent event) {

        if(permsCore.check(event)) { return; }

        event.getTextChannel().sendMessage("The magic command was executed!").queue();

    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        System.out.println("[INFO] Command 'adminhelp' was executed");
    }

    @Override
    public String help() {
        return null;
    }
}
