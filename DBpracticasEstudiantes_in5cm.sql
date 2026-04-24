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
	id_postulacion int auto_increment not null,
    id_practica int,
    titulo varchar(60) not null,
    descripcion varchar(128) not null,
    fecha_post varchar(32) not null,
    estado varchar(32) not null,
    primary key(id_postulacion),
    constraint FK_Postulaciones_practica foreign key (id_practica)
    references Practicas(id_practica) on delete cascade
);
 
Create table Documentos(
	id_documento int not null auto_increment,
    id_estudiante int,
    id_empresa int,
    tipo_doc varchar(100),
    nombre_archivo varchar(50),
    url_archivo varchar(2048),
    fecha_subida datetime,
    primary key (id_documento),
    foreign key(id_estudiante) references Estudiantes(id_estudiante) on delete cascade,
    foreign key(id_empresa) references Empresa(id_empresa) on delete cascade
);
 
Create table Contrato(
	id_contrato int not null auto_increment,
    id_postulacion int,
    id_empresa int,
    id_estudiante int,
    id_documento int,
    fecha_inicio date,
    fecha_fin date,
    objetivos varchar(80),
    primary key (id_contrato),
    foreign key(id_postulacion)references Postulaciones(id_postulacion) on delete cascade,
	foreign key(id_empresa)references Empresa(id_empresa) on delete cascade,
    foreign key(id_estudiante)references Estudiantes(id_estudiante)on delete cascade,
    foreign key(id_documento)references Documentos(id_documento) on delete cascade
);
-- ////////////////////////----Procedimientos Almacenados----///////////////////////
 
-- ////////////////////////----Login----///////////////////////
 
delimiter $$
	create procedure sp_ValidarLogin(in p_correoLogin varchar(50), 
									 in p_usuario varchar(30), 
                                     in p_contrasena varchar(20), 
                                     in p_roles varchar(45))
	begin
	  select id_login, correo_login, usuario_login, contrasena_login, roles
	  from Login
	  where correo_login = p_correoLogin 
		and usuario_login = p_usuario 
        and contrasena_login = p_contrasena 
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
 
-- /////////////////-----REPRESENTATE DE LA EMPRESA--------///////////////
 
delimiter $$
create procedure sp_RepresentanteEmpresa_create(
    in p_id_empresa int,
    in p_nombres varchar(45),
    in p_apellidos varchar(45),
    in p_cargo varchar(45),
    in p_telefono int,
    in p_extension varchar(45),
    in p_estado varchar(45),
    in p_fecha_registro date,
    in p_correo varchar(45)
)
begin
    insert into RepresentanteEmpresa(id_empresa, nombres, apellidos, cargo, telefono, extension, estado, fecha_registro, correo)
	values (p_id_empresa, p_nombres, p_apellidos, p_cargo, p_telefono, p_extension, p_estado, p_fecha_registro, p_correo);
end$$
delimiter ;
 
delimiter $$
create procedure sp_RepresentanteEmpresa_read_all()
begin
    select * from RepresentanteEmpresa;
end$$
delimiter ;
 
delimiter $$
create procedure sp_RepresentanteEmpresa_read_by_id(
	in p_id_representanteempresa int
)
begin
    select * 
    from RepresentanteEmpresa 
    where id_representantempresa = p_id_representanteempresa;
end$$
delimiter ;
 
delimiter $$
create procedure sp_RepresentanteEmpresa_update(
    in p_id_representanteempresa int,
    in p_id_empresa int,
    in p_nombres varchar(45),
    in p_apellidos varchar(45),
    in p_cargo varchar(45),
    in p_telefono int,
    in p_extension varchar(45),
    in p_estado varchar(45),
    in p_fecha_registro date,
    in p_correo varchar(45)
)
begin
    update RepresentanteEmpresa
    set id_empresa = p_id_empresa,
        nombres = p_nombres,
        apellidos = p_apellidos,
        cargo = p_cargo,
        telefono = p_telefono,
        extension = p_extension,
        estado = p_estado,
        fecha_registro = p_fecha_registro,
        correo = p_correo
    where id_representantempresa = p_id_representanteempresa;
 
    select row_count() as filas_afectadas;
