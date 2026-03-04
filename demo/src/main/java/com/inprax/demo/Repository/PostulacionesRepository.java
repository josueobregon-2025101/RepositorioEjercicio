package com.inprax.demo.Repository;

import com.inprax.demo.Entity.Postulaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostulacionesRepository extends JpaRepository <Postulaciones, Integer> {

}
