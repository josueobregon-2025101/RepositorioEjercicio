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
    public String index() { return "redirect:/login"; }

    @GetMapping("/login")
    public String loginForm() { return "Index/Login"; }

    @PostMapping("/login")
    public String validarLogin(@RequestParam String identificador,
                               @RequestParam String contrasenaLogin,
                               HttpSession session,
                               Model model) {
        try {
            Login resultado = loginService.validarLogin(identificador, contrasenaLogin);
            if (resultado != null) {
                session.setAttribute("usuarioLogueado", resultado);
                return switch (resultado.getRoles()) {
                    case "Administrador" -> "redirect:/admin/dashboard";
                    case "Empresa"       -> "redirect:/empresa/dashboard";
                    case "Estudiante"    -> "redirect:/estudiante/dashboard";
                    default              -> "redirect:/login";
                };
            } else {
                model.addAttribute("error", "Credenciales inválidas");
                return "Index/Login";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error al iniciar sesión");
            return "Index/Login";
        }
    }

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

    @GetMapping("/register")
    public String registerForm() { return "Index/register"; }

    // redirects
    @GetMapping("/dashboard-admin.html")
    public String r1() { return "redirect:/admin/dashboard"; }
    @GetMapping("/Index/dashboard-admin.html")
    public String r2() { return "redirect:/admin/dashboard"; }
    @GetMapping("/perfil-admin.html")
    public String r3() { return "redirect:/admin/perfil"; }
    @GetMapping("/practicas-admin.html")
    public String r4() { return "redirect:/admin/practicas"; }
    @GetMapping("/login.html")
    public String r5() { return "redirect:/login"; }
}