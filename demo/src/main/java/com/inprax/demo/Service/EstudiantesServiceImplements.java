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
    public Estudiantes getEstudianteById(Integer id) {
        return
                estudiantesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("El estudiante no existe"));

    }


    @Override
    public Estudiantes saveEstudiantes(Estudiantes estudiantes) throws RuntimeException {
        if (estudiantes.getCorreo() == null && !estudiantes.getCorreo().endsWith("@gmail.com") && !estudiantes.getCorreo().endsWith("@outlook.com")) {
            throw new BadRequestException("El dominio del correo debe ser @gmail.com o @outlook.com, verifique por favor");
        }

        if (estudiantesRepository.existsByCorreo(estudiantes.getCorreo())) {
            throw new IllegalArgumentException("El correo ya existe, verifique por favor");
        }

        return estudiantesRepository.save(estudiantes);
    }

    @Override
    public Estudiantes updateEstudiantes(Integer id, Estudiantes estudiantes) {
        Estudiantes existingEstudiantes = estudiantesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("El usuario no existe"));

        existingEstudiantes.setInstitucion(estudiantes.getInstitucion());
        existingEstudiantes.setIdlogin(estudiantes.getIdlogin());
        existingEstudiantes.setNombre(estudiantes.getNombre());
        existingEstudiantes.setApellido(estudiantes.getApellido());
        existingEstudiantes.setTelefono(estudiantes.getTelefono());
        existingEstudiantes.setGrado(estudiantes.getGrado());
        existingEstudiantes.setCarrera(estudiantes.getCarrera());
        existingEstudiantes.setCorreo(estudiantes.getCorreo());
        existingEstudiantes.setNombreInstitucion(estudiantes.getNombreInstitucion());
        existingEstudiantes.setTutortel(estudiantes.getTutortel());
        existingEstudiantes.setEdad(estudiantes.getEdad());

        return estudiantesRepository.save(existingEstudiantes);
    }

    @Override
    public void deleteEstudiantes(Integer id) {
        estudiantesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("El usuario no existe"));
        estudiantesRepository.deleteById(id);
    }
}