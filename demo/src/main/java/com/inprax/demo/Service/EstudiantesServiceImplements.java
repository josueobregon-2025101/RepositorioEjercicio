package com.inprax.demo.Service;

import com.inprax.demo.Entity.Estudiantes;
import com.inprax.demo.Repository.EstudiantesRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudiantesServiceImplements implements EstudiantesService {
    
    @Autowired
    private EstudiantesRepository repository;

    @Override
    public List<Estudiantes> getAllEstudiantes() {
        return repository.findAll();
    }

  @Override
    public Estudiantes getEstudianteById(Integer id) {
    return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontró el estudiante con ID: " + id));
}

    @Override
    public Estudiantes saveEstudiante(Estudiantes e) throws RuntimeException {
        if (e.getCorreo() != null && !e.getCorreo().contains("@gmail.com") && !e.getCorreo().contains("@outlook.com")) {
            throw new RuntimeException("El correo del estudiante debe ser @gmail.com o @outlook.com");
        }

        boolean existe = repository.existsByNombreAndApellidoAndCorreoAndTelefonoAndGradoAndCarreraAndEdadAndInstitucionAndRepresentanteInstitucion(
                e.getNombre(),
                e.getApellido(),
                e.getCorreo(),
                e.getTelefono(),
                e.getGrado(),
                e.getCarrera(),
                e.getEdad(),
                e.getInstitucion(),
                e.getRepresentanteInstitucion()
        );

        if (existe) {
            throw new RuntimeException("Este estudiante ya existe con los mismos datos registrados.");
        }

        return repository.save(e);
    }

    @Override
    public Estudiantes updateEstudiante(Integer id, Estudiantes e) {
        Estudiantes existente = getEstudianteById(id);

        if (e.getCorreo() != null && !e.getCorreo().contains("@gmail.com") && !e.getCorreo().contains("@outlook.com")) {
            throw new RuntimeException("El nuevo correo no cumple con el dominio permitido.");
        }

        existente.setNombre(e.getNombre());
        existente.setApellido(e.getApellido());
        existente.setCorreo(e.getCorreo());
        existente.setTelefono(e.getTelefono());
        existente.setGrado(e.getGrado());
        existente.setCarrera(e.getCarrera());
        existente.setEdad(e.getEdad());
        existente.setTutortel(e.getTutortel());
        existente.setNombreInstitucion(e.getNombreInstitucion());
        existente.setIdlogin(e.getIdlogin());
        
        existente.setInstitucion(e.getInstitucion());
        existente.setRepresentanteInstitucion(e.getRepresentanteInstitucion());

        return repository.save(existente);
    }

    @Override
    public void deleteEstudiante(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: el estudiante con ID " + id + " no existe.");
        }
        repository.deleteById(id);
    }
}
