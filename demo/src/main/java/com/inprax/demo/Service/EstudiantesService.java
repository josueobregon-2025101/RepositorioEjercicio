package com.inprax.demo.Service;

import com.inprax.demo.Entity.Estudiantes;
import com.inprax.demo.Entity.Institucion;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EstudiantesService {
    List<Estudiantes> getAllEstudiantes();
    Estudiantes getEstudianteById(Integer id);
    Estudiantes saveEstudiante (Estudiantes estudiantes) throws RuntimeException;
    Estudiantes updateEstudiante(Integer id, Estudiantes estudiantes);
    void deleteEstudiante (Integer id);
}
