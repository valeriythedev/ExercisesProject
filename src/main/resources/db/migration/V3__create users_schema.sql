CREATE TABLE IF NOT EXISTS users (
    id bigint PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY,
    login varchar,
    password varchar
);

