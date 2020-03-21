package com.company;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game  implements   KeyListener, ChangeListener {

    //@FXML
    //Label l1;
    @FXML
    private AnchorPane anchropane;

char [] operations={'+','-','*','/','%','^'};
    static int n=9;
    int x=n/2;
    int y=n/2;
    int numOfOperations=6;
    int maxNum=10;
    String [][] gameArray=new String[n][n];
    ArrayList <String> equation = new ArrayList();
    int [][] indexesPath=new int[n-1][2];
    int halfsize=gameArray.length/2;

    //   @Override
    public  Game() {
        this.n=n;
        boolean x=true;
        long displayMinutes=0;
        long starttime=System.currentTimeMillis();
        System.out.println("Timer:");
      /*  while(x&&displayMinutes<=0)
        {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long timepassed=System.currentTimeMillis()-starttime;
            long secondspassed=timepassed/1000;
            if(secondspassed==60)
            {
                secondspassed=0;
                starttime=System.currentTimeMillis();
            }
            if((secondspassed%60)==0)
                displayMinutes++;

            System.out.println(displayMinutes+"::"+secondspassed);
        }*/
        for (int i=0 ; i<n;i++){
            for(int j=0;j<n;j++){
                Label tf=new Label("");
               if(i==0){
                   if(j==0||j==n-1)
                       continue;
               }else if(i==n-1){
                   if(j==0||j==n-1)
                       continue;
               } if(i==n/2 && j==n/2) {
                    tf = new Label("player");
                    gameArray[i][j]="player";
                    continue;
                }
                if(i%2==0){
                    if(j%2==0) {
                        gameArray[i][j]="" + operations[(int) (Math.random() * numOfOperations)];
                        tf = new Label("" + gameArray[i][j]);
                    }
                    else{
                        gameArray[i][j]=""+(int)(Math.random()*(maxNum-1)+1);
                        tf=new Label(""+gameArray[i][j]);

                }
                }
                else{
                    if(j%2==0){
                        gameArray[i][j] =""+(int)(Math.random()*(maxNum-1)+1);
                        tf=new Label(""+ gameArray[i][j]);
                    }
                    else{
                        gameArray[i][j]=""+operations[ (int)(Math.random()*numOfOperations)];
                        tf=new Label(""+gameArray[i][j]);
                    }
                }

                tf.setBounds(i*150 +20,j*100+20,50,50);
            }
        }

        indexesPath[0][0]=halfsize;
        indexesPath[0][1]=halfsize;

        boolean isTrue=false;
     while (true){
         calculatePath(gameArray.length, halfsize, halfsize, 1, indexesPath);
       for (int i=0;i<2;i++){
           for (int j=0;j<gameArray.length;j++){
               if(i==0){
                  if(indexesPath[indexesPath.length-1][0]==0&&indexesPath[indexesPath.length-1][1]==j||indexesPath[indexesPath.length-1][0]==j&&indexesPath[indexesPath.length-1][1]==0)
                      isTrue=true;
               }   else if(i==1){
                   if(indexesPath[indexesPath.length-1][0]==gameArray.length-1&&indexesPath[indexesPath.length-1][1]==j||indexesPath[indexesPath.length-1][0]==j&&indexesPath[indexesPath.length-1][1]==gameArray.length-1)
                       isTrue=true;
               }
               if(isTrue)
                   break;
           }
           if(isTrue)
               break;

       }
            if(isTrue){
                break;
            }
        }
        printIntegerTable(indexesPath);
        try {
            cleanTabelPower(2);
            cleanTabelDiv();;
        } catch (Exception e) {
            System.out.println("excption cathed 2");
            e.printStackTrace();
        }
        printGameTable();
     calculateEquation(indexesPath,gameArray);
     System.out.println(equation.toString());
     System.out.println(calculateResult());

    }

    public char[] getOperations() {
        return operations;
    }

    public void setOperations(char[] operations) {
        this.operations = operations;
    }

    public  int getN() {
        return n;
    }

    public  void setN(int n) {
        this.n = n;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNumOfOperations() {
        return numOfOperations;
    }

    public void setNumOfOperations(int numOfOperations) {
        this.numOfOperations = numOfOperations;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public  String[][] getGameArray() {
        return gameArray;
    }

    public  void setGameArray(String[][] gameArray) {
        this.gameArray = gameArray;
    }

    public ArrayList<String> getEquation() {
        return equation;
    }

    public void setEquation(ArrayList<String> equation) {
        this.equation = equation;
    }

    public int[][] getIndexesPath() {
        return indexesPath;
    }

    public void setIndexesPath(int[][] indexesPath) {
        this.indexesPath = indexesPath;
    }

    public int getHalfsize() {
        return halfsize;
    }

    public void setHalfsize(int halfsize) {
        this.halfsize = halfsize;
    }

    public void calculatePath(int size , int x, int y, int continueIndex, int [][] path){
        if (continueIndex>=path.length)
            return ;
        int mid=size/2;
        int [] Xindexes = {x,x+1,x-1};
        int newX = Xindexes[(int)(Math.random()*3)];
        int [] Yindexes = {y-1,y+1};
        int newY = Yindexes[(int)(Math.random()*2)];
        if(x==mid) {
            newX = Xindexes[(int) (Math.random() * 2)+1];
            if(y==mid){
                newX = Xindexes[(int) (Math.random() * 3)];
                if(newX==x)
                    newY=Yindexes[(int)(Math.random()*2)];
                else
                    newY=y;
            }
            else if(y<mid&&y>=0) {
                if(y==mid-1){
                    newX=Xindexes[(int)(Math.random()*3)];
                    if(newX==x){
                        newY=y-1;
                    }
                    else newY=y;
                }else if(y==0||y==size-1) {
                    newX = Xindexes[(int) (Math.random() * 2) + 1];
                    newY=y;
                }
                else {
                    newX=Xindexes[(int)(Math.random()*3)];
                    if(newX==x){
                        newY=Yindexes[(int)(Math.random()*2)];
                    }else {
                        newY=y;
                    }
                }
            }
           else if(y>mid&&y<size) {
                if(y==mid+1){
                    newX=Xindexes[(int)(Math.random()*3)];
                    if(newX==x){
                        newY=y+1;
                    }
                    else newY=y;
                }
                else {
                    newX=Xindexes[(int)(Math.random()*3)];
                    if(newX==x){
                        newY=Yindexes[(int)(Math.random()*2)];;
                    }
                    else newY=y;

                }
                if(y==0||y==size-1) {
                    newX = Xindexes[(int) (Math.random() * 2) + 1];
                    newY=y;
                }
           }
       }
            else if(x>mid&&x<=size&&y>=0&&y<size){
                if(x==mid+1&&y==mid){
                    newX=Xindexes[(int)(Math.random()*2)];;
                    if(newX ==x)
                        newY=Yindexes[(int)(Math.random()*2)];
                    else {
                      newY=y;
                    }
                    }
                else {
                    newX=Xindexes[(int)(Math.random()*3)];;
                    if(x==newX){
                        newY=Yindexes[(int)(Math.random()*2)];
                    }
                    else{
                        newY = y;
                    }
         }
            if(y==0||y==size-1) {
                newX = Xindexes[(int) (Math.random() * 2) + 1];
                newY=y;
            }
       }
            else if (x<mid&&x>=0&&y>=0&&y<size){
                if(x==mid-1&&y==mid){
                        newY=Yindexes[(int)(Math.random()*2)];
                        newX=x;
                }
                else {
                    int ran=(int)(Math.random() * 2);
                    if(ran==0){
                        newX=x;
                        newY=y+1;
                    }
                    else{
                        newX=x-1;
                        newY=y;
                    }
                    if(y==0||y==size-1) {
                        newX = Xindexes[(int) (Math.random() * 2) + 1];
                        newY=y;
                    }
                }
            }
       if(continueIndex>1){
        if(newX==path[continueIndex-2][0]&&newY==path[continueIndex-2][1]||newX==path[continueIndex-1][0]&&newY==path[continueIndex-1][1]) {
            calculatePath(size,newX,newY,continueIndex,path);
        }else{
            path[continueIndex][0] = newX;
            path[continueIndex][1] = newY;
            calculatePath(size,newX,newY,++continueIndex,path);
        }
       }else{
           path[continueIndex][0] = newX;
           path[continueIndex][1] = newY;
           calculatePath(size,newX,newY,++continueIndex,path);
       }
    }
    public void calculateEquation(int [][] path,String [][] game){
        for(int i=0;i<path.length;i++){
         //   System.out.println(game[path[i][0]][path[i][1]]);
            equation.add(game[path[i][0]][path[i][1]]);
        }
    }
    public int calculateResult(){
        int result=Integer.parseInt(equation.get(1));
        for(int i=2 ;i < equation.size();i++){
            if(equation.get(i).equals("+")){
                result+=Integer.parseInt(equation.get(++i));
            }else if(equation.get(i).equals("-")){
                result-=Integer.parseInt(equation.get(++i));
            }else if(equation.get(i).equals("*")){
                result*=Integer.parseInt(equation.get(++i));
            }else if(equation.get(i).equals("/")){
                result/=Integer.parseInt(equation.get(++i));
            }else if(equation.get(i).equals("%")){
                result%=Integer.parseInt(equation.get(++i));
            }else if(equation.get(i).equals("^")){
                result=(int)Math.pow(result,Integer.parseInt(equation.get(++i)));
            }

        }
        return result;
    }
    public void printGameTable(){
        for(int i=0;i<gameArray.length;i++){
            for (int j=0;j<gameArray.length;j++)
                System.out.printf("  %7s",gameArray[j][i]);
            System.out.println();
        }
    }

    public void printIntegerTable(int [][] gameArray){
        for(int i=0;i<gameArray.length;i++){
            System.out.print(i+"- ");
            for (int j=0;j<gameArray[i].length;j++)
                System.out.printf("  %5d",gameArray[i][j]);
            System.out.println();
        }
    }

    public void cleanTabelDiv() throws Exception{
        //try {
            for (int i = 1; i < n-1; i++) {
                for (int j = 1 ; j < n-1; j++) {
                    if (gameArray[i][j].equals("/")) {
                        System.out.println("i : "+ i + " j : "+ j+" is /");
                        if (i <= n / 2 && j <= n / 2) {
                            System.out.println("i : "+i +"< n/2  j : "+j +" < n/2" );
                            while (Integer.parseInt(gameArray[i - 1][j]) > Integer.parseInt(gameArray[i + 1][j]) ||
                                    Integer.parseInt(gameArray[i - 1][j]) > Integer.parseInt(gameArray[i][j + 1]) ||
                                    Integer.parseInt(gameArray[i][j - 1]) > Integer.parseInt(gameArray[i + 1][j]) ||
                                    Integer.parseInt(gameArray[i][j - 1]) > Integer.parseInt(gameArray[i][j + 1])) {

                                gameArray[i - 1][j] = "" + (int) (Math.random() * (maxNum - 1) + 1);
                                gameArray[i + 1][j] = "" + (int) (Math.random() * (maxNum - 1) + 1);
                                gameArray[i][j - 1] = "" + (int) (Math.random() * (maxNum - 1) + 1);
                                gameArray[i][j + 1] = "" + (int) (Math.random() * (maxNum - 1) + 1);
                                System.out.println("changed 1");
                            }

                        }
                        else  if (i < n / 2 && j >= n / 2) {
                            System.out.println("i : "+i +" < n/2  j : "+j +" > n/2" );
                            while (Integer.parseInt(gameArray[i][j-1]) < Integer.parseInt(gameArray[i - 1][j]) ||
                                    Integer.parseInt(gameArray[i][j-1]) < Integer.parseInt(gameArray[i][j + 1]) ||
                                    Integer.parseInt(gameArray[i+1][j]) < Integer.parseInt(gameArray[i - 1][j]) ||
                                    Integer.parseInt(gameArray[i+1][j]) < Integer.parseInt(gameArray[i][j + 1])) {

                                gameArray[i][j-1] = "" + (int) (Math.random() * (maxNum - 1) + 1);
                                gameArray[i + 1][j] = "" + (int) (Math.random() * (maxNum - 1) + 1);
                                gameArray[i - 1][j] = "" + (int) (Math.random() * (maxNum - 1) + 1);
                                gameArray[i][j + 1] = "" + (int) (Math.random() * (maxNum - 1) + 1);
                                System.out.println("changed 2 ");
                            }

                        }  else  if (i > n / 2 && j < n / 2) {
                            System.out.println("i : "+i +" > n/2  j : "+j +" < n/2" );
                            while (Integer.parseInt(gameArray[i][j-1]) > Integer.parseInt(gameArray[i - 1][j]) ||
                                    Integer.parseInt(gameArray[i][j-1]) > Integer.parseInt(gameArray[i][j + 1]) ||
                                    Integer.parseInt(gameArray[i+1][j]) > Integer.parseInt(gameArray[i - 1][j]) ||
                                    Integer.parseInt(gameArray[i+1][j]) > Integer.parseInt(gameArray[i][j + 1])) {

                                gameArray[i][j-1] = "" + (int) (Math.random() * (maxNum - 1) + 1);
                                gameArray[i + 1][j] = "" + (int) (Math.random() * (maxNum - 1) + 1);
                                gameArray[i - 1][j] = "" + (int) (Math.random() * (maxNum - 1) + 1);
                                gameArray[i][j + 1] = "" + (int) (Math.random() * (maxNum - 1) + 1);
                                System.out.println("changed 3 ");
                            }

                        } else  if (i > n / 2 && j > n / 2) {
                            System.out.println("i : "+i +" > n/2  j : "+j +" > n/2" );
                            while (Integer.parseInt(gameArray[i - 1][j]) < Integer.parseInt(gameArray[i + 1][j]) ||
                                    Integer.parseInt(gameArray[i - 1][j]) < Integer.parseInt(gameArray[i][j + 1]) ||
                                    Integer.parseInt(gameArray[i][j - 1]) < Integer.parseInt(gameArray[i + 1][j]) ||
                                    Integer.parseInt(gameArray[i][j - 1]) < Integer.parseInt(gameArray[i][j + 1])) {

                                gameArray[i - 1][j] = "" + (int) (Math.random() * (maxNum - 1) + 1);
                                gameArray[i + 1][j] = "" + (int) (Math.random() * (maxNum - 1) + 1);
                                gameArray[i][j - 1] = "" + (int) (Math.random() * (maxNum - 1) + 1);
                                gameArray[i][j + 1] = "" + (int) (Math.random() * (maxNum - 1) + 1);
                                System.out.println("changed 4");
                            }

                        }


                    }
                }
            }
    //    }catch (Exception ex){
      //      System.out.println("exption cathed");
       // }
    }
    public void cleanTabelPower(int num) throws Exception{
        //try {
        for (int i = 1; i < n-1; i++) {
            for (int j = 1 ; j < n-1; j++) {
                if (gameArray[i][j].equals("^")) {
                    System.out.println("i : "+ i + " j : "+ j+" is ^");
                    if (i <= n / 2 && j <= n / 2) {
                        int min=Math.min(Integer.parseInt(gameArray[i + 1][j]),Integer.parseInt(gameArray[i][j + 1]));
                        System.out.println("i : "+i +"< n/2  j : "+j +" < n/2" );
                        while (Integer.parseInt(gameArray[i - 1][j]) > min/num ||
                                Integer.parseInt(gameArray[i][j - 1]) > min/num) {
                            System.out.println(min);
                            gameArray[i - 1][j] = "" + (int) (Math.random() * (maxNum/num - 1) + 1);
                            gameArray[i + 1][j] = "" + (int) (Math.random() * (maxNum - 1) + 1);
                            gameArray[i][j - 1] = "" + (int) (Math.random() * (maxNum/num - 1) + 1);
                            gameArray[i][j + 1] = "" + (int) (Math.random() * (maxNum - 1) + 1);
                            min=Math.min(Integer.parseInt(gameArray[i + 1][j]),Integer.parseInt(gameArray[i][j + 1]));
                            System.out.println("changed 1");
                            printGameTable();
                        }

                    }
                    else  if (i < n / 2 && j >= n / 2) {
                        int min=Math.min(Integer.parseInt(gameArray[i + 1][j]),Integer.parseInt(gameArray[i][j - 1]));
                        System.out.println("i : "+i +"< n/2  j : "+j +" >= n/2" );
                        while (Integer.parseInt(gameArray[i][j+1]) > min/num ||
                                Integer.parseInt(gameArray[i-1][j]) > min/num) {
                            System.out.println(min);
                            gameArray[i][j-1] = "" + (int) (Math.random() * (maxNum - 1) + 1);
                            gameArray[i + 1][j] = "" + (int) (Math.random() * (maxNum - 1) + 1);
                            gameArray[i - 1][j] = "" + (int) (Math.random() * (maxNum/num - 1) + 1);
                            gameArray[i][j + 1] = "" + (int) (Math.random() * (maxNum/num - 1) + 1);
                            System.out.println("changed 2 ");
                            min=Math.min(Integer.parseInt(gameArray[i + 1][j]),Integer.parseInt(gameArray[i][j - 1]));
                            printGameTable();
                        }

                    }  else  if (i > n / 2 && j < n / 2) {
                        System.out.println("i : "+i +" > n/2  j : "+j +" < n/2" );
                        int min=Math.min(Integer.parseInt(gameArray[i - 1][j]),Integer.parseInt(gameArray[i][j + 1]));
                        while (Integer.parseInt(gameArray[i][j-1]) > min/num ||
                                Integer.parseInt(gameArray[i+1][j]) >min / num ) {
                            System.out.println(min);
                            gameArray[i][j-1] = "" + (int) (Math.random() * (maxNum/num - 1) + 1);
                            gameArray[i + 1][j] = "" + (int) (Math.random() * (maxNum/num - 1) + 1);
                            gameArray[i - 1][j] = "" + (int) (Math.random() * (maxNum - 1) + 1);
                            gameArray[i][j + 1] = "" + (int) (Math.random() * (maxNum - 1) + 1);
                            min=Math.min(Integer.parseInt(gameArray[i - 1][j]),Integer.parseInt(gameArray[i][j + 1]));
                            System.out.println("changed 3 ");
                            printGameTable();
                        }

                    } else  if (i > n / 2 && j > n / 2) {
                        System.out.println("i : "+i +" > n/2  j : "+j +" > n/2" );
                        int min=Math.min(Integer.parseInt(gameArray[i - 1][j]),Integer.parseInt(gameArray[i][j - 1]));
                        while (Integer.parseInt(gameArray[i + 1][j]) > min/num ||
                                Integer.parseInt(gameArray[i][j + 1]) > min/num  ) {
                            System.out.println(min);
                            gameArray[i - 1][j] = "" + (int) (Math.random() * (maxNum - 1) + 1);
                            gameArray[i + 1][j] = "" + (int) (Math.random() * (maxNum/num - 1) + 1);
                            gameArray[i][j - 1] = "" + (int) (Math.random() * (maxNum - 1) + 1);
                            gameArray[i][j + 1] = "" + (int) (Math.random() * (maxNum/num - 1) + 1);
                            min=Math.min(Integer.parseInt(gameArray[i - 1][j]),Integer.parseInt(gameArray[i][j - 1]));
                            System.out.println("changed 4");
                            printGameTable();
                        }

                    }


                }
            }
        }
        //    }catch (Exception ex){
        //      System.out.println("exption cathed");
        // }
    }
    public void DrawSceen(){
        for(int i=0 ; i< n ;i++){
            for(int j=0 ; j < n ;j++){
                TextField tf=new TextField();
                tf.onKeyPressedProperty().addListener(this);
                tf.setText(gameArray[i][j]);
                tf.setLayoutX(i*60 +20);
                tf.setLayoutY(j*60+20);
                tf.setPrefSize(50,50);
                anchropane.onKeyPressedProperty().addListener(this);
                anchropane.getChildren().add(tf);
            }
        }
    }

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

    }

    public void keyyTaypedd(javafx.scene.input.KeyEvent keyEvent) {
        x++;
        y++;
        System.out.println("x : " +x +" y : "+y);

    }

    public void keyyPressedd(javafx.scene.input.KeyEvent keyEvent) {
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
}
