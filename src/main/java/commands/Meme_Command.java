package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Meme_Command implements Command {
    @Override
    public boolean called(String[] agrs, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] agrs, MessageReceivedEvent event) {
        JSONParser parser = new JSONParser();

        String postLink = "";
        String title = "";
        String url = "";
        String subreddit = "";

        try {
            URL memeURL = new URL("https://meme-api.herokuapp.com/gimme");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(memeURL.openConnection().getInputStream()));

            String lines;
            while ((lines = bufferedReader.readLine()) != null) {
                JSONArray array = new JSONArray();
                array.add(parser.parse(new InputStreamReader(memeURL.openConnection().getInputStream())));

                for (Object o : array) {
                    JSONObject jsonObject = (JSONObject) o;

                    postLink = (String) jsonObject.get("postLink");
                    url = (String) jsonObject.get("url");
                    title = (String) jsonObject.get("title");
                    subreddit = (String) jsonObject.get("subreddit");
                }
            }
            bufferedReader.close();

            event.getMessage().delete().queue();
            EmbedBuilder builder = new EmbedBuilder()
                    .setTitle(title, postLink)
                    .setImage(url)
                    .setFooter(subreddit)
                    .setColor(Color.YELLOW);
            event.getChannel().sendMessage(builder.build()).queue();

        } catch (Exception e) {
            e.printStackTrace();
            EmbedBuilder builder = new EmbedBuilder()
                    .setDescription(":warning: Sorry, " + event.getAuthor().getAsMention() + ", an error happend while excectuting this command")
                    .setColor(Color.RED);
            event.getChannel().sendMessage(builder.build()).queue();
        }
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        System.out.println("[INFO] Command 'meme' was executed");
    }

    @Override
    public String help() {
        return null;
    }
}
