package controllers;

import com.alibaba.fastjson.JSON;
import entities.News;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import services.HttpRequestService;
import services.Session;

import java.io.IOException;
import java.util.List;

/**
 * Created by Rustem.
 */
public class NewsController extends Controller{
    public void init() throws IOException {
        String response = HttpRequestService.get("news");
        if (!"".equals(response)) {
            List<News> newsList= JSON.parseArray(response, News.class);

            VBox bodyBox = new VBox();
            bodyBox.setSpacing(20);
            bodyBox.setMaxWidth(670);
            for(News news: newsList) {
                VBox newsBox = new VBox();
                newsBox.setSpacing(5);
                newsBox.setPrefWidth(670);

                Label header = new Label(news.getHeader());
                header.setFont(new Font("Arial", 24));
                header.setTextFill(Color.web("#7c7272"));
                header.setWrapText(true);
                header.setPrefWidth(670);

                Label body = new Label(news.getBody());
                body.setFont(new Font("Arial", 18));
                body.setPrefWidth(670);
                body.setWrapText(true);

                Label date = new Label(news.getDate().toString());
                date.setFont(new Font("Arial", 14));
                date.setTextFill(Color.web("#cd8e36"));

                newsBox.getChildren().addAll(header, body, date);
                if (Session.hasCurrentAppUserEditPermissions()) {
                    HBox buttonsBox = new HBox();
                    buttonsBox.setSpacing(20);
                    Button changeNews = new Button("Изменить");
                    Button deleteNews = new Button("Удалить");
                    changeNews.setOnAction(x -> changeNews(news.getId()));
                    deleteNews.setOnAction(x -> deleteNews(news.getId()));
                    buttonsBox.getChildren().addAll(changeNews, deleteNews);
                    newsBox.getChildren().addAll(buttonsBox);
                }
                bodyBox.getChildren().addAll(newsBox);
            }
            body.getChildren().addAll(bodyBox);
        }
    }

    private void changeNews(int id) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../update_news.fxml"));
            Parent root = loader.load();
            UpdateNewsController updateNewsController = loader.getController();
            updateNewsController.setStage(stage);
            updateNewsController.init(id);
            stage.setScene(new Scene(root, 700, 700));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteNews(int id) {
        try {
            HttpRequestService.post("news/" + id + "/delete", "");
            body.getChildren().clear();
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
