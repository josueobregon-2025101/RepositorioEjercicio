package com.inprax.demo.Service;

import com.inprax.demo.Entity.RepresentanteEmpresa;
import com.inprax.demo.Repository.RepresentanteEmpresaRepository;

import java.util.List;

public class RepresentanteEmpresaServiceImplements implements RepresentanteEmpresaService {
    private final RepresentanteEmpresaRepository representanteEmpresaRepository;

    public RepresentanteEmpresaServiceImplements(RepresentanteEmpresaRepository representanteEmpresaRepository) {
        this.representanteEmpresaRepository = representanteEmpresaRepository;
    }

    @Override
    public List<RepresentanteEmpresa> getAllRepresentantesEmpresa() {
        return representanteEmpresaRepository.findAll();
    }

    @Override
    public RepresentanteEmpresa saveRepresentantesEmpresa(RepresentanteEmpresa representanteEmpresa) throws RuntimeException {
        return representanteEmpresaRepository.save(representanteEmpresa);
    }

    @Override
    public RepresentanteEmpresa updateRepresentantesEmpresa(Integer id, RepresentanteEmpresa representanteEmpresa) {
        RepresentanteEmpresa existingRepresentanteEmpresa = representanteEmpresaRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe el representante"));
        return representanteEmpresaRepository.save(existingRepresentanteEmpresa);
    }

    @Override
    public void deleteRepresentantesEmpresa(Integer id) {
        representanteEmpresaRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe el representante"));
        representanteEmpresaRepository.deleteById(id);
    }
}
