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
            Estudiantes createdEstudiantes = estudiantesService.saveEstudiantes(estudiantes);
            return new ResponseEntity<>(createdEstudiantes, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEstudiantes(@Valid @PathVariable Integer id, @RequestBody Estudiantes estudiantes) {
            Estudiantes updateEstudiantes = estudiantesService.updateEstudiantes(id, estudiantes);
            return ResponseEntity.ok(updateEstudiantes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletedEmpleado(@Valid @PathVariable Integer id, @RequestBody Estudiantes estudiantes) {
            estudiantesService.deleteEstudiantes(id);
            return ResponseEntity.ok("Estudiante eliminado exitosamente");
    }
}
