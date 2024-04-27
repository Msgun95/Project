package myproject.ecommerse.service;



import myproject.ecommerse.model.Address;

import java.util.List;

public interface IAddressService {

     Address addAdress(Address address);
     List<Address> getAllAddress();
     Address getAddressById(int id);
     void UpdateAddress(Address address);
     void deleteAddress(int id);

}
