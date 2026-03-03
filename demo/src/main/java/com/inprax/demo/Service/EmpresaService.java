package com.inprax.demo.Service;

import com.inprax.demo.Entity.Empresa;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmpresaService {

    List<Empresa> getAllEmpresas();

    Empresa getEmpresaById(Integer id);

    Empresa saveEmpresa(Empresa empresa);

    Empresa updateEmpresa(Integer id, Empresa empresa);

    void deleteEmpresa(Integer id);
}
