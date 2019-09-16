package commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class cmdSay implements Command {
    @Override
    public boolean called(String[] agrs, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] agrs, MessageReceivedEvent event) {

    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        System.out.println("[INFO] Command 'say' was executed");
    }

    @Override
    public String help() {
        return null;
    }
}
