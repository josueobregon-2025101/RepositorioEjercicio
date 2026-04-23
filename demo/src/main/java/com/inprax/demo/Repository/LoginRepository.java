package com.inprax.demo.Repository;

import com.inprax.demo.Entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;


@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {
    Optional<Login> findByCorreoLoginAndUsuarioLoginAndContrasenaLoginAndRoles(
            String correoLogin,
            String usuarioLogin,
            String contrasenaLogin,
            String roles
    );

    Login findByUsuarioLogin(String usuarioLogin);

}
