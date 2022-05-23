package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

public class Elaborazione extends Thread{
    Gson gson=new Gson();
    Socket clientSocket=null;
    ServerSocket serverSocket=null;
    static Array el;

    Elaborazione(){}

    Elaborazione(Socket clientSocket, ServerSocket serverSocket){
        this.clientSocket=clientSocket;
        this.serverSocket=serverSocket;
    }
    void createEl(){
        el=new Array();
        el.crea_lista();
    }
    @Override
    public void run(){
        BufferedReader input=null;
        try {
            input=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Errore buffered reader\n"+e.toString());
        }
        String messaggio="";
        PrintWriter output=null;
        try {
            output=new PrintWriter(clientSocket.getOutputStream(),true);
        } catch (IOException e) {
            System.out.println("Errore printwriter \n"+e.toString());
        }
        output.println("CONNESSO AL SERVER");

        try {
            while ((messaggio = input.readLine()) != null) {

                output.println(messaggio.toLowerCase(Locale.ROOT));
            }
        }catch(IOException e){
            System.out.println("Errore nella lettura\n"+e.toString());
        }
        System.out.println("Client disconnesso");
    }
}
