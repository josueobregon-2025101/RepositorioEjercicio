package com.inprax.demo.Controller;

import com.inprax.demo.Entity.Empresa;
import com.inprax.demo.Service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public String getAllEmpresas(Model model) {
        model.addAttribute("empresas", empresaService.getAllEmpresas());
        return "Pages/Admin/empresas";
    }

    @GetMapping("/agregar")
    public String agregarEmpresaForm() {
        return "Pages/Admin/agregar-empresa";
    }

    @PostMapping("/guardar")
    public String guardarEmpresa(@Valid @ModelAttribute Empresa empresa) {
        empresaService.saveEmpresa(empresa);
        return "redirect:/admin/empresas";
    }

    @GetMapping("/editar/{id}")
    public String editarEmpresaForm(@PathVariable Integer id, Model model) {
        model.addAttribute("empresa", empresaService.getEmpresaById(id));
        return "Pages/Admin/editar-empresa";
    }

    @PostMapping("/editar")
    public String editarEmpresa(@RequestParam Integer id,
                                @Valid @ModelAttribute Empresa empresa) {
        empresaService.updateEmpresa(id, empresa);
        return "redirect:/admin/empresas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEmpresa(@PathVariable Integer id) {
        empresaService.deleteEmpresa(id);
        return "redirect:/admin/empresas";
    }
}