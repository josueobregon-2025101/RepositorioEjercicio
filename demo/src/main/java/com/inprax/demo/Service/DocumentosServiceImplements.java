package com.inprax.demo.Service;

import com.inprax.demo.Entity.Documentos;
import com.inprax.demo.Repository.DocumentosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentosServiceImplements implements DocumentosService{
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
    public Documentos saveDocumentos(Documentos documentos) throws RuntimeException {
        return documentosRepository.save(documentos);
    }

    @Override
    public Documentos updateDocumentos(int idDocumento, Documentos documentos) {
        Optional<Documentos> existentDocument = documentosRepository.findById(idDocumento);
        if (existentDocument.isPresent()){
            Documentos newDocument = existentDocument.get();
            newDocument.setIdEstudiante(documentos.getIdEstudiante());
            newDocument.setIdEmpresa(documentos.getIdEmpresa());
            newDocument.setTipoDoc(documentos.getTipoDoc());
            newDocument.setNombreArchivo(documentos.getNombreArchivo());
            newDocument.setUrlArchivo(documentos.getUrlArchivo());
            newDocument.setFechaSubida(documentos.getFechaSubida());
            return documentosRepository.save(newDocument);
        }
        return null;
    }

    @Override
    public void deleteDocumentosById(int idDocumento) {
    documentosRepository.deleteById(idDocumento);
    }
}
