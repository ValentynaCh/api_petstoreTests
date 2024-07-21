package controllers;

import configReaders.PetConfigReader;
import dataBaseQueries.CategoryQueries;
import dataBaseQueries.PetQueries;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import models.petModels.CategoryModel;
import models.petModels.PetModel;
import models.petModels.TagModel;

public class PetController extends BaseController {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(PetController.class);
    public PetConfigReader petConfigReader = new PetConfigReader();
    public String petCreatePath = petConfigReader.getPetCreatePath();
    public static PetQueries petQueries = new PetQueries();
    public static CategoryQueries categoryQueries = new CategoryQueries();

    public Response createPet(PetModel pet) {
        logger.info("Creating a pet");
        Response response = postRequest(petCreatePath, pet);
        if (response.getStatusCode() == 200) {
            int categoryId = pet.getCategory().getId();
            if (!categoryQueries.categoryExists(categoryId)) {
                createCategoryIfNotExists(pet.getCategory());
            }
            if (!pet.getTags().isEmpty()) {
                TagModel firstTag = pet.getTags().get(0);
                petQueries.addPet(categoryId, pet.getName(), String.join(",", pet.getPhotoUrls()), firstTag.getId(), pet.getStatus());
            } else {
                petQueries.addPet(categoryId, pet.getName(), String.join(",", pet.getPhotoUrls()), null, pet.getStatus());
            }
        } else {
            logger.error("Failed to create pet via API. Response code: " + response.getStatusCode());
        }
        return response;
    }

    private void createCategoryIfNotExists(CategoryModel category) {
        logger.info("Category with ID " + category.getId() + " does not exist. Creating new category.");
        categoryQueries.addCategory(category.getId(), category.getName());
    }
}
