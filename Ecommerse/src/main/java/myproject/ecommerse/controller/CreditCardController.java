package myproject.ecommerse.controller;

import lombok.RequiredArgsConstructor;
import myproject.ecommerse.dto.CreditCardDTO;
import myproject.ecommerse.service.imp.CreditCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/credit-card")
public class CreditCardController {
    private final CreditCardService creditCardService;

    @PostMapping("/customer/{customerId}")
    public ResponseEntity<CreditCardDTO> createCreditCard(@PathVariable Integer customerId, @RequestBody CreditCardDTO creditCardDTO){
        CreditCardDTO creditCardDTO1 =creditCardService.addCreditCard(customerId, creditCardDTO);
        return ResponseEntity.ok(creditCardDTO1);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CreditCardDTO>> retrieveAllCreditCardofTheCustomer(){
        return ResponseEntity.ok(creditCardService.getAllCreditCards());
    }
    @GetMapping
    public ResponseEntity<List<CreditCardDTO>> retrieveAllCreditCard(){
        return ResponseEntity.ok(creditCardService.getAllCreditCards());
    }
    @GetMapping("/{creditcardId}")
    public ResponseEntity<CreditCardDTO> retrieveCreditCardById(@PathVariable Integer creditcardId){
        return ResponseEntity.ok(creditCardService.getCreditCardById(creditcardId));
    }
    @PutMapping("/{credicardId}")
    public ResponseEntity<CreditCardDTO> updateCreditCard(@PathVariable Integer credicardId, @RequestBody CreditCardDTO creditCardDTO){
        return ResponseEntity.ok(creditCardService.updateCreditCard(credicardId, creditCardDTO));
    }

    @DeleteMapping("/{id}")
    public void removeCreditCard(@PathVariable Integer id){
        creditCardService.deleteCreditCard(id);
    }


}
