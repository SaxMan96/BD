SELECT * FROM mydb.warehouse;
-- Tylko ta linia później od INSERT

INSERT INTO employees (employee_id, task, firstname, lastname, warehouse_id)
VALUES (12772893,'storekeeper', 'Ada', 'Mak', 9010);

SELECT * FROM mydb.warehouse;
