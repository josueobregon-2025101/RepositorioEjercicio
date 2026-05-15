package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Empresa;
import com.inprax.demo.Entity.Estudiantes;
import com.inprax.demo.Entity.Login;
import com.inprax.demo.Service.EstudiantesService;
import com.inprax.demo.Service.InstitucionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/estudiantes")
public class EstudiantesController {

    @Autowired
    private EstudiantesService service;

    @Autowired
    private InstitucionService institucionService;

    private Login verificarEstudiante(HttpSession session) {
        Login usuario = (Login) session.getAttribute("usuarioLogueado");
        if (usuario == null || !usuario.getRoles().equals("Estudiante")) return null;
        return usuario;
    }


    @GetMapping("/estudiantes")
    public String listarEstudiantes(HttpSession session, Model model) {
        Login usuario = (Login) session.getAttribute("usuarioLogueado");
        String rol = (usuario != null) ? usuario.getRoles() : "Administrador";
        model.addAttribute("rolUsuario", rol);
        List<Estudiantes> estudiantes = service.getAllEstudiantes();
        model.addAttribute("estudiantes", estudiantes);
        return "Index/estudiantes";
    }

    @GetMapping("/estudiantes/dashboard")
    public String dashboardEstudianteString(HttpSession session, Model model) {
        Login usuario = (Login) session.getAttribute("usuarioLogueado");
        String rol = (usuario != null) ? usuario.getRoles() : "Estudiante";
        model.addAttribute("rolUsuario", rol);
        return "Index/dashboard-estudiante";
    }

    @GetMapping("/estudiantes/perfil")
    public String perfilEstudiante(HttpSession session, Model model) {
        Login usuario = verificarEstudiante(session);
        if (usuario == null) return "redirect:/login/login";
        model.addAttribute("rolUsuario", "Estudiante");
        Estudiantes estudiante = service.getEstudianteByLogin(usuario.getIdLogin());
        System.out.println(estudiante);
        model.addAttribute("EstudianteEdit",estudiante);
        model.addAttribute("Usuario",usuario);
        return "Index/perfil-estudiante";
    }


    @GetMapping("/estudiantes/nuevos")
    public String estudianteNuevo(Model model) {
        model.addAttribute("estudiantes", new Estudiantes());
        model.addAttribute("institucion", institucionService.getAllInstituciones());
        return "Index/agregar-estudiante";
    }

    @PostMapping("/estudiantes/guardar")
    public String guardarEstudiante(@ModelAttribute Estudiantes estudiantes) {
        service.saveEstudiantes(estudiantes);
        return "redirect:/estudiantes/estudiantes";
    }

    @GetMapping("/estudiantes/editar/{id}")
    public ResponseEntity<?> editarEstudiante(@PathVariable Integer id, Estudiantes estudiantes ) {
        try {
            Estudiantes actualizada = service.updateEstudiante(id, estudiantes);
            if (actualizada != null) {
                return ResponseEntity.ok(actualizada);
            } else {
                return ResponseEntity.status(404).body("No se encontro la empresa");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/estudiantes/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Integer id) {
        service.deleteEstudiantes(id);
        return "redirect:/estudiantes/estudiantes";
    }

    @GetMapping("/configuracion")
    public String configestudiante() {
        return "Index/configuracion-estudiante";
    }

}