package myproject.ecommerse.Config;


import lombok.RequiredArgsConstructor;
import myproject.ecommerse.model.Customer;
import myproject.ecommerse.repository.CustomerRepo;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final CustomerRepo customerRepo;


    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public class WebSecurityConfigurerAdapter {

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return username ->{
//            Optional<User> user =userrepository.findByEmail(username) ;
//            if (user.isPresent()) {
//                return user.get();
//            }
            // Try to find the user in the CustomerRepository
            Optional<Customer> customer = customerRepo.findByEmail(username);

            if (customer.isPresent()) {
                return customer.get();
            }
            throw new UsernameNotFoundException("Customer not found with username: " + username);
        };
    }





}
