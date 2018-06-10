package controllers;

import entities.AppUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.Session;

import java.io.IOException;

/**
 * Created by Rustem.
 */
public abstract class Controller {
    protected Stage stage;

    @FXML
    protected AnchorPane body;
    @FXML
    protected MenuItem loginMenuItem;
    @FXML
    protected MenuItem logoutMenuItem;
    @FXML
    protected MenuItem addNewsMenu;
    @FXML
    protected Label currentUsernameLabel;
    @FXML
    protected Label pageNameLabel;

    @FXML
    public void initialize() {
        AppUser appUser = Session.getCurrentAppUser();
        if (appUser != null) {
            loginMenuItem.setVisible(false);
            currentUsernameLabel.setVisible(true);
            logoutMenuItem.setVisible(true);
            currentUsernameLabel.setText(appUser.getName());
            if (Session.hasCurrentAppUserEditPermissions()) {
                addNewsMenu.setVisible(true);
            } else {
                addNewsMenu.setVisible(false);
            }
        } else {
            loginMenuItem.setVisible(true);
            currentUsernameLabel.setVisible(false);
            logoutMenuItem.setVisible(false);
            addNewsMenu.setVisible(false);
        }
    }

    @FXML
    protected void news() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../news.fxml"));
        Parent root = loader.load();
        NewsController newsController = loader.getController();
        newsController.setStage(stage);
        newsController.init();
        stage.setScene(new Scene(root, 700, 700));
    }

    @FXML
    protected void main() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../main.fxml"));
        Parent root = loader.load();
        MainController mainController = loader.getController();
        mainController.setStage(stage);
        stage.setScene(new Scene(root, 700, 700));
    }

    @FXML
    protected void addNews() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../add_news.fxml"));
        Parent root = loader.load();
        AddNewsController addNewsController = loader.getController();
        addNewsController.setStage(stage);
        stage.setScene(new Scene(root, 700, 700));
    }

    @FXML
    public void login() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../login.fxml"));
        Parent root = loader.load();
        LoginController loginController = loader.getController();
        loginController.setStage(stage);
        stage.setScene(new Scene(root, 700, 700));
    }

    @FXML
    protected void logout() {
        Session.setCurrentAppUser(null);
        loginMenuItem.setVisible(true);
        currentUsernameLabel.setVisible(false);
        logoutMenuItem.setVisible(false);
        addNewsMenu.setVisible(false);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
