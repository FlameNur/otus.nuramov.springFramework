INSERT INTO AUTHOR (firstname, lastname) VALUES ('firstname1', 'lastname1');
INSERT INTO GENRE (name) VALUES ('genreName1');
INSERT INTO BOOKS (title, AUTHOR_lastname, GENRE_name)
VALUES ('bookName1', (SELECT lastname FROM AUTHOR WHERE lastname = 'lastname1'), (SELECT name FROM GENRE WHERE name = 'genreName1'));

/*Добавили 2-ю книгу*/
INSERT INTO AUTHOR (firstname, lastname) VALUES ('firstname2', 'lastname2');
INSERT INTO GENRE (name) VALUES ('genreName2');
INSERT INTO BOOKS (title, AUTHOR_lastname, GENRE_name)
VALUES ('bookName2', (SELECT lastname FROM AUTHOR WHERE lastname = 'lastname2'), (SELECT name FROM GENRE WHERE name = 'genreName2'));
