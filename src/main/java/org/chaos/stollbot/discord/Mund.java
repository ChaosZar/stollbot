package org.chaos.stollbot.discord;

import org.chaos.stollbot.config.Config;
import org.chaos.stollbot.config.Messages;
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
    private Messages messages;

    @Inject
    @Preferred
    private Config config;

    /**
     * Reads a message from resource bundle und sends it to the chat.
     *
     * @param key key to message
     */
    public void sendMessage(String key) {
        saySomething(messages.getMessage(key));
    }

    /**
     * Sends text to chat.
     *
     * @param message text to be sended
     */
    public void saySomething(String message) {
        IDiscordClient client = this.client.getClient();
        IGuild guild = client.getGuildByID(config.getGuildId());
        new MessageBuilder(client)
                .appendContent(message)
                .withChannel(guild.getGeneralChannel())
                .build();
    }
}
