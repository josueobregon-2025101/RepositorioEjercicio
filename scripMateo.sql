create table Login(
	id_login int auto_increment not null,
    correo_login varchar(50) not null,
    usuario_login varchar(30) not null,
    contrasena_login varchar(20) not null,
    roles varchar(45) not null,
    primary key (id_login)
);
create table Empresa(
	id_empresa int auto_increment not null,
    nombre_empresa varchar(45),
    tipo_empresa varchar(45) not null,
    tamano_empresa varchar(20),
    telefono_empresa varchar(15),
    correo_empresa varchar(50),
    direccion_empresa varchar(45),
    horario_empresa varchar(45),
    descripcion text,
    id_login int not null,
    primary key (id_empresa),
    constraint FK_empresa_login foreign key (id_login)
	references Login(id_login) on delete cascade
);
create table Administradores(
	id_administradores int auto_increment not null,
    nombre_administradores varchar(45) not null,
    apellido_administradores varchar(45) not null,
    estado_administradores varchar(45) not null,
    id_login int not null,
    primary key (id_administradores),
    constraint FK_admin_login foreign key (id_login) 
    references Login(id_login) on delete cascade
);