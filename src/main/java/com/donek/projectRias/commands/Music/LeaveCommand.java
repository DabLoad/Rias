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

            final Guild guild = event.getGuild();
            final AudioManager audioManager = event.getGuild().getAudioManager();
            final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);
            musicManager.scheduler.repeating = false;
            musicManager.scheduler.queue.clear();
            musicManager.audioPlayer.stopTrack();
            audioManager.closeAudioConnection();
            event.getHook().sendMessage("I left the voice channel").queue();
        }
    }
}
