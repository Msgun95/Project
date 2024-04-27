package myproject.ecommerse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myproject.ecommerse.enum1.OrderStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetStatusDTO {

    private OrderStatus orderstatus;
}
