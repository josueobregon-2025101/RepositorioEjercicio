package com.inprax.demo.Controller;

import com.inprax.demo.DTO.EmpresaDTO;
import com.inprax.demo.Entity.Empresa;
import com.inprax.demo.Entity.Login;
import com.inprax.demo.Service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    //vistas

    @GetMapping("/lista")
    public String listarEmpresas(Model model) {
        model.addAttribute("empresas", empresaService.getAllEmpresas());
        return "Index/empresas";
    }

    @GetMapping("/nuevo")
    public String formularioNueva(Model model) {
        model.addAttribute("empresaDTO", new EmpresaDTO());
        return "Index/agregar-empresa";
    }

    @PostMapping("/guardar")
    public String guardarEmpresa(@ModelAttribute EmpresaDTO dto, Model model) {
        Empresa empresa = new Empresa();
        empresa.setNombreEmpresa(dto.getNombreEmpresa());
        empresa.setTipoEmpresa(dto.getTipoEmpresa());
        empresa.setTelefonoEmpresa(dto.getTelefonoEmpresa());
        empresa.setCorreoEmpresa(dto.getCorreoEmpresa());

        Login login = new Login();
        login.setUsuarioLogin(dto.getUsuarioLogin());
        login.setContrasenaLogin(dto.getContrasenaLogin());
        login.setCorreoLogin(dto.getCorreoLogin());

        Empresa guardada = empresaService.saveEmpresa(empresa, login);
        if (guardada == null) {
            model.addAttribute("error", "El usuario ya existe");
            return "Index/agregar-empresa";
        }
        return "redirect:/admin/empresas";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model model) {
        Empresa empresa = empresaService.getEmpresaById(id);
        model.addAttribute("empresa", empresa);
        return "Index/editar-empresa";
    }

    @PostMapping("/editar/{id}")
    public String actualizarEmpresa(@PathVariable Integer id, @ModelAttribute Empresa empresa) {
        empresaService.updateEmpresa(id, empresa);
        return "redirect:/admin/empresas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEmpresa(@PathVariable Integer id) {
        empresaService.deleteEmpresa(id);
        return "redirect:/admin/empresas";
    }

    // APIs

    @GetMapping
    @ResponseBody
    public List<Empresa> getAllEmpresas() {
        return empresaService.getAllEmpresas();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getEmpresaById(@PathVariable @Valid Integer id) {
        Empresa empresa = empresaService.getEmpresaById(id);
        return empresa != null ? ResponseEntity.ok(empresa)
                : ResponseEntity.status(404).body("No se encontró la empresa");
    }

    @PutMapping("/editar/{id}/api")
    @ResponseBody
    public ResponseEntity<?> updateEmpresaApi(@PathVariable Integer id,
                                              @RequestBody @Valid Empresa empresa) {
        Empresa actualizada = empresaService.updateEmpresa(id, empresa);
        return actualizada != null ? ResponseEntity.ok(actualizada)
                : ResponseEntity.status(404).body("No se encontró la empresa");
    }
}