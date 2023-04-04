package com.donek.projectRias.commands;

import com.donek.projectRias.MongoDB;
import com.donek.projectRias.listeners.EventListener;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.bson.Document;

public class VerificationCreateCommand extends EventListener {
    private EmbedBuilder embed;

    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("add_verification")) {
            EmbedBuilder embed = new EmbedBuilder();
            event.deferReply().setEphemeral(true).queue();
            if (event.getMember().isOwner() || event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                if (event.getOption("ver_role") != null) {
                    MongoDB.addRole(event);
                }
                if (event.getOption("ver_text") != null) {
                    String verification_text = event.getOption("ver_text").getAsString();
                    embed.setDescription(verification_text);
                } else {
                    embed.setTitle("Верификация");
                    embed.setDescription("`Что-бы перейти к общению нажмите кнопку ниже:`");
                }
                if (event.getOption("ver_title") != null) {
                    String verification_title = event.getOption("ver_title").getAsString();
                    embed.setTitle(verification_title);
                } else {
                    embed.setTitle("Верификация");
                }
                embed.setColor(5446170);
                if (event.getOption("ver_button") != null) {
                    String verification_button = event.getOption("ver_button").getAsString();
                    event.getChannel().sendMessageEmbeds(embed.build())
                            .addActionRow(
                                    Button.primary("VerifyMe", verification_button))
                            .queue();
                } else {
                    event.getChannel().sendMessageEmbeds(embed.build())
                            .addActionRow(
                                    Button.primary("VerifyMe", "Verify me"))
                            .queue();
                }
                event.getHook().sendMessage("Готово!").queue();
            }
        }
    }



    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getComponentId().equals("VerifyMe")) {
            event.deferReply().setEphemeral(true).queue();
            event.getHook().sendMessage("Готово!").queue();
            event.getGuild().addRoleToMember(event.getUser(), event.getGuild().getRoleById(MongoDB.getCurrentRole(event))).complete();
        }
    }
}
