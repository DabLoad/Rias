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
                        embed.setDescription(event.getUser().getAsMention() + " обнял(а) " + event.getOption("emote_mention").getAsUser().getAsMention());
                        embed.setImage(gifs.hugGif());
                    } else {
                        embed.setDescription(event.getUser().getAsMention() + " обнял(а) сам(а) себя.");
                        embed.setImage(gifs.hugGif());
                    }

                    break;
                case ("poke"):
                    if (event.getOption("emote_mention") != null && event.getUser().getIdLong() != event.getOption("emote_mention").getAsUser().getIdLong()) {
                        embed.setDescription(event.getUser().getAsMention() + " тыкнул(а) " + event.getOption("emote_mention").getAsUser().getAsMention());
                        embed.setImage(gifs.pokeGif());
                    } else {
                        embed.setDescription(event.getUser().getAsMention() + " тыкнул(а) сам(а) себя.");
                        embed.setImage(gifs.pokeGif());
                    }
                    break;
                case ("kill"):
                    if (event.getOption("emote_mention") != null && event.getUser().getIdLong() != event.getOption("emote_mention").getAsUser().getIdLong()){
                        embed.setDescription(event.getUser().getAsMention() + " убил(а) " + event.getOption("emote_mention").getAsUser().getAsMention());
                        embed.setImage(gifs.killGif());
                    } else {
                        embed.setDescription(event.getUser().getAsMention() + " убил(а) сам(а) себя.");
                        embed.setImage(gifs.killGif());
                    }
                    break;
                case ("kiss"):
                    if (event.getOption("emote_mention") != null && event.getUser().getIdLong() != event.getOption("emote_mention").getAsUser().getIdLong()){
                        embed.setDescription(event.getUser().getAsMention() + " поцеловал(а) " + event.getOption("emote_mention").getAsUser().getAsMention());
                        embed.setImage(gifs.kissGif());
                    } else {
                        embed.setDescription(event.getUser().getAsMention() + " поцеловал(а) сам(а) себя.");
                        embed.setImage(gifs.kissGif());
                    }

                    break;
                case ("lick"):
                    if (event.getOption("emote_mention") != null && event.getUser().getIdLong() != event.getOption("emote_mention").getAsUser().getIdLong()){
                        embed.setDescription(event.getUser().getAsMention() + " лизнул(а) " + event.getOption("emote_mention").getAsUser().getAsMention());
                        embed.setImage((gifs.lickGif()));
                    } else {
                        embed.setDescription(event.getUser().getAsMention() + " лизнул(а) сам(а) себя.");
                        embed.setImage((gifs.lickGif()));
                    }
                    break;
                case ("feed"):
                    if (event.getOption("emote_mention") != null && event.getUser().getIdLong() != event.getOption("emote_mention").getAsUser().getIdLong()){
                        embed.setDescription(event.getUser().getAsMention() + " покормил(а) " + event.getOption("emote_mention").getAsUser().getAsMention());
                        embed.setImage(gifs.feedGif());
                    } else {
                        embed.setDescription(event.getUser().getAsMention() + " покормил(а) сам(а) себя.");
                        embed.setImage(gifs.feedGif());
                    }
                    break;
                case ("pat"):
                    if (event.getOption("emote_mention") != null && event.getUser().getIdLong() != event.getOption("emote_mention").getAsUser().getIdLong()){
                        embed.setDescription(event.getUser().getAsMention() + " погладил(а) " + event.getOption("emote_mention").getAsUser().getAsMention());
                        embed.setImage(gifs.patGif());
                    } else {
                        embed.setDescription(event.getUser().getAsMention() + " погладил(а) сам(а) себя.");
                        embed.setImage(gifs.patGif());
                    }
                    break;
                case ("punch"):
                    if (event.getOption("emote_mention") != null && event.getUser().getIdLong() != event.getOption("emote_mention").getAsUser().getIdLong()){
                        embed.setDescription(event.getUser().getAsMention() + " ударил(а) " + event.getOption("emote_mention").getAsUser().getAsMention());
                        embed.setImage(gifs.punchGif());
                    } else {
                        embed.setDescription(event.getUser().getAsMention() + " ударил(а) сам(а) себя.");
                        embed.setImage(gifs.punchGif());
                    }
                    break;
                case ("tickle"):
                    if (event.getOption("emote_mention") != null && event.getUser().getIdLong() != event.getOption("emote_mention").getAsUser().getIdLong()){
                        embed.setDescription(event.getUser().getAsMention() + " тыкнул(а) " + event.getOption("emote_mention").getAsUser().getAsMention());
                        embed.setImage(gifs.tickleGif());
                    } else {
                        embed.setDescription(event.getUser().getAsMention() + " тыкнул(а) сам(а) себя.");
                        embed.setImage(gifs.tickleGif());
                    }
                    break;
                case ("cry"):
                    if (event.getOption("emote_mention") != null && event.getUser().getIdLong() != event.getOption("emote_mention").getAsUser().getIdLong()){
                        embed.setDescription(event.getUser().getAsMention() + " заплакал(а) из-за" + event.getOption("emote_mention").getAsUser().getAsMention());
                        embed.setImage(gifs.cryGif());
                    } else {
                        embed.setDescription(event.getUser().getAsMention() + " заплакал(а).");
                        embed.setImage(gifs.cryGif());
                    }
                    break;
                case ("smug"):
                    if (event.getOption("emote_mention") != null && event.getUser().getIdLong() != event.getOption("emote_mention").getAsUser().getIdLong()){
                        embed.setDescription(event.getUser().getAsMention() + " самодовольный(ая) из-за " + event.getOption("emote_mention").getAsUser().getAsMention());
                        embed.setImage(gifs.smugGif());
                    } else {
                        embed.setDescription(event.getUser().getAsMention() + " самодовольный.");
                        embed.setImage(gifs.smugGif());
                    }
                    break;
                case ("hi"):
                    if (event.getOption("emote_mention") != null && event.getUser().getIdLong() != event.getOption("emote_mention").getAsUser().getIdLong()){
                        embed.setDescription(String.format("%s поздоровался(ась) с !%s", event.getUser().getAsMention(), event.getOption("emote_mention").getAsUser().getAsMention()));
                        embed.setImage(gifs.hiGif());
                        //Embeds();
                    } else {
                        embed.setDescription(event.getUser().getAsMention() + " поздоровался(ась) со всеми.");
                        embed.setImage(gifs.hiGif());
                        //Embeds(embed.build());
                    }
                    break;
                default:
                    break;
            }
            event.replyEmbeds(embed.build()).queue();
        }

    }
}
