create table Instituciones(
	id_institucion int auto_increment not null,
    nombre_institucion varchar(50),
    correo_institucion varchar(100),
    direccion_institucion varchar(100),
    numero_telefono varchar(20),
    primary key (id_institucion)
);



create table RepresentanteInstitucion(
	id_representante_institucion int auto_increment not null,
    nombre_representante_institucion varchar(50),
    apellido_representante_institucion varchar(50),
    numero_telefono varchar(20),
    correo_representante_institucion varchar(100),
    id_institucion int,
    id_estudiante int ,
    primary key(id_representante_institucion),
    constraint fk_id_institucion foreign key (id_institucion)
	references Instituciones(id_institucion) on delete cascade,
    constraint fk_estudiante foreign key (id_estudiante)
	references Estudiantes(id_estudiante) on delete cascade
);