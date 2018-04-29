
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

import static com.sun.org.apache.bcel.internal.util.SecuritySupport.getResourceAsStream;

public class test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("laboratory work");
        stage.getIcons().add(new Image("icon.jpg"));
        Parent root = FXMLLoader.load(getClass().getResource("/Fxml/lab1/design.fxml"));

        Scene otherScene = new Scene(root,800,650);
        otherScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setResizable(false);
        stage.setScene(otherScene);

        stage.show();
    }
}
