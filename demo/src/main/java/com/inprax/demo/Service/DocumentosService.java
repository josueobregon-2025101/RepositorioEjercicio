package com.inprax.demo.Service;

import com.inprax.demo.Entity.Documentos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DocumentosService{
    List<Documentos> getAllDocumentos();
    Documentos getDocumentosById(int idDocumento);
    Documentos saveDocumentos(Documentos documentos) throws RuntimeException;
    Documentos updateDocumentos(int idDocumento,Documentos documentos);
    void deleteDocumentosById(int idDocumento);
}
