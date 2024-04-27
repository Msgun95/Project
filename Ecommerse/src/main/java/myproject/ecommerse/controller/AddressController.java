package myproject.ecommerse.controller;

import lombok.RequiredArgsConstructor;
import myproject.ecommerse.model.Address;
import myproject.ecommerse.repository.AddressRepository;
import myproject.ecommerse.service.imp.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<Address> addAddress(@RequestBody Address address){

        return ResponseEntity.ok( addressService.addAdress(address));

    }
    @GetMapping("/{id}")
    public ResponseEntity<Address> getByIdAddres(@PathVariable int id){
        return ResponseEntity.ok(addressService.getAddressById(id));
    }
    @GetMapping
    public ResponseEntity<List<Address>> getAllAddress(){
        return ResponseEntity.ok(addressService.getAllAddress());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable int id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

}
