package models.petModels;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class PetModel {
    private int id;
    private CategoryModel category;
    private String name;
    private List<String> photoUrls;
    private List<TagModel> tags;
    private String status;
}
