package com.donek.projectRias.commands.Music;

import com.donek.projectRias.LavaPlayer.GuildMusicManager;
import com.donek.projectRias.LavaPlayer.PlayerManager;
import com.donek.projectRias.listeners.EventListener;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class SkipCommand extends EventListener {
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("skip")){
            event.deferReply().setEphemeral(false).queue();

            final TextChannel channel = event.getChannel().asTextChannel();
            final Member self = event.getGuild().getSelfMember();
            final GuildVoiceState selfVoiceState = self.getVoiceState();


            if (!selfVoiceState.inAudioChannel()){
                event.getHook().sendMessage("I have to be on voice for it to work").queue();
            }

            final Member member = event.getMember();
            final GuildVoiceState memberVoiceState = member.getVoiceState();

            if (!memberVoiceState.inAudioChannel()){
                event.getHook().sendMessage("You have to be on voice for it to work").queue();
            }

            if (!memberVoiceState.getChannel().asVoiceChannel().equals(selfVoiceState.getChannel().asVoiceChannel())){
                event.getHook().sendMessage("You’re supposed to be on my voice channel for this to work").queue();
            }

            final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
            final AudioPlayer audioPlayer = musicManager.audioPlayer;

            if (audioPlayer == null){
                event.getHook().sendMessage("Nothing is playing now").queue();
            } else {
                if (event.getOption("skip_count") != null) {
                    int skipCount = event.getOption("skip_count").getAsInt();
                    for (int i = 0; i < skipCount; i++) {
                        musicManager.scheduler.nextTrack();
                    }
                    event.getHook().sendMessageFormat("Skipped").queue();
                }
                musicManager.scheduler.nextTrack();
                event.getHook().sendMessage("Skipped").queue();
            }
        }
    }
}
