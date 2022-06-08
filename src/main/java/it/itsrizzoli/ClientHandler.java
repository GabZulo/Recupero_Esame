package it.itsrizzoli;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{

    Socket Client;
    BufferedReader in;
    PrintWriter out;
    static ArrayList<Auto> cars=new ArrayList<Auto>();
    @Override
    public void run() {
        this.buildList();
        this.clientToClientHandler();
        this.clientHandlerToClient();
    }

    private void clientToClientHandler() {
        try {
            in=new BufferedReader(
                    new InputStreamReader(Client.getInputStream())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out=new PrintWriter(Client.getOutputStream(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clientHandlerToClient() {
        Gson gson=new Gson();
        String s;
        while (true){
            s=receive();
            try{
                switch (s) {
                    default:
                        out.println(s + "non è un comando accettabile!");
                        break;
                    case "more_expensive":
                        out.println(gson.toJson(MaxPrice()));
                        break;
                    case "all":
                        out.println(cars);
                        break;
                    case "all_sorted":
                        sort_by_brand();
                        out.println(cars);
                        break;
                }
            }catch (NullPointerException e){
                System.out.println("Client disconnected!");
                break;
            }
            if (s=="") break;

        }
    }

    void sort_by_brand() {
        cars.sort((o1, o2) -> {
            return o1.getBrand().compareTo(o2.getBrand());
        });
    }

    Auto MaxPrice() {
        sort_by_price();
        return cars.get(0);
    }

    void sort_by_price() {
        cars.sort((o1,o2) -> {
            if (o1.getPrice()<o2.getPrice())
                return 1;
            if (o1.getPrice()>o2.getPrice())
                return -1;
            return 0;
        });
    }


    String receive() {
        String s="";
        try {
            s=in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    static void buildList(){
        cars.add(new Auto(123,"bmw",35949.2));
        cars.add(new Auto(3634,"audi",383469.1));
        cars.add(new Auto(135,"ferrari",1300004.10));
        System.out.println(cars);
    }

    ClientHandler(Socket client) {
        this.Client = client;
    }

}

