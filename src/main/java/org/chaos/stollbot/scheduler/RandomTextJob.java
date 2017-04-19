package org.chaos.stollbot.scheduler;

import de.mirkosertic.cdicron.api.Cron;
import org.chaos.stollbot.Hirn;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RandomTextJob {

    @Inject
    private Hirn hirn;

    @Cron(cronExpression = "0 0 18 1/1 * ? *")
    public void onSchedule() {
        hirn.sendRandomMessage();
    }

}
