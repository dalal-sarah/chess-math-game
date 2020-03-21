package com.company;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

class frameGL implements GLEventListener, KeyListener {

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();

		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		//LIGHTING         
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT0);
		gl.glEnable(GL2.GL_COLOR_MATERIAL);



		//LIGHT 0
		float[] diffuseLight0 = {1f, 1f, 0f, 0f}; //light diffuse lighting
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuseLight0, 0);

		float[] ambientLight0 = {1f, 1f, 0f, 0f}; //light diffuse lighting
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambientLight0, 0);

		float[] specLight0 = {0f, 1f, 1f, 1f}; //light diffuse lighting
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, specLight0, 0);

		float[] light0_position = {0.1f, -0.1f,0.5f,0.5f};
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, light0_position, 0);


		//material properties
		float[] ambient_and_diffuse = {1f,.8f,0.5f, 1f};
		float[] speculaer = {1f,1f,1f,1f};
		float[] shinning = {25f};


		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, ambient_and_diffuse,0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, speculaer,0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SHININESS, shinning,0);


		level_one(gl);
		pic(gl);

	}

	void pic(GL2 gl)
	{


			        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
			        gl.glLoadIdentity();        // Reset The View

		gl.glTranslatef(0f, 0f, -5.0f);

		gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
		gl.glBegin(GL2.GL_QUADS);

		// Front Face
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);
		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);
		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);
		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);
	}





	void level_one(GL2 gl) {
		//final GL2 gl = glad.getGL().getGL2();

		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3f(0f,1f,1f);
		gl.glVertex3f( -0.8f,0.8f,0);
		gl.glVertex3f(-0.8f,-0.8f,0);
		gl.glVertex3f(0.5f,-0.8f,0);
		gl.glVertex3f(0.5f,0.8f,0);

		gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
		gl.glBegin(GL2.GL_QUADS);

		// Front Face
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);
		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);
		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);
		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);


	}

	@Override
	public void dispose(GLAutoDrawable glad) {
	}
	private int texture;
	@Override

	public void init(GLAutoDrawable glad) {
		final GL2 gl = glad.getGL().getGL2();
		gl.glShadeModel(GL2.GL_SMOOTH);
		gl.glClearColor(1f, 1f,1f,1f);
		gl.glClearDepth(1.0f);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glDepthFunc(GL2.GL_LEQUAL);
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);


		gl.glEnable(GL2.GL_TEXTURE_2D);
		try{
			File im = new File("LadyBug.png ");
			Texture t = TextureIO.newTexture(im, true);
			texture= t.getTextureObject(gl);

		}catch(IOException e){
			e.printStackTrace();
		}

	}



	@Override
	public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {

		//		int xlabel=SOL_6_4.label.getX();
		//		int ylabel=SOL_6_4.label.getY();
		//		SOL_6_4.label.setBounds((int)(xlabel*((double)i2/SOL_6_4.width)),(int)( ylabel*((double)i3/SOL_6_4.hight)), 60, 40);
		//		SOL_6_4.label.setText("Eman");
		//		int xlabel2=SOL_6_4.label2.getX();
		//		int ylabel2=SOL_6_4.label2.getY();
		//		SOL_6_4.label2.setBounds((int)(xlabel2*((double)i2/SOL_6_4.width)),(int)( ylabel2*((double)i3/SOL_6_4.hight)), 60, 40);
		//		SOL_6_4.label2.setText("Eman");
		//		int xlabel3=SOL_6_4.label3.getX();
		//		int ylabel3=SOL_6_4.label3.getY();
		//		SOL_6_4.label3.setBounds((int)(xlabel3*((double)i2/SOL_6_4.width)),(int)( ylabel3*((double)i3/SOL_6_4.hight)), 60, 40);
		//		SOL_6_4.hight=i2;
		//		SOL_6_4.width=i3;

	}

	@Override
	public void keyTyped(KeyEvent keyEvent) {

	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		System.out.println("key");

	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {

	}
}

public class SOL_6_4 extends JPanel{
	//extends Application{
	//	static JLabel label =new JLabel("*");
	//	static JLabel label2 =new JLabel("/");
	//	static JLabel label3 =new JLabel("+");
	static JLabel[][] array =new JLabel [5][5];
	static int hight = 1600;
	static int width = 1600;
	static final GLProfile profile = GLProfile.get(GLProfile.GL2);
	static  GLCapabilities capabilities = new GLCapabilities(profile);
	static  final GLCanvas glcanvas = new GLCanvas(capabilities);

	public static void main(String[] args) {

		//launch(args);

		frameGL l = new frameGL();
		glcanvas.addGLEventListener(l);

		glcanvas.setSize(hight, width);

		//		label.setBounds(100,190,20,15);
		//		label2.setBounds(100,250,20,15);
		//		label3.setBounds(100, 350, 20, 15);

		final JFrame frame = new JFrame("");
		frame.addKeyListener(l);
		int x =300;
		int y=100 ;
		int h =60;
		int w =60 ;
		for(int i=0 ; i<array.length ; i++)
		{

			for(int j=0 ; j<array.length;j++)
			{

				array[i][j]=new JLabel();
				array[i][j].setBounds(x, y, h, w);
				array[i][j].setText(i +" "+ j + "");
				array[i][j].setFont(new Font("Serif", Font.BOLD, 20));
				array[i][j].setForeground(Color.BLACK);

				frame.add(array[i][j]);
				if((j+1)%5==0)
				{
					x+=120;
					y=0;
				}
				y+=100;
				h=60;
				w=60 ;


			}



		}
		array[0][0].setVisible(false);
		array[4][0].setVisible(false);
		array[0][4].setVisible(false);
		array[4][4].setVisible(false);






		frame.getContentPane().add(glcanvas);




		//frame.add(lab);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
	}
	//	@Override
	//	public void start(Stage stage) throws Exception {
	//		try {
	//			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Welcome.fxml"));
	//			Scene scene = new Scene(root);
	//			stage.setScene(scene);
	//			stage.setTitle("Math Chess Game");
	//			stage.show();
	//		} catch(Exception e) {
	//			e.printStackTrace();
	//		}
	//	}

}

