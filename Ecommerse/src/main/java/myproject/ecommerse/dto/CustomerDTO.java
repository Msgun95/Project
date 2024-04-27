package myproject.ecommerse.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import myproject.ecommerse.enum1.Role;
import myproject.ecommerse.model.*;

import java.util.List;

@Data

public class CustomerDTO {

    private Integer customerId;
    private String firstName;


    private String lastName;


//@Column(unique = true)
    private String email;


    private String password;


    private Role role;



    private List<Address> addressList;

//
//    private List<CreditCard> creditCardList;
//
//
//    private List<Order> orderList;
//
//
//    private List<Review> reviews;


}
