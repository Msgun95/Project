package myproject.ecommerse.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jdk.jfr.Unsigned;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import myproject.ecommerse.enum1.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Customer implements UserDetails {


    @Id
    @GeneratedValue
    private Integer customerId;
    @NotBlank(message ="first-Name Must be have")
    private String firstName;
    @NotBlank(message ="Last-name must be have")
    private String lastName;


    //@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#])[A-Za-z\\d@#]+$", message = "email must include at least one alphabet, one number, and one of '@' or '#'")
    @NotBlank(message ="email must be have")
     @Column(unique = true)
    private String email;

    @NotBlank(message ="password must be have")
   // @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#])[A-Za-z\\d@#]+$", message = "Password must include at least one alphabet, one number, and one of '@' or '#'")
    private String password;


    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private List<Address> addressList;


    @JoinColumn(name = "customer_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CreditCard> creditCards;

    @JoinColumn(name = "customer_id")
 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Order> orderList;


    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Review> reviews;

    @OneToMany()
    @JoinTable(name = "customer_shopping_cart",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> shoppingCart;









    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}