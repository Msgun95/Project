package myproject.ecommerse.service.imp;

import lombok.RequiredArgsConstructor;

import myproject.ecommerse.dto.CreditCardDTO;
import myproject.ecommerse.exception.CustomerNotFoundException;
import myproject.ecommerse.model.CreditCard;
import myproject.ecommerse.model.Customer;
import myproject.ecommerse.repository.CreditCardRepo;
import myproject.ecommerse.repository.CustomerRepo;
import myproject.ecommerse.service.ICreditCard;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CreditCardService implements ICreditCard {
    private  final CreditCardRepo creditCardRepo;
    private final ModelMapper modelMapper;
    private final CustomerRepo customerRepo;

    @Override
    public CreditCardDTO addCreditCard(int customerId, CreditCardDTO creditCardDTO) {
        Customer customer = customerRepo.findById(customerId).orElseThrow(()->
                new CustomerNotFoundException("Customer with the Id "+ customerId+" does not exist"));
        if (customer !=null){
            CreditCard creditCard = modelMapper.map(creditCardDTO, CreditCard.class);
            CreditCard savedcreditCard = creditCardRepo.save(creditCard);
           customer.getCreditCards().add(savedcreditCard);
           // customer.setCreditCards(savedcreditCard);
            customerRepo.save(customer);
            return modelMapper.map(customer.getCreditCards(), CreditCardDTO.class);
        }

        return null;

    }

    @Override
    public List<CreditCardDTO> getAllCreditCards() {
        List<CreditCard> creditCard =creditCardRepo.findAll();
        return creditCard.stream().map(creditCard1 -> modelMapper
                .map(creditCard1,CreditCardDTO.class)).toList();
    }

    @Override
    public CreditCardDTO getCreditCardById(int id) {
      CreditCard creditCard =  creditCardRepo.findById(id)
              .orElseThrow(()-> new RuntimeException("This Credit card with " +id +" does not exist"));
        return modelMapper.map(creditCard, CreditCardDTO.class);
    }

    @Override
    public CreditCardDTO deleteCreditCard(int id) {
        creditCardRepo.findById(id).get();
        return new CreditCardDTO() ;
    }

    @Override
    public CreditCardDTO updateCreditCard(int id, CreditCardDTO creditCardDTO) {
        CreditCard existcreditCard =  creditCardRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("This Credit card with " +id +" does not exist"));
        existcreditCard.setCardnumber(creditCardDTO.getCardnumber());
        existcreditCard.setExpireDate(creditCardDTO.getExpireDate());
        existcreditCard.setSecurityNumber(creditCardDTO.getSecurityNumber());

        return modelMapper.map(creditCardRepo.save(existcreditCard), CreditCardDTO.class);
    }
}
