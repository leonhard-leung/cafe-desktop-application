package com.leonhard_leung;

import com.leonhard_leung.controller.ServerController;
import com.leonhard_leung.model.ServerModel;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8080;
    private static final ServerModel model = new ServerModel();

    public static void main(String[] args) {
        // start the server
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                // connect clients to the server
                Socket client = serverSocket.accept();

                // create a new thread that listens for requests to each client
                new Thread(() -> handleClient(client)).start();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    } // end of main

    public static void handleClient(Socket client) {
        ServerController controller = new ServerController(model);

        try (
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
        ) {

            while (true) {
                try {
                    // read the request from the client
                    Object request = in.readObject();

                    // process the request and send the response back to the client if request is not null
                    if (request != null) {
                        Object response = controller.processRequest(request);

                        out.writeObject(response);
                        out.flush();
                        out.reset();
                    }
                } catch (EOFException eofException) {
                    // stop the loop if exception is caught
                    System.out.println("Client has closed the connection");
                    break;
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    } // end of handleClient
} // end of Server class
