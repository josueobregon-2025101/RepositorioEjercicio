package com.inprax.demo.Service;

import com.inprax.demo.Entity.Contrato;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContratoService {
    List<Contrato> getAllContratos();
    Contrato getContratoById(Integer idContrato);
    Contrato saveContrato(Contrato contrato) throws RuntimeException;
    Contrato updateContrato(Integer id, Contrato contrato);
    void deleteContratoById(Integer id);
}
