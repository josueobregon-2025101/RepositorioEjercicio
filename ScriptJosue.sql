Create table Documentos(
	id_documento int not null auto_increment,
    id_estudiante int,
    id_empresa int,
    tipoDoc enum("Carta de Solicitud de Práctica","Carta de Presentación de la Institución",
		"Curriculum Vitae (CV)","Fotocopia de DPI o CUI","Constancia de Estudios","Pensum de la Carrera",
		"Certificación de Notas","Constancia de Seguro Estudiantil","Fotografías tamaño cédula",
		"Informe Final de Práctica"),
    nombreArchivo varchar(50),
    urlArchivo varchar(2048),
    fechaSubida datetime,
    primary key (id_documento)
);

Create table Contrato(
	id_contrato int not null auto_increment,
    id_postulacion int,
    id_empresa int,
    id_estudiante int,
    id_documento int,
    fechaInicio date,
    fechaFin date,
    objetivos varchar(80),
    primary key (id_contrato)
);
