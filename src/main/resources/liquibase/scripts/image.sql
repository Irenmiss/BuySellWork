
CREATE TABLE image
(
    image_id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    data     oid NOT NULL
);