package controllers;

import com.alibaba.fastjson.JSON;
import entities.AppUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.JSONObject;
import services.HttpRequestService;
import services.Session;

import java.io.IOException;

/**
 * Created by Rustem.
 */
public class LoginController extends Controller{
    @FXML
    private Label errorLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField loginField;

    @FXML
    private void doLogin() throws IOException {
        String login = loginField.getText();
        String password = passwordField.getText();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("login", login);
        jsonObject.put("password", password);

        String response = HttpRequestService.post("login", jsonObject.toString());

        if (!"".equals(response)) {
            AppUser appUser = JSON.parseObject(response, AppUser.class);
            Session.setCurrentAppUser(appUser);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../main.fxml"));
            Parent root = loader.load();
            MainController mainController = loader.getController();
            mainController.setStage(stage);
            stage.setScene(new Scene(root, 700, 700));
        } else {
            errorLabel.setVisible(true);
        }
    }
}
