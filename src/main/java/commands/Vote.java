package commands;

import core.permsCore;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.STATIC;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Vote implements Command, Serializable {

    private static TextChannel channel;

    private static HashMap<Guild, Poll> polls = new HashMap<>();

    private static final String[] EMOTI = {":one:", ":two:", ":three:", ":four:", ":five:", ":six:", ":seven:", ":eight:", ":nine:", ":keycap_ten:"};


    private class Poll implements Serializable {

        private String creator;
        private String heading;
        private List<String> answers;
        private HashMap<String, Integer> votes;

        private Poll(Member creator, String heading, List<String> answers) {
            this.creator = creator.getUser().getId();
            this.heading = heading;
            this.answers = answers;
            this.votes = new HashMap<>();
        }

        private Member getCreator(Guild guild) {
            return guild.getMember(guild.getJDA().getUserById(creator));
        }

    }


    private static void message(String content) {
        EmbedBuilder eb = new EmbedBuilder().setDescription(content);
        channel.sendMessage(eb.build()).queue();
    }

    private static void message(String content, Color color) {
        EmbedBuilder eb = new EmbedBuilder().setDescription(content).setColor(color);
        channel.sendMessage(eb.build()).queue();
    }

    private EmbedBuilder getParsedPoll(Poll poll, Guild guild) {

        StringBuilder ansSTR = new StringBuilder();
        final AtomicInteger count = new AtomicInteger();

        poll.answers.forEach(s -> {
            long votescount = poll.votes.keySet().stream().filter(k -> poll.votes.get(k).equals(count.get() + 1)).count();
            ansSTR.append(EMOTI[count.get()] + "  -  " + s + "  -  Votes: `" + votescount + "` \n");
            count.addAndGet(1);
        });

        return new EmbedBuilder()
                .setAuthor(poll.getCreator(guild).getEffectiveName() + "'s poll.", null, poll.getCreator(guild).getUser().getAvatarUrl())
                .setDescription(":pencil:   " + poll.heading + "\n\n" + ansSTR.toString())
                .setFooter("Enter '" + STATIC.prefix + "vote v <number>' to vote!", null)
                .setColor(Color.cyan);

    }

    private void createPoll(String[] agrs, MessageReceivedEvent event) {

        if (polls.containsKey(event.getGuild())) {
            message(":warning: There is already a vote running on this guild!", Color.red);
            return;
        }

        String argsSTRG = String.join(" ", new ArrayList<>(Arrays.asList(agrs).subList(1, agrs.length)));
        List<String> content = Arrays.asList(argsSTRG.split("\\|"));
        String heading = content.get(0);
        List<String> answers = new ArrayList<>(content.subList(1, content.size()));

        Poll poll = new Poll(event.getMember(), heading, answers);
        polls.put(event.getGuild(), poll);

        channel.sendMessage(getParsedPoll(poll, event.getGuild()).build()).queue();

    }

    private void votePoll(String[] agrs, MessageReceivedEvent event) {

        if (!polls.containsKey(event.getGuild())) {
            message("There is currently no poll running to vote for!", Color.red);
            return;
        }

        Poll poll = polls.get(event.getGuild());

        int vote;
        try {
            vote = Integer.parseInt(agrs[1]);
            if (vote > poll.answers.size())
                throw new Exception();
        } catch (Exception e) {
            message("Please enter a valid number to vote for!", Color.red);
            return;
        }

        if (poll.votes.containsKey(event.getAuthor().getId())) {
            message("Sorry, but you can only vote **once** for a poll!", Color.red);
            return;
        }

        poll.votes.put(event.getAuthor().getId(), vote);
        polls.replace(event.getGuild(), poll);
        event.getMessage().delete().queue();

    }

    private void voteStats(MessageReceivedEvent event) {

        if (!polls.containsKey(event.getGuild())) {
            message("There is currently no vote running!", Color.red);
            return;
        }
        channel.sendMessage(getParsedPoll(polls.get(event.getGuild()), event.getGuild()).build()).queue();

    }

    private void closeVote(MessageReceivedEvent event) {

        if (!polls.containsKey(event.getGuild())) {
            message("There is currently no vote running!", Color.red);
            return;
        }

        Guild g = event.getGuild();
        Poll poll = polls.get(g);

        if (!poll.getCreator(g).equals(event.getMember())) {
            message("Only the creator of the poll (" + poll.getCreator(g).getAsMention() + ") can close this poll!", Color.red);
            return;
        }

        polls.remove(g);
        channel.sendMessage(getParsedPoll(poll, g).build()).queue();
        message("Poll closed by " + event.getAuthor().getAsMention() + ".", new Color(0xFF7000));

    }

    private void savePoll(Guild guild) throws IOException {

        if (!polls.containsKey(guild))
            return;

        String saveFile = "SERVER_SETTINGS/" + guild.getId() + "/vote.dat";
        Poll poll = polls.get(guild);

        FileOutputStream fos = new FileOutputStream(saveFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(poll);
        oos.close();
    }

    private static Poll getPoll(Guild guild) throws IOException, ClassNotFoundException {

        if (polls.containsKey(guild))
            return null;

        String saveFile = "SERVER_SETTINGS/" + guild.getId() + "/vote.dat";

        FileInputStream fis = new FileInputStream(saveFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Poll out = (Poll) ois.readObject();
        ois.close();
        return out;
    }

    public static void loadPolls(JDA jda) {

        jda.getGuilds().forEach(g -> {

            File f = new File("SERVER_SETTINGS/" + g.getId() + "/vote.dat");
            if (f.exists())
                try {
                    polls.put(g, getPoll(g));
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

        });

    }


    @Override
    public boolean called(String[] agrs, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] agrs, MessageReceivedEvent event) {



            channel = event.getTextChannel();

            if (agrs.length < 1) {
                message(help(), Color.red);
                return;
            }

            switch (agrs[0]) {

                case "create":
                    if(permsCore.check(event)) return;
                    createPoll(agrs, event);
                    break;

                case "v":
                    votePoll(agrs, event);
                    break;

                case "stats":
                    if(permsCore.check(event)) return;
                    voteStats(event);
                    break;

                case "close":
                    if(permsCore.check(event)) return;
                    closeVote(event);
                    break;
            }

            polls.forEach((g, poll) -> {

                File path = new File("SERVER_SETTINGS/" + g.getId() + "/");
                if (!path.exists())
                    path.mkdirs();

                try {
                    savePoll(g);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        System.out.println("[INFO] Command 'vote' was executed");
    }

    @Override
    public String help() {
        return null;
    }
}
