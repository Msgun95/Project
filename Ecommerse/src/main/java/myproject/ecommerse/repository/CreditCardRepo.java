package myproject.ecommerse.repository;


import myproject.ecommerse.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepo extends JpaRepository<CreditCard, Integer> {
}
