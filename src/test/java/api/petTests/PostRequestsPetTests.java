package api.petTests;

import api.BaseTest;
import controllers.PetController;
import data.factory.PetData;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import models.petModels.PetModel;

import static org.testng.Assert.assertEquals;

public class PostRequestsPetTests extends BaseTest {
    private static PetController petController = new PetController();

    @Test(description = "Verify that there is a possibility to create a randomly generated pet on the backend",
            groups = {"PetAPI", "Smoke", "APITests"})
    public void createPetTest() {
        PetModel pet = new PetData().generatePet();
        Response response = petController.createPet(pet);
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }
}
