package com.inprax.demo.Repository;

import com.inprax.demo.Entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {

    Optional<Login> findByCorreoLoginAndUsuarioLoginAndContrasenaLoginAndRoles(
            String correoLogin, String usuarioLogin, String contrasenaLogin, String roles);

    Optional<Login> findByCorreoLoginAndContrasenaLogin(
            String correoLogin, String contrasenaLogin);

    Optional<Login> findByUsuarioLoginAndContrasenaLogin(
            String usuarioLogin, String contrasenaLogin);

    Login findByUsuarioLogin(String usuarioLogin);
    Login findByidLogin(Integer idLogin);
}