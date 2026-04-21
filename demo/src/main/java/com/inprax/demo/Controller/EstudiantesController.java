package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Estudiantes;
import com.inprax.demo.Service.EstudiantesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EstudiantesController {

    private final EstudiantesService service;

    public EstudiantesController(EstudiantesService service) {
        this.service = service;
    }

    @GetMapping("/admin/estudiantes")
    public String listarEstudiantes(Model model) {
        List<Estudiantes> estudiantes = service.getAllEstudiantes();
        model.addAttribute("estudiantes", estudiantes);
        return "Index/estudiantes";
    }

    @GetMapping("/admin/estudiantes/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Integer id) {
        service.deleteEstudiantes(id);
        return "redirect:/admin/estudiantes";
    }


}