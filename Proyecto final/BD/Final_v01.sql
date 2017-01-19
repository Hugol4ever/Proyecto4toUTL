create database Final;
use Final;

create table Huella
(
	Id_Huella int auto_increment,
    Huella blob,
    constraint pk_Id_Huella primary key(Id_Huella)
);

create table Usuario
(
	Id_Usuario int auto_increment,
    Nom_Usuario varchar(35),
    Contraseña varchar(20),
    Id_Huella1 int,
    constraint pk_Id_Usuario primary key(Id_Usuario),
    constraint fk_Id_Huella1 foreign key(Id_Huella1) references Huella(Id_Huella)
);

create table Cliente
(
	Id_Cliente int auto_increment,
    Nombre varchar(35),
    Correo varchar(35),
    Telefono varchar(10),
    Genero varchar(10),
    N_Tarjeta varchar(25),
    Id_Usuario1 int,
    constraint pk_Id_Cliente primary key(Id_Cliente),
    constraint fk_Id_Usuario1 foreign key(Id_Usuario1) references Usuario(Id_Usuario)
);

create table Venta
(
	Id_Venta int auto_increment,
    Fecha date,
    Hora time,
    Id_Cliente1 int,
    constraint pk_Id_Venta primary key(Id_Venta),
    constraint fk_Id_Cliente1 foreign key(Id_Cliente1) references Cliente(Id_Cliente)
);

create table Producto
(
	Id_Producto int,
    Nombre varchar(35),
    Marca varchar(20),
    Categoria varchar(20),
    Existencia int,
	Precio double(10,2),
    Foto varchar(30),
    constraint pk_Id_Producto primary key(Id_Producto)
);
insert into Producto values(124, 'Arroz', 'La fina', 'Granos', 10, 12, '');


create table Detalle_Venta
(
	Id_DetalleVenta int auto_increment,
    Cantidad int,
    Precio double(10,2),
    Id_Producto1 int,
    Id_Venta1 int,
    constraint pk_Id_DetalleVenta primary key(Id_DetalleVenta),
    constraint fk_Id_Producto1 foreign key(Id_Producto1) references Producto(Id_Producto),
    constraint fk_Id_Venta1 foreign key(Id_Venta1) references Venta(Id_Venta)
);


-- Registrar Cliente --------------------------------------------------------------------------------------------------------------------------------------------------------------
Delimiter $$
create procedure regCliente (out var_Id_Huella int, in var_Huella blob, out var_Id_Usuario int, in var_Nom_Usuario varchar(35),
							in var_Contraseña varchar(20), out var_Id_Huella1 int, out var_Id_Cliente int, in var_Nombre varchar(35),
                            in var_Correo varchar(35), in var_Telefono varchar(10), in var_Genero varchar(10), in var_N_Tarjeta varchar(25),
							out var_Id_Usuario1 int)                                      
begin
insert into Huella (Huella) values (var_Huella);
set var_Id_Huella = last_insert_id();

insert into Usuario (Nom_Usuario,Contraseña,Id_Huella1) values (var_Nom_Usuario,var_Contraseña,var_Id_Huella1);
set var_Id_Usuario = last_insert_id();

insert into Cliente (Nombre,Correo,Telefono,Genero,N_Tarjeta,Id_Usuario1) values (var_Nombre,var_Correo,var_Telefono,var_Genero,var_N_Tarjeta,var_Id_Usuario1);
set var_Id_Cliente = last_insert_id();
end $$
Delimiter ;
-- --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------





-- Registrar Venta ----------------------------------------------------------------------------------------------------------------------------------------------------------------
Delimiter $$
create procedure regVenta (out var_Id_Producto int,in var_Nombre varchar(35), in var_Marca varchar(20),in var_Categoria varchar(20),in var_Existencia int,in Precio double(10,2),
							in var_Foto varchar(30), out var_Id_Venta int,in var_Id_Cliente1 int,out var_Id_DetalleVenta int, in var_Cantidad int,in var_Precio double(10,2),
							out var_Id_Producto1 int, out var_Id_Venta1 int)                                      
begin
insert into Producto (Nombre,Marca,Categoria,Existencia,Precio,Foto) values (var_Nombre,var_Marca,var_Categoria,var_Existencia,var_Precio,var_Foto);
set var_Id_Producto = last_insert_id();

insert into Venta (Fecha,Hora,Id_Cliente1) values (date_format(now(),'%Y-%m-%d'),time(now()),var_Id_Cliente1);
set var_Id_Venta = last_insert_id();

insert into Detalle_Venta (Cantidad,Precio,Id_Producto1,Id_Venta1) values (var_Cantidad,var_Precio,var_Id_Producto1,var_Id_Venta1);
end $$
Delimiter ;
-- --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------









create view vistProductos as select * from Producto;
select * from vistProductos;



create view vistCliente as select H.Id_Huella, H.Huella, U.Id_Usuario, U.Nom_Usuario, U.Contraseña, U.Id_Huella1, C.id_Cliente,C.Nombre,C.Correo,C.Telefono,C.Genero,C.N_Tarjeta,C.Id_Usuario1 from Huella H inner join Usuario U on H.Id_Huella=U.Id_Huella1 inner join Cliente C on C.Id_Usuario1 = U.Id_Usuario;
select * from vistCliente;

