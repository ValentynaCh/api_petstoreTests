package configReaders;

import ch.qos.logback.classic.Logger;
import configReaders.UserConfigReader;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PetConfigReader extends CommonConfigReader {
    private static final String FILE_NAME = "base.properties";
    private static final ch.qos.logback.classic.Logger logger = (Logger) LoggerFactory.getLogger(PetConfigReader.class);

    public String getPetCreatePath() {
        String petCreatePath = properties.getProperty("pet.create");
        if (petCreatePath == null) {
            logger.error("user create path is not specified in {} file.", FILE_NAME);
        }
        return petCreatePath;
    }


}
