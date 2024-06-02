package com.pespasioninterior.demo_ppi.Security.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    
    String username;
    String password;
    
    
}
