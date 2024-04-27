package myproject.ecommerse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import myproject.ecommerse.enum1.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Order_table")
public class Order {

    @Id
    @GeneratedValue
    private Integer orderId;
    private LocalDateTime createdAt;
    private Double totalPrice;
    //private Double orderAmount;
    @Enumerated
    private OrderStatus orderstatus;

  //  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId")
//    private List<Item> itemsList;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Item> itemsList;


    @ManyToOne(cascade = CascadeType.ALL, fetch =  FetchType.EAGER)
    private Customer customer;

    @ManyToOne
    private Address shippingAddress;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private CreditCard creditCard;


//    public void orderAmount(Item item, Order order){
//        item.getPrice()* order.
//    }

}
