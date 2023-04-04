package com.donek.projectRias.commands.Music;

import com.donek.projectRias.LavaPlayer.GuildMusicManager;
import com.donek.projectRias.LavaPlayer.PlayerManager;
import com.donek.projectRias.listeners.EventListener;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class LeaveCommand extends EventListener {
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("leave")) {
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

            final Guild guild = event.getGuild();
            final AudioManager audioManager = event.getGuild().getAudioManager();
            final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);
            musicManager.scheduler.repeating = false;
            musicManager.scheduler.queue.clear();
            musicManager.audioPlayer.stopTrack();
            audioManager.closeAudioConnection();
            event.getHook().sendMessage("Я покинул голосовой канал").queue();
        }
    }
}
