package myproject.ecommerse.repository;

import myproject.ecommerse.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> deleteByToken(String token);

   Optional<Token> findByToken (String token);


}


