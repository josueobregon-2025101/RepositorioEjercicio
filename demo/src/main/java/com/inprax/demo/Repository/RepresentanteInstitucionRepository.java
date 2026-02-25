package com.inprax.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inprax.demo.Entity.Institucion;
import com.inprax.demo.Entity.RepresentanteInstitucion;

public interface RepresentanteInstitucionRepository extends JpaRepository<RepresentanteInstitucion, Integer>{
    
    boolean existsByNombreAndApellidoAndCorreoAndTelefonoAndInstitucion(
            String nombre, 
            String apellido, 
            String correo, 
            String telefono, 
            Institucion institucion
    );
}
