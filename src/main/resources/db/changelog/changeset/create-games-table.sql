--liquibase formatted sql

--changeset shnirelman:create-words-table

CREATE TABLE IF NOT EXISTS games (
    id bigint NOT NULL,
    user_id bigint,
    is_active boolean,
    word_id bigint,
    CONSTRAINT games_pkey PRIMARY KEY (id),
    CONSTRAINT user_fkey FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT word_fkey FOREIGN KEY (word_id) REFERENCES words(id)
);

ALTER TABLE IF EXISTS public.games
    OWNER to admin;

CREATE SEQUENCE IF NOT EXISTS games_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.games_seq
    OWNER TO admin;
