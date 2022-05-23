package org.example;

import java.util.ArrayList;

public class Array {
    ArrayList<Wine> arr;
    Array(){
        arr=new ArrayList<Wine>();
    }

    void add_Wine(int id, String name, double price, String type){
        Wine a=new Wine(id,name, price, type);
        arr.add(a);
    }


    void crea_lista(){
        add_Wine(13,"Dom Perignon Vintage Moet & Chandon 2008",225.95,"white");
        add_Wine(124, "Pinot Nero Elena Walch Elena Walch 2018", 43, "red");
        add_Wine(14, "Pignoli Radikon Radikon 2009", 133.0, "red");

    }


    String sorted_by_price(){
        arr.sort((Wine p1, Wine p2)->(int) (p1.price-p2.price));
        String msg="Sorted by price: ";
        for (int i = 0; i < arr.size(); i++) {
            msg=msg+arr.get(i).toString();
        }
        return msg;
    }

}
