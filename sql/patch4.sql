drop view ocenialneView;
create view ocenialneView AS SELECT id, avg(gwiazdki) AS "srednia", count(gwiazdki) AS "ilosc" FROM ocenialne LEFT JOIN ocena ON podmiot=id GROUP BY id; 

insert into ocenialne values (4), (5);
insert into pizza values (1, 'klasyczne'), (10, 'chrupkie');
insert into oferta values (4, 1, 2, 12, 5), (5, 10, 3, 20, 7);
insert into ocena values (1, 2, 'user', 'blablabla', 4);
insert into menu values (1, 1, 'Jelenitta'), (2, 10, 'Habababa');

grant select on ocenialneView to uzytkownikRole;