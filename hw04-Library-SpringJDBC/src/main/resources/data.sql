/*Добавили 1-ю книгу*/
INSERT INTO AUTHOR (name) VALUES ('authorName1');
INSERT INTO GENRE (name) VALUES ('genreName1');
INSERT INTO BOOKS (title, GENRE_ID, AUTHOR_ID) VALUES
    ('bookName1', (SELECT ID FROM GENRE WHERE name = 'genreName1'), (SELECT ID FROM AUTHOR WHERE name = 'authorName1'));

/*Добавили 2-ю книгу*/
INSERT INTO GENRE (name) VALUES ('genreName2');
INSERT INTO BOOKS (title, GENRE_ID, AUTHOR_ID) VALUES
    ('bookName2', (SELECT ID FROM GENRE WHERE name = 'genreName2'), (SELECT ID FROM AUTHOR WHERE name = 'authorName1'));

/*Добавили 2-ю книгу*/
INSERT INTO AUTHOR (name) VALUES ('authorName2');
INSERT INTO BOOKS (title, GENRE_ID, AUTHOR_ID) VALUES
    ('bookName3', (SELECT ID FROM GENRE WHERE name = 'genreName2'), (SELECT ID FROM AUTHOR WHERE name = 'authorName2'));