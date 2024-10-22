--liquibase formatted sql

--changeset shnirelman:insert-users-anonymous

TRUNCATE users;

INSERT INTO users(id, login, password) VALUES
(0, 'anonymous', 'abacaba');
