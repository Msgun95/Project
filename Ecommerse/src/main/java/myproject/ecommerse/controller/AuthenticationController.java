package myproject.ecommerse.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import myproject.ecommerse.dto.AuthorizationDTO;
import myproject.ecommerse.dto.CustomerDTO;
import myproject.ecommerse.model.AuthenticationResponse;
import myproject.ecommerse.model.Login;
import myproject.ecommerse.service.imp.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")

 public ResponseEntity<String> register(
     @Valid @RequestBody AuthorizationDTO request
  ) {
    return ResponseEntity.ok(service.register(request));

  }


  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody Login request
  ) {
    return ResponseEntity.ok(service.login(request));
  }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request){
        String token = extractTokenFromRequest(request);
        if(token != null){
            service.logout(token);
            return  ResponseEntity.ok("Logout Successful");
        }else {
            return ResponseEntity.badRequest().body("Token extraction failed");
        }}

    private String extractTokenFromRequest(HttpServletRequest request) {

        String authorizationHeader =request.getHeader("Authorization");
        if(authorizationHeader != null && authorizationHeader.startsWith("access_token")){
            return authorizationHeader.substring(7);
        }
        return  null;
    }


  }
