package com.db;

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

public class inventoryCont implements Initializable {

    public static ArrayList<Inventory> itemsQuantitiesArrayList;

    @FXML
    private Text BunsCount;
    @FXML
    private TextField itemIDField;
    @FXML
    private TextField itemNameField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField expiryDateField;
    @FXML
    private TextField CostField;
    @FXML
    private Text totalPrice;
    @FXML
    private Button insertButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Text beefCount;

    @FXML
    private Text chickenCount;

    @FXML
    private Text CheeseCount;

    @FXML
    private Text LettuceCount;

    @FXML
    private Text tomatoCount;

    @FXML
    private Text onionCount;

    @FXML
    private Text pickleCount;

    @FXML
    private Text KetchupCount;

    @FXML
    private Text ranchCount;

    @FXML
    private Text mustardCount;

    @FXML
    private Text sodaCount;

    @FXML
    private Text PaperTowels;
    private List<Button> itemButtons;
    @FXML
    private Text PaperTowelsCount;
    @FXML
    private static Connection databaseConnection;
    static {

        try {
            databaseConnection = new DBConn("127.0.0.1", "3306", "db", "sqluser", "password").connectDB();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button itemBtn1, itemBtn2, itemBtn3, itemBtn4, itemBtn5, itemBtn6, itemBtn7, itemBtn8;
    private ObservableList<Inventory> inventoryList = FXCollections.observableArrayList();

    private final InventoryOperations inventoryoperation = new InventoryOperations(databaseConnection);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            refresh(); // Call the refresh method
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Handle the exceptions
        }
        itemButtons = new ArrayList<>();
        itemButtons.add(itemBtn1);
        itemButtons.add(itemBtn2);
        itemButtons.add(itemBtn3);
        itemButtons.add(itemBtn4);
        itemButtons.add(itemBtn5);
        itemButtons.add(itemBtn6);
        itemButtons.add(itemBtn7);
        itemButtons.add(itemBtn8);
        try {
            getInventories();
        } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();

        }
        System.out.println("hhff");

    }

    @FXML
    void ItemBtns(ActionEvent event) {
        for (Button btn : itemButtons) {
            btn.setVisible(false);
        }
    }

    @FXML
    private void switchToCash(ActionEvent event) throws IOException {
        System.out.println("hhh");
        DB_Main.setRoot("cash");
    }

    @FXML
    private void switchToOrder(ActionEvent event) throws IOException {
        System.out.println("hhh");
        DB_Main.setRoot("Order");
    }

    @FXML
    private void switchToEmployee(ActionEvent event) throws IOException {
        System.out.println("hhh");
        DB_Main.setRoot("imployee");
    }

    @FXML
    private void InsertInventory() {

        System.out.println("Inserting Inventory");
        int inventoryItemID = Integer.parseInt(itemIDField.getText());
        String name = itemNameField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        String expiryDate = expiryDateField.getText();
        int cost = Integer.parseInt(CostField.getText());

        Inventory newInventory = new Inventory(inventoryItemID, name, quantity, expiryDate, cost);
        try {
            inventoryoperation.insertInventory(newInventory.getInventoryItemID(), newInventory.getItemName(),
                    newInventory.getQuantity(),
                    newInventory.getExpiryDate(), newInventory.getCost());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Add the new order to the ObservableList
        inventoryList.add(newInventory);
        try {
            refresh(); // Call the refresh method
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Handle the exceptions
        }

    }

    @FXML
    private void UpdateInventory() {
        System.out.println("Updating Inventoy");
        try {
            int inventoryItemID = Integer.parseInt(itemIDField.getText());
            String name = itemNameField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            String expiryDate = expiryDateField.getText();
            int cost = Integer.parseInt(CostField.getText());

            inventoryoperation.updateInvnetory(inventoryItemID, name, quantity, expiryDate, cost);

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception as needed
        }
        try {
            refresh(); // Call the refresh method
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Handle the exceptions
        }

    }

    @FXML
    private void DeleteInventory() {
        System.out.println("Deleting Inventory");
        try {
            int oid = Integer.parseInt(itemIDField.getText());
            inventoryoperation.deleteInventroy(oid);

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception as needed
        }
        try {
            refresh(); // Call the refresh method
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Handle the exceptions
        }
    }

    private static void getInventories()
            throws SQLException, ClassNotFoundException {

        itemsQuantitiesArrayList = new ArrayList<>();

        String SQL = "SELECT * from inventories ;";
        Connector.con.connectDB();
        Statement stmt = Connector.con.connectDB().createStatement();
        ResultSet rs = stmt.executeQuery(SQL);

        while (rs.next()) {
            itemsQuantitiesArrayList
                    .add(new Inventory(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5)));

        }
        System.out.println("Raghad");
        System.out.println(itemsQuantitiesArrayList.toString());
        rs.close();
        stmt.close();
        Connector.con.connectDB().close();
    }

    public void refresh() throws SQLException, ClassNotFoundException {

        itemsQuantitiesArrayList = new ArrayList<>();

        String SQL = "SELECT * from inventories ;";
        Connector.con.connectDB();
        Statement stmt = Connector.con.connectDB().createStatement();
        ResultSet rs = stmt.executeQuery(SQL);

        while (rs.next()) {
            itemsQuantitiesArrayList
                    .add(new Inventory(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5)));

        }
        for (int i = 0; i < itemsQuantitiesArrayList.size(); i++) {
            System.out.println(itemsQuantitiesArrayList.get(i).getItemName());
            System.out.println(itemsQuantitiesArrayList.get(i).getQuantity());
            String a = itemsQuantitiesArrayList.get(i).getItemName();
            a = a.toLowerCase();

            String str2 = "bun";
            boolean f = true;
            int si;
            if (a.length() >= str2.length())
                si = str2.length();
            else
                si = a.length();
            for (int j = 0; j < si; j++) {
                char char1 = a.charAt(j);
                char char2 = str2.charAt(j);

                if (char1 != char2) {
                    f = false;
                    break;
                }
                if (f) {
                    int n = (int) (itemsQuantitiesArrayList.get(i).getQuantity());
                    BunsCount.setText(String.valueOf(n));
                }
            }

            str2 = "beefmeet";
            f = true;
            if (a.length() >= str2.length())
                si = str2.length();
            else
                si = a.length();
            for (int j = 0; j < si; j++) {
                char char1 = a.charAt(j);
                char char2 = str2.charAt(j);

                if (char1 != char2) {
                    f = false;
                    break;
                }
                if (f) {
                    int n = (int) (itemsQuantitiesArrayList.get(i).getQuantity());
                    beefCount.setText(String.valueOf(n));
                }
            }
            str2 = "cheese";
            f = true;
            if (a.length() >= str2.length())
                si = str2.length();
            else
                si = a.length();
            for (int j = 0; j < si; j++) {
                char char1 = a.charAt(j);
                char char2 = str2.charAt(j);

                if (char1 != char2) {
                    f = false;
                    break;
                }
                if (f) {
                    int n = (int) (itemsQuantitiesArrayList.get(i).getQuantity());
                    CheeseCount.setText(String.valueOf(n));
                }
            }

            str2 = "chicken";
            f = true;
            if (a.length() >= str2.length())
                si = str2.length();
            else
                si = a.length();
            for (int j = 0; j < si; j++) {
                char char1 = a.charAt(j);
                char char2 = str2.charAt(j);

                if (char1 != char2) {
                    f = false;
                    break;
                }
                if (f) {
                    int n = (int) (itemsQuantitiesArrayList.get(i).getQuantity());
                    chickenCount.setText(String.valueOf(n));
                }
            }

            str2 = "cheese";
            f = true;
            if (a.length() >= str2.length())
                si = str2.length();
            else
                si = a.length();
            for (int j = 0; j < si; j++) {
                char char1 = a.charAt(j);
                char char2 = str2.charAt(j);

                if (char1 != char2) {
                    f = false;
                    break;
                }
                if (f) {
                    int n = (int) (itemsQuantitiesArrayList.get(i).getQuantity());
                    CheeseCount.setText(String.valueOf(n));
                }
            }
            str2 = "lettuce";
            f = true;
            if (a.length() >= str2.length())
                si = str2.length();
            else
                si = a.length();
            for (int j = 0; j < si; j++) {
                char char1 = a.charAt(j);
                char char2 = str2.charAt(j);

                if (char1 != char2) {
                    f = false;
                    break;
                }
                if (f) {
                    int n = (int) (itemsQuantitiesArrayList.get(i).getQuantity());
                    LettuceCount.setText(String.valueOf(n));
                }
            }
            str2 = "tomato";
            f = true;
            if (a.length() >= str2.length())
                si = str2.length();
            else
                si = a.length();
            for (int j = 0; j < si; j++) {
                char char1 = a.charAt(j);
                char char2 = str2.charAt(j);

                if (char1 != char2) {
                    f = false;
                    break;
                }
                if (f) {
                    int n = (int) (itemsQuantitiesArrayList.get(i).getQuantity());
                    tomatoCount.setText(String.valueOf(n));
                }
            }
            str2 = "onion";
            f = true;
            if (a.length() >= str2.length())
                si = str2.length();
            else
                si = a.length();
            for (int j = 0; j < si; j++) {
                char char1 = a.charAt(j);
                char char2 = str2.charAt(j);

                if (char1 != char2) {
                    f = false;
                    break;
                }
                if (f) {
                    int n = (int) (itemsQuantitiesArrayList.get(i).getQuantity());
                    onionCount.setText(String.valueOf(n));
                }
            }
            str2 = "pickle";
            f = true;
            if (a.length() >= str2.length())
                si = str2.length();
            else
                si = a.length();
            for (int j = 0; j < si; j++) {
                char char1 = a.charAt(j);
                char char2 = str2.charAt(j);

                if (char1 != char2) {
                    f = false;
                    break;
                }
                if (f) {
                    int n = (int) (itemsQuantitiesArrayList.get(i).getQuantity());
                    pickleCount.setText(String.valueOf(n));
                }
            }
            str2 = "ketchup";
            f = true;
            if (a.length() >= str2.length())
                si = str2.length();
            else
                si = a.length();
            for (int j = 0; j < si; j++) {
                char char1 = a.charAt(j);
                char char2 = str2.charAt(j);

                if (char1 != char2) {
                    f = false;
                    break;
                }
                if (f) {
                    int n = (int) (itemsQuantitiesArrayList.get(i).getQuantity());
                    KetchupCount.setText(String.valueOf(n));
                }
            }
            str2 = "ranch";
            f = true;
            if (a.length() >= str2.length())
                si = str2.length();
            else
                si = a.length();
            for (int j = 0; j < si; j++) {
                char char1 = a.charAt(j);
                char char2 = str2.charAt(j);

                if (char1 != char2) {
                    f = false;
                    break;
                }
                if (f) {
                    int n = (int) (itemsQuantitiesArrayList.get(i).getQuantity());
                    ranchCount.setText(String.valueOf(n));
                }
            }
            str2 = "mustrad";
            f = true;
            if (a.length() >= str2.length())
                si = str2.length();
            else
                si = a.length();
            for (int j = 0; j < si; j++) {
                char char1 = a.charAt(j);
                char char2 = str2.charAt(j);

                if (char1 != char2) {
                    f = false;
                    break;
                }
                if (f) {
                    int n = (int) (itemsQuantitiesArrayList.get(i).getQuantity());
                    mustardCount.setText(String.valueOf(n));
                }
            }

            str2 = "soda";
            f = true;
            if (a.length() >= str2.length())
                si = str2.length();
            else
                si = a.length();
            for (int j = 0; j < si; j++) {
                char char1 = a.charAt(j);
                char char2 = str2.charAt(j);

                if (char1 != char2) {
                    f = false;
                    break;
                }
                if (f) {
                    int n = (int) (itemsQuantitiesArrayList.get(i).getQuantity());
                    sodaCount.setText(String.valueOf(n));
                }
            }

            str2 = "paper";
            f = true;
            if (a.length() >= str2.length())
                si = str2.length();
            else
                si = a.length();
            for (int j = 0; j < si; j++) {
                char char1 = a.charAt(j);
                char char2 = str2.charAt(j);

                if (char1 != char2) {
                    f = false;
                    break;
                }
                if (f) {
                    int n = (int) (itemsQuantitiesArrayList.get(i).getQuantity());
                    PaperTowelsCount.setText(String.valueOf(n));
                }
            }
        }
        System.out.println("Raghad");
        System.out.println(itemsQuantitiesArrayList.toString());
        rs.close();
        stmt.close();
        Connector.con.connectDB().close();
    }

}