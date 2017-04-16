package org.chaos.stollbot;

import org.chaos.stollbot.discord.Client;
import org.chaos.stollbot.discord.Ohren;
import org.jboss.weld.environment.se.events.ContainerInitialized;
import sx.blah.discord.api.events.EventDispatcher;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.logging.Logger;

/**
 * Main Method.
 */
@Dependent
public class Stollbot {

    private static Logger logger = Logger.getLogger(Stollbot.class.getName());

    @Inject
    private Client client;

    @Inject
    private Ohren ohren;


    public void main(@Observes ContainerInitialized event) {
        logger.info("entering main");
        EventDispatcher dispatcher = client.getClient().getDispatcher();
        dispatcher.registerListener(ohren);
    }
}