package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Login;
import com.inprax.demo.Service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "Index/Login";
    }

    @PostMapping("/login")
    public String validarLogin(@RequestParam String correoLogin,
                               @RequestParam String contrasenaLogin,
                               HttpSession session,
                               Model model) {
        try {
            Login resultado = loginService.validarLogin(correoLogin, contrasenaLogin);
            if (resultado != null) {
                session.setAttribute("usuarioLogueado", resultado);
                return "redirect:/admin/dashboard";
            } else {
                model.addAttribute("error", "Credenciales inválidas");
                return "Index/Login";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error al iniciar sesión");
            return "Index/Login";
        }
    }

    //redirecs de las rutas
    @GetMapping("/dashboard-admin.html")
    public String oldDashboardAdmin() { return "redirect:/admin/dashboard"; }

    @GetMapping("/Index/dashboard-admin.html")
    public String oldIndexDashboardAdmin() { return "redirect:/admin/dashboard"; }

    @GetMapping("/perfil-admin.html")
    public String oldPerfilAdmin() { return "redirect:/admin/perfil"; }

    @GetMapping("/practicas-admin.html")
    public String oldPracticasAdmin() { return "redirect:/admin/practicas"; }

    @GetMapping("/estudiantes.html")
    public String oldEstudiantes() { return "redirect:/admin/estudiantes"; }

    @GetMapping("/empresas.html")
    public String oldEmpresas() { return "redirect:/admin/empresas"; }

    @GetMapping("/login.html")
    public String oldLogin() { return "redirect:/login"; }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/admin/logout")
    public String logoutAdmin(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}