package Controller;

import DatabaseUtility.DBUtil;
import com.sun.xml.internal.stream.dtd.DTDGrammarUtil;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.collections.ListChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.Tables.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import org.jetbrains.annotations.NotNull;

public class Controller implements Initializable {

    @FXML private AnchorPane mainAnchorPane;
    @FXML private AnchorPane leftAnchorPane;
    @FXML private AnchorPane upperPane;
    @FXML private TextField EmployeeIDTextField;
    @FXML private TextField FirstNameTextField;
    @FXML private TextField LastNameTextField;
    @FXML private TextField TaskTextField;
    @FXML private CheckBox managerIDCheck;
    @FXML private CheckBox warehouseIDCheck;
    @FXML private ComboBox<Integer> warehouseIDCombo;
    @FXML private ComboBox<Integer> managerIDCombo;
    @FXML private TextField DeleteTextField;

    @FXML private TextField OrderTotalValueTextField;
    @FXML private TextField warehousesOcupancyTextField;

    @FXML private Button deleteBtn;
    @FXML private Button addBtn;
    @FXML private Button updateBtn;
    @FXML private Button initializeBtn;

    @FXML private TableView<Employee> employeesTable;
    @FXML private TableColumn<Employee, Integer> EmployeeIDColumn;
    @FXML private TableColumn<Employee, String> EmployeesNameColumn;
    @FXML private TableColumn<Employee, String> EmployeesSurnameColumn;
    @FXML private TableColumn<Employee, String> EmployeesTaskColumn;
    @FXML private TableColumn<Employee, Integer> EmployeesManagerIDColumn;
    @FXML private TableColumn<Employee, Integer> EmployeesWarehouseIDColumn;

    @FXML private  Tab ordersTab;
    @FXML private TableView<Order> ordersTable;
    @FXML private TableColumn<Order, Integer> OrdersIDColumn;
    @FXML private TableColumn<Order, Integer> OrdersSellerIDColumn;
    @FXML private TableColumn<Order, Integer> OrdersCustomerIDColumn;
    @FXML private TableColumn<Order, Integer> OrdersProductsColumn;
    @FXML private TableColumn<Order, Integer> OrdersProductIDColumn;
    @FXML private TableColumn<Order, Integer> OrdersQuantityColumn;
    @FXML private TableColumn<Order, String> OrdersDateColumn;

    @FXML private TableView<Product> productsTable;
    @FXML private TableColumn<Product, Integer> ProductsIDColumn;
    @FXML private TableColumn<Product, String> ProductsNameColumn;
    @FXML private TableColumn<Product, Integer> ProductsWarehouseIDColumn;
    @FXML private TableColumn<Product, Integer> ProductsQuantityColumn;
    @FXML private TableColumn<Product, Integer> ProductsProductionColumn;
    @FXML private TableColumn<Product, Integer> ProductsPriceColumn;



    private ObservableList<Order> orders;
    private ObservableList<Employee> employees;
    private ObservableList<OrderHasProduct> orderHasProducts;
    private ObservableList<Warehouse> warehouses;
    private ObservableList<Product> products;
    private ResultSet rs;
    private PreparedStatement pst;

    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        orders = FXCollections.observableArrayList();
        employees = FXCollections.observableArrayList();
        orderHasProducts = FXCollections.observableArrayList();
        warehouses = FXCollections.observableArrayList();
        products = FXCollections.observableArrayList();

        EmployeeIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        EmployeesNameColumn.setCellValueFactory(new PropertyValueFactory<>("Firstname"));
        EmployeesSurnameColumn.setCellValueFactory(new PropertyValueFactory<>("Lastname"));
        EmployeesTaskColumn.setCellValueFactory(new PropertyValueFactory<>("Task"));
        EmployeesManagerIDColumn.setCellValueFactory(new PropertyValueFactory<>("ManagerID"));
        EmployeesWarehouseIDColumn.setCellValueFactory(new PropertyValueFactory<>("WarehouseID"));

        OrdersIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        OrdersDateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        OrdersSellerIDColumn.setCellValueFactory(new PropertyValueFactory<>("SellerID"));
        OrdersCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));

        ProductsIDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        ProductsNameColumn.setCellValueFactory(new PropertyValueFactory<>("CoffeeName"));
        ProductsWarehouseIDColumn.setCellValueFactory(new PropertyValueFactory<>("WarehouseID"));
        ProductsQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        ProductsProductionColumn.setCellValueFactory(new PropertyValueFactory<>("ProductionCost"));
        ProductsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

        /*addBtn.setDisable(true);*/
        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);


        warehouseIDCheck.setSelected(true);
        managerIDCheck.setSelected(true);
        warehouseIDCombo.setDisable(true);
        managerIDCombo.setDisable(true);

        ordersTab.setDisable(true);
        ordersTab = null;

        System.out.println("Zrobiłem inicjalizację");
        refreshEmployee();
    }

    @FXML
    private void addEmployee(ActionEvent event) {
        if(checkEmployeeTextFields()){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Potwierdzenie wprowadzenia pracownika");
            alert.setHeaderText(null);
            alert.setContentText("Czy na pewno chcesz wprowadzić tego pracownika?");
            Optional <ButtonType> action = alert.showAndWait();
            Connection conn = null;
            if(action.get() == ButtonType.OK){
                try {
                    conn = DBUtil.getConnection();
                    conn.setAutoCommit(false);
                    String sql = "INSERT INTO employees (employee_id, firstname, lastname, task, warehouse_id, manager_id)"
                            + " values(?,?,?,?,?,?);";
                    pst = conn.prepareStatement(sql);
                    pst.setInt(1, Integer.parseInt(EmployeeIDTextField.getText()));
                    pst.setString(2,FirstNameTextField.getText());
                    pst.setString(3,LastNameTextField.getText());
                    pst.setString(4,TaskTextField.getText());
                    if(warehouseIDCheck.isSelected())
                        pst.setString(5, null);
                    else
                        pst.setInt(5, Integer.parseInt(warehouseIDCombo.getValue().toString()));
                    if(managerIDCheck.isSelected())
                        pst.setString(6, null);
                    else
                        pst.setInt(6, Integer.parseInt(managerIDCombo.getValue().toString()));

                    pst.executeUpdate();
                    conn.commit();
                } catch ( SQLException ex) {
                    System.err.println(ex);
                    if(conn != null){
                        try {
                            System.err.println("Wycofanie transakcji dodania Employeea");
                            conn.rollback();
                        } catch (SQLException ex1) {
                            System.err.println(ex1);
                        }
                    }
                } finally{
                    if(pst != null){
                        try {
                            pst.close();
                        } catch (SQLException ex) {
                            System.err.println(ex);
                        }
                    }
                    if(conn != null){
                        try {
                            conn.close();
                        } catch (SQLException ex) {
                            System.err.println(ex);
                        }
                    }
                }
                refreshEmployee();
            }
        }
    }

    private void clearEmployee(){
        EmployeeIDTextField.clear();
        FirstNameTextField.clear();
        LastNameTextField.clear();
        TaskTextField.clear();
        warehouseIDCombo.getSelectionModel().clearSelection();
        managerIDCombo.getSelectionModel().clearSelection();
        DeleteTextField.clear();

      /*  dateUrodzenie.getEditor().setText(null);
        fieldAdresEmployeea.clear();
        fieldPensja.clear();
        dateZatrudniono.setValue(null);
        dateZatrudniono.getEditor().setText(null);
        comboOrderHasProduct.getSelectionModel().clearSelection();
        tablePracownicy.getSelectionModel().clearSelection();
        btnUsunEmployeea.setDisable(true);
        btnAktualizujEmployeea.setDisable(true);*/
    }

   /* @FXML
    private void wyczyscEmployeea(ActionEvent event) {
        czyszczenieEmployeea();
    }
*/
    @FXML
    private void deleteEmployee(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Potwierdzenie usunięcia");
        alert.setHeaderText(null);
        alert.setContentText("Czy na pewno chcesz usunąć tego Employeea?");
        Optional <ButtonType> action = alert.showAndWait();
        Connection conn = null;
        if(action.get() == ButtonType.OK){
            try {
                conn = DBUtil.getConnection();
                conn.setAutoCommit(false);
                String sql = "delete from employees where employee_id = ?;";
                pst = conn.prepareStatement(sql);
                pst.setString(1, DeleteTextField.getText());
                pst.executeUpdate();
                conn.commit();
            } catch (SQLException ex) {
                System.err.println(ex);
                if(conn != null){
                    try {
                        System.err.println("Wycofanie transakcji usunięcia Employeea");
                        conn.rollback();
                    } catch (SQLException ex1) {
                        System.err.println(ex1);
                    }
                }
            } finally{
                if(pst != null){
                    try {
                        pst.close();
                    }catch (SQLException ex) {
                        System.err.println(ex);
                    }
                }
                if(conn != null){
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        System.err.println(ex);
                    }
                }
            }
            refreshEmployee();
        }
        else{
            employeesTable.getSelectionModel().clearSelection();
            clearEmployee();
            employeesTable.setDisable(true);
        }
    }

    @FXML
    private void updateEmployee(ActionEvent event) {
        if(checkEmployeeTextFields()){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Potwierdzenie modyfikacji");
            alert.setHeaderText(null);
            alert.setContentText("Czy na pewno chcesz zmodyfikować dane tego Employeea?");
            Optional <ButtonType> action = alert.showAndWait();
            Connection conn = null;
            if(action.get() == ButtonType.OK){
                try {
                    conn = DBUtil.getConnection();
                    conn.setAutoCommit(false);
                    String sql = "update employees set firstname=?, lastname=?, task=?, warehouse_id=?, manager_id=? WHERE employee_id=?;";
                    pst = conn.prepareStatement(sql);
                    pst.setString(1,FirstNameTextField.getText());
                    pst.setString(2,LastNameTextField.getText());
                    pst.setString(3,TaskTextField.getText());
                    if(warehouseIDCheck.isSelected())
                        pst.setString(4, null);
                    else
                        pst.setInt(4, Integer.parseInt(warehouseIDCombo.getValue().toString()));
                    if(managerIDCheck.isSelected())
                        pst.setString(5, null);
                    else
                        pst.setInt(5, Integer.parseInt(managerIDCombo.getValue().toString()));
                    pst.setInt(6, Integer.parseInt(EmployeeIDTextField.getText()));
                    pst.executeUpdate();
                    conn.commit();
                } catch (SQLException ex) {
                    System.err.println(ex);
                    if(conn != null){
                        try {
                            System.err.println("Wycofanie transakcji modyfikacji Employeea");
                            conn.rollback();
                        } catch (SQLException ex1) {
                            System.err.println(ex1);
                        }
                    }
                } finally{
                    if(pst != null){
                        try {
                            pst.close();
                        } catch (SQLException ex) {
                            System.err.println(ex);
                        }
                    }
                    if(conn != null){
                        try {
                            conn.close();
                        } catch (SQLException ex) {
                            System.err.println(ex);
                        }
                    }
                }
                refreshEmployee();
            }
        }
    }


    private void refreshEmployee(){
        Integer suma = 0,ilosc = 0;
        Float srednia = 0f;
        Connection conn = null;
        deleteBtn.setDisable(true);
        updateBtn.setDisable(true);
        ArrayList<Integer> warehouseIDs = new ArrayList<>();
        ArrayList<Integer> managerIds = new ArrayList<>();
        try {
            employees.clear();
            conn = DBUtil.getConnection();
            rs = conn.createStatement().executeQuery("select * from employees");
            while(rs.next()){
                employees.add(new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("task"),
                        rs.getInt("warehouse_id"),
                        rs.getInt("manager_id")));
                        warehouseIDs.add( rs.getInt("warehouse_id"));
                        managerIds.add(rs.getInt("manager_id"));
            }
            warehouseIDs.sort(Comparator.naturalOrder());
            managerIds.sort(Comparator.naturalOrder());
            for(Integer e: warehouseIDs)
                if(!warehouseIDCombo.getItems().contains(e))
                    warehouseIDCombo.getItems().add(e);
            for(Integer e: managerIds)
                if(!managerIDCombo.getItems().contains(e))
                    managerIDCombo.getItems().add(e);
            //rs = conn.createStatement().executeQuery("select IDZlecenia from zlecenia order by IDZlecenia asc");
            /*while(rs.next()){
                projekty.add(rs.getInt("IDZlecenia"));
            }*/

    // Przy dodatkowych funkcjach ---------------------------

            /*rs = conn.createStatement().executeQuery("SELECT count(*) as ilosc, avg(Wynagrodzenie) as srednia, sum(Wynagrodzenie) as suma from pracownicy;");
            if(rs.next()){
                ilosc = rs.getInt("ilosc");
                srednia = rs.getFloat("srednia");
                suma = rs.getInt("suma");
            }*/
        }
        catch (SQLException ex) {
            System.err.println(ex);
        }
        catch (NullPointerException e){
            System.out.println("NullPointerException - pierwsza iteracja ObservebleList<Employee>");
        }
        finally{
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }
        employeesTable.setItems(employees);
        //comboOrderHasProduct.setItems(projekty);
        /*
        fieldIloscZatrudnionych.setText(ilosc.toString());
        fieldSumaWyplat.setText(suma.toString());
        fieldSredniaPlaca.setText(srednia.toString());
        */
        clearEmployee();
        System.out.println("Odswiezam pracownicy");
    }

    @FXML
    private void fillEmployee(MouseEvent event) {
        if(!employeesTable.getSelectionModel().isEmpty()){
            deleteBtn.setDisable(false);
            updateBtn.setDisable(false);
            Employee employee = employeesTable.getSelectionModel().getSelectedItem();
            EmployeeIDTextField.setText(employee.getID().toString());
            FirstNameTextField.setText(employee.getFirstname());
            LastNameTextField.setText(employee.getLastname());
            TaskTextField.setText(employee.getTask());
            if(employee.getWarehouseID() != 0)
                warehouseIDCombo.setValue(employee.getWarehouseID());
            else
                warehouseIDCombo.getSelectionModel().clearSelection();
            if(employee.getManagerID() != 0)
                managerIDCombo.setValue(employee.getWarehouseID());
            else
                managerIDCombo.getSelectionModel().clearSelection();
            DeleteTextField.setText(employee.getID().toString());
        }
    }

    @FXML
    private void refreshEmployees(Event event) {
        refreshEmployee();
        clearEmployee();
    }


    private boolean checkEmployeeTextFields(){
        Integer ID;
        if( EmployeeIDTextField.getText().isEmpty() || FirstNameTextField.getText().isEmpty() || LastNameTextField.getText().isEmpty()){

            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Wypełnienie pól");
            alert.setHeaderText(null);
            alert.setContentText("Niektóre pola pozostają puste. Uzupełnij wymagane pola i spróbuj ponownie");
            alert.showAndWait();
            return false;
        }

        try{
            ID = Integer.parseInt(EmployeeIDTextField.getText());
            if(ID < 0){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Niepoprawne ID");
                alert.setHeaderText(null);
                alert.setContentText("ID powinno być liczbą dodatnią.");
                alert.showAndWait();
                return false;
            }
        }catch(NumberFormatException e){
            System.err.println(e);
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Format liczbowy");
            alert.setHeaderText(null);
            alert.setContentText("Wprowadzone ID nie jest liczbą.");
            alert.showAndWait();
            return false;
        }

        return true;
    }

    @FXML
    private void setWarehouseIDCheck(Event event){
        warehouseIDCombo.getSelectionModel().clearSelection();
        warehouseIDCombo.setDisable( warehouseIDCheck.isSelected());
        warehouseIDCombo.getSelectionModel().select(0);
    }

    @FXML
    private void setManagerIDCheck(Event event){
        managerIDCombo.getSelectionModel().clearSelection();
        managerIDCombo.setDisable(managerIDCheck.isSelected());
        managerIDCombo.getSelectionModel().select(0);
    }

    @FXML
    private void refreshOrders(Event event){
        refreshOrder();
    }
    private void refreshOrder() {
        Connection conn = null;
        try{
            orders.clear();
            conn = DBUtil.getConnection();
            rs = conn.createStatement().executeQuery("select * from orders");
            while(rs.next()){
                orders.add(new Order(
                        rs.getInt("order_id"),
                        rs.getInt("seller_id"),
                        rs.getInt("customer_id"),
                        rs.getString("orderdate")
                        )
                );
            }
        }
        catch(SQLException e){
            System.err.println(e);
        }finally{
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }
        ordersTable.setItems(orders);

                //Jakoś trzeba się dobrać do tych kolumn.set();
    }

    @FXML
    private void refreshProducts(Event event) {
        refreshProduct();
    }
    private void refreshProduct(){
        Connection conn = null;
        try {
            products.clear();
            conn = DBUtil.getConnection();
            rs = conn.createStatement().executeQuery("select * from products");
            while(rs.next()){
                products.add(new Product(
                        rs.getInt("product_id"),
                        rs.getString("coffeename"),
                        rs.getInt("warehouse_id"),
                        rs.getInt("quantity"),
                        rs.getInt("price"),
                        rs.getInt("productioncost")
                ));
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        } finally{
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }
        productsTable.setItems(products);
    }

    @FXML private void ordersTotalValue(Event event){
        Connection conn = null;
        try {
            products.clear();
            conn = DBUtil.getConnection();
            rs = conn.createStatement().executeQuery("SELECT  \n" +
                    "        SUM(a.quantity*b.price) AS total\n" +
                    "FROM    orderhasproducts a\n" +
                    "JOIN\tproducts b\n" +
                    "\tON (a.product_id = b.product_id)");
            rs.next();
            Integer i = rs.getInt("total");
            OrderTotalValueTextField.setText(i.toString());
        } catch (SQLException ex) {
            System.err.println(ex);
        } finally{
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }
    }

    @FXML private void warehousesOcupancy(Event event){
        Connection conn = null;
        try {
            products.clear();
            conn = DBUtil.getConnection();
            rs = conn.createStatement().executeQuery("SELECT  \n" +
                    "        SUM(a.ocupancy) AS total\n" +
                    "FROM    warehouse a\n");
            rs.next();
            Integer i = rs.getInt("total");
            warehousesOcupancyTextField.setText(i.toString());
        } catch (SQLException ex) {
            System.err.println(ex);
        } finally{
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }
    }

}
