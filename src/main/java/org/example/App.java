package org.example;

import java.util.ArrayList;

public class App
{
    public static void main( String[] args )
    {
        Elaborazione el=new Elaborazione();
        el.createEl();
        Server server= new Server(1234);
    }
}