end$$
delimiter ;
 
delimiter $$
create procedure sp_RepresentanteEmpresa_delete(
	in p_id_representanteempresa int
)
begin
    delete from RepresentanteEmpresa 
    where id_representantempresa = p_id_representanteempresa;
 
    select row_count() as filas_afectadas;
end$$
delimiter ;
 
-- /////////////////-------Estudiante--------////////////////////
 
delimiter $$
create procedure sp_Estudiantes_create(
    in p_id_institucion int,
    in p_id_login int,
    in p_nombre varchar(30),
    in p_apellido varchar(30),
    in p_telefono int,
    in p_grado varchar(30),
    in p_carrera varchar(40),
    in p_correo varchar(50),
    in p_nombreinstitucion varchar(40),
    in p_tutortel int,
    in p_edad int
)
begin
    insert into Estudiantes(id_institucion, id_login, nombre, apellido, telefono, grado, carrera, correo, nombreinstitucion, tutortel, edad)
    values (p_id_institucion, p_id_login, p_nombre, p_apellido, p_telefono, p_grado, p_carrera, p_correo, p_nombreinstitucion, p_tutortel, p_edad);
end$$
delimiter ;
 
delimiter $$
create procedure sp_Estudiante_read_all()
begin
    select * from Estudiantes;
end$$
delimiter ;
 
delimiter $$
create procedure sp_Estudiantes_read_by_id(
	in p_id_estudiante int
)
begin
    select * 
    from Estudiantes
    where id_estudiante = p_id_estudiante;
end$$
delimiter ;
 
delimiter $$
create procedure sp_Estudiantes_update(
    in p_id_estudiante int,
    in p_id_institucion int,
    in p_id_login int,
    in p_nombre varchar(30),
    in p_apellido varchar(30),
    in p_telefono int,
    in p_grado varchar(30),
    in p_carrera varchar(40),
    in p_correo varchar(50),
    in p_nombreinstitucion varchar(40),
    in p_tutortel int,
    in p_edad int
)
begin
    update Estudiantes
    set id_institucion = p_id_institucion,
        id_login = p_id_login,
        nombre = p_nombre,
        apellido = p_apellido,
        telefono = p_telefono,
        grado = p_grado,
        carrera = p_carrera,
        correo = p_correo,
        nombreinstitucion = p_nombreinstitucion,
        tutortel = p_tutortel,
        edad = p_edad
    where id_estudiante = p_id_estudiante;
 
    select row_count() as filas_afectadas;
end$$
delimiter ;
 
delimiter $$
create procedure sp_Estudiantes_delete(
	in p_id_estudiante int
)
begin
    delete from Estudiantes 
    where id_estudiante = p_id_estudiante;
 
    select row_count() as filas_afectadas;
end$$
delimiter ;
-- //////////////////////////------Instituciones--------////////////////////
 
delimiter $$
create procedure sp_instituciones_create(
	in p_nombre varchar(50),
    in p_correo varchar(100),
    in p_direccion varchar(100),
    in p_numeroTelefono varchar(20)
)
begin
	insert into Instituciones(nombre_institucion, correo_institucion, direccion_institucion, numero_telefono)
	values (p_nombre, p_correo, p_direccion, p_numeroTelefono);
end$$
delimiter ;
 
delimiter $$
create procedure sp_instituciones_read_all()
begin
	select * from Instituciones order by id_institucion;
end$$
delimiter ;
 
delimiter $$
create procedure sp_instituciones_read_by_id(p_id int)
begin
	select * from Instituciones where id_institucion = p_id;
end$$
delimiter ;
 
