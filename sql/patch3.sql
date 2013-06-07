CREATE TYPE typ_konta AS ENUM ('uzytkownik', 'wlasciciel', 'admin'); 
ALTER TABLE uzytkownik ADD COLUMN typ_konta typ_konta DEFAULT 'uzytkownik';
UPDATE uzytkownik SET typ_konta='uzytkownik';
ALTER TABLE uzytkownik ALTER COLUMN typ_konta SET NOT NULL;