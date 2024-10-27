--liquibase formatted sql

--changeset shnirelman:create-words-table

CREATE TABLE IF NOT EXISTS users (
    id bigint,
    login varchar(32) UNIQUE,
    password varchar(256),
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.users
    OWNER to admin;

CREATE SEQUENCE IF NOT EXISTS users_seq
    INCREMENT 50
    START 2
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.users_seq
    OWNER TO admin;