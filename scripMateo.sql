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
	select last_insert_id() as id_empresa;
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