DROP TABLE IF EXISTS order_rows CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS authorities CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP SEQUENCE IF EXISTS seq1 CASCADE;

CREATE SEQUENCE seq1 AS INTEGER START WITH 1;

CREATE TABLE orders (
                        id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('seq1'),
                        order_number VARCHAR(255)
);

CREATE TABLE order_rows (
                            item_name VARCHAR(255),
                            price INT,
                            quantity INT,
                            orders_id BIGINT,
                            FOREIGN KEY (orders_id)
                                REFERENCES orders(id) ON DELETE CASCADE
);

CREATE TABLE USERS (
                       username VARCHAR(255) NOT NULL PRIMARY KEY,
                       password VARCHAR(255) NOT NULL,
                       enabled BOOLEAN NOT NULL,
                       first_name VARCHAR(255) NOT NULL
);

CREATE TABLE AUTHORITIES (
                             username VARCHAR(50) NOT NULL,
                             authority VARCHAR(50) NOT NULL,
                             FOREIGN KEY (username) REFERENCES USERS
                                 ON DELETE CASCADE
);