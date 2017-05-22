DROP TABLE IF EXISTS customers_social_media;
DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
  id IDENTITY PRIMARY KEY,
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  country_code CHAR(2),
  age INT
);

CREATE TABLE customers_social_media (
  id IDENTITY PRIMARY KEY,
  customer_id INT,
  social_media VARCHAR(20),
  check (social_media in ('FACEBOOK', 'TWITTER', 'OTHER')),
  FOREIGN KEY (customer_id) REFERENCES customers(id)
);