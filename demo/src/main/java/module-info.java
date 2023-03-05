module asteroid_app.initial {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens asteroid_app.initial to javafx.fxml;
    exports asteroid_app.initial;
}
