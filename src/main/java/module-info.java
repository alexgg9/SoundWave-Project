module SoundWaveProject {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.xml.bind;

    opens accesoadatos.soundwaveproject.SQLConnection to java.xml.bind;
    exports accesoadatos.soundwaveproject;
    exports accesoadatos.soundwaveproject.testsDAO;
}