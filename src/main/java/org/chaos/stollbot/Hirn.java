package org.chaos.stollbot;

import org.chaos.stollbot.discord.Mund;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.StatusType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller.
 */
@ApplicationScoped
public class Hirn {

    private static final String TUER_ZU = "Tür zu";
    private Map<String, LocalDate> gedaechniss = new HashMap<>();

    @Inject
    private Mund mund;

    public void shoutTuerZu(IUser user, StatusType oldStatus, StatusType newStatus) {
        if (oldStatus != newStatus && newStatus == StatusType.ONLINE) {
            String key = TUER_ZU + user.getName();
            if (!gedaechniss.getOrDefault(key, LocalDate.MIN).isEqual(LocalDate.now())) {
                mund.sendMessage(TUER_ZU);
                gedaechniss.put(key, LocalDate.now());
            }
        }
        clearGedaechniss();
    }

    private void clearGedaechniss() {
        for (Map.Entry<String, LocalDate> entry : gedaechniss.entrySet()) {
            if (entry.getValue().isBefore(LocalDate.now())) {
                gedaechniss.remove(entry.getKey());
            }
        }
    }
}
