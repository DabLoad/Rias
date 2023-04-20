package com.donek.projectRias.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.text.Format;
import java.util.ArrayList;
import java.util.List;

public class Reactions extends ListenerAdapter {
    private EmbedBuilder embed;
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (command.equals("emote")) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(5446170);
            OptionMapping option = event.getOption("emotion_type");
            String type = option.getAsString();
            switch (type.toLowerCase()){
                case ("hug"):
                    if (event.getOption("emote_mention") != null && event.getUser().getIdLong() != event.getOption("emote_mention").getAsUser().getIdLong()) {
                        embed.setDescription(event.getUser().getAsMention() + " hug " + event.getOption("emote_mention").getAsUser().getAsMention());
                        embed.setImage(gifs.hugGif());
                    } else {
                        embed.setDescription(event.getUser().getAsMention() + " hug itself.");
                        embed.setImage(gifs.hugGif());
                    }

                    break;
                case ("poke"):
                    if (event.getOption("emote_mention") != null && event.getUser().getIdLong() != event.getOption("emote_mention").getAsUser().getIdLong()) {
                        embed.setDescription(event.getUser().getAsMention() + " poke " + event.getOption("emote_mention").getAsUser().getAsMention());
                        embed.setImage(gifs.pokeGif());
                    } else {
                        embed.setDescription(event.getUser().getAsMention() + " poke itself.");
                        embed.setImage(gifs.pokeGif());
                    }
                    break;
                case ("kill"):
                    if (event.getOption("emote_mention") != null && event.getUser().getIdLong() != event.getOption("emote_mention").getAsUser().getIdLong()){
                        embed.setDescription(event.getUser().getAsMention() + " kill " + event.getOption("emote_mention").getAsUser().getAsMention());
                        embed.setImage(gifs.killGif());
                    } else {
                        embed.setDescription(event.getUser().getAsMention() + " kill itself.");
                        embed.setImage(gifs.killGif());
                    }
                    break;
                case ("kiss"):
                    if (event.getOption("emote_mention") != null && event.getUser().getIdLong() != event.getOption("emote_mention").getAsUser().getIdLong()){
                        embed.setDescription(event.getUser().getAsMention() + " kiss " + event.getOption("emote_mention").getAsUser().getAsMention());
                        embed.setImage(gifs.kissGif());
                    } else {
                        embed.setDescription(event.getUser().getAsMention() + " kiss itself.");
                        embed.setImage(gifs.kissGif());
                    }

                    break;
                case ("lick"):
                    if (event.getOption("emote_mention") != null && event.getUser().getIdLong() != event.getOption("emote_mention").getAsUser().getIdLong()){
                        embed.setDescription(event.getUser().getAsMention() + " lick " + event.getOption("emote_mention").getAsUser().getAsMention());
                        embed.setImage((gifs.lickGif()));
                    } else {
                        embed.setDescription(event.getUser().getAsMention() + " lick itself.");
                        embed.setImage((gifs.lickGif()));
                    }
                    break;
                case ("feed"):
                    if (event.getOption("emote_mention") != null && event.getUser().getIdLong() != event.getOption("emote_mention").getAsUser().getIdLong()){
                        embed.setDescription(event.getUser().getAsMention() + " feed " + event.getOption("emote_mention").getAsUser().getAsMention());
                        embed.setImage(gifs.feedGif());
                    } else {
                        embed.setDescription(event.getUser().getAsMention() + " feed itself.");
                        embed.setImage(gifs.feedGif());
                    }
                    break;
                case ("pat"):
                    if (event.getOption("emote_mention") != null && event.getUser().getIdLong() != event.getOption("emote_mention").getAsUser().getIdLong()){
                        embed.setDescription(event.getUser().getAsMention() + " pat " + event.getOption("emote_mention").getAsUser().getAsMention());
                        embed.setImage(gifs.patGif());
                    } else {
                        embed.setDescription(event.getUser().getAsMention() + " pat itself.");
                        embed.setImage(gifs.patGif());
                    }
                    break;
                case ("punch"):
                    if (event.getOption("emote_mention") != null && event.getUser().getIdLong() != event.getOption("emote_mention").getAsUser().getIdLong()){
                        embed.setDescription(event.getUser().getAsMention() + " punch " + event.getOption("emote_mention").getAsUser().getAsMention());
                        embed.setImage(gifs.punchGif());
                    } else {
                        embed.setDescription(event.getUser().getAsMention() + " punch itself.");
                        embed.setImage(gifs.punchGif());
                    }
                    break;
                case ("tickle"):
                    if (event.getOption("emote_mention") != null && event.getUser().getIdLong() != event.getOption("emote_mention").getAsUser().getIdLong()){
                        embed.setDescription(event.getUser().getAsMention() + " tickle " + event.getOption("emote_mention").getAsUser().getAsMention());
                        embed.setImage(gifs.tickleGif());
                    } else {
                        embed.setDescription(event.getUser().getAsMention() + " tickle itself.");
                        embed.setImage(gifs.tickleGif());
                    }
                    break;
                case ("cry"):
                    if (event.getOption("emote_mention") != null && event.getUser().getIdLong() != event.getOption("emote_mention").getAsUser().getIdLong()){
                        embed.setDescription(event.getUser().getAsMention() + " crying because of" + event.getOption("emote_mention").getAsUser().getAsMention());
                        embed.setImage(gifs.cryGif());
                    } else {
                        embed.setDescription(event.getUser().getAsMention() + " crying.");
                        embed.setImage(gifs.cryGif());
                    }
                    break;
                case ("smug"):
                    if (event.getOption("emote_mention") != null && event.getUser().getIdLong() != event.getOption("emote_mention").getAsUser().getIdLong()){
                        embed.setDescription(event.getUser().getAsMention() + " smug because of " + event.getOption("emote_mention").getAsUser().getAsMention());
                        embed.setImage(gifs.smugGif());
                    } else {
                        embed.setDescription(event.getUser().getAsMention() + " smug.");
                        embed.setImage(gifs.smugGif());
                    }
                    break;
                case ("hi"):
                    if (event.getOption("emote_mention") != null && event.getUser().getIdLong() != event.getOption("emote_mention").getAsUser().getIdLong()){
                        embed.setDescription(String.format("%s say hello to !%s", event.getUser().getAsMention(), event.getOption("emote_mention").getAsUser().getAsMention()));
                        embed.setImage(gifs.hiGif());
                    } else {
                        embed.setDescription(event.getUser().getAsMention() + " say hello to everyone.");
                        embed.setImage(gifs.hiGif());
                    }
                    break;
                default:
                    break;
            }
            event.replyEmbeds(embed.build()).queue();
        }

    }
}
