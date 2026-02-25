package com.inprax.demo.Service;

import java.util.List;

import com.inprax.demo.Entity.RepresentanteInstitucion;

public interface RepresentanteInstitucionService {
    List<RepresentanteInstitucion> getAllRepresentanteInstituciones();
    RepresentanteInstitucion getRepresentanteInstitucionById(Integer id);
    RepresentanteInstitucion saveRepresentanteInstitucion(RepresentanteInstitucion representanteInstitucion) throws RuntimeException;
    RepresentanteInstitucion updateRepresentanteInstitucion(Integer id, RepresentanteInstitucion representanteInstitucion);
    void deleteRepresentanteInstitucion(Integer id);   
}
