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

    //Vistas de Admin //

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalEstudiantes", estudiantesService.getAllEstudiantes().size());
        model.addAttribute("totalEmpresas", empresaService.getAllEmpresas().size());
        model.addAttribute("totalPracticas", practicasService.getAllPracticas().size());
        return "Index/dashboard-admin";
    }

    @GetMapping("/perfil")
    public String perfil(Model model) {
        return "Index/perfil-admin";
    }

    @PostMapping("/perfil/guardar")
    public String guardarPerfilPost(@ModelAttribute Administradores administrador) {
        administradoresService.saveAdministrador(administrador);
        return "redirect:/admin/perfil";
    }

    @GetMapping("/practicas")
    public String practicasAdmin(Model model) {
        List<Practicas> practicas = practicasService.getAllPracticas();
        model.addAttribute("practicas", practicas);
        return "Index/practicas-admin";
    }

    @GetMapping("/practicas/editar/{id}")
    public String editarPractica(@PathVariable Integer id, Model model) {
        Practicas practica = practicasService.getIdPracticas(id);
        model.addAttribute("practica", practica);
        return "Index/editar-practicaadmin";
    }

    @PostMapping("/practicas/editar/{id}")
    public String actualizarPractica(@PathVariable Integer id,
                                     @ModelAttribute Practicas practica) {
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

    @GetMapping("/empresas")
    public String empresas(Model model) {
        model.addAttribute("empresas", empresaService.getAllEmpresas());
        return "Index/empresas";
    }

    // api rest //

    @GetMapping("/lista")
    @ResponseBody
    public List<Administradores> getAllAdministradores() {
        return administradoresService.getAllAdministradores();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getAdministradorById(@PathVariable @Valid Integer id) {
        Administradores administrador = administradoresService.getAdministradorById(id);
        if (administrador != null) {
            return ResponseEntity.ok(administrador);
        } else {
            return ResponseEntity.status(404).body("No existe el administrador");
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createAdministrador(@Valid @RequestBody Administradores administrador) {
        try {
            Administradores creado = administradoresService.saveAdministrador(administrador);
            return ResponseEntity.ok(creado);
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
            if (actualizado != null) {
                return ResponseEntity.ok(actualizado);
            } else {
                return ResponseEntity.status(404).body("No existe el administrador");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteAdministrador(@PathVariable @Valid Integer id) {
        Administradores administrador = administradoresService.getAdministradorById(id);
        if (administrador != null) {
            administradoresService.deleteAdministrador(id);
            return ResponseEntity.ok("Administrador " + id + " eliminado correctamente");
        } else {
            return ResponseEntity.status(404).body("No existe el administrador");
        }
    }
}