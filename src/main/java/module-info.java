module com.example.basedestroyers {
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

    opens com.example.basedestroyers to javafx.fxml;
    exports com.example.basedestroyers;
    exports com.example.basedestroyers.AI;
    opens com.example.basedestroyers.AI to javafx.fxml;
}