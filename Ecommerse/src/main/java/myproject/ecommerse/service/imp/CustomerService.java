package myproject.ecommerse.service.imp;

import lombok.RequiredArgsConstructor;
import myproject.ecommerse.dto.CustomerDTO;
import myproject.ecommerse.exception.CustomerNotFoundException;
import myproject.ecommerse.model.Address;
import myproject.ecommerse.model.Customer;
import myproject.ecommerse.model.Review;
import myproject.ecommerse.repository.AddressRepository;
import myproject.ecommerse.repository.CustomerRepo;
import myproject.ecommerse.service.ICustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepo customerRepo;
    private final ModelMapper modelMapper;
    private final AddressRepository addressRepo;
    @Override

//    }

//    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
//        Customer customer = modelMapper.map(customerDTO, Customer.class);
//
//        List<Address> addressList = Optional.ofNullable(customerDTO.getAddressList())
//                .orElseGet(Collections::emptyList)
//                .stream()
//                .map(addressDTO -> addressDTO.getAddressId() != null ?
//                        addressRepo.findById(addressDTO.getAddressId()).orElse(null) :
//                        addressRepo.save(modelMapper.map(addressDTO, Address.class)))
//                .filter(Objects::nonNull)
//                .collect(Collectors.toList());
//
//       customer.setAddressList(addressList);
//       // customer.getAddressList().addAll(addressList);
//
//        return modelMapper.map(customerRepo.save(customer), CustomerDTO.class);
//    }


    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);

        List<Address> addressList = Optional.ofNullable(customerDTO.getAddressList())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(addressDTO -> {
                    if (addressDTO.getAddressId() != null) {
                        // If addressId is provided, check if the address exists
                        return addressRepo.findById(addressDTO.getAddressId()).orElse(null);
                    } else {
                        // If addressId is not provided, save the address
                        return addressRepo.save(modelMapper.map(addressDTO, Address.class));
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

//        if (addressList.isEmpty()) {
//            // If no address is provided or added, return null or handle the error as per your requirement
//            return null; // or throw an exception
//        }

        // Set the address list to the customer

            customer.setAddressList(addressList);


        // customer.getCreditCards().add(savedcreditCard);

        // Save the customer
        customer = customerRepo.save(customer);

        // Map and return the saved customer as DTO
        return modelMapper.map(customer, CustomerDTO.class);
    }







    @Override
    public List<CustomerDTO> getAllCustomers() {

         List<Customer> customer = customerRepo.findAll();
        return customer.stream().
                map(customer1 -> modelMapper.map(customer1, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(int id) {
        Customer customer= customerRepo.findById(id)
                .orElseThrow(()->new CustomerNotFoundException("Customer is not exist in the system"));
        return modelMapper.map(customer, CustomerDTO.class);


    }

    @Override
    public void deleteCustomer(int id) {
        Customer customer= customerRepo.findById(id).orElseThrow(()->
                new CustomerNotFoundException("The customer with ID " + id +" Is already deleted or not exist"));
        List<Address> addresses = customer.getAddressList();
        for (Address address : addresses) {
            addressRepo.delete(address);
        }
        customerRepo.delete(customer);

    }

    @Override
    public void updateCustomer(int id, CustomerDTO customerDTO) {
        Customer existcustomer= customerRepo.findById(id).orElseThrow(()->
                new CustomerNotFoundException("The customer with ID " + id +" Is already deleted or not exist"));

        existcustomer.setFirstName(customerDTO.getFirstName());
        existcustomer.setLastName(customerDTO.getLastName());
        existcustomer.setEmail(customerDTO.getEmail());

          modelMapper.map(customerRepo.save(existcustomer), CustomerDTO.class);

    }
}
