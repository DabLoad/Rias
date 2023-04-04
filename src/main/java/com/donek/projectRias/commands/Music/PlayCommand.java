package com.donek.projectRias.commands.Music;

import com.donek.projectRias.LavaPlayer.PlayerManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.managers.AudioManager;

import java.net.URI;
import java.net.URISyntaxException;

public class PlayCommand extends ListenerAdapter {
    private boolean isUrl(String url) {
        try {
            new URI(url);
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {


        if (event.getName().equals("play")) {
            event.deferReply().setEphemeral(false).queue();
            final TextChannel channel = event.getChannel().asTextChannel();
            final Member self = event.getGuild().getSelfMember();
            final GuildVoiceState selfVoiceState = self.getVoiceState();


            final Member member = event.getMember();
            final GuildVoiceState memberVoiceState = member.getVoiceState();

            if (!memberVoiceState.inAudioChannel()){
                event.getHook().sendMessage("Ты должен быть в голосовом канале, что-бы это сработало").queue();
            }

            if (memberVoiceState.inAudioChannel()) {
                final AudioManager audioManager = event.getGuild().getAudioManager();
                final VoiceChannel memberChannel = memberVoiceState.getChannel().asVoiceChannel();
                if (!selfVoiceState.inAudioChannel()){
                    audioManager.openAudioConnection(memberChannel);
                    event.getHook().sendMessage("Подключаюсь к " + memberChannel.getAsMention()).complete();
                }
                OptionMapping youtubeUrl = event.getOption("track");
                String trackUrl = youtubeUrl.getAsString();
                if (!isUrl(trackUrl)) {
                    trackUrl = "ytsearch:" + trackUrl;
                }
                PlayerManager.getInstance().loadAndPlay(channel, trackUrl, event);
            }
            else if (!memberVoiceState.getChannel().asVoiceChannel().equals(selfVoiceState.getChannel().asVoiceChannel())){
                event.getHook().sendMessage("Ты должен быть со мной в одном голосовом канале, что-бы это сработало").queue();
            }


        }
    }

}
