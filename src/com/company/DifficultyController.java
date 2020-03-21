package com.company;

import com.jogamp.opengl.awt.GLCanvas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class DifficultyController {

    @FXML
    private Button easy;
    @FXML
    private Button mediumBtn;
    @FXML
    private Button hardBtn;

    int x=400;
    int y=100;
    @FXML
    void easyBtnAction(MouseEvent event) throws IOException {
        System.out.println("easy");

//         final GLProfile profile =Main.profile;
//         GLCapabilities capabilities = Main.capabilities;
         final GLCanvas glcanvas = Main.glcanvas;
         Game.n=5;
        Esay b = new Esay();
      //  b. DrawScene(5,400,80,3000,2700,60,60);
        //Main.DrawSceen(Game.n,100,100,50);
//        glcanvas.addGLEventListener(b);
//        glcanvas.setSize(Main.width, Main.hight);
//        glcanvas.setBackground(Color.yellow);
//
//        JFrame frame = new JFrame();
//        frame.setTitle("Sketch");
//        frame.getContentPane().add(glcanvas);
//        frame.setSize(frame.getContentPane().getPreferredSize());
//        frame.setLocation(600,0);
//        frame.addKeyListener(b);
//        frame.setVisible(true);
//    	Parent parent = FXMLLoader.load(getClass().getResource("Difficulty.fxml"));
//        Scene scene = new Scene(parent);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//		stage.setTitle("Math Chess Game");
//        stage.setScene(scene);
//        stage.show();
    }

    @FXML
    void hardBtnAction(MouseEvent event) throws IOException {
        System.out.println("hard");
        final GLCanvas glcanvas = Main.glcanvas;
        Game.n=9;
        Hard b = new Hard();
      //  b. DrawScene(5,x,y,3000,2700,60,60);
    }

    @FXML
    void mediumBtnAction(MouseEvent event) throws IOException {
        System.out.println("hard");
        final GLCanvas glcanvas = Main.glcanvas;
        Game.n=7;
        Med b = new Med();
      //  b. DrawScene(5,x,y,3000,2700,60,60);
    }

}
