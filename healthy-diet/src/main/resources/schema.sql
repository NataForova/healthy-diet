CREATE TABLE IF NOT EXISTS users (
    id  SERIAL  PRIMARY KEY,
    email VARCHAR(255),
    username VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    password VARCHAR(255),
    is_subscribed BOOLEAN
);
CREATE TABLE IF NOT EXISTS tag (
    id int,
    name VARCHAR(255),
    color VARCHAR(255),
    slug VARCHAR(255)
);
CREATE SEQUENCE IF NOT EXISTS "ID_SEQUENCE"
    MINVALUE 1
    MAXVALUE 999999999
    INCREMENT BY 1
    START WITH 6;