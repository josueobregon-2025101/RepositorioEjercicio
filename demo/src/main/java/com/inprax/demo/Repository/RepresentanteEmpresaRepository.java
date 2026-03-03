package com.inprax.demo.Repository;

import com.inprax.demo.Entity.RepresentanteEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepresentanteEmpresaRepository extends JpaRepository<RepresentanteEmpresa, Integer> {
    boolean existsByCorreo(String correo);
    boolean existsByTelefono(Integer telefono);
}
