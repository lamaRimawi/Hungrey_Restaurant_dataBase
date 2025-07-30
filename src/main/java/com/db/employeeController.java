package com.db;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class employeeController {

    @FXML
    private TableView<Employee> tableView;

    @FXML
    private TableColumn<Employee, Integer> employeeID;

    @FXML
    private TableColumn<Employee, String> fullName, email, phone, role, branch, schedule, salary;


    @FXML
    private TextField tvEmployeeID, tvFullName, tvEmail, tvPhone, tvRole, tvBranch, tvSchedule, tvSalary;


    private Connection connection;

    private ObservableList<Employee> employeesList = FXCollections.observableArrayList();

    @FXML
    void initialize() throws ClassNotFoundException {
        tableView.setItems(employeesList);

        // Configure table columns
        employeeID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        branch.setCellValueFactory(new PropertyValueFactory<>("branch"));
        schedule.setCellValueFactory(new PropertyValueFactory<>("schedule"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));


        // Load orders data into the table
        loadEmployeesData();

    }

    @FXML
    private void loadEmployeesData() throws ClassNotFoundException {
        try {

            Statement statement = Connector.con.connectDB().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

            // Clear existing data from the table
            tableView.getItems().clear();

            // Populate the table with data from the database
            while (resultSet.next()) {
                int employeeID = resultSet.getInt("employeeID");
                String fullName = resultSet.getString("fullName");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String role = resultSet.getString("role");
                String brunch = resultSet.getString("brunch");
                String schedule = resultSet.getString("schedule");
                String salary = resultSet.getString("salary");

                // Create an Order object and add it to the table
                tableView.getItems()
                        .add(new Employee(employeeID, fullName, email, phone, role, brunch, schedule,salary));
            }
            // Calculate and display the number of orders made during different time periods
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void clear(ActionEvent event) {
        tvEmployeeID.clear();
        tvFullName.clear();
        tvEmail.clear();
        tvPhone.clear();
        tvRole.clear();
        tvBranch.clear();
        tvSchedule.clear();
        tvSalary.clear();
    }

  

    @FXML
    private void logOut(ActionEvent event) throws IOException {
        DB_Main.setRoot("login");
    }

    @FXML
    private void switchToCash(ActionEvent event) throws IOException {
        DB_Main.setRoot("cash");
    }

    @FXML
    private void addOrder(ActionEvent event) throws SQLException, ClassNotFoundException {

        {

            System.out.println("Adding Employee");
            int employeeID = Integer.parseInt(tvEmployeeID.getText());
            String fullName = tvFullName.getText();
            String email = tvEmail.getText();
            String phone = tvPhone.getText();
            String role = tvRole.getText();
            String branch = tvBranch.getText();
            String schedule = tvSchedule.getText();
            String salary = tvSalary.getText();

            Employee newEmployee = new Employee(employeeID, fullName, email, phone, role, branch, schedule,
                    salary);

            employeesList.add(newEmployee);

            // Clear existing data from the table
            tableView.getItems().clear();

            String query = "INSERT INTO employees VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = Connector.con.connectDB().prepareStatement(query);
            pstmt.setInt(1, employeeID);
            pstmt.setString(2, fullName);
            pstmt.setString(3, email);
            pstmt.setString(4, phone);
            pstmt.setString(5, role);
            pstmt.setString(6, branch);
            pstmt.setString(8, schedule);
            pstmt.setString(9, salary);
            pstmt.executeUpdate();

            clear(event);
            loadEmployeesData();
        }

    }

    @FXML
    private void updateEmployee(ActionEvent event) throws SQLException, ClassNotFoundException {

        {
            System.out.println("Updating Employee");
            int employeeID = Integer.parseInt(tvEmployeeID.getText());
            String fullName = tvFullName.getText();
            String email = tvEmail.getText();
            String phone = tvPhone.getText();
            String role = tvRole.getText();
            String branch = tvBranch.getText();
            String schedule = tvSchedule.getText();
            String salary = tvSalary.getText();

            // Clear existing data from the table
            tableView.getItems().clear();

            String query = "UPDATE employee SET fullName = ?, email = ?, phone = ?," +
                    " role = ?, branch = ?, schedule = ?, salary = ?  WHERE employeeID = ?";
            PreparedStatement pstmt = Connector.con.connectDB().prepareStatement(query);
            pstmt.setString(1, fullName);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, role);
            pstmt.setString(5, branch);
            pstmt.setString(6, schedule);
            pstmt.setString(7, salary);
            pstmt.setInt(8, employeeID);
            pstmt.executeUpdate();

            clear(event);
            loadEmployeesData();
        }

    }

    @FXML
    private void deleteEmployee(ActionEvent event) throws SQLException, ClassNotFoundException {

        {
            System.out.println("Deleting Employee");
            int employeeID = Integer.parseInt(tvEmployeeID.getText());

            // Clear existing data from the table
            tableView.getItems().clear();

            String query = "DELETE FROM orders WHERE oemployeeID = ?";
            PreparedStatement pstmt = Connector.con.connectDB().prepareStatement(query);
            pstmt.setInt(1, employeeID);
            pstmt.executeUpdate();

            clear(event);
            loadEmployeesData();
        }

    } 
  

    @FXML
    private void searchEmployee(ActionEvent event) {
    String employeeID = tvEmployeeID.getText();
    String fullName = tvFullName.getText();
    String email = tvEmail.getText();
    String phone = tvPhone.getText();
    String role = tvRole.getText();
    String branch = tvBranch.getText();
    String schedule = tvSchedule.getText();
    String salary = tvSalary.getText();

    // Construct the SQL query based on the provided parameters
    String query = "SELECT * FROM employees WHERE";
    boolean isFirstCondition = true;

    if (!employeeID.isEmpty()) {
        query += " employeeID = '" + employeeID + "'";
        isFirstCondition = false;
    }
    if (!fullName.isEmpty()) {
        if (!isFirstCondition) {
            query += " AND";
        }
        query += " fullName = '" + fullName + "'";
        isFirstCondition = false;
    }
    if (!email.isEmpty()) {
        if (!isFirstCondition) {
            query += " AND";
        }
        query += " email = '" + email + "'";
        isFirstCondition = false;
    }
    if (!phone.isEmpty()) {
        if (!isFirstCondition) {
            query += " AND";
        }
        query += " phone = '" + phone + "'";
        isFirstCondition = false;
    }
    if (!role.isEmpty()) {
        if (!isFirstCondition) {
            query += " AND";
        }
        query += " role = '" + role + "'";
        isFirstCondition = false;
    }
    if (!branch.isEmpty()) {
        if (!isFirstCondition) {
            query += " AND";
        }
        query += " branch = '" + branch + "'";
        isFirstCondition = false;
    }
    if (!schedule.isEmpty()) {
        if (!isFirstCondition) {
            query += " AND";
        }
        query += " schedule = '" + schedule + "'";
        isFirstCondition = false;
    }
    if (!salary.isEmpty()) {
        if (!isFirstCondition) {
            query += " AND";
        }
        query += " salary = '" + salary + "'";
    }

    Statement statement = null;
    ResultSet resultSet = null;
    try {
        connection = Connector.con.connectDB();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);

        tableView.getItems().clear();
        ObservableList<Employee> employees = FXCollections.observableArrayList();

        while (resultSet.next()) {
            int fetchedEmployeeID = resultSet.getInt("employeeID");
            String fetchedFullName = resultSet.getString("fullName");
            String fetchedEmail = resultSet.getString("email");
            String fetchedPhone = resultSet.getString("phone");
            String fetchedRole = resultSet.getString("role");
            String fetchedBranch = resultSet.getString("branch");
            String fetchedSchedule = resultSet.getString("schedule");
            String fetchedSalary = resultSet.getString("salary");

            Employee employee = new Employee(fetchedEmployeeID, fetchedFullName, fetchedEmail, fetchedPhone, fetchedRole, fetchedBranch, fetchedSchedule, fetchedSalary);
            employees.add(employee);
        }
        tableView.setItems(employees);
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } finally {
        // Close resources and handle exceptions
        try {
            if (resultSet != null) resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (statement != null) statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Clear input fields
        clear(event);
    }
}
}
