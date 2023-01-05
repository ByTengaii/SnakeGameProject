package com.example.snakegame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController extends Main{
    /**
     *
     * @throws Exception
     */
    @FXML
    public void playPush() throws Exception {
        Main.player.stop();
        Stage stage = new Stage();
        Game game = new Game();
        game.start(stage);
    }

    /**
     *
     * @param event
     * @throws Exception
     */
    @FXML
    public void settingPush(ActionEvent event)throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Scene settingScene = new Scene(root);
        //Stage stage = new Stage();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(settingScene);
        stage.getIcons().add(new Image(System.getProperty("user.dir")+"\\src\\main\\resources\\com\\example\\snakegame\\img\\about.png"));
        stage.show();
    }

    /**
     * <p>This method close game</p>
     * @throws IOException
     */
    @FXML
    public void exitPush()throws IOException {
        System.exit(0);
    }
}
