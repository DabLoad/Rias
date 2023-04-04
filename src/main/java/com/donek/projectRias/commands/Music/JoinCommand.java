package com.donek.projectRias.commands.Music;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.EventListener;

public class JoinCommand extends ListenerAdapter {
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("join")) {
            event.deferReply().setEphemeral(false).queue();
            final TextChannel channel = event.getChannel().asTextChannel();
            final Member self = event.getGuild().getSelfMember();
            final GuildVoiceState selfVoiceState = self.getVoiceState();

            if (selfVoiceState.inAudioChannel()){
                event.getHook().sendMessage("Я уже нахожусь в голосовом канале").queue();
            }

            final Member member = event.getMember();
            final GuildVoiceState memberVoiceState = member.getVoiceState();

            if(!memberVoiceState.inAudioChannel()) {
                event.getHook().sendMessage("Ты должен быть в голосовом канале, что-бы это сработало").queue();
            }
            if (memberVoiceState.inAudioChannel()) {
                final AudioManager audioManager = event.getGuild().getAudioManager();
                final VoiceChannel memberChannel = memberVoiceState.getChannel().asVoiceChannel();
                if (!selfVoiceState.inAudioChannel()){
                    audioManager.openAudioConnection(memberChannel);
                    event.getHook().sendMessage("Подключаюсь к " + memberChannel.getAsMention()).queue();
                }
            }
        }
    }

}
