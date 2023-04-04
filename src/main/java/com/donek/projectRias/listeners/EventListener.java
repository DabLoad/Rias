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
    /*@Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        User user = event.getUser();
        String emoji = event.getEmoji().getAsReactionCode();
        String channelMention = event.getChannel().getAsMention();
        String jumpLink = event.getJumpUrl();

        String message = user.getAsMention() + " reacted to a message with " + emoji + " in the " + channelMention + " channel!";
        event.getGuild().getDefaultChannel().asStandardGuildMessageChannel().sendMessage(message).queue();
    }*/
    /*@Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        String avatar = event.getUser().getEffectiveAvatarUrl();
        event.getGuild().getDefaultChannel().asStandardGuildMessageChannel().sendMessage(avatar).queue();
    }*/

    /*@Override
    public void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent event) {
        List<Member> members =  event.getGuild().getMembers();
        int onlineMembers = 0;
        for (Member member : members){
            if (member.getOnlineStatus() == OnlineStatus.ONLINE ||
                    member.getOnlineStatus() == OnlineStatus.DO_NOT_DISTURB ||
                    member.getOnlineStatus() == OnlineStatus.IDLE){
                onlineMembers++;
            }
        }


        User user = event.getUser();
        String message = user.getAsTag() + " updated their status! There are now: " + onlineMembers + " users online in this guild!";
        event.getGuild().getDefaultChannel().asStandardGuildMessageChannel().sendMessage(message).queue();

    }*/
}
