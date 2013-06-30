ALTER TABLE pizzeria ALTER COLUMN adres DROP NOT NULL;
UPDATE pizzeria SET adres = NULL;
ALTER TABLE pizzeria ALTER COLUMN adres TYPE varchar;
UPDATE pizzeria SET adres = 'Abackiej 1' WHERE pizzeria_id=1;
UPDATE pizzeria SET adres = 'Babackiego 2/2' WHERE pizzeria_id=2;
UPDATE pizzeria SET adres = 'Powstańców Cabackich 3C' WHERE pizzeria_id=3;
ALTER TABLE pizzeria ALTER COLUMN adres SET NOT NULL;

grant SELECT ON ocenialneView to wlascicielRole;