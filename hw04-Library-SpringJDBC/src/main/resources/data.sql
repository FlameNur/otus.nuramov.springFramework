INSERT INTO AUTHOR (id, firstname, lastname) VALUES (DEFAULT, 'sfs', 'AA');
INSERT INTO GENRE (id, name) VALUES (DEFAULT, 'DD');
INSERT INTO BOOKS (id, title, AUTHOR_lastname, GENRE_name)
VALUES (DEFAULT, 'fssfs', (SELECT lastname FROM AUTHOR WHERE lastname = 'AA'), (SELECT name FROM GENRE WHERE name = 'DD'))