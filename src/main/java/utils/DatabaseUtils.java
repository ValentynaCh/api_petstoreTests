package utils;

import models.UserModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUtils {
    public static ConfigReader configReader = new ConfigReader();
    public static String dataBaseUrl = configReader.getDataBaseConnection();
    public static String userDataBaseName = configReader.getUserDataBaseName();
    public static String userDataBasePassword = configReader.getUserDataBasePassword();

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dataBaseUrl, userDataBaseName, userDataBasePassword);
    }

    public static void insertUser(UserModel user) {
        String query = "INSERT INTO users (username, firstName, lastName, email, password, phone, userStatus) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.setString(7, user.getUserStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteUser(String username) {
        String query = "DELETE FROM users WHERE username = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("No user found with the given username.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
