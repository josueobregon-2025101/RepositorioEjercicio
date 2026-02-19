drop database if exists DBpracticasEstudiantes_in5cm;
create database DBpracticasEstudiantes_in5cm;
use DBpracticasEstudiantes_in5cm;

drop database if exists DBpracticasEstudiantes_in5cm;
create database DBpracticasEstudiantes_in5cm;
use DBpracticasEstudiantes_in5cm;

create table Login(
	id_login int auto_increment not null,
    correo_login varchar(50) not null,
    usuario_login varchar(30) not null unique,
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
create table Instituciones(
	id_institucion int auto_increment not null,
    nombre_institucion varchar(50),
    correo_institucion varchar(100),
    direccion_institucion varchar(100),
    numero_telefono varchar(20),
    primary key (id_institucion)
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
-- ////////////////////////----Procedimientos Almacenados----///////////////////////

-- ////////////////////////----Login----///////////////////////

delimiter $$
	create procedure sp_ValidarLogin(in p_correoLogin varchar(50), 
									 in p_usuario varchar(30), 
                                     in p_contrasena varchar(20), 
                                     in p_roles varchar(45))
	begin
	  select idLogin, correoLogin, usuario, contrasena, roles
	  from Login
	  where correoLogin = p_correoLogin 
		and usuario = p_usuario 
        and contrasena = p_contrasena 
        and roles = p_roles
	  limit 1;
	end $$
delimiter ;
 
-- ////////////////////////----empresa----///////////////////////
 
-- Listar Empresa --
delimiter $$
 
create procedure sp_ListarEmpresa()
begin
	select *
    from Empresa
    order by id_empresa;
end $$
 
delimiter ;

-- Agregar Empresa --
 
delimiter $$
create procedure sp_AgregarEmpresa(
    in p_nombreEmpresa varchar(45),
    in p_tipoEmpresa varchar(45),
    in p_tamanoEmpresa varchar(20),
    in p_telefonoEmpresa varchar(15),
    in p_correoEmpresa varchar(50),
    in p_direccionEmpresa varchar(45),
    in p_horarioEmpresa varchar(45),
    in p_descripcion text,
    in p_idLogin int
)
 
begin
    insert into Empresa(nombre_empresa, tipo_empresa, tamano_empresa, telefono_empresa, correo_empresa, direccion_empresa, horario_empresa, descripcion, id_login)
    values(p_nombreEmpresa, p_tipoEmpresa, p_tamanoEmpresa, p_telefonoEmpresa, p_correoEmpresa, p_direccionEmpresa, p_horarioEmpresa, p_descripcion, p_idLogin);
end$$
 
delimiter ;
 
-- Actualizar Empresa --
 
delimiter $$
create procedure sp_ActualizarEmpresa(
    in p_idEmpresa int,
    in p_nombreEmpresa varchar(45),
    in p_tipoEmpresa varchar(45),
    in p_tamanoEmpresa varchar(20),
    in p_telefonoEmpresa varchar(15),
    in p_correoEmpresa varchar(50),
    in p_direccionEmpresa varchar(45),
    in p_horarioEmpresa varchar(45),
    in p_descripcion text,
    in p_idLogin int
)
begin
 
    update Empresa
    set nombre_empresa    = p_nombreEmpresa,
        tipo_empresa      = p_tipoEmpresa,
        tamano_empresa    = p_tamanoEmpresa,
        telefono_empresa  = p_telefonoEmpresa,
        correo_empresa    = p_correoEmpresa,
        direccion_empresa = p_direccionEmpresa,
        horario_empresa   = p_horarioEmpresa,
        descripcion      = p_descripcion,
        id_login          = p_idLogin
    where id_empresa = p_idEmpresa;
end$$
 
delimiter ;
 
-- Eliminar Empresa --
 
delimiter $$
 
create procedure sp_EliminarEmpresa(in p_idEmpresa int)
begin
    delete from Empresa where id_empresa = p_idEmpresa;
end$$
 
delimiter ;
 
-- Buscar Empresa por ID--
 
delimiter $$
 
create procedure sp_BuscarEmpresaPorId(in p_idEmpresa int)
begin
    select * from Empresa where id_empresa = p_idEmpresa;
end$$
 
delimiter ;
 
 
 -- ////////////////////////----Administradores----///////////////////////
-- Agregar Administrador --
 
delimiter $$
 
create procedure sp_ListarAdministradores()
begin
    select * from Administradores order by id_administradores;
end$$
 
delimiter ;

-- Agregar Administrador --
delimiter $$
 
create procedure sp_AgregarAdministrador(
    in p_nombre varchar(45),
    in p_apellido varchar(45),
    in p_estado varchar(45),
    in p_idLogin int
)
 
begin
    insert into Administradores(nombre_administradores, apellido_administradores, estado_administradores, id_login)
    values(p_nombre, p_apellido, p_estado, p_idLogin);
end$$
 
delimiter ;
 
-- Actualizar Administrador --
 
delimiter $$
 
create procedure sp_ActualizarAdministrador(
    in p_idAdministradores int,
    in p_nombre varchar(45),
    in p_apellido varchar(45),
    in p_estado varchar(45),
    in p_idLogin int
)
 
begin
    update Administradores
    set nombre_administradores   = p_nombre,
        apellido_administradores = p_apellido,
        estado_administradores   = p_estado,
        id_login  = p_idLogin
    where id_administradores = p_idAdministradores;
end$$
 
delimiter ;
 
-- Eliminar Administrador --
 
delimiter $$
 
create procedure sp_EliminarAdministrador(in p_idAdministradores int)
begin
    delete from Administradores where id_administradores = p_idAdministradores;
end$$
 
delimiter ;
 
-- Buscar Administrador por ID
 
delimiter $$
 
create procedure sp_BuscarAdministradorPorId(in p_idAdministradores int)
begin
    select * from Administradores where id_administradores = p_idAdministradores;
end$$
 
delimiter ;

-- /////////////////////////// ----Documentos--------//////////////////////////
 
-- Listar Documentos --
delimiter $$
 
create procedure sp_ListarDocumentos()
begin
	select *
    from Documentos
    order by id_documento;
end $$
 
delimiter ;
 
-- Agregar Documento --
 
delimiter $$
create procedure sp_AgregarDocumentos(
    in d_id_estudiante int,
    in d_id_empresa int,
    in d_tipoDoc enum("Carta de Solicitud de Práctica","Carta de Presentación de la Institución",
		"Curriculum Vitae (CV)","Fotocopia de DPI o CUI","Constancia de Estudios","Pensum de la Carrera",
		"Certificación de Notas","Constancia de Seguro Estudiantil","Fotografías tamaño cédula",
		"Informe Final de Práctica"),
    in d_nombreArchivo varchar(50),
    in d_urlArchivo varchar(2048),
    in d_fechaSubida datetime
)
 
begin
    insert into Documentos(id_estudiante, id_empresa, tipoDoc, nombreArchivo, urlArchivo,fechaSubida)
    values(d_id_estudiante, d_id_empresa, d_tipoDoc, d_nombreArchivo, d_urlArchivo, d_fechaSubida);
end$$
 
delimiter ;
 
-- Actualizar Documentos --
 
delimiter $$
create procedure sp_ActualizarDocumentos(
    in d_id_documento int,
    in d_id_estudiante int,
    in d_id_empresa int,
    in d_tipoDoc enum("Carta de Solicitud de Práctica","Carta de Presentación de la Institución",
		"Curriculum Vitae (CV)","Fotocopia de DPI o CUI","Constancia de Estudios","Pensum de la Carrera",
		"Certificación de Notas","Constancia de Seguro Estudiantil","Fotografías tamaño cédula",
		"Informe Final de Práctica"),
    in d_nombreArchivo varchar(50),
    in d_urlArchivo varchar(2048),
    in d_fechaSubida datetime
)
begin
 
    update Documentos
    set id_estudiante    = d_id_estudiante,
        id_empresa      = d_id_empresa,
        tipoDoc    = d_tipoDoc,
        nombreArchivo  = d_nombreArchivo,
        urlArchivo    = d_urlArchivo,
        fechaSubida = d_fechaSubida
    where id_documento = d_id_documento;
end$$
 
delimiter ;
 
-- Eliminar Documentos --
 
delimiter $$
 
create procedure sp_EliminarDocumentos(in d_id_documento int)
begin
    delete from Documentos where id_documento = d_id_documento;
end$$
 
delimiter ;
 
-- Buscar Documento por ID--
 
delimiter $$
 
create procedure sp_BuscarDocumentoPorId(in d_id_documento int)
begin
    select * from Documentos where id_documento = d_id_documento;
end$$
 
delimiter ;
 
  -- /////////////////////////// ----Contrato--------//////////////////////////
-- Listar Contrato --
delimiter $$
 
create procedure sp_ListarContrato()
begin
	select *
    from Contrato
    order by id_contrato;
end $$
 
delimiter ;
 
-- Agregar Contrato --
 
delimiter $$
create procedure sp_AgregarContrato(
    in c_id_postulacion int,
    in c_id_empresa int,
    in c_id_estudiante int,
    in c_id_documento int,
    in c_fechaInicio date,
    in c_fechaFin date,
    in c_objetivos varchar(80)
)
 
begin
    insert into Contrato(id_postulacion , id_empresa, id_estudiante, id_documento, fechaInicio,fechaFin,objetivos)
    values(c_id_postulacion, c_id_empresa, c_id_estudiante, c_id_documento, c_fechaInicio , c_fechaFin,c_objetivos);
end$$
 
delimiter ;
 
-- Actualizar Contrato --
 
delimiter $$
create procedure sp_ActualizarContrato(
    in c_id_contrato int,
    in c_id_postulacion int,
    in c_id_empresa int,
    in c_id_estudiante int,
    in c_id_documento int,
    in c_fechaInicio date,
    in c_fechaFin date,
    in c_objetivos varchar(80)
)
begin
 
    update Contrato
    set id_postulacion    = c_id_postulacion,
        id_empresa      = c_id_empresa,
        id_estudiante    = c_id_estudiante,
        id_documento  = c_id_documento,
        fechaInicio    = c_fechaInicio,
        fechaFin = c_fechaFin,
        objetivos = c_objetivos
    where id_contrato = c_id_contrato;
end$$
 
delimiter ;
 
-- Eliminar Contrato --
 
delimiter $$
 
create procedure sp_EliminarContrato(in c_id_contrato int)
begin
    delete from Contrato where id_contrato = c_id_contrato;
end$$
 
delimiter ;
 
-- Buscar Contrato por ID--
 
delimiter $$
 
create procedure sp_BuscarContratoPorId(in c_id_contrato int)
begin
    select * from Contrato where id_contrato = c_id_contrato;
end$$
 
delimiter ; 