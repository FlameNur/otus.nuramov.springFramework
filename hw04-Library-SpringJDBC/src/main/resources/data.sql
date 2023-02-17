/*Добавили 1-ю книгу*/
INSERT INTO AUTHOR (firstname, lastname) VALUES ('firstname1', 'lastname1');
INSERT INTO AUTHOR (firstname, lastname) VALUES ('firstname2', 'lastname2');
INSERT INTO GENRE (name) VALUES ('genreName1');
INSERT INTO BOOKS (title, GENRE_name) VALUES ('bookName1', (SELECT name FROM GENRE WHERE name = 'genreName1'));

INSERT INTO LIBRARY (BOOK_title, AUTHOR_lastname)
VALUES ((SELECT title FROM BOOKS WHERE title = 'bookName1'), (SELECT lastname FROM AUTHOR WHERE lastname = 'lastname1'));
INSERT INTO LIBRARY (BOOK_title, AUTHOR_lastname)
VALUES ((SELECT title FROM BOOKS WHERE title = 'bookName1'), (SELECT lastname FROM AUTHOR WHERE lastname = 'lastname2'));

/*Добавили 2-ю книгу*/