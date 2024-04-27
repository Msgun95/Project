package myproject.ecommerse.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myproject.ecommerse.model.Customer;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDTO {

    private Integer id;
    @Column(unique = true)
    private Long cardnumber;

    private LocalDate expireDate;
    @Column(unique = true)
    private Integer securityNumber;
}
