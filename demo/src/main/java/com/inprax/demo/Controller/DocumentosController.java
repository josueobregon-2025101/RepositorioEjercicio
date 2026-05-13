package com.inprax.demo.Controller;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.inprax.demo.Service.DocumentosService;
import com.inprax.demo.Entity.Documentos;

import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/documentos")
public class DocumentosController {

    private final DocumentosService documentosService;

    public DocumentosController(DocumentosService documentosService) {
        this.documentosService = documentosService;
    }

    @GetMapping
    public String documentos(Model model) {

        model.addAttribute(
            "documentos",
            documentosService.getAllDocumentos()
        );

        return "Index/documentos";
    }

}