DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
  id IDENTITY PRIMARY KEY,
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  country_code CHAR(2),
  age INT
);