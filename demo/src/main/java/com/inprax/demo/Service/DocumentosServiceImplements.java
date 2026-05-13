package com.inprax.demo.Service;

import com.inprax.demo.Entity.Documentos;
import com.inprax.demo.Repository.DocumentosRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentosServiceImplements implements DocumentosService {

    private final DocumentosRepository documentosRepository;

    public DocumentosServiceImplements(DocumentosRepository documentosRepository) {
        this.documentosRepository = documentosRepository;
    }

    @Override
    public List<Documentos> getAllDocumentos() {
        return documentosRepository.findAll();
    }

    @Override
    public Documentos getDocumentosById(int idDocumento) {
        return documentosRepository.findById(idDocumento).orElse(null);
    }

    @Override
    public Documentos saveDocumentos(Documentos documentos) {
        return documentosRepository.save(documentos);
    }

    @Override
    public Documentos saveDocumentoConArchivo(int idEstudiante, String tipoDoc, MultipartFile file) {

        try {

            String carpeta = "C:/uploads/documentos/";
            Files.createDirectories(Paths.get(carpeta));

            String nombreArchivo = UUID.randomUUID() + "_" + file.getOriginalFilename();

            Path ruta = Paths.get(carpeta + nombreArchivo).toAbsolutePath().normalize();
            Files.write(ruta, file.getBytes());

            Documentos doc = new Documentos();

            doc.setIdEstudiante(idEstudiante);
            doc.setIdEmpresa(null);
            doc.setTipoDoc(tipoDoc);
            doc.setNombreArchivo(nombreArchivo);
            doc.setUrlArchivo(ruta.toString());
            doc.setFechaSubida(LocalDateTime.now());

            return documentosRepository.save(doc);

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar archivo", e);
        }
    }

    @Override
    public void deleteDocumentosById(int idDocumento) {
        documentosRepository.deleteById(idDocumento);
    }

    @Override
    public Documentos updateDocumentos(int idDocumento, Documentos documentos) {

        Optional<Documentos> existent = documentosRepository.findById(idDocumento);

        if (existent.isPresent()) {
            Documentos d = existent.get();

            d.setIdEstudiante(documentos.getIdEstudiante());
            d.setIdEmpresa(documentos.getIdEmpresa());
            d.setTipoDoc(documentos.getTipoDoc());
            d.setNombreArchivo(documentos.getNombreArchivo());
            d.setUrlArchivo(documentos.getUrlArchivo());
            d.setFechaSubida(LocalDateTime.now());

            return documentosRepository.save(d);
        }

        return null;
    }
}