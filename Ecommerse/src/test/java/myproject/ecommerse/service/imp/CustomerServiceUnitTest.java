package myproject.ecommerse.service.imp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import myproject.ecommerse.dto.CustomerDTO;
import myproject.ecommerse.enum1.AddressType;
import myproject.ecommerse.enum1.Role;
import myproject.ecommerse.exception.CustomerNotFoundException;
import myproject.ecommerse.model.Address;
import myproject.ecommerse.model.Customer;
import myproject.ecommerse.repository.AddressRepository;
import myproject.ecommerse.repository.CustomerRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomerService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CustomerServiceUnitTest {
    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private CustomerRepo customerRepo;

    @Autowired
    private CustomerService customerService;

    @MockBean
    private ModelMapper modelMapper;


    @Test
    void addCustomerTest() {
        // Arrange
        when(customerRepo.save(Mockito.<Customer>any())).thenThrow(new CustomerNotFoundException("An error occurred"));

        Customer customer = new Customer();
        customer.setAddressList(new ArrayList<>());
        customer.setCreditCards(new ArrayList<>());
        customer.setCustomerId(1);
        customer.setEmail("msgun.bbb@example.gmail.com");
        customer.setFirstName("Msgun");
        customer.setLastName("Berhe");
        customer.setOrderList(new ArrayList<>());
        customer.setPassword("iloveyou");
        customer.setReviews(new ArrayList<>());
        customer.setRole(Role.CUSTOMER);
        customer.setShoppingCart(new ArrayList<>());
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Customer>>any())).thenReturn(customer);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setAddressList(new ArrayList<>());
        customerDTO.setCustomerId(1);
        customerDTO.setEmail("msgun.bbb@example.gmail.com");
        customerDTO.setFirstName("Msgun");
        customerDTO.setLastName("Berhe");
        customerDTO.setPassword("iloveyou");
        customerDTO.setRole(Role.CUSTOMER);

        // Act and Assert
        assertThrows(CustomerNotFoundException.class, () -> customerService.addCustomer(customerDTO));
        verify(modelMapper).map(isA(Object.class), isA(Class.class));
        verify(customerRepo).save(isA(Customer.class));
    }


    @Test
    void getAllCustomersTest() {
        // Arrange
        when(customerRepo.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<CustomerDTO> actualAllCustomers = customerService.getAllCustomers();

        // Assert
        verify(customerRepo).findAll();
        assertTrue(actualAllCustomers.isEmpty());
    }








    @Test
    void DeleteCustomerTest() {
        // Arrange
        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepo.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(CustomerNotFoundException.class, () -> customerService.deleteCustomer(1));
        verify(customerRepo).findById(eq(1));
    }






}
