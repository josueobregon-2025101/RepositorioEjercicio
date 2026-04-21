package com.inprax.demo.Service;

import com.inprax.demo.Entity.Practicas;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PracticasService {
    List<Practicas> getAllPracticas();
    Practicas getIdPracticas(Integer id) throws RuntimeException;
    Practicas savePracticas(Practicas practicas) throws RuntimeException;
    Practicas updatePracticas(Practicas practicas, Integer id);
    void deletePracticas(Integer id) throws RuntimeException;
}