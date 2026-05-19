package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Estudiantes;
import com.inprax.demo.Entity.Institucion;
import com.inprax.demo.Entity.Login;
import com.inprax.demo.Service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/login/validar")
    @ResponseBody
    public ResponseEntity<?> contrasenaValid(@RequestParam String contrasena, HttpSession session) {
        Login usuario = (Login) session.getAttribute("usuarioLogueado");
        if (usuario == null){
            return ResponseEntity.status(401).body("No autorizado");
        }
        if(usuario.getContrasenaLogin().equals(contrasena)){

            return ResponseEntity.ok(true);

        }else{

            return ResponseEntity
                    .badRequest()
                    .body(false);
        }
    }

    @PutMapping("/login/edit/{id}")
    public ResponseEntity<?> editarUsuario(@PathVariable Integer id, @RequestBody Login login ,HttpSession session) {
        try {
            Login actualizada = loginService.actualizarLogin(id, login);
            if (actualizada != null) {
                session.setAttribute("usuarioLogueado", actualizada);
                return ResponseEntity.ok(actualizada);


            } else {
                return ResponseEntity.status(404).body("No se encontro el usuario");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}