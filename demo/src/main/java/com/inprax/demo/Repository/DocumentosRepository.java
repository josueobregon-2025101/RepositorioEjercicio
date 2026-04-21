package com.inprax.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.inprax.demo.Entity.Documentos;

@Repository
public interface DocumentosRepository extends JpaRepository<Documentos,Integer> {
    
}
