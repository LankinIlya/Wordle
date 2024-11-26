--liquibase formatted sql

--changeset shnirelman:insert-users-anonymous

TRUNCATE users;

INSERT INTO users(id, login, password, games, wins) VALUES
(1, 'anonymous', 'abacaba', 0, 0);
