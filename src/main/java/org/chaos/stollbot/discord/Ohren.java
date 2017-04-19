package org.chaos.stollbot.discord;

import org.chaos.stollbot.Hirn;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.user.PresenceUpdateEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Logger;

/**
 * Chat room Listener.
 */
@ApplicationScoped
public class Ohren {

    @Inject
    private Hirn hirn;

    @EventSubscriber
    public void onPresenceUpdateEvent(PresenceUpdateEvent event) {
        Logger.getLogger(Ohren.class.getName()).fine("entered with UserJoinEvent");
        hirn.shoutTuerZu(event.getUser(), event.getOldPresence().getStatus(), event.getNewPresence().getStatus());
    }
}
