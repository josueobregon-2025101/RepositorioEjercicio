package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Estudiantes;
import com.inprax.demo.Service.EstudiantesService;
import com.inprax.demo.Service.InstitucionService;
import com.inprax.demo.Service.LoginService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EstudiantesController {

    @Autowired
    private EstudiantesService service;

    @Autowired
    private InstitucionService institucionService;


    @GetMapping("/admin/estudiantes")
    public String listarEstudiantes(Model model) {
        List<Estudiantes> estudiantes = service.getAllEstudiantes();
        model.addAttribute("estudiantes", estudiantes);
        return "Index/estudiantes";
    }

    @GetMapping("/admin/estudiantes/agregarestudiante")
    public String estudianteNuevo(Model model) {
        model.addAttribute("estudiantes", new Estudiantes());
        model.addAttribute("institucion", institucionService.getAllInstituciones());
        return "Index/agregar-estudiante";
    }

    @PostMapping("/admin/estudiantes/guardarestudiante")
    public String guardarEstudiante(@ModelAttribute Estudiantes estudiantes) {
        service.saveEstudiantes(estudiantes);
        return "redirect:/admin/estudiantes";
    }

    @GetMapping("/admin/estudiantes/editarestudiante/{id}")
    public String editarEstudiante(@PathVariable Integer id, Model model) {
        Estudiantes estudiantes = service.getEstudianteById(id);
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("institucion", institucionService.getAllInstituciones());
        return "editar-estudiante";
    }
    
    
    

    @GetMapping("/admin/estudiantes/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Integer id) {
        service.deleteEstudiantes(id);
        return "redirect:/admin/estudiantes";
    }


}