package myproject.ecommerse.controller;

import lombok.RequiredArgsConstructor;
import myproject.ecommerse.dto.CustomerDTO;

import myproject.ecommerse.service.imp.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO savedCustomerDTO = customerService.addCustomer(customerDTO);
        return ResponseEntity.ok(savedCustomerDTO);
    }
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> retrieveAllCustomers(){
       return ResponseEntity.ok(customerService.getAllCustomers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> retrieveCustomerById(@PathVariable int id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void>  removeCustomer (@PathVariable  int customerId){
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }
@PutMapping("/{id}")
    public void updateCustomer(@PathVariable int id, @RequestBody CustomerDTO customerDTO){
         customerService.updateCustomer(id, customerDTO);
    }

}
