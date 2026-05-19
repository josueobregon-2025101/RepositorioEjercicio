package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Empresa;
import com.inprax.demo.Entity.Login;
import com.inprax.demo.Entity.Practicas;
import com.inprax.demo.Service.EmpresaService;
import com.inprax.demo.Service.PracticasService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/practicas")
public class PracticasController {

    @Autowired
    private  PracticasService practicasService;
    @Autowired
    private  EmpresaService empresaService;


    @GetMapping("/practicas")
    public String verPracticas(HttpSession session, Model model) {
        Login usuario = (Login) session.getAttribute("usuarioLogueado");
        String rol = (usuario != null) ? usuario.getRoles() : "Administrador";
        model.addAttribute("rolUsuario", rol);
        List<Practicas> practicas = practicasService.getAllPracticas();
        model.addAttribute("practicas", practicas);
        if ("Empresa".equals(rol)) {
            Empresa empresa = empresaService.getEmpresaByIdLogin(usuario.getIdLogin());
            Integer idEmpresa = empresa.getIdEmpresa();
            List<Practicas> practicasEmpresa = practicasService.getPracticasByEmpresaId(idEmpresa);
            model.addAttribute("practicas", practicasEmpresa);
            return "Index/practicas";
        }
        return "Index/practicas-admin";
    }

    @GetMapping("/estudiantes/practicas")
    public String practicasEstudiante(HttpSession session, Model model) {
        Login usuario = (Login) session.getAttribute("usuarioLogueado");
        String rol = (usuario != null) ? usuario.getRoles() : "Estudiante";
        model.addAttribute("rolUsuario", rol);
        List<Practicas> practicas = practicasService.getAllPracticas();
        model.addAttribute("practicas", practicas);
        return "Index/practicas";
    }

    @GetMapping("/misPracticas")
    public String verMisPracticas(HttpSession session, Model model) {
        Login usuario = (Login) session.getAttribute("usuarioLogueado");
        String rol = (usuario != null) ? usuario.getRoles() : "Administrador";
        model.addAttribute("rolUsuario", rol);
        List<Practicas> empresa = practicasService.getAllPracticas();
        model.addAttribute("practicas", empresa);
        return "Index/mis-practicas";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model,HttpSession session) {
        Login usuario = (Login) session.getAttribute("usuarioLogueado");
        String rol = (usuario != null) ? usuario.getRoles() : "Administrador";
        model.addAttribute("rolUsuario", rol);
        Empresa empresa = empresaService.getEmpresaByIdLogin(usuario.getIdLogin());
        model.addAttribute("idEmpresa", empresa.getIdEmpresa());
        model.addAttribute("practica", new Practicas());
        return "Index/agregar-practica";
    }

    @PostMapping("/guardar")
    public String guardarPractica(@Valid @ModelAttribute Practicas practicas,
            RedirectAttributes redirectAttributes) {
        try {
            practicasService.savePracticas(practicas);
            redirectAttributes.addFlashAttribute("message", "¡Práctica agregada exitosamente!");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("message", "Error al guardar: " + e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");
        }
        return "redirect:/practicas/practicas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model,
            RedirectAttributes redirectAttributes) {
        try {
            Practicas practica = practicasService.getIdPracticas(id);
            model.addAttribute("practica", practica);
            return "Index/editar-practica-form";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("message", "Práctica no encontrada");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/practicas/practicas-admin";
        }
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarPractica(@PathVariable Integer id,
            @Valid @ModelAttribute Practicas practicas,
            RedirectAttributes redirectAttributes) {
        try {
            practicasService.updatePracticas(practicas, id);
            redirectAttributes.addFlashAttribute("message", "¡Práctica actualizada exitosamente!");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("message", "Error al actualizar: " + e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");
        }
        return "redirect:/practicas/practicas-admin";
    }

    @PostMapping("/aprobar/{id}")
    public String aprobarPractica(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            Practicas practica = practicasService.getIdPracticas(id);
            practica.setVigencia("Aprobado");
            practicasService.updatePracticas(practica, id);
            redirectAttributes.addFlashAttribute("message", "¡Práctica aprobada exitosamente!");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("message", "Error al aprobar: " + e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");
        }
        return "redirect:/practicas/practica-admin";
    }

    @PostMapping("/rechazar/{id}")
    public String rechazarPractica(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            Practicas practica = practicasService.getIdPracticas(id);
            practica.setVigencia("Rechazado");
            practicasService.updatePracticas(practica, id);
            redirectAttributes.addFlashAttribute("message", "¡Práctica rechazada!");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("message", "Error al rechazar: " + e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");
        }
        return "redirect:/practicas/practicas-admin";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarPractica(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            practicasService.deletePracticas(id);
            redirectAttributes.addFlashAttribute("message", "¡Práctica eliminada exitosamente!");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("message", "Error al eliminar: " + e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");
        }
        return "redirect:/practicas/practicas-admin";
    }
}