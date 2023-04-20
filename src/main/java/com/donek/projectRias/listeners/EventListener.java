package com.donek.projectRias.listeners;

import com.donek.projectRias.LavaPlayer.GuildMusicManager;
import com.donek.projectRias.LavaPlayer.PlayerManager;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.Widget;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.StatusChangeEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.List;

public class EventListener extends ListenerAdapter {

    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        final Member self = event.getGuild().getSelfMember();
        final Guild guild = event.getGuild();
        final VoiceChannel selfVoice = self.getVoiceState().getChannel().asVoiceChannel();
        final AudioManager audioManager = event.getGuild().getAudioManager();
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);
        if (1 >= selfVoice.getMembers().size()) {
            audioManager.closeAudioConnection();
            musicManager.scheduler.queue.clear();
            musicManager.audioPlayer.stopTrack();
        }
    }
}
