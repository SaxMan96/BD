package model.DAO;

/**
 * Created by Mateusz on 2017-05-11.
 */
import DatabaseUtility.DBUtil;
import model.Tables.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {

    //*******************************
    //SELECT an Employee
    //*******************************
    public static Employee searchEmployee (String empId) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM employees WHERE employee_id="+empId;

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            Employee employee = getEmployeeFromResultSet(rsEmp);

            //Return employee object
            return employee;
        } catch (SQLException e) {
            System.out.println("While searching an employee with " + empId + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    //Use ResultSet from DB as parameter and set Employee Object's attributes and return employee object.
    private static Employee getEmployeeFromResultSet(ResultSet rs) throws SQLException
    {
        Employee emp = null;
        if (rs.next()) {
            emp = new Employee( rs.getInt("employee_id"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("task"),
                    rs.getInt("warehouse_id"),
                    rs.getInt("manager_id"));

        }
        return emp;
    }

    //*******************************
    //SELECT Employees
    //*******************************
    public static ObservableList<Employee> searchEmployees () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM employees";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmps = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Employee> empList = getEmployeeList(rsEmps);

            //Return employee object
            return empList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    //Select * from employees operation
    private static ObservableList<Employee> getEmployeeList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Employee objects
        ObservableList<Employee> empList = FXCollections.observableArrayList();

        while (rs.next()) {
            Employee emp = new Employee(rs.getInt("employee_id"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("task"),
                    rs.getInt("warehouse_id"),
                    rs.getInt("manager_id"));

            //Add employee to the ObservableList
            empList.add(emp);
        }
        //return empList (ObservableList of Employees)
        return empList;
    }

    //*************************************
    //UPDATE an employee's email address
    //*************************************
    /*public static void updateEmpEmail (String empId, String empEmail) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt =
                "BEGIN\n" +
                        "   UPDATE employees\n" +
                        "      SET EMAIL = '" + empEmail + "'\n" +
                        "    WHERE EMPLOYEE_ID = " + empId + ";\n" +
                        "   COMMIT;\n" +
                        "END;";

        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }*/

    //*************************************
    //DELETE an employee
    //*************************************
    public static void deleteEmpWithId (String empId) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                "BEGIN\n" +
                        "   DELETE FROM employees\n" +
                        "         WHERE employee_id ="+ empId +";\n" +
                        "   COMMIT;\n" +
                        "END;";

        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    //*************************************
    //INSERT an employee
    //*************************************
    public static void insertEmp (Integer id, String name, String lastname, String task, Integer warehouseID, Integer managerID) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                "BEGIN\n" +
                        "INSERT INTO employees\n" +
                        "employee_id, firstName, lastName, task, warehouse_id, manager_id\n" +
                        "VALUES\n" +
                        "('"+id+"', '"+name+"', '"+lastname+"','"+task+"','"+warehouseID+"','"+managerID+"' );\n" +
                        "END;";

        //Execute DELETE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
}