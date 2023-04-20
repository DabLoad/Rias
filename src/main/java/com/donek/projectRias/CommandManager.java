package com.donek.projectRias;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;


public class CommandManager extends ListenerAdapter {
    private EmbedBuilder embed;
    @Override
    public void onReady(ReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();

        commandData.add(Commands.slash("ping", "Ping."));

        commandData.add(Commands.slash("info", "Bot information + commands"));

        OptionData youtubeUrl = new OptionData(OptionType.STRING, "track", "Track name or url.", true);
        commandData.add(Commands.slash("play", "Play command").addOptions(youtubeUrl));

        commandData.add(Commands.slash("stop", "Stops music playing"));

        OptionData skipCount = new OptionData(OptionType.INTEGER, "skip_count", "count", false);
        commandData.add(Commands.slash("skip", "Track skip").addOptions(skipCount));

        commandData.add(Commands.slash("nowplaying", "Now playing"));

        commandData.add(Commands.slash("queue", "Track queue"));

        commandData.add(Commands.slash("repeat", "Loop mode"));

        OptionData emoteType = new OptionData(OptionType.STRING, "emotion_type", "Emotion", true)
                .addChoice("Hi", "hi")
                .addChoice("Hug", "hug")
                .addChoice("Poke", "poke")
                .addChoice("Kill", "kill")
                .addChoice("Kiss", "kiss")
                .addChoice("Lick", "lick")
                .addChoice("Feed", "feed")
                .addChoice("Pat", "pat")
                .addChoice("Punch", "punch")
                .addChoice("Tickle", "tickle")
                .addChoice("Cry", "cry")
                .addChoice("Smug", "smug");
        OptionData emoteMention = new OptionData(OptionType.USER, "emote_mention", "Mention user", false);
        commandData.add(Commands.slash("emote", "Reaction command").addOptions(emoteType, emoteMention));

        event.getJDA().updateCommands().addCommands(commandData).queue();
    }
}