delimiter $$
create procedure sp_instituciones_update(
	in p_id int,
	in p_nombreInstitucion varchar(50),
    in p_correoInstitucion varchar(100),
    in p_direccionIntitucion varchar(100),
    in p_numeroTelefono varchar(20)
)
begin
	update Instituciones
    set nombre_institucion = p_nombreInstitucion,
    correo_institucion = p_correoInstitucion,
    direccion_institucion = p_direccionIntitucion,
    numero_telefono = p_numeroTelefono
    where id_institucion = p_id;
    select row_count() as filas_afectadas;
end$$
delimiter ;
 
delimiter $$
create procedure sp_instituciones_delete(p_id int)
begin
	delete from Instituciones where id_institucion = p_id;
    select row_count() as filas_afectadas;
end$$
delimiter ;
 
 
-- /////////////////////////--------REPRESENTANTE DE LA INSTITUCION----------------////////////////////////
 
delimiter $$
create procedure sp_representantesInstitucion_create(
    in p_nombreRepresentanteInstitucion varchar(50),
    in p_apellidoRepresentanteInstitucion varchar(50),
    in p_numeroTelefono varchar(20),
    in p_correoRepresentanteInstitucion varchar(100),
    in p_idInstitucion int,
    in p_idEstudiante int
)
begin
    insert into RepresentanteInstitucion(nombre_representante_institucion, apellido_representante_institucion, numero_telefono, correo_representante_institucion, id_institucion, id_estudiante )
    values (p_nombreRepresentanteInstitucion, p_apellidoRepresentanteInstitucion, p_numeroTelefono, p_correoRepresentanteInstitucion, p_idInstitucion, p_idEstudiante);
    end$$
delimiter ;
 
delimiter $$
create procedure sp_representantesInstitucion_read_all()
begin
    select * from RepresentanteInstitucion order by id_representante_institucion;
end$$
delimiter ;
 
delimiter $$
create procedure sp_representantesInstitucion_read_by_id(p_id int)
begin
    select * 
    from RepresentanteInstitucion 
    where id_representante_institucion = p_id;
end$$
delimiter ;
 
delimiter $$
create procedure sp_representantesInstitucion_update(
    in p_id int,
    in p_nombreRepresentanteInstitucion varchar(50),
    in p_apellidoRepresentanteInstitucion varchar(50),
    in p_numeroTelefono varchar(20),
    in p_correoRepresentanteInstitucion varchar(100),
    in p_idInstitucion int,
    in p_idEstudiante int
)
begin
    update RepresentanteInstitucion
    set nombre_representante_institucion = p_nombreRepresentanteInstitucion,
        apellido_representante_institucion = p_apellidoRepresentanteInstitucion,
        numero_telefono = p_numeroTelefono,
        correo_representante_institucion = p_correoRepresentanteInstitucion,
        id_institucion = p_idInstitucion,
        id_estudiante = p_idEstudiante
    where id_representante_institucion = p_id;
 
    select row_count() as filas_afectadas;
end$$
delimiter ;
 
delimiter $$
create procedure sp_representantesInstitucion_delete(p_id int)
begin
    delete from RepresentanteInstitucion 
    where id_representante_institucion = p_id;
 
    select row_count() as filas_afectadas;
end$$
delimiter ;
 
-- /////////////////////////////////------ Practicas----------//////////////////////////////
-- Insertar práctica
delimiter $$
create procedure sp_insertar_practica(in p_id_empresa int, in p_titulo varchar(60),
				in p_tiempo_practica datetime,in p_tipo_practica varchar(32),
				in p_carrera_practica varchar(60),in p_vigencia varchar(32),
				in p_disponibilidad varchar(60),in p_horario datetime
)
begin
    insert into Practicas(id_empresa,titulo, 
						  tiempo_practica, tipo_practica, 
						  carrera_practica, vigencia, 
                          disponibilidad, horario) 
    values (p_id_empresa,p_titulo, 
			p_tiempo_practica, p_tipo_practica, 
			p_carrera_practica, p_vigencia, 
			p_disponibilidad, p_horario);
