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

    select last_insert_id() as id_institucion;

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

	select last_insert_id() as id_representante_institucion;

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

 
-- ------INSTITUCIONES--------
 
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
    select last_insert_id() as id_institucion;
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
 
 
-- --------REPRESENTANTE DE LA INSTITUCION----------------
 
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
	select last_insert_id() as id_representante_institucion;
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
    select * from RepresentanteInstitucion 
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

 