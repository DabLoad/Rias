package com.donek.projectRias.commands.Music;

import com.donek.projectRias.LavaPlayer.GuildMusicManager;
import com.donek.projectRias.LavaPlayer.PlayerManager;
import com.donek.projectRias.listeners.EventListener;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class QueueCommand extends EventListener {
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if (event.getName().equals("queue")) {
            event.deferReply().setEphemeral(false).queue();
            EmbedBuilder embed = new EmbedBuilder();
            final TextChannel textChannel = event.getChannel().asTextChannel();
            final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
            final BlockingQueue<AudioTrack> queue = musicManager.scheduler.queue;

            if (queue.isEmpty()){
                event.getHook().sendMessage("Очередь пуста").queue();
            }

            final int trackCount = Math.min(queue.size(), 10);
            final List<AudioTrack> trackList = new ArrayList<>(queue);
            embed.setTitle("Очередь треков:");
            embed.setColor(5446170);

            for (int i = 0; i < trackCount; i++) {
                final AudioTrack track = trackList.get(i);
                final AudioTrackInfo info = track.getInfo();
                embed.addField("#" + String.valueOf(i+1), "`" + info.title + "` by `" + info.author + "` [`" + formatTime(track.getDuration()) + "`]", false);
            }
            if (trackList.size() > trackCount) {
                embed.setFooter("+ еще " + String.valueOf(trackList.size() - trackCount));
            }
            event.getHook().sendMessageEmbeds(embed.build()).queue();

        }
    }
    private String formatTime(long timeInMillis) {
        final long hours = timeInMillis / TimeUnit.HOURS.toMillis(1);
        final long minutes = timeInMillis / TimeUnit.MINUTES.toMillis(1);
        final long seconds = timeInMillis % TimeUnit.MINUTES.toMillis(1) / TimeUnit.SECONDS.toMillis(1);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

}
