DROP TABLE IF EXISTS films;

CREATE TABLE films (
    id INT IDENTITY NOT NULL PRIMARY KEY,
    titre VARCHAR(250) NOT NULL,
    description VARCHAR(250) NOT NULL
);


DROP TABLE IF EXISTS acteurs;

CREATE TABLE acteurs (
    id INT IDENTITY PRIMARY KEY,
    nom VARCHAR(250) NOT NULL,
    prenom VARCHAR(250) NOT NULL
);


DROP TABLE IF EXISTS films_acteurs;

CREATE TABLE films_acteurs (
    film_id INT,
    acteur_id INT,
    FOREIGN KEY (film_id) REFERENCES films(id),
    FOREIGN KEY (acteur_id) REFERENCES acteurs(id)
);



INSERT INTO films (titre, description) VALUES
                                           ('The Shawshank Redemption', 'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.'),
                                           ('Cast Away', 'A FedEx executive undergoes a physical and emotional transformation after crash landing on a deserted island.');

INSERT INTO acteurs (nom, prenom) VALUES
                                      ('Freeman', 'Morgan'),
                                      ('Robbins', 'Tim'),
                                      ('Hanks', 'Tom');

INSERT INTO films_acteurs (film_id, acteur_id) VALUES
    ((SELECT id FROM films WHERE titre = 'The Shawshank Redemption'), (SELECT id FROM acteurs WHERE nom = 'Robbins')),
    ((SELECT id FROM films WHERE titre = 'The Shawshank Redemption'), (SELECT id FROM acteurs WHERE nom = 'Freeman')),
    ((SELECT id FROM films WHERE titre = 'Cast Away'), (SELECT id FROM acteurs WHERE nom = 'Hanks'));