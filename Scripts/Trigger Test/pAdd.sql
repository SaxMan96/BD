SELECT * FROM mydb.warehouse;
-- Tylko ta linia później od INSERT

INSERT INTO  products (product_id, coffeename, quantity, productioncost, price, warehouse_id)
VALUES  (2, 'Arabica', 7, 20, 28, 9010);

SELECT * FROM mydb.warehouse;