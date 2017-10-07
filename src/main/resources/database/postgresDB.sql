DROP DATABASE clientsDB;
CREATE DATABASE  clientsDB;

CREATE TABLE clients
(
  id SERIAL PRIMARY KEY NOT NULL,
  surname VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  birthday DATE NOT NULL,
  gender VARCHAR(255) NOT NULL,
  tin BIGINT NOT NULL
);

CREATE TABLE orders
(
  id SERIAL PRIMARY KEY NOT NULL ,
  created_date DATE NOT NULL,
  status VARCHAR(255),
  amount DOUBLE PRECISION NOT NULL,
  currency VARCHAR(255) NOT NULL,
  confirmation BOOLEAN,
  client_id BIGINT
);
CREATE TABLE users
(
  id SERIAL PRIMARY KEY NOT NULL,
  username VARCHAR(255),
  password VARCHAR(255)
);

ALTER TABLE orders ADD FOREIGN KEY (client_id) REFERENCES clients (id);