package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Estudiantes;
import com.inprax.demo.Service.EstudiantesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudiantesController {
    private final EstudiantesService estudiantesService;

    public EstudiantesController(EstudiantesService estudiantesService) {
        this.estudiantesService = estudiantesService;
    }

    @GetMapping
    public List<Estudiantes> getAllEstudiantes() {
        return estudiantesService.getAllEstudiantes();
    }

    @PostMapping
    public ResponseEntity<Object> createdEstudiantes(@Valid @RequestBody Estudiantes estudiantes) {
        try {
            Estudiantes createdEstudiantes = estudiantesService.saveEstudiantes(estudiantes);
            return new ResponseEntity<>(createdEstudiantes, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEstudiantes(@PathVariable Integer id, @RequestBody Estudiantes estudiantes) {
        try {
            Estudiantes updateEstudiantes = estudiantesService.updateEstudiantes(id, estudiantes);
            return ResponseEntity.ok(updateEstudiantes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmpleado(@PathVariable Integer id, @RequestBody Estudiantes estudiantes) {
        try {
            estudiantesService.deleteEstudiantes(id);
            return ResponseEntity.ok("Estudiante eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
