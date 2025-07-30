module com.db {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens com.db to javafx.fxml;
    exports com.db;
}
