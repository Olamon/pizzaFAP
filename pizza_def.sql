create database pizzaDB; -- rozbudowac jak zachodzi potrzeba

create type adresType AS (ulica varchar NOT NULL, nr_budynku int NOT NULL, nr_lokalu int);
create type dzienZGodz AS (nr_dnia int NOT NULL CHECK nr_dnia >=0 AND nr_dnia <=6, od time NOT NULL, do time NOT NULL);
create type grubosc AS ENUM ('chrupkie', 'klasyczne', 'grube'); 

create table ocenialne (
	id serial primary_key
);

create table uzytkownik (
	e-mail varchar primary key,
	pass varchar NOT NULL
);

create table pizzeria (
	pizzeria_id primary key references ocenialne(id),
	nazwa varchar NOT NULL,
	adres adresType NOT NULL,
	strona varchar,
	telefon varchar[],
	godziny dzienZGodz[]
);

create table pizza (
	sklad int primary key, --do przemyślenia
	ciasto grubosc DEFAULT 'klasyczne';
);

create table oferta (
	of_id primary key references ocenialne(id),
	sklad references pizza NOT NULL ON DELETE cascade,
	pizzeria_id references pizzeria NOT NULL ON DELETE cascade,
	cena real NOT NULL,
	rozmiar int NOT NULL
);

create table ocena (
	oc_id serial primary key,
	podmiot references ocenialne(id) NOT NULL ON DELETE cascade,
	e-mail references uzytkownik ON DELETE SET NULL ON UPDATE cascade,
	recenzja text,
	gwiazdki int CHECK (gwiazdki in (0,1,2,3,4,5)) NOT NULL
	-- punkty_oceny - realizacja jak znajdzie się czas i logiczny pomysł :-)
);

create table menu (
	pizzeria_id references pizzeria,
	pizza references pizza(sklad),
	nazwa varchar NOT NULL,
	primary key(pizzeria_id, pizza)
);

create view ocenialneView AS SELECT id, avg(gwiazdki) AS "srednia" FROM ocenialne LEFT JOIN ocena ON podmiot=id GROUP BY id; 
create view liczbaOcenView AS SELECT e-mail, count(DISTINCT oc_id) AS "liczba_ocen" FROM uzytkownik LEFT JOIN ocena USING(e-mail) GROUP BY e-mail;

create role uzytkownikRole;
create role wlascicielRole;

grant SELECT ON ALL TABLES IN schema public TO uzytkownikRole;
grant SELECT ON ocenialneView, liczbaOcen TO uzytkownikRole
grant INSERT ON ocena, uzytkownik TO uzytkownikRole;
grant UPDATE ON uzytkownik, ocena TO uzytkownikRole;
grant DELETE ON ocena TO uzytkownikRole;

grant SELECT, INSERT, DELETE, UPDATE ON oferta, pizzeria, pizza, menu TO wlascicielRole;
grant SELECT ON ocenialneView TO wlascicielRole;
