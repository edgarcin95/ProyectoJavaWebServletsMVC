SELECT @@global.time_zone;
SET GLOBAL time_zone = '-3:00';
show databases;
create database proyectoprogramacionavanzada;
use proyectoprogramacionavanzada;
create table grafos(nombre char(20) primary key,numvertices int unsigned not null,numaristas int unsigned not null,
aristas char(100));
insert into grafos values ('g',4,6,'0,1/0,2/1,2/2,0/2,3/3,3');
insert into grafos values ('grafoEdgar',5,7,'0,1/0,3/1,2/1,3/1,4/2,4/3,4');
insert into grafos values ('grafoPrueba',6,8,'0,1/0,5/1,2/1,5/1,4/2,0/2,3/3,4');
insert into grafos values ('hexagono',6,6,'0,1/1,2/2,3/3,4/4,5/5,0');
insert into grafos values ('estrella',6,5,'0,1/0,2/0,3/0,4/0,5');
insert into grafos values ('triangulo',3,3,'0,1/1,2/2,0');
insert into grafos values ('gra',8,10,'0,1/1,2/2,3/0,4/1,5/2,6/3,7/4,5/5,6/6,7');
insert into grafos values ('g2',5,6,'0,1/1,2/2,3/1,3/1,4/0,4');
-- delete from grafos where nombre = '';
select * from grafos;
-- SELECT * FROM grafos WHERE nombre = 'grafoEdgar';
