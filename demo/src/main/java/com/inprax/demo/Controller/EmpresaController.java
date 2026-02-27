package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Empresa;
import com.inprax.demo.Service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllEmpresas() {
        try {
            List<Empresa> empresas = empresaService.getAllEmpresas();
            return ResponseEntity.ok(empresas);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al obtener empresas");
            error.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmpresaById(@PathVariable Integer id) {
        try {
            Empresa empresa = empresaService.getEmpresaById(id);
            if (empresa == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Empresa no encontrada");
                error.put("mensaje", "No existe una empresa con el ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            return ResponseEntity.ok(empresa);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al buscar empresa");
            error.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createEmpresa(@Valid @RequestBody Empresa empresa) {
        try {
            Empresa creada = empresaService.saveEmpresa(empresa);
            return new ResponseEntity<>(creada, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al crear empresa");
            error.put("mensaje", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmpresa(@PathVariable Integer id, @Valid @RequestBody Empresa empresa) {
        try {
            Empresa empresaExistente = empresaService.getEmpresaById(id);
            if (empresaExistente == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Empresa no encontrada");
                error.put("mensaje", "No existe una empresa con el ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            Empresa actualizada = empresaService.updateEmpresa(id, empresa);
            return ResponseEntity.ok(actualizada);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al actualizar empresa");
            error.put("mensaje", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmpresa(@PathVariable Integer id) {
        try {
            Empresa empresaExistente = empresaService.getEmpresaById(id);
            if (empresaExistente == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Empresa no encontrada");
                error.put("mensaje", "No existe una empresa con el ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            empresaService.deleteEmpresa(id);
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Empresa eliminada exitosamente");
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al eliminar empresa");
            error.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
