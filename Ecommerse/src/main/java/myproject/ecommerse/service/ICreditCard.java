package myproject.ecommerse.service;




import myproject.ecommerse.dto.CreditCardDTO;

import java.util.List;

public interface ICreditCard {

    CreditCardDTO addCreditCard(int customerId, CreditCardDTO creditCardDTO);
    List<CreditCardDTO> getAllCreditCards();
    CreditCardDTO getCreditCardById(int id);

    CreditCardDTO deleteCreditCard(int id);

    CreditCardDTO updateCreditCard(int id,CreditCardDTO creditCardDTO);

}
