--liquibase formatted sql

--changeset shnirelman:insert-words-1

TRUNCATE words;

INSERT INTO words(id, text) VALUES
(1, 'table'),
(2, 'chair'),
(3, 'cloud'),
(4, 'dream'),
(5, 'phone');