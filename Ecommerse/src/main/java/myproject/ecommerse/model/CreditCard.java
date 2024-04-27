package myproject.ecommerse.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CreditCard {

    @Id
    @GeneratedValue
    private Integer creditCardId;
    @Column(unique = true)
    private Long cardnumber;

    private LocalDate expireDate;
    @Column(unique = true)
    private Integer securityNumber;

}
