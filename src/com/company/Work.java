package com.company;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

import javax.swing.*;
import java.awt.*;
public class Work extends JPanel implements GLEventListener {

    public void paint(Graphics g){

        g.setColor(Color.red);
        g.fillRect(100, 100, 400, 400);
        for(int i = 100; i <= 400; i+=100){
            for(int j = 100; j <= 400; j+=100){
                g.setColor(Color.getHSBColor(i*j/360,1,1));
                g.clearRect(i, j, 50, 50);
            }
        }

        for(int i = 150; i <= 450; i+=100){
            for(int j = 150; j <= 450; j+=100){
                g.clearRect(i, j, 50, 50);
            }
        }
    }
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setSize(600,600);
        frame.getContentPane().add(new Work());
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {

        for (int i = 0; i < 4; i++) {
            final GL2 gl = glAutoDrawable.getGL().getGL2();
            gl.glBegin(GL2.GL_QUADS);
            gl.glVertex3f(.5f, 0.5f, 0);
            gl.glVertex3f(-0.5f, 0.5f, 0);
            gl.glVertex3f(-0.5f, -0.5f, 0);
            gl.glVertex3f(0.5f, -0.5f, 0);
            gl.glEnd();


        }
    }
    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }
}