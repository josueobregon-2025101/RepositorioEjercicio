package com.inprax.demo.Service;

import com.inprax.demo.Entity.Estudiantes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EstudiantesService {
    List<Estudiantes> getAllEstudiantes();
    Estudiantes getEstudianteById(Integer id);
    void saveEstudiantes (Estudiantes estudiantes) throws RuntimeException;
    void deleteEstudiantes (Integer id);
    Estudiantes getEstudianteByLogin(Integer idlogin);
    Estudiantes updateEstudiante(Integer id, Estudiantes estudiantes);
}