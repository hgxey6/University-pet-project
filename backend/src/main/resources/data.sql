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

INSERT INTO address(country, city, street, building, apartment) VALUES
('Qazaqstan', 'Aqtobe', 'Satbayev', '45a', '3'),
('Russia', 'Moscow', 'Lenin prospect', '34', '1'),
('United States', 'New-York', 'First street', '1', '1');

INSERT INTO person(person_name, person_surname, person_date_of_birth, person_sex,
person_number, person_email, person_address_id) VALUES
('Rahat',  'Maibekov','2001.11.10', 'Male', '87076564565', 'raharaha@mail.ru', 1),
('Andrey', 'Belkov',  '1999.05.12', 'Male', '87341229645', 'adryhaaaa@mail.ru', 2),
('Bob',    'Martin',  '2000.04.03', 'Male', '05654356565', 'indexxxxxx@gmail.com', 3);