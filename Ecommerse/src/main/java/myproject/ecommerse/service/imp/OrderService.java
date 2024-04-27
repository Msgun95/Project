package myproject.ecommerse.service.imp;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import myproject.ecommerse.dto.CustomerDTO;
import myproject.ecommerse.dto.ItemDTO;
import myproject.ecommerse.dto.OrderDTO;
import myproject.ecommerse.enum1.OrderStatus;
import myproject.ecommerse.exception.CustomerNotFoundException;
import myproject.ecommerse.exception.ItemNotFoundException;
import myproject.ecommerse.exception.OrderNotFoundException;
import myproject.ecommerse.model.*;
import myproject.ecommerse.repository.CustomerRepo;
import myproject.ecommerse.repository.ItemsRepo;
import myproject.ecommerse.repository.OrderRepo;
import myproject.ecommerse.service.IOrderService;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;
    private final ItemsRepo itemsRepo;
    private final ModelMapper modelMapper;


//    @Override
//    public List<OrderDTO> getAllOrders(Integer customerId) {
//
//         List<Order> order = orderRepo.findByCustomerId(customerId);
//        return order.stream().map(order1 -> modelMapper.map(order1, OrderDTO.class)).toList();
//    }
//@Transactional(readOnly = true)  // Ensure transactional context for lazy loading outside repository
public List<OrderDTO> getCustomerOrders(Integer customerId) {
    List<Order> orders = orderRepo.findByCustomerId(customerId);
   return orders.stream().map(order -> {
                OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
                if (order.getCustomer() != null) {
                    CustomerDTO customerDTO = modelMapper.map(order.getCustomer(), CustomerDTO.class);
                    orderDTO.setCustomerDTO(customerDTO);
                }
               // Address address = modelMapper.map(order.getShippingAddress(), Address.class);
                List<ItemDTO> itemDTOs = order.getItemsList().stream()
                        .map(item -> modelMapper.map(item, ItemDTO.class))
                        .collect(Collectors.toList());
                orderDTO.setItemDTOList(itemDTOs);
                return orderDTO;
            })
            .collect(Collectors.toList());

}

    @Override
    public OrderDTO getOrderDetail(Integer orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow(()->
                new OrderNotFoundException("order with Id " + orderId+ " does not exist"));

        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        CustomerDTO customerDTO = modelMapper.map(order.getCustomer(), CustomerDTO.class);
        orderDTO.setCustomerDTO(customerDTO);
        List<ItemDTO> itemDTOS = order.getItemsList().stream().map(item ->
                modelMapper.map(item, ItemDTO.class)
        ).toList();
        orderDTO.setItemDTOList(itemDTOS);
        return orderDTO;
    }
    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepo.findAll();
        return orders.stream().map(order -> modelMapper.map(order, OrderDTO.class)).toList();
    }

    @Override
    public OrderDTO CreateOrder ( OrderDTO orderDTO, Integer customerId, List<Integer> itemId) {
        Order order = modelMapper.map(orderDTO, Order.class);
        if (order.getCustomer() == null || order.getCustomer().getCustomerId() == null){
            throw new IllegalArgumentException("Customer information is required.");
        }
        else {
            order.setOrderstatus(OrderStatus.NEW);
         //   order = orderRepo.save(order);
            Customer customer = customerRepo.findById(customerId).orElseThrow(()->new CustomerNotFoundException("no customer"));
            order.setCustomer(customer);


            List<Item> itemlist = itemId.stream()
                    .map(item -> itemsRepo.findById(item)
                            .orElseThrow(() -> new ItemNotFoundException("Item does not exist with ID: " + item)))
                    .collect(Collectors.toList());
            order.setItemsList(itemlist);
            order = orderRepo.save(order);

         OrderDTO orderDTO1 = modelMapper.map(order, OrderDTO.class);

            CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
            orderDTO1.setCustomerDTO(customerDTO);
            List<ItemDTO> itemDTOS = order.getItemsList().stream().map(item ->
                modelMapper.map(item, ItemDTO.class)
            ).toList();
            orderDTO1.setItemDTOList(itemDTOS);
            return orderDTO1;
    }}




    @Override
    public OrderDTO addItemToOrder(Integer orderId, List<Item> items) throws OrderNotFoundException {

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));

        if (!order.getOrderstatus().equals(OrderStatus.NEW)) {
            throw new IllegalArgumentException("Order is not in NEW status.");
        }

        // Map to track existing items by item ID for quick lookup and update
        Map<Integer, Item> existingItemsMap = order.getItemsList().stream()
                .collect(Collectors.toMap(Item::getItemId, item -> item, (item1, item2) -> item1));

        // Iterate over the provided items to add or update quantities
        for (Item newItem : items) {
            if (newItem.getItemId() == null) {
                throw new IllegalArgumentException("Item ID cannot be null.");
            }

            Item existingItem = existingItemsMap.get(newItem.getItemId());
            if (existingItem != null) {
                // Item exists in the order, update the quantity
                existingItem.setQuantityAvailable(existingItem.getQuantityAvailable() + newItem.getQuantityAvailable());
            } else {
                // New item, add it to the order
                order.getItemsList().add(newItem);
            }
        }

        Customer customer = order.getCustomer();
        if (customer == null || customer.getCustomerId() == null) {
            throw new IllegalArgumentException("Valid customer information is required for the order.");
        }

        Customer refreshedCustomer = customerRepo.findById(customer.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + customer.getCustomerId()));
        order.setCustomer(refreshedCustomer);
        order = orderRepo.save(order);

        OrderDTO updatedOrderDTO = modelMapper.map(order, OrderDTO.class);
        return updatedOrderDTO;
    }





//    @Override

    public Order orderPayment(Integer orderId, int creditCardId, Integer customerId) throws OrderNotFoundException {
        Order order = orderRepo.findById(orderId).orElse(null);
        if (order != null) {
            Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer with this id " + customerId + " does not exist"));

            Optional<CreditCard> matchingCard = customer.getCreditCards().stream()
                    .filter(card -> card.getCardnumber().equals(creditCardId))
                    .findFirst();

            order.setCreditCard(matchingCard.get());
            order.setOrderstatus(OrderStatus.PROCESSED);

            order.setCustomer(customer);
            order = orderRepo.save(order);

        }
        return order;
    }



    @Override
    public OrderStatus setStatus(Integer orderId, OrderStatus status) {
        Order order = orderRepo.findById(orderId).orElseThrow(()->new OrderNotFoundException("order does not exist") );
        if(order!=null){
            order.setOrderstatus(status);
            orderRepo.save(order);
        }

       return  status;
    }


    @Override
    public Order getOrderById(int id) {
        return null;
    }

    @Override
    public void deleteOrder(int orderid) {
        Optional<Order> order = orderRepo.findById(orderid);
        if (order.isPresent()) {
            Order order1 = order.get();
        order1.setCustomer(null);

            orderRepo.deleteById(orderid);
        }
    }

}
