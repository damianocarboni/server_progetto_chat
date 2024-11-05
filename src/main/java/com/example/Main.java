package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main extends IOException {
    public static void main(String[] args) throws IOException {
        System.out.println("server avviato");
        ArrayList<MioThread> Threads = new ArrayList<MioThread>();
        ArrayList<String> NomiScelti = new ArrayList<String>();
        ServerSocket s1 = new ServerSocket(3000);
        

        do{
            Socket s = s1.accept();
            System.out.println("un client si è collegato");
            MioThread t = new MioThread(s, Threads, NomiScelti);
            t.start();
        } while(true);
    }
}