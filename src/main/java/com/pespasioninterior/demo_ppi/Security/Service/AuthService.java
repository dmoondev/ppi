package com.pespasioninterior.demo_ppi.Security.Service;

import com.pespasioninterior.demo_ppi.Security.Entity.AuthResponse;
import com.pespasioninterior.demo_ppi.Security.Request.LoginRequest;
import com.pespasioninterior.demo_ppi.Security.Request.RegisterRequest;
import com.pespasioninterior.demo_ppi.Security.Entity.User;
import com.pespasioninterior.demo_ppi.Security.Enum.Role;
import com.pespasioninterior.demo_ppi.Security.Repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class AuthService {
 
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JavaMailSender mailSender;

    // Constructor manual
    public AuthService(UserRepository userRepository, JwtService jwtService, 
    		PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
    		JavaMailSender mailSender) {
    	
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.mailSender = mailSender;
    }
    
    public AuthResponse login(LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken userPass = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            authenticationManager.authenticate(userPass);
            UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
            String token = jwtService.getToken(user);
            return new AuthResponse(token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error en la autenticación");
        }
    }
    
    public AuthResponse register(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setCountry(request.getCountry());
        user.setRole(Role.ADMIN);
        user.setImg(request.getImg());
        
        userRepository.save(user);
        
        return new AuthResponse(jwtService.getToken(user));
    }
    
    public void forgotPassword(String email) {
    	Optional<User> userOptional = userRepository.findByEmail(email);
    	if(userOptional.isPresent()) {
    		User user = userOptional.get();
    		String token = UUID.randomUUID().toString();
    		LocalDateTime expiryDate = LocalDateTime.now().plusHours(1);
    		
    		user.setResetToken(token);
    		user.setTokenExpiryDate(expiryDate);
    		userRepository.save(user);
    		
    		sendResetEmail(user.getEmail(), token);
    	}
    	else {
    		throw new RuntimeException("Usuario no encontrado");
    	}
    }
    
    public void resetPassword(String token, String newPassword) {
    	Optional<User> userOptional = userRepository.findByResetToken(token);
    	if(userOptional.isPresent()) {
    		User user = userOptional.get();
    		if(user.getTokenExpiryDate().isAfter(LocalDateTime.now())) {
    			user.setPassword(passwordEncoder.encode(newPassword));
    			user.setResetToken(null);
    			user.setTokenExpiryDate(null);
    			userRepository.save(user);
    		}
    		else {
    			throw new RuntimeException("Token expirado.");
    		}
    	}
    	else {
    		throw new RuntimeException("Token inválido.");
    	}
    }
    
    private void sendResetEmail(String email, String token) {
    	String resetUrl = "http://localhost:4200/reset-password?token=" + token;
    	SimpleMailMessage message = new SimpleMailMessage();
    	message.setTo(email);
    	message.setSubject("Restablecimiento de contraseña");
    	message.setText("Para restablecer su contraseña, haga clic en el siguiente enlace:\n\n" + resetUrl);
    	mailSender.send(message);
    }
}
