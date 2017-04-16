package org.chaos.stollbot.discord;

import org.chaos.stollbot.config.StollConfig;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Discord client.
 */

@ApplicationScoped
public class Client {

    private IDiscordClient client;

    @Inject
    private StollConfig stollConfig;

    @PostConstruct
    public void init() {
        client = buildClient();
    }

    private IDiscordClient buildClient() {
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.withToken(stollConfig.getToken());
        return clientBuilder.login();

    }

    public IDiscordClient getClient() {
        return client;
    }
}
