package org.example;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


public class Client {

    private Socket clientSocket;
    private static BufferedReader in = null;
    private static PrintWriter out = null;

    public Client(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void handle() {
        in = allocateReader(clientSocket);
        out = allocateWriter(clientSocket);
        buildProductList();
        handleInput();
    }

    private BufferedReader allocateReader(Socket clientSocket) {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Reader failed" + e);
            return null;
        } return in;
    }

    private PrintWriter allocateWriter(Socket clientSocket) {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } return out;
    }

    public void handleInput() {
        String userInput;
        try {
            while ((userInput = in.readLine()) != null) {
                System.out.println("Client: " + (userInput));
                out.println(process(userInput));
            }
        } catch (IOException e) {
            System.out.println("Client disconnected");
        }
    }

    static ArrayList<Wine> wines = new ArrayList<>();


    static void buildProductList() {
        wines.add(new Wine(13,"Dom Perignon Vintage Moet & Chadon 2009",225.94, "white"));
        wines.add(new Wine(124,"Pinot Nero Elena Walch Elena Walch 2018",43.0, "red"));
        wines.add(new Wine(14,"Pignoli Radikon Radikon 2009",133.0, "red"));
    }

    private void cheapest() {
        wines.sort((Wine product1, Wine product2) -> {
            if (product1.getPrice() > product2.getPrice())
                return 1;
            if (product1.getPrice() < product2.getPrice())
                return -1;
            return 0;
        });
    }

    private void sortName() {
        wines.sort((Wine product1, Wine product2) -> {
            return product1.getName().compareTo(product2.getName());
        });
    }


    public ArrayList red(ArrayList red) {
        for (int i = 0; i < wines.size(); i++) {
            if (wines.get(i).type == "red") {
                red.add(wines.get(i));
            }
        }
        return red;


    }

    public ArrayList white(ArrayList white) {
        for (int i = 0; i < wines.size(); i++) {
            if (wines.get(i).type == "white") {
                white.add(wines.get(i));
            }
        }
        return white;
    }







    public String process(String input) {

        Gson gson = new Gson();

        String frase;
        String risultato;

        if (input.equals("sorted_by_price")) {

            frase = "Lista ordinata di tutti i prodotti in base al prezzo: ";

            cheapest();
            risultato = gson.toJson(wines);
            return frase + risultato;
            }

            else if (input.equals("all")) {
                frase = "Lista di tutti i vini: ";
                risultato = gson.toJson(wines);

                return frase + risultato;}

            else if (input.equals("sorted_by_name")) {
                frase = "Lista ordinata di tutti i prodotti in base al nome: ";

                sortName();
                risultato = gson.toJson(wines);
                return frase + risultato;


            } else {
            risultato = "Comandi: 'all' | 'red' | 'white' | 'sorted_by_price' | 'sorted_by_name' ";
            return risultato;
        }

    }



}