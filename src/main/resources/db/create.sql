SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS customers (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  phone VARCHAR,
  type VARCHAR,
  breed VARCHAR
);

CREATE TABLE IF NOT EXISTS animals (
  id int PRIMARY KEY auto_increment,
    name VARCHAR,
    gender VARCHAR,
    type VARCHAR,
    breed VARCHAR,
    ownerId INTEGER
 );