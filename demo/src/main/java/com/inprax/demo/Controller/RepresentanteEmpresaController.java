package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Estudiantes;
import com.inprax.demo.Entity.RepresentanteEmpresa;
import com.inprax.demo.Service.RepresentanteEmpresaService;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/representanteEmpresa")
public class RepresentanteEmpresaController {
    private final RepresentanteEmpresaService representanteEmpresaService;

    public RepresentanteEmpresaController(RepresentanteEmpresaService representanteEmpresaService) {
        this.representanteEmpresaService = representanteEmpresaService;
    }

    @GetMapping
    public List<RepresentanteEmpresa> getAllRepresentantesEmpresas() {
        return representanteEmpresaService.getAllRepresentantesEmpresa();
    }

    @PostMapping
    public ResponseEntity<Object> createdRepresentanteEmpresa(@Valid @RequestBody RepresentanteEmpresa representanteEmpresa) {
        try {
            RepresentanteEmpresa createdEstudiantes = representanteEmpresaService.saveRepresentantesEmpresa(representanteEmpresa);
            return new ResponseEntity<>(createdEstudiantes, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRepresentanteEmpresa(@PathVariable Integer id, @RequestBody RepresentanteEmpresa representanteEmpresa) {
        try {
            RepresentanteEmpresa updateRepresentanteEstudiantes = representanteEmpresaService.updateRepresentantesEmpresa(id, representanteEmpresa);
            return ResponseEntity.ok(updateRepresentanteEstudiantes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletedEmpleado(@PathVariable Integer id, @RequestBody Estudiantes estudiantes) {
        try {
            representanteEmpresaService.deleteRepresentantesEmpresa(id);
            return ResponseEntity.ok("Representante eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
