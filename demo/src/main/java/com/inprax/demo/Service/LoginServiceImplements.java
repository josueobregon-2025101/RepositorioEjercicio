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

    @Override
    public Login registrarLogin(String usuarioLogin, String contrasenaLogin, String correoLogin, String roles){
        if (loginRepository.findByUsuarioLogin(usuarioLogin) != null) {
            return null;
        }
        Login l = new Login();
        l.setUsuarioLogin(usuarioLogin);
        l.setContrasenaLogin(contrasenaLogin);
        l.setCorreoLogin(correoLogin);
        l.setRoles(roles);
        return loginRepository.save(l);
    }

}
