package myproject.ecommerse.service;



import myproject.ecommerse.dto.CustomerDTO;

import java.util.List;


public interface ICustomerService {

    CustomerDTO addCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(int id);
    void deleteCustomer(int id);

    void updateCustomer(int id, CustomerDTO customerDTO);
}
