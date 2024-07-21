package configReaders;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class CommonConfigReader {
    protected Properties properties;
    private static final String FILE_NAME = "base.properties";
    private static final Path RESOURCES_PATH = Paths.get("src", "main", "resources");
    private static final ch.qos.logback.classic.Logger logger = (Logger) LoggerFactory.getLogger(CommonConfigReader.class);

    public CommonConfigReader() {
        try (BufferedReader reader = Files.newBufferedReader(RESOURCES_PATH.resolve(FILE_NAME))) {
            properties = new Properties();
            properties.load(reader);
        } catch (IOException e) {
            logger.error("Error while reading a {} file", FILE_NAME);
        }
    }

    public String getBaseUrl() {
        String baseUrl = properties.getProperty("base.url");
        if (baseUrl == null) {
            logger.error("base_Url not specified in {} file.", FILE_NAME);
        }
        return baseUrl;
    }

    public String getApiVersion() {
        String apiVersion = properties.getProperty("api.version");
        if (apiVersion == null) {
            logger.error("api version is not specified in {} file.", FILE_NAME);
        }
        return apiVersion;
    }
}
