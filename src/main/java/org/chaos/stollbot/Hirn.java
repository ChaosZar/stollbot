package org.chaos.stollbot;

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

    private static final String TUER_ZU = "Tür zu";
    private Map<LocalDate, HashSet<String>> gedaechniss = new HashMap<>();

    @Inject
    private Mund mund;

    public void shoutTuerZu(IUser user, StatusType oldStatus, StatusType newStatus) {
        if (oldStatus != StatusType.ONLINE && newStatus == StatusType.ONLINE) {
            String key = TUER_ZU + user.getName();
            if (!doRemember(key)) {
                mund.sendMessage(TUER_ZU);
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
}
