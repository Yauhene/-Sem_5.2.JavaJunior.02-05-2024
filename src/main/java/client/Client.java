package client;

import server.ServerMain;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);
        System.out.println("Введите ваш логин: ");
        String login = console.nextLine();

        Socket server = new Socket("localhost", ServerMain.SERVER_PORT);
        System.out.println("Подключение к серверу успешно");
        Scanner in = new Scanner(server.getInputStream());
        PrintWriter out = new PrintWriter(server.getOutputStream(), true);
//        Map<String, String> msg = new HashMap<>();
//        msg.put("login", login);
//        out.println(msg);
//
        out.println(login);

        // Поток на чтение
        new Thread(() -> {
            while (true) {
                String message = in.nextLine();
                System.out.println("Новое сообщение: " + message);

                // TODO: exit пока не реализовываем
            }
        }).start();

        // Поток на запись
        new Thread(() -> {
            while (true) {
                String inputFromConsole = console.nextLine();
                out.println(inputFromConsole);
            }
        }).start();
    }
}



