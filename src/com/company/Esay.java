package com.company;


import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;

import static com.jogamp.opengl.GL.GL_LINE_LOOP;
import static com.jogamp.opengl.GL.GL_TRIANGLE_FAN;

public class Esay extends JPanel   implements KeyListener , GLEventListener {
    int x=3;
    int y=3;
    int oldx=3;
    int oldy=3;
    Game g;
    int numOfpress=0;
    ArrayList<String> playerEquation = new ArrayList();
    ArrayList<Integer>  playerPath0 = new ArrayList();
    ArrayList<Integer>  playerPath1 = new ArrayList();
    static JLabel label =new JLabel("*");
    static JLabel label2 =new JLabel("/");
    static JLabel label3 =new JLabel("+");
    static  int width=1500 ;
    static  int hight=1500;
    static final GLProfile profile = GLProfile.get(GLProfile.GL2);
    static  GLCapabilities capabilities = new GLCapabilities(profile);
    static final GLCanvas glcanvas = new GLCanvas(capabilities);;
    static JFrame frame = new JFrame();
    static JLabel[][] array =new JLabel [Game.n][Game.n];
    static JLabel column_label = new JLabel("New label");

    public Esay() {
        System.out.println("cons");
        this.addKeyListener(this);
        g=new Game();
        g.n=5;
        g.printGameTable();
        glcanvas.addGLEventListener(this);
        glcanvas.setSize(width, hight);
        glcanvas.setBackground(Color.yellow);
        int n;
        DrawScene(5,400,80,12000,12000,60,60);
        column_label.setIcon(new ImageIcon("ladyBug.jpg "));
        column_label.setBounds(630,300,60,60);
        // column_label.setLocation(width/2,hight/2);
        column_label.setBackground(Color.red);
        column_label.setText("player");
        frame.add(column_label);
        JLabel result=new JLabel(""+g.calculateResult());
        result.setBounds(10,10,50,50);
        frame.add(result);
        frame.setTitle("Sketch");
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setLocation(0,0);
        frame.addKeyListener(this);
        //DrawSceen(Game.n);
        frame.setVisible(true);

    }
    //    public static void main(String[] args) throws InterruptedException, IOException {
//
//        Main b = new Main();
//
////        Timer timer = new Timer();
////        TimerTask task = new Helper();
////
////        //instance of date object for fixed-rate execution
////        Date date = new Date();
////
////        timer.scheduleAtFixedRate(task, date, 5000);
////
////        System.out.println("Timer running");
////        synchronized(b)
////        {
////            //make the main thread wait
////            b.wait();
////
////            //once timer has scheduled the task 4 times,
////            //main thread resumes
////            //and terminates the timer
////            timer.cancel();
////
////            //purge is used to remove all cancelled
////            //tasks from the timer'stak queue
////            System.out.println(timer.purge());
////        }
//
//        glcanvas.addGLEventListener(b);
//        glcanvas.setSize(width, hight);
//        glcanvas.setBackground(Color.yellow);
//
//
//        BufferedImage bi = ImageIO.read(new File("ladyBug.jpg "));
//        Graphics2D gr = (Graphics2D)bi.getGraphics();
//        gr.rotate(Math.toRadians(45),26,26);
//        gr.drawImage(bi, 0, 0, null);
//        column_label.setIcon(new ImageIcon(bi));
//        column_label.setBounds(100,100,60,60);
//        column_label.setLocation(width/2,hight/2);
//        column_label.setBackground(Color.red);
//        column_label.setText("player");
//        frame.add(column_label);
//        JLabel l;
//
//        int n=Game.n;
////        int x=400 ;
////        int y= 100 ;
////
////
////
////        for(int i=0 ; i< n ;i++){
////            for(int j=0 ; j < n ;j++){
////
////                l=new JLabel();
////                l.setBounds(x ,y, 60, 60);
////                l.setText(" "+ g.gameArray[i][j] + "");
////                l.setFont(new Font("Serif", Font.BOLD, 20));
////
////                l.setForeground(Color.BLACK);
////                l.setBackground(Color.WHITE);
////                l.repaint();
////                l.setOpaque(true);
////                //l.setBackground(Color.BLACK);
////
////                frame.add(l);
////                if((j+1)%5==0)
////                {
////                    x+=120;
////                    y=0;
////                }
////                y+=100;
////
////
////
////
////            }
////        }
//
//
////        label.setBounds(100,100,20,20);
////        label2.setBounds(240,250,20,20);
////        label3.setBounds(400, 400, 20, 20);
//
//       // DrawSceen(Game.n,110,100,10);
////        JLabel result=new JLabel(""+g.calculateResult());
////        result.setBounds(10,10,50,50);
////        frame.add(result);
//        frame.setTitle("Sketch");
//        frame.getContentPane().add(glcanvas);
//        frame.setSize(frame.getContentPane().getPreferredSize());
//        frame.setLocation(0,0);
//        frame.addKeyListener(b);
//        //DrawSceen(Game.n);
//        frame.setVisible(true);
//    }
    public void calculateEquation(){
        playerEquation.clear();
        for(int i=0;i<playerPath0.size();i++){
            //   System.out.println(game[path[i][0]][path[i][1]]);
            playerEquation.add(g.gameArray[playerPath0.get(i)][playerPath1.get(i)]);
        }
    }
    public int calculateResult(){
        int result=Integer.parseInt(playerEquation.get(0));
        for(int i=1 ;i < playerEquation.size();i++){
            if(playerEquation.get(i).equals("+")){
                result+=Integer.parseInt(playerEquation.get(++i));
            }else if(playerEquation.get(i).equals("-")){
                result-=Integer.parseInt(playerEquation.get(++i));
            }else if(playerEquation.get(i).equals("*")){
                result*=Integer.parseInt(playerEquation.get(++i));
            }else if(playerEquation.get(i).equals("/")){
                result/=Integer.parseInt(playerEquation.get(++i));
            }else if(playerEquation.get(i).equals("%")){
                result%=Integer.parseInt(playerEquation.get(++i));
            }else if(playerEquation.get(i).equals("^")){
                result=(int)Math.pow(result,Integer.parseInt(playerEquation.get(++i)));
            }

        }
        return result;
    }
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Line2D lin = new Line2D.Float(100, 100, 250, 260);
        g2.draw(lin);
    }
    public   void DrawSceen(int n ,  int x , int y , int w){

        JLabel l;

        for(int i=0 ; i< n ;i++){
            for(int j=0 ; j < n ;j++){

                l=new JLabel();
                l.setBounds(i*x +w, j*y+w, 60, 60);
                l.setText(" "+ g.gameArray[i][j] + "");
                l.setFont(new Font("Serif", Font.BOLD, 20));
//               l.setForeground(Color.BLACK);
//               l.setBackground(Color.YELLOW);
//               l.repaint();
                l.setOpaque(false);
                l.setBackground(Color.BLACK);

                frame.add(l);



            }
        }
    }
    public   void DrawScene(int n ,  int x , int y , int width , int hight , int lw , int lh){
        n=g.gameArray.length;
        JLabel l;
        int w=width/lw/(n-1);
        for(int i=x ,ii=0; i< width+x&&ii<n ;i+=lw,ii++){
            for(int j=y ,jj=0; j < hight+y&&jj<n ;j+=lh,jj++){

                l=new JLabel();
                l.setBounds(i, j, lw, lh);
                l.setText(" "+ g.gameArray[ii][jj] + "");
                l.setOpaque(false);
                l.setBackground(Color.BLACK);

                frame.add(l);

                j+=w;
            }
            i+=w;
        }
    }
    void drawInsect(GL2 gl , float x , float y){
        gl.glColor3f(1,0,0);
        drawCircle( gl, x , y,.1f );
        gl.glColor3f(0,0,0);
        drawCircle( gl, x+2 , y+2,.01f );
        drawCircle( gl, x-2 , y+2,.01f );
        drawCircle( gl, x+2 , y+2,.01f );
        drawCircle( gl, x-2 , y-2,.01f );
        gl.glBegin (GL2.GL_LINES);//static field
        gl.glVertex3f(x,y+.1f,0);
        gl.glVertex3f(x,y-.1f,0);
        gl.glEnd();




    }
    void DrawEllipse(float radiusX, float radiusY, GL2 gl)
    {
        float  DEG2RAD = 3.14159f/180.0f;
        int i;

        gl.glBegin(GL_LINE_LOOP);

        for(i=0;i<360;i++)
        {
            float rad = i*DEG2RAD;
            gl.glVertex2f((float)Math.cos(rad)*radiusX, (float) ((float)radiusY * Math.sin(rad)));
        }

        gl.glEnd();
    }
    void drawSphere(double r, int lats, int longs, GL2 gl) {
        int i, j;
        for (i = 0; i <= lats; i++) {
            double lat0 = Math.PI * (-0.5 + (double) (i - 1) / lats);
            double z0 = Math.sin(lat0);
            double zr0 = Math.cos(lat0);

            double lat1 = Math.PI * (-0.5 + (double) i / lats);
            double z1 = Math.sin(lat1);
            double zr1 = Math.cos(lat1);

            gl.glBegin(gl.GL_QUAD_STRIP);
            for (j = 0; j <= longs; j++) {
                double lng = 2 * Math.PI * (double) (j - 1) / longs;
                double x = Math.cos(lng);
                double y = Math.sin(lng);

                gl.glNormal3d(x * zr0, y * zr0, z0);
                gl.glVertex3d(x * zr0, y * zr0, z0);
                gl.glNormal3d(x * zr1, y * zr1, z1);
                gl.glVertex3d(x * zr1, y * zr1, z1);
            }
            gl.glEnd();
        }
    }
    void drawCircle( GL2 gl,float x1 ,float y1,float radius){
        float x2,y2;
        float angle;

        gl.glBegin(GL_TRIANGLE_FAN);
        gl.glVertex2f(x1,y1);
        gl.glColor3f(1f,1.0f,0.0f);;
        for (angle=1.0f;angle<361.0f;angle+=0.2)
        {
            x2 = (float) (x1+Math.sin((float)angle)*radius);
            y2 = (float) (y1+Math.cos((float)angle)*radius);
            gl.glVertex2f(x2,y2);
        }

        gl.glEnd();

    }
    void level_one2(GL2 gl) {
        float p=.1f ;
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0.3984f,0.19921f,0f);
        gl.glVertex3f( -0.5f,0.8f,0);

        gl.glVertex3f(0.5f,0.8f,0);
        gl.glVertex3f(0.5f,-.8f,0);
        gl.glVertex3f(-0.5f,-0.8f,0);

        gl.glEnd();

    }
    void level_one(GL2 gl) {
        float p=.1f ;
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,0f,1f);
        gl.glVertex3f( -.4f-p,0.6f+p,0);
        gl.glVertex3f(-0.6f-p,0.6f+p,0);
        gl.glVertex3f(-0.6f-p,0.4f+p,0);
        gl.glVertex3f(-0.4f-p,0.4f+p,0);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,1f,1f);
        gl.glVertex3f( -.1f-p,0.6f+p,0);
        gl.glVertex3f(-0.3f-p,0.6f+p,0);
        gl.glVertex3f(-0.3f-p,0.4f+p,0);
        gl.glVertex3f(-0.1f-p,0.4f+p,0);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,0f,1f);
        gl.glVertex3f( .2f-p,0.6f+p,0);
        gl.glVertex3f(0.0f-p,0.6f+p,0);
        gl.glVertex3f(0.0f-p,0.4f+p,0);
        gl.glVertex3f(0.2f-p,0.4f+p,0);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,1f,1f);
        gl.glVertex3f( .5f-p,0.6f+p,0);
        gl.glVertex3f(0.3f-p,0.6f+p,0);
        gl.glVertex3f(0.3f-p,0.4f+p,0);
        gl.glVertex3f(0.5f-p,0.4f+p,0);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,0f,1f);
        gl.glVertex3f( .8f-p,0.6f+p,0);
        gl.glVertex3f(0.6f-p,0.6f+p,0);
        gl.glVertex3f(0.6f-p,0.4f+p,0);
        gl.glVertex3f(0.8f-p,0.4f+p,0);
        gl.glEnd();

        /////////////

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,1f,1f);
        gl.glVertex3f( -.4f-p,0.3f+p,0);
        gl.glVertex3f(-0.6f-p,0.3f+p,0);
        gl.glVertex3f(-0.6f-p,0.1f+p,0);
        gl.glVertex3f(-0.4f-p,0.1f+p,0);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,0f,1f);
        gl.glVertex3f( -.1f-p,0.3f+p,0);
        gl.glVertex3f(-0.3f-p,0.3f+p,0);
        gl.glVertex3f(-0.3f-p,0.1f+p,0);
        gl.glVertex3f(-0.1f-p,0.1f+p,0);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,1f,1f);
        gl.glVertex3f( .2f-p,0.3f+p,0);
        gl.glVertex3f(0.0f-p,0.3f+p,0);
        gl.glVertex3f(0.0f-p,0.1f+p,0);
        gl.glVertex3f(0.2f-p,0.1f+p,0);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,0f,1f);
        gl.glVertex3f( .5f-p,0.3f+p,0);
        gl.glVertex3f(0.3f-p,0.3f+p,0);
        gl.glVertex3f(0.3f-p,0.1f+p,0);
        gl.glVertex3f(0.5f-p,0.1f+p,0);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,1f,1f);
        gl.glVertex3f( .8f-p,0.3f+p,0);
        gl.glVertex3f(0.6f-p,0.3f+p,0);
        gl.glVertex3f(0.6f-p,0.1f+p,0);
        gl.glVertex3f(0.8f-p,0.1f+p,0);
        gl.glEnd();

        /////////
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,0f,1f);
        gl.glVertex3f( -.4f-p,0.0f+p,0);
        gl.glVertex3f(-0.6f-p,0.0f+p,0);
        gl.glVertex3f(-0.6f-p,-0.2f+p,0);
        gl.glVertex3f(-0.4f-p,-0.2f+p,0);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,1f,1f);
        gl.glVertex3f( -.1f-p,0.0f+p,0);
        gl.glVertex3f(-0.3f-p,0.0f+p,0);
        gl.glVertex3f(-0.3f-p,-0.2f+p,0);
        gl.glVertex3f(-0.1f-p,-0.2f+p,0);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,0f,1f);
        gl.glVertex3f( .2f-p,0.0f+p,0);
        gl.glVertex3f(0.0f-p,0.0f+p,0);
        gl.glVertex3f(0.0f-p,-0.2f+p,0);
        gl.glVertex3f(0.2f-p,-0.2f+p,0);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f-p,1f,1f);
        gl.glVertex3f( .5f-p,0.0f+p,0);
        gl.glVertex3f(0.3f-p,0.0f+p,0);
        gl.glVertex3f(0.3f-p,-0.2f+p,0);
        gl.glVertex3f(0.5f-p,-0.2f+p,0);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,0f,1f);
        gl.glVertex3f( .8f-p,0.0f+p,0);
        gl.glVertex3f(0.6f-p,0.0f+p,0);
        gl.glVertex3f(0.6f-p,-0.2f+p,0);
        gl.glVertex3f(0.8f-p,-0.2f+p,0);
        gl.glEnd();

        /////////

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,1f,1f);
        gl.glVertex3f( -.4f-p,-0.3f+p,0);
        gl.glVertex3f(-0.6f-p,-0.3f+p,0);
        gl.glVertex3f(-0.6f-p,-0.5f+p,0);
        gl.glVertex3f(-0.4f-p,-0.5f+p,0);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,0f,1f);
        gl.glVertex3f( -.1f-p,-0.3f+p,0);
        gl.glVertex3f(-0.3f-p,-0.3f+p,0);
        gl.glVertex3f(-0.3f-p,-0.5f+p,0);
        gl.glVertex3f(-0.1f-p,-0.5f+p,0);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,1f,1f);
        gl.glVertex3f( .2f-p,-0.3f+p,0);
        gl.glVertex3f(0.0f-p,-0.3f+p,0);
        gl.glVertex3f(0.0f-p,-0.5f+p,0);
        gl.glVertex3f(0.2f-p,-0.5f+p,0);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,0f,1f);
        gl.glVertex3f( .5f-p,-0.3f+p,0);
        gl.glVertex3f(0.3f-p,-0.3f+p,0);
        gl.glVertex3f(0.3f-p,-0.5f+p,0);
        gl.glVertex3f(0.5f-p,-0.5f+p,0);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,1f,1f);
        gl.glVertex3f( .8f-p,-0.3f+p,0);
        gl.glVertex3f(0.6f-p,-0.3f+p,0);
        gl.glVertex3f(0.6f-p,-0.5f+p,0);
        gl.glVertex3f(0.8f-p,-0.5f+p,0);
        gl.glEnd();

        ///////////

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,0f,1f);
        gl.glVertex3f( -.4f-p,-0.6f+p,0);
        gl.glVertex3f(-0.6f-p,-0.6f+p,0);
        gl.glVertex3f(-0.6f-p,-0.8f+p,0);
        gl.glVertex3f(-0.4f-p,-0.8f+p,0);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,1f,1f);
        gl.glVertex3f( -.1f-p,-0.6f+p,0);
        gl.glVertex3f(-0.3f-p,-0.6f+p,0);
        gl.glVertex3f(-0.3f-p,-0.8f+p,0);
        gl.glVertex3f(-0.1f-p,-0.8f+p,0);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,0f,1f);
        gl.glVertex3f( .2f-p,-0.6f+p,0);
        gl.glVertex3f(0.0f-p,-0.6f+p,0);
        gl.glVertex3f(0.0f-p,-0.8f+p,0);
        gl.glVertex3f(0.2f-p,-0.8f+p,0);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,1f,1f);
        gl.glVertex3f( .5f-p,-0.6f+p,0);
        gl.glVertex3f(0.3f-p,-0.6f+p,0);
        gl.glVertex3f(0.3f-p,-0.8f+p,0);
        gl.glVertex3f(0.5f-p,-0.8f+p,0);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0f,0f,1f);
        gl.glVertex3f( .8f-p,-0.6f+p,0);
        gl.glVertex3f(0.6f-p,-0.6f+p,0);
        gl.glVertex3f(0.6f-p,-0.8f+p,0);
        gl.glVertex3f(0.8f-p,-0.8f+p,0);
        gl.glEnd();


    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();

        gl.glClearColor(0f, 0.597f,0f,1f);

    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    private float rtri;
    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        //LIGHTING
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_COLOR_MATERIAL);



        //LIGHT 0
        float[] diffuseLight0 = {1f, 1f, 1f, 1f}; //light diffuse lighting
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuseLight0, 0);

        float[] ambientLight0 = {1f, 1f, 1f, 0f}; //light diffuse lighting
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambientLight0, 0);

        float[] specLight0 = {1f, 1f, 1f, 1f}; //light diffuse lighting
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, specLight0, 0);

        float[] light0_position = {0.5f, -0.5f,0.5f,0.5f};
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, light0_position, 0);


        //material properties
        float[] ambient_and_diffuse = {1f,.8f,0.5f, 1f};
        float[] speculaer = {1f,1f,1f,1f};
        float[] shinning = {5f};


        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, ambient_and_diffuse,0);
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, speculaer,0);
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SHININESS, shinning,0);
        gl.glColor3f(0.199f,0.5976f,0.996f);
        drawSphere(6,20,20, gl);
        float[] shinning2 = {50f};
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, ambient_and_diffuse,0);
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, speculaer,0);
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SHININESS, shinning2,0);
        level_one2(gl);
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable,  int x,  int y, int width, int height) {
        final GL2 gl = glAutoDrawable.getGL().getGL2();

        // get the OpenGL 2 graphics object
        if(height <=0) height =1;

        //preventing devided by 0 exception height =1;
        final float h = (float) width / (float) height;

        // display area to cover the entire window
        gl.glViewport(0, 0, width, height);

        //transforming projection matrix
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        //   glu.gluPerspective(45.0f, h, 1.0, 20.0);


        //transforming model view gl.glLoadIdentity();
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        int newwidth= glcanvas.getSize().width;
        int newhight= glcanvas.getSize().height;

        //  System.out.println("i3" +i3);

        int oldxlabel=label.getX();
        int oldylabel=label.getY();
        int newxlabel=(int)(oldxlabel*((double)newwidth/width));
        int newylabel=(int)( oldylabel*((double)newhight/hight));

        label.setLocation(newxlabel,newylabel);
        label.setBounds(newxlabel,newylabel, 60, 60);
        System.out.println("label 1 : old : x : "+ oldxlabel +" y : " +oldylabel+" new  : x : "+newxlabel + " y : " + newylabel);

        label.setText("Eman");
        int oldxlabel2=label2.getX();
        int oldylabel2=label2.getY();
        int newxlabel2=(int)(oldxlabel2*((double)newwidth/width));
        int newylabel2=(int)( oldylabel2*((double)newhight/hight));
        label2.setLocation(newxlabel2,newylabel2);
        label2.setBounds(newxlabel2,newylabel2, 60, 60);
        System.out.println("label 2 : old : x : "+ oldxlabel2 +" y : " +oldylabel2+" new  : x : "+newxlabel2 + " y : " + newylabel2);
        label2.setText("Eman");

        int oldxlabel3=label3.getX();
        int oldylabel3=label3.getY();
        int newxlabel3=(int)(oldxlabel3*((double)newwidth/width));
        int newylabel3=(int)( oldylabel3*((double)newhight/hight));
        label3.setLocation(newxlabel3,newylabel3);
        label3.setBounds(newxlabel3,newylabel3, 60, 60);
        System.out.println("label3 : old : x : "+ oldxlabel3 +" y : " +oldylabel3+" new  : x : "+newxlabel3 + " y : " + newylabel3);
        hight=newhight;
        width=newwidth;
    }
    public Esay(String text) {
        super(Boolean.parseBoolean(text));
        Font font = new Font("Verdana", Font.ITALIC, 10);
        FontMetrics metrics = new FontMetrics(font){};
        Rectangle2D bounds = metrics.getStringBounds(text, null);
        setBounds(0, 0, (int) bounds.getWidth(), (int) bounds.getHeight());
    }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D gx = (Graphics2D) g;
        gx.rotate(0.6, getX() + getWidth()/2, getY() + getHeight()/2);
        super.paintComponent(g);
    }
    public void keyPressed(KeyEvent evt) {
        int width =50;
        int hight=50;
        int p=50;
        // column_label.setBounds((int)(Math.random()*300),100,50,50);
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            if(y!=0){
                y--;
                column_label.setBounds((int)(column_label.getBounds().getX()),(int)(column_label.getBounds().getY()-p),width,hight);
                column_label.setLocation(column_label.getX(),column_label.getY()-p);
                column_label.repaint();
                frame.repaint();
            }
            else return ;
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            if(y!=g.n-1){
                y++;

                column_label.setBounds((int)(column_label.getBounds().getX()),(int)(column_label.getBounds().getY()+p),width,hight);
                column_label.setLocation(column_label.getX(),column_label.getY()+p);
//                column_label.setAlignmentX(column_label.getAlignmentX()+p);
//                column_label.setAlignmentY(column_label.getAlignmentY()+p);
                column_label.repaint();
                frame.repaint();
            }
            else return ;
        } else if(evt.getKeyCode()==KeyEvent.VK_LEFT){
            if(x!=0){
                x--;
                column_label.setBounds((int)(column_label.getBounds().getX()-p),(int)(column_label.getBounds().getY()),width,hight);
                column_label.setLocation(column_label.getX()-p,column_label.getY());
                column_label.repaint();
                frame.repaint();
            }
            else return ;
        } else if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
            if(x!=g.n-1){
                x++;
                column_label.setBounds((int)(column_label.getBounds().getX()+p),(int)(column_label.getBounds().getY()),width,hight);
                column_label.setLocation(column_label.getX()+p,column_label.getY());
                column_label.repaint();
                frame.repaint();
            }
            else return ;
        }

        int exist =0 ;
        for(int i=0 ;i < playerPath0.size() ;i++){
            if(playerPath0.get(i).equals(x)&&playerPath1.get(i).equals(y)){
                System.out.println( playerPath0.remove(i) +" is removed") ;
                System.out.println( playerPath1.remove(i)+" is removed");
                exist=1;
            }
        }
        if(exist==0){
            playerPath0.add(x);
            playerPath1.add(y);
            System.out.println(x +" "+y+" is added");
        }
        numOfpress++;
        if(x==0||y==0||x==g.n-1||y==g.n-1&&playerPath0.size()%2==1){
            System.out.println("finish");
            calculateEquation();
            System.out.println("my result is : "+calculateResult());
            if(calculateResult()==g.calculateResult()){

                System.out.println("you win ");
                System.exit(0);
                Parent parent = null;
                try {
                    parent = FXMLLoader.load(getClass().getResource("YouWin.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
                stage.setTitle("Math Chess Game");
                stage.setScene(scene);
                stage.show();
                //
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("YouWin.fxml"));
//                AnchorPane pane = new AnchorPane();
//                try {
//                    pane = loader.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Scene scene = new Scene(pane);
//                Stage stage = new Stage();
//                stage.initStyle(StageStyle.UNDECORATED);
//                stage.setScene(scene);
//                stage.show();

            }

        }
        System.out.println("x : " +x +" y : "+y);
        System.out.println(g.gameArray[x][y]);
        System.out.println(playerEquation);
        System.out.println(playerPath0);
        System.out.println(playerPath1);

    }
    public void keyReleased(KeyEvent evt) {
    }

    public void keyTyped(KeyEvent evt) {
    }
    public boolean isFocusTraversable() {
        return true;
    }

}


 /*  if(evt.getKeyCode()==KeyEvent.VK_UP){
            if(y>g.halfsize){
                playerEquation.remove(playerEquation.size()-1);
            }else{
                playerEquation.add(g.gameArray[x][y]);
            }
            y--;
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            if(y<g.halfsize){
                playerEquation.remove(playerEquation.size()-1);
            }else{
                playerEquation.add(g.gameArray[x][y]);
            }
            y++;
        } else if(evt.getKeyCode()==KeyEvent.VK_LEFT){
            if(x>g.halfsize){
                playerEquation.remove(playerEquation.size()-1);
            }else{
                playerEquation.add(g.gameArray[x][y]);
            }
            x--;
        } else if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
            if(x<g.halfsize){
                playerEquation.remove(playerEquation.size()-1);
            }else{
                playerEquation.add(g.gameArray[x][y]);
            }
            x++;
        }*/

        /*oldy=y;
        oldx=x;
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            y--;
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            y++;
        } else if(evt.getKeyCode()==KeyEvent.VK_LEFT){
            x--;
        } else if(evt.getKeyCode()==KeyEvent.VK_RIGHT){
            x++;
        }
        if(x==oldx&&y==oldy){
            playerEquation.remove(playerEquation.size()-1);
        }else{
            playerEquation.add(g.gameArray[x][y]);
        }*/