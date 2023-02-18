/*Добавили 1-ю книгу*/
INSERT INTO AUTHOR (name) VALUES ('authorName1');
INSERT INTO AUTHOR (name) VALUES ('authorName2');
INSERT INTO GENRE (name) VALUES ('genreName1');
INSERT INTO BOOKS (title, GENRE_name) VALUES ('bookName1', (SELECT name FROM GENRE WHERE name = 'genreName1'));

/*Добавили 2-ю книгу*/
INSERT INTO AUTHOR (name) VALUES ('authorName3');
INSERT INTO AUTHOR (name) VALUES ('authorName4');
INSERT INTO GENRE (name) VALUES ('genreName2');
INSERT INTO BOOKS (title, GENRE_name) VALUES ('bookName2', (SELECT name FROM GENRE WHERE name = 'genreName2'));

/*Добавили 2-ю книгу*/
INSERT INTO AUTHOR (name) VALUES ('authorName1');
INSERT INTO AUTHOR (name) VALUES ('authorName4');
INSERT INTO GENRE (name) VALUES ('genreName2');
INSERT INTO BOOKS (title, GENRE_name) VALUES ('bookName3', (SELECT name FROM GENRE WHERE name = 'genreName2'));