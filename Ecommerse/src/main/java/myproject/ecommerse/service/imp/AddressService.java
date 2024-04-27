package myproject.ecommerse.service.imp;

import lombok.RequiredArgsConstructor;
import myproject.ecommerse.exception.AddressNotFoundException;
import myproject.ecommerse.model.Address;
import myproject.ecommerse.repository.AddressRepository;
import myproject.ecommerse.service.IAddressService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService  implements IAddressService {


    private final AddressRepository addressRepo;
    @Override
    public Address addAdress(Address address) {
       return addressRepo.save(address);



    }

    @Override
    public List<Address> getAllAddress() {
        return addressRepo.findAll();
    }

    @Override
    public Address getAddressById(int id) {
        return addressRepo.findById(id).orElseThrow(()-> new AddressNotFoundException("Address does not exist"));
    }

    @Override
    public void UpdateAddress(Address newaddress) {
        Optional<Address> address1 = addressRepo.findById(newaddress.getAddressId());
        if(address1.isPresent()){
            Address existAddress = address1.get();
            existAddress.setAddressType(newaddress.getAddressType());
            existAddress.setStreet(newaddress.getStreet());
            existAddress.setCity(newaddress.getCity());
            existAddress.setState(newaddress.getState());
            existAddress.setZipCode(newaddress.getZipCode());

            addressRepo.save(existAddress);
        }
        else {
            throw new AddressNotFoundException("The addres witj Id " + newaddress.getAddressId() + " no exist");
        }


    }

    @Override
    public void  deleteAddress(int id) {
         addressRepo.deleteById(id);
       // return ;
    }
}
