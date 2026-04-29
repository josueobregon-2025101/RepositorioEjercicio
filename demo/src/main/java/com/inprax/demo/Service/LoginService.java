package com.inprax.demo.Service;

import com.inprax.demo.Entity.Login;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    Login validarLogin(String identificador, String contrasenaLogin);
    Login registrarLogin(String usuarioLogin, String contrasenaLogin, String correoLogin, String roles);
}