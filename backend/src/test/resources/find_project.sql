DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS address;

CREATE TABLE address
(
    address_id SERIAL NOT NULL PRIMARY KEY,
    country VARCHAR(200) NOT NULL,
    city VARCHAR(200) NOT NULL,
    street VARCHAR(200) NOT NULL,
    building VARCHAR(100) NOT NULL,
    apartment VARCHAR(100) NOT NULL
);

CREATE TABLE person
(
    person_id SERIAL NOT NULL PRIMARY KEY,
    person_name VARCHAR(200) NOT NULL,
    person_surname VARCHAR(200) NOT NULL,
    person_patronymic VARCHAR(200),
    person_date_of_birth DATE NOT NULL,
    person_sex VARCHAR(10),
    person_number VARCHAR(20) UNIQUE,
    person_email VARCHAR(250) NOT NULL UNIQUE,
    person_address_id INT,
    person_country VARCHAR(200),
    person_city VARCHAR(200),
    person_street VARCHAR(200),
    person_building VARCHAR(200),
    person_apartment VARCHAR(200),
    FOREIGN KEY (person_address_id) REFERENCES address(address_id) ON DELETE RESTRICT
);