package api;

import controllers.UserController;
import data.factory.UserData;
import io.restassured.response.Response;
import models.UserModel;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PostRequestsUserTests extends BaseTest {
    private static UserController userController = new UserController();

    @Test(description = "Verify that there is a possibility to create a randomly generated User on the backend",
            groups = {"UserAPI", "Smoke", "APITests"})
    public void createUserTest() {
        UserModel user = new UserData().userCreationData();
        Response response = userController.createUser(user);
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        //userController.deleteUser(user.getUsername());
    }

    @Test(description = "Verify that there is a possibility to create a randomly generated list of users on the backend",
            groups = {"UserAPI", "APITests"})
    public void createUsersList() {
        Response response = userController.createUsersList(5);
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }
}