end $$
delimiter ;
 
-- Actualizar práctica
delimiter $$
create procedure sp_actualizar_practica(in p_id_practica int,in p_id_empresa int,
										in p_titulo varchar(60),in p_tiempo_practica datetime,
										in p_tipo_practica varchar(32),in p_carrera_practica varchar(60),
										in p_vigencia varchar(32),in p_disponibilidad varchar(60),
										in p_horario datetime)
begin
    update Practicas 
    set id_empresa = p_id_empresa,
        titulo = p_titulo,
        tiempo_practica = p_tiempo_practica,
        tipo_practica = p_tipo_practica,
        carrera_practica = p_carrera_practica,
        vigencia = p_vigencia,
        disponibilidad = p_disponibilidad,
        horario = p_horario
    where id_practica = p_id_practica;
    select row_count() as filas_afectadas;
end $$
delimiter ;
 
-- Eliminar práctica
delimiter $$
create procedure sp_eliminar_practica(
    in p_id_practica int
)
begin
    delete from Practicas where id_practica = p_id_practica;
    select row_count() as filas_afectadas;
end $$
delimiter ;
 
-- Obtener todas las prácticas
delimiter $$
create procedure sp_obtener_practicas()
begin
    select * from Practicas order by id_practica;
end $$
delimiter ;
 
-- Obtener práctica por ID
delimiter $$
create procedure sp_obtener_practica_por_id(
    in p_id_practica int
)
begin
    select * from Practicas where id_practica = p_id_practica;
end $$
delimiter ;
 
-- Obtener prácticas por empresa
delimiter $$
create procedure sp_obtener_practicas_por_empresa(
    in p_id_empresa int
)
begin
    select * from Practicas where id_empresa = p_id_empresa;
end $$
delimiter ;
 
 
-- //////////////////////////////----------Postulaciones------------////////////////////////////
 
-- Insertar postulación
delimiter $$
create procedure sp_insertar_postulacion(in p_id_practica int,in p_titulo varchar(60),
										in p_descripcion varchar(128),in p_fecha_post varchar(32),
										in p_estado varchar(32))
begin
    insert into Postulaciones(id_practica,titulo, 
							  descripcion, fecha_post, estado) 
	values (p_id_practica,p_titulo, 
        p_descripcion, p_fecha_post,
        p_estado);
 
end $$
delimiter ;
 
-- Actualizar postulación
delimiter $$
create procedure sp_actualizar_postulacion(
    in p_id_postulacion int,
    in p_id_practica int,
    in p_titulo varchar(60),
    in p_descripcion varchar(128),
    in p_fecha_post varchar(32),
    in p_estado varchar(32)
)
begin
    update Postulaciones 
    set id_practica = p_id_practica,
        titulo = p_titulo,
        descripcion = p_descripcion,
        fecha_post = p_fecha_post,
        estado = p_estado
    where id_postulacion = p_id_postulacion;
    select row_count() as filas_afectadas;
end $$
delimiter ;
 
-- Eliminar postulación
delimiter $$
create procedure sp_eliminar_postulacion(
    in p_id_postulacion int
)
begin
    delete from Postulaciones where id_postulacion = p_id_postulacion;
    select row_count() as filas_afectadas;
end $$
delimiter ;
 
-- Obtener todas las postulaciones
delimiter $$
create procedure sp_obtener_postulaciones()
begin
    select * from Postulaciones order by id_postulacion;
end $$
delimiter ;
 
-- Obtener postulación por ID
delimiter $$
create procedure sp_obtener_postulacion_por_id(
    in p_id_postulacion int
)
begin
    select * from Postulaciones where id_postulacion = p_id_postulacion;
end $$
delimiter ;
 
