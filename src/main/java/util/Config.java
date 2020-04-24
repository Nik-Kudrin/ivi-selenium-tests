package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

public class Config {
    private static final String CONFIG_NAME = "config.properties";
    private static final Logger LOG = LogManager.getLogger(Config.class);

    private URL googleUrl;

    public URL getGoogleUrl() {
        return googleUrl;
    }

    private Path localDriverPath;

    public Path getLocalDriverPath() {
        return localDriverPath;
    }

    // TODO: to enum
    private String driver;

    public String getDriver() {
        return driver;
    }

    private static Config instance;

    private Config() {
        try (var propFileStream = Config.class.getClassLoader().getResourceAsStream(CONFIG_NAME)) {
            var properties = new Properties();
            properties.load(propFileStream);

            googleUrl = new URL(properties.getProperty("google-url"));
            driver = properties.getProperty("driver");
            localDriverPath = Paths.get(properties.getProperty("local-driver-path"));
        } catch (IOException e) {
            LOG.error("Error during reading configuration file", e);
        }
    }

    public static Config Instance() {
        synchronized (Config.class) {
            if (instance == null) {
                instance = new Config();
            }
            return instance;
        }
    }
}
