package org.chaos.stollbot;

import org.apache.commons.lang3.RandomUtils;
import org.chaos.stollbot.config.messages.MessageKey;
import org.chaos.stollbot.config.messages.RandomMessages;
import org.chaos.stollbot.discord.Mund;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.StatusType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Controller.
 */
@ApplicationScoped
public class Hirn {

    private Map<LocalDate, HashSet<String>> gedaechniss = new HashMap<>();

    @Inject
    private Mund mund;

    public void shoutTuerZu(IUser user, StatusType oldStatus, StatusType newStatus) {
        if (oldStatus != StatusType.ONLINE && newStatus == StatusType.ONLINE) {
            String key = MessageKey.ZUER_ZU.name() + user.getName();
            if (!doRemember(key)) {
                mund.sendMessage(MessageKey.ZUER_ZU);
            }
        }
    }

    private boolean doRemember(String key) {
        LocalDate today = LocalDate.now();

        if (!gedaechniss.containsKey(today)) {
            gedaechniss = new HashMap<>();
            gedaechniss.put(today, new HashSet<>());
        }
        if (gedaechniss.get(today).contains(key)) {
            return true;
        }
        gedaechniss.get(today).add(key);
        return false;
    }

    public void sendRandomMessage() {
        int pos = RandomUtils.nextInt(0, RandomMessages.values().length);
        mund.sendMessage(RandomMessages.values()[pos]);
    }

}
