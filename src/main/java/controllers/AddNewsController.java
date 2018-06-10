package controllers;

import com.alibaba.fastjson.JSON;
import entities.News;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.HttpRequestService;

import java.io.IOException;

/**
 * Created by Rustem.
 */
public class AddNewsController extends Controller{
    @FXML
    private TextArea bodyArea;
    @FXML
    private TextField headerField;
    @FXML
    private Label errorLabel;

    @FXML
    private void createNews() throws IOException {
        String header = headerField.getText();
        String body = bodyArea.getText();
        if (!"".equals(header) && !"".equals(body) && header.length() < 40) {
            News news = new News();
            news.setHeader(header);
            news.setBody(body);
            String json = JSON.toJSONString(news);
            HttpRequestService.post("news/add", json);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../news.fxml"));
            Parent root = loader.load();
            NewsController newsController = loader.getController();
            newsController.setStage(stage);
            newsController.init();
            stage.setScene(new Scene(root, 700, 700));
        } else {
            errorLabel.setVisible(true);
        }
    }

    @Override
    protected void logout(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../main.fxml"));
            Parent root = loader.load();
            MainController mainController = loader.getController();
            mainController.setStage(stage);
            stage.setScene(new Scene(root, 700, 700));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
