package com.donek.projectRias;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;


public class CommandManager extends ListenerAdapter {
    private EmbedBuilder embed;
    @Override
    public void onReady(ReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();

        commandData.add(Commands.slash("ping", "Ping."));

        commandData.add(Commands.slash("info", "Информация о боте"));

        OptionData youtubeUrl = new OptionData(OptionType.STRING, "track", "Track name or url.", true);
        commandData.add(Commands.slash("play", "Команда для проигрывания музыки").addOptions(youtubeUrl));

        commandData.add(Commands.slash("stop", "Останавливает проигрывание музыки"));

        OptionData skipCount = new OptionData(OptionType.INTEGER, "skip_count", "Количество треков для пропуска", false);
        commandData.add(Commands.slash("skip", "Пропускает текущий трек").addOptions(skipCount));

        commandData.add(Commands.slash("nowplaying", "Показывает трек который проигрывается в данный момент"));

        commandData.add(Commands.slash("queue", "Показывает очередь треков"));

        commandData.add(Commands.slash("repeat", "Включает режим повтора для текущего трека"));

        commandData.add(Commands.slash("leave", "Команда для отключения бота от войса"));

        OptionData verification_role = new OptionData(OptionType.ROLE, "ver_role", "Роль для выдачи", true);
        OptionData verification_text = new OptionData(OptionType.STRING, "ver_text", "Текст для сообщения с верификацией", false);
        OptionData verification_title = new OptionData(OptionType.STRING, "ver_title", "Заголовок для сообщения с верификацией", false);
        OptionData verification_button = new OptionData(OptionType.STRING, "ver_button", "Текст для кнопки верификации", false);
        commandData.add(Commands.slash("add_verification", "Команда для добавления верификации (Owner/Admin only)").addOptions(verification_role, verification_text, verification_title, verification_button));

        OptionData emoteType = new OptionData(OptionType.STRING, "emotion_type", "Emotion", true)
                .addChoice("Hi", "hi")
                .addChoice("Hug", "hug")
                .addChoice("Poke", "poke")
                .addChoice("Kill", "kill")
                .addChoice("Kiss", "kiss")
                .addChoice("Lick", "lick")
                .addChoice("Feed", "feed")
                .addChoice("Pat", "pat")
                .addChoice("Punch", "punch")
                .addChoice("Tickle", "tickle")
                .addChoice("Cry", "cry")
                .addChoice("Smug", "smug");
        OptionData emoteMention = new OptionData(OptionType.USER, "emote_mention", "Mention user", false);
        commandData.add(Commands.slash("emote", "Команда для реакций в чате").addOptions(emoteType, emoteMention));

        event.getJDA().updateCommands().addCommands(commandData).queue();
    }
}
