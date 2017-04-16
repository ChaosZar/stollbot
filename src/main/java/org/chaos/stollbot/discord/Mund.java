package org.chaos.stollbot.discord;

import org.chaos.stollbot.config.StollConfig;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.util.MessageBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Create new Messages in the Public Chat.
 */
@ApplicationScoped
public class Mund {

    @Inject
    private Client client;

    @Inject
    private StollConfig config;

    public void sendMessage(String message) {
        IGuild guildByID = client.getClient().getGuildByID(config.getGuildId());
        new MessageBuilder(client.getClient())
                .appendContent(message)
                .withChannel(guildByID.getGeneralChannel())
                .build();
    }
}
