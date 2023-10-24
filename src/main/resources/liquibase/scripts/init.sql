
CREATE TABLE "users"
(
    user_id    INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    first_name VARCHAR(20) NOT NULL,
    last_name  VARCHAR(20) NOT NULL,
    email      VARCHAR(40) NOT NULL UNIQUE,
    password   VARCHAR(20) NOT NULL,
    phone      VARCHAR(11) NOT NULL,
    role       VARCHAR(10) DEFAULT 'USER',
    image      VARCHAR(255) NOT NULL

);

CREATE TABLE "ads"
(
    ad_id           INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    ad_title        VARCHAR(40)  NOT NULL,
    ad_description  VARCHAR(500) NOT NULL,
    ad_price        INTEGER      NOT NULL,
    user_id         INTEGER      NOT NULL,
    image_path      VARCHAR(255) NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (user_id)

);

CREATE TABLE "comments"
(
    com_id          INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    creation_time   INTEGER      NOT NULL,
    com_text        VARCHAR(100) NOT NULL,
    ad_id           INTEGER      NOT NULL,
    user_id         INTEGER      NOT NULL,

    FOREIGN KEY (ad_id) REFERENCES ads (ad_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);