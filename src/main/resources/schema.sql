DROP TABLE device;

CREATE TABLE device
(
  id   SERIAL PRIMARY KEY    NOT NULL,
  uuid VARCHAR(64),
  swagger VARCHAR(1024)
);