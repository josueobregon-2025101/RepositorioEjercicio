package com.inprax.demo.Repository;

import com.inprax.demo.Entity.Practicas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticasRepository extends JpaRepository <Practicas, Integer>{

}