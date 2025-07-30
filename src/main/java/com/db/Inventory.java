package com.db;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Inventory {
    private final IntegerProperty inventoryItemID = new SimpleIntegerProperty();
    private final StringProperty itemName = new SimpleStringProperty();
    private final DoubleProperty quantity = new SimpleDoubleProperty();
    private final StringProperty expiryDate = new SimpleStringProperty();
    private final DoubleProperty cost = new SimpleDoubleProperty();

    public Inventory() {
    }

    public Inventory(int inventoryID, String ItemName, double quantity, String expiryDate, double cost) {
        this.inventoryItemID.set(inventoryID);
        this.itemName.set(ItemName);
        this.quantity.set(quantity);
        this.expiryDate.set(expiryDate);
        this.cost.set(cost);
    }

    public int getInventoryItemID() {
        return inventoryItemID.get();
    }

    public IntegerProperty getDoubleInventoryItemID() {
        return inventoryItemID;
    }

    public void setInventoryItemID(int id) {
        this.inventoryItemID.set(id);
    }

    public String getItemName() {
        return itemName.get();
    }

    public StringProperty getItemNameProperty() {
        return itemName;
    }

    public void setItemNameDate(String Name) {
        this.itemName.set(Name);
    }

    public double getQuantity() {
        return quantity.get();
    }

    public DoubleProperty getQuantityProperty() {
        return quantity;
    }

    public void setQuantity(double quant) {
        this.quantity.set(quant);
    }

    public String getExpiryDate() {
        return expiryDate.get();
    }

    public StringProperty getExpiryDateProperty() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate.set(expiryDate);
    }

    public double getCost() {
        return cost.get();
    }

    public DoubleProperty getCostProperty() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost.set(cost);
    }
}
