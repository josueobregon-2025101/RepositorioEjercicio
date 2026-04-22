package com.inprax.demo.Service;

import com.inprax.demo.Entity.Login;
import com.inprax.demo.Repository.LoginRepository;


import org.springframework.stereotype.Service;

@Service
public class LoginServiceImplements implements LoginService {

    private final LoginRepository loginRepository;

    public LoginServiceImplements(LoginRepository loginRepository) { this.loginRepository = loginRepository; }

    @Override
    public Login validarLogin(String correoLogin, String usuarioLogin, String contrasenaLogin, String roles) {
        return loginRepository
                .findByCorreoLoginAndUsuarioLoginAndContrasenaLoginAndRoles(correoLogin, usuarioLogin, contrasenaLogin, roles)
                .orElse(null);
    }

}
