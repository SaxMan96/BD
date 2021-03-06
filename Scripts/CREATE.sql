CREATE DATABASE IF NOT EXISTS mydb;

USE mydb;

SET foreign_key_checks = 0;
-- Drop tables
DROP TABLE employees;
DROP TABLE orderhasproducts;
DROP TABLE orders;
DROP TABLE products;
DROP TABLE warehouse;

SET foreign_key_checks = 1;

CREATE TABLE employees (
    employee_id              INTEGER NOT NULL,
    task                     VARCHAR(45),
    firstname                VARCHAR(20),
    lastname                 VARCHAR(30),
    warehouse_id             INTEGER,
	manager_id				 INTEGER
);
ALTER TABLE employees ADD CONSTRAINT employees_pk PRIMARY KEY ( employee_id );


CREATE TABLE orderhasproducts (
    product_id   INTEGER NOT NULL,
    order_id     INTEGER NOT NULL,
    quantity     INTEGER NOT NULL
);
ALTER TABLE orderhasproducts ADD CONSTRAINT orderhasproducts_pk PRIMARY KEY ( product_id, order_id );

    
CREATE TABLE orders (
    order_id      INTEGER NOT NULL,
	seller_id	  INTEGER,
    customer_id   INTEGER,
    orderdate     DATE
);
ALTER TABLE orders ADD CONSTRAINT orders_pk PRIMARY KEY ( order_id );

CREATE TABLE products (
    product_id       INTEGER NOT NULL,
    coffeename       VARCHAR(15),
    quantity         INTEGER,
    productioncost   INTEGER,
    price            INTEGER,
    warehouse_id     INTEGER NOT NULL
);
ALTER TABLE products ADD CONSTRAINT products_pk PRIMARY KEY ( product_id );

    
CREATE TABLE warehouse (
    warehouse_id     INTEGER NOT NULL,
    capitance        INTEGER,
    ocupancy         INTEGER,
    storekeepersnr   INTEGER
);
ALTER TABLE warehouse ADD CONSTRAINT warehouse_pk PRIMARY KEY ( warehouse_id );


ALTER TABLE orderhasproducts    ADD CONSTRAINT orderhasproducts_products_fk FOREIGN KEY ( product_id )
    REFERENCES products ( product_id );
ALTER TABLE orderhasproducts    ADD CONSTRAINT orderhasproducts_orders_fk   FOREIGN KEY ( order_id )
    REFERENCES orders ( order_id );
ALTER TABLE products            ADD CONSTRAINT warehousestoredinid          FOREIGN KEY ( warehouse_id )
    REFERENCES warehouse ( warehouse_id );
ALTER TABLE employees           ADD CONSTRAINT warehouseworkedinid          FOREIGN KEY ( warehouse_id )
    REFERENCES warehouse ( warehouse_id );
ALTER TABLE employees           ADD CONSTRAINT supervisor                  FOREIGN KEY ( manager_id )
    REFERENCES employees ( employee_id );
ALTER TABLE orders              ADD CONSTRAINT servingemployee              FOREIGN KEY ( seller_id )
    REFERENCES employees ( employee_id );