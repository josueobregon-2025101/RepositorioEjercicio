package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Administradores;
import com.inprax.demo.Entity.Practicas;
import com.inprax.demo.Service.AdministradoresService;
import com.inprax.demo.Service.EmpresaService;
import com.inprax.demo.Service.EstudiantesService;
import com.inprax.demo.Service.PracticasService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdministradoresController {

    private final AdministradoresService administradoresService;
    private final EstudiantesService estudiantesService;
    private final EmpresaService empresaService;
    private final PracticasService practicasService;

    public AdministradoresController(AdministradoresService administradoresService,
                                     EstudiantesService estudiantesService,
                                     EmpresaService empresaService,
                                     PracticasService practicasService) {
        this.administradoresService = administradoresService;
        this.estudiantesService = estudiantesService;
        this.empresaService = empresaService;
        this.practicasService = practicasService;
    }

    //vistas

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalEstudiantes", estudiantesService.getAllEstudiantes().size());
        model.addAttribute("totalEmpresas", empresaService.getAllEmpresas().size());
        model.addAttribute("totalPracticas", practicasService.getAllPracticas().size());
        return "Index/dashboard-admin";
    }

    @GetMapping("/perfil")
    public String perfil() {
        return "Index/perfil-admin";
    }

    @GetMapping("/practicas")
    public String practicas(Model model) {
        model.addAttribute("practicas", practicasService.getAllPracticas());
        return "Index/practicas-admin";
    }

    @GetMapping("/practicas/editar/{id}")
    public String editarPractica(@PathVariable Integer id, Model model) {
        model.addAttribute("practica", practicasService.getIdPracticas(id));
        return "Index/editar-practicaadmin";
    }

    @PostMapping("/practicas/editar/{id}")
    public String actualizarPractica(@PathVariable Integer id, @ModelAttribute Practicas practica) {
        practicasService.updatePracticas(practica, id);
        return "redirect:/admin/practicas";
    }

    @GetMapping("/practicas/eliminar/{id}")
    public String eliminarPractica(@PathVariable Integer id) {
        practicasService.deletePracticas(id);
        return "redirect:/admin/practicas";
    }


    @GetMapping("/estudiantes")
    public String estudiantes(Model model) {
        model.addAttribute("estudiantes", estudiantesService.getAllEstudiantes());
        return "Index/estudiantes";
    }

    @GetMapping("/estudiantes/nuevo")
    public String nuevoEstudiante(Model model) {
        return "redirect:/estudiantes/nuevo";
    }

    @GetMapping("/empresas")
    public String empresas(Model model) {
        model.addAttribute("empresas", empresaService.getAllEmpresas());
        return "Index/empresas";
    }

    @GetMapping("/empresas/nuevo")
    public String nuevaEmpresa() {
        return "redirect:/empresas/nuevo";
    }

    @GetMapping("/empresas/guardar")
    public String guardarEmpresaRedirect() {
        return "redirect:/empresas/nuevo";
    }

    //APIs

    @GetMapping("/lista")
    @ResponseBody
    public List<Administradores> getAllAdministradores() {
        return administradoresService.getAllAdministradores();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getAdministradorById(@PathVariable @Valid Integer id) {
        Administradores a = administradoresService.getAdministradorById(id);
        return a != null ? ResponseEntity.ok(a)
                : ResponseEntity.status(404).body("No existe el administrador");
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createAdministrador(@Valid @RequestBody Administradores administrador) {
        try {
            return ResponseEntity.ok(administradoresService.saveAdministrador(administrador));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear el administrador");
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateAdministrador(@PathVariable Integer id,
                                                 @Valid @RequestBody Administradores administrador) {
        try {
            Administradores actualizado = administradoresService.updateAdministrador(id, administrador);
            return actualizado != null ? ResponseEntity.ok(actualizado)
                    : ResponseEntity.status(404).body("No existe el administrador");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteAdministrador(@PathVariable @Valid Integer id) {
        Administradores a = administradoresService.getAdministradorById(id);
        if (a != null) {
            administradoresService.deleteAdministrador(id);
            return ResponseEntity.ok("Administrador " + id + " eliminado");
        }
        return ResponseEntity.status(404).body("No existe el administrador");
    }
}
