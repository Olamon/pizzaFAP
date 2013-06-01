-- kilka wpisów
-- jak dodawać wpisy do pizzerii tak by automatycznie tworzyły się id w ocenialnych?

INSERT INTO ocenialne(id) VALUES (1), (2), (3);
INSERT INTO pizzeria(pizzeria_id, nazwa, adres) 
  VALUES (1, 'Pizza FAP', ROW('Abackiej', 1, 1)),
         (2, 'Pizza Hut', ROW('Babackiego', 2, 2)),
         (3, 'Pizzeria Plastyczna', ROW('Powstańców Cabackich', 3, 3));