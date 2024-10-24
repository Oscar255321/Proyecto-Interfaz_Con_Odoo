module com.example.interfazconodoo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.interfazconodoo to javafx.fxml;
    exports com.example.interfazconodoo;
    exports com.example.interfazconodoo.Controladores;
    opens com.example.interfazconodoo.Controladores to javafx.fxml;
}