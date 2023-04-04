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
                event.getHook().sendMessage("Я должна быть в голосовом канале, что-бы это сработало").queue();
            }

            final Member member = event.getMember();
            final GuildVoiceState memberVoiceState = member.getVoiceState();

            if (!memberVoiceState.inAudioChannel()){
                event.getHook().sendMessage("Ты должен быть в голосовом канале, что-бы это сработало").queue();
            }

            if (!memberVoiceState.getChannel().asVoiceChannel().equals(selfVoiceState.getChannel().asVoiceChannel())){
                event.getHook().sendMessage("Ты должен быть со мной в одном голосовом канале, что-бы это сработало").queue();
            }

            final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
            final AudioPlayer audioPlayer = musicManager.audioPlayer;

            if (audioPlayer == null){
                event.getHook().sendMessage("Сейчас ничего не проигрывается").queue();
            } else {
                if (event.getOption("skip_count") != null) {
                    int skipCount = event.getOption("skip_count").getAsInt();
                    for (int i = 0; i < skipCount; i++) {
                        musicManager.scheduler.nextTrack();
                    }
                    event.getHook().sendMessageFormat("Треки пропущены").queue();
                }
                musicManager.scheduler.nextTrack();
                event.getHook().sendMessage("Трек пропущен").queue();
            }
        }
    }
}
