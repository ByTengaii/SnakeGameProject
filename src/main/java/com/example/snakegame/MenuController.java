package com.example.snakegame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class MenuController{
    /**
     * <p>Opens Game window when clicks Play button.</p>
     * @throws Exception
     */
    @FXML
    public void playPush() throws Exception {
        Main.player.stop();
        Stage stage = new Stage();
        stage.setResizable(false);
        Game game = new Game();
        game.start(stage);
    }

    /**
     * <p>Opens settings window, when click Settings button.</p>
     * @param event
     * @throws Exception
     */
    @FXML
    public void settingPush(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Scene settingScene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(settingScene);
        stage.getIcons().add(new Image(System.getProperty("user.dir")+"\\src\\main\\resources\\com\\example\\snakegame\\img\\about.png"));
        stage.show();
    }

    /**
     * <p>This method close Menu window</p>
     * @throws IOException
     */
    @FXML
    public void exitPush()throws IOException {
        System.exit(0);
    }
}
