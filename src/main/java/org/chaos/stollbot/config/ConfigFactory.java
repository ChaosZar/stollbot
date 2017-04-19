package org.chaos.stollbot.config;

import com.thoughtworks.xstream.XStream;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Logger;

@Dependent
public class ConfigFactory {

    private static Logger logger = Logger.getLogger(ConfigFactory.class.getName());

    @Produces
    @Preferred
    @ApplicationScoped
    public static Config produceConfig() {
        XStream xStream = new XStream();
        xStream.processAnnotations(Config.class);

        Config config = null;
        File configFile = new File("./config.xml");
        try {
            config = (Config) xStream.fromXML(configFile);
        } catch (Exception e) {
            logger.warning(
                    "Failed to load config. Config will be reseted.\r\n" + e.getMessage());
            try {
                xStream.toXML(new Config(), new FileOutputStream(configFile));
            } catch (Exception e1) {
                logger.warning("Failed to reset Config.\r\n" + e1.getMessage());
            }
            throw new Error(e);
        }
        return config;
    }

}
