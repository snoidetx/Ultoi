package ultoi.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ultoi.gui.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class GuiDriver extends Application {
    @Override
    public void start(Stage stage) {
        Path fileDir = Paths.get(".", "data");
        Path filePath = Paths.get(".", "data", "Ultoi.txt");
        // System.out.println(fileDir.toString());
        // System.out.println(filePath.toString());
        
        try {
            new File(fileDir.toString()).mkdirs();
            new File(filePath.toString()).createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Ultoi ultoi = new Ultoi(filePath);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GuiDriver.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDriver(ultoi);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
