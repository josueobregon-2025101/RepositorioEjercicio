package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Administradores;
import com.inprax.demo.Service.AdministradoresService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/administradores")
public class AdministradoresController {

    private final AdministradoresService administradoresService;

    public AdministradoresController(AdministradoresService administradoresService) {
        this.administradoresService = administradoresService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllAdministradores() {
        try {
            List<Administradores> administradores = administradoresService.getAllAdministradores();
            return ResponseEntity.ok(administradores);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al obtener administradores");
            error.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAdministradorById(@PathVariable Integer id) {
        try {
            Administradores administrador = administradoresService.getAdministradorById(id);
            if (administrador == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Administrador no encontrado");
                error.put("mensaje", "No existe un administrador con el ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            return ResponseEntity.ok(administrador);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al buscar administrador");
            error.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createAdministrador(@Valid @RequestBody Administradores administrador) {
        try {
            Administradores creado = administradoresService.saveAdministrador(administrador);
            return new ResponseEntity<>(creado, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al crear administrador");
            error.put("mensaje", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAdministrador(@PathVariable Integer id, @Valid @RequestBody Administradores administrador) {
        try {
            Administradores existente = administradoresService.getAdministradorById(id);
            if (existente == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Administrador no encontrado");
                error.put("mensaje", "No existe un administrador con el ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            Administradores actualizado = administradoresService.updateAdministrador(id, administrador);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al actualizar administrador");
            error.put("mensaje", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAdministrador(@PathVariable Integer id) {
        try {
            Administradores existente = administradoresService.getAdministradorById(id);
            if (existente == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Administrador no encontrado");
                error.put("mensaje", "No existe un administrador con el ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            administradoresService.deleteAdministrador(id);
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Administrador eliminado exitosamente");
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al eliminar administrador");
            error.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
