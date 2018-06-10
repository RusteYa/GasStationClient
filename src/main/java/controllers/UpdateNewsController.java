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
public class UpdateNewsController extends Controller{
    @FXML
    private Label errorLabel;
    @FXML
    private TextField headerField;
    @FXML
    private TextArea bodyArea;

    private int newsId;
    private News news;

    public void init(int newsId) throws IOException {
        this.newsId = newsId;
        String response = HttpRequestService.get("news/" + newsId);
        news = JSON.parseObject(response, News.class);
        headerField.setText(news.getHeader());
        bodyArea.setText(news.getBody());
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

    @FXML
    private void changeNews() throws IOException {
        String header = headerField.getText();
        String body = bodyArea.getText();
        if (!"".equals(header) && !"".equals(body) && header.length() < 40) {
            news.setHeader(header);
            news.setBody(body);
            String json = JSON.toJSONString(news);
            HttpRequestService.post("news/" + newsId, json);

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
}
