package gov.iti.jets.services;
import gov.iti.jets.models.dtos.authentication.AuthenticationRequestDTO;
import gov.iti.jets.models.dtos.authentication.AuthenticationResponseDTO;
import gov.iti.jets.models.dtos.authentication.RegisterRequestDTO;
import gov.iti.jets.models.entities.User;
import gov.iti.jets.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDTO register(RegisterRequestDTO request) {
        User user = User.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .age(request.getAge())
                .gender(request.getGender())
                .build();
        var savedUser = repository.save(user);
        Map<String, Object> claims = new HashMap<>();
        claims.put("role","user" );
        claims.put("id", savedUser.getId());
        claims.put("name", savedUser.getUserName());
        claims.put("gender", savedUser.getGender());
        var jwtToken = jwtService.generateToken(claims, savedUser);
        return AuthenticationResponseDTO.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        Map<String, Object> claims = new HashMap<>();
        if(user.getIsAdmin()){
            claims.put("role","admin" );
        }
        else {
            claims.put("role","user" );
        }
        claims.put("id", user.getId());
        claims.put("name", user.getUserName());
        claims.put("gender", user.getGender());
        var jwtToken = jwtService.generateToken(claims, user);
        return AuthenticationResponseDTO.builder()
                .accessToken(jwtToken)
                .build();
    }


}