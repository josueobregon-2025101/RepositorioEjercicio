package com.inprax.demo.Service;

import com.inprax.demo.Entity.Administradores;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdministradoresService {

    List<Administradores> getAllAdministradores();

    Administradores getAdministradorById(Integer id);

    Administradores saveAdministrador(Administradores administrador);

    Administradores updateAdministrador(Integer id, Administradores administrador);

    void deleteAdministrador(Integer id);
}
