package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Login;
import com.inprax.demo.Service.LoginService;
import jakarta.validation.Valid;
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
    public String validarLogin(@Valid @ModelAttribute Login login,
                               HttpSession session,
                               Model model) {
        try {
            Login resultado = loginService.validarLogin(
                    login.getCorreoLogin(),
                    login.getUsuarioLogin(),
                    login.getContrasenaLogin(),
                    login.getRoles()
            );
            if (resultado != null) {
                session.setAttribute("usuarioLogueado", resultado);
                return "redirect:/admin/dashboard";
            } else {
                model.addAttribute("error", "Credenciales inválidas");
                return "Pages/login";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error al iniciar sesión");
            return "Index/Login";
        }
    }
}