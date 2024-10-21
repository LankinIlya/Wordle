--liquibase formatted sql

--changeset shnirelman:create-words-table

CREATE TABLE IF NOT EXISTS trywords (
    id bigint NOT NULL,
    game_id bigint,
    word_id bigint,
    try_number bigint,
    CONSTRAINT trywords_pkey PRIMARY KEY (id),
    CONSTRAINT game_fkey FOREIGN KEY (game_id) REFERENCES games(id),
    CONSTRAINT word_fkey FOREIGN KEY (word_id) REFERENCES words(id)

);

ALTER TABLE IF EXISTS public.trywords
    OWNER to admin;

CREATE SEQUENCE IF NOT EXISTS trywords_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.trywords_seq
    OWNER TO admin;
