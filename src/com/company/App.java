package com.company;
import javax.swing.*;

public class App extends JFrame {

    private static final int CANVAS_WIDTH = 640;
    private static final int CANVAS_HEIGHT = 480;
    private static final int FPS = 60;

    public App() {/*
        GLCanvas canvas = new JavaApplication1();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        final FPSAnimator animator = new FPSAnimator(canvas, FPS, true);

        this.getContentPane().add(canvas);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Thread() {
                    @Override
                    public void run() {
                        if (animator.isStarted()) animator.stop();
                        System.exit(0);
                    }
                }.start();
            }
        });
        this.setTitle("JOGL (GLCanvas)");
        this.pack();
        this.setVisible(true);
        animator.start();
        */
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }
}