-- Obtener postulaciones por práctica
delimiter $$
create procedure sp_obtener_postulaciones_por_practica(
    in p_id_practica int
)
begin
    select * from Postulaciones where id_practica = p_id_practica;
end $$
delimiter ;
 
-- Obtener postulaciones con detalles de práctica
delimiter $$
create procedure sp_obtener_postulaciones_con_practica()
begin
    select p.*, pr.titulo as titulo_practica, pr.tipo_practica, pr.carrera_practica, pr.id_empresa
    from Postulaciones p
    inner join Practicas pr on p.id_practica = pr.id_practica;
end $$
delimiter ;
 
-- Actualizar estado de postulación
delimiter $$
create procedure sp_actualizar_estado_postulacion(
    in p_id_postulacion int,
    in p_estado varchar(32)
)
begin
    update Postulaciones 
    set estado = p_estado
    where id_postulacion = p_id_postulacion;
    select row_count() as filas_afectadas;
end $$
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
    in d_tipoDoc varchar(100),
    in d_nombreArchivo varchar(50),
    in d_urlArchivo varchar(2048),
    in d_fechaSubida datetime
)
begin
    insert into Documentos(id_estudiante, id_empresa, tipo_doc, nombre_archivo, url_archivo,fecha_subida)
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
        tipo_doc    = d_tipoDoc,
        nombre_archivo  = d_nombreArchivo,
        url_archivo    = d_urlArchivo,
        fecha_subida = d_fechaSubida
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
    insert into Contrato(id_postulacion , id_empresa, id_estudiante, id_documento, fecha_inicio,fecha_fin,objetivos)
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
        fecha_inicio    = c_fechaInicio,
        fecha_fin = c_fechaFin,
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
 
-- ================================
-- PRUEBAS CREATE / INSERT
-- DBpracticasEstudiantes_in5cm
-- ================================
 
-- =========================
-- LOGIN (5 USUARIOS)
-- =========================
 
insert into Login (correo_login, usuario_login, contrasena_login, roles) values
('admin1@gmail.com','admin1','12345','Administrador'),
('empresa1@gmail.com','empresa1','12345','Empresa'),
('empresa2@gmail.com','empresa2','12345','Empresa'),
('estudiante1@gmail.com','estudiante1','12345','Estudiante'),
('estudiante2@gmail.com','estudiante2','12345','Estudiante');
 
 
-- =========================
-- INSTITUCIONES (6 REGISTROS)
-- =========================
 
call sp_instituciones_create('Instituto Tecnológico Central','itc@gmail.com','Zona 1','22223333');
call sp_instituciones_create('Colegio San José','csj@gmail.com','Zona 2','22224444');
call sp_instituciones_create('Universidad Nacional','un@gmail.com','Zona 3','22225555');
call sp_instituciones_create('Instituto Técnico Industrial','iti@gmail.com','Zona 4','22226666');
call sp_instituciones_create('Colegio Mixto Moderno','cmm@gmail.com','Zona 5','22227777');
call sp_instituciones_create('Universidad del Valle','uv@gmail.com','Zona 6','22228888');
 
 
-- =========================
-- EMPRESAS (6 REGISTROS)
-- =========================
 
call sp_AgregarEmpresa('Tech Solutions','Tecnología','Grande','55511111','tech@gmail.com','Zona 10','8AM-5PM','Empresa de software',2);
call sp_AgregarEmpresa('InnovaSoft','Tecnología','Mediana','55522222','innova@gmail.com','Zona 11','8AM-5PM','Desarrollo web',3);
call sp_AgregarEmpresa('DataCorp','Análisis de Datos','Grande','55533333','data@gmail.com','Zona 12','9AM-6PM','Big Data',2);
call sp_AgregarEmpresa('RedNetworks','Redes','Pequeña','55544444','red@gmail.com','Zona 13','8AM-4PM','Infraestructura de red',3);
call sp_AgregarEmpresa('CyberSecurity GT','Seguridad','Mediana','55555555','cyber@gmail.com','Zona 14','9AM-5PM','Seguridad informática',2);
call sp_AgregarEmpresa('SmartApps','Desarrollo','Pequeña','55566666','apps@gmail.com','Zona 15','8AM-3PM','Apps móviles',3);
 
 
-- =========================
-- ADMINISTRADORES (6 REGISTROS)
-- =========================
 
