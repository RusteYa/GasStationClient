import controllers.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        GridPane root = loader.load();
        this.controller = loader.getController();
        controller.setStage(primaryStage);
        primaryStage.setScene(new Scene(root, 700, 700));
        primaryStage.setTitle("ITIS oil client");
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
