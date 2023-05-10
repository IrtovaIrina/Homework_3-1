CREATE TABLE car
(
    id BIGINT NOT NULL PRIMARY KEY,
    brand VARCHAR(100),
    model VARCHAR(100),
    price INTEGER
);

CREATE TABLE persons
(
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(100),
    age INTEGER,
    driver_license BOOLEAN,
    car_id bigint,
    car_id SERIAL REFERENCES cars (id)
);
