package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Login;
import com.inprax.demo.Repository.EmpresaRepository;
import com.inprax.demo.Repository.EstudiantesRepository;
import com.inprax.demo.Repository.PracticasRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empresa")
public class EmpresaDashboardController {

    @Autowired
    private EstudiantesRepository estudiantesRepo;

    @Autowired
    private EmpresaRepository empresasRepo;

    @Autowired
    private PracticasRepository practicasRepo;

    private Login verificarEmpresa(HttpSession session) {
        Login usuario = (Login) session.getAttribute("usuarioLogueado");
        if (usuario == null || !usuario.getRoles().equals("Empresa")) return null;
        return usuario;
    }

    @GetMapping("/dashboard")
    public String dashboardEmpresa(HttpSession session, Model model) {
        Login usuario = verificarEmpresa(session);
        if (usuario == null) return "redirect:/login/login";
        model.addAttribute("totalEstudiantes", estudiantesRepo.count());
        model.addAttribute("totalEmpresas", empresasRepo.count());
        model.addAttribute("totalPracticas", practicasRepo.count());
        model.addAttribute("rolUsuario", "Empresa");
        return "Index/dashboard-admin";
    }

    @GetMapping("/perfil")
    public String perfilEmpresa(HttpSession session, Model model) {
        Login usuario = verificarEmpresa(session);
        if (usuario == null) return "redirect:/login/login";
        model.addAttribute("rolUsuario", "Empresa");
        return "Index/perfil-admin";
    }

    @GetMapping("/configuracion")
    public String configuracionEmpresa(HttpSession session, Model model) {
        Login usuario = verificarEmpresa(session);
        if (usuario == null) return "redirect:/login/login";
        model.addAttribute("rolUsuario", "Empresa");
        return "Index/configuracion";
    }
}
