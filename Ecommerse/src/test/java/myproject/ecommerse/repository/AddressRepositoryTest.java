package myproject.ecommerse.repository;

import myproject.ecommerse.enum1.AddressType;
import myproject.ecommerse.model.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@DataJpaTest
class AddressRepositoryTest {
//
//    @Autowired  // Injects the repository managed by Spring into your test
//    private AddressRepository addressRepository;
//
//    @Test
//    void itShouldExistAddress(){
//        //given
//       Integer id = 1;
//
//        Address address = new Address(
//                id,
//                "123 sandvalley",
//                "city",
//                "state",
//                "2234",
//                AddressType.BillingAddress
//        );
//        addressRepository.save(address);
//        //when
//      boolean expected=   addressRepository.findById(id).isPresent();
//        //then
//        assertThat(expected).isTrue();
//
//    }

    @Mock  // Injects the repository managed by Spring into your test
    private AddressRepository addressRepository;


    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void itShouldExistAddress(){
        //given
        Integer id = 1;

        Address address = new Address(
                id,
                "123 sandvalley",
                "city",
                "state",
                "2234",
                AddressType.BillingAddress
        );
        when(addressRepository.findById(id)).thenReturn(java.util.Optional.of(address));
        addressRepository.save(address);
        //when
        boolean expected=   addressRepository.findById(id).isPresent();
        //then
        assertThat(expected).isTrue();
        verify(addressRepository).save(address);
        verify(addressRepository).findById(id);

    }

}