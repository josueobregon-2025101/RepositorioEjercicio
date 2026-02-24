package com.inprax.demo.Service;

import com.inprax.demo.Entity.Institucion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InstitucionService {
    List<Institucion> getAllInstituciones();
    Institucion getInstitucionById(Integer id);
    Institucion saveInstitucion(Institucion institucion) throws RuntimeException;
    Institucion updateInstitucion(Integer id, Institucion institucion);
    void deleteInstitucion(Integer id);
}