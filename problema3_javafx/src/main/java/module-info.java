module com.example.problema3_javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires java.sql;
    requires org.apache.logging.log4j;

    opens com.example.problema3_javafx to javafx.fxml;
    exports com.example.problema3_javafx;
    exports model;


}