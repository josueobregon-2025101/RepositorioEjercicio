package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Login;
import com.inprax.demo.Service.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<?> validarLogin(@Valid @RequestBody Login login) {
        try {
            Login resultado = loginService.validarLogin(
                    login.getCorreoLogin(),
                    login.getUsuarioLogin(),
                    login.getContrasenaLogin(),
                    login.getRoles()
            );
            if (resultado != null) {
                return ResponseEntity.ok().body(resultado);
            } else {
                return ResponseEntity.status(401).body("Credenciales invalidas");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}