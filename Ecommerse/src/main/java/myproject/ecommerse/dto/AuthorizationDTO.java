package myproject.ecommerse.dto;

import lombok.Data;
import myproject.ecommerse.enum1.Role;
@Data
public class AuthorizationDTO {
    private Integer customerId;
    private String firstName;


    private String lastName;


    //@Column(unique = true)
    private String email;


    private String password;


    private Role role;
}
