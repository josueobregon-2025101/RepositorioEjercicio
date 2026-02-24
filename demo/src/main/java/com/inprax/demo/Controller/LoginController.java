package com.inprax.demo.Controller;

import org.springframework.web.bind.annotation.*;
import com.inprax.demo.Entity.Login;
import com.inprax.demo.Service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<Object> validarLogin(@RequestBody Login login) {
        try {
            Login resultado = loginService.validarLogin(
                    login.getCorreoLogin(),
                    login.getUsuarioLogin(),
                    login.getContrasenaLogin(),
                    login.getRoles()
            );

            if (resultado == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Credenciales inválidas");
                error.put("mensaje", "El usuario no existe o los datos son incorrectos");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
            }

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Login exitoso");
            respuesta.put("idLogin", resultado.getIdLogin());
            respuesta.put("usuario", resultado.getUsuarioLogin());
            respuesta.put("roles", resultado.getRoles());
            return ResponseEntity.ok(respuesta);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al validar login");
            error.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}