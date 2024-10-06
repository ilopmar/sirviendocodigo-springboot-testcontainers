CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE SEQUENCE users_seq increment by 50;
