package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Empresa;
import com.inprax.demo.Entity.Login;
import com.inprax.demo.Repository.EmpresaRepository;
import com.inprax.demo.Repository.EstudiantesRepository;
import com.inprax.demo.Repository.PracticasRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        if (usuario == null || !usuario.getRoles().equals("Empresa"))
            return null;
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
        Empresa empresa = empresasRepo.findFirstByIdLogin(usuario.getIdLogin());
        model.addAttribute("empresa", empresa);
        model.addAttribute("rolUsuario", "Empresa");
        return "Index/perfil-empresa";
    }

    @PostMapping("/perfil/guardar")
    public String guardarPerfilEmpresa(@ModelAttribute Empresa empresaForm,
                                       HttpSession session,
                                       RedirectAttributes redirectAttributes) {
        Login usuario = verificarEmpresa(session);
        if (usuario == null) return "redirect:/login/login";

        Empresa empresaExistente = empresasRepo.findFirstByIdLogin(usuario.getIdLogin());
        if (empresaExistente == null) {
            redirectAttributes.addFlashAttribute("error", "No se encontró la empresa.");
            return "redirect:/empresa/perfil";
        }

        empresaExistente.setNombreEmpresa(empresaForm.getNombreEmpresa());
        empresaExistente.setCorreoEmpresa(empresaForm.getCorreoEmpresa());
        empresaExistente.setTelefonoEmpresa(empresaForm.getTelefonoEmpresa());
        empresaExistente.setDireccionEmpresa(empresaForm.getDireccionEmpresa());
        empresaExistente.setDescripcion(empresaForm.getDescripcion());
        empresaExistente.setTipoEmpresa(empresaForm.getTipoEmpresa());
        empresaExistente.setTamanoEmpresa(empresaForm.getTamanoEmpresa());
        empresaExistente.setHorarioEmpresa(empresaForm.getHorarioEmpresa());

        empresasRepo.save(empresaExistente);

        redirectAttributes.addFlashAttribute("mensaje", "Perfil actualizado correctamente.");
        return "redirect:/empresa/perfil";
    }

    @GetMapping("/configuracion")
    public String configuracionEmpresa(HttpSession session, Model model) {
        Login usuario = verificarEmpresa(session);
        if (usuario == null) return "redirect:/login/login";
        model.addAttribute("rolUsuario", "Empresa");
        return "Index/configuracion";
    }
}