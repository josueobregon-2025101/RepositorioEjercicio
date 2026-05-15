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

    @GetMapping("/admin")
    public String dashboard(HttpSession session, Model model) {
        Login usuario = (Login) session.getAttribute("usuarioLogueado");
        String rol = (usuario != null) ? usuario.getRoles() : "Administrador";
        model.addAttribute("rolUsuario", rol);
        model.addAttribute("totalEstudiantes", estudiantesRepo.count());
        model.addAttribute("totalEmpresas", empresasRepo.count());
        model.addAttribute("totalPracticas", practicasRepo.count());
        return "Index/dashboard-admin";
    }

    @GetMapping("/perfil")
    public String perfilAdmin(HttpSession session, Model model){
        Login usuario = (Login) session.getAttribute("usuarioLogueado");
        String rol = (usuario != null) ? usuario.getRoles() : "Administrador";
        model.addAttribute("rolUsuario", rol);
        return "Index/perfil-admin";
    }


    @GetMapping("/configuracion")
    public String config(HttpSession session, Model model){
        Login usuario = (Login) session.getAttribute("usuarioLogueado");
        String rol = (usuario != null) ? usuario.getRoles() : "Administrador";
        model.addAttribute("rolUsuario", rol);
        return "Index/configuracion";
    }


}
