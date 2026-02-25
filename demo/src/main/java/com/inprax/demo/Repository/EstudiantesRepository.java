package com.inprax.demo.Repository;

import com.inprax.demo.Entity.Estudiantes;
import com.inprax.demo.Entity.Institucion;
import com.inprax.demo.Entity.RepresentanteInstitucion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudiantesRepository extends JpaRepository<Estudiantes, Integer> {
    boolean existsByNombreAndApellidoAndCorreoAndTelefonoAndGradoAndCarreraAndEdadAndInstitucionAndRepresentanteInstitucion(
            String nombre, 
            String apellido, 
            String correo, 
            Integer telefono, 
            String grado, 
            String carrera, 
            Integer edad, 
            Institucion institucion, 
            RepresentanteInstitucion representanteInstitucion
    );
}
