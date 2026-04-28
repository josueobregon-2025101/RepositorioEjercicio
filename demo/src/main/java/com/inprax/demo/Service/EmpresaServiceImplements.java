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

    public EmpresaServiceImplements(EmpresaRepository empresaRepository,
                                    LoginRepository loginRepository) {
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
        if (loginRepository.findByUsuarioLogin(login.getUsuarioLogin()) != null) {
            return null;
        }
        Login loginNew = new Login();
        loginNew.setUsuarioLogin(login.getUsuarioLogin());
        loginNew.setCorreoLogin(login.getCorreoLogin());
        loginNew.setContrasenaLogin(login.getContrasenaLogin());
        loginNew.setRoles("Empresa");
        Login loginGuardado = loginRepository.save(loginNew);
        empresa.setIdLogin(loginGuardado.getIdLogin());
        System.out.println("Empresa a guardar: " + empresa.getNombreEmpresa() + " | login id: " + empresa.getIdLogin());
        System.out.println("Empresa a guardar: " + empresa.getNombreEmpresa() + " | login id: " + empresa.getIdLogin());
        System.out.println("Tipo: " + empresa.getTipoEmpresa() + " | Correo: " + empresa.getCorreoEmpresa());
        return empresaRepository.save(empresa);
    }

    @Override
    public Empresa updateEmpresa(Integer id, Empresa empresa) {
        Empresa existente = empresaRepository.findById(id).orElse(null);
        if (existente == null) return null;
        existente.setNombreEmpresa(empresa.getNombreEmpresa());
        existente.setTipoEmpresa(empresa.getTipoEmpresa());
        existente.setTamanoEmpresa(empresa.getTamanoEmpresa());
        existente.setTelefonoEmpresa(empresa.getTelefonoEmpresa());
        existente.setCorreoEmpresa(empresa.getCorreoEmpresa());
        existente.setDireccionEmpresa(empresa.getDireccionEmpresa());
        existente.setHorarioEmpresa(empresa.getHorarioEmpresa());
        existente.setDescripcion(empresa.getDescripcion());
        existente.setIdLogin(empresa.getIdLogin());
        return empresaRepository.save(existente);
    }

    @Override
    public void deleteEmpresa(Integer id) {
        empresaRepository.deleteById(id);
    }
}
