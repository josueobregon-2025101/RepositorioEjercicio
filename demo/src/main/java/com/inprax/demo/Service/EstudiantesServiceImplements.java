package com.inprax.demo.Service;

import com.inprax.demo.Entity.Estudiantes;
import com.inprax.demo.Exception.BadRequestException;
import com.inprax.demo.Exception.ResourceNotFoundException;
import com.inprax.demo.Repository.EstudiantesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        if (estudiantes.getCorreo() == null && !estudiantes.getCorreo().endsWith("@gmail.com") && !estudiantes.getCorreo().endsWith("@outlook.com")) {
            throw new BadRequestException("El dominio del correo debe ser @gmail.com o @outlook.com");
        }

        if (estudiantesRepository.existByCorreo(estudiantes.getCorreo())) {
            throw new IllegalArgumentException("El correo ya existe");
        }

        return estudiantesRepository.save(estudiantes);
    }

    @Override
    public Estudiantes updateEstudiantes(Integer id, Estudiantes estudiantes) {
        Estudiantes existingEstudiantes = estudiantesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("El usuario no existe"));
        return estudiantesRepository.save(existingEstudiantes);
    }

    @Override
    public void deleteEstudiantes(Integer id) {
        estudiantesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("El usuario no existe"));
        estudiantesRepository.deleteById(id);
    }
}
