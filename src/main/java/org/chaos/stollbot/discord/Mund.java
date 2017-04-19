package org.chaos.stollbot.discord;

import org.chaos.stollbot.config.Config;
import org.chaos.stollbot.config.Preferred;
import sx.blah.discord.api.IDiscordClient;
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
    @Preferred
    private Config config;

    public void sendMessage(String message) {
        IDiscordClient client = this.client.getClient();
        IGuild guild = client.getGuildByID(config.getGuildId());
        new MessageBuilder(client)
                .appendContent(message)
                .withChannel(guild.getGeneralChannel())
                .build();
    }
}
