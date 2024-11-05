package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class MioThread extends Thread {

    Socket s = new Socket();
    ArrayList<MioThread> threads;
    BufferedReader in;
    DataOutputStream out;
    ArrayList<String> NomiScelti;
    String nome;

    public MioThread(Socket s, ArrayList threads, ArrayList NomiScelti) throws IOException {
        this.s = s;
        this.threads = threads;
        this.NomiScelti = NomiScelti;
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        out = new DataOutputStream(s.getOutputStream());
    }

    public boolean verificaNome() throws IOException {
        for (int i = 0; i < NomiScelti.size(); i++) {
            if (nome.equals(NomiScelti.get(i))) {
                out.writeBytes("!Username già in uso\n");
                return true;
            }
        }
        NomiScelti.add(nome);
        return false;
    }

    public void comunicaJoin() throws IOException {
        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).sendMessage("Join:" + nome);
        }
    }

    public void run() {
        try {

            do {
                out.writeBytes("Inserisci lo username:\n");
                nome = in.readLine();
            } while (verificaNome());
            comunicaJoin();
            threads.add(this);

            s.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String msg) throws IOException{
        out.writeBytes(msg + "\n");
    }
}
