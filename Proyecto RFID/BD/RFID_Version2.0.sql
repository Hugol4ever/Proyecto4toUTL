drop database if exists RFID;
create database RFID;
use RFID;

create table Participante
(
	Clave int(8)  auto_increment,
    Nombre varchar(40),
    Email varchar(40),
    Utl_procedencia varchar(40),
    constraint pk_Clave primary key (Clave)
)auto_increment = 15001500;

create table Conferencia
(
	Id_conferencia int auto_increment,
    Conferencesita varchar(40),
    Titulo varchar (40),
    constraint pk_Id_conferencia primary key (Id_conferencia)
);

insert into Conferencia values(001,'Jose','Pito');

create table Registro
(
	Id_registro int auto_increment,
    Fecha date,
    Hora time,
    Clave1 int(8),
    Id_conferencia1 int,
    constraint pk_Id_registro primary key (Id_registro),
    constraint fk_Clave1 foreign key (Clave1) references Participante(Clave),
    constraint fk_Id_conferencia1 foreign key (Id_conferencia1) references Conferencia(Id_conferencia)
);


drop procedure if exists insertarParticipante;
Delimiter $$
create procedure insertarParticipante(in var_Nombre varchar(40), in var_Email varchar(40), in Utl_procedencia  varchar(40),out Clave int(8))
begin
insert into Registro (Nombre,Email,Utl_procedencia) values (var_Nombre,var_Email,var_Utl_procedencia);
end $$
Delimiter ;



drop procedure if exists insertarRegistro;
Delimiter $$
create procedure insertarRegistro(in var_Clave1 int(8), in var_Id_conferencia1 int, out var_Id_registro int)
begin
insert into Registro (Clave1,Id_conferencia1,Fecha,Hora) values (var_Clave1,var_Id_conferencia1, date_format(now(),'%Y-%m-%d'),time(now()));
end $$
Delimiter ;

call insertarRegistro(15001518,001,@id);