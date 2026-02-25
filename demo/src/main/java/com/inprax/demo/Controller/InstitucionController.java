package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Institucion;
import com.inprax.demo.Service.InstitucionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/instituciones")
public class InstitucionController {

    @Autowired
    private InstitucionService service;

    @GetMapping
    public List<Institucion> listar() {
        return service.getAllInstituciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            return ResponseEntity.ok(service.getInstitucionById(id));
        } catch (RuntimeException e) {
            respuesta.put("mensaje", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Institucion institucion, BindingResult result) {
        Map<String, Object> respuesta = new HashMap<>();

        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), err.getDefaultMessage());
            });
            return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
        }

        try {
            Institucion nueva = service.saveInstitucion(institucion);
            return new ResponseEntity<>(nueva, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            respuesta.put("mensaje", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @Valid @RequestBody Institucion institucion, BindingResult result) {
        Map<String, Object> respuesta = new HashMap<>();

        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), err.getDefaultMessage());
            });
            return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
        }

        try {
            Institucion actualizada = service.updateInstitucion(id, institucion);
            return new ResponseEntity<>(actualizada, HttpStatus.OK);
        } catch (RuntimeException e) {
            respuesta.put("mensaje", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            service.deleteInstitucion(id);
            respuesta.put("mensaje", "Institución eliminada con éxito");
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (RuntimeException e) {
            respuesta.put("mensaje", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
        }
    }
}