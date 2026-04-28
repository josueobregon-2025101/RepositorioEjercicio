package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Estudiantes;
import com.inprax.demo.Service.EstudiantesService;
import com.inprax.demo.Service.InstitucionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/estudiantes")
public class EstudiantesController {

    private final EstudiantesService service;
    private final InstitucionService institucionService;

    public EstudiantesController(EstudiantesService service, InstitucionService institucionService) {
        this.service = service;
        this.institucionService = institucionService;
    }

    @GetMapping("/lista")
    public String listarEstudiantes(Model model) {
        model.addAttribute("estudiantes", service.getAllEstudiantes());
        return "Index/estudiantes";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("estudiantes", new Estudiantes());
        model.addAttribute("institucion", institucionService.getAllInstituciones());
        return "Index/agregar-estudiante";
    }

    @PostMapping("/guardar")
    public String guardarEstudiante(@ModelAttribute Estudiantes estudiantes) {
        service.saveEstudiantes(estudiantes);
        return "redirect:/admin/estudiantes";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model model) {
        model.addAttribute("estudiantes", service.getEstudianteById(id));
        model.addAttribute("institucion", institucionService.getAllInstituciones());
        return "Index/editar-estudiante";
    }

    @PostMapping("/editar/{id}")
    public String actualizarEstudiante(@PathVariable Integer id,
                                       @ModelAttribute Estudiantes estudiantes) {
        service.saveEstudiantes(estudiantes);
        return "redirect:/admin/estudiantes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Integer id) {
        service.deleteEstudiantes(id);
        return "redirect:/admin/estudiantes";
    }
}