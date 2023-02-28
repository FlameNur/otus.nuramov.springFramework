/*Для корректной работы надо создать DDL*/
/*Добавили 1-ю книгу*/
INSERT INTO AUTHOR (id, name) VALUES (default, 'authorName1');
INSERT INTO GENRE (id, name) VALUES (default, 'genreName1');
INSERT INTO BOOKS (id, title, GENRE_ID, AUTHOR_ID) VALUES
    (default, 'bookName1', (SELECT ID FROM GENRE WHERE name = 'genreName1'), (SELECT ID FROM AUTHOR WHERE name = 'authorName1'));

/*Добавили 2-ю книгу*/
INSERT INTO GENRE (id, name) VALUES (default, 'genreName2');
INSERT INTO BOOKS (id, title, GENRE_ID, AUTHOR_ID) VALUES
    (default, 'bookName2', (SELECT ID FROM GENRE WHERE name = 'genreName2'), (SELECT ID FROM AUTHOR WHERE name = 'authorName1'));

/*Добавили 2-ю книгу*/
INSERT INTO AUTHOR (id, name) VALUES (default, 'authorName2');
INSERT INTO BOOKS (id, title, GENRE_ID, AUTHOR_ID) VALUES
    (default, 'bookName3', (SELECT ID FROM GENRE WHERE name = 'genreName2'), (SELECT ID FROM AUTHOR WHERE name = 'authorName2'));