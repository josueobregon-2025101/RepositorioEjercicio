package com.inprax.demo.Service;

import com.inprax.demo.Entity.Empresa;
import com.inprax.demo.Entity.Estudiantes;
import com.inprax.demo.Exception.ResourceNotFoundException;
import com.inprax.demo.Repository.EstudiantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudiantesServiceImplements implements EstudiantesService {

    @Autowired
    private EstudiantesRepository estudiantesRepository;

    @Override
    public List<Estudiantes> getAllEstudiantes() {
        return estudiantesRepository.findAll();
    }

    @Override
    public Estudiantes getEstudianteById(Integer id) {
        return estudiantesRepository.findById(id).orElse(null);
    }


    @Override
    public void saveEstudiantes(Estudiantes estudiantes){
        estudiantesRepository.save(estudiantes);
    }



    @Override
    public void deleteEstudiantes(Integer id) {
        estudiantesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("El usuario no existe"));
        estudiantesRepository.deleteById(id);
    }

    @Override
    public Estudiantes getEstudianteByLogin(Integer idlogin) {
        return estudiantesRepository.findByIdlogin(idlogin);
    }

    @Override
    public Estudiantes updateEstudiante(Integer id, Estudiantes estudiantes) {
        Estudiantes estudianteExistente = estudiantesRepository.findById(id).orElse(null);

        if (estudianteExistente == null) {
            return null;
        }

        estudianteExistente.setNombreInstitucion(estudiantes.getNombreInstitucion());
        estudianteExistente.setIdlogin(estudiantes.getIdlogin());
        estudianteExistente.setNombre(estudiantes.getNombre());
        estudianteExistente.setApellido(estudiantes.getApellido());
        estudianteExistente.setCarrera(estudiantes.getCarrera());
        estudianteExistente.setEdad(estudiantes.getEdad());
        estudianteExistente.setGrado(estudiantes.getGrado());
        estudianteExistente.setTelefono(estudiantes.getTelefono());
        estudianteExistente.setCorreo(estudiantes.getCorreo());
        estudianteExistente.setTutortel(estudiantes.getTutortel());
        estudianteExistente.setInstitucion(estudiantes.getInstitucion());

        return estudiantesRepository.save(estudianteExistente);
    }
}