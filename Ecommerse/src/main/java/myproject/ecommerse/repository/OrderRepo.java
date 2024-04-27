package myproject.ecommerse.repository;

import myproject.ecommerse.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order, Integer> {
   // @Override
    @Query ("select o from Order o where o.customer.customerId = :customerId ")
    List<Order> findByCustomerId(Integer customerId);
}
