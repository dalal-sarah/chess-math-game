package com.company;
import java.util.TimerTask;


class Helper extends TimerTask
{
    public static int i = 0;
    public void run()
    {

            System.out.println("Timer ran " + ++i);

    }

}