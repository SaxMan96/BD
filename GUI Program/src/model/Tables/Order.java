package model.Tables;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Mateusz on 2017-05-10.
 */
public class Order {
    private final IntegerProperty orderID;
    private final StringProperty orderDate;
    private final IntegerProperty sellerID;
    private final IntegerProperty customerID;

    public Order(Integer orderID,  Integer sellerID, Integer customerID, String orderDate) {
        this.orderID = new SimpleIntegerProperty(orderID);
        this.orderDate = new SimpleStringProperty(orderDate);
        this.sellerID = new SimpleIntegerProperty(sellerID);
        this.customerID = new SimpleIntegerProperty(customerID);
    }

    public Integer getID() {
        return orderID.get();
    }

    public String getDate() {
        return orderDate.get();
    }

    public Integer getSellerID() {
        return sellerID.get();
    }

    public Integer getCustomerID() {
        return customerID.get();
    }

    public void setID(Integer value){
        orderID.set(value);
    }

    public void setDate(String value){
        orderDate.set(value);
    }

    public void setSellerID(Integer value){
        sellerID.set(value);
    }

    public void setCustomerID(Integer value){
        customerID.set(value);
    }


}
