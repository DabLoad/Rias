package com.donek.projectRias.LavaPlayer;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerManager extends ListenerAdapter {
    private static PlayerManager INSTANCE;

    private final Map<Long, GuildMusicManager> musicManagers;
    private final AudioPlayerManager audioPlayerManager;

    public PlayerManager() {
        this.musicManagers = new HashMap<>();
        this.audioPlayerManager = new DefaultAudioPlayerManager();

        AudioSourceManagers.registerRemoteSources(this.audioPlayerManager);
        AudioSourceManagers.registerLocalSource(this.audioPlayerManager);
    }

    public GuildMusicManager getMusicManager(Guild guild) {
        return this.musicManagers.computeIfAbsent(guild.getIdLong(), (guildId) -> {
            final GuildMusicManager guildMusicManager = new GuildMusicManager(this.audioPlayerManager);

            guild.getAudioManager().setSendingHandler(guildMusicManager.getSendHandler());

            return guildMusicManager;
        });
    }
    public void loadAndPlay(TextChannel channel, String trackUrl, boolean isTextSearch, SlashCommandInteractionEvent event){
        final GuildMusicManager musicManager = this.getMusicManager(channel.getGuild());
        this.audioPlayerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                musicManager.scheduler.queue(track);

                event.getHook().sendMessage("The track was added to the queue: `"
                        + track.getInfo().title
                        + "` by `"
                        + track.getInfo().author
                        + "`")
                        .queue();

            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                if (!isTextSearch) {
                    final List<AudioTrack> tracks = playlist.getTracks();
                    for (final AudioTrack track: tracks) {
                        musicManager.scheduler.queue(track);
                    }
                    event.getHook().sendMessage("To the queue was added - `"
                                    + String.valueOf(tracks.size())
                                    + "` tracks from playlist: `"
                                    + playlist.getName()
                                    + "`")
                            .queue();
                } else {
                    final List<AudioTrack> tracks = playlist.getTracks();
                    AudioTrack track = tracks.get(0);
                    musicManager.scheduler.queue(track);
                    event.getHook().sendMessage("The track was added to the queue: `"
                                    + track.getInfo().title
                                    + "` by `"
                                    + track.getInfo().author
                                    + "`")
                            .queue();
                }

            }

            @Override
            public void noMatches() {
                event.getHook().sendMessage(("Nothing founded")).queue();
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                event.getHook().sendMessage(("Error")).queue();
            }
        });
    }
    public static PlayerManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerManager();
        }

        return INSTANCE;
    }

}