module accesoadatos.soundwaveproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.xml.bind;
    requires java.sql;

    opens accesoadatos.soundwaveproject to javafx.fxml;
    exports accesoadatos.soundwaveproject;
}