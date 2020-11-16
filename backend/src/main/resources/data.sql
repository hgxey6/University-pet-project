DROP TABLE IF EXISTS person;

CREATE TABLE person
(
    person_id SERIAL NOT NULL PRIMARY KEY,
    person_name VARCHAR(200) NOT NULL,
    person_surname VARCHAR(200) NOT NULL
);

INSERT INTO person(person_name, person_surname) VALUES
('John', 'Smith'),
('Martin', 'MacFly'),
('Bob', 'Gar');