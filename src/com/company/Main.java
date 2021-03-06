package com.company;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Waiting for connection");
        Socket socket = serverSocket.accept();
        System.out.println("Client connected");
        PrintStream sout = new PrintStream(socket.getOutputStream());
        Scanner sin = new Scanner(socket.getInputStream());

        HostOutputThread hostOutputThread = new HostOutputThread(sout);
        hostOutputThread.start();

        sout.println("Hello, WHats you name?");
        String userName = sin.nextLine();
        sout.println("Hi, " + userName);
        String input = sin.nextLine();
        while (!input.equals("Bye")) {
            System.out.println("[" + userName + "] " + input);
            input = sin.nextLine();
        }
        hostOutputThread.interrupt();
    }
}

class HostOutputThread extends Thread {
    Scanner in = new Scanner(System.in);
    PrintStream sout;
    HostOutputThread(PrintStream sout) {
        this.sout = sout;
    }
    public void run() {
        while (!interrupted()) {
            sout.println(in.nextLine());
        }
        sout.println("See youn soon");
    }
}
