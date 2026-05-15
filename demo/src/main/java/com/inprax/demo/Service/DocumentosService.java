package com.inprax.demo.Service;

import com.inprax.demo.Entity.Documentos;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentosService {

    List<Documentos> getAllDocumentos();

    Documentos getDocumentosById(int idDocumento);

    Documentos saveDocumentos(Documentos documentos);

    Documentos updateDocumentos(int idDocumento, Documentos documentos);

    void deleteDocumentosById(int idDocumento);

    Documentos saveDocumentoConArchivo(int idEstudiante, String tipoDoc, MultipartFile file);
    
}