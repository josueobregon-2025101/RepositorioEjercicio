package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Empresa;
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

    @GetMapping("/lista")
    public String mostrarEmpresas(Model model) {
        List<Empresa> listar = empresaService.getAllEmpresas();
        model.addAttribute("empresa", listar);
        return "Index/empresas";
    }

    @GetMapping("/empresas")
    public String mostrarEstudiantes() {
        return "Index/empresas";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmpresaById(@PathVariable @Valid Integer id) {
        Empresa empresa = empresaService.getEmpresaById(id);
        if (empresa != null) {
            return ResponseEntity.ok(empresa);
        } else {
            return ResponseEntity.status(404).body("No existe la empresa");
        }
    }

    @PostMapping
    public ResponseEntity<?> createEmpresa(@Valid @RequestBody Empresa empresa) {
        try {
            Empresa creada = empresaService.saveEmpresa(empresa);
            return ResponseEntity.ok().body(creada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/upgradear/{id}")
    public String updateEmpresa(@PathVariable Integer id, @Valid @RequestBody Empresa empresa) {
        try {
            Empresa actualizada = empresaService.updateEmpresa(id, empresa);
            if (actualizada != null) {
                return "redirect:/Index/empresas";
            } else {
                return "No existe la empresa";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/eliminar/{id}")
    public String deleteEmpresa(@PathVariable @Valid Integer id) {
        Empresa empresa = empresaService.getEmpresaById(id);
        if (empresa != null) {
            empresaService.deleteEmpresa(id);
            return "redirect:/Index/empresas";
        } else {
            return "No existe la empresa";
        }
    }
}
