package com.inprax.demo.Repository;

import com.inprax.demo.Entity.Practicas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PracticasRepository extends JpaRepository <Practicas, Integer>{
    List<Practicas> getPracticasByidEmpresa(Integer idEmpresa);

}