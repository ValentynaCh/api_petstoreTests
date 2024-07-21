package api.userTests;

import api.BaseTest;
import controllers.UserController;
import data.factory.UserData;
import io.restassured.response.Response;
import models.userModels.UserModel;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PutRequestsUserTests extends BaseTest {
    private static UserController userController = new UserController();
    @Test(description = "Verify that there is a possibility to edit First and Last name of a randomly generated User on the backend",
            groups = {"UserAPI", "Smoke", "APITests"})
    public void editUserFirstAndLastNameTest() {
        SoftAssert softAssert = new SoftAssert();
        UserModel user = new UserData().generateUser();
        String userName = user.getUsername();
        userController.createUser(user);
        String newFirstName = "UpdatedFirstName";
        String newLastName = "UpdatedLastName";

        Response updateResponse = userController.editUserFirstAndLastName( user, newFirstName, newLastName);
        softAssert.assertEquals(updateResponse.getStatusCode(), HttpStatus.SC_OK, "User updating is failed");

        UserModel updatedUser = userController.getUser(userName).getBody().as(UserModel.class);
        softAssert.assertEquals(updatedUser.getFirstName(), newFirstName, "The Firstname hasn't been updated.");
        softAssert.assertEquals(updatedUser.getLastName(), newLastName, "The Lastname hasn't been updated.");
        softAssert.assertEquals(updatedUser.getEmail(), user.getEmail(), "Email has been changed");
        softAssert.assertEquals(updatedUser.getPassword(), user.getPassword(), "Password has been changed");
        softAssert.assertEquals(updatedUser.getPhone(), user.getPhone(), "The phone number has been changed");
        softAssert.assertEquals(updatedUser.getUserStatus(), user.getUserStatus(), "User's status has been changed");
        softAssert.assertAll();
        userController.deleteUser(user.getUsername());

    }
}
