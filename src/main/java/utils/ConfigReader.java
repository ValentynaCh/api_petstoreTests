package utils;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;
    public static ConfigReader configReader;
    private static final String FILE_NAME = "base.properties";
    private static final Path RESOURCES_PATH = Paths.get("src", "main", "resources");
    private static final ch.qos.logback.classic.Logger logger = (Logger) LoggerFactory.getLogger(ConfigReader.class);

    public ConfigReader() {
        try (BufferedReader reader = Files.newBufferedReader(RESOURCES_PATH.resolve(FILE_NAME))) {
            properties = new Properties();
            properties.load(reader);
        } catch (IOException e) {
            logger.error("error while reading a {} file", FILE_NAME);
        }
    }

    public static ConfigReader getInstance() {
        if (configReader == null) {
            configReader = new ConfigReader();
        }
        return configReader;
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
    public String getUserLoginPath(){
        String userLoginPath = properties.getProperty("user.login");
        if(userLoginPath == null){
            logger.error("user login path is not specified in {} file.", FILE_NAME);
        }
        return userLoginPath;
    }

    public String getDataBaseConnection(){
        String dataBaseConnection = properties.getProperty("database.url");
        if(dataBaseConnection == null){
            logger.error("DataBase connection string is not specified in {} file.", FILE_NAME);
        }
        return dataBaseConnection;
    }

    public String getUserDataBaseName(){
        String userDataBaseName = properties.getProperty("database.username");
        if(userDataBaseName == null){
            logger.error("user DataBase name is not specified in {} file.", FILE_NAME);
        }
        return userDataBaseName;
    }

    public String getUserDataBasePassword(){
        String userDataBasePassword = properties.getProperty("database.password");
        if(userDataBasePassword == null){
            logger.error("user DataBase password is not specified in {} file.", FILE_NAME);
        }
        return userDataBasePassword;
    }
}
