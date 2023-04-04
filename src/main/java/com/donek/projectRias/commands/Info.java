package com.donek.projectRias.commands;
import com.donek.projectRias.MongoDB;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

public class Info extends ListenerAdapter {
    EmbedBuilder embed = new EmbedBuilder();


    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("info")) {
            embed.setTitle("Информация");
            embed.setDescription("Выберите раздел:");
            embed.setColor(5446170);
            embed.setAuthor("Rias");
            embed.setThumbnail("https://cdn.discordapp.com/attachments/963878656726409281/1090601321834496092/artworks-a97bKtSqgVNU2sM9-0enDuQ-t500x500.jpg");
            embed.setFooter("©Project Rias • 2023");
            event.replyEmbeds(embed.build())
                    .addActionRow(
                            StringSelectMenu.create("informationMenu")
                                    .setPlaceholder("Выберите раздел")
                                    .addOptions(SelectOption.of("Команды", "commands") // another way to create a SelectOption
                                            .withDescription("Список команд") // this time with a description
                                            .withEmoji(Emoji.fromUnicode("U+1F47E")) // and an emoji
                                            .withDefault(false))
                                    .addOptions(SelectOption.of("Информация о боте", "bInfo")
                                            .withDescription("Информация о боте") // this time with a description
                                            .withEmoji(Emoji.fromUnicode("U+1F47E")) // and an emoji
                                            .withDefault(false))// while also being the default option
                                    .addOptions(SelectOption.of("Сервер поддержки", "server")
                                            .withDescription("Сервер поддержки")
                                            .withEmoji(Emoji.fromUnicode("U+1F47E"))
                                            .withDefault(false))
                                    .build())
                    .queue();
        }
    }

    @Override
    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        if (event.getComponentId().equals("informationMenu")) {
            switch (event.getValues().get(0).toString()){
                case ("commands"):
                    embed.clear();
                    embed.setTitle("Список комманд:");
                    embed.addField( "Reaction command:", "`/emote <emote_type> [mention]`", false);
                    embed.addField("Ping command:", "`/ping`", false);
                    //embed.addField("Join voice command:", "`/join`", false);
                    embed.addField("Play command:", "`/play <Song>`", false);
                    embed.addField("Now playing command:", "`/nowplaying`", false);
                    embed.addField("Queue command:", "`/queue`", false);
                    embed.addField("Repeat command:", "`/repeat`", false);
                    embed.addField("Skip command:", "`/skip [amount]`", false);
                    embed.addField("Stop command:", "`/stop`", false);
                    embed.addField("Leave command:", "`/leave`", false);
                    embed.addField("Add verification command", "`/add_verification <role> [ver_text] [ver_title] [ver_button]`", false);
                    embed.setColor(5446170);
                    embed.setAuthor("Rias");
                    embed.setThumbnail("https://cdn.discordapp.com/attachments/963878656726409281/1090601321834496092/artworks-a97bKtSqgVNU2sM9-0enDuQ-t500x500.jpg");
                    embed.setFooter("©Project Rias • 2023");
                    event.editMessageEmbeds(embed.build()).queue();
                case ("bInfo"):
                    embed.clear();
                    embed.setTitle("Информация:");
                    embed.addField("Codename:", "`Project Rias`", false);
                    embed.addField("Язык программирования:", "`Java`", false);
                    embed.addField("Автор:", "<@!636179261367255070>", false);
                    embed.addField("Версия:", "`1.3`", false);
                    embed.setColor(5446170);
                    embed.setAuthor("Rias");
                    embed.setThumbnail("https://cdn.discordapp.com/attachments/963878656726409281/1090601321834496092/artworks-a97bKtSqgVNU2sM9-0enDuQ-t500x500.jpg");
                    embed.setFooter("©Project Rias • 2023");
                    event.editMessageEmbeds(embed.build()).queue();
                case ("server"):
                    embed.clear();
                    embed.setTitle("**Project Rias Server**","https://discord.gg/BqA9RpksqT");
                    embed.setColor(5446170);
                    embed.setAuthor("Rias");
                    embed.setThumbnail("https://cdn.discordapp.com/attachments/963878656726409281/1090601321834496092/artworks-a97bKtSqgVNU2sM9-0enDuQ-t500x500.jpg");
                    embed.setFooter("©Project Rias • 2023");
                    event.editMessageEmbeds(embed.build()).queue();
            }
        }
    }
}
