package com.inprax.demo.Service;

import com.inprax.demo.Entity.Postulaciones;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostulacionesService {
    List<Postulaciones> getAllPostulaciones();
    Postulaciones getIdPostulaciones(Integer id) throws RuntimeException;
    Postulaciones savePostulaciones(Postulaciones postulaciones) throws  RuntimeException;
    Postulaciones updatePostulaciones(Integer id, Postulaciones postulaciones) throws RuntimeException;
    void deletePostulaciones(Integer id) throws RuntimeException;
}