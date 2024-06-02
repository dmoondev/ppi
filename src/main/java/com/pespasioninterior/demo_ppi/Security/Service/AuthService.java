package com.pespasioninterior.demo_ppi.Security.Service;

import com.pespasioninterior.demo_ppi.Security.Entity.AuthResponse;
import com.pespasioninterior.demo_ppi.Security.Request.LoginRequest;
import com.pespasioninterior.demo_ppi.Security.Request.RegisterRequest;
import com.pespasioninterior.demo_ppi.Security.Entity.User;
import com.pespasioninterior.demo_ppi.Security.Enum.Role;
import com.pespasioninterior.demo_ppi.Security.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
 
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    
    public AuthResponse login(LoginRequest request){
//        UsernamePasswordAuthenticationToken userPass = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
//        System.out.println("User: " + userPass);
//        authenticationManager.authenticate(userPass);
//        System.out.println("User: " + userPass);
//        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
//        String token = jwtService.getToken(user);
//        return AuthResponse.builder()
//                .token(token)
//                .build();

try {
            UsernamePasswordAuthenticationToken userPass = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            authenticationManager.authenticate(userPass);
            UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
            String token = jwtService.getToken(user);
            return AuthResponse.builder()
                    .token(token)
                    .build();
        } catch (Exception e) {
            e.printStackTrace(); // Agrega logs para ayudar en la depuración
            throw new RuntimeException("Error en la autenticación");
        }
    }
    
    public AuthResponse register(RegisterRequest request){
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.getLastname()) 
                .country(request.getCountry())
                .role(Role.USER)
                .build();
        
        userRepository.save(user);
        
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
    
    
}

