package myproject.ecommerse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue
    private Integer reviewId;
    private String title;
    private String description;
    private Integer numberofStars;
    private LocalDate date;

    @ManyToOne(  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private Customer customer;
    @JoinColumn(name = "itemId")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Item item;
}
