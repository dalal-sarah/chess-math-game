package com.company;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//import javax.media.opengl.glu.GLU;

/**
 *
 * @author 1171055
 */

public class JavaApplication1 extends JPanel  implements GLEventListener , KeyListener, ChangeListener , Initializable {
    @FXML
    private static AnchorPane anchropane;
    /**
     * @param args the command line arguments
     */


    public static DisplayMode dm, dm_old;
    private GLU glu = new GLU();
    private float xrot,yrot,zrot;
    private int texture;
    static final JFrame frame = new JFrame (" Basic Frame");
    public static void main(String[] args) {
        // TODO code application logic here
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        JavaApplication1 r = new JavaApplication1();
        glcanvas.addGLEventListener(r);
        glcanvas.setSize(400, 400);
        final JFrame frame = new JFrame (" Textured Cube");
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);



    }
    public  JavaApplication1(){
        addKeyListener(this);
    }
    @Override
    public void init(GLAutoDrawable glad) {
        final GL2 gl = glad.getGL().getGL2();
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0f, 0f, 0f, 0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

        //
        gl.glEnable(GL2.GL_TEXTURE_2D);
        try{
            File im = new File("75550445_406469086963722_4090110648904581120_n (1).png ");
            Texture t = TextureIO.newTexture(im, true);
            texture= t.getTextureObject(gl);

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void dispose(GLAutoDrawable glad) {

    }
    private float rtri;  //for angle of rotation
    @Override
    public void display(GLAutoDrawable glad ){
        final GL2 gl = glad.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();        // Reset The View

        gl.glTranslatef(0f, 0f, -7.0f);
        gl.glRotatef(xrot, 1.0f, 1.0f, 1.0f);
        gl.glRotatef(yrot, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(zrot, 0.0f, 0.0f, 1.0f);
        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
        gl.glBegin(GL2.GL_QUADS);

        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);




        gl.glEnd();
        gl.glFlush();

        //change the speeds here
        xrot+=.1f;
        yrot+=.1f;
        zrot+=.1f;
    }





    @Override
    public void reshape(GLAutoDrawable glad,  int x, int y,
                        int width, int height) {
        final GL2 gl = glad.getGL().getGL2();
        if(height <=0)
            height =1;
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
    public void drawcir(GL2 glad, float cx, float cy, double r, int alpha) {

        glad.glBegin(GL2.GL_LINE_LOOP);
        for(int ii = 0; ii < alpha; ii++)
        {
            double angle =Math.PI *ii /180 ;
            double x = r * Math.cos(angle);
            double y = r * Math.sin(angle);

            glad.glVertex2f((float)x + cx,(float) y + cy);

        }
        glad.glEnd();
    }

    //@Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Test");

      /*  CustomCanvas canvas = new CustomCanvas();

        AnchorPane.setTopAnchor(canvas, 0d);
        AnchorPane.setLeftAnchor(canvas, 0d);
        AnchorPane.setBottomAnchor(canvas, 0d);
        AnchorPane.setRightAnchor(canvas, 0d);

        AnchorPane root = new AnchorPane();
        root.getChildren().add(canvas);
        root.setPrefWidth(500);
        root.setPrefHeight(500);

        stage.setScene(new Scene(root,500,500));*/
        stage.show();
    }
int x=3;
    int y=3;
    @Override
    public void keyTyped(KeyEvent keyEvent) {
        x++;
        y++;
        System.out.println("x : " +x +" y : "+y);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        x++;
        y++;
        System.out.println("x : " +x +" y : "+y);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        x++;
        y++;
        System.out.println("x : " +x +" y : "+y);
    }

    @Override
    public void changed(ObservableValue observableValue, Object o, Object t1) {
        x++;
        y++;
        System.out.println("x : " +x +" y : "+y);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //frame.addKeyListener();
    }
}