package model.Tables;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Mateusz on 2017-05-10.
 */
public class Employee {
    private final IntegerProperty employeeID;
    private final StringProperty firstname;
    private final StringProperty lastname;
    private final StringProperty task;
    private final IntegerProperty warehouseID;
    private final IntegerProperty managerID;

    public Employee(Integer employeeID, String fisrtname, String lastname, String task, Integer warehouseID, Integer managerID) {
        this.employeeID = new SimpleIntegerProperty(employeeID);
        this.firstname = new SimpleStringProperty(fisrtname);
        this.lastname = new SimpleStringProperty(lastname);
        this.task = new SimpleStringProperty(task);
        this.warehouseID = new SimpleIntegerProperty(warehouseID);
        this.managerID = new SimpleIntegerProperty(managerID);
    }

    public Integer getID() {
        return employeeID.get();
    }

    public String getFirstname() {
        return firstname.get();
    }

    public String getLastname() {
        return lastname.get();
    }

    public String getTask() {
        return task.get();
    }

    public Integer getWarehouseID() {
        return warehouseID.get();
    }

    public Integer getManagerID() {
        return managerID.get();
    }

    public void setID(Integer value){
        employeeID.set(value);
    }

    public void setFirstname(String value){
        firstname.set(value);
    }

    public void setLastname(String value){
        lastname.set(value);
    }

    public void setTask(String value){
        task.set(value);
    }

    public void setWarehouseID(Integer value){
        warehouseID.set(value);
    }

    public void setManagerID(Integer value){
        managerID.set(value);
    }

}
