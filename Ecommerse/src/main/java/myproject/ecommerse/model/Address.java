package myproject.ecommerse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myproject.ecommerse.enum1.AddressType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue
    private Integer addressId; // address_id
    @NotBlank(message ="street must be provided")
    private String street;
    @NotBlank(message ="city must be provided")
    private String city;
    @NotBlank(message ="state must be provided")
    private String state;
    //@Column(length = 16)
    @NotBlank(message ="zipcode must be provided")
    private String zipCode;

    @Enumerated
    private AddressType addressType;

//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private Customer customer;
}