call sp_AgregarAdministrador('Carlos','Lopez','Activo',1);
call sp_AgregarAdministrador('Ana','Martinez','Activo',1);
call sp_AgregarAdministrador('Luis','Gomez','Inactivo',1);
call sp_AgregarAdministrador('Maria','Perez','Activo',1);
call sp_AgregarAdministrador('Jorge','Ramirez','Activo',1);
call sp_AgregarAdministrador('Sofia','Hernandez','Activo',1);
 
 
-- =========================
-- ESTUDIANTES (6 REGISTROS)
-- =========================
 
call sp_Estudiantes_create(1,4,'Juan','Perez',44441111,'5to Bach','Informática','juan@gmail.com','Instituto Tecnológico Central',33331111,18);
call sp_Estudiantes_create(2,5,'Laura','Diaz',44442222,'6to Bach','Computación','laura@gmail.com','Colegio San José',33332222,19);
call sp_Estudiantes_create(3,4,'Miguel','Lopez',44443333,'5to Bach','Sistemas','miguel@gmail.com','Universidad Nacional',33333333,20);
call sp_Estudiantes_create(4,5,'Andrea','Ruiz',44444444,'6to Bach','Redes','andrea@gmail.com','Instituto Técnico Industrial',33334444,21);
call sp_Estudiantes_create(5,4,'Pedro','Castro',44445555,'5to Bach','Programación','pedro@gmail.com','Colegio Mixto Moderno',33335555,18);
call sp_Estudiantes_create(6,5,'Lucia','Morales',44446666,'6to Bach','Software','lucia@gmail.com','Universidad del Valle',33336666,22);
 
 
-- =========================
-- PRACTICAS (6 REGISTROS)
-- =========================
 
call sp_insertar_practica(1,'Desarrollador Junior','2026-01-01 08:00:00','Presencial','Informática','Vigente','2 plazas','2026-01-01 08:00:00');
call sp_insertar_practica(2,'Analista de Datos','2026-01-02 08:00:00','Híbrida','Sistemas','Vigente','1 plaza','2026-01-02 08:00:00');
call sp_insertar_practica(3,'Soporte Técnico','2026-01-03 08:00:00','Presencial','Redes','Vigente','3 plazas','2026-01-03 08:00:00');
call sp_insertar_practica(4,'Programador Web','2026-01-04 08:00:00','Remota','Computación','Vigente','2 plazas','2026-01-04 08:00:00');
call sp_insertar_practica(5,'Tester QA','2026-01-05 08:00:00','Presencial','Software','Vigente','1 plaza','2026-01-05 08:00:00');
call sp_insertar_practica(6,'Administrador de Redes','2026-01-06 08:00:00','Presencial','Redes','Vigente','2 plazas','2026-01-06 08:00:00');
 
 
-- =========================
-- POSTULACIONES (6 REGISTROS)
-- =========================
 
call sp_insertar_postulacion(1,'Postulación Dev','Interesado en desarrollo','01/01/2026','Pendiente');
call sp_insertar_postulacion(2,'Postulación Data','Experiencia en datos','02/01/2026','Pendiente');
call sp_insertar_postulacion(3,'Postulación Soporte','Conocimientos básicos','03/01/2026','Aceptado');
call sp_insertar_postulacion(4,'Postulación Web','HTML y CSS','04/01/2026','Pendiente');
call sp_insertar_postulacion(5,'Postulación QA','Pruebas manuales','05/01/2026','Rechazado');
call sp_insertar_postulacion(6,'Postulación Redes','Configuración básica','06/01/2026','Pendiente');
 
