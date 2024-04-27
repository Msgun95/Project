package myproject.ecommerse.repository;

import myproject.ecommerse.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopingCartRepo extends JpaRepository<ShoppingCart, Integer> {
}
