package com.inprax.demo.Service;

import com.inprax.demo.Entity.Estudiantes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EstudiantesService {
    List<Estudiantes> getAllEstudiantes();
    Estudiantes saveEstudiantes (Estudiantes estudiantes) throws RuntimeException;
    Estudiantes updateEstudiantes (Integer id, Estudiantes estudiantes);
    void deleteEstudiantes (Integer id);
}