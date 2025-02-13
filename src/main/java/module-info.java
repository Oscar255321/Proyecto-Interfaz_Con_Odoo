module com.example.interfazconodoo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.base;



    opens com.example.interfazconodoo to javafx.fxml;
    exports com.example.interfazconodoo;
    exports com.example.interfazconodoo.controladores;
    exports com.example.interfazconodoo.dao;
    exports com.example.interfazconodoo.modelos;
    opens com.example.interfazconodoo.dao to javafx.fxml;
    opens com.example.interfazconodoo.modelos to javafx.fxml, javafx.base;
    opens com.example.interfazconodoo.controladores to javafx.fxml, org.testfx;

}