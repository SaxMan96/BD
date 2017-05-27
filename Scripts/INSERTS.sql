
INSERT INTO employees (employee_id, task, firstname, lastname)
VALUES (101,'engineer', 'Janusz', 'Nowak');
INSERT INTO employees (employee_id, task, firstname, lastname)
VALUES (102,'engineer', 'Adam', 'Wenge');
INSERT INTO employees (employee_id, task, firstname, lastname)
VALUES (103,'engineer', 'Jerzy', 'Mak');


INSERT INTO employees (employee_id, task, firstname, lastname)
VALUES (301,'seller', 'Edyta', 'Kaminowska');
INSERT INTO employees (employee_id, task, firstname, lastname)
VALUES (302,'seller', 'Dariusz', 'Marecki');
INSERT INTO employees (employee_id, task, firstname, lastname)
VALUES (303,'seller', 'Adam', 'Henke');


INSERT INTO warehouse (warehouse_id, ocupancy, capitance, storekeepersnr)
VALUES (9010, 0, 6350, 0);
INSERT INTO warehouse (warehouse_id, ocupancy, capitance, storekeepersnr)
VALUES (9020, 0, 4200, 0);
INSERT INTO warehouse (warehouse_id, ocupancy, capitance, storekeepersnr)
VALUES (9021, 0, 5500, 0);
INSERT INTO warehouse (warehouse_id, ocupancy, capitance, storekeepersnr)
VALUES (9022, 0, 3200, 0);
INSERT INTO warehouse (warehouse_id, ocupancy, capitance, storekeepersnr)
VALUES (9023, 0, 2500, 0);
INSERT INTO warehouse (warehouse_id, ocupancy, capitance, storekeepersnr)
VALUES (9024, 0, 4300, 0);
INSERT INTO warehouse (warehouse_id, ocupancy, capitance, storekeepersnr)
VALUES (9025, 0, 7300, 0);
INSERT INTO warehouse (warehouse_id, ocupancy, capitance, storekeepersnr)
VALUES (9011, 0, 2300, 0);
INSERT INTO warehouse (warehouse_id, ocupancy, capitance, storekeepersnr)
VALUES (9012, 0, 4500, 0);
INSERT INTO warehouse (warehouse_id, ocupancy, capitance, storekeepersnr)
VALUES (9013, 0, 5600, 0);


INSERT INTO employees (employee_id, task, firstname, lastname, warehouse_id)
VALUES (201, 'storekeeper', 'Maciej', 'Krzak', 9010);
UPDATE warehouse
SET storekeepersnr = storekeepersnr + 1
WHERE warehouse_id = 9010;
INSERT INTO employees (employee_id, task, firstname, lastname, warehouse_id)
VALUES (202, 'storekeeper', 'Michalina', 'Kowalczyk', 9010);
UPDATE warehouse
SET storekeepersnr = storekeepersnr + 1
WHERE warehouse_id = 9010;
INSERT INTO employees (employee_id, task, firstname, lastname, warehouse_id)
VALUES (203, 'storekeeper', 'Katarzyna', 'Rakowska', 9020);
UPDATE warehouse
SET storekeepersnr = storekeepersnr + 1
WHERE warehouse_id = 9020;
INSERT INTO employees (employee_id, task, firstname, lastname, warehouse_id)
VALUES (204, 'storekeeper', 'Piotr', 'Makowiecki', 9021);
UPDATE warehouse
SET storekeepersnr = storekeepersnr + 1
WHERE warehouse_id = 9021;
INSERT INTO employees (employee_id, task, firstname, lastname, warehouse_id)
VALUES (205, 'storekeeper', 'Maciej', 'Kosowski', 9021);
UPDATE warehouse
SET storekeepersnr = storekeepersnr + 1
WHERE warehouse_id = 9021;


INSERT INTO employees (employee_id, task, firstname, lastname)
VALUES (501, 'manager', 'Mateusz', 'Borowski');
INSERT INTO employees (employee_id, task, firstname, lastname)
VALUES (502, 'manager', 'Maria', 'Chrzanowska');
INSERT INTO employees (employee_id, task, firstname, lastname)
VALUES (503, 'manager', 'Ryszard', 'Bocian');


UPDATE employees
SET manager_id = 501
WHERE employee_id = 101;
UPDATE employees
SET manager_id = 501
WHERE employee_id = 102;
UPDATE employees
SET manager_id = 501
WHERE employee_id = 103;


UPDATE employees
SET manager_id = 502
WHERE employee_id = 201;
UPDATE employees
SET manager_id = 502
WHERE employee_id = 202;
UPDATE employees
SET manager_id = 502
WHERE employee_id = 203;
UPDATE employees
SET manager_id = 502
WHERE employee_id = 204;
UPDATE employees
SET manager_id = 502
WHERE employee_id = 205;


