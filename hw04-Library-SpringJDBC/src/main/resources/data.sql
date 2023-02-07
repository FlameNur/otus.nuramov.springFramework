INSERT INTO AUTHOR (firstname, lastname) VALUES ('sfs', 'AA');
INSERT INTO GENRE (name) VALUES ('DD');
INSERT INTO BOOKS (title, AUTHOR_lastname, GENRE_name)
VALUES ('fssfs', (SELECT lastname FROM AUTHOR WHERE lastname = 'AA'), (SELECT name FROM GENRE WHERE name = 'DD'))
