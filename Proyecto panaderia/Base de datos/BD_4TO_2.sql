drop database if exists Panaderia;

create database Panaderia; 
use Panaderia;


create table Pan
(
	CodPan int,
    Id int auto_increment ,
    Descripcion varchar (20),
    PrecioP double(4,2),
    PrecioV double(4,2),
    constraint pk_Id primary key (Id)
);


create table Inventario 
(
	IdInven int auto_increment,
	Existencia int default'0',
	Fecha date,
	Id1 int,
	constraint pk_IdInven primary key (IdInven),
	constraint fk_Id1 foreign key (Id1) references Pan (Id)
);


create table Venta
(
	Folio int auto_increment,
    Fecha date,
    Total double(7,2),
    constraint pk_Folio primary key (Folio) 
);


create table Detalle
(
	IdDetalle int auto_increment,
    Cantidad int,
    Importe double(7,2),
    Id2 int,
    Folio1 int,
    constraint pk_IdDetalle primary key (IdDetalle),
    constraint fk_Id2 foreign key (Id2)references Pan (Id),
    constraint fk_Folio1 foreign key (Folio1) references Venta (Folio)
);


select * from Pan;
select * from Inventario;





-- Calcular codigo pan ------------------------------------------------------------------------------------------------------------------------------------- 
drop trigger if exists calcod;

Delimiter $$
create trigger calcod before insert on Pan
for each row
begin
Declare Cod int;
DECLARE numero varchar(3);

select AUTO_INCREMENT from 
	information_schema.TABLES 
    where TABLE_SCHEMA='Panaderia' 
    and TABLE_NAME='Pan' into Cod;    

SET numero = (LPAD(Cod, 3, '0'));
SET NEW.CodPan = CONCAT('40105', numero);

end $$
Delimiter ;
-- ---------------------------------------------------------------------------------------------------------------------------------------------------------





-- Insertar pan nuevo --------------------------------------------------------------------------------------------------------------------------------------
drop procedure if exists insertarPan;

Delimiter $$
CREATE  PROCEDURE insertarPan (in var_Descripcion varchar(20),in var_PrecioP double(4,2), in var_PrecioV double(4,2), out var_CodPan int)
begin
 insert into Pan (Descripcion,PrecioP,PrecioV) values (var_Descripcion,var_PrecioP,var_PrecioV);
set var_CodPan = last_insert_id();
insert into Inventario (Fecha,Id1) values (CURDATE(), var_CodPan);

end $$
Delimiter ;

call insertarPan('Bolillo',1.5,3.5,@id);
call insertarPan('Dona',1.5,3.5,@id);
call insertarPan('Panque',10.00,12.00,@id);

-- ----------------------------------------------------------------------------------------------------------------------------------------------------------





-- Insertar pan existente --------------------------------------------------------------------------------------------------------------------------------------------
drop procedure if exists panExistente;

Delimiter $$
create procedure panExistente (in var_CodPan int,in var_Existencia int)
begin
Declare var_claves int;
Declare var_variable int;
select Id from Pan where CodPan = var_CodPan into var_claves;
Select Existencia from Inventario where Id1 = var_claves into var_variable;
Update Inventario set Existencia = var_Existencia + var_variable where Id1 = var_claves;
end $$
Delimiter ;

call panExistente (40105001,100);
select * from Inventario;
-- -------------------------------------------------------------------------------------------------------------------------------------------------------------------





-- Insertar Venta ---------------------------------------------------------------------------------------------------------------------------------------------------
drop procedure if exists venta;

Delimiter $$
create procedure venta (in var_Total double(7,2), out var_id_Venta int)
begin

insert into Venta (Fecha,Total) values (curdate(),var_Total);
set var_id_Venta = last_insert_id();

end $$
Delimiter ;

call venta(10, @id_venta);
select * from Venta;
-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------





-- Insertar detalle --------------------------------------------------------------------------------------------------------------------------------------
drop procedure if exists detalle;

Delimiter $$
create procedure detalle (in var_Cantidad int, in var_Importe double(7,2),in var_CodPan int,in var_Folio1 int)
begin
Declare var_id_pan int;
Declare var_variable int;

select Id from Pan where CodPan = var_CodPan into var_id_pan;

insert into Detalle (Cantidad,Importe,Id2,Folio1) values (var_Cantidad,var_Importe,var_id_pan,var_Folio1);

select Existencia from Inventario where Id1 = var_id_pan into var_variable;

Update Inventario set Existencia = (var_variable - var_Cantidad) where Id1 = var_id_pan;
end $$
Delimiter ;

call detalle (20,1.5,40105001,@id_venta);
-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------





-- vista panes ------------------------------------------------------------------------------------------------------------------------------------------------------
drop view if exists Panes;

create view panes as (select CodPan,Descripcion,PrecioV,PrecioP from Pan);
select * from panes;
-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Vista Grafica --
drop view if exists grafPanes;

create view grafPanes as  (select p.Descripcion, i.Existencia from Pan as p inner join Inventario as i on Id = Id1);
SELECT * FROM grafPanes;




-- vista tabla panes Grafica ----------------------------------------------------------------------------------------------------------------------------------------
drop view if exists tablaPanes;

create view tablaPanes as  (select p.CodPan, p.Descripcion, p.PrecioV, p.PrecioP, i.Existencia from Pan as p inner join Inventario as i on Id = Id1);
select * from tablaPanes;
-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------






