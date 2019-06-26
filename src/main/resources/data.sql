DROP TABLE IF EXISTS billionaire;
DROP TABLE IF EXISTS office;

CREATE TABLE billionaire (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  career VARCHAR(250) DEFAULT NULL,
  city VARCHAR(250) DEFAULT NULL,
  street VARCHAR(250) DEFAULT NULL
);

CREATE TABLE office (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  country VARCHAR(250) DEFAULT NULL,
  employee Int DEFAULT 0
);


INSERT INTO billionaire (first_name, last_name, career) VALUES
  ('Aliko', 'Dangote', 'Billionaire Industrialist'),
  ('Bill', 'Gates', 'Billionaire Tech Entrepreneur'),
  ('Folrunsho', 'Alakija', 'Billionaire Oil Magnate');
