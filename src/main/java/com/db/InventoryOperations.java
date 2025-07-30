package com.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class InventoryOperations {
    @FXML
    private static Connection databaseConnection;
    private Connection con;

    public InventoryOperations(Connection con) {
        this.con = con;
    }

    // get all inventories
    public List<Inventory> getAllInventories() throws SQLException {
        List<Inventory> inventories = new ArrayList<>();

        String query = "SELECT * FROM Inventories";
        try (Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int inventoryItemID = rs.getInt("inventoryItemID");
                String itemName = rs.getString("itemName");
                double quanitiy = rs.getDouble("quantity");
                String expiryDate = rs.getString("expiryDate");
                double cost = rs.getDouble("cost");
                Inventory inventory = new Inventory(inventoryItemID, itemName, quanitiy, expiryDate, cost);
                inventories.add(inventory);
            }
            System.out.println(inventories.toString());
        }
        return inventories;
    }

    // insert a new branch
    public void insertInventory(int inventoryItemID, String itemName, double quanitiy, String expiryDate, double cost)
            throws SQLException {

        String query = "INSERT INTO Inventories (inventoryItemID, itemName, quantity ,expiryDate , cost) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement rs = con.prepareStatement(query);
        System.out.println("expiryDate");

        rs.setInt(1, inventoryItemID);
        rs.setString(2, itemName);
        rs.setDouble(3, quanitiy);
        rs.setString(4, expiryDate);
        rs.setDouble(5, cost);
        rs.executeUpdate();
    }

    // update Branch information
    public void updateInvnetory(int inventoryItemID, String itemName, double quanitiy, String expiryDate, double cost)
            throws SQLException {
        String query = "UPDATE Inventories SET itemName=?, quantity = ?, expiryDate = ?, cost = ? WHERE inventoryItemID = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, itemName);
        pstmt.setDouble(2, quanitiy);
        pstmt.setString(3, expiryDate);
        pstmt.setDouble(4, cost);
        pstmt.setInt(5, inventoryItemID);
        pstmt.executeUpdate();
    }

    // delete a branch by ID
    public void deleteInventroy(int inventoryItemID) throws SQLException {
        String query = "DELETE FROM Inventories WHERE inventoryItemID = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, inventoryItemID);
        pstmt.executeUpdate();
    }
}
