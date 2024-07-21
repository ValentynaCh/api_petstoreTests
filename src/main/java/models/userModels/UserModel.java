package models.userModels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserModel {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String userStatus;
}
