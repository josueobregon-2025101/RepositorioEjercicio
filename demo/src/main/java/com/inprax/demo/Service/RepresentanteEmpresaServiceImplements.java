package com.inprax.demo.Service;

import com.inprax.demo.Entity.RepresentanteEmpresa;
import com.inprax.demo.Exception.BadRequestException;
import com.inprax.demo.Exception.ResourceNotFoundException;
import com.inprax.demo.Repository.RepresentanteEmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        if (representanteEmpresa.getCorreo() == null && !representanteEmpresa.getCorreo().endsWith("@gmail.com") && !representanteEmpresa.getCorreo().endsWith("@outlook.com")) {
            throw new BadRequestException("El dominio del correo debe ser @gmail.com o @outlook.com, verifique por favor");
        }

        if (representanteEmpresaRepository.existsByCorreo(representanteEmpresa.getCorreo())) {
            throw new IllegalArgumentException("El correo ya existe, verifique por favor");
        }

        if (representanteEmpresaRepository.existsByTelefono(representanteEmpresa.getTelefono())) {
            throw new IllegalArgumentException("El numero de telefono ya existe, verefique por favor");
        }

        return representanteEmpresaRepository.save(representanteEmpresa);
    }

    @Override
    public RepresentanteEmpresa updateRepresentantesEmpresa(Integer id, RepresentanteEmpresa representanteEmpresa) {
        RepresentanteEmpresa existingRepresentanteEmpresa = representanteEmpresaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe el representante"));
        return representanteEmpresaRepository.save(existingRepresentanteEmpresa);
    }

    @Override
    public void deleteRepresentantesEmpresa(Integer id) {
        representanteEmpresaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe el representante"));
        representanteEmpresaRepository.deleteById(id);
    }
}
