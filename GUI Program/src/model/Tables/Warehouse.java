package model.Tables;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
/**
 * Created by Mateusz on 2017-05-10.
 */
public class Warehouse {

    private final IntegerProperty warehouseID;
    private final IntegerProperty capitance;
    private final IntegerProperty quantity;
    private final IntegerProperty storekeepersNr;


    public Warehouse(Integer warehouseID, Integer capitance, Integer quantity, Integer storekeepersNr) {
        this.warehouseID = new SimpleIntegerProperty(warehouseID);
        this.capitance = new SimpleIntegerProperty(capitance);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.storekeepersNr = new SimpleIntegerProperty(storekeepersNr);
    }

    public int getID() {
        return warehouseID.get();
    }

    public Integer getLastname() {
        return capitance.get();
    }

    public Integer getQuantity() {
        return quantity.get();
    }

    public Integer getStorekeepersNr() {
        return storekeepersNr.get();
    }

    public void setID(Integer value){
        warehouseID.set(value);
    }

    public void setCapitance(Integer value){
        capitance.set(value);
    }

    public void setQuantity(Integer value){
        quantity.set(value);
    }

    public void setStorekeepersNr(Integer value){
        storekeepersNr.set(value);
    }
}
