CREATE TABLE if not exists exercises (
    id bigint PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY,
    race_distance double precision,
    race_duration double precision,
    race_date timestamp without time zone,
    user_id bigint
);

