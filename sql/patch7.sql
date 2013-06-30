ALTER TABLE pizzeria ALTER COLUMN telefon TYPE varchar;
GRANT INSERT ON ocenialne TO wlascicielRole;
GRANT SELECT ON ocenialne TO wlascicielRole;
GRANT SELECT, UPDATE ON ocenialne_id_seq TO wlascicielRole;
SELECT setval('ocenialne_id_seq', (SELECT max(id) FROM ocenialne));
UPDATE pizzeria SET strona='' WHERE strona IS NULL;
UPDATE pizzeria SET telefon='' WHERE telefon IS NULL;