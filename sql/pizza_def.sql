-- drop database if exists pizzadb;
create database pizzadb; -- rozbudowac jak zachodzi potrzeba
\connect pizzadb;

create domain numerDniaDomain int NOT NULL CHECK (VALUE >= 0 AND VALUE <=6);
create domain varcharNN varchar NOT NULL;
create domain intNN int NOT NULL;
create domain timeNN time NOT NULL;

create type adresType AS (
  ulica varcharNN,
  nr_budynku intNN,
  nr_lokalu intNN);
create type dzienZGodz AS (
  nr_dnia numerDniaDomain, 
  otwarcie timeNN,
  zamkniecie timeNN);
create type grubosc AS ENUM ('chrupkie', 'klasyczne', 'grube'); 

create table ocenialne (
  id serial primary key
);

create table uzytkownik (
  email varchar primary key,
  pass varchar NOT NULL
);

create table pizzeria (
  pizzeria_id int primary key references ocenialne(id),
  nazwa varchar NOT NULL,
  adres adresType NOT NULL,
  strona varchar,
  telefon varchar[],
  godziny dzienZGodz[]
);

create table pizza (
  sklad int primary key, --do przemyślenia
  ciasto grubosc DEFAULT 'klasyczne'
);

create table oferta (
  of_id int primary key references ocenialne(id),
  sklad int NOT NULL references pizza ON DELETE cascade,
  pizzeria_id int NOT NULL references pizzeria ON DELETE cascade,
  cena real NOT NULL,
  rozmiar int NOT NULL
);

create table ocena (
  oc_id serial primary key,
  podmiot int NOT NULL references ocenialne(id) ON DELETE cascade,
  email varchar references uzytkownik ON DELETE SET NULL ON UPDATE cascade,
  recenzja text,
  gwiazdki int CHECK (gwiazdki in (0,1,2,3,4,5)) NOT NULL
  -- punkty_oceny - realizacja jak znajdzie się czas i logiczny pomysł :-)
);

create table menu (
  pizzeria_id int references pizzeria,
  pizza int references pizza(sklad),
  nazwa varchar NOT NULL,
  primary key(pizzeria_id, pizza)
);

create view ocenialneView AS SELECT id, avg(gwiazdki) AS "srednia", count(gwiazdki) AS "ilosc" FROM ocenialne LEFT JOIN ocena ON podmiot=id GROUP BY id; 
create view liczbaOcenView AS SELECT email, count(DISTINCT oc_id) AS "liczba_ocen" FROM uzytkownik LEFT JOIN ocena USING(email) GROUP BY email;

create role uzytkownikRole WITH LOGIN PASSWORD 'uzytkownikRole';
create role wlascicielRole WITH LOGIN PASSWORD 'wlascicielRole';

grant SELECT ON ALL TABLES IN schema public TO uzytkownikRole;
grant SELECT ON ocenialneView, liczbaOcenView TO uzytkownikRole;
grant INSERT ON ocena, uzytkownik TO uzytkownikRole;
grant UPDATE ON uzytkownik, ocena TO uzytkownikRole;
grant DELETE ON ocena TO uzytkownikRole;

grant SELECT, INSERT, DELETE, UPDATE ON oferta, pizzeria, pizza, menu TO wlascicielRole;
grant SELECT ON ocenialneView TO wlascicielRole;
