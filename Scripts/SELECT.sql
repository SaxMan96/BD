-- 2 polecenia select uzywajace klauzuli where 
SELECT firstname, lastname 
FROM employees
WHERE task = 'seller';

SELECT warehouse_id, capitance
FROM warehouse
WHERE capitance > 4000;
-- 2 polecenia select wykorzystujace funkcje agregujace oraz grupowanie
SELECT warehouse_id, ocupancy
FROM warehouse
ORDER BY OCUPANCY ASC;

SELECT task, lastname, employee_id
FROM employees
ORDER BY task ASC, employee_id DESC;

SELECT COUNT(employee_id), task
FROM employees
GROUP BY task;
-- 2 polecenia select z podzapytaniem
SELECT warehouse_id, ocupancy FROM warehouse
WHERE ocupancy =
(SELECT MIN(ocupancy)
FROM warehouse);

SELECT warehouse_id, storekeepersnr FROM warehouse
WHERE storekeepersnr =
(SELECT MAX(storekeepersnr)
FROM warehouse);

-- 2 polecenia select uzywajace zlaczen (join)
SELECT products.product_id, products.coffeename,products.quantity, products.productioncost, products.price, products.warehouse_id
FROM products INNER JOIN warehouse ON (products.warehouse_id = warehouse.warehouse_id AND warehouse.storekeepersnr > 1);

SELECT employees.employee_id, employees.warehouse_id
FROM employees INNER JOIN warehouse ON (employees.warehouse_id = warehouse.warehouse_id AND warehouse.storekeepersnr = 2);
