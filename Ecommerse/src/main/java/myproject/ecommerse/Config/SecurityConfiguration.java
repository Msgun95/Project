package myproject.ecommerse.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import static myproject.ecommerse.enum1.Role.ADMIN;
import static myproject.ecommerse.enum1.Role.CUSTOMER;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
//import static sun.net.ftp.FtpDirEntry.Permission.USER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final String[] WHITE_LIST_URL = {"/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
         //   "/address/**",
        //    "item/**",
          //  " /api/v1/auth/login"
          //  "/api/v1/auth/register",
          //  "/api/customers/register"

    }
            ;
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
   //private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
              //  .disable()
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers("/api/v1/auth/**").hasAnyRole(CUSTOMER.name(), ADMIN.name())
                                .requestMatchers(HttpMethod.POST,"/customer/**").hasAnyRole(CUSTOMER.name())
                                .requestMatchers(HttpMethod.GET,"/customer/**").hasAnyRole(CUSTOMER.name())
                                .requestMatchers(HttpMethod.PUT,"/customer/**").hasAnyRole(CUSTOMER.name())
                                .requestMatchers(HttpMethod.DELETE,"/customer/**").hasAnyRole(CUSTOMER.name())

                                .requestMatchers(HttpMethod.POST,"/creditcard/**", "/address/**", "item/**"
                                        , "review/**", "stock/**", "order/**").authenticated()

                                .requestMatchers(HttpMethod.GET,"/creditcard/**", "/address/**", "item/**"
                                        , "review/**", "stock/**", "order/**").authenticated()

                                .requestMatchers(HttpMethod.DELETE,"/creditcard/**", "/address/**", "item/**"
                                        , "review/**", "stock/**", "order/**").authenticated()

                                .requestMatchers(HttpMethod.PUT,"/creditcard/**", "/address/**", "item/**"
                                        , "review/**", "stock/**", "order/**").authenticated()
                               // .requestMatchers(GET, "/api/v1/auth/**").hasAnyAuthority(USER.name()) //, MANAGER_READ.name())
                        //      .requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
//                                .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
//                                .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/v1/auth/logout")
                              //  .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )
        ;

        return http.build();
    }
}



