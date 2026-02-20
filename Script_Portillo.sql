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
	select last_insert_id() as id_representantempresa; 
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
	select last_insert_id() as id_estudiante;
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