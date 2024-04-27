package myproject.ecommerse.service;

import myproject.ecommerse.dto.OrderDTO;
import myproject.ecommerse.enum1.OrderStatus;
import myproject.ecommerse.model.CreditCard;
import myproject.ecommerse.model.Item;
import myproject.ecommerse.model.Order;

import java.util.List;

public interface IOrderService {

    OrderDTO CreateOrder( OrderDTO orderDTO,  Integer customerId, List<Integer> itemId);
    List<OrderDTO> getAllOrders();
    List<OrderDTO> getCustomerOrders(Integer customerId);
    public OrderDTO getOrderDetail(Integer orderId);
    Order getOrderById(int id);
    void deleteOrder(int id);
    public OrderDTO addItemToOrder(Integer orderId, List<Item> item);
//    public Order orderPayment(Integer orderId, CreditCard creditCard, Integer customerId);

    public OrderStatus setStatus(Integer orderId, OrderStatus status);
}
