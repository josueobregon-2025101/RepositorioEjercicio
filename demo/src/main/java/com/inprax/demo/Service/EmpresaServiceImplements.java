package com.inprax.demo.Service;

import com.inprax.demo.Entity.Empresa;
import com.inprax.demo.Entity.Login;
import com.inprax.demo.Repository.EmpresaRepository;
import com.inprax.demo.Repository.LoginRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaServiceImplements implements EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final LoginRepository loginRepository;

    public EmpresaServiceImplements(EmpresaRepository empresaRepository, LoginRepository loginRepository) {
        this.empresaRepository = empresaRepository;
        this.loginRepository = loginRepository;
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
    public Empresa saveEmpresa(Empresa empresa, Login login) {
        //METODO PARA GUARDAR LA EMPRESA

    if (loginRepository.findByUsuarioLogin(login.getUsuarioLogin()) != null) {
        return null;
    }
        Login loginNew = new Login();
        loginNew.setUsuarioLogin(login.getUsuarioLogin());
        loginNew.setCorreoLogin(login.getCorreoLogin());
        loginNew.setContrasenaLogin(login.getContrasenaLogin());
        loginNew.setRoles("Empresa");

        Login loginGuardado =loginRepository.save(loginNew);


        empresa.setIdLogin(loginGuardado.getIdLogin());
        empresa.setCorreoEmpresa(login.getCorreoLogin());

        return empresaRepository.save(empresa);
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
