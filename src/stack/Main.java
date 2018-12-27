package stack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(getClass().getClassLoader().getResource("stack/view.fxml"))
        );
        primaryStage.setTitle("Stack");
        primaryStage.setScene(new Scene(root, 1080 / 2, 1920 / 2));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
