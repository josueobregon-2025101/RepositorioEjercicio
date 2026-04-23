package com.inprax.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inprax.demo.Entity.Login;

@Service
public interface LoginService {

    Login validarLogin(String correoLogin, String usuarioLogin, String contrasenaLogin, String roles);

    Login registrarLogin(String usuarioLogin, String contrasenaLogin, String correoLogin, String roles);
}
