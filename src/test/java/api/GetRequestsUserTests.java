package api;

import controllers.UserController;
import data.factory.UserData;
import io.restassured.response.Response;
import models.UserModel;


import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class GetRequestsUserTests extends BaseTest {
    private static UserController userController = new UserController();
    @Test(description = "Verify that there is a possibility to get info from randomly generated User on the backend",
            groups = {"UserAPI", "Smoke", "APITests"})
    public void getUserByUsernameTest(){
        UserModel user = new UserData().userCreationData();
        userController.createUser(user);
        Response response = userController.getUser(user.getUsername());
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Failed to retrieve user info by username");
        userController.deleteUser(user.getUsername());
    }
}
