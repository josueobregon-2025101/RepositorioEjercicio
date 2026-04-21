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

    @GetMapping("/buscar/{id}")
    public String obtener(@PathVariable Integer id) {
        Estudiantes obtener = service.getEstudianteById(id);
        return "Index/estudiantes";
    }

    @PostMapping("/agregar/{id}")
    public String crear(@Valid Estudiantes estudiante, BindingResult result) {
        Estudiantes nuevo = service.saveEstudiantes(estudiante);
        return "redirect:/Index/estudiantes";
    }

    @PostMapping ("/upgradear/{id}")
    public String actualizar(@PathVariable Integer id, @Valid Estudiantes estudiante, BindingResult result) {
        Estudiantes actualizado = service.updateEstudiantes(id, estudiante);
        return "redirect:/Index/estudiantes";
    }

    @GetMapping("/eliminar/{id}")
    public String deletedEstudiante(@Valid @PathVariable Integer id) {
        service.deleteEstudiantes(id);
        return "redirect:/Index/estudiantes";
    }
}