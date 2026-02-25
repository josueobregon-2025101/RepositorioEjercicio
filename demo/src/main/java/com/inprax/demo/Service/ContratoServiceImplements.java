package com.inprax.demo.Service;

import com.inprax.demo.Entity.Contrato;
import com.inprax.demo.Repository.ContratoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContratoServiceImplements implements ContratoService{

    private final ContratoRepository contratoRepository;

    public ContratoServiceImplements(ContratoRepository contratoRepository){
        this.contratoRepository = contratoRepository;
    }

    @Override
    public List<Contrato> getAllContratos() {
        return contratoRepository.findAll();
    }

    @Override
    public Contrato getContratoById(Integer idContrato) {

        return contratoRepository.findById(idContrato).orElse(null);
    }

    @Override
    public Contrato saveContrato(Contrato contrato) throws RuntimeException {
        return contratoRepository.save(contrato);
    }

    @Override
    public Contrato updateContrato(Integer id, Contrato contrato) {
        Optional<Contrato> existente = contratoRepository.findById(id);
        if (existente.isPresent()){
            Contrato newContrato = existente.get();
            newContrato.setIdEstudiante(contrato.getIdEstudiante());
            newContrato.setIdDocumento(contrato.getIdDocumento());
            newContrato.setIdEmpresa(contrato.getIdEmpresa());
            newContrato.setIdPostulacion(contrato.getIdPostulacion());
            return contratoRepository.save(newContrato);
        }
        return null;
    }

    @Override
    public void deleteContratoById(Integer id) {
    contratoRepository.deleteById(id);
    }
}
