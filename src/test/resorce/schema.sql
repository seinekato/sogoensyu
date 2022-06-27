drop table if exists forresister cascade;

create table forresister(
id serial primary key not null
, mail character varying(50) not null
, key character varying(100) not null
, resister_date timestamp not null
, doneflag boolean not null);


drop table if exists users cascade;

create table users(
id serial primary key not null
, name character varying(20) not null
, hurigana character varying(20) not null
, zip_code character varying(20) not null
, address character varying(50) not null
, tel character varying(20) not null
, password character varying(100) not null
, mail character varying(50) not null
, resister_user integer
, resister_date timestamp not null
, update_user integer
, update_date timestamp not null
, deleteflag boolean not null);