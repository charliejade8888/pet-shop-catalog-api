-- liquibase formatted sql
-- changeset mydb:data

DROP TABLE IF EXISTS mydb.pets;

CREATE TABLE mydb.pets
(
    id          INT AUTO_INCREMENT PRIMARY KEY, -- TODO remove and use name as PK
    name        VARCHAR(250) NOT NULL,
    description VARCHAR(250) NOT NULL,
    breed       VARCHAR(250) NOT NULL,
    type        VARCHAR(250) NOT NULL,
    price       VARCHAR(250) NOT NULL
);

INSERT INTO pets (name, description, breed, type, price)
VALUES ('Captain Patches', 'No comment', 'Moggy', 'Cat', '$200');
INSERT INTO pets (name, description, breed, type, price)
VALUES ('The Flash', 'Very fast', 'Mutant', 'Cat', '$10000');
INSERT INTO pets (name, description, breed, type, price)
VALUES ('Krypto', 'From Krypton', 'Kryptonian', 'Dog', '$100000');
INSERT INTO pets (name, description, breed, type, price)
VALUES ('Nemo', 'Friend of Dori', 'Goldfish', 'Fish', '$15');

-- rollback DROP TABLE IF EXISTS mydb.pets