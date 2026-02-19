create table RepresentanteEmpresa (
	id_representantempresa int auto_increment not null,
    id_empresa int,
    nombres varchar(45),
    apellidos varchar(45),
    cargo varchar(45),
    telefono int,
    extension varchar(45),
    estado varchar(45),
    fecha_registro date,
    correo varchar(45),
    primary key(id_representantempresa),
    constraint fk_empresa foreign key (id_empresa)
    references Empresa(id_empresa) on delete cascade
);
create table Estudiantes (
	id_estudiante int auto_increment not null,
    id_institucion int,
    id_login int,
    nombre varchar(30) not null,
    apellido varchar(30) not null,
    telefono int,
    grado varchar(30),
    carrera varchar(40),
    correo varchar(50),
    nombreinstitucion varchar(40),
    tutortel int,
    edad int,
    primary key(id_estudiante),
    constraint fk_institucion foreign key (id_institucion)
	references Instituciones(id_institucion) on delete cascade,
    constraint fk_login foreign key (id_login) 
    references Login(id_login) on delete cascade
);