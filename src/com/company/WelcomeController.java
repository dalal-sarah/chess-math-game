package com.company;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController{

	@FXML
	private Button statrtBtn;

	@FXML
	private void startBtnAction(MouseEvent event) throws IOException {
		
		Parent parent = FXMLLoader.load(getClass().getResource("Difficulty.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("Math Chess Game");
        stage.setScene(scene);
        stage.show();


	}

	public void start(ActionEvent actionEvent) {

	}
}
