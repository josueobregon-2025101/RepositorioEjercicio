package com.inprax.demo.Service;

import com.inprax.demo.Entity.Empresa;
import com.inprax.demo.Entity.Login;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmpresaService {

    List<Empresa> getAllEmpresas();

    Empresa getEmpresaById(Integer id);

    Empresa saveEmpresa(Empresa empresa, Login login);

    Empresa updateEmpresa(Integer id, Empresa empresa);

    void deleteEmpresa(Integer id);
}
