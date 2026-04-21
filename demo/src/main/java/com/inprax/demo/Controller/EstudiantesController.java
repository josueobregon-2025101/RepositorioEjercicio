package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Estudiantes;
import com.inprax.demo.Service.EstudiantesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/estudiantes")
public class EstudiantesController {

    private final EstudiantesService service;

    public EstudiantesController(EstudiantesService service) {
        this.service = service;
    }

    @GetMapping
    public List<Estudiantes> listar() {
        return service.getAllEstudiantes();
    }

    @GetMapping("/estudiantes")
    public String mostrarEstudiantes() {
        return "Index/estudiantes";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        Estudiantes obtener = service.getEstudianteById(id);
        return ResponseEntity.ok(obtener);
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Estudiantes estudiante, BindingResult result) {
        Estudiantes nuevo = service.saveEstudiantes(estudiante);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @Valid @RequestBody Estudiantes estudiante, BindingResult result) {
        Estudiantes actualizado = service.updateEstudiantes(id, estudiante);
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletedEstudiante(@Valid @PathVariable Integer id) {
        service.deleteEstudiantes(id);
        return ResponseEntity.ok("Estudiante eliminado exitosamente");
    }
}