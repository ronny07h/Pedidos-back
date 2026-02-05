package com.examen.pedidos_back.controller;

import com.examen.pedidos_back.dto.LoginRequest;
import com.examen.pedidos_back.dto.LoginResponse;
import com.examen.pedidos_back.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        // Hardcoded credentials as per user request: admin / admin
        if ("admin".equals(loginRequest.getUsername()) && "admin".equals(loginRequest.getPassword())) {
            String jwt = jwtUtils.generateJwtToken(loginRequest.getUsername());
            return ResponseEntity.ok(new LoginResponse(jwt));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}
