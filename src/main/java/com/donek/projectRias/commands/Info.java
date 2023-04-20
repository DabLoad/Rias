package com.donek.projectRias.commands;
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
            embed.setTitle("Information");
            embed.setDescription("Choose an option:");
            embed.setColor(5446170);
            embed.setAuthor("Rias");
            embed.setThumbnail("https://cdn.discordapp.com/attachments/963878656726409281/1090601321834496092/artworks-a97bKtSqgVNU2sM9-0enDuQ-t500x500.jpg");
            embed.setFooter("©Project Rias • 2023");
            event.replyEmbeds(embed.build())
                    .addActionRow(
                            StringSelectMenu.create("informationMenu")
                                    .setPlaceholder("Choose an option:")
                                    .addOptions(SelectOption.of("Commands", "commands")
                                            .withDescription("Commands list")
                                            .withEmoji(Emoji.fromUnicode("U+1F47E"))
                                            .withDefault(false))
                                    .addOptions(SelectOption.of("Bot information", "bInfo")
                                            .withDescription("Bot information")
                                            .withEmoji(Emoji.fromUnicode("U+1F47E"))
                                            .withDefault(false))
                                    .addOptions(SelectOption.of("Support", "support")
                                            .withDescription("Support")
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
                    embed.setTitle("Commands:");
                    embed.addField( "Reaction command:", "`/emote <emote_type> [mention]`", false);
                    embed.addField("Ping command:", "`/ping`", false);
                    embed.addField("Play command:", "`/play <Song>`", false);
                    embed.addField("Now playing command:", "`/nowplaying`", false);
                    embed.addField("Queue command:", "`/queue`", false);
                    embed.addField("Repeat command:", "`/repeat`", false);
                    embed.addField("Skip command:", "`/skip [amount]`", false);
                    embed.addField("Stop command:", "`/stop`", false);
                    embed.addField("Leave command:", "`/leave`", false);
                    embed.setColor(5446170);
                    embed.setAuthor("Rias");
                    embed.setThumbnail("https://cdn.discordapp.com/attachments/963878656726409281/1090601321834496092/artworks-a97bKtSqgVNU2sM9-0enDuQ-t500x500.jpg");
                    embed.setFooter("©Project Rias • 2023");
                    event.editMessageEmbeds(embed.build()).queue();
                case ("bInfo"):
                    embed.clear();
                    embed.setTitle("Information:");
                    embed.addField("Codename:", "`Project Rias OpenSource`", false);
                    embed.addField("Programming language:", "`Java`", false);
                    embed.addField("Author:", "<@!636179261367255070>", false);
                    embed.addField("Ver:", "`1.0`", false);
                    embed.setColor(5446170);
                    embed.setAuthor("Rias");
                    embed.setThumbnail("https://cdn.discordapp.com/attachments/963878656726409281/1090601321834496092/artworks-a97bKtSqgVNU2sM9-0enDuQ-t500x500.jpg");
                    embed.setFooter("©Project Rias • 2023");
                    event.editMessageEmbeds(embed.build()).queue();
                case ("support"):
                    embed.clear();
                    embed.setTitle("**Project Rias GitHub**","https://github.com/DabLoad/Rias");
                    embed.setColor(5446170);
                    embed.setAuthor("Rias");
                    embed.setThumbnail("https://cdn.discordapp.com/attachments/963878656726409281/1090601321834496092/artworks-a97bKtSqgVNU2sM9-0enDuQ-t500x500.jpg");
                    embed.setFooter("©Project Rias • 2023");
                    event.editMessageEmbeds(embed.build()).queue();
            }
        }
    }
}
