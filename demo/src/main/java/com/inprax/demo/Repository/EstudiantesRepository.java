package com.inprax.demo.Repository;

import com.inprax.demo.Entity.Estudiantes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudiantesRepository extends JpaRepository<Estudiantes, Integer> {
    boolean existsByCorreo(String correo);

}