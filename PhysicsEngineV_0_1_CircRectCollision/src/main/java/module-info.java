module com.example.physicsenginev_0_1 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.physicsenginev_0_1 to javafx.fxml;
    exports com.example.physicsenginev_0_1;
}