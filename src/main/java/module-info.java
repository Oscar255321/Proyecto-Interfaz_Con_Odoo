module com.example.interfazconodoo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.interfazconodoo to javafx.fxml;
    exports com.example.interfazconodoo;
    exports com.example.interfazconodoo.Controladores;
    exports com.example.interfazconodoo.DAO;
    exports com.example.interfazconodoo.Modelos;
    opens com.example.interfazconodoo.Controladores to javafx.fxml;
    opens com.example.interfazconodoo.DAO to javafx.fxml;
    opens com.example.interfazconodoo.Modelos to javafx.fxml;
}