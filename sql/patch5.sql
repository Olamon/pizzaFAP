CREATE ROLE loginRole WITH LOGIN PASSWORD 'loginrole';
ALTER ROLE uzytkownikRole PASSWORD 'uzytkownikrole';
ALTER ROLE wlascicielRole PASSWORD 'wlascicielrole';
GRANT SELECT, INSERT ON uzytkownik TO loginRole;