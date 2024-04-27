package myproject.ecommerse.dto;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myproject.ecommerse.enum1.OrderStatus;
import myproject.ecommerse.model.Address;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Integer orderId;
    private LocalDateTime createdAt;
    private Double totalPrice;
    private Double orderAmount;
    private OrderStatus orderstatus;
    private CustomerDTO customerDTO;
    private List<ItemDTO> itemDTOList;
   private Address shippingAddress;

}
