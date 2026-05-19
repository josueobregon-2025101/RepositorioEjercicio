package com.inprax.demo.Repository;

import com.inprax.demo.Entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    Empresa findEmpresaByIdLogin(Integer idLogin);
    Empresa findFirstByIdLogin(Integer idLogin);
}



