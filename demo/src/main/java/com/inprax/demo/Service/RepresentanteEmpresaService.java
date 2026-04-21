package com.inprax.demo.Service;

import com.inprax.demo.Entity.Estudiantes;
import com.inprax.demo.Entity.RepresentanteEmpresa;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RepresentanteEmpresaService {
    List<RepresentanteEmpresa> getAllRepresentantesEmpresa();
    RepresentanteEmpresa saveRepresentantesEmpresa (RepresentanteEmpresa representanteEmpresa) throws RuntimeException;
    RepresentanteEmpresa updateRepresentantesEmpresa (Integer id, RepresentanteEmpresa representanteEmpresa);
    void deleteRepresentantesEmpresa (Integer id);
}
