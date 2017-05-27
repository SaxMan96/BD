CREATE TRIGGER productDeleted AFTER DELETE ON products
FOR EACH ROW
    DELETE FROM orderhasproducts WHERE product_id = OLD.product_id;


CREATE TRIGGER increaseStorekeepers AFTER INSERT ON employees
FOR EACH ROW
    UPDATE warehouse 
    SET storekeepersnr = storekeepersnr + 1
    WHERE (warehouse_id = NEW.warehouse_id AND NEW.task = 'storekeeper');


CREATE TRIGGER productAdded AFTER INSERT ON products
FOR EACH ROW
    UPDATE warehouse
    SET ocupancy = ocupancy + n.quantity 
    WHERE warehouse_id = n.warehouse_id;
