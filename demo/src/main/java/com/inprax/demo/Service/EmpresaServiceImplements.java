package com.inprax.demo.Service;

import com.inprax.demo.Entity.Empresa;
import com.inprax.demo.Repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaServiceImplements implements EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaServiceImplements(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Override
    public List<Empresa> getAllEmpresas() {
        return empresaRepository.findAll();
    }

    @Override
    public Empresa getEmpresaById(Integer id) {
        return empresaRepository.findById(id).orElse(null);
    }

    @Override
    public Empresa saveEmpresa(Empresa empresa) {
        //METODO PARA GUARDAR LA EMPRESA

    if (empresaRepository.findByNombreEmpresa(empresa.getNombreEmpresa()) != null) {
        return null;
    }
    Empresa newEmpresa = empresa;
        System.out.println("Nombre: " + empresa.getNombreEmpresa());
        return empresaRepository.save(newEmpresa);
    }

    @Override
    public Empresa updateEmpresa(Integer id, Empresa empresa) {
        Empresa empresaExistente = empresaRepository.findById(id).orElse(null);

        if (empresaExistente == null) {
            return null;
        }

        empresaExistente.setNombreEmpresa(empresa.getNombreEmpresa());
        empresaExistente.setTipoEmpresa(empresa.getTipoEmpresa());
        empresaExistente.setTamanoEmpresa(empresa.getTamanoEmpresa());
        empresaExistente.setTelefonoEmpresa(empresa.getTelefonoEmpresa());
        empresaExistente.setCorreoEmpresa(empresa.getCorreoEmpresa());
        empresaExistente.setDireccionEmpresa(empresa.getDireccionEmpresa());
        empresaExistente.setHorarioEmpresa(empresa.getHorarioEmpresa());
        empresaExistente.setDescripcion(empresa.getDescripcion());
        empresaExistente.setIdLogin(empresa.getIdLogin());

        return empresaRepository.save(empresaExistente);
    }

    @Override
    public void deleteEmpresa(Integer id) {
        empresaRepository.deleteById(id);
    }
}
