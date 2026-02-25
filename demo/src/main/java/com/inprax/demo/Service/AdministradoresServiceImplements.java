package com.inprax.demo.Service;

import com.inprax.demo.Entity.Administradores;
import com.inprax.demo.Repository.AdministradoresRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradoresServiceImplements implements AdministradoresService {

    private final AdministradoresRepository administradoresRepository;

    public AdministradoresServiceImplements(AdministradoresRepository administradoresRepository) {
        this.administradoresRepository = administradoresRepository;
    }

    @Override
    public List<Administradores> getAllAdministradores() {
        return administradoresRepository.findAll();
    }

    @Override
    public Administradores getAdministradorById(Integer id) {
        return administradoresRepository.findById(id).orElse(null);
    }

    @Override
    public Administradores saveAdministrador(Administradores administrador) {
        return administradoresRepository.save(administrador);
    }

    @Override
    public Administradores updateAdministrador(Integer id, Administradores administrador) {
        Administradores existente = administradoresRepository.findById(id).orElse(null);
        if (existente == null) {
            return null;
        }
        existente.setNombreAdministradores(administrador.getNombreAdministradores());
        existente.setApellidoAdministradores(administrador.getApellidoAdministradores());
        existente.setEstadoAdministradores(administrador.getEstadoAdministradores());
        existente.setIdLogin(administrador.getIdLogin());
        return administradoresRepository.save(existente);
    }

    @Override
    public void deleteAdministrador(Integer id) {
        administradoresRepository.deleteById(id);
    }
}
