package com.db;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee {
  private final IntegerProperty employeeID = new SimpleIntegerProperty();
  private final StringProperty fullName = new SimpleStringProperty();
  private final StringProperty email = new SimpleStringProperty();
  private final StringProperty phone = new SimpleStringProperty();
  private final StringProperty role = new SimpleStringProperty();
  private final StringProperty branch = new SimpleStringProperty();
  private final StringProperty schedule = new SimpleStringProperty();
  private final StringProperty salary = new SimpleStringProperty();
  

  public Employee() {
  }

  public Employee(int employeeID, String fullName, String email, String phone, String role, String branch, String schedule , String salary) {
    this.employeeID.set(employeeID);
    this.fullName.set(fullName);
    this.email.set(email);
    this.phone.set(phone);
    this.role.set(role);
    this.branch.set(branch);
    this.schedule.set(schedule);
    this.salary.set(salary);
  }

  public int getEmployeeId() {
    return employeeID.get();
  }

  public void setEmployeeID(int employeeID) {
    this.employeeID.set(employeeID);
  }

  public String getFullName() {
    return fullName.get();
  }

  public void setFullName(String fullName) {
    this.fullName.set(fullName);
  }
  public String getEmail() {
    return email.get();
  }

  public void setEmail(String email) {
    this.email.set(email);
  }

  public String getPhone() {
    return phone.get();
  }

  public void setPhone(String phone) {
    this.phone.set(phone);
  }

  public String getRole() {
    return role.get();
  }

  public void setRole(String role) {
    this.role.set(role);
  }
  public String getBranch() {
    return branch.get();
  }

  public void setBranch(String branch) {
    this.branch.set(branch);
  }
  public String getSchedule() {
    return schedule.get();
  }

  public void setSchedule(String schedule) {
    this.schedule.set(schedule);
  }
  public String getSalary() {
    return salary.get();
  }

  public void setSalary(String salary) {
    this.salary.set(salary);
  }
  
}
