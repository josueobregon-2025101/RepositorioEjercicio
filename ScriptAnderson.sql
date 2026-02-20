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
    select last_insert_id() as id_practica;
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
 
    select last_insert_id() as id_postulacion;
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