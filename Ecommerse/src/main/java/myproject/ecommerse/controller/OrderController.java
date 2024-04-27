package myproject.ecommerse.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import myproject.ecommerse.dto.OrderDTO;
import myproject.ecommerse.dto.SetStatusDTO;
import myproject.ecommerse.enum1.OrderStatus;
import myproject.ecommerse.exception.OrderNotFoundException;
import myproject.ecommerse.model.CreditCard;
import myproject.ecommerse.model.Item;
import myproject.ecommerse.model.Order;
import myproject.ecommerse.repository.OrderRepo;
import myproject.ecommerse.service.imp.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;


    @PostMapping("customer/{customerId}/item")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody @Valid OrderDTO orderDTO,
                                                @PathVariable Integer customerId,
                                                @RequestParam List<Integer> itemId) {
        return ResponseEntity.ok(orderService.CreateOrder(orderDTO, customerId,itemId));
    }

    @GetMapping("/customer/{customerId}/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders(@PathVariable  Integer customerId) {
        return ResponseEntity.ok(orderService.getCustomerOrders(customerId));

    }
    @GetMapping("")
    public ResponseEntity<List<OrderDTO>> getALLOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());

    }


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrdersDetails(@PathVariable Integer orderId) {
        return ResponseEntity.ok(orderService.getOrderDetail(orderId));

    }


    @PutMapping("/{orderId}/item")
    public ResponseEntity<OrderDTO> addItemToOrder(@PathVariable Integer orderId, @RequestBody List<Item> item) throws OrderNotFoundException {
        OrderDTO updatedOrder = orderService.addItemToOrder(orderId, item);
        return ResponseEntity.ok(updatedOrder);
    }

    @PutMapping("/{orderId}/customer/{customerId}/creditCard/{creditCardId}")
    public ResponseEntity<Order> orderPayment(@PathVariable Integer orderId ,@PathVariable Integer customerId, @PathVariable int creditCardId) throws OrderNotFoundException {
        Order paidOrder = orderService.orderPayment(orderId, creditCardId, customerId);
        return ResponseEntity.ok(paidOrder);
    }
    @PutMapping("/{orderId}/status/{status}")
    public ResponseEntity<OrderStatus> setStatus(@PathVariable  Integer orderId, @PathVariable("status") OrderStatus status){
        return ResponseEntity.ok(orderService.setStatus(orderId, status));
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder (@PathVariable Integer orderId){
        orderService.deleteOrder(orderId);
    }
}