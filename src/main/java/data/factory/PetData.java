package data.factory;

import com.github.javafaker.Faker;
import models.petModels.CategoryModel;
import models.petModels.PetModel;
import models.petModels.TagModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PetData {
    private Faker faker;

    public PetData() {
        this.faker = new Faker(new Locale("en_US"));
    }

    public PetModel generatePet() {
        PetModel pet = new PetModel();
        pet.setId(faker.number().randomDigitNotZero());
        pet.setCategory(generateCategory());
        pet.setName(faker.funnyName().name());
        pet.setPhotoUrls(generatePhotoUrls());
        pet.setTags(generateTags());
        pet.setStatus("available");
        return pet;
    }

    private CategoryModel generateCategory() {
        CategoryModel category = new CategoryModel();
        category.setId(faker.number().randomDigitNotZero());
        category.setName(faker.animal().name());
        return category;
    }

    private List<String> generatePhotoUrls() {
        List<String> photoUrls = new ArrayList<>();
        photoUrls.add(faker.internet().image());
        return photoUrls;
    }

    private List<TagModel> generateTags() {
        List<TagModel> tags = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            TagModel tag = new TagModel();
            tag.setId(faker.number().randomDigitNotZero());
            tag.setName(faker.book().title());
            tags.add(tag);
        }
        return tags;
    }
}
