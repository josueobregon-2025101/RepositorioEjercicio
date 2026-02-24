package com.inprax.demo.Repository;

import com.inprax.demo.Entity.Institucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitucionRepository extends JpaRepository<Institucion, Integer> {

    boolean existsByNombreAndCorreoAndDireccionAndTelefono(
            String nombre,
            String correo,
            String direccion,
            String telefono
    );
}

