module com.example.layeredarchitecture {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;

    opens lk.sasax.layeredarchitecture to javafx.fxml;
    opens lk.sasax.layeredarchitecture.controller to javafx.fxml;
    opens lk.sasax.layeredarchitecture.tdm to javafx.base;

    exports lk.sasax.layeredarchitecture;
    exports lk.sasax.layeredarchitecture.controller;
}
