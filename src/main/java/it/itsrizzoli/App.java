package it.itsrizzoli;


import java.io.IOException;
import java.net.ServerSocket;

public class App
{
    static int HostN=1234;

    public static void main( String[] args )
    {
        ServerSocket server=null;
        try {
            server=new ServerSocket(HostN);
            System.out.println("Server started!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
