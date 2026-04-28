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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    @ResponseBody
    public List<Empresa> getAllEmpresas() {
        return empresaService.getAllEmpresas();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getEmpresaById(@PathVariable @Valid Integer id) {
        Empresa empresa = empresaService.getEmpresaById(id);
        if (empresa != null) {
            return ResponseEntity.ok(empresa);
        } else {
            return ResponseEntity.status(404).body("No se encontro la empresa");
        }
    }

    @GetMapping("/empresas")
    public String empresas(Model model) {
        List<Empresa> empresas= empresaService.getAllEmpresas();
        model.addAttribute("empresas", empresas);
        model.addAttribute("empresa", new Empresa());
        return "Index/empresas";
    }

    @GetMapping("/empresas/nuevo")
    public String agregarEmpresa(Model model){
        model.addAttribute("empresaDTO", new EmpresaDTO());
        return "Index/agregar-empresa";
    }

    @PostMapping("/guardar")
    public String nuevaEmpresa(@ModelAttribute EmpresaDTO dto, Model model) {
        Empresa empresa = new Empresa();
        empresa.setNombreEmpresa( dto.getNombreEmpresa());
        empresa.setTelefonoEmpresa( dto.getTelefonoEmpresa());
        empresa.setTipoEmpresa( dto.getTipoEmpresa());
        empresa.setCorreoEmpresa(dto.getCorreoEmpresa());

        Login login = new Login();
        login.setUsuarioLogin(dto.getUsuarioLogin());
        login.setContrasenaLogin(dto.getContrasenaLogin());
        login.setCorreoLogin(dto.getCorreoLogin());

        Empresa e = empresaService.saveEmpresa(empresa,login);

        if (e == null) {
            model.addAttribute("error", "La empresa ya existe");
            return "Index/agregar-empresa";
        }
        System.out.println("Empresa agregado correctamente");
        return "redirect:/empresas/empresas";
    }
    /*
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> saveEmpresa(@Valid @RequestBody Empresa empresa) {
        try {
            Empresa nuevaEmpresa = empresaService.saveEmpresa(empresa);
            if (nuevaEmpresa != null) {
                return ResponseEntity.ok(nuevaEmpresa);
            } else {
                return ResponseEntity.status(402).body("No se creo la empresa");
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error al crear la empresa");
        }
    }

     */

    @PutMapping("/empresas/editar/{id}")
    @ResponseBody
    public ResponseEntity<?> updateEmpresa(@PathVariable Integer id, @RequestBody @Valid Empresa empresa) {
        try {
            Empresa actualizada = empresaService.updateEmpresa(id, empresa);
            if (actualizada != null) {
                return ResponseEntity.ok(actualizada);
            } else {
                return ResponseEntity.status(404).body("No se encontro la empresa");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/empresas/eliminar/{id}")
    public String eliminarEmpresa(@PathVariable Integer id) {
        empresaService.deleteEmpresa(id);
            return "redirect:/empresas/empresas";
    }
    
    
}