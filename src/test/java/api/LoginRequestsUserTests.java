package api;

import controllers.UserController;
import data.factory.UserData;
import io.restassured.response.Response;
import models.UserModel;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginRequestsUserTests extends BaseTest {
    public UserController userController = new UserController();
    @Test(description = "Verify that there is a possibility to login into the system by valid User on the backend",
            groups = {"UserAPI", "Smoke", "APITests"})
    public void loginUserWithValidCredentialsTest(){
        UserModel user = new UserData().userCreationData();
        String userName = user.getUsername();
        String userPassword = user.getPassword();
        userController.createUser(user);
        Response response = userController.loginUser(userName, userPassword);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "User hasn't been logged in");
        userController.deleteUser(user.getUsername());
    }

    @Test(description = "Verify that there is not a possibility to login into the system by valid User on the backend",
            groups = {"UserAPI", "Smoke", "APITests"})
    public void loginUserWithInvalidCredentialsTest(){
        UserModel user = new UserData().userCreationData();
        String userName = user.getUsername();
        String userPassword = user.getPassword();
        Response response = userController.loginUser(userName, userPassword);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, "User hasn't been logged in");
    }
}