UPDATE employees
SET manager_id = 503
WHERE employee_id = 301;
UPDATE employees
SET manager_id = 503
WHERE employee_id = 302;
UPDATE employees
SET manager_id = 503
WHERE employee_id = 303;



INSERT INTO  products (product_id, coffeename, quantity, productioncost, price, warehouse_id)
VALUES  (1, 'Inka', 750, 20, 28, 9010);
UPDATE warehouse
SET ocupancy = ocupancy + 750 
WHERE warehouse_id = 9010;
INSERT INTO  products (product_id, coffeename, quantity, productioncost, price, warehouse_id)
VALUES  (2, 'Arabica', 420, 35, 43, 9020);
UPDATE warehouse
SET ocupancy = ocupancy + 420 
WHERE warehouse_id = 9020;
INSERT INTO  products (product_id, coffeename, quantity, productioncost, price, warehouse_id)
VALUES  (3, 'Robusta', 210, 54, 67, 9020);
UPDATE warehouse
SET ocupancy = ocupancy + 210 
WHERE warehouse_id = 9020;
INSERT INTO  products (product_id, coffeename, quantity, productioncost, price, warehouse_id)
VALUES  (4, 'InkaBlack', 560, 24, 29, 9021);
UPDATE warehouse
SET ocupancy = ocupancy + 560 
WHERE warehouse_id = 9021;
INSERT INTO  products (product_id, coffeename, quantity, productioncost, price, warehouse_id)
VALUES  (5, 'ArabicaPalona', 160, 64, 72, 9021);
UPDATE warehouse
SET ocupancy = ocupancy + 160 
WHERE warehouse_id = 9021;


INSERT INTO  products (product_id, coffeename, quantity, productioncost, price, warehouse_id)
VALUES  (6, 'Liberica', 110, 34, 42, 9010);
UPDATE warehouse
SET ocupancy = ocupancy + 110 
WHERE warehouse_id = 9010;
INSERT INTO  products (product_id, coffeename, quantity, productioncost, price, warehouse_id)
VALUES  (7, 'Zielona', 260, 22, 29, 9023);
UPDATE warehouse
SET ocupancy = ocupancy + 260
WHERE warehouse_id = 9023;
INSERT INTO  products (product_id, coffeename, quantity, productioncost, price, warehouse_id)
VALUES  (8, 'Attibassi', 220, 25, 34, 9024);
UPDATE warehouse
SET ocupancy = ocupancy + 220
WHERE warehouse_id = 9024;
INSERT INTO  products (product_id, coffeename, quantity, productioncost, price, warehouse_id)
VALUES  (9, 'Hopje', 360, 56, 70, 9022);
UPDATE warehouse
SET ocupancy = ocupancy + 360
WHERE warehouse_id = 9022;

INSERT INTO  products (product_id, coffeename, quantity, productioncost, price, warehouse_id)
VALUES  (10, 'Pluton', 130, 78, 89, 9024);
UPDATE warehouse
SET ocupancy = ocupancy + 130
WHERE warehouse_id = 9024;


INSERT INTO orders (order_id, seller_id, customer_id)
VALUES (1,301, 1);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (1,1,200);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (2,1,300);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (3,1,150);

INSERT INTO orders (order_id, seller_id, customer_id)
VALUES (2,301, 1);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (3,2,430);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (2,2,870);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (4,2,550);

INSERT INTO orders (order_id, seller_id, customer_id)
VALUES (3,301, 2);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (5,3,200);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (2,3,430);

INSERT INTO orders (order_id, seller_id, customer_id)
VALUES (4,302, 3);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (1,4,200);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (2,4,300);


INSERT INTO orders (order_id, seller_id, customer_id)
VALUES (5,302, 4);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (1,5,200);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (2,5,270);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (3,5,150);

INSERT INTO orders (order_id, seller_id, customer_id)
VALUES (6,302, 5);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (1,6,270);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (2,6,320);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (3,6,270);

INSERT INTO orders (order_id, seller_id, customer_id)
VALUES (7,302, 6);

INSERT INTO orders (order_id, seller_id, customer_id)
VALUES (8,302, 7);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (1,8,200);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (2,8,270);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (3,8,350);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (4,8,220);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (5,8,320);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (6,8,850);

INSERT INTO orders (order_id, seller_id, customer_id)
VALUES (9,302, 8);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (3,9,160);

INSERT INTO orders (order_id, seller_id, customer_id)
VALUES (10,302, 9);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (3,10,140);
INSERT INTO orderhasproducts(product_id, order_id, quantity)
VALUES (5,10,350);
Commit;
