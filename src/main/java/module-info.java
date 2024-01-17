module com.example.base_destroyers {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
//    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens base_destroyer to javafx.fxml;
    exports base_destroyer;
    exports base_destroyer.map;
    opens base_destroyer.map to javafx.fxml;
}