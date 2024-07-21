package configReaders;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class UserConfigReader extends CommonConfigReader {
    private Properties properties;
    private static final String FILE_NAME = "base.properties";
    private static final ch.qos.logback.classic.Logger logger = (Logger) LoggerFactory.getLogger(UserConfigReader.class);

    public String getUserCreatePath() {
        String userCreatePath = properties.getProperty("user.create");
        if (userCreatePath == null) {
            logger.error("user create path is not specified in {} file.", FILE_NAME);
        }
        return userCreatePath;
    }

    public String getUserCreateWithListPath() {
        String userCreateWithListPath = properties.getProperty("user.createWithList");
        if (userCreateWithListPath == null) {
            logger.error("user create with list path is not specified in {} file.", FILE_NAME);
        }
        return userCreateWithListPath;
    }

    public String getUserLoginPath() {
        String userLoginPath = properties.getProperty("user.login");
        if (userLoginPath == null) {
            logger.error("user login path is not specified in {} file.", FILE_NAME);
        }
        return userLoginPath;
    }
}