-- =========================================
-- REPRESENTANTE EMPRESA (6 REGISTROS)
-- =========================================
 
call sp_RepresentanteEmpresa_create(1,'Mario','Lopez','Gerente TI',55510001,'101','Activo','2026-01-01','mario@tech.com');
call sp_RepresentanteEmpresa_create(2,'Andrea','Gomez','Jefe Desarrollo',55510002,'102','Activo','2026-01-02','andrea@innova.com');
call sp_RepresentanteEmpresa_create(3,'Carlos','Ruiz','Analista Senior',55510003,'103','Activo','2026-01-03','carlos@data.com');
call sp_RepresentanteEmpresa_create(4,'Lucia','Martinez','Supervisor Redes',55510004,'104','Activo','2026-01-04','lucia@red.com');
call sp_RepresentanteEmpresa_create(5,'Pedro','Ramirez','Encargado Seguridad',55510005,'105','Activo','2026-01-05','pedro@cyber.com');
call sp_RepresentanteEmpresa_create(6,'Sofia','Hernandez','Lider Proyectos',55510006,'106','Activo','2026-01-06','sofia@apps.com');
 
 
-- =========================================
-- REPRESENTANTE INSTITUCION (6 REGISTROS)
-- =========================================
 
call sp_representantesInstitucion_create('Luis','Morales','22220001','luis@itc.com',1,1);
call sp_representantesInstitucion_create('Ana','Castro','22220002','ana@csj.com',2,2);
call sp_representantesInstitucion_create('Miguel','Perez','22220003','miguel@un.com',3,3);
call sp_representantesInstitucion_create('Laura','Diaz','22220004','laura@iti.com',4,4);
call sp_representantesInstitucion_create('Jorge','Lopez','22220005','jorge@cmm.com',5,5);
call sp_representantesInstitucion_create('Maria','Ramirez','22220006','maria@uv.com',6,6);
 
 
-- =========================================
-- DOCUMENTOS (6 REGISTROS)
-- =========================================
 
call sp_AgregarDocumentos(1,1,'Curriculum Vitae (CV)','cv_juan.pdf','/docs/cv_juan.pdf','2026-01-10 08:00:00');
call sp_AgregarDocumentos(2,2,'Constancia de Estudios','constancia_laura.pdf','/docs/constancia_laura.pdf','2026-01-11 08:00:00');
call sp_AgregarDocumentos(3,3,'Fotocopia de DPI o CUI','dpi_miguel.pdf','/docs/dpi_miguel.pdf','2026-01-12 08:00:00');
call sp_AgregarDocumentos(4,4,'Carta de Solicitud de Práctica','carta_andrea.pdf','/docs/carta_andrea.pdf','2026-01-13 08:00:00');
call sp_AgregarDocumentos(5,5,'Certificación de Notas','notas_pedro.pdf','/docs/notas_pedro.pdf','2026-01-14 08:00:00');
call sp_AgregarDocumentos(6,6,'Pensum de la Carrera','pensum_lucia.pdf','/docs/pensum_lucia.pdf','2026-01-15 08:00:00');
 
 
-- =========================================
-- CONTRATO (6 REGISTROS)
-- =========================================
 
call sp_AgregarContrato(1,1,1,1,'2026-02-01','2026-06-01','Apoyo en desarrollo de software');
call sp_AgregarContrato(2,2,2,2,'2026-02-02','2026-06-02','Análisis de bases de datos');
call sp_AgregarContrato(3,3,3,3,'2026-02-03','2026-06-03','Soporte técnico empresarial');
call sp_AgregarContrato(4,4,4,4,'2026-02-04','2026-06-04','Desarrollo web institucional');
call sp_AgregarContrato(5,5,5,5,'2026-02-05','2026-06-05','Pruebas y control de calidad');
call sp_AgregarContrato(6,6,6,6,'2026-02-06','2026-06-06','Administración de redes');