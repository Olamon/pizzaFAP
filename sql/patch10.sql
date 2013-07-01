INSERT INTO uzytkownik(email, pass, typ_konta) VALUES ('temp148736', 'temp148736', 'wlasciciel');
ALTER TABLE pizzeria ADD COLUMN wlasciciel varchar default 'temp148736';
ALTER TABLE pizzeria ALTER COLUMN wlasciciel SET NOT NULL;
ALTER TABLE pizzeria ADD CONSTRAINT pizz_wla_fk FOREIGN KEY (wlasciciel) REFERENCES uzytkownik(email);
