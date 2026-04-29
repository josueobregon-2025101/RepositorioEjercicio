package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Login;
import com.inprax.demo.Service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/index")
    public String index() {
        return "Index/index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "Index/Login";
    }

    @PostMapping("/validar")
    public String validarLogin(@RequestParam String identificador,
                               @RequestParam String contrasenaLogin,
                               HttpSession session,
                               Model model) {
        try {
            Login resultado = loginService.validarLogin(identificador, contrasenaLogin);
            if (resultado != null) {
                session.setAttribute("usuarioLogueado", resultado);
                return switch (resultado.getRoles()) {
                    case "Administrador" -> "redirect:/api/administradores/admin";
                    case "Empresa"       -> "redirect:/empresa/dashboard";
                    case "Estudiante"    -> "redirect:/estudiantes/estudiantes/dashboard";
                    default              -> "redirect:/login/login";
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
        return "redirect:/login/login";
    }
}
