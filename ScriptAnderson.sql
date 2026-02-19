create table Practicas(
	id_practica int auto_increment not null,
    id_empresa int,
    titulo varchar(60) not null,
    tiempo_practica datetime not null,
    tipo_practica varchar(32) not null,
    carrera_practica varchar(60) not null,
    vigencia varchar(32) not null,
    disponibilidad varchar(60) not null,
    horario datetime not null,
    primary key(id_practica),
    constraint FK_Practicas_empresa foreign key(id_empresa)
    references Empresa(id_empresa) on delete cascade
);
create table Postulaciones(
	id_postulación int auto_increment not null,
    id_practica int,
    titulo varchar(60) not null,
    descripcion varchar(128) not null,
    fecha_post varchar(32) not null,
    estado varchar(32) not null,
    primary key(id_postulación),
    constraint FK_Postulaciones_practica foreign key (id_practica)
    references Practicas(id_practica) on delete cascade
);