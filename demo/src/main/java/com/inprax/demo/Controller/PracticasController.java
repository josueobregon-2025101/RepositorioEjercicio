package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Login;
import com.inprax.demo.Entity.Practicas;
import com.inprax.demo.Service.PracticasService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/practicas")
public class PracticasController {

    private final PracticasService practicasService;

    public PracticasController(PracticasService practicasService) {
        this.practicasService = practicasService;
    }

    @GetMapping("/practicas")
    public String verPracticas(HttpSession session, Model model){
        Login usuario = (Login) session.getAttribute("usuarioLogueado");
        String rol = (usuario != null) ? usuario.getRoles() : "Administrador";
        model.addAttribute("rolUsuario", rol);
        List<Practicas> practicas = practicasService.getAllPracticas();
        model.addAttribute("practicas", practicas);
        return "Index/practicas";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
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
            return "redirect:/practicas/practicas";
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
        return "redirect:/practicas/practicas";
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
        return "redirect:/practicas/practicas";
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
        return "redirect:/practicas/practicas";
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
        return "redirect:/practicas/practicas";
    }
}