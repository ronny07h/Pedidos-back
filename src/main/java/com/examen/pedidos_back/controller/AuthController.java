package com.examen.pedidos_back.controller;

import com.examen.pedidos_back.dto.LoginRequest;
import com.examen.pedidos_back.dto.LoginResponse;
import com.examen.pedidos_back.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        String username = (loginRequest.getUsername() != null) ? loginRequest.getUsername().trim() : "";
        String password = (loginRequest.getPassword() != null) ? loginRequest.getPassword().trim() : "";
        
        System.out.println("DEBUG: Intento de login - Usuario: [" + username + "] Clave: [" + password + "]");
        
        // Hardcoded credentials as per user request: admin / admin
        if ("admin".equals(username) && "admin".equals(password)) {
            System.out.println("DEBUG: Login EXITOSO");
            String jwt = jwtUtils.generateJwtToken(username);
            return ResponseEntity.ok(new LoginResponse(jwt));
        } else {
            System.out.println("DEBUG: Login FALLIDO - Credenciales no coinciden");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}
