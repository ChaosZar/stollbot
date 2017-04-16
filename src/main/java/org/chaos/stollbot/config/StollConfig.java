package org.chaos.stollbot.config;

import javax.enterprise.context.Dependent;

/**
 * Static Config.
 * TODO: Load from File
 */
@Dependent
public class StollConfig {

    private String token = "";
    private String guildId = "";

    public String getToken() {
        return token;
    }

    public String getGuildId() {
        return guildId;
    }
}
