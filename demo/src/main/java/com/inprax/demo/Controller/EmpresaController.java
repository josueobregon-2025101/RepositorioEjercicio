package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Empresa;
import com.inprax.demo.Service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public List<Empresa> getAllEmpresas() {
        return empresaService.getAllEmpresas();
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

    @GetMapping("/empresas")
    public String mostrarEstudiantes() {
        return "Index/empresas";
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

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmpresa(@PathVariable Integer id, @Valid @RequestBody Empresa empresa) {
        try {
            Empresa actualizada = empresaService.updateEmpresa(id, empresa);
            if (actualizada != null) {
                return ResponseEntity.ok(actualizada);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmpresa(@PathVariable @Valid Integer id) {
        Empresa empresa = empresaService.getEmpresaById(id);
        if (empresa != null) {
            empresaService.deleteEmpresa(id);
            return ResponseEntity.ok().body("Se elimino la empresa");
        } else {
            return ResponseEntity.status(404).body("No existe la empresa");
        }
    }
}
