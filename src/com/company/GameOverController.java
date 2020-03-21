package com.company;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class GameOverController {

    @FXML
    private Button homeBtn;

    @FXML
    void homeBtnAction(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("Math Chess Game");
        stage.setScene(scene);
        stage.show();
    }
}
