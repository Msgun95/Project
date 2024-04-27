
package myproject.ecommerse.service.imp;

import myproject.ecommerse.repository.AddressRepository;
import myproject.ecommerse.service.imp.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;

class  AddressServiceTest {

    private AddressService addressService;

    @Mock
    private AddressRepository addressRepo; // Assuming AddressRepository is the interface

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        addressService = new AddressService(addressRepo); // Correct the instantiation to match the class name
    }

    @Test
    public void testDeleteAddress() {
        int idToDelete = 1;

        // Perform the action
        addressService.deleteAddress(idToDelete);

        // Verify the deleteById method was called on the repository
        verify(addressRepo).deleteById(idToDelete);
    }
}
