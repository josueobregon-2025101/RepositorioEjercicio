package com.inprax.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.inprax.demo.Entity.Login;
import com.inprax.demo.Service.LoginService;


@Controller
@RequestMapping("/register")
public class RegisterControlller {
    
    @Autowired
    private LoginService loginService;

    @GetMapping("/register")
    public String registro(Model model) {
        model.addAttribute("login", new Login());
        return "Index/register";
    }


    @PostMapping("/registro")
    public String guardarUsuario(@ModelAttribute("login") Login login,
                                 @RequestParam("rol") String rol,
                                 Model model) {

        String rolAsignado = rol.equalsIgnoreCase("Estudiante") ? "Estudiante" : "Empresa";

        Login l = loginService.registrarLogin(
                login.getUsuarioLogin(),
                login.getContrasenaLogin(),
                login.getCorreoLogin(),
                rolAsignado
        );

        if (l == null) {
            model.addAttribute("error", "El usuario ya existe");
            return "Index/register";
        }

        return "redirect:/login/login";
    }
    
    
}
