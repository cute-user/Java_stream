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
        sout.println("Hello, WHats you name?");
        String userName = sin.nextLine();
        System.out.println(userName);
        sout.println("Nice to meet you, " + userName);
    }
}
