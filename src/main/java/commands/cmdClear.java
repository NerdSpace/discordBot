package commands;

import core.permsCore;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class cmdClear implements Command {

    EmbedBuilder error = new EmbedBuilder().setColor(Color.RED);

    private int getInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public boolean called(String[] agrs, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] agrs, MessageReceivedEvent event) {
        if(permsCore.check(event))
            return;

        int numb = getInt(agrs[0]);

        if(agrs.length < 1) {
            event.getTextChannel().sendMessage(
                error.setDescription(":warning: Please enter a number of messages you want to delete!").build()
            ).queue();
        }

        if (numb > 1 && numb <= 100) {

            try {

                MessageHistory history = new MessageHistory(event.getTextChannel());
                List<Message> msgs;

                event.getMessage().delete().queue();

                msgs = history.retrievePast(numb).complete();
                event.getTextChannel().deleteMessages(msgs).queue();

                Message msg = event.getTextChannel().sendMessage(
                        new EmbedBuilder().setColor(Color.GREEN).setDescription(":white_check_mark: Deleted " + agrs[0] + " messages!").build()
                ).complete();

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        msg.delete().queue();
                    }
                }, 7500);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            event.getTextChannel().sendMessage(
                    error.setDescription("Please enter a number of messages between 2 and 100!").build()
            ).queue();
        }

    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        System.out.println("[INFO] Command 'clear' was executed");
    }

    @Override
    public String help() {
        return null;
    }
}
