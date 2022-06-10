CREATE TABLE IF NOT EXISTS roles (
    id bigint PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY,
    role_name varchar
);

INSERT INTO roles (role_name) VALUES ('ROLE_USER');