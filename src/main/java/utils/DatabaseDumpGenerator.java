package utils;

import configReaders.DataBaseConfigReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.io.IOException;

public class DatabaseDumpGenerator {
    public static DataBaseConfigReader dataBaseConfigReader = new DataBaseConfigReader();
    public static String petDataBaseUrl = dataBaseConfigReader.getPetDataBaseConnection();
    public static String petDataBaseName = dataBaseConfigReader.getPetDataBaseName();
    public static String petDataBasePassword = dataBaseConfigReader.getPetDataBasePassword();
    public static String petDataBaseUsername = dataBaseConfigReader.getPetDataBaseUserName();
    static String dumpFile = "PetStoreDB_dump.sql";

    static {
        try {
            createDatabaseAndTables(petDataBaseUsername, petDataBasePassword, petDataBaseName);
            generateDump(petDataBaseUrl, petDataBaseName, petDataBaseUsername, petDataBasePassword, dumpFile);
            System.out.println("Database backup created successfully.");
        } catch (SQLException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void createDatabaseAndTables(String user, String password, String dbName) throws SQLException {
        String url = user + "/" + dbName;
        String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " + dbName;
        String useDatabaseSQL = "USE " + dbName;
        String createCategoryTableSQL = "CREATE TABLE IF NOT EXISTS category ("
                + "id INT PRIMARY KEY AUTO_INCREMENT, "
                + "name VARCHAR(255))";
        String createTagTableSQL = "CREATE TABLE IF NOT EXISTS tag ("
                + "id INT PRIMARY KEY AUTO_INCREMENT, "
                + "name VARCHAR(255))";
        String createPetTableSQL = "CREATE TABLE IF NOT EXISTS pet ("
                + "id INT PRIMARY KEY AUTO_INCREMENT, "
                + "category_id INT, "
                + "name VARCHAR(255) NOT NULL, "
                + "photo_urls VARCHAR(255) NOT NULL, "
                + "status VARCHAR(255), "
                + "FOREIGN KEY (category_id) REFERENCES category(id))";
        String createPetTagsTableSQL = "CREATE TABLE IF NOT EXISTS pet_tags ("
                + "pet_id INT, "
                + "tag_id INT, "
                + "FOREIGN KEY (pet_id) REFERENCES pet(id), "
                + "FOREIGN KEY (tag_id) REFERENCES tag(id), "
                + "PRIMARY KEY (pet_id, tag_id))";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createDatabaseSQL);
            stmt.executeUpdate(useDatabaseSQL);
            stmt.executeUpdate(createCategoryTableSQL);
            stmt.executeUpdate(createTagTableSQL);
            stmt.executeUpdate(createPetTableSQL);
            stmt.executeUpdate(createPetTagsTableSQL);
            System.out.println("Database and tables created successfully.");
        }


    }

    public static void generateDump(String url, String dbName, String user, String password, String dumpFile) throws IOException, InterruptedException {
        String mysqldumpPath = "/usr/bin/mysqldump";
        String command = String.format("%s --host=%s -u%s -p%s %s -r %s", mysqldumpPath, url, user, password, dbName, dumpFile);

        Process process = Runtime.getRuntime().exec(command);
        int processComplete = process.waitFor();

        if (processComplete == 0) {
            System.out.println("Dump created successfully.");
        } else {
            System.out.println("Could not create the dump.");
        }
    }

}
