CREATE TABLE IF NOT EXISTS users (
    id  SERIAL  PRIMARY KEY,
    email VARCHAR(255) UNIQUE,
    user_name VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    password VARCHAR(255),
    salt VARCHAR(255),
    is_subscribed BOOLEAN
);
CREATE TABLE IF NOT EXISTS tag (
    id SERIAL  PRIMARY KEY,
    name VARCHAR(255),
    color VARCHAR(255),
    slug VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS ingridient (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    measurement_unit VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS recipe (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    image_link VARCHAR(255),
    text VARCHAR(4095),
    cooking_time  VARCHAR(255),
    tag_id int,
    ingridient_id int,
    users_id int,
    foreign key (tag_id) references tag (id) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key (ingridient_id) references ingridient (id) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key (users_id) references users (id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE SEQUENCE IF NOT EXISTS "ID_SEQUENCE"
    MINVALUE 1
    MAXVALUE 999999999
    INCREMENT BY 1
    START WITH 6;