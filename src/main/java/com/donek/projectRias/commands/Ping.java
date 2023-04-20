package com.donek.projectRias.commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Ping extends ListenerAdapter {
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (command.equals("ping")) { // Command:  /ping
            event.deferReply().setEphemeral(false).queue();
            JDA jda = event.getJDA();

            jda.getRestPing().queue(
                    (ping) -> event
                            .getHook().sendMessageFormat("Reset ping: %sms\nWS ping: %sms", ping, jda.getGatewayPing()).queue()
            );

        }
    }
}
