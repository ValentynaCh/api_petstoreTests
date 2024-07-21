package configReaders;

import ch.qos.logback.classic.Logger;
import configReaders.UserConfigReader;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class DataBaseConfigReader {
    private Properties properties;
    public static UserConfigReader dataBaseUserConfigReader;
    private static final String FILE_NAME = "base.properties";
    private static final Path RESOURCES_PATH = Paths.get("src", "main", "resources");
    private static final ch.qos.logback.classic.Logger logger = (Logger) LoggerFactory.getLogger(UserConfigReader.class);

    public DataBaseConfigReader() {
        try (BufferedReader reader = Files.newBufferedReader(RESOURCES_PATH.resolve(FILE_NAME))) {
            properties = new Properties();
            properties.load(reader);
        } catch (IOException e) {
            logger.error("error while reading a {} file", FILE_NAME);
        }
    }

    public static UserConfigReader getInstance() {
        if (dataBaseUserConfigReader == null) {
            dataBaseUserConfigReader = new UserConfigReader();
        }
        return dataBaseUserConfigReader;
    }

    public String getPetDataBaseConnection(){
        String dataBaseConnection = properties.getProperty("petstoreDB.url");
        if(dataBaseConnection == null){
            logger.error("DataBase connection string is not specified in {} file.", FILE_NAME);
        }
        return dataBaseConnection;
    }

    public String getPetDataBaseUserName(){
        String userDataBaseName = properties.getProperty("petstoreDB.username");
        if(userDataBaseName == null){
            logger.error("user DataBase name is not specified in {} file.", FILE_NAME);
        }
        return userDataBaseName;
    }

    public String getPetDataBasePassword(){
        String userDataBasePassword = properties.getProperty("petstoreDB.password");
        if(userDataBasePassword == null){
            logger.error("user DataBase password is not specified in {} file.", FILE_NAME);
        }
        return userDataBasePassword;
    }

    public String getPetDataBaseName(){
        String petDataBaseName = properties.getProperty("petstoreDB.name");
        if(petDataBaseName == null){
            logger.error("user DataBase password is not specified in {} file.", FILE_NAME);
        }
        return petDataBaseName;
    }

}
