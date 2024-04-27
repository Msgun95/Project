package myproject.ecommerse.repository;

import myproject.ecommerse.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepo extends JpaRepository<Item, Integer> {
}
