package controllers;

import data.factory.UserData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.UserModel;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import utils.ConfigReader;
import utils.DatabaseUtils;


public class UserController extends BaseController {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);
    public ConfigReader configReader = new ConfigReader();
    public String userCreatePath = configReader.getUserCreatePath();
    public String userCreateWithListPath = configReader.getUserCreateWithListPath();
    public String userLoginPath = configReader.getUserLoginPath();


    public Response createUser(UserModel user) {
        logger.info("Create a user");
        Response response = postRequest(userCreatePath, user);
        if (response.getStatusCode() == 200) {
            DatabaseUtils.insertUser(user);
        }
        return response;
    }

    public Response createUsersList(int usersCount) {
        logger.info("Create a list of users");
        List<UserModel> users = new ArrayList<>();
        for (int i = 0; i < usersCount; i++) {
            users.add(new UserData().userCreationData());
        }
        return postRequest(userCreateWithListPath, users);
    }

    public Response deleteUser(String username) {
        logger.info("Delete a user");
        Response response = deleteRequest(userCreatePath + "/" + username);
        if (response.getStatusCode() == 200) {
            DatabaseUtils.deleteUser(username);
        }
        return response;
    }

    public Response editUserFirstAndLastName(UserModel user, String firstName, String lastName) {
        logger.info("Edit First and Last name of a user");
        String originalUsername = user.getUsername();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return putRequest(configReader.getUserCreatePath() + "/" + originalUsername, user);
    }

    public Response getUser(String username) {
        logger.info("Get user info");
        return getRequest(configReader.getUserCreatePath() + "/" + username);
    }

    public Response loginUser(String username, String password) {
        logger.info("Login user");
        return RestAssured.given()
                .queryParam("username", username)
                .queryParam("password", password)
                .get(BASE_URL_PATH + userLoginPath);
    }
}
