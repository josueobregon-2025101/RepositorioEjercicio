package com.inprax.demo.Service;

import com.inprax.demo.Entity.Estudiantes;
import com.inprax.demo.Repository.EstudiantesRepository;

import java.util.List;

public class EstudiantesServiceImplements implements EstudiantesService {
    private final EstudiantesRepository estudiantesRepository;

    public EstudiantesServiceImplements(EstudiantesRepository estudiantesRepository) {
        this.estudiantesRepository = estudiantesRepository;
    }

    @Override
    public List<Estudiantes> getAllEstudiantes() {
        return estudiantesRepository.findAll();
    }

    @Override
    public Estudiantes saveEstudiantes(Estudiantes estudiantes) throws RuntimeException {
        return estudiantesRepository.save(estudiantes);
    }

    @Override
    public Estudiantes updateEstudiantes(Integer id, Estudiantes estudiantes) {
        Estudiantes existingEstudiantes = estudiantesRepository.findById(id).orElseThrow(() -> new RuntimeException("El usuario no existe"));
        return estudiantesRepository.save(existingEstudiantes);
    }

    @Override
    public void deleteEstudiantes(Integer id) {
        Estudiantes existingEstudiantes = estudiantesRepository.findById(id).orElseThrow(() -> new RuntimeException("El usuario no existe"));
        estudiantesRepository.deleteById(id);
    }
}
