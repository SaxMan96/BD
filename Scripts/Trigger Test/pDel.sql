SELECT * FROM mydb.orderhasproducts;
-- Tylko ta linia później od DELETE

DELETE FROM products
WHERE product_id=1;

SELECT * FROM mydb.orderhasproducts;