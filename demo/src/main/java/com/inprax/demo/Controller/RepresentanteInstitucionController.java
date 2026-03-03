package com.inprax.demo.Controller;

import com.inprax.demo.Entity.RepresentanteInstitucion;
import com.inprax.demo.Service.RepresentanteInstitucionService;
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
@RequestMapping("/api/representantes")
public class RepresentanteInstitucionController {

    @Autowired
    private RepresentanteInstitucionService representanteInstitucionService;

    @GetMapping
    public List<RepresentanteInstitucion> listar() {
        return representanteInstitucionService.getAllRepresentanteInstituciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            return ResponseEntity.ok(representanteInstitucionService.getRepresentanteInstitucionById(id));
        } catch (RuntimeException e) {
            respuesta.put("mensaje", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody RepresentanteInstitucion representante, BindingResult result) {
        Map<String, Object> respuesta = new HashMap<>();

        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), err.getDefaultMessage());
            });
            return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
        }

        try {
            RepresentanteInstitucion nuevo = representanteInstitucionService.saveRepresentanteInstitucion(representante);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            respuesta.put("mensaje", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @Valid @RequestBody RepresentanteInstitucion representante, BindingResult result) {
        Map<String, Object> respuesta = new HashMap<>();

        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), err.getDefaultMessage());
            });
            return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
        }

        try {
            RepresentanteInstitucion actualizado = representanteInstitucionService.updateRepresentanteInstitucion(id, representante);
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            respuesta.put("mensaje", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            representanteInstitucionService.deleteRepresentanteInstitucion(id);
            respuesta.put("mensaje", "Representante eliminado con éxito");
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (RuntimeException e) {
            respuesta.put("mensaje", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
        }
    }
 }
