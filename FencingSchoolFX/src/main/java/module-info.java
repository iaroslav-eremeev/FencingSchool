module com.iaroslaveremeev.fencingschoolfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.iaroslaveremeev.fencingschoolfx to javafx.fxml;
    exports com.iaroslaveremeev.fencingschoolfx;
    exports com.iaroslaveremeev.fencingschoolfx.controllers;
    opens com.iaroslaveremeev.fencingschoolfx.controllers to javafx.fxml;
}