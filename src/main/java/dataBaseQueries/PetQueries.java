package dataBaseQueries;

import configReaders.DataBaseConfigReader;
import models.petModels.CategoryModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PetQueries {

    private static final DataBaseConfigReader dataBaseConfigReader = new DataBaseConfigReader();
    private static final String petDataBaseUrl = dataBaseConfigReader.getPetDataBaseConnection();
    private static final String petDataBaseName = dataBaseConfigReader.getPetDataBaseName();
    private static final String petDataBasePassword = dataBaseConfigReader.getPetDataBasePassword();
    private static final String petDataBaseUsername = dataBaseConfigReader.getPetDataBaseUserName();

    public void addPet(int categoryId, String name, String photoUrls, Integer tagId, String status) {
        String insertSql = "INSERT INTO pet (category_id, name, photo_urls, status) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(petDataBaseUrl + "/" + petDataBaseName, petDataBaseUsername, petDataBasePassword);
             PreparedStatement statement = connection.prepareStatement(insertSql)) {
            statement.setInt(1, categoryId);
            statement.setString(2, name);
            statement.setString(3, photoUrls);
            statement.setString(4, status);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pet added successfully.");
            } else {
                System.out.println("Failed to add pet.");
            }
        } catch (SQLException e) {
            System.err.println("Error adding pet: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
