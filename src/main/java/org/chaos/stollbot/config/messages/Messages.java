package org.chaos.stollbot.config.messages;

import javax.enterprise.context.Dependent;
import java.util.ResourceBundle;

/**
 * Messages.
 */
@Dependent
public class Messages {

    private ResourceBundle messages;

    public Messages() {
        messages = ResourceBundle.getBundle("messages");
    }

    public String getMessage(String key) {
        if (key == null) {
            return "null";
        }
        if (!messages.containsKey(key)) {
            return key;
        }
        return messages.getString(key);
    }

    public String getMessage(MessageKey messageKey) {
        return getMessage(messageKey.name());
    }
}
