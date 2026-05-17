package com.inprax.demo.Service;

import com.inprax.demo.Entity.Institucion;
import com.inprax.demo.Entity.Login;
import com.inprax.demo.Repository.LoginRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImplements implements LoginService {

    private final LoginRepository loginRepository;

    public LoginServiceImplements(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public Login validarLogin(String identificador, String contrasenaLogin) {
        if (identificador.contains("@")) {
            return loginRepository
                    .findByCorreoLoginAndContrasenaLogin(identificador, contrasenaLogin)
                    .orElse(null);
        } else {
            return loginRepository
                    .findByUsuarioLoginAndContrasenaLogin(identificador, contrasenaLogin)
                    .orElse(null);
        }
    }

    @Override
    public Login registrarLogin(String usuarioLogin, String contrasenaLogin, String correoLogin, String roles) {
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

    @Override
    public Login actualizarLogin(Integer idLogin, Login login) {
        Login existente = loginRepository.findByidLogin(idLogin);

        existente.setRoles(login.getRoles());
        existente.setCorreoLogin(login.getCorreoLogin());
        existente.setUsuarioLogin(login.getUsuarioLogin());
        existente.setContrasenaLogin(login.getContrasenaLogin());

        return loginRepository.save(existente);
    }
}
