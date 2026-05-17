package com.inprax.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

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
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la institución con el ID: " + id));
    }

    @Override
    public Institucion saveInstitucion(Institucion institucion) throws RuntimeException {
        if (institucion.getCorreo() != null && !institucion.getCorreo().contains("@gmail.com") 
            && !institucion.getCorreo().contains("@outlook.com") && !institucion.getCorreo().contains("@icloud.com") && !institucion.getCorreo().contains("yahoo.com")) {
            throw new RuntimeException("El dominio del correo debe ser @gmail.com o @outlook.com");
        }

        boolean existe = repository.existsByNombreAndCorreoAndDireccionAndTelefono(
                institucion.getNombre(),
                institucion.getCorreo(),
                institucion.getDireccion(),
                institucion.getTelefono()
        );

        if (existe) {
            throw new RuntimeException("Esta institución ya existe con los mismos datos registrados.");
        }

        return repository.save(institucion);
    }

    @Override
    public Institucion updateInstitucion(Integer id, Institucion institucion) {
        Institucion existente = getInstitucionById(id);

        if (institucion.getCorreo() != null && !institucion.getCorreo().contains("@gmail.com") 
            && !institucion.getCorreo().contains("@outlook.com") && !institucion.getCorreo().contains("@icloud.com") && !institucion.getCorreo().contains("yahoo.com")) {
            throw new RuntimeException("El nuevo correo no tiene un dominio permitido.");
        }


        existente.setNombre(institucion.getNombre());
        existente.setCorreo(institucion.getCorreo());
        existente.setDireccion(institucion.getDireccion());
        existente.setTelefono(institucion.getTelefono());

        return repository.save(existente);
    }

    @Override
    public void deleteInstitucion(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: la institución con ID " + id + " no existe.");
        }
        repository.deleteById(id);
    }

    @Override
    public Institucion findByName(String nombre) {
        return repository.findByNombre(nombre);
    }
}
