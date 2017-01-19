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
    Titulo varchar (80),
    constraint pk_Id_conferencia primary key (Id_conferencia)
);

insert into Conferencia values(001,'Ismael Garcia Rangel','Nuevas tecnologias RFID');
insert into Conferencia values(002,'Ismael Garcia Rangel','Aplicacion de las Nuevas Tecnologias RFID');
select * from participante;
 
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
create procedure insertarParticipante(in var_Nombre varchar(40), in var_Email varchar(40), in var_Utl_procedencia  varchar(40),out var_Clave int(8))
begin
insert into Participante (Nombre,Email,Utl_procedencia) values (var_Nombre,var_Email,var_Utl_procedencia);
set var_Clave = last_insert_id();
end $$
Delimiter ;


drop procedure if exists insertarRegistro;
Delimiter $$
create procedure insertarRegistro(in var_Clave1 int(8), in var_Id_conferencia1 int, out var_Id_registro int)
begin
insert into Registro (Clave1,Id_conferencia1,Fecha,Hora) values (var_Clave1,var_Id_conferencia1, date_format(now(),'%Y-%m-%d'),time(now()));
set var_Id_registro = last_insert_id();
end $$
Delimiter ;

select * from Registro;

create view Buscarp as select * from Participante;

drop view if exists PartiConfe;
create view PartiConfe as select R.Clave1, p.Nombre from Registro as R inner join Participante p on  R.Clave1=p.Clave where R.Id_conferencia1=1;
select * from PartiConfe;

drop view if exists PartiConfedos;
create view PartiConfedos as select R.Clave1, p.Nombre from Registro as R inner join Participante p on  R.Clave1=p.Clave where R.Id_conferencia1=2;
select * from PartiConfedos;

drop view if exists PartiConfetres;
create view PartiConfetres as select p.Clave1, p.Nombre from Particonfe p inner join Particonfedos pd on p.Clave1 = pd.Clave1;
select * from PartiConfetres;