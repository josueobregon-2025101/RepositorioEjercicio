package com.inprax.demo.Service;

import org.springframework.stereotype.Service;
import com.inprax.demo.Entity.Login;

@Service
public interface LoginService {

    Login validarLogin(String correoLogin, String usuarioLogin, String contrasenaLogin, String roles);
}
