package api;

import controllers.UserController;
import data.factory.UserData;
import io.restassured.response.Response;
import models.UserModel;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteRequestsUserTests extends BaseTest {
    private static UserController userController = new UserController();
    @Test(description = "Verify that randomly generated User is deleted on the backend",
            groups = {"UserAPI", "APITests"})
    public void deleteExistedUserTest(){
        UserModel user = new UserData().userCreationData();
        userController.createUser(user);
        Response response = userController.deleteUser(user.getUsername());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "User hasn't been deleted");
    }
    @Test(description = "Verify that not existed user isn't deleted on the backend",
            groups = {"UserAPI", "APITests"})
    public void deleteNonExistedUserTest(){
        UserModel user = new UserData().userCreationData();
        Response response = userController.deleteUser(user.getUsername());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND, "There is a possibility to delete not existed user");
    }
}
