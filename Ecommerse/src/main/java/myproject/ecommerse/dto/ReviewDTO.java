package myproject.ecommerse.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myproject.ecommerse.model.Customer;
import myproject.ecommerse.model.Item;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Integer reviewId;
    private String title;
    private String description;
    private Integer numberofStars;
    private LocalDate date;


//    @NotNull
//    private Integer customerId;
//
//    private Integer itemId;
     private CustomerDTO customer;
    private ItemDTO item;
}
