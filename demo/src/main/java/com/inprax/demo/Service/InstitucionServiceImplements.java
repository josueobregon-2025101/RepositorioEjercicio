package com.inprax.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inprax.demo.Service.InstitucionService;
import com.inprax.demo.Repository.InstitucionRepository;
import com.inprax.demo.Entity.Institucion;
import java.util.List;
import java.util.Optional;

@Service
public class InstitucionServiceImplements implements InstitucionService {

    @Autowired
    private InstitucionRepository repository;

    @Override
    public List<Institucion> getAllInstituciones() {
        return repository.findAll();
    }

    @Override
    public Institucion getInstitucionById(Integer id) {
        Optional<Institucion> op = repository.findById(id);
        if (op.isPresent()) {
            return op.get();
        }
        throw new RuntimeException("No se encontró la institución con el ID: " + id);
    }

    @Override
    public Institucion saveInstitucion(Institucion institucion) throws RuntimeException {
        String correo = institucion.getCorreo();
        if (correo == null || (!correo.contains("@gmail.com") && !correo.contains("@outlook.com"))) {
            throw new RuntimeException("Dominio no permitido: use @gmail.com o @outlook.com");
        }

        boolean existe = repository.existsByNombreAndCorreoAndDireccionAndTelefono(
                institucion.getNombre(),
                institucion.getCorreo(),
                institucion.getDireccion(),
                institucion.getTelefono()
        );

        if (existe) {
            throw new RuntimeException("La institución ya existe con estos mismos datos");
        }

        return repository.save(institucion);
    }

    @Override
    public Institucion updateInstitucion(Integer id, Institucion institucion) {
        Institucion existente = getInstitucionById(id);

        String correo = institucion.getCorreo();
        if (correo == null || (!correo.contains("@gmail.com") && !correo.contains("@outlook.com"))) {
            throw new RuntimeException("Dominio no permitido: use @gmail.com o @outlook.com");
        }

        existente.setNombre(institucion.getNombre());
        existente.setCorreo(institucion.getCorreo());
        existente.setDireccion(institucion.getDireccion());
        existente.setTelefono(institucion.getTelefono());

        return repository.save(existente);
    }

    @Override
    public void deleteInstitucion(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("No se puede eliminar: el ID no existe");
        }
    }
}
