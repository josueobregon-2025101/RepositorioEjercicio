package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Administradores;
import com.inprax.demo.Entity.Login;
import com.inprax.demo.Repository.EmpresaRepository;
import com.inprax.demo.Repository.EstudiantesRepository;
import com.inprax.demo.Repository.PracticasRepository;
import com.inprax.demo.Service.AdministradoresService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/administradores")
public class AdministradoresController {

    private final AdministradoresService administradoresService;

    public AdministradoresController(AdministradoresService administradoresService) {
        this.administradoresService = administradoresService;
    }

    @Autowired
    public EstudiantesRepository estudiantesRepo;

    @Autowired
    EmpresaRepository empresasRepo;

    @Autowired
    public PracticasRepository practicasRepo;

    private Login verificarAdmin(HttpSession session) {
        Login usuario = (Login) session.getAttribute("usuarioLogueado");
        if (usuario == null || !usuario.getRoles().equals("Administrador")) return null;
        return usuario;
    }

    @GetMapping("/admin")
    public String dashboard(HttpSession session, Model model) {
        Login usuario = verificarAdmin(session);
        if (usuario == null) return "redirect:/login/login";
        model.addAttribute("rolUsuario", usuario.getRoles());
        model.addAttribute("totalEstudiantes", estudiantesRepo.count());
        model.addAttribute("totalEmpresas", empresasRepo.count());
        model.addAttribute("totalPracticas", practicasRepo.count());
        return "Index/dashboard-admin";
    }

    @GetMapping("/perfil")
    public String perfilAdmin(HttpSession session, Model model) {
        Login usuario = verificarAdmin(session);
        if (usuario == null) return "redirect:/login/login";
        model.addAttribute("rolUsuario", usuario.getRoles());
        return "Index/perfil-admin";
    }

    @GetMapping("/configuracion")
    public String config(HttpSession session, Model model) {
        Login usuario = verificarAdmin(session);
        if (usuario == null) return "redirect:/login/login";
        model.addAttribute("rolUsuario", usuario.getRoles());
        return "Index/configuracion";
    }
}