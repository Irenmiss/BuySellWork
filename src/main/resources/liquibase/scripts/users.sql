
CREATE TABLE users
(
    user_id    INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    first_name VARCHAR(16) NOT NULL,
    last_name  VARCHAR(16) NOT NULL,
    username   VARCHAR(32) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    phone      VARCHAR(15) NOT NULL,
    role       VARCHAR(10) DEFAULT 'USER',
    image      VARCHAR (255)

);