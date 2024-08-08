package com.pespasioninterior.demo_ppi.Security.Controller;

import com.pespasioninterior.demo_ppi.Security.Entity.AuthResponse;
import com.pespasioninterior.demo_ppi.Security.Request.RegisterRequest;
import com.pespasioninterior.demo_ppi.Security.Request.ResetPasswordRequest;
import com.pespasioninterior.demo_ppi.Security.Request.ForgotPasswordRequest;
import com.pespasioninterior.demo_ppi.Security.Request.LoginRequest;
import com.pespasioninterior.demo_ppi.Security.Service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "localhost:4200")
public class AuthController {
    
	@Autowired
    private final AuthService authService;

    // Constructor manual
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
    
    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
    
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        authService.forgotPassword(request.getEmail());
        return new ResponseEntity<>(new Mensaje("Se ha enviado un correo electrónico con las instrucciones para restablecer su contraseña."), HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        authService.resetPassword(request.getToken(), request.getNewPassword());
        return new ResponseEntity<>(new Mensaje("Contraseña restablecida correctamente."),HttpStatus.OK);
    }
}
