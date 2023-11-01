
CREATE TABLE comments
(
    com_id          INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    creation_time   timestamp       NOT NULL,
    com_text        VARCHAR(64) NOT NULL,
    ad_id           INTEGER      NOT NULL,
    user_id         INTEGER      NOT NULL,

    FOREIGN KEY (ad_id) REFERENCES ads (ad_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);
