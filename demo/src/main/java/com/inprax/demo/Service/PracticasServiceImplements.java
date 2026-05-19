package com.inprax.demo.Service;

import com.inprax.demo.Entity.Practicas;
import com.inprax.demo.Repository.PracticasRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service  // También con @Service en la implementación
public class PracticasServiceImplements implements PracticasService {

    private final PracticasRepository practicasRepository;

    // Inyección por constructor
    public PracticasServiceImplements(PracticasRepository practicasRepository) {
        this.practicasRepository = practicasRepository;
    }

    @Override
    public List<Practicas> getAllPracticas() {
        return practicasRepository.findAll();
    }

    @Override
    public List<Practicas> getPracticasByEmpresaId(Integer idEmpresa) {
        return practicasRepository.getPracticasByidEmpresa(idEmpresa);
    }

    @Override
    public Practicas getIdPracticas(Integer id) throws RuntimeException {
        Optional<Practicas> practica = practicasRepository.findById(id);
        if (practica.isPresent()) {
            return practica.get();
        } else {
            throw new RuntimeException("Práctica no encontrada con ID: " + id);
        }
    }

    @Override
    public Practicas savePracticas(Practicas practicas) throws RuntimeException {
        try {
            // Asegurar que el ID sea null para nueva inserción
            practicas.setIdPractica(null);
            return practicasRepository.save(practicas);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la práctica: " + e.getMessage());
        }
    }

    @Override
    public Practicas updatePracticas(Practicas practicas, Integer id) {
        Optional<Practicas> existente = practicasRepository.findById(id);
        if (existente.isPresent()) {
            Practicas practicaExistente = existente.get();

            // Actualizar todos los campos
            practicaExistente.setIdEmpresa(practicas.getIdEmpresa());
            practicaExistente.setTitulo(practicas.getTitulo());
            practicaExistente.setTiempoPractica(practicas.getTiempoPractica());
            practicaExistente.setTipoPractica(practicas.getTipoPractica());
            practicaExistente.setCarreraPractica(practicas.getCarreraPractica());
            practicaExistente.setVigencia(practicas.getVigencia());
            practicaExistente.setDisponibilidad(practicas.getDisponibilidad());
            practicaExistente.setHorario(practicas.getHorario());

            return practicasRepository.save(practicaExistente);
        } else {
            throw new RuntimeException("Práctica no encontrada con ID: " + id);
        }
    }

    @Override
    public void deletePracticas(Integer id) throws RuntimeException {
        if (practicasRepository.existsById(id)) {
            practicasRepository.deleteById(id);
        } else {
            throw new RuntimeException("Práctica no encontrada con ID: " + id);
        }
    }
}