package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Administradores;
import com.inprax.demo.Service.AdministradoresService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdministradoresController {

    private final AdministradoresService administradoresService;

    public AdministradoresController(AdministradoresService administradoresService) {
        this.administradoresService = administradoresService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("administradores", administradoresService.getAllAdministradores());
        return "Pages/Admin/dashboard-admin";
    }

    @GetMapping("/perfil")
    public String perfil() {
        return "Pages/Admin/perfil-admin";
    }

    @PostMapping("/perfil/guardar")
    public String guardarPerfil(@Valid @ModelAttribute Administradores administrador) {
        administradoresService.saveAdministrador(administrador);
        return "redirect:/admin/perfil";
    }
}