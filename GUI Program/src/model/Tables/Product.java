package model.Tables;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Mateusz on 2017-05-10.
 */
public class Product {
    private final IntegerProperty productID;
    private final StringProperty coffeeName;
    private final IntegerProperty warehouseID;
    private final IntegerProperty quantity;
    private final IntegerProperty price;
    private final IntegerProperty productionCost;


    public Product(Integer productID, String coffeeName, Integer warehouseID, Integer quantity, Integer price, Integer productionCost) {
        this.productID = new SimpleIntegerProperty(productID);
        this.coffeeName = new SimpleStringProperty(coffeeName);
        this.warehouseID = new SimpleIntegerProperty(warehouseID);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.price = new SimpleIntegerProperty(price);
        this.productionCost = new SimpleIntegerProperty(productionCost);
    }

    public Integer getID() {
        return productID.get();
    }

    public String getCoffeeName() {
        return coffeeName.get();
    }

    public Integer getQuantity() {
        return quantity.get();
    }

    public Integer getPrice() {
        return price.get();
    }

    public Integer getProductionCost() {
        return productionCost.get();
    }

    public Integer getWarehouseID() {
        return warehouseID.get();
    }


    public void setID(Integer value){
        productID.set(value);
    }

    public void setCoffeeName(String value){
        coffeeName.set(value);
    }

    public void setQuantity(Integer value){
        quantity.set(value);
    }

    public void setPrice(Integer value){
        price.set(value);
    }

    public void setProductionCost(Integer value){
        productionCost.set(value);
    }

    public void setWarehouseID(Integer value){
        warehouseID.set(value);
    }

}
