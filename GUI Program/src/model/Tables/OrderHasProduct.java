package model.Tables;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by Mateusz on 2017-05-10.
 */
public class OrderHasProduct {
    private final IntegerProperty productID;
    private final IntegerProperty orderID;
    private final IntegerProperty quantity;


    public OrderHasProduct(Integer productID, Integer warehouseID, Integer quantity) {
        this.productID = new SimpleIntegerProperty(productID);
        this.orderID = new SimpleIntegerProperty(warehouseID);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    public Integer getProductID() {
        return productID.get();
    }

    public Integer getQuantity() {
        return quantity.get();
    }

    public int getOrderID() {
        return orderID.get();
    }


    public void setProductID(Integer value){
        productID.set(value);
    }

    public void setQuantity(Integer value){
        quantity.set(value);
    }

    public void setOrderID(Integer value){
        orderID.set(value);
    }

}
