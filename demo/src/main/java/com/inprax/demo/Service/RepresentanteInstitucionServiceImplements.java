package com.inprax.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inprax.demo.Entity.RepresentanteInstitucion;
import com.inprax.demo.Repository.RepresentanteInstitucionRepository;

@Service
public class RepresentanteInstitucionServiceImplements implements RepresentanteInstitucionService{

    @Autowired
    private RepresentanteInstitucionRepository repository;

    @Override
    public List<RepresentanteInstitucion> getAllRepresentanteInstituciones() {
        return repository.findAll();
    }

    @Override
    public RepresentanteInstitucion getRepresentanteInstitucionById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el representante con ID: " + id));
    }

    @Override
    public RepresentanteInstitucion saveRepresentanteInstitucion(RepresentanteInstitucion r) throws RuntimeException {
        if (r.getCorreo() != null && !r.getCorreo().contains("@gmail.com") && !r.getCorreo().contains("@outlook.com")) {
            throw new RuntimeException("El correo del representante debe ser @gmail.com o @outlook.com");
        }
        boolean existe = repository.existsByNombreAndApellidoAndCorreoAndTelefonoAndInstitucion(
                r.getNombre(),
                r.getApellido(),
                r.getCorreo(),
                r.getTelefono(),
                r.getInstitucion()
        );

        if (existe) {
            throw new RuntimeException("Este representante ya existe con los mismos datos en esta institución.");
        }

        return repository.save(r);
    }

    @Override
    public RepresentanteInstitucion updateRepresentanteInstitucion(Integer id, RepresentanteInstitucion r) {
        RepresentanteInstitucion existente = getRepresentanteInstitucionById(id);

        if (r.getCorreo() != null && !r.getCorreo().contains("@gmail.com") && !r.getCorreo().contains("@outlook.com")) {
            throw new RuntimeException("El nuevo correo no cumple con el dominio permitido.");
        }
        existente.setNombre(r.getNombre());
        existente.setApellido(r.getApellido());
        existente.setCorreo(r.getCorreo());
        existente.setTelefono(r.getTelefono());
        existente.setInstitucion(r.getInstitucion());

        return repository.save(existente);
    }

    @Override
    public void deleteRepresentanteInstitucion(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: el representante con ID " + id + " no existe.");
        }
        repository.deleteById(id);
    }
}
