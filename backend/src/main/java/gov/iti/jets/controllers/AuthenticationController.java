package gov.iti.jets.controllers;

import gov.iti.jets.models.dtos.authentication.AuthenticationRequestDTO;
import gov.iti.jets.models.dtos.authentication.AuthenticationResponseDTO;
import gov.iti.jets.models.dtos.authentication.RegisterRequestDTO;
import gov.iti.jets.services.AuthenticationService;
import gov.iti.jets.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

//@CrossOrigin(origins = "http://localhost:4200")
@Validated
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register( @RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody AuthenticationRequestDTO request ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}
