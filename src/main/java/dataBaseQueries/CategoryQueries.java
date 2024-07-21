package dataBaseQueries;

import configReaders.DataBaseConfigReader;
import models.petModels.CategoryModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryQueries {
    private static final DataBaseConfigReader dataBaseConfigReader = new DataBaseConfigReader();
    private static final String petDataBaseUrl = dataBaseConfigReader.getPetDataBaseConnection();
    private static final String petDataBaseName = dataBaseConfigReader.getPetDataBaseName();
    private static final String petDataBasePassword = dataBaseConfigReader.getPetDataBasePassword();
    private static final String petDataBaseUsername = dataBaseConfigReader.getPetDataBaseUserName();

    public boolean categoryExists(int categoryId) {
        String query = "SELECT COUNT(*) FROM category WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(petDataBaseUrl + "/" + petDataBaseName, petDataBaseUsername, petDataBasePassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addCategory(int id, String name) {
        String insertSql = "INSERT INTO category (id, name) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(petDataBaseUrl + "/" + petDataBaseName, petDataBaseUsername, petDataBasePassword);
             PreparedStatement stmt = conn.prepareStatement(insertSql)) {
            stmt.setInt(1, id);
            stmt.setString(2, name);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Category added successfully.");
            } else {
                System.out.println("Failed to add category.");
            }
        } catch (SQLException e) {
            System.err.println("Error adding category: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
