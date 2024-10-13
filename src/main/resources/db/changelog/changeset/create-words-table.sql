--liquibase formatted sql

--changeset shnirelman:create-words-table

CREATE TABLE IF NOT EXISTS words (
    id bigint NOT NULL,
    text character(5),
    CONSTRAINT words_pkey PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.words
    OWNER to admin;

CREATE SEQUENCE IF NOT EXISTS words_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.words_seq
    OWNER TO admin;
