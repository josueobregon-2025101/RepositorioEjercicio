package com.inprax.demo.Service;

import com.inprax.demo.Entity.Postulaciones;
import com.inprax.demo.Repository.PostulacionesRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostulacionesServiceImplements implements PostulacionesService {

    private final PostulacionesRepository postulacionesRepository;

    // Inyección por constructor
    public PostulacionesServiceImplements(PostulacionesRepository postulacionesRepository) {
        this.postulacionesRepository = postulacionesRepository;
    }

    @Override
    public List<Postulaciones> getAllPostulaciones() {
        return postulacionesRepository.findAll();
    }

    @Override
    public Postulaciones getIdPostulaciones(Integer id) throws RuntimeException {
        return postulacionesRepository.findById(id).orElse(null);
    }

    @Override
    public Postulaciones savePostulaciones(Postulaciones postulaciones) throws RuntimeException {
        // Asegurar que el ID sea null para nueva inserción
        postulaciones.setIdPostulacion(null);
        return postulacionesRepository.save(postulaciones);
    }

    @Override
    public Postulaciones updatePostulaciones(Integer id, Postulaciones postulaciones) throws RuntimeException {
        Optional<Postulaciones> existente = postulacionesRepository.findById(id);
        if (existente.isPresent()) {
            Postulaciones newPostulacion = existente.get();
            newPostulacion.setIdPractica(postulaciones.getIdPractica());
            newPostulacion.setTitulo(postulaciones.getTitulo());
            newPostulacion.setDescripcion(postulaciones.getDescripcion());
            newPostulacion.setFechaPost(postulaciones.getFechaPost());
            newPostulacion.setEstado(postulaciones.getEstado());
            return postulacionesRepository.save(newPostulacion);
        }
        return null;
    }

    @Override
    public void deletePostulaciones(Integer id) throws RuntimeException {
        postulacionesRepository.deleteById(id);
    }
}